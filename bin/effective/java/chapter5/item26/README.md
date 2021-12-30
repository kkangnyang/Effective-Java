# ITEM26. ��(Raw) Ÿ���� ������� ����

## Raw Type�̶�
class�� interface ���� type parameter�� ���̸�, �̸� ���׸� Ŭ���� Ȥ�� ���׸� �������̽��� �Ѵ�.

```java
public interface List<E> extends Collection<E> {...}
```

List �������̽��� ������ Ÿ���� ��Ÿ���� type parameter E�� �޴´�.

List�������̽��� �� ��, Ÿ���� E�� ��ġ�� �־ ����Ѵ�.
List<String>�� ������ Ÿ���� String�� ����Ʈ�� ���ϴ� parameterized type�̴�. -> �Ű������� ���� Ÿ���̶�� ��

�̷��� ���׷� Ÿ���� �ϳ� �����ϸ� �׿� ���� raw type�� �Բ� ���ǵȴ�.

- Raw Type : ���׸� Ÿ�Կ��� Ÿ�� �Ű������� ���� ������� ���� ���� ���Ѵ�.

List<E>�� Raw Type�� �Ű������� ���� List �� ��ü�̴�.

���׸��� �ڹ�5���� ����� �� �ִ�. Raw Type�� ���׸��� �����ϱ� ���� �ڵ忡 ���� ȣȯ�� ������ �ÿ���å�̶� �� �� �ִ�.

```java
List list = new ArrayList(); // Raw type
List<Integer> listIntegers = new ArrayList<>(); // parameterized type
```

Raw Type�� �ڹ�5 ���� generic�� ���� ���Ž� �ڵ忡 ���� �� �ִ�. ������, �츮�� �������� ����ϸ� �ȵȴ�! 

����:
- Not expressive
- They lack type safety, and
- Problems are observed at run time and not at compile time

## ������ Ÿ���� �ƴ� ��Ÿ�ӿ� ������ �����Ѵ�.

### �� Ÿ�� ���𿡼��� ��

```java
public class RawType {
	
	static class Stamp {
		public Stamp() {
			
		}
	}
	
	static class Coin {
		public Coin() {
			
		}
	}
	
	private final static Collection stamps = new ArrayList<>();
	
	public static void main(String[] args) {
		stamps.add(new Coin());
	}

}
```

�� �ڵ带 �����ϸ� �Ʒ��� ����

- Stamp, Coin Ŭ����
- Collection �� Ÿ������ stamps ����(�����ڴ� Stamp��� Ŭ������ ��´ٴ� �ǹ̷� �ش� ���� ����)
- �Ǽ��� Stamp ��� Coin �߰�

�� ��, �Ǽ��� Stamp ��� Coin�� �ش� �÷��ǿ� �־ �Ʒ��� ���� ���â�� �����ְ� ���� ���� �����ϵǰ� ����ȴ�.

>Type safety: The method add(Object) belongs to the raw type Collection. References to generic type Collection should be parameterized


�� �׷�, Coin�� ����� �ִ� stamps���� ���Ҹ� ��������, (�翬�� Stamp�� �� ���� �Ŷ�� ������ �����ڰ�) Stamp�� ����ȯ �غ���.

```java
public static void main(String[] args) {
	stamps.add(new Coin());
	
	for (Iterator iterator = stamps.iterator(); iterator.hasNext();) {
		Stamp stamp = (Stamp) iterator.next(); // ClassCaseException!
		
	}
}
```

>java.lang.ClassCastException: ch5.sunmin.item26.RawType$Coin cannot be cast to ch5.sunmin.item26.RawType$Stamp

��, �÷��ǿ��� �� ������ �ٽ� ������ ������ ������ �˾�ä�� ���Ѵ�. ������ Ÿ���� �ƴ� ��Ÿ�ӿ� ������ �����Ѵ�.


### ���ʸ��� Ȱ���ؼ� Ÿ�� ����

```java
private final static Collection<Stamp> stampsGeneric = new ArrayList<>();
```

