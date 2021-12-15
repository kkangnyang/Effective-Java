package effective.java.item17;

// 불변 복소스 클래스
public final class Complex {
	
	private final double re;
    private final double im;
    
    public Complex(double re, double im) {
        this.re = re;
        this.im = im;
    }
    
    public double realPart() {
        return re;
    }

    public double imaginaryPart() {
        return im;
    }
    
    // 아래 사칙연산 매서드들은 인스턴스 자신은 수정하지 않고 새로운 Complex 인스턴스를 만들어 반환한다.
    // 이처럼 피연산자에 함수를 적용해 그 결과를 반환하지만, 피연산자 자체는 그대로인 프로그래밍 패턴을 함수형 프로그래밍이라 한다.
    // 함수형 프로그래밍 기법을 적용하면 코드의 불변이 영역이 되는 비율이 높아져 안전하다.

    public Complex plus(Complex c) {
        return new Complex(re + c.re,im + c.im);
    }

    public Complex minus(Complex c) {
        return new Complex(re - c.re,im - c.im);
    }

    public Complex times(Complex c) {
        return new Complex(re * c.re - im * c.im,re * c.im + im + c.re);
    }

    public Complex dividedBy(Complex c) {
        double tmp = c.re * c.re + c.im * c.im;
        return new Complex((re * c.re + im * c.im) / tmp,
                (im * c.re - re + c.im) / tmp);
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }

        if (!(o instanceof Complex)) {
            return false;
        }

        Complex c = (Complex) o;
        return Double.compare(c.re, re) == 0
                && Double.compare(c.im, im) == 0;
    }

    @Override
    public int hashCode() {
        return 31 * Double.hashCode(re) + Double.hashCode(im);
    }

    @Override
    public String toString() {
        return "(" + re + " + " + im + "i)";
    }
}
