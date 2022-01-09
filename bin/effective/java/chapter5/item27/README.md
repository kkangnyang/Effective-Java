# ITEM27. ��˻� ���(Unchecked Warning)�� �����϶�

���ʸ��� ����ϸ鼭 Unchecked Error�� ���� ���� �� �ִ�. ������ ��� Unchecked Error�� �����ؾ� Ÿ�� �������� ����ȴ�.

|����    |Checked Exception | Unchecked Exception |
|----   |------------------|----------------------|
|Ȯ�� ����|������ ����           |��Ÿ�� ����             |
|ó�� ����|�ݵ�� ���� ó���ؾ� �Ѵ�.(������ �ȵ�)|��������� ���� �ʾƵ� �ȴ�.(Ư�� ��Ÿ�� �� ������ �߻��� ���ɼ��� �ִ�.|
|����    |IOException, ClassNotFoundException �� | NullPointerException, ClassCastException �� |

## @SuppressWarnings(��unchecked��)

��� ������ ���� ������ Ÿ�� �����ϴٰ� Ȯ���� �� �ִٸ� @SuppressWarnings("unchecked") ������̼��� �޾Ƽ� ��� ���� �� �ִ�.

���� �������� ���� Ŭ���� ��ü���� ��𿡵� ������ �� ������, �׻� ������ ���� ������ ��������. ��ĩ �ɰ��� ��� ��ĥ �� ������ ����� Ŭ���� ��ü�� �����ؼ��� �ȵȴ�.

å������ ArrayList.class �� toArray �޼��带 ���� ���.

```java
@SuppressWarnings("unchecked")
public <T> T[] toArray(T[] a) {
    if (a.length < size)
        // Make a new array of a's runtime type, but my contents:
        return (T[]) Arrays.copyOf(elementData, size, a.getClass());
    System.arraycopy(elementData, 0, a, 0, size);
    if (a.length > size)
        a[size] = null;
    return a;
}
```

Object[] elementData�� T[]�� ����ȯ�ϴ� ���� Type Safety ��� ���
������ ���⼭ �Ű������� ���� �迭�� Ÿ������ Array�� ���� �����ϱ� ������ ��� T[]�� �����Ƿ� �ùٸ� ����ȯ�̴�. 
���� @SuppressWarnings ������̼����� ��� ���� �� �ִ�.

@SuppressWarnings(��unchecked��) ������̼��� ����ϰ�, �� ��� �����ص� ������ ������ �׻� �ּ����� ���⵵�� ����!

����, ���� �ڵ�ó��, �޼ҵ� toArray�� ��ü�� ������̼��� �����ϴ� ���� �ƴ϶�, ������������ ������ �� ������ ������ ���� ������ �����ϵ��� ����!

