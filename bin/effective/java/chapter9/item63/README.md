# ITEM63. ���ڿ� ������ ������ �����϶�

������17������ ����ߵ��� ���ڿ��� �Һ��̶� �� ���ڿ��� + �����ڷ� ������ ��� ������ ������ ��� �����ؾ��ϹǷ� ���� ���ϸ� �ʷ��Ѵ�. ���� `StringBuilder` �� �������

```java
public static String statement2() {
	StringBuilder b = new StringBuilder(numItems.size());
	for (int i = 0; i < numItems.size(); i++)
		b.append(numItems.get(i));
	return b.toString();
}
```