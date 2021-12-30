package effective.java.chapter5.item28;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class GenericChooser<T> {
	//private final T[] choiceArray;
	private final List<T> choiceList;
	
	public GenericChooser(Collection<T> choices) {
		//choiceArray = choices.toArray(); // compile error
		choiceList = new ArrayList<>(choices);
	}
	
	public Object choose() {
		Random rnd = ThreadLocalRandom.current();
		//return choiceArray[rnd.nextInt(choiceArray.length)]; // 여기서 형변환 오류가 날 수 있다.
		return choiceList.get(rnd.nextInt(choiceList.size()));
	}
}
