# ITEM38. Ȯ���� �� �ִ� ����Ÿ���� �ʿ��ϸ� �������̽��� ����϶�

���� Ÿ���� Ȯ���� �� ����. �׵� �׷� ����, ���� Ÿ�԰�, Ȯ�� Ÿ���� ���� ��� �� ���� ��θ� ��ȸ�� ����� ����ġ �ʱ� ������ ���� Ÿ���� Ȯ���� �� ���� �س���. �� ���, ���� Ÿ���� Ư�� �������̽��� ������ �� �ְ� �ϸ� �ȴ�.


��, ���� Ÿ���� �������̽��� ����ü ������ �ϴ� ���̴�.


## �������̽��� Ȯ���� ����Ÿ��Permalink

- Operation �������̽�

```java
public interface Operation {
	double apply(double x, double y);
}
```

- Operation �������̽��� Ȯ���� BasicOperation �� ExtendedOperation

```java
public enum BasicOperation implements Operation {
	PLUS("+") {
        public double apply(double x, double y) { return x + y; }
    },
    MINUS("-") {
        public double apply(double x, double y) { return x - y; }
    },
    TIMES("*") {
        public double apply(double x, double y) { return x * y; }
    },
    DIVIDE("/") {
        public double apply(double x, double y) { return x / y; }
    };

	BasicOperation(String string) {
	}

}

public enum ExtendsOperation implements Operation {
	EXP("^") {
        public double apply(double x, double y) {
            return Math.pow(x, y);
        }
    },
    REMAINDER("%") {
        public double apply(double x, double y) {
            return x % y;
        }
    };

	ExtendsOperation(String string) {
	}
}
```

������34���� �ô� ����� �޼��� ������ �ٸ��� ����ٸ� �̷��� �������̽��� Ȯ���� ������ �ȴ�.

Operation �������̽��� �����, �ϳ��� �߻�޼��带 ������, �� Ȯ��� Enum Type�� �̰��� �����ϰ� ����� ���̴�.

## ����Ÿ�Գ��� ���� ��� �Ұ�

���� ���� �������̽��� �̿��� ���� Ÿ���� Ȯ��������, �� ���� Ÿ�Գ��� ������ ������ ���� ����. 
���� �����ϴ� ����� ���� �ߺ��� �κ��� ���ٸ� ������ ����� Ŭ������ ���� ����� �޼���� �и��ϴ� ����� ����ؾ� �Ѵ�.

