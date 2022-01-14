package effective.java.chapter6.item39;

import java.lang.reflect.Method;

public class RunTests3 {

	public static void main(String[] args) throws Exception {
		int tests = 0;
	    int passed = 0;
	    Class<?> testClass = Class.forName("effective.java.chapter6.item39.Sample3"); // 클래스 리터럴
	    for (Method m : testClass.getDeclaredMethods()) {
	        if (m.isAnnotationPresent(ExceptionArrayTest.class)) {
	            tests++;
	            try {
	                m.invoke(null);
	                System.out.printf("테스트 %s 실패: 예외를 던지지 않음%n", m);
	            } catch (Throwable wrappedExc) {
	                Throwable exc = wrappedExc.getCause();
	                int oldPassed = passed;
	                Class<? extends Throwable>[] excTypes = m.getAnnotation(ExceptionArrayTest.class).value();
	                
	                // 선언한 Exception 중에 하나라도 맞으면 passed 증가
	                for(Class<? extends Throwable> excType : excTypes) {
                	  if(excType.isInstance(exc)) {
                	    passed++;
                	    break;
                	  }
                	}
	                // 그치만 이전 passed 개수와 동일하다면, 맞은 Exception 이 하나도 없던 것이니 fail
	                if(passed == oldPassed) {
	                  System.out.printf("테스트 %s 실패 : 기대한 예외 %s, 발생한 예외 %s%n", m, exc);
	                }  
	            }
	        }

	        System.out.printf("성공: %d, 실패: %d%n", passed, tests - passed);
	    }

	}

}
