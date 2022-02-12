# ITEM49. �Ű������� ��ȿ���� �˻��϶�

�޼���� ������ ��κ��� �Է� �Ű������� ���� Ư�� ������ �����ϱ⸦ �ٶ���. �������� ������ �� ���� ��ƾ� �Ѵ١� ���� �޼��� Ȥ�� �����ڷ� ���ԵǴ� `�Ķ����`�� ��ȿ���� �˻�����


## �Ű����� �˻縦 ����� ���� ���ϸ� ����� ������

�޼��尡 ����Ǵ� �߰��� ��ȣ�� ���ܸ� ������ ������ �� �ִ�. �� ���� ��Ȳ�� �޼���� �������� ���������, � ��ü�� �̻��� ���·� �������Ƽ� �̷��� �� �� ���� ������ �� �޼���� ���� ���� ������ �� ���̴�.


## ���� API�� �ݵ�� ����ȭ�϶�

public�� protected �޼���� �Ű����� ���� �߸����� �� ������ ���ܸ� ����ȭ�ؾ� �Ѵ�.(@throws �ڹٵ� �±׸� ����ϸ� �ȴ�.)
������ **IllegalArgumentException, IndexOutOfBoundsException, NullPointerException** �� �ϳ��� �� ���̴�.
�Ű������� ������ ����ȭ�Ѵٸ� �� ������ ����� �� �߻��ϴ� ���ܵ� �Բ� ����ؾ� �Ѵ�.

### BigInteger Ŭ������ mod() �޼��� ����

```java
/**
 * Returns a BigInteger whose value is {@code (this mod m}).  This method
 * differs from {@code remainder} in that it always returns a
 * <i>non-negative</i> BigInteger.
 *
 * @param  m the modulus.
 * @return {@code this mod m}
 * @throws ArithmeticException {@code m} &le; 0
 * @see    #remainder
 */
public BigInteger mod(BigInteger m) {
    if (m.signum <= 0)
        throw new ArithmeticException("BigInteger: modulus not positive");

    BigInteger result = this.remainder(m);
    return (result.signum >= 0 ? result : result.add(m));
}
```


�� �޼���� m�� null�̸� m.signum() ȣ�� �� NullPointerException�� ������.
�׷��� "m�� null�� �� NullPointerException�� ������"��� ���� �޼��� ���� ��𿡵� ����.
�� ������ **�� ������ (���� �޼��尡 �ƴ�) BigInteger Ŭ���� ���ؿ��� ����߱� �����̴�.**


Ŭ���� ���� �ּ��� �� Ŭ������ ��� public �޼��忡 ����ǹǷ� �� �޼��忡 ������ ����ϴ� �ͺ��� �ξ� ����� ���.


@Nullable�̳� �̿� ����� �ֳ����̼��� ����� Ư�� �Ű������� null�� �� �� �ִٰ� �˷��� ���� ������, ǥ������ ����� �ƴϴ�.

**�ڹ� 7�� �߰��� java.util.Objects.requireNonNull �޼���� �����ϰ� ����ϱ⵵ ���ϴ�, �� �̻� null �˻縦 �������� ���� �ʾƵ� �ȴ�.**

```java
this.strategy = Objects.requireNonNull(strategy, "ErrorMessage");
```

## �ܾ�(assert)

�������� ���� �޼����� ��Ű�� �������� �ۼ��ڰ� �޼��尡 ȣ��Ǵ� ��Ȳ�� ������ �� �ִ�. 
���� ���� ��ȿ�� ������ �޼��忡 �Ѱ�������� ���� �ۼ��ڰ� ������ �� �ִ�.
�ٽ� ���� public�� �ƴ� �޼����� �ܾ�(assert)�� ����� �Ű����� ��ȿ���� ������ �� �ִ�.

```java
private static void sort(long a[], int offset, int length) {
    assert a != null;
    assert offset >= 0 && offset <= a.length;
    assert length >= 0 && length <= a.length - offset;
    //��� ���� ...
}
```

> �Ϲ����� ��ȿ�� �˻�� �ܾ��� �ٸ� ��

- �����ϸ� AssertionError�� ������.
- ��Ÿ�ӿ��� �ƹ��� ȿ����, �ƹ��� ���� ���ϵ� ����. (��, java�� ������ �� ����ٿ��� -ea Ȥ�� --enableas sertions �÷��׸� �����ϸ� ��Ÿ�ӿ� ������ �ش�.)


## ��Ģ�� ���� ����

�޼��� ��ü ���� ���� �Ű����� ��ȿ���� �˻��ؾ� �Ѵٴ� ��Ģ���� ���ܴ� �ִ�. ��ȿ�� �˻� ����� ����ġ�� ���ų� �ǿ������� ���� ��, Ȥ�� ��� �������� �Ϲ������� �˻簡 ����� ��.


���� ��� Collections.sort(List)ó�� ��ü ����Ʈ�� �����ϴ� �޼��带 ������ ���ڴ�. ����Ʈ ���� ��ü���� ��� ��ȣ �񱳵� �� �־�� �ϸ�, ���� �������� �� �񱳰� �̷�����. ���� ��ȣ �񱳵� �� ���� Ÿ���� ��ü�� ����ִٸ� �� ��ü�� ���� �� ClassCastException�� ���� ���̰�, ���� ���ϱ� �ռ� ����Ʈ ���� ��� ��ü�� ��ȣ �񱳵� �� �ִ��� �˻��غ��� ���ٸ� ������ ����.
