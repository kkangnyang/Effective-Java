# ITEM22. 인터페이스는 타입을 정의하는 용도로만 사용하라

인터페이스는 자신을 구현한 클래스의 인스턴스를 참조할 수 있는 타입 역할을 한다.

인터페이스를 잘못 사용하고 있는 예로 상수 인터페이스가 있다.

## 인터페이스 안티패턴 - 상수 인터페이스
상수 인터페이스란 메서드 없이, 상수를 뜻하는 static final 필드로만 가득 찬 인터페이스를 말한다.
자바 플랫폼 라이브러리에도 이와 같은 상수 인터페이스가 몇 개 있다. java.io.ObjectStreamConstants

```
public interface ObjectStreamConstants {
    final static short STREAM_MAGIC = (short)0xaced;
    final static short STREAM_VERSION = 5;
}
```

상수는 내부 구현에 해당해서 클래스의 API로 노출하는 행위다. 따라서 인터페이스의 역할과는 멀다.
또한, 클라이언트 코드가 이 상수들에 종속되버려서 상수를 쓰지 않아도 이 상수 인터페이스를 구현하고 있어야 한다.

상수를 공개할 목적이라면 아래 방식으로 구현해야 한다.
- 클래스 자체에 추가해야 한다. (Integer, Double의 MAX_VALUE, MIN_VALUE 처럼)
- 열거타입으로 만들어 공개한다.
- 인스턴스화할 수 없는 유틸리티 클래스 아이템4에 담아 공개하자.

```
public class PhysicalConstants {
    private PhysicalConstants() {} // 인스턴스화 방지

    public static final double AVOGADROS_NUMBER = 6.022_140_857e23;
  }
```