# ITEM6. public 클래스에서는 public 필드가 아닌 접근자 메서드를 사용하라

> public 필드 사용

```
class Point {
	public double x;
   public double y;
}

public static void main(String[] args) {
	 Point p = new Point();
    p.x = 123;
    System.out.println(p.x);
}
```

- 위와 같은 클래스 Point는 데이터 필드에 직접 접근할 수 있으니 캡슐화의 이점을 제공하지 못합니다.
- API를 수정하지 않고는 내부 표현을 바꿀 수 없습니다.
- 불변식을 보장할 수 없습니다.


> 접근자와 변경자 설정을 통해 데이터를 캡슐화
```
public class Point2 {
	private double x;
    private double y;

	public Point2(double x, double y) {
	    this.x = x;
	    this.y = y;
	}
	
	public double getX() {
	    return x;
	}
	
	public double getY() {
	    return y;
	}
	
	public void setX(double x) {
	    this.x = x;
	}
	
	public void setY(double y) {
	    this.y = y;
	}
}

public static void main(String[] args) {
	Point2 p2 = new Point2(12, 34);
    p2.setX(123);
    System.out.println(p2.getX());
}
```

## 결론
- public 클래스는 절대 가변 필드를 직접 노출해서는 안됩니다.
- 불변 필드라면 노출해도 덜 위험하지만 완전히 안심할 수는 없습니다. (item 15의 배열의 경우 안심할 수 없음)
- package-private 클래스나 중첩 클래스에서는 종종 필드를 노출하는 편이 나을때도 있습니다.

