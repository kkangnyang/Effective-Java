# ITEM52. 다중정의는 신중히 사용하라

다중정의(Overloading)는 어느 메서드가 호출될 지 컴파일 타임에 정해진다.

```java
public class CollectionClassifier {
    public static String classify(Set<?> s) {
        return "집합";
    }

    public static String classify(List<?> lst) {
        return "리스트";
    }

    public static String classify(Collection<?> c) {
        return "그 외";
    }

    public static void main(String[] args) {
        Collection<?>[] collections = {
                new HashSet<String>(),
                new ArrayList<BigInteger>(),
                new HashMap<String, String>().values()
        };

        for (Collection<?> c : collections)
            System.out.println(classify(c));
    }
}

// 출력 :
// 그 외
// 그 외
// 그 외
```

위의 코드를 수행해서 기대한 것은 “집합, 리스트, 그 외” 지만 “그 외”만 세 번 출력된다. classify 메서드에 전달된 파라미터가 `Collection<?>` 타입이기 때문이다. 런타임시에는 타입이 매번 달라지지만 호출할 메서드를 선택하는 데는 영향을 주지 못한다.


반면, 재정의(Overriding)을 사용한 아래 코드를 보자


```java
static class Wine {
    String name() { return "포도주"; }
}

static class SparklingWine extends Wine {
    @Override String name() { return "발포성 포도주"; }
}

static class Champagne extends SparklingWine {
    @Override String name() { return "샴페인"; }
}

public static void main(String[] args) {
    List<Wine> wineList = List.of(
            new Wine(), new SparklingWine(), new Champagne());

    for (Wine wine : wineList)
        System.out.println(wine.name());
}

// 포도주
// 발포성 포도주
// 샴페인
```

for 문에서의 컴파일타임 타입이 모두 Wine인 것에 무관하게 항상 “가장 하위에서 정의한” 재정의 메서드가 선택되는 것이다.

한편, 앞서 살펴본 `CollectionClassifier` 를 제대로 구현하려면, instanceof로 명시적으로 검사하면 말끔히 해결된다.

```java
public static String classify(Collection<?> c) {
    return c instanceof Set  ? "집합" :
            c instanceof List ? "리스트" : "그 외";
}

public static void main(String[] args) {
    Collection<?>[] collections = {
            new HashSet<String>(),
            new ArrayList<BigInteger>(),
            new HashMap<String, String>().values()
    };

    for (Collection<?> c : collections)
        System.out.println(classify(c));
}
```

## 다중정의가 혼동을 일으키는 상황을 피해야 한다.

안전하고 보수적으로 가려면 매개변수 수가 같은 다중정의는 만들지 말자. 다중정의하는 대신 메서드 이름을 다르게 지어주는게 좋다. (ObjectOutputStream의 writeBoolean, writeInt 처럼)


매개변수 수가 동일한 다중정의 메서드가 많더라도, 명확히 구분만 된다면 헷갈릴 일은 없다. 즉, 매개변수 타입이 서로 형변환이 될수 없는 타입이라면 명확히 구분될 것이다.


List 인터페이스가 remove(Object)와 remove(int) 를 다중정의 했는데, 그 영향을 아래 예제로 확인할 수 있다.

```java
public static void main(String[] args) {
	Set<Integer> set = new TreeSet<>();
    List<Integer> list = new ArrayList<>();

    for (int i = -3; i < 3; i++) {
        set.add(i);
        list.add(i);
    }
    for (int i = 0; i < 3; i++) {
        set.remove(i);
        list.remove(i);
    }
    System.out.println(set + " " + list);
    
    // [-3, -2, -1] [-2, 0, 2]
}
```

또한, 자바8에서 도입한 람다와 메서드 참조 역시 다중정의 시에 혼란을 키웠다.

```java
// 1번
new Thread(System.out::println).start();
// 2번
ExecutorService exec = Executors.newCachedThreadPool();
exec.submit(System.out::println);
```

2번 케이스는 에러가 난다. 참조된 메서드 (println)과 호출한 메서드(submit) 양쪽 모두 다중정의되어있기 때문이다. 따라서 메서드를 다중정의할 때, 서로 다른 함수형 인터페이스라도 **같은 위치의 인수로 받아서는 안된다.**

어떤 다중정의 메서드가 불리는지 몰라도 기능이 똑같다면 신경쓰지 않아도 된다. 예를 들어, String의 contentEquals(StringBuffer) 메서드, contentEquals(CharSequence) 메서드의 기능은 동일하다!

이럴 경우 보통 상대적으로 더 특수한 다중정의 메서드에서 덜 특수한(더 일반적인) 다중정의 메서드로 일을 넘겨버리는 것이다.

```java
public boolean contentEquals(StringBuffer sb) {
  return contentEquals((CharSequence) sb);
}
```

물론 예외도 있다. String.valueOf(char[]) 와 String.valueOf(Object)는 같은 객체를 건네더라도 전혀 다른일을 수행한다.