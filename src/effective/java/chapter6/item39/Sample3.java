package effective.java.chapter6.item39;

import java.util.ArrayList;
import java.util.List;

public class Sample3 {
	
	@ExceptionArrayTest({IndexOutOfBoundsException.class, NullPointerException.class})
	public static void doubleBad() {	// �����ؾ� �Ѵ�.
		List<String> list = new ArrayList<>();
		list.addAll(5, null);	// NullPointException�� ���� �� �ִ�.
	}
}
