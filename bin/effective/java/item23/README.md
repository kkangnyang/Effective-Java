# ITEM23. 태그 달린 클래스보다는 클래스 계층구조를 활용하라
특정 필드를 태그로 삼고 현재 상태를 의미하는 코드를 본적이 있을 것이다.

```
class Figure {
    enum Shape { RECTANGLE, CIRCLE };

    // 태그 필드 - 현재 모양을 나타낸다.
    final Shape shape;

    // 다음 필드들은 모양이 사각형(RECTANGLE)일 때만 쓰인다.
    double length;
    double width;

    // 다음 필드는 모양이 원(CIRCLE)일 때만 쓰인다.
    double radius;

    // 원용 생성자
    Figure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    // 사각형용 생성자
    Figure(double length, double width) {
        shape = Shape.RECTANGLE;
        this.length = length;
        this.width = width;
    }

    double area() {
        switch(shape) {
            case RECTANGLE:
                return length * width;
            case CIRCLE:
                return Math.PI * (radius * radius);
            default:
                throw new AssertionError(shape);
        }
    }
}
```

- final Shape shape 현재 모양을 나타내는 필드 값
- area() 메서드를 보면, 현재 상태에 따라 리턴값이 다르다.

## 태그 달린 클래스의 단점
- 다른 의미를 위한 코드가 섞여 있어 메모리도 많이 사용되고 가독성이 좋지 않다.
- 새로운 의미를 추가할 때마다 switch문을 찾아 새 의미를 처리하는 코드를 추가해야 한다.

## 클래스 계층구조를 활용하는 서브타이핑(subtyping)을 하자
- 계층구조의 root 가 될 추상 클래스를 정의하고
- 태그 값에 따라 동작이 달라지는 메서드들을 루트 클래스의 추상 메서드로 선언한다.
- 태그 값에 상관없이 동작이 일정한 메서드들은 루트 클래스에 일반 메서드로 추가한다.
- 루트 클래스를 확장한 구체 클래스를 의미별로 하나씩 정의한다.
// 루트 클래스

```
// 루트 클래스
abstract class Figure {
    abstract double area();
}

class Circle extends Figure {
    final double radius;

    Circle(double radius) { this.radius = radius; }

    @Override double area() { return Math.PI * (radius * radius); }
}

class Rectangle extends Figure {
    final double length;
    final double width;

    Rectangle(double length, double width) {
        this.length = length;
        this.width  = width;
    }
    @Override double area() { return length * width; }
}
```

- 각 의미를 독립된 클래스에 담아 관련 없던 데이터 필드가 모두 제거 됬다.
- 독립적으로 계층구조를 확장하고 함께 사용할 수 있다.