# ITEM36. ��Ʈ �ʵ� ��� EnumSet�� ����϶�

������ ������ �ַ� �������� ���� ���, �� ������ ������� �ܵ����� ������� �ʰ�, ��� ���� ��찡 �ִ�.

```java
public class Text {

  public static final int STYLE_BOLD = 1 << 0; // 1
  public static final int STYLE_ITALIC = 1 << 1; // 2

  // �Ű����� style�� 0�� �̻��� STYLE_ ����� ��Ʈ�� OR�� ���̴�.
  public void applyStyles(int styles) { ... }
}
```

������ ���� OR ������ ����ؼ� ���� ����� �ϳ��� �������� ���� �� �ְ�, �̷��� ������� ������ ��Ʈ �ʵ��� �Ѵ�.

```java
text.applyStyles(STYLE_BOLD | STYLE_ITALIC);
```

��Ʈ �ʵ带 ����ϸ� ��Ʈ�� ������ ����� �����հ� ������ ���� ���� ������ ȿ�������� ������ �� �ִ�. ������ ITEM34���� ���캻 ���� ���� ����� ������ �״�� ���ϰ� ������, ��Ʈ �ʵ� ���� �״�� ��µǸ� �� �ؼ��ϱⰡ ��ƴ�.

## EnumSet Ŭ����

Set �������̽��� �Ϻ��� �����ϸ�, Ÿ�� �����ϴ�. ���ΰ� ��Ʈ ���ͷ� �����Ǿ����Ƿ� ��Ʈ �ʵ忡 ��ߵǴ� ������ �����ش�.

```java
public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}

// � Set�� �Ѱܵ� �ǳ�, EnumSet�� ���� ����.
public void applyStyles(Set<Style> styles) {
    System.out.printf("Applying styles %s to text%n",
            Objects.requireNonNull(styles));
}

// ��� ��
public static void main(String[] args) {
    Text text = new Text();
    text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
}
```

EnumSet Ŭ������ ��Ʈ�ʵ� ������ ����԰� ������ �����Ѵ�. ������ �����̶�� ���� �Һ� EnumSet�� ���� �� ���ٴ� ���̴�. ���� Collections.unmodifiableSet���� EnumSet�� ���� ����ϸ� ������� ���ϵȴ�.