# ITEM35. ordinal �޼��� ��� �ν��Ͻ� �ʵ带 ����϶�

���� Ÿ���� ordinal �޼���� �ش� ����� ��ġ�� ��ȯ���ش�.

```java
public enum Ensemble {
    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
    SEXTET(6), SEPTET(7), OCTET(8), DOUBLE_QUARTET(8),
    NONET(9), DECTET(10), TRIPLE_QUARTET(12);

    public int numberOfMusicians() { return ordinal() + 1; }
}
```

numberOfMusicians �޼����� �ǵ���, ����� ��ġ (�迭ó��)�� 1�� ���ؼ�, �������� ���ο��� ��ȯ�ϴ� ���̴�. ������ �̷��� ���� Ÿ���� (������) ������ �ִ�.

- ������ ������� ����Ÿ���� �����ؾ� �Ѵ�.
- ���� �߰��� ����� �� ����. (�׻� 1,2,3,4,5 �� ���������� �����ؾ� �Ѵ�.)

���� ���� Ÿ�Ի���� ����� ���� ordinal �޼��� ���� ���� �ν��Ͻ� �ʵ忡 �����ؼ� �������.

```java
public enum Ensemble {
    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
    SEXTET(6), SEPTET(7), OCTET(8), DOUBLE_QUARTET(8),
    NONET(9), DECTET(10), TRIPLE_QUARTET(12);

    private final int numberOfMusicians; // �ν��Ͻ� �ʵ�
    Ensemble(int size) { this.numberOfMusicians = size; }
    public int numberOfMusicians() { return numberOfMusicians; }
}
```

�׷��ٸ� �� ordinal �޼��带 ���������? Enum�� API ������ ���� EnumSet�� EnumMap�� �ڷᱸ���� �� �������� ����Ǿ��� ��� �����ִ�.


����) ��ǥ���� ENUM �޼ҵ�
http://www.tcpschool.com/java/java_api_enum
