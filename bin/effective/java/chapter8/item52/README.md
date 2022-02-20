# ITEM52. �������Ǵ� ������ ����϶�

��������(Overloading)�� ��� �޼��尡 ȣ��� �� ������ Ÿ�ӿ� ��������.

```java
public class CollectionClassifier {
    public static String classify(Set<?> s) {
        return "����";
    }

    public static String classify(List<?> lst) {
        return "����Ʈ";
    }

    public static String classify(Collection<?> c) {
        return "�� ��";
    }

    public static void main(String[] args) {
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };

        for (Collection<?> c : collections)
            System.out.println(classify(c));
    }
}

// ��� :
// �� ��
// �� ��
// �� ��
```

���� �ڵ带 �����ؼ� ����� ���� ������, ����Ʈ, �� �ܡ� ���� ���� �ܡ��� �� �� ��µȴ�. classify �޼��忡 ���޵� �Ķ���Ͱ� `Collection<?>` Ÿ���̱� �����̴�. ��Ÿ�ӽÿ��� Ÿ���� �Ź� �޶������� ȣ���� �޼��带 �����ϴ� ���� ������ ���� ���Ѵ�.


�ݸ�, ������(Overriding)�� ����� �Ʒ� �ڵ带 ����


```java
static class Wine {
    String name() { return "������"; }
}

static class SparklingWine extends Wine {
    @Override String name() { return "������ ������"; }
}

static class Champagne extends SparklingWine {
    @Override String name() { return "������"; }
}

public static void main(String[] args) {
    List<Wine> wineList = List.of(
            new Wine(), new SparklingWine(), new Champagne());

    for (Wine wine : wineList)
        System.out.println(wine.name());
}

// ������
// ������ ������
// ������
```

for �������� ������Ÿ�� Ÿ���� ��� Wine�� �Ϳ� �����ϰ� �׻� ������ �������� �����ѡ� ������ �޼��尡 ���õǴ� ���̴�.

����, �ռ� ���캻 `CollectionClassifier` �� ����� �����Ϸ���, instanceof�� ��������� �˻��ϸ� ������ �ذ�ȴ�.

```java
public static String classify(Collection<?> c) {
    return c instanceof Set  ? "����" :
            c instanceof List ? "����Ʈ" : "�� ��";
}

public static void main(String[] args) {
    Collection<?>[] collections = {
            new HashSet<String>(),
            new ArrayList<BigInteger>(),
            new HashMap<String, String>().values()
    };

    for (Collection<?> c : collections)
        System.out.println(classify(c));
}
```

## �������ǰ� ȥ���� ����Ű�� ��Ȳ�� ���ؾ� �Ѵ�.

�����ϰ� ���������� ������ �Ű����� ���� ���� �������Ǵ� ������ ����. ���������ϴ� ��� �޼��� �̸��� �ٸ��� �����ִ°� ����. (ObjectOutputStream�� writeBoolean, writeInt ó��)


�Ű����� ���� ������ �������� �޼��尡 ������, ��Ȯ�� ���и� �ȴٸ� �򰥸� ���� ����. ��, �Ű����� Ÿ���� ���� ����ȯ�� �ɼ� ���� Ÿ���̶�� ��Ȯ�� ���е� ���̴�.


List �������̽��� remove(Object)�� remove(int) �� �������� �ߴµ�, �� ������ �Ʒ� ������ Ȯ���� �� �ִ�.

```java
public static void main(String[] args) {
	Set<Integer> set = new TreeSet<>();
    List<Integer> list = new ArrayList<>();

    for (int i = -3; i < 3; i++) {
        set.add(i);
        list.add(i);
    }
    for (int i = 0; i < 3; i++) {
        set.remove(i);
        list.remove(i);
    }
    System.out.println(set + " " + list);
    
    // [-3, -2, -1] [-2, 0, 2]
}
```

����, �ڹ�8���� ������ ���ٿ� �޼��� ���� ���� �������� �ÿ� ȥ���� Ű����.

```java
// 1��
new Thread(System.out::println).start();
// 2��
ExecutorService exec = Executors.newCachedThreadPool();
exec.submit(System.out::println);
```

2�� ���̽��� ������ ����. ������ �޼��� (println)�� ȣ���� �޼���(submit) ���� ��� �������ǵǾ��ֱ� �����̴�. ���� �޼��带 ���������� ��, ���� �ٸ� �Լ��� �������̽��� **���� ��ġ�� �μ��� �޾Ƽ��� �ȵȴ�.**

� �������� �޼��尡 �Ҹ����� ���� ����� �Ȱ��ٸ� �Ű澲�� �ʾƵ� �ȴ�. ���� ���, String�� contentEquals(StringBuffer) �޼���, contentEquals(CharSequence) �޼����� ����� �����ϴ�!

�̷� ��� ���� ��������� �� Ư���� �������� �޼��忡�� �� Ư����(�� �Ϲ�����) �������� �޼���� ���� �Ѱܹ����� ���̴�.

```java
public boolean contentEquals(StringBuffer sb) {
  return contentEquals((CharSequence) sb);
}
```

���� ���ܵ� �ִ�. String.valueOf(char[]) �� String.valueOf(Object)�� ���� ��ü�� �ǳ״��� ���� �ٸ����� �����Ѵ�.