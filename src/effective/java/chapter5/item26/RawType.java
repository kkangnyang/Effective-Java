package effective.java.chapter5.item26;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class RawType {
	
	static class Stamp {
		public Stamp() {
			
		}
	}
	
	static class Coin {
		public Coin() {
			
		}
	}
	
	private final static Collection stamps = new ArrayList<>();
	
	public static void main(String[] args) {
		stamps.add(new Coin());
		
		for (Iterator iterator = stamps.iterator(); iterator.hasNext();) {
			Stamp stamp = (Stamp) iterator.next(); // ClassCaseException!
			
		}
	}

}
