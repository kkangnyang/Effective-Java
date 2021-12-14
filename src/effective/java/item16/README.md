# ITEM6. public Ŭ���������� public �ʵ尡 �ƴ� ������ �޼��带 ����϶�

> public �ʵ� ���

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

- ���� ���� Ŭ���� Point�� ������ �ʵ忡 ���� ������ �� ������ ĸ��ȭ�� ������ �������� ���մϴ�.
- API�� �������� �ʰ�� ���� ǥ���� �ٲ� �� �����ϴ�.
- �Һ����� ������ �� �����ϴ�.


> �����ڿ� ������ ������ ���� �����͸� ĸ��ȭ
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

## ���
- public Ŭ������ ���� ���� �ʵ带 ���� �����ؼ��� �ȵ˴ϴ�.
- �Һ� �ʵ��� �����ص� �� ���������� ������ �Ƚ��� ���� �����ϴ�. (item 15�� �迭�� ��� �Ƚ��� �� ����)
- package-private Ŭ������ ��ø Ŭ���������� ���� �ʵ带 �����ϴ� ���� �������� �ֽ��ϴ�.

