# ITEM44. 표준 함수형 인터페이스를 사용하라

LinkedHashMap 클래스를 보면 아래와 같은 메서드가 있다. 
맵에 새로운 키를 추가하는 put 메서드가 수행되면 이 메서드를 호출해서 true가 반환되면 맵에서 가장 오래된 원소를 제거한다.

```java
// LinkedHashMap.java
protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
    return false;
}
```

이 메서드를 조금 변형해서 아래와 같은 조건을 주었다.

```java
// LinkedHashMap.java
protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
    return size() > 100;
}
```

원소가 100개가 넘어가면 가장 오래된 원소를 제거할 수 있도록 만든 것이다. 이처럼 특정 동작을 할 수 있는 함수 객체를 removeEldestEntry 로 넘길 수 있으면 보다 유연한 구조가 될것이다. 하지만 생성자에 넘기는 함수 객체는 맵의 인스턴스 메서드가 아니다. 팩터리나 생성자를 호출할 때는 맵의 인스턴스가 존재하지 않기 때문이다. 따라서 맵은 자기 자신도 함수 객체에 건네줘야 한다.

```java
@FunctionalInterface
interface EldestEntryRemovalFunction<K, V> {
  boolean remove(Map<K,V> map, Map.Entry<K,V> eldest);
}
```

이렇게 선언한 함수형 인터페이스를 람다객체로 만들어서 파라미터로 전달하게 구현할 수 있다. 


하지만 이런 형태의 인터페이스는 java.util.function 패키지에 준비되어 있으므로 굳이 새로 만들어서 쓰지 않아도 된다.


앞서 만든 함수형 인터페이스는 표준 인터페이스인 BiPredicate<Map<K,V>, Map.Entry<K,V>>를 사용할 수 있다.


## 표준 함수형 인터페이스 종류

기본 인터페이스 6개만 기억하면 나머지를 충분히 유추해낼 수 있다.

### 기본 인터페이스

|인터페이스 |함수 시그니처 |예 |
|UnaryOperator | T apply(T t) |String::toLowerCase |
|BinaryOperator | T apply(T t1, T t2) | BigInteger::add |
|Predicate | boolean test(T t) | Collection::isEmpty |
|Function<T,R> | R apply(T t) | Arrays::asList |
|Supplier | T get() | Instant::now |
|Consumer | void accept(T t) | System.out::pringln |

- Operator: 반환값과 인수의 타입이 같은 함수
- Predecate: boolean 반환
- Function: 인수를 받지 않고 값을 반환
- Supplier: 인수를 받지 않고 값을 반환
- Consumer: 인수를 하나 받고 반환값은 없는 함수


### 기본 타입 인터페이스

기본타입인 int, long, double 용으로 각 3개씩 변형이 생긴다.

- IntPredicate
- LongBinaryOperator
- DoubleBinaryOperator

Function의 변형만 매개변수화됬다. return type이 매개변수화 됬는데, 예를 들어 LongFunction<int[]>는 long인수를 받아 int[]를 반환한다.


### Function 인터페이스 변형

- 입력과 결과 타입이 모두 기본 타입이면 접두어로 SrcToResult를 사용한다.
	- long을 받아 int를 반환한다면 LongToIntFunction이 된다.
- 입력이 객체 참조이고, 결과가 기본타입인 경우 접두어로 ToResult를 사용한다.
	- int[] 인수를 받아 long을 반환하면 ToLongFunction<int[]> 가 된다.
	

### BooleanSupplier
boolean을 반환하도록 하는 Supplier의 변형이다.



표준 함수형 인터페이스 대부분은 기본 타입만 지원한다. 그렇다고 기본 함수형 인터페이스에 박싱된 기본 타입을 넣어 사용하지는 말자 (아이템61)


구조적으로 똑같은 표준 함수형 인터페이스가 있더라도 직접 작성해야 할 때가 있다. Comparator<T> 인터페이스는 구조적으로는 ToIntBiFunction<T,U>와 동일하다. 하지만 아래와 같은 이유로 따로 빠진 케이스다

- 자주 쓰이며, 이름 자체가 용도를 명확히 설명해준다.
- 반드시 따라야 하는 규약이 있다.
- 유용한 디폴트 메서드를 제공할 수 있다.

직접 만든 함수형 인터페이스에는 항상 @FunctionalInterface 애너테이션을 사용하라.