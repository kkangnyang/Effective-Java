# ITEM54. null이 아닌, 빈 컬렉션이나 배열을 반환하라


다음은 치즈를 얻어올 때 비어있으면 null을 반환하는 코드이다.

```java
public List<Cheese> getCheeses() {
    return cheesesInStock.isEmpty() ? null : new ArrayList<>(cheesesInStock);
}
```

위의 코드처럼 작성하면 클라이언트는 null 상황을 처리하는 코드를 추가로 작성해야한다. 

```java
Shop shop = new Shop();
List<Cheese> cheeses = shop.getCheeses();
if (cheeses != null && cheeses.contains(Cheese.STILTON))
    System.out.println("치즈 획득!");
```


컬렉션이나 배열 같은 컨테이너가 비었을 때 null을 반환하는 메서드를 사용할 때면 항시 이와 같은 방어 코드를 넣어줘야한다. 방어 코드를 빼먹으면 오류가 발생할 수도 있다.


때로는 빈 컨테이너를 할당하는 데도 비용이 드니 null을 반환하는 쪽이 낫다는 주장도 있다. 하지만 이는 두가지 면에서 틀린 주장이다.


- 성능 분석 결과 이 할당이 성능 저하의 주범이라고 확인되지 않는 한 이 정도의 성능 차이는 신경 쓸 수준이 아니다.
-  빈 컬렉션과 배열은 굳이 새로 할당하지 않고도 반환할 수 있다. 다음은 빈 컬렉션을 반환하는 전형적인 코드로, 대부분의 상황에서는 이렇게 하면 된다.

```java
public List<Cheese> getCheeses() {
    return new ArrayList<>(cheesesInStock);
}
```


가능성은 작지만, 사용 패턴에 따라 빈 컬렉션 할당이 성능을 눈에 띄게 떨어뜨릴 수도 있다. 그럴 경우 매번 똑같은 빈 '불변' 컬렉션을 반환하면된다.

```java
public List<Cheese> getCheeses() {
    return cheesesInStock.isEmpty() ? Collections.emptyList() : new ArrayList<>(cheesesInStock);
}
```


배열을 쓸 때도 마찬가지다. 절대 null을 반환하지 말고 길이가 0인 배열을 반환하라. toArray 메서드는 cheesesInStock 배열에 원소가 있으면 cheesesInStock 배열을 반환하고, 그렇지 않으면 Cheese[] 타입의 배열을 새로 생성해 반환한다.

```java
public Cheese[] getCheeses() {
    return cheesesInStock.toArray(new Cheese[0]);
}
```


위 방식이 성능을 떨어뜨릴꺼 같으면 빈 불변 배열을 만들어 매번 새로 할당하지 않도록한다.

```java
private static final Cheese[] EMPTY_CHEESE_ARRAY = new Cheese[0];

public Cheese[] getCheeses() {
    return cheesesInStock.toArray(EMPTY_CHEESE_ARRAY);
}
```

