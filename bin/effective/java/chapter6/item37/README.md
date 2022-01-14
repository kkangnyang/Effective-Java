# ITEM37. ordinal �ε��� ��� EnumMap�� ����϶�

LifeCycle �̶�� enum Type�� ������ Plant Ŭ������ �ִ�. 
Plant�� �迭�� �ִ� ��Ȳ����, LifeCycle �������� Map�� ������.


```java
public class Plant {
	
	enum LifeCycle { ANNUAL, PERENNIAL, BIENNIAL }
	
	final String name;
	final LifeCycle lifeCycle;
	
	Plant(String name, LifeCycle lifeCycle) {
		this.name = name;
		this.lifeCycle = lifeCycle;
	}
	
	@Override
	public String toString() {
		return name;
	}
}
```

## ordinal ���
������ 35���� �˾ƺõ��� enum type�� ����� ��ġ�� ��ȯ�ϴ� ordinal �޼��带 ������ �ִ�. 
�� ���� �̿��ؼ� �迭�� �ε����� ����ؼ� Map�� ���� �� �ִ�.


```java
Plant[] garden = {
		        new Plant("����",    LifeCycle.ANNUAL),
		        new Plant("ĳ������", LifeCycle.BIENNIAL),
		        new Plant("��",      LifeCycle.ANNUAL),
		        new Plant("�󺥴�",   LifeCycle.PERENNIAL),
		        new Plant("�Ľ���",   LifeCycle.BIENNIAL),
		        new Plant("�����", LifeCycle.PERENNIAL)
		};
		
// Plant�� LifeCycle enum ��ŭ Set<> �迭�� �����.
Set<Plant>[] plantsByLifeCycle = (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];

for(int i=0; i<plantsByLifeCycle.length; i++) {
	plantsByLifeCycle[i] = new HashSet<>();
}

for(Plant p : garden) {
	// Plant�� LifeCycle�� �����ͼ� �ش� LifeCycle�� ��ġ�� �ε����� Ȱ���Ѵ�.
	plantsByLifeCycle[p.lifeCycle.ordinal()].add(p);
}
```


�� ����� ��������, ���׸� �迭�� ����߱� ������ ��˻� ����ȯ�� �����ؾ� �Ѵ�. �ȱ׷��� �Ʒ��� ���� warning�� ���.

> Type safety: Unchecked cast from Set[] to Set[]

�� �ɰ��� ������ ordinal()�޼��忡�� ��ȯ�Ǵ� ������ ��Ȯ�� �������� ����Ѵٴ� ���� �����ؾ� �Ѵٴ� ���̴�.


## EnumMap ���

EnumMap�� ª�� ����ϰ� �����ϰ� enum Ÿ���� ���� ���� �� �ִ�.

```java
Plant[] garden = {
        new Plant("����",    LifeCycle.ANNUAL),
        new Plant("ĳ������", LifeCycle.BIENNIAL),
        new Plant("��",      LifeCycle.ANNUAL),
        new Plant("�󺥴�",   LifeCycle.PERENNIAL),
        new Plant("�Ľ���",   LifeCycle.BIENNIAL),
        new Plant("�����", LifeCycle.PERENNIAL)
};

// �迭�� ����ϱ� ������ �迭�� ������ ������ �ִ�.
Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(Plant.LifeCycle.class); // ������ Ÿ�� ��ū

// �� ���� value (HashSet)�� ���� �ʱ�ȭ ���ش�.
for (Plant.LifeCycle lc : Plant.LifeCycle.values() ) {
  plantsByLifeCycle.put(lc, new HashSet<>());
}

for(Plant p : garden) {
  plantsByLifeCycle.get(p.lifeCycle).add(p);
}
```


EnumMap�� �����ڰ� �޴� �Ķ���ʹ� ���� Key�� Type�̰� �� Class ��ü�� ������ Ÿ�� ��ū���� ��Ÿ�� ���׷� Ÿ�� ������ �����Ѵ�. ���� Type �������� ������ �� �ִ�.


## ��Ʈ�� Ȱ��

```java
System.out.println(Arrays.stream(garden)
                         .collect(Collectors.groupingBy(p -> p.lifeCycle)));

Map<Object,List<Plant>> test = Arrays.stream(garden)
									 .collect(Collectors.groupingBy(p -> p.lifeCycle));
```

���� ���� ��Ʈ���� ����ؼ�, LifeCycle �� Ű�� ������ �ִ� ���� ���� ���� �ִ�. ������ ��ȯ Ÿ���� Ȯ���غ��� Map<Object, List<Plant>>�� Key�� Object�̰�, Value�� List�̴�. �� �ʿ��� ���� ���� �� Object�� �������Ѵٴ°� �����̹Ƿ�, �� ��Ʈ���� ���� �� �����غ���.

```java
System.out.println(Arrays.stream(garden)
			             .collect(Collectors.groupingBy(p -> p.lifeCycle,
			                            () -> new EnumMap<>(LifeCycle.class),
			                            Collectors.toSet())));

EnumMap<Plant.LifeCycle, Set<Plant>> test2 = Arrays.stream(garden)
												   .collect(Collectors.groupingBy(p -> p.lifeCycle,
													            () -> new EnumMap<>(LifeCycle.class),
													            Collectors.toSet()));
```

Collectors.groupingBy�� mapFactory �Ű������� ���ϴ� �� ����ü�� ����� ȣ���� �� �ִ�.

> Collector<T, ?, M> groupingBy(Function<? super T, ? extends K> classifier, Supplier mapFactory, Collector<? super T, A, D> downstream)

�׷��� return Type�� EnumMap<Plant.LifeCycle, Set> �� �ȴ�.


## �� ���� Ÿ�� ������ �����ϴ� ���

�� �� �̻��� ���� ����� �����ϰ��� �ϴ� ��쿡�� EnumMap�� ��ø�ؼ� ����ϸ� ���� ������ �� �ִ�.

```java
public enum Phase {
    SOLID, LIQUID, GAS;
    public enum Transition {
        MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);

        private final Phase from; // �ʵ�1
        private final Phase to; // �ʵ� 2
        Transition(Phase from, Phase to) {
            this.from = from;
            this.to = to;
        }

        // ������ ���� �ʱ�ȭ�Ѵ�.
        private static final Map<Phase, Map<Phase, Transition>>
                m = Stream.of(values()).collect(groupingBy(t -> t.from,
                () -> new EnumMap<>(Phase.class),
                toMap(t -> t.to, t -> t,
                        (x, y) -> y, () -> new EnumMap<>(Phase.class))));

        public static Transition from(Phase from, Phase to) {
            return m.get(from).get(to);
        }
    }
}
```

���⼭ ������ ���� ��� �����ߴ��� �����ϸ� �ȴ�.

- ù��° Collector������ ����(from) ���¸� �������� ���´�.
	- SOLID, LIQUID, GAS
- �ι�° Collector������ ����(to) ���¸� ������ ���� ��(MELT, SUMLIME)�� �����Ѵ�.
	- (SOLID)-LIQUID=MELT, (SOLID)-GAS=SUBLIME


������ �� m�� ���

```
{SOLID={LIQUID=MELT, GAS=SUBLIME}, LIQUID={SOLID=FREEZE, GAS=BOIL}, GAS={SOLID=DEPOSIT, LIQUID=CONDENSE}}
```

�� ������ Collector.toMap �޼��带 �� �˾ƾ� ���ذ� �� �� ����.