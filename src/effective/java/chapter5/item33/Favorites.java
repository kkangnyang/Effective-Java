package effective.java.chapter5.item33;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;

public class Favorites {
	
	private Map<Class<?>, Object> favorites = new HashMap<>();
	
	public <T> T getFavorites(Class<T> type) {
		return type.cast(favorites.get(type));
	}

	public <T> void putFavorites(Class<T> type, T instance) {
		//favorites.put(Objects.requireNonNull(type), instance);
		favorites.put(Objects.requireNonNull(type), type.cast(instance));
	}

	public static void main(String[] args) {
		Favorites f = new Favorites();
		
		f.putFavorites(String.class, "Java");
		//f.putFavorites(Integer.class, 0xcafebabe);
		f.putFavorites(Class.class, Favorites.class);
		
		f.putFavorites((Class)Integer.class, "Integer의 인스턴스가 아닙니다.");
		
		String fovoriteString = f.getFavorites(String.class);
		int favoriteInteger = f.getFavorites(Integer.class);
		Class<?> favoriteClass = f.getFavorites(Class.class);
		
		HashSet<Integer> set = new HashSet<>();
		((HashSet)set).add("문자열입니다.");
		
		System.out.printf("%s %x %s%n", fovoriteString, favoriteInteger, favoriteClass.getName());;
	}

}
