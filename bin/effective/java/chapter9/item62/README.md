# ITEM62. �ٸ� Ÿ���� �����ϴٸ� ���ڿ� ����� ���϶�


�̹� �����ۿ��� ���ڿ��� ���� �ʾƾ� �� ��ʸ� �ٷ��.

### ���ڿ��� �ٸ� �� Ÿ���� ����ϱ⿡ �������� �ʴ�.

���� �����Ͱ� ��ġ���̶�� int, float �� ������ ��ġŸ������ ��ȯ�ؾ��Ѵ�. �����Ͱ� ��¥ ���ڿ��� ���� ���ڿ��� ����.


### ���ڿ��� ���� Ÿ���� ����ϱ⿡ �������� �ʴ�.

������34���� �̾߱� �ߵ�, ����� ������ ���� ���ڿ����ٴ� ���� Ÿ���� ����.


### ȥ�� Ÿ���� ����ϱ⿡ �������� �ʴ�.

���� ��Ұ� ȥ�յ� �����͸� �ϳ��� ���ڿ��� ǥ���� ���� ���� �ʴ�.

```java
String compoundKey = className + "#" + i.next();
```


### ���ڿ��� ������ ǥ���ϱ⿡ �������� �ʴ�.


������ ���������� ���� ����. ������ ���������� �� �����尡 ���������� ������ �ִ� ����̴�. 1���� �����带 �����Ѵٸ� �� �ȿ��� ������������ ������ �� �ִ�. 


�ڹ� 2���� �� ����� �����ߴµ�, �����带 �ĺ��ϱ� ���ؼ� Ŭ���̾�Ʈ�� ���ڿ� key�� �����������.

```java
public class ThreadLocal {
  private ThreadLocal() {} // ��ü �����Ұ�

  // �� �������� ���� Ű�� ������ �����Ѵ�.
  public static void set(String key, Object value);

  // (Ű�� ����Ű��) �� �������� ���� ��ȯ�Ѵ�.
  public static Object get(String key);
}
```

�� ����� �ǵ���� �����Ϸ��� �� Ŭ���̾�Ʈ�� ������ Ű�� �����ؾ� �Ѵ�. �׷��� Ŀ�´����̼� �̽��� Ű�� �ߺ��ؼ� ����ϸ� �ǵ�ġ ���� ���װ� ���� ���̴�.

�ذ�å�� ���ڿ� ��� ������ �� ���� Ű�� ����ϸ� �ȴ�. �� Ű�� capacity��� �Ѵ�.

```java
public class ThreadLocal {
  private ThreadLocal() {}
  public static class Key {
    Key() {}
  }
  public static Key getKey() {
    return new Key();
  }
  public static void set(Key key, Object value);
  public static Object get(Key key);
}
```

�� �ڵ�� �ռ� ���ڿ� ��� API�� ������ �ذ���������, �� ������ �� �ִ�. Key �� �� ��ü�� ������ ���������� �ȴ�.


```java
public class ThreadLocal {
  public ThreadLocal();
  public void set(Object value);
  public Object get();
}
```


�� API���� get()�� Object ������ ����ȯ ���ֱ� ������ Ÿ�Ծ������� �ʴ�. ���� �Ű�����ȭ Ÿ������ ��������.


```java
public class ThreadLocal<T> {
  public ThreadLocal();
  public void set(T value);
  public T get();
}
```