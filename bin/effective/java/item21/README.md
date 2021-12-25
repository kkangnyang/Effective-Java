#ITEM21. �������̽��� �����ϴ� ���� ������ �����϶�
�ڹ�8���ʹ� ���� ����ü�� �������� �ʾƵ� �������̽��� ����Ʈ �޼ҵ带 �߰��� �� �ְԵǾ���. �ַ� ���ٸ� Ȱ���ϱ� ���ؼ���.

��κ� ������ ������ ������, ��� ��Ȳ�� �����ϱ�� ��ƴ�.

## ��� ����ü�� ������ ���� ���� �ƴϴ�.
Collection �������̽��� removeIf�� �� �� �ϳ���.
�� �޼ҵ�� �Ű������� �־��� Predicate�� true�� ��� ���Ҹ� �����Ѵ�.

```
public interface Collection<E> extends Iterable<E> {
  default boolean removeIf(Predicate<? super E> filter) {
      Objects.requireNonNull(filter);
      boolean removed = false;
      final Iterator<E> each = iterator();
      while (each.hasNext()) {
          if (filter.test(each.next())) {
              each.remove();
              removed = true;
          }
      }
      return removed;
  }
}
```

��� Collection ����ü�� �� ��췯���� ������, org.apache.commons.collections4.collection.SynchronizedCollection Ŭ���������� ������ �ִ�.

�� Ŭ������ �÷��� ��� Ŭ���̾�Ʈ�� ������ ��ü�� ���� �Ŵ� �ɷ��� �߰��� �����Ѵ�. ��, ��� �޼��忡�� �־��� �� ��ü�� ����ȭ�� �� ���� �÷��� ��ü�� (���� SynchronizedCollection��)����� �����ϴ� ���� Ŭ������.

�� Ŭ������ �ڹ� 8�� �Բ� ����Ѵٸ�, �׷��� removeIf�� ����Ʈ ������ �����ް� �ȴٸ�, �ڽ��� �� ����� �� �̻� ��Ű�� ���Ѵ�. �ֳ��ϸ� removeIf�� ������ ����ȭ�� ���� �ƹ��͵� �𸣹Ƿ� �� ��ü�� ����� �� ����.

��Ƽ ������ ȯ�濡�� �� �����尡 removeIf�� ȣ���ϸ� ConcurrentModificationException�� �߻��� �� �ִ�.

�ڹ� �÷��� ���̺귯������ �̷� ������ �����ϱ� ���� ����Ʈ �޼��� ȣ�� �� �ʿ��� �۾��� �����ϵ��� �س���.


## ����Ʈ �޼���� ��Ÿ�� ������ ����ų �� �ִ�.
���� ���� �ƴ�����, �ڹ�8�� �÷��� �������̽��� �� ���� ����Ʈ �޼��带 �߰��߰�, �� ��� ������ ¥���� ���� �ڹ� �ڵ尡 ������ ���� ������ �˷�����.

����, ���� �������̽��� ����Ʈ �޼���� �� �޼��带 �߰��ϴ� ���� �� �ʿ��� ��찡 �ƴϸ� ���ؾ� �Ѵ�. ���, �ű� �������̽��� ��쿡�� ���� ������ �����̴�.

## ���� - Iterator �� ConcurrentModificationException

SynchronizedCollection �� ó�� �о���� ���۸��ؼ� �� ���� �о�� �����Ѵ�.
- https://tyboss.tistory.com/entry/Java-SynchronizedCollections-vs-ConcurrentCollections

Collection Ŭ������ ���� �ݺ����� �о�� ���� ǥ������ ����� �ٷ� Iterator�� ����ϴ� ����̴�. Iterator�� ����� �÷��� Ŭ���� ������ ���� ���ʷ� �а� ����ϴ� ���� �ٸ� �����尡 ���� ������ �÷��� Ŭ���� ������ ���� �����ϴ� �۾��� ó�������� ���ϰ� �Ǿ� �ְ�, ��� ��� ����(fail-fast) �� ���·� �����ϵ��� �Ǿ� �ִ�.

��ø����̶� �ݺ����� �����ϴ� ���߿� �÷��� Ŭ���� ������ ���� �����ϴ� ��Ȳ�� �����Ǹ� �� ��� ConcurrentModificationException ���ܸ� �߻���Ű�� ���ߴ� ó�� ����̴�.
