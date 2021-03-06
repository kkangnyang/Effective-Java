# ITEM26. 로(Raw) 타입은 사용하지 말라

## Raw Type이란
class와 interface 선언에 type parameter가 쓰이면, 이를 제네릭 클래스 혹은 제네릭 인터페이스라 한다.

```java
public interface List<E> extends Collection<E> {...}
```

List 인터페이스는 원소의 타입을 나타내는 type parameter E를 받는다.

List인터페이스를 쓸 때, 타입을 E의 위치에 넣어서 사용한다.
List<String>은 원소의 타입이 String인 리스트를 뜻하는 parameterized type이다. -> 매개변수로 변한 타입이라는 뜻

이렇게 제네렉 타입을 하나 정의하면 그에 딸린 raw type도 함께 정의된다.

- Raw Type : 제네릭 타입에서 타입 매개변수를 전혀 사용하지 않을 때를 말한다.

List<E>의 Raw Type은 매개변수가 없는 List 그 자체이다.

제네릭은 자바5부터 사용할 수 있다. Raw Type은 제네릭을 지원하기 전의 코드에 대한 호환의 목적인 궁여지책이라 할 수 있다.

```java
List list = new ArrayList(); // Raw type
List<Integer> listIntegers = new ArrayList<>(); // parameterized type
```

Raw Type은 자바5 이전 generic이 없던 레거시 코드에 사용될 수 있다. 하지만, 우리는 이제부터 사용하면 안된다! 

이유:
- Not expressive
- They lack type safety, and
- Problems are observed at run time and not at compile time

## 컴파일 타임이 아닌 런타임에 에러를 인지한다.

### 로 타입 선언에서의 예

```java
public class RawType {
	
	static class Stamp {
		public Stamp() {
			
		}
	}
	
	static class Coin {
		public Coin() {
			
		}
	}
	
	private final static Collection stamps = new ArrayList<>();
	
	public static void main(String[] args) {
		stamps.add(new Coin());
	}

}
```

위 코드를 설명하면 아래와 같다

- Stamp, Coin 클래스
- Collection 로 타입으로 stamps 선언(개발자는 Stamp라는 클래스만 담는다는 의미로 해당 변수 선언)
- 실수로 Stamp 대신 Coin 추가

이 떄, 실수로 Stamp 대신 Coin을 해당 컬렉션에 넣어도 아래와 같은 경고창만 보여주고 오류 없이 컴파일되고 실행된다.

>Type safety: The method add(Object) belongs to the raw type Collection. References to generic type Collection should be parameterized


자 그럼, Coin이 담겨져 있는 stamps에서 원소를 꺼내오고, (당연히 Stamp가 들어가 있을 거라고 생각한 개발자가) Stamp로 형변환 해보자.

```java
public static void main(String[] args) {
	stamps.add(new Coin());
	
	for (Iterator iterator = stamps.iterator(); iterator.hasNext();) {
		Stamp stamp = (Stamp) iterator.next(); // ClassCaseException!
		
	}
}
```

>java.lang.ClassCastException: ch5.sunmin.item26.RawType$Coin cannot be cast to ch5.sunmin.item26.RawType$Stamp

즉, 컬랙션에서 이 동전을 다시 꺼내기 전에는 오류를 알아채지 못한다. 컴파일 타임이 아닌 런타임에 에러를 인지한다.


### 제너릭을 활용해서 타입 선언

```java
private final static Collection<Stamp> stampsGeneric = new ArrayList<>();
```

이렇게 제너릭을 사용해서 타입을 매개변수로 지정해주면, 컴파일러에서 Stamp의 인스턴스만 넣어야 함을 인지하게 된다. 따라서 의도대로 동작할 것임을 보장한다.

```java
public static void main(String[] args) {
	stampsGeneric.add(new Coin()); // compile error
}
```
이제 똑같이 잘못된 클래스를 넣을 경우, 컴파일 조차도 안되게끔 에러를 보여주는 것을 볼 수 있다.

### 로 타입을 파라미터로 쓰는 메서드에서의 예

로타입인 List를 받는 메서드에 List<String>을 넘길 수는 있다. 왜냐하면 List<String>은 로 타입인 List의 하위 타입이기 때문이다.

```java
public class RawTypeMethod {
	
	private static void unsafeAdd(List list, Object o) {
		list.add(o);
	}

	public static void main(String[] args) {
		List<String> strings = new ArrayList<>();
		unsafeAdd(strings, Integer.valueOf(42));
		String s = strings.get(0);
	}
}
```

하지만 실행을 하면 컴파일은 정상이지만 Integer 를 String으로 변환하려 시도하기 때문에 ClassCastException을 던지게 된다.

이제 unsafeAdd 메서드의 파라미터를 List에서 List<Object>로 바꿔보자.

```java
public class GenericTypeMethod {

	private static void unsafeAdd(List<Object> list, Object o) {
		list.add(o);
	}

	public static void main(String[] args) {
		List<String> strings = new ArrayList<>();
		unsafeAdd(strings, Integer.valueOf(42)); // compile error
		String s = strings.get(0);
	}

}
```

아예 컴파일이 되지 않는다. 왜냐하면 List<String>은 List<Object>의 하위 타입은 아니기 때문이다. [아이템28]

