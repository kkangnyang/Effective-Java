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
		        new Plant("����",    LifeCycle.ANNUAL),
		        new Plant("ĳ������", LifeCycle.BIENNIAL),
		        new Plant("��",      LifeCycle.ANNUAL),
		        new Plant("�󺥴�",   LifeCycle.PERENNIAL),
		        new Plant("�Ľ���",   LifeCycle.BIENNIAL),
		        new Plant("�����", LifeCycle.PERENNIAL)
		};
		
		ordinalTest(garden);
		enumMapTest(garden);
		streamTest(garden);
		streamEnumMapTest(garden);
	}
	
	static void ordinalTest(Plant[] garden) {
		
		// Plant�� LifeCycle enum ��ŭ Set<> �迭�� �����.
		Set<Plant>[] plantsByLifeCycle = (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];

		for(int i=0; i<plantsByLifeCycle.length; i++) {
			plantsByLifeCycle[i] = new HashSet<>();
		}

		for(Plant p : garden) {
			// Plant�� LifeCycle�� �����ͼ� �ش� LifeCycle�� ��ġ�� �ε����� Ȱ���Ѵ�.
			plantsByLifeCycle[p.lifeCycle.ordinal()].add(p);
		}
	}
	
	static void enumMapTest(Plant[] garden) {
		
		// �迭�� ����ϱ� ������ �迭�� ������ ������ �ִ�.
		Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(Plant.LifeCycle.class); // ������ Ÿ�� ��ū
		
		// �� ���� value (HashSet)�� ���� �ʱ�ȭ ���ش�.
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
