package effective.java.item19;

import java.time.Instant;

public final class Sub extends Super {
	private final Instant instant;
	
	Sub() {
		instant = Instant.now();
	}
	
	@Override
	public void overrideMe() {
		System.out.println(instant);
	}
	
	public static void main(String[] args) {
		Sub sub = new Sub();
		sub.overrideMe();
	}
	
	/**
	 * ��ӿ� Ŭ������ ���� �ϱ�� ���� ����ġ �ʴ�. 
	 * Ŭ���� ���ο��� �����θ� ��� ����ϴ���(�ڱ��� ����) ��� ������ ���ܾ� �ϸ�, �ϴ� ����ȭ�� ���� �� Ŭ������ ���̴� �� �ݵ�� ���Ѿ� �Ѵ�. 
	 * �׷��� ������ �� ���� ���� ����� �ϰ� Ȱ���ϴ� ���� Ŭ������ �������ϰ� ���� �� �ִ�. 
	 * �ٸ� �̰� ȿ�� ���� ���� Ŭ������ ���� �� �ֵ��� �Ϻ� �޼��带 protected�� �����ؾ� �� ���� �ִ�. 
	 * �׷��� Ŭ������ Ȯ���ؾ� �� ��Ȯ�� ������ �������� ������ ����� �����ϴ� ���� ���� ���̴�. 
	 * ����� �����Ϸ��� Ŭ������ final�� �����ϰų� ������ ��θ� �ܺο��� ������ �� ������ ����� �ȴ�.
	 */

}
