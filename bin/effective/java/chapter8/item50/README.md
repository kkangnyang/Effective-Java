# ITEM50. ���ÿ� ����� ���纻�� �����

## �Ⱓ�� ǥ���ϴ� Ŭ���� - �Һ����� ��Ű�� ���ߴ�.

```java
public final class Period {
  private final Date start;

  public Period(Date start) {
    this.start = start;
  }

  public Date start() {
    return start;
  }
}
```

## Period �ν��Ͻ��� ���� ����

```java
Date start = new Date();
Period p = new Period(start);
start.setYear(78);  // p�� ���θ� �����ߴ�
```

- Date�� �����̶� �߻� �� ����
	- �ڹ� 8 ���ķδ� Date ��� �Һ��� Instant�� ����ϸ� �ȴ�.(LocalDateTime�̳� ZonedDateTime ��� ����)
	- Date�� ���� API�̴� ���ο� �ڵ� �ۼ��� �� �̻� ����ϸ� �ȵȴ�.
- �����ڿ��� ���� ���� �Ű����� ������ ��������� ����(defensive copy)�ؾ� �Ѵ�.

## Period �ν��Ͻ��� ���� �� ��° ����

```java
Date start = new Date();
Period p = new Period(start);
p.start.setYear(78); // p�� ���θ� �����ߴ�.
```

## ������ ������ - �ʵ��� ����� ���纻�� ��ȯ�Ѵ�

```java
public Date start() {
  return new Date(start.getTime());
}
```

- �����ڿ� �޸� ������ �޼��忡���� ����� ���翡 clone�� ����ص� �ȴ�.
	- Period�� ������ �ִ� Date ��ü�� java.util.Date���� Ȯ���ϱ� ����
	- �׷��� �ν��Ͻ��� �����ϴ� ���� �Ϲ������� �����ڳ� ���� ���͸��� ���°� ����.


