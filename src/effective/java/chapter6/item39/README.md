# ITEM39. ��� ���Ϻ��� �ֳ����̼��� ����϶�

JUnit ���� 3���� �׽�Ʈ �޼��� �̸��� test�� �����ϰԲ� �ߴ�. ���⿡�� ������ �Ʒ��� ���� ������ �־���.

- �Ǽ��� ��Ÿ�� ���� test�� �ƴ� tste�� �׽�Ʈ �޼��带 ��� ���� ���, JUnit3�� �� �޼��带 �����Ѵ�.
- �����ڰ� �޼��尡 �ƴ� Ŭ���� �̸��� Test�� ���̰� JUnit�� �����ߴٰ� ��������. �� �����ڴ� �ڱⰡ ���� Ŭ���� �ȿ� �ִ� �޼��带 JUnit3�� �׽�Ʈ���ٰŶ� ���������, JUnit3�� Ŭ���� �̸����� ���ɾ���.
- Ư�� �ൿ�� �׽�Ʈ�ϵ��� ������� �Ķ���ͷ� �μ��� �����ؾ��ϴµ�, ���δ�.

�׷��� JUnit4 ���ʹ� ������̼��� �����߰�, �̹� é�Ϳ����� �̷��� ���� ������ �ϴ� ������̼ǿ� ���ؼ� �˾ƺ���.

## ��Ŀ �ֳ����̼�
marker �ֳ����̼��̶� �ƹ� �Ű����� ���� �ܼ��� ��� ��ŷ�ϴ� �뵵�� ���̴� �ֳ����̼��̴�.

### @Test
```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Test {

}
```

- @Retention(RetentionPolicy.RUNTIME) : @Test�� ��Ÿ�ӿ��� �����Ǿ�� �Ѵٴ� ǥ��
- @Target(ElementType.METHOD) : @Test�� �ݵ�� �޼��� ���𿡼��� ���ž� �Ѵٴ� ǥ��

������ ������ @Test �ֳ����̼��� ����� ���̴�. 
�ܼ��� void �޼����̸� �ش� �޼��尡 ����Ǵ��� �ȵǴ����� ���� �׽�Ʈ�� �� ���̴�.

```java
public class Sample {
	@Test
    public static void m1() { }        // �����ؾ� �Ѵ�.
    public static void m2() { }
    @Test public static void m3() {    // �����ؾ� �Ѵ�.
        throw new RuntimeException("����");
    }
    public static void m4() { }  // �׽�Ʈ�� �ƴϴ�.
    @Test public void m5() { }   // �߸� ����� ��: ���� �޼��尡 �ƴϴ�.
    public static void m6() { }
    @Test public static void m7() {    // �����ؾ� �Ѵ�.
        throw new RuntimeException("����");
    }
    public static void m8() { }
}
```

���� @Test �ֳ����̼��� ���� �޼��忡 ���� �׽�Ʈ�� üũ�ϴ� ���α׷��� ������.

```java
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
```

�׽�Ʈ �޼������ �ִ� Sample.class�� Ŭ���� ���ͷ��� �������� isAnnotationPresent �޼���� @Test �ֳ����̼��� �پ��ִ��� Ȯ���Ѵ�. �پ��ִٸ� �ش� �޼��带 invoke �Ͽ� �޼��尡 ���� ����Ǵ� �� Ȯ���Ѵ�.


�̹����� Ư�� ���ܸ� �����߸� �����ϴ� �׽�Ʈ�� �����ϵ��� �غ���.


