# ITEM37. ordinal 인덱싱 대신 EnumMap을 사용하라

LifeCycle 이라는 enum Type을 가지는 Plant 클래스가 있다. 
Plant의 배열이 있는 상황에서, LifeCycle 기준으로 Map을 만들어보자.


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

## ordinal 사용
아이템 35에서 알아봤듯이 enum type은 상수의 위치를 반환하는 ordinal 메서드를 가지고 있다. 
이 값을 이용해서 배열의 인덱스로 사용해서 Map을 만들 수 있다.


```java
Plant[] garden = {
		        new Plant("바질",    LifeCycle.ANNUAL),
		        new Plant("캐러웨이", LifeCycle.BIENNIAL),
		        new Plant("딜",      LifeCycle.ANNUAL),
		        new Plant("라벤더",   LifeCycle.PERENNIAL),
		        new Plant("파슬리",   LifeCycle.BIENNIAL),
		        new Plant("로즈마리", LifeCycle.PERENNIAL)
		};
		
// Plant의 LifeCycle enum 만큼 Set<> 배열을 만든다.
Set<Plant>[] plantsByLifeCycle = (Set<Plant>[]) new Set[Plant.LifeCycle.values().length];

for(int i=0; i<plantsByLifeCycle.length; i++) {
	plantsByLifeCycle[i] = new HashSet<>();
}

for(Plant p : garden) {
	// Plant의 LifeCycle을 가져와서 해당 LifeCycle의 위치를 인덱스로 활용한다.
	plantsByLifeCycle[p.lifeCycle.ordinal()].add(p);
}
```


이 방법의 문제점은, 제네릭 배열을 사용했기 때문에 비검사 형변환을 수행해야 한다. 안그러면 아래와 같은 warning이 뜬다.

> Type safety: Unchecked cast from Set[] to Set[]

더 심각한 문제는 ordinal()메서드에서 반환되는 정수가 정확한 정수값을 사용한다는 것을 보증해야 한다는 점이다.


## EnumMap 사용

EnumMap은 짧고 명료하고 안전하게 enum 타입의 맵을 만들 수 있다.

```java
Plant[] garden = {
        new Plant("바질",    LifeCycle.ANNUAL),
        new Plant("캐러웨이", LifeCycle.BIENNIAL),
        new Plant("딜",      LifeCycle.ANNUAL),
        new Plant("라벤더",   LifeCycle.PERENNIAL),
        new Plant("파슬리",   LifeCycle.BIENNIAL),
        new Plant("로즈마리", LifeCycle.PERENNIAL)
};

// 배열을 사용하기 때문에 배열의 성능을 가지고 있다.
Map<Plant.LifeCycle, Set<Plant>> plantsByLifeCycle = new EnumMap<>(Plant.LifeCycle.class); // 한정적 타입 토큰

// 각 맵의 value (HashSet)을 먼저 초기화 해준다.
for (Plant.LifeCycle lc : Plant.LifeCycle.values() ) {
  plantsByLifeCycle.put(lc, new HashSet<>());
}

for(Plant p : garden) {
  plantsByLifeCycle.get(p.lifeCycle).add(p);
}
```


EnumMap의 생성자가 받는 파라미터는 맵의 Key의 Type이고 이 Class 객체는 한정적 타입 토큰으로 런타임 제네렉 타입 정보를 제공한다. 따라서 Type 안정성도 보장할 수 있다.


## 스트림 활용

```java
System.out.println(Arrays.stream(garden)
                         .collect(Collectors.groupingBy(p -> p.lifeCycle)));

Map<Object,List<Plant>> test = Arrays.stream(garden)
									 .collect(Collectors.groupingBy(p -> p.lifeCycle));
```

위와 같이 스트림을 사용해서, LifeCycle 을 키로 가지고 있는 맵을 만들 수도 있다. 하지만 반환 타입을 확인해보면 Map<Object, List<Plant>>로 Key가 Object이고, Value가 List이다. 이 맵에서 값을 꺼낼 때 Object로 꺼내야한다는게 단점이므로, 이 스트림을 조금 더 개선해보자.

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

Collectors.groupingBy의 mapFactory 매개변수에 원하는 맵 구현체를 명시해 호출할 수 있다.

> Collector<T, ?, M> groupingBy(Function<? super T, ? extends K> classifier, Supplier mapFactory, Collector<? super T, A, D> downstream)

그러면 return Type은 EnumMap<Plant.LifeCycle, Set> 가 된다.


## 두 열거 타입 값들을 매핑하는 경우

한 개 이상의 값을 상수에 매핑하고자 하는 경우에는 EnumMap을 중첩해서 사용하면 쉽게 구현할 수 있다.

```java
public enum Phase {
    SOLID, LIQUID, GAS;
    public enum Transition {
        MELT(SOLID, LIQUID), FREEZE(LIQUID, SOLID),
        BOIL(LIQUID, GAS), CONDENSE(GAS, LIQUID),
        SUBLIME(SOLID, GAS), DEPOSIT(GAS, SOLID);

        private final Phase from; // 필드1
        private final Phase to; // 필드 2
        Transition(Phase from, Phase to) {
            this.from = from;
            this.to = to;
        }

        // 상전이 맵을 초기화한다.
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

여기서 상전이 맵을 어떻게 선언했는지 집중하면 된다.

- 첫번째 Collector에서는 이전(from) 상태를 기준으로 묶는다.
	- SOLID, LIQUID, GAS
- 두번째 Collector에서는 이후(to) 상태를 기준을 묶고 값(MELT, SUMLIME)을 저장한다.
	- (SOLID)-LIQUID=MELT, (SOLID)-GAS=SUBLIME


상전이 맵 m의 결과

```
{SOLID={LIQUID=MELT, GAS=SUBLIME}, LIQUID={SOLID=FREEZE, GAS=BOIL}, GAS={SOLID=DEPOSIT, LIQUID=CONDENSE}}
```

위 내용은 Collector.toMap 메서드를 잘 알아야 이해가 갈 것 같다.