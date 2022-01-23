package effective.java.chapter7.item42;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Item42Main {

	public static void main(String[] args) {
		List<String> words = List.of("ga", "young", "kimgayoung");
		
		Collections.sort(words, new Comparator<String>() {
		    public int compare(String s1, String s2) {
		      return Integer.compare(s1.length(), s2.length());
		    }
		});
		
		Collections.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
		
		Collections.sort(words, Comparator.comparingInt(String::length));
	}

}
