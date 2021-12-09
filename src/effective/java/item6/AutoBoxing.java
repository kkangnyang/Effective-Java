package effective.java.item6;

public class AutoBoxing {

	public static void main(String[] args) {
		
		// 오토박싱이란? 기본타입과 박싱된 기본타입을 섞어 쓸 때 자동으로 상호 변환해주는 기술
		
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
	sum변수를 long이 아닌 Long으로 사용해서 불필요한 Long인스턴스가 약 2의 31승개나 만들어졌다.
	(long 타입인 i가 Long 타입인 sum 인스턴스에 더해질 때마다)
	Long으로 선언된 변수를 long으로 바꾸면 훨씬 더 빠른 프로그램이 된다.  
	 */
	
	/** 결론! 박싱된 기본 타입보다는 기본 타입을 사용하고, 의도치 않은 오토박싱이 숨어들지 않도록 주의하자. */

}
