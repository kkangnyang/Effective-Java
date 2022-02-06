#ITEM46. ��Ʈ�������� ���ۿ� ���� �Լ��� ����϶�

��Ʈ���� ����� ����Ϸ��� �з������� �����ؾ� �Ѵ�. ��Ʈ���� �� ������������ ����Ǵ� �Լ��� �����Լ��μ�, ���� �Է¸��� ����� ������ �ִ� �Լ����� �Ѵ�. ��, ���� ���¸� �������� �ʰ�, �Լ� �����ε� �ٸ� ���¸� �������� �ʾƾ��Ѵ�


������ ��Ʈ���� �߸� ����� �ڵ��.

```java
Map<String, Long> freq = new HashMap<>();
try (Stream<String> words = new Scanner(file).tokens()) {
    words.forEach(word -> {
        freq.merge(word.toLowerCase(), 1L, Long::sum);
    });
}
```

`tokens`�� ���� ��Ʈ���� ������ ������ ������ ���� ���������� freq�� for���� ���� merge �ϰ� �ִ�. ��Ʈ�� �ڵ带 ������ �ݺ��� �ڵ��̴�. forEach ���� ���� ��Ʈ���� ������ ���� ����� �����ִ� ���� �ؾ��ϴµ�, �� �̻��� ���� �ϰ� �ִ�.

���������� �ۼ��� ����� ���캸��.

```java
Map<String, Long> freq;
try (Stream<String> words = new Scanner(file).tokens()) {
    freq = words
            .collect(groupingBy(String::toLowerCase, counting()));
}
```

word�� �ҹ��ڷ� �ٲ� ��, �� ������ �󵵼��� groupingBy�� ���� Map���� �������. forEach ������ ��Ʈ�� ��� ����� ������ ���� ����ϰ� ����ϴ� ���� ��������.


�� �ڵ忡 collector�� ����ϴµ�, ��Ʈ���� ����Ϸ��� �� ����� �ϴ� ���ο� �����̴�.


### java.util.stream.Collectors

Collectors�� ����� ��������, ��Ʈ���� ���ҵ��� ��ü �ϳ��� �����Ѵٴ� ������ �����ϸ� �ȴ�. �Ʒ��� ���� �� ������ �����Ⱑ �ִ�.

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

��Ʈ�� ���Ҹ� Ű�� �����ϴ� �Լ���, ���� �����ϴ� �Լ��� �μ��� �޴´�.

```java
private static final Map<String, Operation> stringToEnum = Stream.of(values()).collect(toMap(Object::toString), e->e);
```

��Ʈ���� �� ���Ұ� ������ Ű�� ���εǾ� ���� �� �����ϴ�. ���� ��Ʈ�� ���� �ټ��� ���� Ű�� ����Ѵٸ� ������������ IllegalStateException�� ������ ����� ���̴�.
```