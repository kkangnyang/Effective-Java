package effective.java.chapter6.item39;

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
