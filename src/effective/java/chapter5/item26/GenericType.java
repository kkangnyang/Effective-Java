package effective.java.chapter5.item26;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

import effective.java.chapter5.item26.RawType.Coin;
import effective.java.chapter5.item26.RawType.Stamp;

public class GenericType {
	
	static class Stamp {
		public Stamp() {
			
		}
	}
	
	static class Coin {
		public Coin() {
			
		}
	}
	
	private final static Collection<Stamp> stampsGeneric = new ArrayList<>();

	public static void main(String[] args) {
		//stampsGeneric.add(new Coin()); // compile error
	}

}
