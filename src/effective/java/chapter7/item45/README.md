# ITEM45. ��Ʈ���� �����ؼ� ����϶�

��Ʈ�� API�� �ٷ��� ������ ó�� �۾��� ������ �ڹ�8�� �߰��Ǿ���.

- ��Ʈ���� ������ ������ ���� Ȥ�� ���� �������� ���Ѵ�.
- ��Ʈ�� ������������ �� ���ҵ�� �����ϴ� ���� �ܰ踦 ǥ���ϴ� �����̴�.

�̷��� ��Ʈ�� API ������������ �⺻������ ���������� ��������� ���ķ� �����Ϸ��� `parallel` �޼��带 ����ȴ�.

�׷��� ȿ���� �� �� �ִ� ��Ȳ�� ���� �ʴ�. �׷��ٸ� ��Ʈ�� API�� ���� ��� �ɱ�?



�Ʒ� �Ƴ��׷��� �����ϴ� �ڵ带 ���÷� ����


### Iterator ���

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

���� �ڵ�� map�� `computeIfAbsent` �޼��带 ����ؼ� �ʾȿ� Ű�� �ִ��� Ȯ�� ��, ������ �ܼ��� �� Ű�� ���ε� ���� ��ȯ�Ѵ�. ������ �ǳ��� �Լ� ��ü�� Ű�� �����Ͽ� ���� ����س� ���� �� Ű�� ���� �����س���, ���� ���� ��ȯ�Ѵ�.


### ��Ʈ���� ����� ���

�Ʒ��� ��Ʈ���� ���ϰ� �� ���̽���.�׳� ���⸸ �ص� ��ƴ�.

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


### �����

��Ʈ���� ������ ���鼭, `alphabetize` �κ��� ��Ʈ���� ������� �ʰ� ���� �޼��带 ����ߴ�. �ξ� ��Ȯ�ϰ� � ���� �ϴ��� �˼��� �ִ�.


```java
try (Stream<String> words = Files.lines(dictionary)) {
    words.collect(Collectors.groupingBy(word -> alphabetize(word)))
            .values().stream()
            .filter(group -> group.size() >= minGroupSize)
            .forEach(g -> System.out.println(g.size() + ": " + g));
}
```

`alphabetize` �޼��嵵 ��Ʈ���� ����� �ٸ��� ������ �� ������, ��Ȯ���� �������� �߸� ������ ���ɼ��� Ŀ����. ������ ���������� �ִµ�, �ڹٰ� �⺻ Ÿ���� char�� ��Ʈ���� �������� �ʱ� �����̴�. char �� ��ȯ�ϴ� ��Ʈ���� ���Ҵ� char�� �ƴ϶� int ���̱� �����̴�. (�׷��� ������ ����ȯ�� ��������� ����� �Ѵ�.)


```
"Hello World!".chars().forEach(x -> System.out.print((char) x));
```


## ��Ʈ���� ���� ����ұ�?

�Ʒ��� ���� ������ ��Ʈ���� �� �� ���� ���̴�.

- ��Ʈ�������� ���������� �а� ������ �� ����.
- �ڵ��Ͽ���ó�� return���� ����� �޼��忡�� ���������ų� break�� continue ������ �ǳʶ� �� ����.
- ��Ʈ�� ������������ �ϴ� �Ѱ��� �ٸ� ���� �����ϰ� ���� ������ ���� �Ҵ� �����̱� ������, ���� stage�� ����ϰ� �ٽ� original ���� ������ �Ҷ� ����� �� �ִ�.


�ݴ�� ���� �ϵ鿡�� ��Ʈ���� �ȼ������̴�.

- ���ҵ��� �������� �ϰ��ǰ� ��ȯ�Ѵ�.
- ���ҵ��� �������� ���͸��Ѵ�.
- ���ҵ��� �������� �ϳ��� ������ ��G�� �����Ѵ�.
- ���ҵ��� �������� �÷��ǿ� ������.
- ���ҵ��� ���������� Ư�� ������ �����ϴ� ���Ҹ� ã�´�.

