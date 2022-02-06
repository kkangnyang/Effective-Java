# ITEM44. ǥ�� �Լ��� �������̽��� ����϶�

LinkedHashMap Ŭ������ ���� �Ʒ��� ���� �޼��尡 �ִ�. 
�ʿ� ���ο� Ű�� �߰��ϴ� put �޼��尡 ����Ǹ� �� �޼��带 ȣ���ؼ� true�� ��ȯ�Ǹ� �ʿ��� ���� ������ ���Ҹ� �����Ѵ�.

```java
// LinkedHashMap.java
protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
    return false;
}
```

�� �޼��带 ���� �����ؼ� �Ʒ��� ���� ������ �־���.

```java
// LinkedHashMap.java
protected boolean removeEldestEntry(Map.Entry<K,V> eldest) {
    return size() > 100;
}
```

���Ұ� 100���� �Ѿ�� ���� ������ ���Ҹ� ������ �� �ֵ��� ���� ���̴�. ��ó�� Ư�� ������ �� �� �ִ� �Լ� ��ü�� removeEldestEntry �� �ѱ� �� ������ ���� ������ ������ �ɰ��̴�. ������ �����ڿ� �ѱ�� �Լ� ��ü�� ���� �ν��Ͻ� �޼��尡 �ƴϴ�. ���͸��� �����ڸ� ȣ���� ���� ���� �ν��Ͻ��� �������� �ʱ� �����̴�. ���� ���� �ڱ� �ڽŵ� �Լ� ��ü�� �ǳ���� �Ѵ�.

```java
@FunctionalInterface
interface EldestEntryRemovalFunction<K, V> {
  boolean remove(Map<K,V> map, Map.Entry<K,V> eldest);
}
```

�̷��� ������ �Լ��� �������̽��� ���ٰ�ü�� ���� �Ķ���ͷ� �����ϰ� ������ �� �ִ�. 


������ �̷� ������ �������̽��� java.util.function ��Ű���� �غ�Ǿ� �����Ƿ� ���� ���� ���� ���� �ʾƵ� �ȴ�.


�ռ� ���� �Լ��� �������̽��� ǥ�� �������̽��� BiPredicate<Map<K,V>, Map.Entry<K,V>>�� ����� �� �ִ�.


## ǥ�� �Լ��� �������̽� ����

�⺻ �������̽� 6���� ����ϸ� �������� ����� �����س� �� �ִ�.

### �⺻ �������̽�

|�������̽� |�Լ� �ñ״�ó |�� |
|UnaryOperator | T apply(T t) |String::toLowerCase |
|BinaryOperator | T apply(T t1, T t2) | BigInteger::add |
|Predicate | boolean test(T t) | Collection::isEmpty |
|Function<T,R> | R apply(T t) | Arrays::asList |
|Supplier | T get() | Instant::now |
|Consumer | void accept(T t) | System.out::pringln |

- Operator: ��ȯ���� �μ��� Ÿ���� ���� �Լ�
- Predecate: boolean ��ȯ
- Function: �μ��� ���� �ʰ� ���� ��ȯ
- Supplier: �μ��� ���� �ʰ� ���� ��ȯ
- Consumer: �μ��� �ϳ� �ް� ��ȯ���� ���� �Լ�


### �⺻ Ÿ�� �������̽�

�⺻Ÿ���� int, long, double ������ �� 3���� ������ �����.

- IntPredicate
- LongBinaryOperator
- DoubleBinaryOperator

Function�� ������ �Ű�����ȭ���. return type�� �Ű�����ȭ ��µ�, ���� ��� LongFunction<int[]>�� long�μ��� �޾� int[]�� ��ȯ�Ѵ�.


### Function �������̽� ����

- �Է°� ��� Ÿ���� ��� �⺻ Ÿ���̸� ���ξ�� SrcToResult�� ����Ѵ�.
	- long�� �޾� int�� ��ȯ�Ѵٸ� LongToIntFunction�� �ȴ�.
- �Է��� ��ü �����̰�, ����� �⺻Ÿ���� ��� ���ξ�� ToResult�� ����Ѵ�.
	- int[] �μ��� �޾� long�� ��ȯ�ϸ� ToLongFunction<int[]> �� �ȴ�.
	

### BooleanSupplier
boolean�� ��ȯ�ϵ��� �ϴ� Supplier�� �����̴�.



ǥ�� �Լ��� �������̽� ��κ��� �⺻ Ÿ�Ը� �����Ѵ�. �׷��ٰ� �⺻ �Լ��� �������̽��� �ڽ̵� �⺻ Ÿ���� �־� ��������� ���� (������61)


���������� �Ȱ��� ǥ�� �Լ��� �������̽��� �ִ��� ���� �ۼ��ؾ� �� ���� �ִ�. Comparator<T> �������̽��� ���������δ� ToIntBiFunction<T,U>�� �����ϴ�. ������ �Ʒ��� ���� ������ ���� ���� ���̽���

- ���� ���̸�, �̸� ��ü�� �뵵�� ��Ȯ�� �������ش�.
- �ݵ�� ����� �ϴ� �Ծ��� �ִ�.
- ������ ����Ʈ �޼��带 ������ �� �ִ�.

���� ���� �Լ��� �������̽����� �׻� @FunctionalInterface �ֳ����̼��� ����϶�.