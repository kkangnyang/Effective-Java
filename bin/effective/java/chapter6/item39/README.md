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


```