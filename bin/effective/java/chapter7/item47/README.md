# ITEM47. 반환 타입으로는 스트림보다 컬렉션이 낫다

원소 시퀀스, 즉 일련의 원소를 반환하는 메서드는 수없이 많다. Collection, List, Set 과 같은 컬렉션 인터페이스, 혹은 Iterable이나 배열을 썼다. 자바8에서는 스트림이라는 개념이 들어오면서 선택지가 늘어났다.


아이템45에서 말했듯이 스트림은 반복을 지원하지 않는다.(한번 사용하면 끝난다.)


어댑터 메서드를 사용하면 스트림을 iterate해서 사용할 수 있다.


### 스트림 <-> 반복자 어댑터

```java
public class Adapters {
    // 코드 47-3 Stream<E>를 Iterable<E>로 중개해주는 어댑터 (285쪽)
    public static <E> Iterable<E> iterableOf(Stream<E> stream) {
        return stream::iterator; // stream은 Iterator 인터페이스를 확장했으므로 iterator를 가지고 있다
    }

    // 코드 47-4 Iterable<E>를 Stream<E>로 중개해주는 어댑터 (286쪽)
    public static <E> Stream<E> streamOf(Iterable<E> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
```

위와 같이 Stream을 iterator로, iterator를 스트림으로 변경할 수 있다. 하지만, 공개 API를 작성할때 이를 사용하는 용도를 예상해서 Iterator를 반환할지, Stream을 반환할지 고려하도록 하자


Collection 인터페이스는 Iterable의 하위 타입이고 stream 메서드도 제공하니 반복과 스트림을 동시에 지원한다. 따라서 원소 시퀀스를 반환하는 공개 API의 반환 타입에는 Collection이나 그 하위 타입을 쓰는게 일반적으로 최선이다.

>> 단지 컬렉션을 반환한다는 이유로 덩치 큰 시퀀스를 메모리에 올려서는 안된다.