�̷��� ���ʸ��� ����ؼ� Ÿ���� �Ű������� �������ָ�, �����Ϸ����� Stamp�� �ν��Ͻ��� �־�� ���� �����ϰ� �ȴ�. ���� �ǵ���� ������ ������ �����Ѵ�.

```java
public static void main(String[] args) {
	stampsGeneric.add(new Coin()); // compile error
}
```
���� �Ȱ��� �߸��� Ŭ������ ���� ���, ������ ������ �ȵǰԲ� ������ �����ִ� ���� �� �� �ִ�.

### �� Ÿ���� �Ķ���ͷ� ���� �޼��忡���� ��

��Ÿ���� List�� �޴� �޼��忡 List<String>�� �ѱ� ���� �ִ�. �ֳ��ϸ� List<String>�� �� Ÿ���� List�� ���� Ÿ���̱� �����̴�.

```java
public class RawTypeMethod {
	
	private static void unsafeAdd(List list, Object o) {
		list.add(o);
	}

	public static void main(String[] args) {
		List<String> strings = new ArrayList<>();
		unsafeAdd(strings, Integer.valueOf(42));
		String s = strings.get(0);
	}
}
```

������ ������ �ϸ� �������� ���������� Integer �� String���� ��ȯ�Ϸ� �õ��ϱ� ������ ClassCastException�� ������ �ȴ�.

���� unsafeAdd �޼����� �Ķ���͸� List���� List<Object>�� �ٲ㺸��.

```java
public class GenericTypeMethod {

	private static void unsafeAdd(List<Object> list, Object o) {
		list.add(o);
	}

	public static void main(String[] args) {
		List<String> strings = new ArrayList<>();
		unsafeAdd(strings, Integer.valueOf(42)); // compile error
		String s = strings.get(0);
	}

}
```

�ƿ� �������� ���� �ʴ´�. �ֳ��ϸ� List<String>�� List<Object>�� ���� Ÿ���� �ƴϱ� �����̴�. [������28]

�������ڸ�, �� Ÿ���� List�� ��� Ÿ���� ���Ҹ� �Ѱܹ��� �� ������, �Ű�����ȭ Ÿ���� List<Object>�� List<Object>�θ� �Ķ���͸� ���� �� �ִ�.

�̷��� �ϸ� Type Safety �ϰ� �ڵ带 �ۼ��� �� ������, �ƿ� ������ Ÿ���� ���� �Ǵ� �� Ÿ���� ���� ���� �� �ִ�.

## �������� ���ϵ�ī�� Ÿ��

```java
private static int numElementsInCommon(Set s1, Set s2) {
	int result = 0;
	for (Object o1 : s1) {
		if (s2.contains(o1)) {
			result++;
		}
	}
	return result;
}
```

��Ÿ���� Set �� �Ķ���ͷ� �޾Ƽ�, �𸣴� Ÿ���� ���ҵ� ���� �� �ֵ��� �ۼ��ߴ�. �޼���� ���� �۵������� Type Safety ���� ���ϴ�. (���� ����ó�� �ٸ� Type�� ������ �� ClassCastException) �̷� ���� �������� ���ϵ�ī�� Ÿ�� (unbounded wildcard type) �� ��� ����ϴ°� ����.

���׸� Ÿ���� ����� ������ ���� Ÿ�� �Ű������� �������� �Ű� ���� ���� �ʴٸ� unbounded wildcard type�� ����ϸ� �ȴ�.

