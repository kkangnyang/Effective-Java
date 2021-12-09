package effective.java.item6;

import java.util.regex.Pattern;

public class HeavyObject {
	
	// ���� �󵵰� ���� ��������� ��� ��� - ĳ���Ͽ� ���� ����.
	// Pattern�ν��Ͻ��� �� �� ���� �������� ��ٷ� ������ �÷��� ����� �ȴ�. 
	// Pattern�� ��������� ���� Ŭ���� �� �ϳ��̴�. ���� �� ���� Pattern�� �ʿ����� ����ǰ� ���� �󵵰� ���ٸ� �Ʒ��� ���� ���(static final)�� �ʱ⿡ ĳ���س��� ������ �� �ִ�.
	private static final Pattern ROMAN = Pattern.compile("^(?=.)M*(C[MD]|D?C{0,3})"
            + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");

	public static void main(String[] args) {
		
		// ����µ� �޸𸮳� �ð��� ���� �ɸ��� ��ü �� "��� ��ü"�� �ݺ������� ������ �Ѵٸ�,
		// ĳ���صΰ� ������ �� �ִ��� ����ϴ� ���� ����.
		
	}
	
	static boolean isRomanNumeralSlow(String s) {
		// String.matches�� ����ǥ�������� ���ڿ� ���¸� Ȯ���ϴ� ���� ���� ���������, ������ �߿��� ��Ȳ���� �ݺ��� ����ϱ� �������� �ʴ�.
		return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
            + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
	}
	
	static boolean isRomanNumeralFast(String s) {
		return ROMAN.matcher(s).matches();
	}
	
	/** ���! ��������� ��� ��ü��� "ĳ��"����� ���. ���־��� ���̶�� static final�� �ʱ⿡ ĳ���س��� ���� ����! */

}
