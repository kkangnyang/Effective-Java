#ITEM46. 스트림에서는 부작용 없는 함수를 사용하라

스트림을 제대로 사용하려면 패러다임을 이해해야 한다. 스트림의 각 스테이지에서 수행되는 함수는 순수함수로서, 오직 입력만이 결과에 영향을 주는 함수여야 한다. 즉, 가변 상태를 참조하지 않고, 함수 스스로도 다른 상태를 변경하지 않아야한다


다음은 스트림을 잘못 사용한 코드다.

```java
Map<String, Long> freq = new HashMap<>();
try (Stream<String> words = new Scanner(file).tokens()) {
    words.forEach(word -> {
        freq.merge(word.toLowerCase(), 1L, Long::sum);
    });
}
```

`tokens`를 통해 스트림을 얻어오고 위에서 선언한 가변 지역변수인 freq에 for문을 돌며 merge 하고 있다. 스트림 코드를 가장한 반복적 코드이다. forEach 문은 그저 스트림이 수행한 연산 결과를 보여주는 일을 해야하는데, 그 이상의 일을 하고 있다.

정상적으로 작성한 모습을 살펴보자.

```java
Map<String, Long> freq;
try (Stream<String> words = new Scanner(file).tokens()) {
    freq = words
            .collect(groupingBy(String::toLowerCase, counting()));
}
```

word를 소문자로 바꾼 뒤, 각 문자의 빈도수를 groupingBy를 통해 Map으로 만들었다. forEach 연산은 스트림 계산 결과를 보고할 때만 사용하고 계산하는 데는 쓰지말자.


이 코드에 collector를 사용하는데, 스트림을 사용하려면 꼭 배워야 하는 새로운 개념이다.


### java.util.stream.Collectors

Collectors는 축소의 개념으로, 스트림의 원소들을 객체 하나에 취합한다는 뜻으로 생각하면 된다. 아래와 같이 총 세가지 수집기가 있다.

- toList()
- toSet()
- toCollection(collectionFactory)

```java
List<String> topTen = freq.keySet().stream()
        .sorted(comparing(freq::get).reversed())
        .limit(10)
        .collect(toList());
```

### toMap(keyMapper, valueMapper)

스트림 원소를 키에 매핑하는 함수와, 값에 매핑하는 함수로 인수로 받는다.

```java
private static final Map<String, Operation> stringToEnum = Stream.of(values()).collect(toMap(Object::toString), e->e);
```

스트림의 각 원소가 고유한 키에 매핑되어 있을 때 적합하다. 만약 스트림 원소 다수가 같은 키를 사용한다면 파이프라인이 IllegalStateException을 던지며 종료될 것이다.
```