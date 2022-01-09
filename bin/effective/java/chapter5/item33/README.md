# ITEM33. Ÿ�� ���� ���� �����̳ʸ� ����϶�

���׸��� Set<E>, Map<K,V> ���� �÷��ǰ� ThreadLocal<T>, AtomicReference<T> ���� ���Ͽ��� �����̳ʿ��� ���� ���δ�.

������ Set���� ������ Ÿ���� ���ϴ� �� �ϳ��� Ÿ�� �Ű������� ������ �Ǹ�, Map���� Ű�� ���� Ÿ���� ���ϴ� 2���� �ʿ��� ������ �ϳ��� �����̳ʿ��� �Ű�����ȭ�� �� �ִ� Ÿ���� ���� ���ѵȴ�.

������ �� ������ ������ �ʿ��� ���� ���� �ִ�.

�����ͺ��̽��� ���� ���� ������ ���� ���� �� �ִµ�, ��� ���� Ÿ�� �����ϰ� �̿��� �� �ִٸ� ���� ���̴�.

���� �ع��� �����̳� ��� Ű�� �Ű�����ȭ�� ����, �����̳ʿ� ���� �ְų� �� �� �Ű�����ȭ�� Ű�� �Բ� �����ϸ� �ȴ�.

�̷��� �ϸ� ���׸� Ÿ�� �ý����� ���� Ÿ���� Ű�� ������ �������� ���̸� �̷� ���� ����� Ÿ�� ���� ���� �����̳� �����̶� �Ѵ�.

> Ÿ�� ���� ���� �����̳� ���� (type safe heterogeneous container pattern)

�����̳ʿ� ���� �ְų� �� �� �Ű�����ȭ�� Ű�� �Բ� �����մϴ�. 
��, �� Ÿ���� Class ��ü�� �Ű�����ȭ�� Ű ���ҷ� ����ϴ� ���Դϴ�. 
class ���ͷ��� Ÿ���� Class�� �ƴ� Class<T>�Դϴ�. 
���� ���, String.class�� Ÿ���� Class<String>�̰� Integer.class�� Ÿ���� Class<Integer>�Դϴ�.

������Ÿ�� Ÿ�� ������ ��Ÿ�� Ÿ�� ������ �˾Ƴ��� ���� �޼������ �ְ�޴� class ���ͷ��� Ÿ�� ��ū(type token)�̶� �մϴ�.

```java
public class Favorites {
	
	private Map<Class<?>, Object> favorites = new HashMap<>();
	
	public <T> T getFavorites(Class<T> type) {
		return type.cast(favorites.get(type));
	}

	public <T> void putFavorites(Class<T> type, T instance) {
		favorites.put(Objects.requireNonNull(type), instance);
	}

	public static void main(String[] args) {
		Favorites f = new Favorites();
		
		f.putFavorites(String.class, "Java");
		f.putFavorites(Integer.class, 0xcafebabe);
		f.putFavorites(Class.class, Favorites.class);
		
		String fovoriteString = f.getFavorites(String.class);
		int favoriteInteger = f.getFavorites(Integer.class);
		Class<?> favoriteClass = f.getFavorites(Class.class);
		
		System.out.printf("%s %x %s%n", fovoriteString, favoriteInteger, favoriteClass.getName());;
	}

}
```

Favorites �ν��Ͻ��� Ÿ�� �����մϴ�. String�� ��û�ߴµ� Integer�� ��ȯ�ϴ� ���� ���� �����ϴ�. ���� ��� Ű�� Ÿ���� �������̶�, �Ϲ����� �ʰ� �޸� ���� ���� Ÿ���� ���Ҹ� ���� �� �ֽ��ϴ�.

favorites�� Ÿ���� Map<Class<?>, Object>��, Ű�� ���ϵ�ī�� Ÿ���Դϴ�. �̴� ��� Ű�� ���� �ٸ� �Ű�����ȭ Ÿ���� �� �ִٴ� �� �Դϴ�. ���� Ÿ���� Object�̱� ������ Class�� cast() �޼��带 ����� �ش� ��ü ������ Class ��ü�� ����Ű�� Ÿ������ ���� ����ȯ�� �ؾ��մϴ�.

cast() �޼����� �ñ״�ó�� ���� Class Ŭ������ ���׸��̶�� ���� Ȱ���ϰ� �ֽ��ϴ�. �̸� ���� T�� ��˻� ����ȯ�ϴ� �ս� ���̵� Favorites�� Ÿ�� �����ϰ� ���� �� �ֽ��ϴ�.

```java
public class Class<T> {
    T cast(Object obj);
}
```


> Ÿ�� ���� ���� �����̳��� ����

1. �������� Ŭ���̾�Ʈ�� Class ��ü�� (���׸��� �ƴ�) �� Ÿ������ �ѱ�� Favorites �ν��Ͻ��� Ÿ�� �������� ���� �����ϴ�.

```java
f.putFavorite((Class)Integer.class, "Integer�� �ν��Ͻ��� �ƴմϴ�");
int favoriteInteger = f.getFavorite(Integer.class);
```

������ �̷��� ¥���� Ŭ���̾�Ʈ �ڵ忡���� �������� �� ��˻� ��� �� ���Դϴ�.
 
HashSet�� HashMap ���� �Ϲ� �÷��� ����ü���� ��Ÿ���� ����ϸ� HashSet<Integer>�� String�� ���� �� �ִٴ� ������ �ֽ��ϴ�.

```java
HashSet<Integer> set = new HashSet<>();
((HashSet)set).add("���ڿ��Դϴ�.");
```

�ռ� ���Ҵ� Favorites ��ü�� Ÿ�� �Һ����� ���� ���� ������ �����Ϸ��� putFavorite() �޼��忡�� �μ��� �־��� instance�� Ÿ���� type���� ����� Ÿ�԰� ������ Ȯ���ϸ� �˴ϴ�. ���� �ڵ�� ���� ���� ����ȯ�� ����ϸ� �˴ϴ�.

```java
public <T> void putFavorite(Class<T> type, T instance) {
    favorites.put(Objects.requireNonNull(type), type.cast(instance));
}
```

2. Ÿ�� ���� ���� �����̳ʴ� ��üȭ �Ұ� Ÿ�Կ��� ����� �� �����ϴ�. ��, String�̳� String[]�� ����� �� ������ List<String>�� ��üȭ �Ұ� Ÿ���̹Ƿ� ����� �� �����ϴ�.

List<String>�� List<Integer>�� List.class��� ���� Class ��ü�� �����մϴ�.


> ������ Ÿ�� ��ū

Favorites�� ����ϴ� Ÿ�� ��ū�� ���������Դϴ�. ���δ� �� �޼������ ����ϴ� Ÿ���� �����ϰ� ���� ���� �ִµ�, ������ Ÿ�� ��ū�� Ȱ���ϸ� �˴ϴ�.

������ Ÿ�� ��ū�̶� �ܼ��� **������ Ÿ�� �Ű�����**�� **������ ���ϵ�ī��**�� ����Ͽ� ǥ�� ������ Ÿ���� �����ϴ� Ÿ�� ��ū�Դϴ�.



