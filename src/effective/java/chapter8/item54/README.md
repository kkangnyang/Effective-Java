# ITEM54. null�� �ƴ�, �� �÷����̳� �迭�� ��ȯ�϶�


������ ġ� ���� �� ��������� null�� ��ȯ�ϴ� �ڵ��̴�.

```java
public List<Cheese> getCheeses() {
    return cheesesInStock.isEmpty() ? null : new ArrayList<>(cheesesInStock);
}
```

���� �ڵ�ó�� �ۼ��ϸ� Ŭ���̾�Ʈ�� null ��Ȳ�� ó���ϴ� �ڵ带 �߰��� �ۼ��ؾ��Ѵ�. 

```java
Shop shop = new Shop();
List<Cheese> cheeses = shop.getCheeses();
if (cheeses != null && cheeses.contains(Cheese.STILTON))
    System.out.println("ġ�� ȹ��!");
```


�÷����̳� �迭 ���� �����̳ʰ� ����� �� null�� ��ȯ�ϴ� �޼��带 ����� ���� �׽� �̿� ���� ��� �ڵ带 �־�����Ѵ�. ��� �ڵ带 �������� ������ �߻��� ���� �ִ�.


���δ� �� �����̳ʸ� �Ҵ��ϴ� ���� ����� ��� null�� ��ȯ�ϴ� ���� ���ٴ� ���嵵 �ִ�. ������ �̴� �ΰ��� �鿡�� Ʋ�� �����̴�.


- ���� �м� ��� �� �Ҵ��� ���� ������ �ֹ��̶�� Ȯ�ε��� �ʴ� �� �� ������ ���� ���̴� �Ű� �� ������ �ƴϴ�.
-  �� �÷��ǰ� �迭�� ���� ���� �Ҵ����� �ʰ� ��ȯ�� �� �ִ�. ������ �� �÷����� ��ȯ�ϴ� �������� �ڵ��, ��κ��� ��Ȳ������ �̷��� �ϸ� �ȴ�.

```java
public List<Cheese> getCheeses() {
    return new ArrayList<>(cheesesInStock);
}
```


���ɼ��� ������, ��� ���Ͽ� ���� �� �÷��� �Ҵ��� ������ ���� ��� ����߸� ���� �ִ�. �׷� ��� �Ź� �Ȱ��� �� '�Һ�' �÷����� ��ȯ�ϸ�ȴ�.

```java
public List<Cheese> getCheeses() {
    return cheesesInStock.isEmpty() ? Collections.emptyList() : new ArrayList<>(cheesesInStock);
}
```


�迭�� �� ���� ����������. ���� null�� ��ȯ���� ���� ���̰� 0�� �迭�� ��ȯ�϶�. toArray �޼���� cheesesInStock �迭�� ���Ұ� ������ cheesesInStock �迭�� ��ȯ�ϰ�, �׷��� ������ Cheese[] Ÿ���� �迭�� ���� ������ ��ȯ�Ѵ�.

```java
public Cheese[] getCheeses() {
    return cheesesInStock.toArray(new Cheese[0]);
}
```


�� ����� ������ ����߸��� ������ �� �Һ� �迭�� ����� �Ź� ���� �Ҵ����� �ʵ����Ѵ�.

```java
private static final Cheese[] EMPTY_CHEESE_ARRAY = new Cheese[0];

public Cheese[] getCheeses() {
    return cheesesInStock.toArray(EMPTY_CHEESE_ARRAY);
}
```

