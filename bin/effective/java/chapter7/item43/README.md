# ITEM43. 람다보다는 메서드 참조를 사용하라.

람다가 익명 클래스보다 나은 점 중에서 가장 큰 특징은 간결함인데, 람다보다 더 간결하게 만드는 방법이 메서드 참조다.

## 메서드 참조란

Map을 하나 만들건데, int 값을 key로 가지고, 해당 값의 빈도수를 value로 저장하는 map을 만들어보자.


Map에 추가된 merge메서드를 사용했다. merge 메서드는 키, 값, 함수를 인수로 받아서 주어진 키가 맵 안에 아직 없다면 {key, value} 쌍을 그대로 저장하고, 이미 있다면, 함수를 현재 값과 주어진 값에 적용한 다음, 그 결과로 현재 값을 덮어쓴다.

```java
Map<Integer, Integer> map = new HashMap<>();
map.merge(1, 1, (oldVal, newVal) -> oldVal + newVal);
map.merge(1, 1, (oldVal, newVal) -> oldVal + newVal);
map.merge(1, 1, (oldVal, newVal) -> oldVal + newVal);

map.merge(2, 1, (oldVal, newVal) -> oldVal + newVal);

System.out.println(map.toString());
// {1=3, 2=1}
```


여기서 oldVal 과 newVal은 크게 하는 일 없이 공간만 차지한다. 이 둘이 하는 기능은 두 수를 저장하는 일이다. Integer 클래스에 정적 메서드 sum을 참조하면 훨씬 간결한 코드를 작성할 수 있다.

```java
map.merge(1, 1, Integer::sum);
```

람다로 구현했을 때 너무 길거나 복잡하다면, 그 내용을 메서드로 만들고 람다 대신 그 메서드 참조를 사용하는 방법도 있다.


때론 람다가 메서드 참조보다 간결할 때가 있다. 주로 메서드와 람다가 같은 클래스에 있을 때 그렇다.

## 메서드 참조의 유형 5가지

|메서드 참조 유형 |예 |같은 기능을 하는 람다 |
|정적 | Integer::parseInt | str -> Integer.parseInt(str) |
|한정적(인스턴스) | Instant.now()::isAfter | Instant then = Instant.now(); t -> then.isAfter() |
|비한정적(인스턴스) | String::toLowerCase | str -> str.toLowerCase() |
|클래스 생성자 |TreeMap<K,V>::new | () -> new TreeMap<K,V>() |
|배열 생성자 |int[]::new |len -> new int[len] |

- 한정적(인스턴스) : 수신 객체(참조 대상 인스턴스)를 특정하는 한정적 인스턴스 메서드 참조
- 비한정적(인스턴스) : 수신객체를 특정하지 않음
