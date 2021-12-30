package effective.java.chapter5.item28;

import java.util.Collection;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Chooser {
	private final Object[] choiceArray;
	
	public Chooser(Collection choices) { // 로타입을 썼다.
		choiceArray = choices.toArray();
	}
	
	public Object choose() {
		Random rnd = ThreadLocalRandom.current();
		return choiceArray[rnd.nextInt(choiceArray.length)]; // 여기서 형변환 오류가 날 수 있다.
	}

}
