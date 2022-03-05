package effective.java.chapter9.item63;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Item63Main {
	
	static List<Integer> numItems = new ArrayList<>();
	
	public static void main(String[] args) {
		for (int i = 0; i < 10000; i++) {
			numItems.add(i);
		}
		
		long startTime = System.currentTimeMillis();
		System.out.println(statement());
		//System.out.println(statement2());
		
		long finishTime = System.currentTimeMillis();
        long elapsedTime = finishTime - startTime;
        
        System.out.println("startTime(ms) : " + startTime);
        System.out.println("finishTime(ms) : " + finishTime);
        System.out.println("elapsedTime(ms) : " + elapsedTime);
	}
	
	public static String statement() {
		String result = "";
		for (int i = 0; i < numItems.size(); i++)
			result += numItems.get(i);
		return result;
	}
	
	public static String statement2() {
		StringBuilder b = new StringBuilder(numItems.size());
		for (int i = 0; i < numItems.size(); i++)
			b.append(numItems.get(i));
		return b.toString();
	}
	
	public static String statement3() {
		StringBuffer b = new StringBuffer(numItems.size());
		for (int i = 0; i < numItems.size(); i++)
			b.append(numItems.get(i));
		return b.toString();
	}

}
