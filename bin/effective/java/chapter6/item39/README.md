# ITEM39. 명명 패턴보다 애너테이션을 사용하라

JUnit 버전 3까지 테스트 메서드 이름을 test로 시작하게끔 했다. 여기에는 단점이 아래와 같은 단점이 있었다.

- 실수로 오타를 내서 test가 아닌 tste로 테스트 메서드를 명명 했을 경우, JUnit3은 이 메서드를 무시한다.
- 개발자가 메서드가 아닌 클래스 이름을 Test를 붙이고 JUnit에 전달했다고 가정하자. 이 개발자는 자기가 보낸 클래스 안에 있는 메서드를 JUnit3이 테스트해줄거라 기대하지만, JUnit3은 클래스 이름에는 관심없다.
- 특정 행동을 테스트하도록 만드려면 파라미터로 인수를 전달해야하는데, 별로다.

그래서 JUnit4 부터는 어노테이션을 도입했고, 이번 챕터에서는 이러한 도구 역할을 하는 어노테이션에 대해서 알아본다.

## 마커 애너테이션
marker 애너테이션이란 아무 매개변수 없이 단순히 대상에 마킹하는 용도로 쓰이는 애너테이션이다.

### @Test
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {

}
```

- @Retention(RetentionPolicy.RUNTIME) : @Test가 런타임에도 유지되어야 한다는 표시
- @Target(ElementType.METHOD) : @Test가 반드시 메서드 선언에서만 사용돼야 한다는 표시

위에서 선언한 @Test 애너테이션을 사용한 예이다. 
단순히 void 메서드이며 해당 메서드가 실행되는지 안되는지에 대해 테스트를 할 것이다.

```java
public class Sample {
	@Test
    public static void m1() { }        // 성공해야 한다.
    public static void m2() { }
    @Test public static void m3() {    // 실패해야 한다.
        throw new RuntimeException("실패");
    }
    public static void m4() { }  // 테스트가 아니다.
    @Test public void m5() { }   // 잘못 사용한 예: 정적 메서드가 아니다.
    public static void m6() { }
    @Test public static void m7() {    // 실패해야 한다.
        throw new RuntimeException("실패");
    }
    public static void m8() { }
}
```

이제 @Test 애너테이션이 붙은 메서드에 대해 테스트를 체크하는 프로그램을 만들어보자.

```java
public class RunTests {

	public static void main(String[] args) throws Exception {
		int tests = 0;
		int passed = 0;

		Class<?> testClass = Class.forName("effective.java.chapter6.item39.Sample"); // 클래스 리터럴
		for(Method m : testClass.getDeclaredMethods()) {
			if(m.isAnnotationPresent(Test.class)) { // 선언한 Test 클래스를 지정
				tests ++;
				try {
					m.invoke(null); // 파라미터 없는 메서드를 호출하게 된다.
					passed++;
				} catch(InvocationTargetException wrappedExc) {
					Throwable exc = wrappedExc.getCause();
					System.out.println(m + "실패: " + exc);
				} catch(Exception exc) {
					System.out.println("잘못 사용한 @Test: " + m);
				}
			}
		}

        System.out.printf("성공: %d, 실패: %d%n", passed, tests - passed);
	}

}
```

테스트 메서드들이 있는 Sample.class를 클래스 리터럴로 가져오고 isAnnotationPresent 메서드로 @Test 애너테이션이 붙어있는지 확인한다. 붙어있다면 해당 메서드를 invoke 하여 메서드가 정상 수행되는 지 확인한다.


이번에는 특정 예외를 던져야만 성공하는 테스트를 지원하도록 해보자.


### ExceptionTest

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {
	Class<? extends Throwable> value();
}
```

@Test 와 달리, 이번엔 value() 값을 포함하는 애너테이션을 만들었다. 이 애너테이션의 매개변수 타입은 Class<? extends Throwable> 인데, 모든 예외 타입을 수용한다는 뜻이다.


그리고, 이번에는 Exception을 잡아서, 애너테이션에 붙은 Exception과 같은지 확인하고 passed를 체크하는 RunTests를 만들어보자.

