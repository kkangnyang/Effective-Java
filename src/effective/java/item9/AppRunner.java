package effective.java.item9;

public class AppRunner {
	
	/**
	  꼭 회수해야 하는 자원을 다룰 때는 try-finally 말고 try-with-resources를 사용하자.
	  예외는 없다. 코드는 더 짧고 분명해지고, 만들어지는 예외 정보도 훨씬 유용하다.
      try-finally로 작성하면 실용적이지 못할 만큼 코드가 지저분해지는 경우라도, try-with-resources로는 정확하고 쉽게 자원을 회수할 수 있다. 
	 */
	
	public static void main(String[] args) throws Exception {
		
		
		// 두 번째 예외가 첫 번째 예외를 완전히 집어삼켜 버린다.
		// 그러면 스택 추적 내역에 첫 번째 예외에 관한 정보는 남지 않게 되어, 실제 시스템에서의 디버깅을 어렵게 한다.
		MyResource myResource = new MyResource();
		try {
			myResource.doSomething();
		} finally {
			myResource.close();
		}
		
		
		// try-with-resources로 고치면 close를 알아서 호출
		// close 호출에서 예외가 발생했을 때, close에서 발생한 예외는 숨겨지고 첫 번째 예외가 기록된다.
		// 이렇게 숨겨진 예외들은 스택 추적 내역에 suppressed 꼬리표를 달고 출력된다.
		/*
		 * try (MyResource myResource = new MyResource()){ myResource.doSomething(); }
		 */

	}

}
