package effective.java.chapter6.item39;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Runtests2 {

	public static void main(String[] args) throws Exception {
		int tests = 0;
	    int passed = 0;
	    Class<?> testClass = Class.forName("effective.java.chapter6.item39.Sample2"); // Ŭ���� ���ͷ�
	    for (Method m : testClass.getDeclaredMethods()) {
	        if (m.isAnnotationPresent(ExceptionTest.class)) {
	            tests++;
	            try {
	                m.invoke(null);
	                passed++;
	            } catch (InvocationTargetException wrappedExc) {
	                Throwable exc = wrappedExc.getCause();
	                Class<? extends Throwable> excType = m.getAnnotation(ExceptionTest.class).value();

	                if(excType.isInstance(exc)) {
	                  passed ++;
	                } else {
	                  System.out.printf("�׽�Ʈ %s ���� : ����� ���� %s, �߻��� ���� %s%n", m, excType.getName(), exc);
	                }

	            } catch (Exception exc) {
	                System.out.println("�߸� ����� @Test: " + m);
	            }
	        }

	        System.out.printf("����: %d, ����: %d%n", passed, tests - passed);
	    }

	}

}
