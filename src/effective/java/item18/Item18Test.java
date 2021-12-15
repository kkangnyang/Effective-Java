package effective.java.item18;

import java.util.HashSet;
import java.util.List;

public class Item18Test {

	public static void main(String[] args) {
		InstrumentedHashSet<String> s = new InstrumentedHashSet<>();
        s.addAll(List.of("ƽ", "ŹŹ", "��"));
        System.out.println(s.getAddCount());
        
        
        InstrumentedSet<String> s2 = new InstrumentedSet<>(new HashSet<String>());
        s2.addAll(List.of("ƽ", "ŹŹ", "��"));
        System.out.println(s2.getAddCount());
	}

}
