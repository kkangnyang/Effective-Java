package effective.java.chapter9.item65;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.Set;

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
