# ITEM24. 멤버 클래스는 되도록 static으로 만들라

중첩 클래스(nested class) 란 다른 클래스 안에 정의된 클래스를 말한다.

중첩 클래스의 종류는 아래 네 가지가 있다.

- 정적 멤버 클래스
- (비정적) 멤버 클래스
- 익명 클래스
- 지역 클래스

## 정적 멤버 클래스
정적 멤버 클래스는 흔히 바깥 클래스와 함께 쓰일 때만 유용한 public 도우미 클래스로 쓰인다.

```
public class Calculator {
    public enum Operation{
        PLUS, MINUS
    }
}
```
그러면 클라이언트에서는 Calculator.Operation.PLUS 처럼 쓸 수 있다.

## 비정적 멤버 클래스
비정적 멤버 클래스는 구문상 static이 빠진 형태일 뿐이다.

```
void foo(){
    A a = new A();
    A.B b = a.new B();
}
//or
void foo(){
    A.B b = new A().new B();
}
```
비정적 내부 클래스를 생성하는 경우에는 반드시 A객체를 생성한 뒤 객체를 이용해서 생성해야 한다. 즉, 비정적 내부 클래스는 바깥 클래스(이 경우 A)에 대한 참조가 필요하다는 것이다.

따라서 중첩클래스가 독립적으로 존재해야 한다면, 정적 멤버 클래스로 만들어야 한다.

비정적 내부 클래스의 경우 바깥 클래스에 대한 참조를 가지고 있기 때문에 메모리 누수가 발생할 수 있다. 바깥 클래스는 더이상 사용되지 않지만 내부 클래스의 참조로 인해 GC가 수거해가지 못하기 때문이다.

## 어댑터로 많이 쓰인다.
어떤 클래스의 인스턴스를 감싸 마치 다른 클래스의 인스턴스처럼 보이게 하는 뷰로 사용한다.
참고. https://niceman.tistory.com/141

```
public Set<K> keySet() {
    Set<K> ks = keySet;
    if (ks == null) {
        ks = new KeySet();
        keySet = ks;
    }
    return ks;
}

final class KeySet extends AbstractSet<K> {
    public final int size()                 { return size; }
    public final void clear()               { HashMap.this.clear(); }
    public final Iterator<K> iterator()     { return new KeyIterator(); }
    public final boolean contains(Object o) { return containsKey(o); }
    public final boolean remove(Object key) {
        return removeNode(hash(key), key, null, false, true) != null;
    }
    public final Spliterator<K> spliterator() {
        return new KeySpliterator<>(HashMap.this, 0, -1, 0, 0);
    }

    public Object[] toArray() {
        return keysToArray(new Object[size]);
    }

    public <T> T[] toArray(T[] a) {
        return keysToArray(prepareArray(a));
    }

    public final void forEach(Consumer<? super K> action) {
        Node<K,V>[] tab;
        if (action == null)
            throw new NullPointerException();
        if (size > 0 && (tab = table) != null) {
            int mc = modCount;
            for (Node<K,V> e : tab) {
                for (; e != null; e = e.next)
                    action.accept(e.key);
            }
            if (modCount != mc)
                throw new ConcurrentModificationException();
        }
    }
}
```
HashMap의 keySet() 을 보면, Map의 key에 해당하는 값들을 Set으로 반환해주는데, 비정적 멤버 클래스로 선언해서 KeySet을 생성해서 Set<K>로 반환해주고 있다. (어뎁터 패턴)

## 익명 클래스
쓰이는 시점에 선언과 동시에 인스턴스가 만들어진다. 선언한 지점에서만 인스턴스를 만들 수 있고, instanceof 검사나 클래스의 이름이 필요한 작업은 수행할 수 없다.

람다처럼 즉석에서 작은 함수객체나 처리객체를 만드는데 주로 사용한다.

## 지역 클래스
지역 클래스는 블록 안에 정의된 클래스를 말한다.
참고. https://live-everyday.tistory.com/189
 
```
public void sayHello() {
  class EnglishGreeting implements HelloWorld {...}
}
```
지역변수를 선언할 수 있는 곳이면 어디서든 선언할 수 있고, 유효 범위도 지역변수와 같다.
