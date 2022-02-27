package effective.java.chapter9.item61;

public class Item62Main2 {
	
	static Integer i;
	
	public static void main(String[] args) {
		if(i == 42) { // NullPointerException
		    System.out.println("Unbelivable");
		}
	}

}
