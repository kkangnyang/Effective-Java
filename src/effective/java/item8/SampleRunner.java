package effective.java.item8;

public class SampleRunner {

	public static void main(String[] args) throws Exception {
		
		SampleResource resource = new SampleResource();
		
		// try-finally : ��������� �ڿ� �ݳ�
		try {
			resource.hello(); // ���ҽ� ���
		} finally {
			resource.close(); // ���ҽ��� ����ϴ� �ʿ��� �� ���� �ݵ�� ����. close() ȣ��
		}
		
		// try-with-resource : �Ϲ������� �ڿ� �ݳ�, ���� �̻����� �ڿ� �ݳ� ���
		try(SampleResource resource2 = new SampleResource();) {
			resource2.hello();
		}
		
		// AutoCloseable�� �����ϸ�, ��������� close�� ȣ������ �ʾƵ� try ����� ���� ��, close�� ȣ��.
		
		
		
		
		/**
		 * finalizer�� cleaner�� ���� ����?
		 * 1. AutoCloseable�� �������� �ʾ��� ��츦 ����� "������" ����.
		 *    Ŭ���̾�Ʈ�� ���� ���� �ڿ� ȸ���� �ʰԶ� ���ִ� ���� �ƿ� �� �ϴ� �ͺ��ٴ� ������ ���̴�.
		 * 2. ����Ƽ�� �Ǿ�� ����� ��ü
		 *    ����Ƽ�� �Ǿ�: �Ϲ� �ڹ� ��ü�� ����Ƽ�� �޼��带 ���� ����� ������ ����Ƽ�� ��ü
	 	 *    �ڹ� ��ü�� �ƴϴ� ������ �÷����� gc ����� ���� ���Ѵ�. ��� ȸ���ؾ� �Ѵٸ� close �� ����Ѵ�.
		 */
	}

}
