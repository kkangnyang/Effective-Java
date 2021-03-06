#ITEM21. 인터페이스는 구현하는 쪽을 생각해 설계하라
자바8부터는 기존 구현체를 수정하지 않아도 인터페이스에 디폴트 메소드를 추가할 수 있게되었다. 주로 람다를 활용하기 위해서다.

대부분 문제가 생기지 않지만, 모든 상황을 예측하기는 어렵다.

## 모든 구현체에 문제가 없는 것은 아니다.
Collection 인터페이스의 removeIf가 그 중 하나다.
이 메소드는 매개변수로 주어진 Predicate이 true면 모든 원소를 제거한다.

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

모든 Collection 구현체와 잘 어우러지고 있지만, org.apache.commons.collections4.collection.SynchronizedCollection 클래스에서는 문제가 있다.

이 클래스는 컬렉션 대신 클라이언트가 제공한 객체로 락을 거는 능력을 추가로 제공한다. 즉, 모든 메서드에서 주어진 락 객체로 동기화한 후 내부 컬렉션 객체에 (기존 SynchronizedCollection의)기능을 위임하는 래퍼 클래스다.

이 클래스를 자바 8과 함께 사용한다면, 그래서 removeIf의 디폴트 구현을 물려받게 된다면, 자신이 한 약속을 더 이상 지키지 못한다. 왜냐하면 removeIf의 구현은 동기화에 관해 아무것도 모르므로 락 객체를 사용할 수 없다.

멀티 스레드 환경에서 한 스레드가 removeIf를 호출하면 ConcurrentModificationException이 발생할 수 있다.

자바 플랫폼 라이브러리에서 이런 문제를 예방하기 위해 디폴트 메서드 호출 전 필요한 작업을 수행하도록 해놨다.


## 디폴트 메서드는 런타임 오류를 일으킬 수 있다.
흔한 일은 아니지만, 자바8은 컬렉션 인터페이스에 꽤 많은 디폴트 메서드를 추가했고, 그 결과 기존에 짜여진 많은 자바 코드가 영향을 받은 것으로 알려졌다.

따라서, 기존 인터페이스에 디폴트 메서드로 새 메서드를 추가하는 일은 꼭 필요한 경우가 아니면 피해야 한다. 대신, 신규 인터페이스인 경우에는 아주 유용한 수단이다.

## 참고 - Iterator 와 ConcurrentModificationException

SynchronizedCollection 을 처음 읽어봐서 구글링해서 이 글을 읽어보고 정리한다.
- https://tyboss.tistory.com/entry/Java-SynchronizedCollections-vs-ConcurrentCollections

Collection 클래스의 값을 반복시켜 읽어내는 가장 표준적인 방법은 바로 Iterator를 사용하는 방법이다. Iterator를 사용해 컬렉션 클래스 내부의 값을 차례로 읽고 사용하는 동안 다른 스레드가 같은 시점에 컬렉션 클래스 내부의 값을 변경하는 작업을 처리하지는 못하게 되어 있고, 대신 즉시 멈춤(fail-fast) 의 형태로 반응하도록 되어 있다.

즉시멈춤이란 반복문을 실행하는 도중에 컬랙션 클래스 내부의 값을 변경하는 상황이 포착되면 그 즉시 ConcurrentModificationException 예외를 발생시키고 멈추는 처리 방법이다.