### ExceptionTest

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTest {
	Class<? extends Throwable> value();
}
```

@Test �� �޸�, �̹��� value() ���� �����ϴ� �ֳ����̼��� �������. �� �ֳ����̼��� �Ű����� Ÿ���� Class<? extends Throwable> �ε�, ��� ���� Ÿ���� �����Ѵٴ� ���̴�.


�׸���, �̹����� Exception�� ��Ƽ�, �ֳ����̼ǿ� ���� Exception�� ������ Ȯ���ϰ� passed�� üũ�ϴ� RunTests�� ������.

```java
public class Sample2 {
	@ExceptionTest(ArithmeticException.class)
    public static void m1() {  // �����ؾ� �Ѵ�.
		int i = 0;
		i = i/i;
	}
	@ExceptionTest(ArithmeticException.class)
    public static void m2() {  // �����ؾ� �Ѵ�. (�ٸ� ���� �߻�)
		int[] a = new int[0];
		int i = a[1];
	}
	@ExceptionTest(ArithmeticException.class)
    public static void m3() {    // �����ؾ� �Ѵ�. (������ �߻����� ����)
    }
}

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
```

������ �ϳ��� Exception�� �����ؼ� �׽�Ʈ�� �غôµ�, N���� ���ܸ� ����ϰ� ���� �ϳ��� �߻��ص� �����ϰ� ����� �ִ�. value()�� �迭 �Ű������� �޴°��̴�.


```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionArrayTest {
	Class<? extends Throwable>[] value();
}
```

�̷��� ���ָ� �ֳ����̼��� ������ ������ �߰�ȣ�� ���ΰ� ��ǥ�� �������ֱ⸸ �ϸ� �ȴ�. (�迭 ����ó��)

```java
@ExceptionTest({IndexOutOfBoundsException.class, NullPointerException.class})
public static void doubleBad() {
  List<String> list = new ArrayList<>();		
  list.addAll(5, null);
}
```

�׸���, �̷��� �迭�� ������ �Ķ���͵��� ó���� �׽�Ʈ ���ʸ� �����Ѵ�.

```java
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
```

�ڹ� 8������ �̷��� ���� ���� ���� �޴� �ֳ����̼��� �ٸ�������ε� ���� �� �ִ�. �迭 �Ű������� ����ϴ� ��� �ֳ����̼ǿ� @Repeatable ��Ÿ�ֳ����̼��� �ٴ� ����̴�.

- @Repeatable�� �� �ֳ����̼��� ��ȯ�ϴ� �������̳� �ֳ����̼ǡ��� �ϳ� �� �����ؾ��Ѵ�.
- �������̳� �ֳ����̼ǡ��� ���� �ֳ����̼� Ÿ���� �迭�� ��ȯ�ϴ� value �޼��带 �����ؾ� �Ѵ�.


### �����̳� �ֳ����̼�

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface ExceptionTestContainer {
	ExceptionTest[] value(); // ���� ���ͳ��̼� Ÿ���� �迭�� ��ȯ�ؾ� �Ѵ�.
}
```

### @Repeatable �ֳ����̼�

```java
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Repeatable(ExceptionTestContainer.class) // �����̳� ���ͳ��̼��� �Ű������� �־��ش�.
public @interface ExceptionTest {
    Class<? extends Throwable>[] value();
}
```


�׸��� ���� @Repeatable �ֳ����̼��� ó���� RunTests �޼��带 �����غ���. �������� �׻� isAnnotationPresent �޼���� �ش� �޼ҵ� ���� �ֳ����̼��� ���ǵǾ� �ִ��� Ȯ���ߴ�. ������ @Repeatable �� ��� ������, �ش� �޼���� �����̳� �ֳ����̼��� ExceptionTestContainer.java�� ExceptionTest.java �� ��Ȯ�� �����Ѵ�.


- ��, �ϳ��� �޼��忡 N���� �ֳ����̼��� �ݺ������� ����� ���
	- isAnnotationPresent ���� �ݺ����� �ֳ����̼��� �޷ȴ��� �˻��ϸ� false (�����̳ʰ� �޷ȱ� ������)
- 1���� �ֳ����̼Ǹ� ������ ���
	- isAnnotationPresent ���� �����̳� �ֳ����̼��� �޷ȴ��� �˻��ϸ� false
	
�׷��� �� ���̽� ��� �ƿ츣���� ���� ���ε��� Ȯ���ؾ� �Ѵ�.

```java
if(m.isAnnotationPresent(ExceptionTest.class) || m.isAnnotationPresent(ExceptionTestContainer.class)) {
  ...
}
```

�׸��� @Repeatable �ֳ����̼��� ������ �޸�, �ϳ��� �޾��� ���� �����ϱ� ���� �ش� �������̳ʡ� �ֳ����̼� Ÿ���� ����ȴ�. ���ݱ����� getAnnotation �޼����, Test�޼��忡 �޸� ������̼��� ���� �����Դµ�, @Repeatable �ֳ����̼��� �޷ȴٸ�, �� �޼��忡 �޸� �ٸ� ������ ã�� ���� �����̳� ���ͳ��̼��� ��looking through���ؾ��Ѵ�. �װ� getAnnotaionsByType ���� ������ �� �ִ�.



��, �������� Exception �̳�, �Ķ���ͷ� ���� class�� �׽�Ʈ�� ���, value() �� �Ķ���� �޼��带 �ްų�, ���� ���� @Repeatable �ֳ����̼��� ����� �� �ִ�.