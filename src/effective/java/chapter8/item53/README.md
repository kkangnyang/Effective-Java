# ITEM53. �����μ��� ������ ����϶�

`int... args`�� ���� �����μ��� �޼��忡 �Ķ���ͷ� �ѱ� �� �ִ�. �޼��尡 ȣ��Ǹ� ���� ���� �μ��� ������ ���̰� ���� �迭�� �����, �μ����� �� �迭�� �����Ͽ� �޼��忡 �ǳ��ش�.

- �μ��� 1�� �̻��̾�� �ϴ� �����μ� �޼���

```java
static int min(int... args) {
    if (args.length == 0)
        throw new IllegalArgumentException("�μ��� 1�� �̻� �ʿ��մϴ�.");
    int min = args[0];
    for (int i = 1; i < args.length; i++)
        if (args[i] < min)
            min = args[i];
    return min;
}
```

�� ��Ŀ��� �޼��尡 �������� �� (��Ÿ��) �Ķ������ ��ȿ���� �˻��ؼ� Exception �� ������. �̷� ��� ���� ��������� �ʼ��� �޾ƾ��ϴ� �Ķ���ʹ� �ް�, �ɼų��� �μ����� �����μ��� �޵��� �����ϸ� �� ����� �ڵ尡 �ȴ�.

- �μ��� 1�� �̻��̾�� �ϴ� �����μ� �޼��� (�ùٸ� ��)

```java
static int min(int firstArg, int... remainingArgs) {
    int min = firstArg;
    for (int arg : remainingArgs)
        if (arg < min)
            min = arg;
    return min;
}
```

���ɿ� �ΰ��� ��Ȳ�̶�� �����μ��� ���� ����� �ƴϴ�. �ֳ��ϸ� �޼��尡 ȣ��� ������ �迭�� ���� �ϳ� �Ҵ��ϰ� �ʱ�ȭ�ϱ� �����̴�. ���� ������ ����ؾ��Ѵٸ� **�������Ǹ� ����**

```java
public void foo() {}
public void foo(int a1) {}
public void foo(int a1, int a2) {}
public void foo(int a1, int a2, int... rest) {}
```
