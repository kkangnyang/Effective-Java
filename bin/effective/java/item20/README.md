# ITEM20. 추상 클래스보다는 인터페이스를 우선하라

## 기존 클래스의 릴리즈
기존 클래스에 새로운 인터페이스를 구현해 넣는 것은 쉽지만, 새로운 추상 클래스를 끼워넣기는 어렵다.

## 믹스인 기능
인터페이스는 mixin 정의에 안성맞춤이다. 믹스인이란 클래스가 구현할 수 있는 타입으로, 믹스인을 구현한 클래스에 원래의 '주된 타입'외에도 특정 선택정 행위를 제공한다고 선언하는 효과를 준다.

예를들어, Comparable은 자신을 구현한 클래스의 인스턴스들끼리 순서를 정할 수 있다고 선언하는 믹스인 인터페이스다. 추상 클래스로는 이러한 믹스인을 정의할 수 없다.

## Type 프레임워크
인터페이스로는 계층구조가 없는 타입 프레임워크를 만들 수 있다.

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
이러한 인터페이스를 클래스로 구현하려면, 가능한 조합 전부를 각각의 클래스로 정의한 조합 폭발 현상이 생긴다.

## 래퍼 크래스 (아이템18)
래퍼 클래스와 함께하면 인터페이스는 기능을 향상시키는 안전하고 강력한 수단이 된다.

타입을 추상 클래스로 정의해두면 그 타입에 기능을 추가하는 방법은 상속뿐이다. 인터페이스는 Type을 매개변수로 받아서 래퍼 클래스로 유연하게 대처할 수 있다.

터페이스의 메서드 중 구현 방법이 명백하다면 구현 자체를 디폴트 메서드로 제공할 수 있다.

- equals와 hashCode 같은 Object의 메서드들은 지양하자.

## 추상 골격 구현 클래스(sceletal implementation)
인터페이스로는 타입을 정의하고, 필요하면 디폴트 메서드 몇 개도 함께 제공한다. 그리고 골격 구현 클래스는 나머지 메서드들까지 구현한다. 이게 바로 템플릿 메서드 패턴이다.

```
public class IntArrays {
  // public interface List<E>
	static List<Integer> intArrayAsList(int[] a) {
		Objects.requireNonNull(a);

		// public abstract class AbstractList<E>
		return new AbstractList<Integer>() { // 익명 클래스 형태

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
Interface의 이름이 List 면 그 골격 구현 클래스의 이름은 AbstractList로 짓는다.
- List 는 인터페이스로, 타입을 정의한다. (Integer)
- AbstractList 를 익명클래스 형태로 구현해서 int로 받은 배열을 Integer 리스트로 리턴한다.

골격 구현 클래스 (AbstractList)의 아름다움은 추상 클래스처럼 구현을 도와주는 동시에, 추상 클래스로 타입을 정의할 때 따라오는 제약에서는 자유롭다는 점에 있다.


## 골격 구현 작성 방법
- 인터페이스를 잘 살펴 기반 메서드들을 선정
- 기반 메서드들을 사용해 직접 구현할 수 있는 메서드를 디폴트 메서드로 제공
	- 단, equals와 hashCode와 같은 Object의 메서드는 디폴트 메서드로 제공하면 안된다.
- 기반 메서드나 디폴트 메서드로 만들지 못한 메서드가 남아 있다면, 이 인터페이스를 구현하는 골격 구현 클래스를 하나 만들어 남은 메서드들을 작성해 넣는다.


### Map.Entry 인터페이스를 예시로 살펴보자
```
public abstract class AbstractMapEntry<K, V> implements Map.Entry<K, V>{

    // 변경 가능한 엔트리는 이 메서드를 반드시 재정의해야 한다.
    @Override public V setValue(V value) {
    	throw new UnsupportedOperationException();
    }

    // Map.Entry.equals의 일반 규약을 구현한다.
    @Override public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Map.Entry))
            return false;
        Map.Entry<?,?> e = (Map.Entry) o;
        return Objects.equals(e.getKey(),   getKey())
                && Objects.equals(e.getValue(), getValue());
    }

    // Map.Entry.hashCode의 일반 규약을 구현한다.
    @Override public int hashCode() {
        return Objects.hashCode(getKey())
                ^ Objects.hashCode(getValue());
    }

    @Override public String toString() {
        return getKey() + "=" + getValue();
    }

}
```
- getKey, getValue는 확실한 기반 메서드 이므로 인터페이스 것을 사용한다.
- equals, hashCode, toString 와 같은 Object의 메서드는 인터페이스에서 디폴트 메서드로 제공하면 안되므로 골격 구현에서 작성한다.

복잡한 인터페이스라면 구현하는 수고를 덜어주는 골격 구현을 함께 제공하는 방법을 꼭 고려해보자.


