package effective.java.item6;

public class StaticFactoryMethod {

	public static void main(String[] args) {
		
		// ������ ��� ���� ���丮 �޼��带 �����ϴ� �Һ� Ŭ���������� ���ʿ��� ��ü ������ ���� �� �ִ�.
		// �����ڴ� ȣ���� ������ ���ο� ��ü�� ��������, ���͸� �޼���� �׷��� �ʴ�.
		
		Boolean true1 = Boolean.valueOf("true");
		Boolean true2 = Boolean.valueOf("true");
		
		System.out.println(true1 == true2);
	}

}
