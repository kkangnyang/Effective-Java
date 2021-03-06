# ITEM36. 비트 필드 대신 EnumSet을 사용하라

열거한 값들이 주로 집합으로 사용될 경우, 즉 열거한 상수들을 단독으로 사용하지 않고, 묶어서 사용될 경우가 있다.

```java
public class Text {

  public static final int STYLE_BOLD = 1 << 0; // 1
  public static final int STYLE_ITALIC = 1 << 1; // 2

  // 매개변수 style은 0개 이상의 STYLE_ 상수를 비트별 OR한 값이다.
  public void applyStyles(int styles) { ... }
}
```

다음과 같이 OR 연산을 사용해서 여러 상수를 하나의 집합으로 모을 수 있고, 이렇게 만들어진 집합을 비트 필드라고 한다.

```java
text.applyStyles(STYLE_BOLD | STYLE_ITALIC);
```

비트 필드를 사용하면 비트별 연산을 사용해 합집합과 교집합 같은 집합 연산을 효율적으로 수행할 수 있다. 하지만 ITEM34에서 살펴본 정수 열거 상수의 단점을 그대로 지니고 있으며, 비트 필드 값이 그대로 출력되면 더 해석하기가 어렵다.

## EnumSet 클래스

Set 인터페이스를 완벽히 구현하며, 타입 안전하다. 내부가 비트 벡터로 구현되었으므로 비트 필드에 비견되는 성능을 보여준다.

```java
public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}

// 어떤 Set을 넘겨도 되나, EnumSet이 가장 좋다.
public void applyStyles(Set<Style> styles) {
    System.out.printf("Applying styles %s to text%n",
            Objects.requireNonNull(styles));
}

// 사용 예
public static void main(String[] args) {
    Text text = new Text();
    text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
}
```

EnumSet 클래스는 비트필드 수준의 명료함과 성능을 제공한다. 유일한 단점이라면 아직 불변 EnumSet을 만들 수 없다는 것이다. 따라서 Collections.unmodifiableSet으로 EnumSet을 감싸 사용하면 어느정도 보완된다.