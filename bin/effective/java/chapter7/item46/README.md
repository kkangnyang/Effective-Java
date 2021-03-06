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


### toMap(keyMapper, valueMapper, BinaryOperator)

어떤 키와, 그 키에 연관된 원소들 중 하나를 골라 연관 짓는 맵을 만들 때 유용하다.

```java
Map<Artist, Album> topHits = albums.collect(toMap(Album::artist, a->a, maxBy(comparing(Aplbum::sales))));
```

여기서 BinaryOperator 부분에 `maxBy`라는 정적 팩터리 메서드를 사용했다. 말로 풀어보자면 “앨범 스트림을 맵으로 바꾸는데, 이 맵은 각 음악가와 그 음악가의 베스트 앨범을 짝지은 것이다”라는 이야기다.


이러한 인수가 3개인 toMap은 충돌이 나면 마지막 값을 취하는 수집기를 만들때도 유용하다.

```
toMap(keyMapper, valueMapper, (oldVal, newVal) -> newVal)
```

### toMap(keyMapper, valueMapper, BinaryOperator, Supplier)


네 번째 인수로 맵 팩터리를 받는다. 이 인수로는 EnumMap이나 TreeMap 처럼 원하는 특정 맵 구현체를 지정할 수 있다.


### toConcurrentMap

toConcurrentMap은 병렬실행된 후 결과로 ConcurrentHashMap 인스턴스를 생성한다.

### groupingBy(Function<? super T, ? extends K> classifier)

이 메서드는 입력으로 분류 함수(classifier)를 받고 출력으로는 원소들을 카테고리별로 모아 놓은 맵을 담은 수집기를 반환한다.

```java
words.collect(groupingBy(word -> alphabetize(word)))
```

### groupingBy(Function<? super T, ? extends K> classifier, Collector<? super T, A, D> downstream)

이 수집기가 리스트 외의 값을 갖는 맵을 생성하게 하려면, 분류 함수와 함께 다른 인수로 downstream 수집기도 명시해야 한다.

예를 들어, toSet()을 넘기면 결과값이 Set을 값으로 갖는 맵을 만들어낸다.


### groupingBy(Function<? super T, ? extends K> classifier, Supplier mapFactory, Collector<? super T, A, D> downstream)

Supplier로 맵 팩터리도 지정하면, 원하는 다운 스트림에서 그 안에 담긴 컬렉션의 타입을 모두 지정할 수 있다.


### groupingByConcurrent

병렬 버전으로 수행해서, ConcurrentHashMap 인스턴스를 만들어준다.


### minBy, maxBy

수집과는 관련이 없지만, 인수로 받은 비교자를 이용해 스트림에서 값이 가장 작은 혹은 가장 큰 원소를 찾아 반환한다.

### joining

CharSequence 인스턴스의 스트림에만 적용할 수 있다.

