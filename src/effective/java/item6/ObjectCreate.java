package effective.java.item6;

public class ObjectCreate {
	
	public static void main(String[] args) {
		
		// item6. ���ʿ��� ��ü ������ ���϶�
		
		// String�� new�� �����ϸ� �׻� ���ο� ��ü�� ����� �ȴ�.
		String a = new String("123");
		String b = new String("123");
		
		// ���ο� �ν��Ͻ��� ����� ��� �ϳ��� String �ν��Ͻ��� ���� �Ѵ�.
		String c = "456";
		String d = "456";
		
		if (a == b) {
			System.out.println("a, b �ּҰ� ��ġ");
		} else {
			System.out.println("a, b �ּҰ� ����ġ");
		}
		
		if (c == d) {
			System.out.println("c, d �ּҰ� ��ġ");
		} else {
			System.out.println("c, d �ּҰ� ����ġ");
		}
		
		
		/** ����) String Constant pool�� �ö��̿���Ʈ ����: ���� ������ String ��ü�� ����ȴٸ� ������ ��ü�� �����ϰ� �Ѵ�. */
	}
	
}
