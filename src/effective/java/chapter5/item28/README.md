# ITEM28. �迭���ٴ� ����Ʈ�� ����϶�

�迭�� ���ʸ� Ÿ�Կ��� ������ ���� �������� �ִ�.

- �迭�� ����(covariant)�̴�. Sub�� Super�� ����Ÿ���̶��, Sub[]�� Super[]�� ���� Ÿ���� �ȴ�. �ݸ�, List�� List�� �ƹ� ����� ����.
- �迭�� ��üȭ(relify)�� �ȴ�. ��Ÿ�� �� �迭�� �ڽ��� ��� �ִ� ������ Type�� �˰� ������, ���ʸ��� �Ұŵȴ�.

�迭�� ���ʸ� Ÿ��, parametrized type, Ÿ�� �Ű������� ����� �� ����. �ֳ��ϸ� Type Safety ���� �ʱ� �����̴�.

new List<E>[], new List<String>[], new E[] ������ �ۼ��ϸ� �������� �� ���ʸ� �迭 ���� ������ ����Ų��.

```
Stack,java:8: generic array creation
elements = new E[DEFAULT_INITIAL_CAPACITY];
```

�� é�Ϳ��� ����� ����, �� ������ �ִ�.
- ���ʸ��� ��Ÿ�� �� �� Ÿ���� �Ұŵǰ� �迭�� Ÿ���� ����ϰ� �־ ��� �������� �ȴ�.
- ���ʸ� Ÿ���� ������ ��Ÿ�ӿ� ClassCastException�� �߻��� �����ִ� ���ε�, ���ʸ� Ÿ���� �迭�� ������ �� �ִٸ� �� ������ ���ϰ� �ȴ�.
- �Ұ� ��Ŀ���� ������ ��üȭ�� ������ parameterized type�� List<?>�� Map<?,?>�� ���� �������� ���ϵ�ī�� ���̴�.

## ���ʸ� �迭 ������ �ȵǴ� ����
���� ���ʸ� Ÿ���� ��� �ִ� �迭�� ������ �� �ִٰ� �����غ���. (1) �ڵ�� ������ �����ϵ��� �ʴ´�.

```java
public static void main(String[] args) {
	List<String> stringLists = new List<String>()[1];	// (1)
	List<Integer> intList = List.of(42);				// (2)
	Object[] objects = stringLists;						// (3)
	objects[0] = intList;								// (4)
	String s = stringLists[0].get(0);					// (5)
}
```

(3) : (1)���� ������ List<String>�� ���ҷ� ��� �ִ� �迭�� Object[]�� �Ҵ��Ѵ�. List<String> ���� Object�̱� ������ �Ҵ��� �� �ִ�.

(4) : (2)���� ������ List<Integer>�� �ν��Ͻ��� Object[]�迭�� ù ���ҷ� �����Ѵ�. 

**���ʸ��� �ҰŹ���̱� ������ ������ �� List�� Ÿ���� �ܼ��� List�� �Ǳ� ������ `ArrayStoreException`�� �߻����� �ʴ´�.**

�̷��� �Ǹ� List<String> �ν��Ͻ��� ��ڴٰ� ������ stringLists �迭�� List�� �ν��Ͻ��� ����� �ִ�. �׸��� (5)���� `ClassCastException`�� �߻��Ѵ�.


## �迭���� ����Ʈ�� �����ϴ� ����
�� é�Ϳ��� �迭���� ����Ʈ�� �����ϴ� ������ ������ �˾ƺ� �� ó�� Type Safety ������ �ְ�, �� ����ȯ�� �� ���ʸ� �迭 ���� ������ unchecked exception�� ���� �ߴ� ��찡 �ֱ� �����̴�.

������ ���� �÷��� ���� ���Ҹ� �������� ��ȯ�ϴ� �޼��尡 �ִٰ� �غ���.

```java
public class Chooser {
	private final Object[] choiceArray;
	
	public Chooser(Collection choices) { // ��Ÿ���� ���.
		choiceArray = choices.toArray();
	}
	
	public Object choose() {
		Random rnd = ThreadLocalRandom.current();
		return choiceArray[rnd.nextInt(choiceArray.length)]; // ���⼭ ����ȯ ������ �� �� �ִ�.
	}

}
```

����ȯ ������ ���� ���� ���ʸ� Ŭ������ ������.

```java
public class GenericChooser<T> {
	private final T[] choiceArray;
	
	public GenericChooser(Collection<T> choices) {
		choiceArray = choices.toArray(); // compile error
	}
	
	public Object choose() {
		Random rnd = ThreadLocalRandom.current();
		return choiceArray[rnd.nextInt(choiceArray.length)]; // ���⼭ ����ȯ ������ �� �� �ִ�.
	}
}
```

>Object[] cannot be converted to T[]

�� ������ �߸鼭 �������������� �ʴ´�. Object[]�� T[]�� ����ȯ�ϸ� ������ ��� �ߴµ�, Unchecked cast exception �� ��� ���.

> Type safety: Unchecked cast from Object[] to T[]

T�� ���� Ÿ������ �� �� ������ �����Ϸ� ���忡���� �� ����ȯ�� �������� ������ �� ���ٴ� �޽�����. �̷��� ��� �޽����� ���ְ��� �ϸ� T[] ���ٴ� ����Ʈ�� ���� �ȴ�.

```java
public class GenericChooser<T> {
	//private final T[] choiceArray;
	private final List<T> choiceList;
	
	public GenericChooser(Collection<T> choices) {
		//choiceArray = choices.toArray(); // compile error
		choiceList = new ArrayList<>(choices);
	}
	
	public Object choose() {
		Random rnd = ThreadLocalRandom.current();
		//return choiceArray[rnd.nextInt(choiceArray.length)]; // ���⼭ ����ȯ ������ �� �� �ִ�.
		return choiceList.get(rnd.nextInt(choiceList.size()));
	}
}
```
