package effective.java.chapter5.item26;

import java.util.HashMap;
import java.util.Map;

public class SimpleTypeSafeMap {
	
	private Map<Class<?>, Object> map = new HashMap<>();

    public <T> void put(Class<T> k, T v) {
        map.put(k, v);
    }

    public <T> T get(Class<T> k) {
        return k.cast(map.get(k));
    }
	
	public static void main(String[] args) {
		SimpleTypeSafeMap simpleTypeSafeMap = new SimpleTypeSafeMap();

		simpleTypeSafeMap.put(String.class, "abcde");
		simpleTypeSafeMap.put(Integer.class, 123);

		String v1 = simpleTypeSafeMap.get(String.class);
		Integer v2 = simpleTypeSafeMap.get(Integer.class);

		// 아래와 같은 List<String>.class라는 클래스 리터럴은 언어에서 지원해주지 않으므로 사용 불가!!
		//simpleTypeSafeMap.put(List<String>.class, Arrays.asList("a", "b", "c"));
	}

}
