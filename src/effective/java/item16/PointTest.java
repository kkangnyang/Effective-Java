package effective.java.item16;

public class PointTest {

	public static void main(String[] args) {
		Point p = new Point();
        p.x = 123;
        System.out.println(p.x);

        Point2 p2 = new Point2(12, 34);
        p2.setX(123);
        System.out.println(p2.getX());
	}

}
