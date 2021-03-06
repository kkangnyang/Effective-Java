# ITEM45. 스트림은 주의해서 사용하라

스트림 API는 다량의 데이터 처리 작업을 돕고자 자바8에 추가되었다.

- 스트림은 데이터 원소의 유한 혹은 무한 시퀀스를 뜻한다.
- 스트림 파이프라인은 이 원소들로 수행하는 연산 단계를 표현하는 개념이다.

이러한 스트림 API 파이프라인은 기본적으로 순차적으로 수행되지만 병렬로 실행하려면 `parallel` 메서드를 쓰면된다.

그러나 효과를 볼 수 있는 상황은 많지 않다. 그렇다면 스트림 API는 언제 써야 될까?



아래 아나그램을 구현하는 코드를 예시로 보자


### Iterator 사용

```java
Map<String, Set<String>> groups = new HashMap<>();
try (Scanner s = new Scanner(dictionary)) {
    while (s.hasNext()) {
        String word = s.next();
        groups.computeIfAbsent(alphabetize(word),
                (unused) -> new TreeSet<>()).add(word);
    }
}

for (Set<String> group : groups.values())
    if (group.size() >= minGroupSize)
        System.out.println(group.size() + ": " + group);
```

위의 코드는 map의 `computeIfAbsent` 메서드를 사용해서 맵안에 키가 있는지 확인 후, 있으면 단순히 그 키에 매핑된 값을 반환한다. 없으면 건네진 함수 객체를 키에 적용하여 값을 계산해낸 다음 그 키와 값을 매핑해놓고, 계산된 값을 반환한다.


### 스트림을 사용한 방법

아래는 스트림을 과하게 쓴 케이스다.그냥 보기만 해도 어렵다.

```java
try (Stream<String> words = Files.lines(dictionary)) {
    words.collect(
            Collectors.groupingBy(word -> word.chars().sorted()
                    .collect(StringBuilder::new,
                            (sb, c) -> sb.append((char) c),
                            StringBuilder::append).toString()))
            .values().stream()
            .filter(group -> group.size() >= minGroupSize)
            .map(group -> group.size() + ": " + group)
            .forEach(System.out::println);
}
```


### 절충안

스트림을 적절히 쓰면서, `alphabetize` 부분을 스트림을 사용하지 않고 별도 메서드를 사용했다. 훨씬 명확하게 어떤 일을 하는지 알수가 있다.


```java
try (Stream<String> words = Files.lines(dictionary)) {
    words.collect(Collectors.groupingBy(word -> alphabetize(word)))
            .values().stream()
            .filter(group -> group.size() >= minGroupSize)
            .forEach(g -> System.out.println(g.size() + ": " + g));
}
```

`alphabetize` 메서드도 스트림을 사용해 다르게 구현할 수 있지만, 명확성이 떨어지고 잘못 구현할 가능성이 커진다. 심지어 느려질수도 있는데, 자바가 기본 타입인 char용 스트림을 지원하지 않기 때문이다. char 가 반환하는 스트림의 원소는 char가 아니라 int 값이기 때문이다. (그래서 쓰려면 형변환을 명시적으로 해줘야 한다.)


```
"Hello World!".chars().forEach(x -> System.out.print((char) x));
```


## 스트림을 언제 써야할까?

아래와 같은 내용은 스트림이 할 수 없는 일이다.

- 스트림에서는 지역변수를 읽고 수정할 수 없다.
- 코드블록에서처럼 return문을 사용해 메서드에서 빠져나가거나 break나 continue 문으로 건너뛸 수 없다.
- 스트림 파이프라인은 일단 한값을 다른 값에 매핑하고 나면 원래의 값은 잃는 구조이기 때문에, 여러 stage를 통과하고 다시 original 값을 보려고 할때 어려울 수 있다.


반대로 다음 일들에는 스트림이 안성맞춤이다.

- 원소들의 시퀀스를 일관되게 변환한다.
- 원소들의 시퀀스를 필터링한다.
- 원소들의 시퀀스를 하나의 연산을 사욯해 결합한다.
- 원소들의 시퀀스를 컬렉션에 모은다.
- 원소들의 시퀀스에서 특정 조건을 만족하는 원소를 찾는다.

