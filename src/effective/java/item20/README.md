# ITEM20. �߻� Ŭ�������ٴ� �������̽��� �켱�϶�

## ���� Ŭ������ ������
���� Ŭ������ ���ο� �������̽��� ������ �ִ� ���� ������, ���ο� �߻� Ŭ������ �����ֱ�� ��ƴ�.

## �ͽ��� ���
�������̽��� mixin ���ǿ� �ȼ������̴�. �ͽ����̶� Ŭ������ ������ �� �ִ� Ÿ������, �ͽ����� ������ Ŭ������ ������ '�ֵ� Ÿ��'�ܿ��� Ư�� ������ ������ �����Ѵٰ� �����ϴ� ȿ���� �ش�.

�������, Comparable�� �ڽ��� ������ Ŭ������ �ν��Ͻ��鳢�� ������ ���� �� �ִٰ� �����ϴ� �ͽ��� �������̽���. �߻� Ŭ�����δ� �̷��� �ͽ����� ������ �� ����.

## Type �����ӿ�ũ
�������̽��δ� ���������� ���� Ÿ�� �����ӿ�ũ�� ���� �� �ִ�.

```
public interface Singer {
  AudioClip sing(Song s);
}

public interface Songwriter {
  Song compose(int chartPosition);
}

public interface SingerSongWriter extends Singer, Songwriter {
  AudioClip strum();
  void actSensitive();
}
```
�̷��� �������̽��� Ŭ������ �����Ϸ���, ������ ���� ���θ� ������ Ŭ������ ������ ���� ���� ������ �����.

## ���� ũ���� (������18)
���� Ŭ������ �Բ��ϸ� �������̽��� ����� ����Ű�� �����ϰ� ������ ������ �ȴ�.

Ÿ���� �߻� Ŭ������ �����صθ� �� Ÿ�Կ� ����� �߰��ϴ� ����� ��ӻ��̴�. �������̽��� Type�� �Ű������� �޾Ƽ� ���� Ŭ������ �����ϰ� ��ó�� �� �ִ�.

�����̽��� �޼��� �� ���� ����� ����ϴٸ� ���� ��ü�� ����Ʈ �޼���� ������ �� �ִ�.

- equals�� hashCode ���� Object�� �޼������ ��������.

## �߻� ��� ���� Ŭ����(sceletal implementation)
�������̽��δ� Ÿ���� �����ϰ�, �ʿ��ϸ� ����Ʈ �޼��� �� ���� �Բ� �����Ѵ�. �׸��� ��� ���� Ŭ������ ������ �޼������� �����Ѵ�. �̰� �ٷ� ���ø� �޼��� �����̴�.

```
public class IntArrays {
  // public interface List<E>
	static List<Integer> intArrayAsList(int[] a) {
		Objects.requireNonNull(a);

		// public abstract class AbstractList<E>
		return new AbstractList<Integer>() { // �͸� Ŭ���� ����

			@Override
			public Integer get(int index) {
				return a[index];
			}

			@Override
			public int size() {
				return a.length;
			}

			@Override
			public Integer set(int i, Integer val) {
				int oldVal = a[i];
				a[i] = val;
				return oldVal;
			}

		};
	}

}
```
Interface�� �̸��� List �� �� ��� ���� Ŭ������ �̸��� AbstractList�� ���´�.
- List �� �������̽���, Ÿ���� �����Ѵ�. (Integer)
- AbstractList �� �͸�Ŭ���� ���·� �����ؼ� int�� ���� �迭�� Integer ����Ʈ�� �����Ѵ�.

��� ���� Ŭ���� (AbstractList)�� �Ƹ��ٿ��� �߻� Ŭ����ó�� ������ �����ִ� ���ÿ�, �߻� Ŭ������ Ÿ���� ������ �� ������� ���࿡���� �����Ӵٴ� ���� �ִ�.


## ��� ���� �ۼ� ���
- �������̽��� �� ���� ��� �޼������ ����
- ��� �޼������ ����� ���� ������ �� �ִ� �޼��带 ����Ʈ �޼���� ����
	- ��, equals�� hashCode�� ���� Object�� �޼���� ����Ʈ �޼���� �����ϸ� �ȵȴ�.
- ��� �޼��峪 ����Ʈ �޼���� ������ ���� �޼��尡 ���� �ִٸ�, �� �������̽��� �����ϴ� ��� ���� Ŭ������ �ϳ� ����� ���� �޼������ �ۼ��� �ִ´�.


### Map.Entry �������̽��� ���÷� ���캸��
```
public abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V>{

    // ���� ������ ��Ʈ���� �� �޼��带 �ݵ�� �������ؾ� �Ѵ�.
    @Override public V setValue(V value) {
    	throw new UnsupportedOperationException();
    }

    // Map.Entry.equals�� �Ϲ� �Ծ��� �����Ѵ�.
    @Override public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Map.Entry))
            return false;
        Map.Entry<?,?> e = (Map.Entry) o;
        return Objects.equals(e.getKey(),   getKey())
                && Objects.equals(e.getValue(), getValue());
    }

    // Map.Entry.hashCode�� �Ϲ� �Ծ��� �����Ѵ�.
    @Override public int hashCode() {
        return Objects.hashCode(getKey())
                ^ Objects.hashCode(getValue());
    }

    @Override public String toString() {
        return getKey() + "=" + getValue();
    }

}
```
- getKey, getValue�� Ȯ���� ��� �޼��� �̹Ƿ� �������̽� ���� ����Ѵ�.
- equals, hashCode, toString �� ���� Object�� �޼���� �������̽����� ����Ʈ �޼���� �����ϸ� �ȵǹǷ� ��� �������� �ۼ��Ѵ�.

������ �������̽���� �����ϴ� ���� �����ִ� ��� ������ �Բ� �����ϴ� ����� �� ����غ���.


