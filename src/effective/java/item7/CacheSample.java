package effective.java.item7;

import java.util.HashMap;
import java.util.Map;
import java.util.WeakHashMap;

public class CacheSample {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public void casheLeak() {
		Object key = new Object();
		Object value = new Object();

		Map<Object, Object> cache = new HashMap<>();
		cache.put(key, value);
		
		// key 사용이 없어지더라도 cache가 key의 레퍼런스를 가지고 있으므로, GC의 대상이 될 수 없다.
	}
	
	public void useWeakHashMap() {
		Object key = new Object();
		Object value = new Object();

		Map<Object, Object> cache = new WeakHashMap<>();
		cache.put(key, value);
		
		// 캐시 외부에서 key를 참조하는 동안만 엔트리가 살아있는 캐시가 필요하다면 WeakHashMap을 이용한다.
		// 다 쓴 엔트리는 그 즉시 자동으로 제거된다. 단, WeakHashMap은 이런 상황에서만 유용하다.
		// 캐시 값이 무의미해진다면 자동으로 처리해주는 WeakHashMap은 key 값을 모두 Weak 레퍼런스로 감싸 hard reference가 없어지면 GC의 대상이 된다.
	}

}
