package effective.java.chapter5.item26;

import java.util.Set;

public class InstanceOfMain {

	public static void main(String[] args) {
		Object o = new Object();
		
		if (o instanceof Set) { // ��Ÿ��
			Set<?> s = (Set<?>) o; // ���ϵ�Ÿ������ ����ȯ
		}
		
		/**
		if (o instanceof Set<String>) { // ���ʸ� -> ���� �߻�!
			Set<?> s = (Set<?>) o; // ���ϵ�Ÿ������ ����ȯ
		}
		*/
	}

}
