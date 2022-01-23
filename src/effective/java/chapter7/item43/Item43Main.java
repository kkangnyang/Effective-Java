package effective.java.chapter7.item43;

import java.util.HashMap;
import java.util.Map;

public class Item43Main {
	
	public static void main(String[] args) {
		Map<Integer, Integer> map = new HashMap<>();
		map.merge(1, 1, (oldVal, newVal) -> oldVal + newVal);
		map.merge(1, 1, (oldVal, newVal) -> oldVal + newVal);
		map.merge(1, 1, (oldVal, newVal) -> oldVal + newVal);

		map.merge(2, 1, (oldVal, newVal) -> oldVal + newVal);

		System.out.println(map.toString()); // {1=3, 2=1}
	}
	
}
