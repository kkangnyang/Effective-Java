# ITEM65. ���÷��Ǻ��ٴ� �������̽��� ����϶�


### ���÷���

���÷��� ���(java.lang.reflect)�� �̿��ϸ� ���α׷����� ������ Ŭ������ ������ �� �ִ�. Class ��ü�� �־����� �� Ŭ������ ������, �޼���, �ʵ忡 �ش��ϴ� Constructor, Method, Field �ν��Ͻ��� ������ �� �ְ�, �̾ �� �ν��Ͻ���δ� �� Ŭ������ ��� �̸�, �ʵ� Ÿ��, �޼��� �ñ״�ó ���� ������ �� �ִ�.


���� Constructor, Method, Field �ν��Ͻ��� �̿��� ������ ����� ���� ������, �޼���, �ʵ带 ������ ���� �ִ�. ���� ��� Method.invoke�� � Ŭ������ � ��ü�� ���� � �޼���� ȣ���� �� �ִ�. 


### ���÷��� ����

- ������Ÿ�� Ÿ�� �˻簡 �ִ� ������ �ϳ��� ���� �� ����.
- ���÷����� �̿��ϸ� �ڵ尡 ������������ �������� ��������.
- ���÷����� ���� �޼��� ȣ���� �Ϲ� �޼��� ȣ�⺸�� �ξ� ������.
- ���÷����� ���� ���ѵ� ���·θ� ����ؾ� �� ������ ���ϰ� ������ ���� �� �ִ�.


���� ��� Set<String> �������̽��� �ν��Ͻ��� �����ϴµ�, ��Ȯ�� Ŭ������ ������� ù ��° �μ��� Ȯ���Ѵ�. �׸��� �޼��� ȣ���� �������̽��� ���ؼ� ȣ�� �Ѵ�.


```java
public class ReflectiveInstantiation {

	public static void main(String[] args) {
		// Ŭ���� �̸��� Class ��ü�� ��ȯ
	    Class<? extends Set<String>> cl = null;
	    try {
	    	cl = (Class<? extends Set<String>>)  // ��˻� ����ȯ!
	    			Class.forName("java.util.HashSet");
	    } catch (ClassNotFoundException e) {
	    	fatalError("Ŭ������ ã�� �� �����ϴ�.");
	    }

	    // �����ڸ� ��´�.
	    Constructor<? extends Set<String>> cons = null;
	    try {
	    	cons = cl.getDeclaredConstructor();
	    } catch (NoSuchMethodException e) {
	    	fatalError("�Ű����� ���� �����ڸ� ã�� �� �����ϴ�.");
	    }

	    // ������ �ν��Ͻ��� �����.
	    Set<String> s = null;
	    try {
	    	s = cons.newInstance();
	    } catch (IllegalAccessException e) {
	    	fatalError("�����ڿ� ������ �� �����ϴ�.");
	    } catch (InstantiationException e) {
	    	fatalError("Ŭ������ �ν��Ͻ�ȭ�� �� �����ϴ�.");
	    } catch (InvocationTargetException e) {
	    	fatalError("�����ڰ� ���ܸ� �������ϴ�: " + e.getCause());
	    } catch (ClassCastException e) {
	    	fatalError("Set�� �������� ���� Ŭ�����Դϴ�.");
	    }

	    // ������ ������ ����Ѵ�.
	    s.addAll(Arrays.asList("a","b","c").subList(1, 3));
	    System.out.println(s);

	}
	
	private static void fatalError(String msg) {
	    System.err.println(msg);
	    System.exit(1);
	}

}
```


���� �ڵ�� ���÷����� �� ���� ������ �����ش�.

- ���÷��� ���� �����ߴٸ� ������ Ÿ�ӿ� ��Ƴ� �� �ִ� ��������, ��Ÿ�� �� �� ���������� �Ǵ� ���ܸ� ���� �� �ִ�.

- Ŭ���� �̸������� �ν��Ͻ��� �����س��� ���� 25���̳� �Ǵ� �ڵ带 �ۼ��ߴ�. ���� ���÷��� ���� ������ ��� ��� ��� ���÷��� ������ ���� Ŭ������ ReflectOperationException�� �⵵�� �Ͽ� �ڵ� ���̸� ���� �� �ִ�.



������ Ÿ�ӿ� �� �� ���� Ŭ������ ����ؾ� �Ѵٸ� ���÷����� ����� �� �ۿ� ���� ���̴�. �� ��쿡�� ��ü �������� ���÷����� ����ϰ� ��ü�� ����� ���� ������ �������̽��� ���� Ŭ������ �������.


=> ���÷����� ������ ���ϰ� ������ ���� �� �ִ� ���� ���ѵ� ���·θ� �������!
