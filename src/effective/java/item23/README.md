# ITEM23. �±� �޸� Ŭ�������ٴ� Ŭ���� ���������� Ȱ���϶�
Ư�� �ʵ带 �±׷� ��� ���� ���¸� �ǹ��ϴ� �ڵ带 ������ ���� ���̴�.

```
class Figure {
    enum Shape { RECTANGLE, CIRCLE };

    // �±� �ʵ� - ���� ����� ��Ÿ����.
    final Shape shape;

    // ���� �ʵ���� ����� �簢��(RECTANGLE)�� ���� ���δ�.
    double length;
    double width;

    // ���� �ʵ�� ����� ��(CIRCLE)�� ���� ���δ�.
    double radius;

    // ���� ������
    Figure(double radius) {
        shape = Shape.CIRCLE;
        this.radius = radius;
    }

    // �簢���� ������
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

- final Shape shape ���� ����� ��Ÿ���� �ʵ� ��
- area() �޼��带 ����, ���� ���¿� ���� ���ϰ��� �ٸ���.

## �±� �޸� Ŭ������ ����
- �ٸ� �ǹ̸� ���� �ڵ尡 ���� �־� �޸𸮵� ���� ���ǰ� �������� ���� �ʴ�.
- ���ο� �ǹ̸� �߰��� ������ switch���� ã�� �� �ǹ̸� ó���ϴ� �ڵ带 �߰��ؾ� �Ѵ�.

## Ŭ���� ���������� Ȱ���ϴ� ����Ÿ����(subtyping)�� ����
- ���������� root �� �� �߻� Ŭ������ �����ϰ�
- �±� ���� ���� ������ �޶����� �޼������ ��Ʈ Ŭ������ �߻� �޼���� �����Ѵ�.
- �±� ���� ������� ������ ������ �޼������ ��Ʈ Ŭ������ �Ϲ� �޼���� �߰��Ѵ�.
- ��Ʈ Ŭ������ Ȯ���� ��ü Ŭ������ �ǹ̺��� �ϳ��� �����Ѵ�.
// ��Ʈ Ŭ����

```
// ��Ʈ Ŭ����
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

- �� �ǹ̸� ������ Ŭ������ ��� ���� ���� ������ �ʵ尡 ��� ���� ���.
- ���������� ���������� Ȯ���ϰ� �Բ� ����� �� �ִ�.