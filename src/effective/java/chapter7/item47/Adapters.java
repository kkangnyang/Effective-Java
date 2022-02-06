package effective.java.chapter7.item47;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

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