```java
private static int numElementsByWildCardInCommon(Set<?> s1, Set<?> s2) {
	s1.add("Not Inter String");
}

public static void main(String[] args) {
	Set<Integer> set1 = new HashSet<>();
	set1.add(Integer.valueOf(10));
	Set<Integer> set2 = new HashSet<>();
	set2.add(Integer.valueOf(25));
	
	System.out.println(numElementsByWildCardInCommon(set1, set2));

}
```
>The method add(capture#1-of ?) in the type Set<capture#1-of ?> is not applicable for the arguments (String)

�� �ҽ��� Set�� Integer�� �ְ�, �޼��忡 �Ķ���ͷ� �����ߴ�. �� Set�� String�� �ٽ� �������� �Ʒ��� ���� ������ �����ϵ��� �ʴ´�. ���� �׳� ��Ÿ�� ���ٴ� Type Safe �ϴٰ� �� �� �ִ�.

## �� Ÿ���� ����ϴ� ����

### class ���ͷ�

�ڹ� ���� parameterized type�� �迭�� �⺻Ÿ�Կ��� ��������� List<String>.class �� List<?>.class �� ���� class ���ͷ��� �Ű�����ȭ Ÿ���� ������� ���ϰ� �ߴ�.

����. https://homoefficio.github.io/2016/11/30/%ED%81%B4%EB%9E%98%EC%8A%A4-%EB%A6%AC%ED%84%B0%EB%9F%B4-%ED%83%80%EC%9E%85-%ED%86%A0%ED%81%B0-%EC%88%98%ED%8D%BC-%ED%83%80%EC%9E%85-%ED%86%A0%ED%81%B0/

- Ŭ���� ���ͷ� (Class Literal)�� String.class, Integer.class ���� ���ϸ� �ϳ��� ��ü�� �����ϸ� �ȴ�. �׷���, �̵��� Ÿ�����δ� .. String.class �� Ÿ���� Class<String>, Integer.class�� Ÿ���� Class<Integer> �� �� �� �ִ�.

- �׷��� Class<T> �� ��Ÿ���� Class�� �Ű�����ȭ Ÿ���̶�� ���� �����غ� �� �ִ�.

Class�� ���ʸ��� �߰��Ͽ� Class<T> ó�� �Ű�����ȭ Ÿ���� �Ķ���ͷ� �޴� ���̴�.

```java
public class SimpleTypeSafeMap {
	
	private Map<Class<?>, Object> map = new HashMap<>();

    public <T> void put(Class<T> k, T v) {
        map.put(k, v);
    }

    public <T> T get(Class<T> k) {
        return k.cast(map.get(k));
    }
	
	public static void main(String[] args) {
		SimpleTypeSafeMap simpleTypeSafeMap = new SimpleTypeSafeMap();

		simpleTypeSafeMap.put(String.class, "abcde");
		simpleTypeSafeMap.put(Integer.class, 123);

		String v1 = simpleTypeSafeMap.get(String.class);
		Integer v2 = simpleTypeSafeMap.get(Integer.class);

		// �Ʒ��� ���� List<String>.class��� Ŭ���� ���ͷ��� ���� ���������� �����Ƿ� ��� �Ұ�!!
		simpleTypeSafeMap.put(List<String>.class, Arrays.asList("a", "b", "c"));
	}

}
```

Class<T> ���ʸ� Ÿ������, �پ��� Type�� Map�� put �ϰ� get �Ҷ��� �� Ŭ���� ���ͷ� ��ü�� casting�� �� �� �ִ� Map Ŭ�����̴�.

���⼭ List.class, String[].class, int.class �� ���� Ŭ���� ���ͷ��� Class<T>�� ����ϰ�, List<String>.class �� List<?>.class�� ������� �ʴ´�


### instanceof ������

��Ÿ�ӿ��� ���ʸ� Ÿ�� ������ �������Ƿ� instanceof �����ڴ� �������� ���ϵ�ī�� Ÿ�� �̿��� �Ű�����ȭ Ÿ�Կ��� ������ �� ����.

```java
public static void main(String[] args) {
	Object o = new Object();
	
	if (o instanceof Set) { // ��Ÿ��
		Set<?> s = (Set<?>) o; // ���ϵ�Ÿ������ ����ȯ
	}
	
	if (o instanceof Set<String>) { // ���ʸ� -> ���� �߻�!
		Set<?> s = (Set<?>) o; // ���ϵ�Ÿ������ ����ȯ
	}
}
```