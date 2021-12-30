package effective.java.chapter5.item26;

import java.util.ArrayList;
import java.util.List;

public class GenericTypeMethod {

	private static void unsafeAdd(List<Object> list, Object o) {
		list.add(o);
	}

	public static void main(String[] args) {
		List<String> strings = new ArrayList<>();
		// unsafeAdd(strings, Integer.valueOf(42)); // compile error
		String s = strings.get(0);
	}

}
