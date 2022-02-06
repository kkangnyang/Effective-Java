package effective.java.chapter7.item46;

import static java.util.Comparator.*;
import static java.util.stream.Collectors.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import effective.java.chapter7.item42.Operation;

public class Item46Main {
	
	//private static final Map<String, Operation> stringToEnum = Stream.of(Operation.values()).collect(toMap(Object::toString), e->e);

	public static void main(String[] args) {
		Map<String, Long> freq = new HashMap<>();
		
		freq.put("test1", (long) 1);
		freq.put("test2", (long) 2);
		freq.put("test3", (long) 3);
		
		List<String> topTen = freq.keySet().stream()
		        .sorted(comparing(freq::get).reversed())
		        .limit(10)
		        .collect(toList());
		
		topTen.forEach(System.out::println);
	}

}
