# ITEM32. ���׸��� �����μ��� �Բ� �� ���� �����϶�

## �����μ�

�����μ��� �޼��忡 �ѱ�� �μ��� ������ Ŭ���̾�Ʈ�� ������ �� �ְ� ���ݴϴ�. 
���´� ������ �����ϴ�.

```java
public static void example(String... args) {
    //....
}
```

�����μ� �޼���� ȣ���ϸ� �����μ��� ��� ���� �迭�� �ڵ����� �ϳ� ��������ϴ�. 
������ ���η� ����� ���� �� �迭�� Ŭ���̾�Ʈ�� ����ȴٴ� �������� �ֽ��ϴ�. 
�� ����� varargs �Ű������� ���׸��̳� �Ű�����ȭ Ÿ���� ���ԵǸ� ������ ����� �߻��ϰ� �˴ϴ�.

���׸��� ���� ��üȭ �Ұ� Ÿ���� ��Ÿ�ӿ��� ������Ÿ�Ӻ��� Ÿ�� ���� ������ ���� ��� �ֽ��ϴ�. 
��, �޼��带 ������ �� ��üȭ �Ұ� Ÿ������ varargs �Ű������� �����ϸ� �����Ϸ��� ����� ������ �˴ϴ�. 
�����μ� �޼��带 ȣ���� ���� varargs �Ű������� ��üȭ �Ұ� Ÿ������ �߷еǸ�, �� ȣ�⿡ ���ؼ� ������ ���� ����� �����ϴ�.

```
warning: [unchecked] Possible heap pollution from
    parameterized varargs type List<String>
```

�Ű�����ȭ Ÿ���� ������ Ÿ���� �ٸ� ��ü�� �����ϸ� �� ������ �߻��մϴ�.

> ���׸��� �����μ��� ȥ���Ͽ� Ÿ�� �������� ���� ����

```java
static void dangerous(List<String>... stringLists) {
    List<Integer> integerList = List.of(42); 
    Object[] objects = stringLists;
    objects[0] = integerList;   // �� ���� �߻�
    String s = stringLists[0].get(0);   // ClassCastException
}
```

�� �޼���� ����ȯ�ϴ� ���� ������ �ʴµ��� �μ��� �ǳ� ȣ���ϸ� ClassCastException�� �����ϴ�.
������ �ٿ� �����Ϸ��� ������ (������ �ʴ�) ����ȯ�� ���� �ֱ� �����Դϴ�.
**��ó�� Ÿ�� �������� ������ ���׸� varargs �迭 �Ű������� ���� �����ϴ� ���� �������� �ʽ��ϴ�.**


> @SafeVarargs

�ڹ� 7 ������ ���׸� �����μ� �޼����� �ۼ��ڰ� ȣ���� �ʿ��� �߻��ϴ� ����� ���ؼ� ���� �� �ִ� ���� �������ϴ�. 
���� ����ڴ� �� ������� �׳� �ΰų� (�� ���ϰԴ�) ȣ���ϴ� ������ @SuppressWarnings("unchecked") �ֳ����̼��� �޾� ����� ���ܾ� �߽��ϴ�.

�ڹ� 7������ @SafeVarargs �ֳ����̼��� �߰��Ǿ� ���׸� �����μ� �޼��� �ۼ��ڰ� Ŭ���̾�Ʈ ������ �߻��ϴ� ����� ���� �� �ְ� �Ǿ����ϴ�. 
**@SafeVarargs �ֳ����̼��� �޼��� �ۼ��� �� �޼��尡 Ÿ�� �������� �����ϴ� ��ġ�Դϴ�.**

- ������ ���׸� �����μ� �޼��带 ����ϴ� ���

�޼��尡 ������ �� Ȯ������ �ʴٸ� @SafeVarargs �ֳ����̼��� �޾Ƽ��� �� �˴ϴ�. 
**�ش� �޼���� varargs �迭�� �ƹ��͵� �������� �ʰ�(�� �Ű��������� ����� �ʰ�) �� �迭�� ������ ������ ������� �ʴ´ٸ�(�ŷ��� �� ���� �ڵ尡 �迭�� ������ �� ���ٸ�) Ÿ�� �������� ����˴ϴ�. **

- �ڽ��� ���׸� �Ű����� �迭�� ������ �����ϴ� �ڵ�(�������� ���� �޼���)

```java
static <T> T[] toArray(T... args) {
    return args;
}
```

�� �޼��尡 ��ȯ�ϴ� �迭�� Ÿ���� �� �޼��忡 �μ��� �ѱ�� ������Ÿ�ӿ� �����Ǵµ�, 
�� �������� �����Ϸ����� ����� ������ �־����� �ʾ� Ÿ���� �߸� �Ǵ��� �� �ֽ��ϴ�. 
���� �ڽ��� varargs �Ű����� �迭�� �״�� ��ȯ�ϸ� �� ������ �� �޼��带 ȣ���� ���� �ݽ������α��� �����ϴ� ����� ���� �� �ֽ��ϴ�.

- T Ÿ���� �μ� 3���� �޾� ���� 2���� �������� ��� ���� �迭�� ��ȯ�ϴ� �޼���

```java
static <T> T[] pickTwo(T a, T b, T c) {
    switch (ThreadLocalRandom.current().nextInt(3)) {
        case 0: return toArray(a, b);
        case 1: return toArray(a, c);
        case 2: return toArray(b, c);
    }
    throw new AssertionError(); // ������ �� ����.
}
```

