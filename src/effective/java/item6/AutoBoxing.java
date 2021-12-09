package effective.java.item6;

public class AutoBoxing {

	public static void main(String[] args) {
		
		// ����ڽ��̶�? �⺻Ÿ�԰� �ڽ̵� �⺻Ÿ���� ���� �� �� �ڵ����� ��ȣ ��ȯ���ִ� ���
		
		System.out.println(sum());
		
	}
	
	private static long sum() {
		Long sum = 0L;
		
		for (long i=0; i <= Integer.MAX_VALUE; i++) {
			sum += i;
		}
		
		return sum;
	}
	
	/*
	sum������ long�� �ƴ� Long���� ����ؼ� ���ʿ��� Long�ν��Ͻ��� �� 2�� 31�°��� ���������.
	(long Ÿ���� i�� Long Ÿ���� sum �ν��Ͻ��� ������ ������)
	Long���� ����� ������ long���� �ٲٸ� �ξ� �� ���� ���α׷��� �ȴ�.  
	 */
	
	/** ���! �ڽ̵� �⺻ Ÿ�Ժ��ٴ� �⺻ Ÿ���� ����ϰ�, �ǵ�ġ ���� ����ڽ��� ������� �ʵ��� ��������. */

}
