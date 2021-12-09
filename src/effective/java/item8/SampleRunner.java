package effective.java.item8;

public class SampleRunner {

	public static void main(String[] args) throws Exception {
		
		SampleResource resource = new SampleResource();
		
		// try-finally : 명시적으로 자원 반남
		try {
			resource.hello(); // 리소스 사용
		} finally {
			resource.close(); // 리소스를 사용하는 쪽에서 쓴 다음 반드시 정리. close() 호출
		}
		
		// try-with-resource : 암묵적으로 자원 반납, 가장 이상적인 자원 반납 방법
		try(SampleResource resource2 = new SampleResource();) {
			resource2.hello();
		}
		
		// AutoCloseable을 구현하면, 명시적으로 close를 호출하지 않아도 try 블록이 끝날 때, close를 호출.
		
		
		
		
		/**
		 * finalizer와 cleaner는 언제 쓸까?
		 * 1. AutoCloseable을 구현하지 않았을 경우를 대비한 "안전망" 역할.
		 *    클라이언트가 하지 않은 자원 회수를 늦게라도 해주는 것이 아예 안 하는 것보다는 나으니 말이다.
		 * 2. 네이티브 피어와 연결된 객체
		 *    네이티브 피어: 일반 자바 객체가 네이티브 메서드를 통해 기능을 위임한 네이티브 객체
	 	 *    자바 객체가 아니니 가비지 컬렉터의 gc 대상이 되지 못한다. 즉시 회수해야 한다면 close 를 사용한다.
		 */
	}

}
