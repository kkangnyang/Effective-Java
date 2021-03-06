# ITEM28. 배열보다는 리스트를 사용하라

배열과 제너릭 타입에는 다음과 같은 차이점이 있다.

- 배열은 공변(covariant)이다. Sub가 Super의 하위타입이라면, Sub[]은 Super[]의 하위 타입이 된다. 반면, List와 List은 아무 상관이 없다.
- 배열은 실체화(relify)가 된다. 런타임 시 배열은 자신이 담고 있는 원소의 Type을 알고 있지만, 제너릭은 소거된다.

배열은 제너릭 타입, parametrized type, 타입 매개변수로 사용할 수 없다. 왜냐하면 Type Safety 하지 않기 때문이다.

new List<E>[], new List<String>[], new E[] 식으로 작성하면 컴파일할 때 제너릭 배열 생성 오류를 일으킨다.

```
Stack,java:8: generic array creation
elements = new E[DEFAULT_INITIAL_CAPACITY];
```

이 챕터에서 기억할 점은, 세 가지가 있다.
- 제너릭은 런타임 시 그 타입이 소거되고 배열은 타입을 기억하고 있어서 모두 컴파일이 된다.
- 제너릭 타입의 취지는 런타임에 ClassCastException의 발생을 막아주는 것인데, 제너릭 타입의 배열을 생성할 수 있다면 이 취지에 반하게 된다.
- 소거 매커니즘 때문에 실체화가 가능한 parameterized type은 List<?>와 Map<?,?>와 같은 비한정적 와일드카드 뿐이다.

## 제너릭 배열 생성이 안되는 이유
만약 제너릭 타입을 담고 있는 배열을 생성할 수 있다고 가정해보자. (1) 코드는 실제로 컴파일되지 않는다.

```java
public static void main(String[] args) {
	List<String> stringLists = new List<String>()[1];	// (1)
	List<Integer> intList = List.of(42);				// (2)
	Object[] objects = stringLists;						// (3)
	objects[0] = intList;								// (4)
	String s = stringLists[0].get(0);					// (5)
}
```

(3) : (1)에서 생성한 List<String>을 원소로 담고 있는 배열을 Object[]에 할당한다. List<String> 또한 Object이기 때문에 할당할 수 있다.

(4) : (2)에서 생성한 List<Integer>의 인스턴스를 Object[]배열의 첫 원소로 저장한다. 

**제너릭은 소거방식이기 때문에 컴파일 시 List의 타입이 단순히 List가 되기 때문에 `ArrayStoreException`이 발생하지 않는다.**

이렇게 되면 List<String> 인스턴스만 담겠다고 선언한 stringLists 배열에 List의 인스턴스가 저장돼 있다. 그리고 (5)에서 `ClassCastException`이 발생한다.


## 배열보다 리스트를 권장하는 이유
이 챕터에서 배열보다 리스트를 권장하는 이유는 위에서 알아본 것 처럼 Type Safety 때문도 있고, 또 형변환할 때 제너릭 배열 생성 오류나 unchecked exception이 경고로 뜨는 경우가 있기 때문이다.

다음과 같이 컬렉션 안의 원소를 랜덤으로 반환하는 메서드가 있다고 해보자.

```java
public class Chooser {
	private final Object[] choiceArray;
	
	public Chooser(Collection choices) { // 로타입을 썼다.
		choiceArray = choices.toArray();
	}
	
	public Object choose() {
		Random rnd = ThreadLocalRandom.current();
		return choiceArray[rnd.nextInt(choiceArray.length)]; // 여기서 형변환 오류가 날 수 있다.
	}

}
```

형변환 오류를 막기 위해 제너릭 클래스로 만들자.

```java
public class GenericChooser<T> {
	private final T[] choiceArray;
	
	public GenericChooser(Collection<T> choices) {
		choiceArray = choices.toArray(); // compile error
	}
	
	public Object choose() {
		Random rnd = ThreadLocalRandom.current();
		return choiceArray[rnd.nextInt(choiceArray.length)]; // 여기서 형변환 오류가 날 수 있다.
	}
}
```

>Object[] cannot be converted to T[]

위 에러가 뜨면서 컴파일조차되지 않는다. Object[]를 T[]로 형변환하면 되지만 경고가 뜨는데, Unchecked cast exception 의 경고가 뜬다.

> Type safety: Unchecked cast from Object[] to T[]

T가 무슨 타입인지 알 수 없으니 컴파일러 입장에서는 이 형변환이 안전한지 보장할 수 없다는 메시지다. 이러한 경고 메시지를 없애고자 하면 T[] 보다는 리스트를 쓰면 된다.

```java
public class GenericChooser<T> {
	//private final T[] choiceArray;
	private final List<T> choiceList;
	
	public GenericChooser(Collection<T> choices) {
		//choiceArray = choices.toArray(); // compile error
		choiceList = new ArrayList<>(choices);
	}
	
	public Object choose() {
		Random rnd = ThreadLocalRandom.current();
		//return choiceArray[rnd.nextInt(choiceArray.length)]; // 여기서 형변환 오류가 날 수 있다.
		return choiceList.get(rnd.nextInt(choiceList.size()));
	}
}
```
