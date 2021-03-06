# ITEM35. ordinal 메서드 대신 인스턴스 필드를 사용하라

열거 타입의 ordinal 메서드는 해당 상수의 위치를 반환해준다.

```java
public enum Ensemble {
    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
    SEXTET(6), SEPTET(7), OCTET(8), DOUBLE_QUARTET(8),
    NONET(9), DECTET(10), TRIPLE_QUARTET(12);

    public int numberOfMusicians() { return ordinal() + 1; }
}
```

numberOfMusicians 메서드의 의도는, 상수의 위치 (배열처럼)에 1을 더해서, 연주자의 총인원을 반환하는 것이다. 하지만 이러한 열거 타입은 (끔찍한) 단점이 있다.

- 무조건 순서대로 열거타입을 선언해야 한다.
- 값을 중간에 비워둘 수 없다. (항상 1,2,3,4,5 등 순차적으로 선언해야 한다.)

따라서 열거 타입상수에 연결된 값은 ordinal 메서드 얻지 말고 인스턴스 필드에 저장해서 사용하자.

```java
public enum Ensemble {
    SOLO(1), DUET(2), TRIO(3), QUARTET(4), QUINTET(5),
    SEXTET(6), SEPTET(7), OCTET(8), DOUBLE_QUARTET(8),
    NONET(9), DECTET(10), TRIPLE_QUARTET(12);

    private final int numberOfMusicians; // 인스턴스 필드
    Ensemble(int size) { this.numberOfMusicians = size; }
    public int numberOfMusicians() { return numberOfMusicians; }
}
```

그렇다면 왜 ordinal 메서드를 만들었을까? Enum의 API 문서를 보면 EnumSet과 EnumMap의 자료구조에 쓸 목적으로 설계되었다 라고 적혀있다.


참고) 대표적인 ENUM 메소드
http://www.tcpschool.com/java/java_api_enum
