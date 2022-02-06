# ITEM47. ��ȯ Ÿ�����δ� ��Ʈ������ �÷����� ����

���� ������, �� �Ϸ��� ���Ҹ� ��ȯ�ϴ� �޼���� ������ ����. Collection, List, Set �� ���� �÷��� �������̽�, Ȥ�� Iterable�̳� �迭�� ���. �ڹ�8������ ��Ʈ���̶�� ������ �����鼭 �������� �þ��.


������45���� ���ߵ��� ��Ʈ���� �ݺ��� �������� �ʴ´�.(�ѹ� ����ϸ� ������.)


����� �޼��带 ����ϸ� ��Ʈ���� iterate�ؼ� ����� �� �ִ�.


### ��Ʈ�� <-> �ݺ��� �����

```java
public class Adapters {
    // �ڵ� 47-3 Stream<E>�� Iterable<E>�� �߰����ִ� ����� (285��)
    public static <E> Iterable<E> iterableOf(Stream<E> stream) {
        return stream::iterator; // stream�� Iterator �������̽��� Ȯ�������Ƿ� iterator�� ������ �ִ�
    }

    // �ڵ� 47-4 Iterable<E>�� Stream<E>�� �߰����ִ� ����� (286��)
    public static <E> Stream<E> streamOf(Iterable<E> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
```

���� ���� Stream�� iterator��, iterator�� ��Ʈ������ ������ �� �ִ�. ������, ���� API�� �ۼ��Ҷ� �̸� ����ϴ� �뵵�� �����ؼ� Iterator�� ��ȯ����, Stream�� ��ȯ���� ����ϵ��� ����


Collection �������̽��� Iterable�� ���� Ÿ���̰� stream �޼��嵵 �����ϴ� �ݺ��� ��Ʈ���� ���ÿ� �����Ѵ�. ���� ���� �������� ��ȯ�ϴ� ���� API�� ��ȯ Ÿ�Կ��� Collection�̳� �� ���� Ÿ���� ���°� �Ϲ������� �ּ��̴�.

>> ���� �÷����� ��ȯ�Ѵٴ� ������ ��ġ ū �������� �޸𸮿� �÷����� �ȵȴ�.


