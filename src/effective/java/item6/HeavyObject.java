package effective.java.item6;

import java.util.regex.Pattern;

public class HeavyObject {
	
	// 재사용 빈도가 높고 생성비용이 비싼 경우 - 캐싱하여 재사용 하자.
	// Pattern인스턴스는 한 번 쓰고 버려저서 곧바로 가비지 컬렉션 대상이 된다. 
	// Pattern은 생성비용이 높은 클래스 중 하나이다. 만약 늘 같은 Pattern이 필요함이 보장되고 재사용 빈도가 높다면 아래와 같이 상수(static final)로 초기에 캐싱해놓고 재사용할 수 있다.
	private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})"
            + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

	public static void main(String[] args) {
		
		// 만드는데 메모리나 시간이 오래 걸리는 객체 즉 "비싼 객체"를 반복적으로 만들어야 한다면,
		// 캐싱해두고 재사용할 수 있는지 고려하는 것이 좋다.
		
	}
	
	static boolean isRomanNumeralSlow(String s) {
		// String.matches는 정규표현식으로 문자열 형태를 확인하는 가장 쉬운 방법이지만, 성능이 중요한 상황에서 반복해 사용하기 적합하지 않다.
		return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
            + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
	}
	
	static boolean isRomanNumeralFast(String s) {
		return ROMAN.matcher(s).matches();
	}
	
	/** 결론! 생성비용이 비싼 객체라면 "캐싱"방식을 고려. 자주쓰는 값이라면 static final로 초기에 캐싱해놓고 재사용 하자! */

}
