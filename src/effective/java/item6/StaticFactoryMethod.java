package effective.java.item6;

public class StaticFactoryMethod {

	public static void main(String[] args) {
		
		// 생성자 대신 정적 팩토리 메서드를 제공하는 불변 클래스에서는 불필요한 객체 생성을 피할 수 있다.
		// 생성자는 호출할 때마다 새로운 객체를 만들지만, 팩터리 메서드는 그렇지 않다.
		
		Boolean true1 = Boolean.valueOf("true");
		Boolean true2 = Boolean.valueOf("true");
		
		System.out.println(true1 == true2);
	}

}
