package effective.java.chapter5.item32;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Item32 {

	public static void main(String[] args) {
		
		/*
		List<String> stringLists = List.of("42");
		dangerous(stringLists);
		*/
		
		String[] attributes = pickTwo("좋은", "빠른", "저렴한");
		//List<String> attributes_2 = pickTwo2("좋은", "빠른", "저렴한");
	}
	
	public static void example(String... args) {
	    //....
	}
	
	static void dangerous(List<String>... stringLists) {
        List<Integer> integerList = List.of(42); 
        Object[] objects = stringLists;
        objects[0] = integerList;   // 힙 오염 발생
        String s = stringLists[0].get(0);   // ClassCastException
    }
	
	static <T> T[] toArray(T... args) {
	    return args;
	}
	
	static <T> T[] pickTwo(T a, T b, T c) {
	    switch (ThreadLocalRandom.current().nextInt(3)) {
	        case 0: return toArray(a, b);
	        case 1: return toArray(a, c);
	        case 2: return toArray(b, c);
	    }
	    throw new AssertionError(); // 도달할 수 없다.
	}
	
	static <T> List<T> pickTwo2(T a, T b, T c) {
        switch (ThreadLocalRandom.current().nextInt(3)) {
            case 0: return List.of(a, b);
            case 1: return List.of(a, c);
            case 2: return List.of(b, c);
        }
        throw new AssertionError(); // 도달할 수 없다.
    }
	
	@SafeVarargs
	static <T> List<T> flatten(List<? extends T>... lists) {
	    List<T> result = new ArrayList<>();
	    for (List<? extends T> list : lists) {
	        result.addAll(list);
	    }
	    return result;
	}
	
	static <T> List<T> flatten2(List<List<? extends T>> lists) {
	    List<T> result = new ArrayList<>();
	    for (List<? extends T> list : lists) {
	        result.addAll(list);
	    }
	    return result;
	}

}
