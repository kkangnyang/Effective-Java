package effective.java.chapter6.item36;

import java.util.EnumSet;
import java.util.Objects;
import java.util.Set;

public class Text {
	public static final int STYLE_BOLD = 1 << 0; // 1
	public static final int STYLE_ITALIC = 1 << 1; // 2
	
	public Text() {}
	
	// �Ű����� style�� 0�� �̻��� STYLE_ ����� ��Ʈ�� OR�� ���̴�.
	public void applyStyles(int styles) {
		System.out.println(styles);
	}
	
	public enum Style {BOLD, ITALIC, UNDERLINE, STRIKETHROUGH}
	
	// � Set�� �Ѱܵ� �ǳ�, EnumSet�� ���� ����.
	public void applyStyles2(Set<Style> styles) {
	    System.out.printf("Applying styles %s to text%n",
	            Objects.requireNonNull(styles));
	}
	
	public static void main(String[] args) {
		Text text = new Text();
		text.applyStyles(STYLE_BOLD | STYLE_ITALIC);
		text.applyStyles2(EnumSet.of(Style.BOLD, Style.ITALIC));
	}
	
}




