package effective.java.chapter6.item37;

import java.util.Arrays;
import java.util.EnumMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import effective.java.chapter6.item37.Plant.LifeCycle;

public class Item37Main {

	public static void main(String[] args) {
		Plant[] garden = {
		        new Plant("바질",    LifeCycle.ANNUAL),
		        new Plant("캐러웨이", LifeCycle.BIENNIAL),
		        new Plant("딜",      LifeCycle.ANNUAL),
		        new Plant("라벤더",   LifeCycle.PERENNIAL),
		        new Plant("파슬리",   LifeCycle.BIENNIAL),
		        new Plant("로즈마리", LifeCycle.PERENNIAL)
		};
		
		ordinalTest(garden);
		enumMapTest(garden);
		streamTest(garden);
		streamEnumMapTest(garden);
	}
	
	static void ordinalTest(Plant[] garden) {
		
		// Plant의 LifeCycle enum 만큼 Set<> 배열을 만든다.
		Set<Plant>[] plantsByLifeCycle = (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];

		for(int i=0; i<plantsByLifeCycle.length; i++) {
			plantsByLifeCycle[i] = new HashSet<>();
		}

		for(Plant p : garden) {
			// Plant의 LifeCycle을 가져와서 해당 LifeCycle의 위치를 인덱스로 활용한다.
			plantsByLifeCycle[p.lifeCycle.ordinal()].add(p);
		}
	}
	
	static void enumMapTest(Plant[] garden) {
		
		// 배열을 사용하기 때문에 배열의 성능을 가지고 있다.
		Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(Plant.LifeCycle.class); // 한정적 타입 토큰
		
		// 각 맵의 value (HashSet)을 먼저 초기화 해준다.
		for (Plant.LifeCycle lc : Plant.LifeCycle.values() ) {
		  plantsByLifeCycle.put(lc, new HashSet<>());
		}
		
		for(Plant p : garden) {
		  plantsByLifeCycle.get(p.lifeCycle).add(p);
		}
	}
	
	static void streamTest(Plant[] garden) {
		
		System.out.println(Arrays.stream(garden)
	                             .collect(Collectors.groupingBy(p -> p.lifeCycle)));

		Map<Object,List<Plant>> test = Arrays.stream(garden)
											 .collect(Collectors.groupingBy(p -> p.lifeCycle));
	}
	
	static void streamEnumMapTest(Plant[] garden) {
		
		System.out.println(Arrays.stream(garden)
					             .collect(Collectors.groupingBy(p -> p.lifeCycle,
					                            () -> new EnumMap<>(LifeCycle.class),
					                            Collectors.toSet())));

		EnumMap<Plant.LifeCycle, Set<Plant>> test2 = Arrays.stream(garden)
														   .collect(Collectors.groupingBy(p -> p.lifeCycle,
															            () -> new EnumMap<>(LifeCycle.class),
															            Collectors.toSet()));
	}
	

}
