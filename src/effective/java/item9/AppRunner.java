package effective.java.item9;

public class AppRunner {
	
	/**
	  �� ȸ���ؾ� �ϴ� �ڿ��� �ٷ� ���� try-finally ���� try-with-resources�� �������.
	  ���ܴ� ����. �ڵ�� �� ª�� �и�������, ��������� ���� ������ �ξ� �����ϴ�.
      try-finally�� �ۼ��ϸ� �ǿ������� ���� ��ŭ �ڵ尡 ������������ ����, try-with-resources�δ� ��Ȯ�ϰ� ���� �ڿ��� ȸ���� �� �ִ�. 
	 */
	
	public static void main(String[] args) throws Exception {
		
		
		// �� ��° ���ܰ� ù ��° ���ܸ� ������ ������� ������.
		// �׷��� ���� ���� ������ ù ��° ���ܿ� ���� ������ ���� �ʰ� �Ǿ�, ���� �ý��ۿ����� ������� ��ư� �Ѵ�.
		MyResource myResource = new MyResource();
		try {
			myResource.doSomething();
		} finally {
			myResource.close();
		}
		
		
		// try-with-resources�� ��ġ�� close�� �˾Ƽ� ȣ��
		// close ȣ�⿡�� ���ܰ� �߻����� ��, close���� �߻��� ���ܴ� �������� ù ��° ���ܰ� ��ϵȴ�.
		// �̷��� ������ ���ܵ��� ���� ���� ������ suppressed ����ǥ�� �ް� ��µȴ�.
		/*
		 * try (MyResource myResource = new MyResource()){ myResource.doSomething(); }
		 */

	}

}