���� �޼���� ���׸� �����μ��� �޴� toArray() �޼��带 ȣ���ϰ� �ֽ��ϴ�. 
�����Ϸ��� toArray()�� �ѱ� T �ν��Ͻ� 2���� ���� varargs �Ű����� �迭�� ����� �ڵ带 �����մϴ�. 
�� �ڵ尡 ����� �迭�� Ÿ���� Object[]�ε�, pickTwo�� � Ÿ���� ��ü�� �ѱ���� ���� �� �ִ� ���� ��ü���� Ÿ���̱� �����Դϴ�. 
�׸��� toArray() �޼��尡 ������ �� �迭�� �״�� pickTwo()�� ȣ���� Ŭ���̾�Ʈ���� ���޵˴ϴ�. 
��, pickTwo()�� �׻� Object[] Ÿ�� �迭�� ��ȯ�մϴ�.

```java
public static void main(String[] args) {
    String[] attributes = pickTwo("����", "����", "������");
}
```

������ �ۼ��ߴ� �ڵ���� ������� main �Լ��� �ۼ��ϸ� ClassCastException�� �߻��մϴ�. 
�̴� pickTwo()�� ��ȯ���� attributes�� �����ϱ� ���� String[]�� ����ȯ�ϴ� �ڵ尡 �����Ϸ��� �ڵ� �����ϱ� �����Դϴ�. 
�� �ڵ���� �� ������ �߻���Ų ��¥ ������ toArray()�κ��� �� �ܰ質 ������ �ֽ��ϴ�.

��� �� ���� **���׸� varargs �Ű����� �迭�� �ٸ� �޼��尡 �����ϵ��� ����ϸ� �������� �ʴ�**�� ���� �� �����ְ� �ֽ��ϴ�.

��, ���ܰ� �� ���� �ֽ��ϴ�.

1. @SafeVarargs�� ����� �ֳ�����Ʈ�� �� �ٸ� varargs �޼��忡 �ѱ�� ���� �����մϴ�.
2. varargs �Ű����� �迭 ������ �Ϻ� �Լ��� ȣ�⸸ �ϴ�(varargs�� ���� �ʴ�) �Ϲ� �޼��忡 �ѱ�� �͵� �����մϴ�.


- ���׸� varargs �Ű������� �����ϰ� ����ϴ� �޼���

```java
@SafeVarargs
static <T> List<T> flatten(List<? extends T>... lists) {
    List<T> result = new ArrayList<>();
    for (List<? extends T> list : lists) {
        result.addAll(list);
    }
    return result;
}
```

@SafeVarargs �ֳ����̼��� ����ؾ� �� ���� ���ϴ� ��Ģ

��Ģ�� �����մϴ�. 
**���׸��̳� �Ű�����ȭ Ÿ���� varargs �Ű������� �޴� ��� �޼��忡 @SafeVarargs�� �޾ƾ� �մϴ�.** 
�� ���� �������� ���� varargs �޼���� ���� �ۼ��ؼ��� �� �ȴٴ� ���Դϴ�.

- varargs �Ű����� �迭�� �ƹ��͵� �������� �ʽ��ϴ�.
- �� �迭(Ȥ�� ������)�� �ŷ��� �� ���� �ڵ忡 �������� �ʽ��ϴ�.

@SafeVarargs �ֳ����̼��� �������� �� ���� �޼��忡�� �޾ƾ� �մϴ�. �������� �޼��嵵 ���������� ������ �� ���� �����Դϴ�. �ڹ� 8���� �� �ֳ����̼��� ���� ���� �޼���� final �ν��Ͻ� �޼��忡�� ���� �� �ְ�, �ڹ� 9���ʹ� private �ν��Ͻ� �޼��忡�� ���˴ϴ�.




@SafeVarargs �ֳ����̼��� �̿����� �ʴ� �ٸ� ���

(��ü�� �迭��) varargs �Ű������� List �Ű������� �ٲ� ���� �ֽ��ϴ�. �� ����� �տ��� ���� ���Ҵ� flatten() �޼��忡 �����ϸ� ������ ���� �ۼ��� �� �ֽ��ϴ�. �ܼ��� �Ű����� ���� ������ �ڵ��Դϴ�.
 
```java
static <T> List<T> flatten(List<List<? extends T>> lists) {
    List<T> result = new ArrayList<>();
    for (List<? extends T> list : lists) {
        result.addAll(list);
    }
    return result;
}
```

���� ���丮 �޼����� List.of()�� Ȱ���ϸ� ���� �ڵ�� ���� �� �޼��忡 ���� ������ �μ��� �ѱ� �� �ֽ��ϴ�. 
�̷��� ����ϴ� �� ������ ������ List.of()���� @SafeVarargs �ֳ����̼��� �޷� �ֱ� �����Դϴ�.

```java
audience = flattern(List.of(frends, romans, countrymen));
```

�� ����� ������ �����Ϸ��� �� �޼����� Ÿ�� �������� ������ �� �ִٴ� �� �ֽ��ϴ�. 
@SafeVarargs �ֳ����̼��� ���� ���� �ʾƵ� �Ǹ�, �Ǽ��� �����ϴٰ� �Ǵ��� ������ �����ϴ�. 
������ Ŭ���̾�Ʈ �ڵ尡 ��¦ ������������ �ӵ��� ���� ������ �� �ִٴ� �����Դϴ�.

 �� ����� pickTwo�� �����ϸ� ������ �����ϴ�.
 
 ```java
 static <T> List<T> pickTwo2(T a, T b, T c) {
    switch (ThreadLocalRandom.current().nextInt(3)) {
        case 0: return List.of(a, b);
        case 1: return List.of(a, c);
        case 2: return List.of(b, c);
    }
    throw new AssertionError(); // ������ �� ����.
}
 ```
 
 ```java
 public static void main(String[] args) {
    List<String> attributes = pickTwo2("����", "����", "������");
}
 ```
 
 
 