# ITEM55. 옵셔널 반환은 신중히 하라

### 자바 8 이전

자바 8 전에는 메서드가 특정 조건에서 값을 반환할 수 없을 때 두가지 선택지가 있었다.

1. Exception Throw
- 예외는 반드시 예외적인 상황에서만 사용해야 한다.
- 예외는 실행 스택을 추적 캡처하기 때문에 비용이 비싸다.

2. Null Return
- null을 리턴하는 경우에는 Null Pointer Exception을 항상 조심해야 한다.

### Optional 도입

자바 8에서 Optional<T>가 도입되면서 선택지가 하나 늘었다.
- Optional이란 null이 아닌 T타입 참조를 하나 담거나 아무것도 담지 않은 일종의 래퍼 클래스이다.
- Optional은 원소를 최대 1개 가질 수 있는 불면 Collection이다.
- 자바 8 이전의 코드보다 null-safe한 로직을 처리할 수 있게 해준다.
- Optional을 반환하여 좀 더 유연하게 작성할 수 있게 해준다.
- 반환값이 Optional인 경우는 반환값이 없을 수도 있음을 클라이언트에게 알려준다.

### Optional 메서드

- Optional.empty()
	- 내부 값이 비어있는 Optional 객체 반환
- Optional.of(T value)
	- 내부 값이 value인 Optional 객체 반환
	- 만약 value가 null인 경우 NullPointException 반환
- Optional.ofNullable(T value)
	- 가장 자주 쓰이는 Optional 생성방법
	- value가 null이면, empty Optional을 반환하고, 값이 있으면 Optional.of로 생성
- T get()
	- Optional 내의 값을 반환
	- 만약 Optional 내부 값이 null인 경우 NoSuchElementException 발생
- boolean isPresent()
	- Optional 내부 값이 null이면 false, 있으면 true
	- Optional 내부에서만 사용해야하는 메서드라고 생각
- boolean isEmpty()
	- Optional 내부의 값이 null이면 true, 있으면 false
	- isPresent() 메서드의 반대되는 메서드
- void ifPresent(Consumer<? super T> consumer)
	- Optional 내부의 값이 있는 경우 consumer 함수를 실행
- Optional<T> filter(Predicate<T> predicate)
	- Optional에 filter 조건을 걸어 조건에 맞을 때만 Optional 내부 값이 있음
	- 조건이 맞지 않으면 Optional.empty를 리턴
- Optional<U> map(Funtion<? super T, ? extends U> f)
	- Optional 내부의 값을 Function을 통해 가공
- T orElse(T other)
	- Optional 내부의 값이 있는 경우 그 값을 반환
	- Optional 내부의 값이 null인 경우 other을 반환
- T orElseGet(Supplier<? extends T> supplier)
	- Optional 내부의 값이 있는 경우 그 값을 반환
	- Optional 내부의 값이 null인 경우 supplier을 실행한 값을 반환
- T orElseThrow()
	- Optional 내부의 값이 있는 경우 그 값을 반환
	- Optional 내부의 값이 null인 경우 NoSuchElementException 발생
- T orElseThrow(Supplier<? extends X> exceptionSupplier)
	- Optional 내부의 값이 있는 경우 그 값을 반환
	- Optional 내부의 값이 null인 경우 exceptionSupplier을 실행하여 Exception 발생	
	

### 기본값 세팅

```java
String lastWord = max(words).orElse("단어없음");
```


### 원하는 예외 던집

```java
Toy myToy = max(toys).orElseThrow(TemperTantrumException::new);
```


### 항상 값이 있을 경우

```java
Element lastNobleGas = max(Elements.NOBLE_GASES).get();
```

Optional에는 위와 같이 다양한 메서드들이 있으니 값이 없을때를 대비하여 설계하자.

**Optional은 결과가 없을 수 있으며, 클라이언트가 이 상황을 따로 처리해야 할때 사용하자. 어느정도 성능 저하가 있을 수 있으므로 성능이 민감한 메서드는 null을 반환하자.**


### Optional with Container type


Optional은 컨테이너 타입과는 사용하면 안된다. 빈 Optional<List<T>>를 반환하기 보다는 빈 List<T>를 반환하는 것이 클라이언트 입장에서 Optional 처리를 안해도 되기 때문에 더 좋다.


### Optional with Boxing type


박싱된 기본 타입을 담은 Optional은 사용하지 말자. 기본 타입을 박싱하고 다시 Optional로 감싸면 더욱 무겁기 때문이다. Optional<T>가 아닌 OptionalInt / OptionalLong / OptionalDouble을 사용하자


### Optional in Collection


Optional을 컬렉션의 키 / 값 / 원소로 사용하는 것은 적절치 않다. 쓸데없이 복잡해 혼란을 주고 오류 가능성만 키울 뿐이다.
