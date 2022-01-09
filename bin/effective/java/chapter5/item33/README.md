# ITEM33. 타입 안전 이종 컨테이너를 고려하라

제네릭은 Set<E>, Map<K,V> 등의 컬렉션과 ThreadLocal<T>, AtomicReference<T> 등의 단일원소 컨테이너에도 흔히 쓰인다.

예컨대 Set에는 원소의 타입을 뜻하는 단 하나의 타입 매개변수만 있으면 되며, Map에는 키와 값의 타입을 뜻하는 2개만 필요한 식으로 하나의 컨테이너에서 매개변수화할 수 있는 타입의 수가 제한된다.

하지만 더 유연한 수단이 필요할 때도 종종 있다.

데이터베이스의 행은 임의 개수의 열을 가질 수 있는데, 모두 열을 타입 안전하게 이용할 수 있다면 멋질 것이다.

쉬운 해법은 컨테이너 대신 키를 매개변수화한 다음, 컨테이너에 값을 넣거나 뺄 때 매개변수화한 키를 함께 제공하면 된다.

이렇게 하면 제네릭 타입 시스템이 값의 타입이 키와 같음을 보장해줄 것이며 이런 설계 방식을 타입 안전 이종 컨테이너 패턴이라 한다.

> 타입 안전 이종 컨테이너 패턴 (type safe heterogeneous container pattern)

컨테이너에 값을 넣거나 뺄 때 매개변수화한 키를 함께 제공합니다. 
즉, 각 타입의 Class 객체를 매개변수화한 키 역할로 사용하는 것입니다. 
class 리터럴의 타입은 Class가 아닌 Class<T>입니다. 
예를 들어, String.class의 타입은 Class<String>이고 Integer.class의 타입은 Class<Integer>입니다.

컴파일타임 타입 정보와 런타임 타입 정보를 알아내기 위해 메서드들이 주고받는 class 리터럴을 타입 토큰(type token)이라 합니다.

```java
public class Favorites {
	
	private Map<Class<?>, Object> favorites = new HashMap<>();
	
	public <T> T getFavorites(Class<T> type) {
		return type.cast(favorites.get(type));
	}

	public <T> void putFavorites(Class<T> type, T instance) {
		favorites.put(Objects.requireNonNull(type), instance);
	}

	public static void main(String[] args) {
		Favorites f = new Favorites();
		
		f.putFavorites(String.class, "Java");
		f.putFavorites(Integer.class, 0xcafebabe);
		f.putFavorites(Class.class, Favorites.class);
		
		String fovoriteString = f.getFavorites(String.class);
		int favoriteInteger = f.getFavorites(Integer.class);
		Class<?> favoriteClass = f.getFavorites(Class.class);
		
		System.out.printf("%s %x %s%n", fovoriteString, favoriteInteger, favoriteClass.getName());;
	}

}
```

Favorites 인스턴스는 타입 안전합니다. String을 요청했는데 Integer를 반환하는 일은 절대 없습니다. 또한 모든 키의 타입이 제각각이라, 일반적인 맵과 달리 여러 가지 타입의 원소를 담을 수 있습니다.

favorites의 타입은 Map<Class<?>, Object>로, 키가 와일드카드 타입입니다. 이는 모든 키가 서로 다른 매개변수화 타입일 수 있다는 뜻 입니다. 값의 타입은 Object이기 때문에 Class의 cast() 메서드를 사용해 해당 객체 참조를 Class 객체가 가리키는 타입으로 동적 형변환을 해야합니다.

cast() 메서드의 시그니처를 보면 Class 클래스가 제네릭이라는 점을 활용하고 있습니다. 이를 통해 T로 비검사 형변환하는 손실 없이도 Favorites를 타입 안전하게 만들 수 있습니다.

```java
public class Class<T> {
    T cast(Object obj);
}
```


> 타입 안전 이종 컨테이너의 제약

1. 악의적인 클라이언트가 Class 객체를 (제네릭이 아닌) 로 타입으로 넘기면 Favorites 인스턴스의 타입 안전성이 쉽게 깨집니다.

```java
f.putFavorite((Class)Integer.class, "Integer의 인스턴스가 아닙니다");
int favoriteInteger = f.getFavorite(Integer.class);
```

하지만 이렇게 짜여진 클라이언트 코드에서는 컴파일할 때 비검사 경고가 뜰 것입니다.
 
HashSet과 HashMap 등의 일반 컬렉션 구현체에도 로타입을 사용하면 HashSet<Integer>에 String을 넣을 수 있다는 문제가 있습니다.

```java
HashSet<Integer> set = new HashSet<>();
((HashSet)set).add("문자열입니다.");
```

앞서 보았던 Favorites 객체가 타입 불변식을 어기는 일이 없도록 보장하려면 putFavorite() 메서드에서 인수루 주어진 instance의 타입이 type으로 명시한 타입과 같은지 확인하면 됩니다. 다음 코드와 같이 동적 형변환을 사용하면 됩니다.

```java
public <T> void putFavorite(Class<T> type, T instance) {
    favorites.put(Objects.requireNonNull(type), type.cast(instance));
}
```

2. 타입 안전 이종 컨테이너는 실체화 불가 타입에는 사용할 수 없습니다. 즉, String이나 String[]은 사용할 수 있지만 List<String>은 실체화 불가 타입이므로 사용할 수 없습니다.

List<String>과 List<Integer>는 List.class라는 같은 Class 객체를 공유합니다.


> 한정적 타입 토큰

Favorites가 사용하는 타입 토큰은 비한정적입니다. 때로는 이 메서드들이 허용하는 타입을 제한하고 싶을 수도 있는데, 한정적 타입 토큰을 활용하면 됩니다.

한정적 타입 토큰이란 단순히 **한정적 타입 매개변수**나 **한정적 와일드카드**를 사용하여 표현 가능한 타입을 제한하는 타입 토큰입니다.