```java
public class Sample2 {
	@ExceptionTest(ArithmeticException.class)
    public static void m1() {  // 성공해야 한다.
		int i = 0;
		i = i/i;
	}
	@ExceptionTest(ArithmeticException.class)
    public static void m2() {  // 실패해야 한다. (다른 예외 발생)
		int[] a = new int[0];
		int i = a[1];
	}
	@ExceptionTest(ArithmeticException.class)
    public static void m3() {    // 실패해야 한다. (에러가 발생하지 않음)
    }
}

public static void main(String[] args) throws Exception {
	int tests = 0;
    int passed = 0;
    Class<?> testClass = Class.forName("effective.java.chapter6.item39.Sample2"); // 클래스 리터럴
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
                  System.out.printf("테스트 %s 실패 : 기대한 예외 %s, 발생한 예외 %s%n", m, excType.getName(), exc);
                }

            } catch (Exception exc) {
                System.out.println("잘못 사용한 @Test: " + m);
            }
        }

        System.out.printf("성공: %d, 실패: %d%n", passed, tests - passed);
    }

}
```

지금은 하나의 Exception을 지정해서 테스트를 해봤는데, N개의 예외를 명시하고 그중 하나만 발생해도 성공하게 만들수 있다. value()를 배열 매개변수로 받는것이다.


```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionArrayTest {
	Class<? extends Throwable>[] value();
}
```

이렇게 해주면 애너테이션을 선언한 곳에서 중괄호로 감싸고 쉼표로 구분해주기만 하면 된다. (배열 선언처럼)

```java
@ExceptionTest({IndexOutOfBoundsException.class, NullPointerException.class})
public static void doubleBad() {
  List<String> list = new ArrayList<>();		
  list.addAll(5, null);
}
```

그리고, 이렇게 배열로 들어오는 파라미터들을 처리할 테스트 러너를 수정한다.

```java
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
```

자바 8에서는 이렇게 여러 개의 값을 받는 애너테이션을 다른방식으로도 만들 수 있다. 배열 매개변수를 사용하는 대신 애너테이션에 @Repeatable 메타애너테이션을 다는 방식이다.

- @Repeatable을 단 애너테이션을 반환하는 ‘컨테이너 애너테이션’을 하나 더 정의해야한다.
- ‘컨테이너 애너테이션’은 내부 애너테이션 타입의 배열을 반환하는 value 메서드를 정의해야 한다.


### 컨테이너 애너테이션

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTestContainer {
	ExceptionTest[] value(); // 내부 애터네이션 타입의 배열을 반환해야 한다.
}
```

### @Repeatable 애너테이션

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(ExceptionTestContainer.class) // 컨테이너 애터네이션을 매개변수로 넣어준다.
public @interface ExceptionTest {
    Class<? extends Throwable>[] value();
}
```


그리고 이제 @Repeatable 애너테이션을 처리한 RunTests 메서드를 수행해보자. 위에서는 항상 isAnnotationPresent 메서드로 해당 메소드 위에 애너테이션이 정의되어 있는지 확인했다. 하지만 @Repeatable 을 썼기 때문에, 해당 메서드는 컨테이너 애너테이션인 ExceptionTestContainer.java와 ExceptionTest.java 를 명확히 구분한다.


- 즉, 하나의 메서드에 N개의 애너테이션을 반복적으로 사용한 경우
	- isAnnotationPresent 으로 반복가능 애너테이션이 달렸는지 검사하면 false (컨테이너가 달렸기 때문에)
- 1개의 애너테이션만 정의한 경우
	- isAnnotationPresent 으로 컨테이너 애너테이션이 달렸는지 검사하면 false
	
그래서 두 케이스 모두 아우르려면 둘을 따로따로 확인해야 한다.

```java
if(m.isAnnotationPresent(ExceptionTest.class) || m.isAnnotationPresent(ExceptionTestContainer.class)) {
  ...
}
```

그리고 @Repeatable 애너테이션을 여러개 달면, 하나만 달았을 때와 구분하기 위해 해당 ‘컨테이너’ 애너테이션 타입이 적용된다. 지금까지는 getAnnotation 메서드로, Test메서드에 달린 어노테이션의 값을 가져왔는데, @Repeatable 애너테이션이 달렸다면, 그 메서드에 달린 다른 값들을 찾기 위해 컨테이너 애터네이션을 “looking through”해야한다. 그건 getAnnotaionsByType 으로 구현할 수 있다.



즉, 여러개의 Exception 이나, 파라미터로 받은 class를 테스트할 경우, value() 에 파라미터 메서드를 받거나, 위와 같이 @Repeatable 애너테이션을 사용할 수 있다.