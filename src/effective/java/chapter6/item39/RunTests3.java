package effective.java.chapter6.item39;

import java.lang.reflect.Method;

public class RunTests3 {

	public static void main(String[] args) throws Exception {
		int tests = 0;
	    int passed = 0;
	    Class<?> testClass = Class.forName("effective.java.chapter6.item39.Sample3"); // Ŭ���� ���ͷ�
	    for (Method m : testClass.getDeclaredMethods()) {
	        if (m.isAnnotationPresent(ExceptionArrayTest.class)) {
	            tests++;
	            try {
	                m.invoke(null);
	                System.out.printf("�׽�Ʈ %s ����: ���ܸ� ������ ����%n", m);
	            } catch (Throwable wrappedExc) {
	                Throwable exc = wrappedExc.getCause();
	                int oldPassed = passed;
	                Class<? extends Throwable>[] excTypes = m.getAnnotation(ExceptionArrayTest.class).value();
	                
	                // ������ Exception �߿� �ϳ��� ������ passed ����
	                for(Class<? extends Throwable> excType : excTypes) {
                	  if(excType.isInstance(exc)) {
                	    passed++;
                	    break;
                	  }
                	}
	                // ��ġ�� ���� passed ������ �����ϴٸ�, ���� Exception �� �ϳ��� ���� ���̴� fail
	                if(passed == oldPassed) {
	                  System.out.printf("�׽�Ʈ %s ���� : ����� ���� %s, �߻��� ���� %s%n", m, exc);
	                }  
	            }
	        }

	        System.out.printf("����: %d, ����: %d%n", passed, tests - passed);
	    }

	}

}
