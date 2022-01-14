package effective.java.chapter6.item39;

import java.util.ArrayList;
import java.util.List;

public class Sample3 {
	
	@ExceptionArrayTest({IndexOutOfBoundsException.class, NullPointerException.class})
	public static void doubleBad() {	// 성공해야 한다.
		List<String> list = new ArrayList<>();
		list.addAll(5, null);	// NullPointException을 던질 수 있다.
	}
}