정리하자면, 로 타입인 List는 모든 타입의 원소를 넘겨받을 수 있지만, 매개변수화 타입인 List<Object>는 List<Object>로만 파라미터를 받을 수 있다.

이렇게 하면 Type Safety 하게 코드를 작성할 수 있지만, 아예 원소의 타입을 몰라도 되는 로 타입을 쓰고 싶을 수 있다.

## 비한정적 와일드카드 타입

```java
private static int numElementsInCommon(Set s1, Set s2) {
	int result = 0;
	for (Object o1 : s1) {
		if (s2.contains(o1)) {
			result++;
		}
	}
	return result;
}
```

로타입인 Set 을 파라미터로 받아서, 모르는 타입의 원소도 받을 수 있도록 작성했다. 메서드는 정상 작동하지만 Type Safety 하지 못하다. (위의 예시처럼 다른 Type이 들어왔을 때 ClassCastException) 이럴 때는 비한정적 와일드카드 타입 (unbounded wildcard type) 을 대신 사용하는게 좋다.

제네릭 타입을 쓰고는 싶지만 실제 타입 매개변수가 무엇인지 신경 쓰고 싶지 않다면 unbounded wildcard type을 사용하면 된다.

```java
private static int numElementsByWildCardInCommon(Set<?> s1, Set<?> s2) {
	s1.add("Not Inter String");
}

public static void main(String[] args) {
	Set<Integer> set1 = new HashSet<>();
	set1.add(Integer.valueOf(10));
	Set<Integer> set2 = new HashSet<>();
	set2.add(Integer.valueOf(25));
	
	System.out.println(numElementsByWildCardInCommon(set1, set2));

}
```
>The method add(capture#1-of ?) in the type Set<capture#1-of ?> is not applicable for the arguments (String)

위 소스는 Set에 Integer를 넣고, 메서드에 파라미터로 전송했다. 그 Set에 String을 다시 넣으려면 아래와 같은 오류로 컴파일되지 않는다. 따라서 그냥 로타입 보다는 Type Safe 하다고 볼 수 있다.

## 로 타입을 허용하는 예외

### class 리터럴

자바 명세는 parameterized type을 배열과 기본타입에는 허용하지만 List<String>.class 와 List<?>.class 와 같은 class 리터럴에 매개변수화 타입을 사용하지 못하게 했다.

참고. https://homoefficio.github.io/2016/11/30/%ED%81%B4%EB%9E%98%EC%8A%A4-%EB%A6%AC%ED%84%B0%EB%9F%B4-%ED%83%80%EC%9E%85-%ED%86%A0%ED%81%B0-%EC%88%98%ED%8D%BC-%ED%83%80%EC%9E%85-%ED%86%A0%ED%81%B0/

- 클래스 리터럴 (Class Literal)은 String.class, Integer.class 등을 말하며 하나의 객체로 생각하면 된다. 그러면, 이들의 타입으로는 .. String.class 의 타입은 Class<String>, Integer.class의 타입은 Class<Integer> 로 볼 수 있다.

- 그러면 Class<T> 가 로타입인 Class의 매개변수화 타입이라는 것을 유추해볼 수 있다.

Class에 제너릭을 추가하여 Class<T> 처럼 매개변수화 타입을 파라미터로 받는 예이다.

```java
public class SimpleTypeSafeMap {
	
	private Map<Class<?>, Object> map = new HashMap<>();

    public <T> void put(Class<T> k, T v) {
        map.put(k, v);
    }

    public <T> T get(Class<T> k) {
        return k.cast(map.get(k));
    }
	
	public static void main(String[] args) {
		SimpleTypeSafeMap simpleTypeSafeMap = new SimpleTypeSafeMap();

		simpleTypeSafeMap.put(String.class, "abcde");
		simpleTypeSafeMap.put(Integer.class, 123);

		String v1 = simpleTypeSafeMap.get(String.class);
		Integer v2 = simpleTypeSafeMap.get(Integer.class);

		// 아래와 같은 List<String>.class라는 클래스 리터럴은 언어에서 지원해주지 않으므로 사용 불가!!
		simpleTypeSafeMap.put(List<String>.class, Arrays.asList("a", "b", "c"));
	}

}
```

Class<T> 제너릭 타입으로, 다양한 Type을 Map에 put 하고 get 할때는 그 클래스 리터럴 자체로 casting을 할 수 있는 Map 클래스이다.

여기서 List.class, String[].class, int.class 와 같은 클래스 리터럴은 Class<T>에 허용하고, List<String>.class 와 List<?>.class는 허용하지 않는다


### instanceof 연산자

런타임에는 제너릭 타입 정보가 지워지므로 instanceof 연산자는 비한정적 와일드카드 타입 이외의 매개변수화 타입에는 적용할 수 없다.

```java
public static void main(String[] args) {
	Object o = new Object();
	
	if (o instanceof Set) { // 로타입
		Set<?> s = (Set<?>) o; // 와일드타입으로 형변환
	}
	
	if (o instanceof Set<String>) { // 제너릭 -> 오류 발생!
		Set<?> s = (Set<?>) o; // 와일드타입으로 형변환
	}
}
```