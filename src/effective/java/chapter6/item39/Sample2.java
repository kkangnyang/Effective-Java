package effective.java.chapter6.item39;

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
