package effective.java.chapter6.item39;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class RunTests {

	public static void main(String[] args) throws Exception {
		int tests = 0;
		int passed = 0;

		Class<?> testClass = Class.forName("effective.java.chapter6.item39.Sample"); // Ŭ���� ���ͷ�
		for(Method m : testClass.getDeclaredMethods()) {
			if(m.isAnnotationPresent(Test.class)) { // ������ Test Ŭ������ ����
				tests ++;
				try {
					m.invoke(null); // �Ķ���� ���� �޼��带 ȣ���ϰ� �ȴ�.
					passed++;
				} catch(InvocationTargetException wrappedExc) {
					Throwable exc = wrappedExc.getCause();
					System.out.println(m + "����: " + exc);
				} catch(Exception exc) {
					System.out.println("�߸� ����� @Test: " + m);
				}
			}
		}

        System.out.printf("����: %d, ����: %d%n", passed, tests - passed);
	}

}
