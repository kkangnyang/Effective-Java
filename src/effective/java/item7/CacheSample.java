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
		
		// key ����� ���������� cache�� key�� ���۷����� ������ �����Ƿ�, GC�� ����� �� �� ����.
	}
	
	public void useWeakHashMap() {
		Object key = new Object();
		Object value = new Object();

		Map<Object, Object> cache = new WeakHashMap<>();
		cache.put(key, value);
		
		// ĳ�� �ܺο��� key�� �����ϴ� ���ȸ� ��Ʈ���� ����ִ� ĳ�ð� �ʿ��ϴٸ� WeakHashMap�� �̿��Ѵ�.
		// �� �� ��Ʈ���� �� ��� �ڵ����� ���ŵȴ�. ��, WeakHashMap�� �̷� ��Ȳ������ �����ϴ�.
		// ĳ�� ���� ���ǹ������ٸ� �ڵ����� ó�����ִ� WeakHashMap�� key ���� ��� Weak ���۷����� ���� hard reference�� �������� GC�� ����� �ȴ�.
	}

}
