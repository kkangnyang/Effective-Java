package effective.java.chapter5.item26;

import java.util.Set;

public class InstanceOfMain {

	public static void main(String[] args) {
		Object o = new Object();
		
		if (o instanceof Set) { // 로타입
			Set<?> s = (Set<?>) o; // 와일드타입으로 형변환
		}
		
		/**
		if (o instanceof Set<String>) { // 제너릭 -> 오류 발생!
			Set<?> s = (Set<?>) o; // 와일드타입으로 형변환
		}
		*/
	}

}
