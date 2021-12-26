# ITEM24. ��� Ŭ������ �ǵ��� static���� �����

��ø Ŭ����(nested class) �� �ٸ� Ŭ���� �ȿ� ���ǵ� Ŭ������ ���Ѵ�.

��ø Ŭ������ ������ �Ʒ� �� ������ �ִ�.

- ���� ��� Ŭ����
- (������) ��� Ŭ����
- �͸� Ŭ����
- ���� Ŭ����

## ���� ��� Ŭ����
���� ��� Ŭ������ ���� �ٱ� Ŭ������ �Բ� ���� ���� ������ public ����� Ŭ������ ���δ�.

```
public class Calculator {
    public enum Operation{
        PLUS, MINUS
    }
}
```
�׷��� Ŭ���̾�Ʈ������ Calculator.Operation.PLUS ó�� �� �� �ִ�.

## ������ ��� Ŭ����
������ ��� Ŭ������ ������ static�� ���� ������ ���̴�.

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
������ ���� Ŭ������ �����ϴ� ��쿡�� �ݵ�� A��ü�� ������ �� ��ü�� �̿��ؼ� �����ؾ� �Ѵ�. ��, ������ ���� Ŭ������ �ٱ� Ŭ����(�� ��� A)�� ���� ������ �ʿ��ϴٴ� ���̴�.

���� ��øŬ������ ���������� �����ؾ� �Ѵٸ�, ���� ��� Ŭ������ ������ �Ѵ�.

������ ���� Ŭ������ ��� �ٱ� Ŭ������ ���� ������ ������ �ֱ� ������ �޸� ������ �߻��� �� �ִ�. �ٱ� Ŭ������ ���̻� ������ ������ ���� Ŭ������ ������ ���� GC�� �����ذ��� ���ϱ� �����̴�.

## ����ͷ� ���� ���δ�.
� Ŭ������ �ν��Ͻ��� ���� ��ġ �ٸ� Ŭ������ �ν��Ͻ�ó�� ���̰� �ϴ� ��� ����Ѵ�.
����. https://niceman.tistory.com/141

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
HashMap�� keySet() �� ����, Map�� key�� �ش��ϴ� ������ Set���� ��ȯ���ִµ�, ������ ��� Ŭ������ �����ؼ� KeySet�� �����ؼ� Set<K>�� ��ȯ���ְ� �ִ�. (��� ����)

## �͸� Ŭ����
���̴� ������ ����� ���ÿ� �ν��Ͻ��� ���������. ������ ���������� �ν��Ͻ��� ���� �� �ְ�, instanceof �˻糪 Ŭ������ �̸��� �ʿ��� �۾��� ������ �� ����.

����ó�� �Ｎ���� ���� �Լ���ü�� ó����ü�� ����µ� �ַ� ����Ѵ�.

## ���� Ŭ����
���� Ŭ������ ��� �ȿ� ���ǵ� Ŭ������ ���Ѵ�.
����. https://live-everyday.tistory.com/189
 
```
public void sayHello() {
  class EnglishGreeting implements HelloWorld {...}
}
```
���������� ������ �� �ִ� ���̸� ��𼭵� ������ �� �ְ�, ��ȿ ������ ���������� ����.
