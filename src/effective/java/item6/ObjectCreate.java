package effective.java.item6;

public class ObjectCreate {
	
	public static void main(String[] args) {
		
		// item6. 불필요한 객체 생성을 피하라
		
		// String을 new로 생성하면 항상 새로운 객체를 만들게 된다.
		String a = new String("123");
		String b = new String("123");
		
		// 새로운 인스턴스를 만드는 대신 하나의 String 인스턴스를 재사용 한다.
		String c = "456";
		String d = "456";
		
		if (a == b) {
			System.out.println("a, b 주소값 일치");
		} else {
			System.out.println("a, b 주소값 불일치");
		}
		
		if (c == d) {
			System.out.println("c, d 주소값 일치");
		} else {
			System.out.println("c, d 주소값 불일치");
		}
		
		
		/** 참조) String Constant pool의 플라이웨이트 패턴: 같은 내용의 String 객체가 선언된다면 기존의 객체를 참조하게 한다. */
	}
	
}
