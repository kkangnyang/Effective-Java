package effective.java.chapter5.item26;

import java.util.HashSet;
import java.util.Set;

public class WildCardType {
	
	private static int numElementsInCommon(Set s1, Set s2) {
		int result = 0;
		for (Object o1 : s1) {
			if (s2.contains(o1)) {
				result++;
			}
		}
		return result;
	}
	
	private static int numElementsByWildCardInCommon(Set<?> s1, Set<?> s2) {
		int result = 0;
		//s1.add("Not Inter String");
		return result;
	}

	public static void main(String[] args) {
		Set<Integer> set1 = new HashSet<>();
		set1.add(Integer.valueOf(10));
		Set<Integer> set2 = new HashSet<>();
		set2.add(Integer.valueOf(25));
		
		System.out.println(numElementsByWildCardInCommon(set1, set2));

	}

}
