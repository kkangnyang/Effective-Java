package effective.java.chapter7.item47;

import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class Adapters {
	// �ڵ� 47-3 Stream<E>�� Iterable<E>�� �߰����ִ� ����� (285��)
    public static <E> Iterable<E> iterableOf(Stream<E> stream) {
        return stream::iterator; // stream�� Iterator �������̽��� Ȯ�������Ƿ� iterator�� ������ �ִ�
    }

    // �ڵ� 47-4 Iterable<E>�� Stream<E>�� �߰����ִ� ����� (286��)
    public static <E> Stream<E> streamOf(Iterable<E> iterable) {
        return StreamSupport.stream(iterable.spliterator(), false);
    }
}
