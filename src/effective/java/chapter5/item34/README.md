# ITEM34. int ��� ��� ���� Ÿ���� ����϶�

> ��� ���� ����

```java
public static final int APPLE_FUJI = 0;
public static final int APPLE_PIPPIN = 1;
public static final int APPLE_GRANNY_SMITH = 2;

public static final int ORANGE_NAVEL = 0;
public static final int ORANGE_TEMPLE = 1;
public static final int ORANGE_BLOOD = 2;
```

���� �ڵ�ó�� ���� ����� �� �������� ��Ƽ� �����س��� ���� ���� ���� �����̴�.


> ���� ���� ������ ����

- ���� ���� ���Ͽ��� Ÿ�� ������ ������ �� �����ϴ�. ���� ��� �������� �ǳ׾� �� �޼��忡 ����� ������ ���� ������(==)�� ���ϴ��� �����Ϸ��� �ƹ��� ��� �޽����� ������� �ʽ��ϴ�.
- �ڹٴ� ���� ���� ������ ���� ������ �̸�����(namespace)�� �������� �ʱ� ������ ��¿ �� ���� ���ξ �Ἥ �̸� �浹�� �����ؾ� �մϴ�.
- ���� ����� ���ڿ��� ����ϱⰡ ��ٷӽ��ϴ�. �� ���� ����ϰų� ����ŷ� ���캸�� ���� ���ڷθ� ���̱� ������ ����뿡 ������ ���� �ʽ��ϴ�.
- ���� ���� ���� �׷쿡 ���� ��� ����� �� ���� ��ȸ�ϴ� ����� ����ġ �ʰ�, �� �ȿ� ����� �� �������� �� ���� �����ϴ�.
- ����� ����� ������ �ͻ��̶� �������ϸ� �� ���� Ŭ���̾�Ʈ ���Ͽ� �״�� �������ϴ�. ���� ����� ���� �ٲ�� Ŭ���̾�Ʈ�� �ݵ�� �ٽ� �������ؾ� �մϴ�. �ٽ� ���������� ���� Ŭ���̾�Ʈ�� ������ �Ǵ��� �����ϰ� �����ϰ� �˴ϴ�.
- ���ڿ� ���� ���� �������� ������ �� ������, ����� �̸��� �ϵ� �ڵ��ؾ� �ϱ� ������ ��Ÿ�� �־ �����Ϸ��� ����ϰ� ��Ÿ�� ���װ� ���� ���ɼ��� ����ϴ�.


> ���� Ÿ��

���� Ÿ���� ���� ������ ��� ���� ������ ����, �� ���� ���� ������� �ʴ� Ÿ���Դϴ�.

���� Ÿ�� ��ü�� Ŭ�����̸�, ��� �ϳ��� �ڽ��� �ν��Ͻ��� �ϳ��� ����� public static final �ʵ�� �����մϴ�. ���� Ÿ���� �ۿ��� ������ �� �ִ� �����ڸ� �������� �����Ƿ� ��ǻ� final �Դϴ�. ���� Ŭ���̾�Ʈ�� �ν��Ͻ��� ���� �����ϰų� Ȯ���� �� ������ ���� Ÿ�� �������� ������� �ν��Ͻ����� �� �ϳ����� �������� ����˴ϴ�(�̱���).

**enum Ÿ���� �ۼ� ����**

```java
public enum Apple { FUJI, PIPPIN, GRANNY_SMITH }
public enum Orange { NAVEL, TEMPLE, BLOOD }
```

���� Ÿ���� ������Ÿ�� Ÿ�� �������� �����մϴ�. ���� �ٸ� Ÿ���� ���� �ѱ���ϸ� ������ ������ ���ϴ�.

���� Ÿ���� ������ �޼��峪 �ʵ带 �߰��� �� �ְ� ������ �������̽��� ������ ���� �ֽ��ϴ�. Object �޼����� Comparable, Serializable�� ����������, �� ����ȭ ���µ� ����ŭ ������ ���ص� �������� �����ϰԲ� �����Ǿ� �ֽ��ϴ�.


- �����Ϳ� �޼��带 ���� ���� Ÿ��

```java
public enum Planet {
	MERCURY(3.302e+23, 2.439e6),
    VENUS(4.869e+24, 6.052e6),
    EARTH(5.975e+24, 6.378e6),
    MARS(6.419e+23, 3.393e6),
    JUPITER(1.899e+27, 7.149e7),
    SATURN(5.685e+26, 6.027e7),
    URANUS(8.683e+25, 2.556e7),
    NEPTUNE(1.024e+26, 2.477e7);

    private final double mass; // ���� (����: ų�α׷�)
    private final double radius; // ������ (����: ����)
    private final double surfaceGravity; // ǥ���߷� (����: m / s^2)

    // �߷� ��� (���� : m^3 / kg s^2)
    private static final double G = 6.67300E-11;

    Planet(double mass, double radius) {
        this.mass = mass;
        this.radius = radius;
        surfaceGravity = G * mass / (radius * radius);
    }

    public double getMass() {
        return mass;
    }

    public double getRadius() {
        return radius;
    }

    public double surfaceGravity() {
        return surfaceGravity;
    }

    public double surfaceWeight(double mass) {
        return mass * surfaceGravity; // F = ma
    }
}
```

**���� Ÿ�� ��� ������ Ư�� �����Ϳ� ������������ �����ڿ��� �����͸� �޾� �ν��Ͻ� �ʵ忡 �����ϸ� �˴ϴ�. **
���� Ÿ���� �ٺ������� �Һ��̶� ��� �ʵ�� final�̾�� �մϴ�. �ʵ带 public���� �����ص� ������, private���� �ΰ� ������ public ������ �޼��带 �δ� ���� �����ϴ�.


���� Ÿ���� �ڽ� �ȿ� ���ǵ� ������� ���� �迭�� ��� ��ȯ�ϴ� ���� �޼����� values()�� �����մϴ�. ������ ����� ������ ����˴ϴ�.


�� ���� Ÿ�� ���� toString() �޼���� ��� �̸��� ���ڿ��� ��ȯ�մϴ�.


���� Ÿ���� �Ϲ� Ŭ������ ����������, �� ����� Ŭ���̾�Ʈ�� �����ؾ� �� �մ��� ������ ���ٸ� private��, Ȥ�� (�ʿ��ϴٸ�) package-private���� �����ϴ� ���� �����ϴ�.


�θ� ���̴� ���� Ÿ���� �鷹�� Ŭ������ �����, Ư�� �鷹�� Ŭ���������� ���δٸ� �ش� Ŭ������ ��� Ŭ������ ����ϴ�.


���� Ÿ�Կ��� ��� �̸��� �Է¹޾� �� �̸��� �ش��ϴ� ����� ��ȯ���ִ� valueOf() �޼��尡 �ڵ� �����˴ϴ�.


> ����� �پ��� ����� �����ϴ� ���

```java
public enum Operations {
	PLUS, MINUS, TIMES, DIVIDE;

    public double apply(double x, double y) {
        switch (this) {
            case PLUS:
                return x + y;
            case MINUS:
                return x - y;
            case TIMES:
                return x * y;
            case DIVIDE:
                return x / y;
        }

        throw new AssertionError("�� �� ���� ����: " + this);
    }
}
```

���� �ڵ�� ��Ģ���꿡 ���� ������ ������ ���� Ÿ������ �����ϰ�, �� ���긶�� switch���� ���� �б��ϰ� �ֽ��ϴ�. ���� ���ο� ����� �߰��ϸ� �ش� case���� �߰��ؾ� �մϴ�. Ȥ�ö� �����Ѵٸ�, �������� ������ ���� �߰��� ������ �����Ϸ� �� �� "�� �� ���� ����"�̶�� ��Ÿ�� ������ �� ���Դϴ�.


�̸� �� ���� ������� �����ϵ��� ����� ����� �߻� �޼��带 �����ϰ� �� ����� Ŭ���� ��ü(constant-specific class body)�� �ڽſ� �°� ������ �ϴ� ����Դϴ�.


> ����� �޼��� ������ Ȱ���� ���� Ÿ��

```java
public enum OperationsMethod {
	PLUS {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    }, 
    DIVIDE {
        @Override
        public double apply(double x, double y) {
            return x / y;
        }
    };
    
    public abstract double apply(double x, double y);
}
```


���� Ÿ���� toString() �޼��带 �������Ϸ��ŵ�, toString�� ��ȯ�ϴ� ���ڿ��� �ش� ���� Ÿ�� ����� ��ȯ���ִ� fromString �޼��嵵 �Բ� �����ϴ� ���� ����� �� �� �ֽ��ϴ�.

```java
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public enum OperationsFromString {
	PLUS("+") {
        @Override
        public double apply(double x, double y) {
            return x + y;
        }
    },
    MINUS("-") {
        @Override
        public double apply(double x, double y) {
            return x - y;
        }
    },
    TIMES("*") {
        @Override
        public double apply(double x, double y) {
            return x * y;
        }
    },
    DIVIDE("/") {
        @Override  
        public double apply(double x, double y) {
            return x / y;
        }
    };

    private final String symbol;

    private static Map<String, OperationsFromString> stringToEnum =
            Stream.of(values()).collect(
                    Collectors.toMap(Object::toString, e -> e));

    OperationsFromString(String symbol) {
        this.symbol = symbol;
    }

    @Override
    public String toString() {
        return symbol;
    }

    // ������ ���ڿ��� �ش��ϴ� Operations�� (�����Ѵٸ�) ��ȯ�Ѵ�.
    public static Optional<OperationsFromString> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }

    public abstract double apply(double x, double y);
}
```


**Operations ����� stringToEnum �ʿ� �߰��Ǵ� ������ ���� Ÿ�� ��� ���� �� ���� �ʵ尡 �ʱ�ȭ�� ���Դϴ�. ���� Ÿ���� ���� �ʵ� �� ���� Ÿ���� �����ڿ��� ������ �� �ִ� ���� ��� ���� ���Դϴ�.** ���� Ÿ�� �����ڰ� ����Ǵ� �������� ���� �ʵ���� ���� �ʱ�ȭ�Ǳ� ���̶�, �ڱ�  �ڽ��� �߰����� ���ϰ� �ϴ� ������ �� �ʿ��մϴ�. �� ������ Ư���� ����, ���� Ÿ�� �����ڿ��� ���� ���� Ÿ���� �ٸ� ������� ������ �� �����ϴ�.


> ����� �޼��� ������ ����


����� �޼��� ������ ���� Ÿ�� ������� �ڵ带 �����ϱ� ��ƴٴ� ������ �ֽ��ϴ�. ������� ���� ó���� �ϴ� �޼��尡 �ִ��� �߻� �޼��带 ������ �ؾ��ϴ� �ߺ��Ǵ� �ڵ尡 �߻��ϰ� �˴ϴ�.

�̷��� ������ ����ϰ� �ذ��ϴ� ����� ���ο� ����� �߰��� �� '����'�� �����ϵ��� �����ϴ� ���Դϴ�.

```java
public enum PayrollDay {
	MONDAY(PayType.WEEKDAY),
    TUESDAY(PayType.WEEKDAY),
    WEDNESDAY(PayType.WEEKDAY),
    THURSDAY(PayType.WEEKDAY),
    FRIDAY(PayType.WEEKDAY),
    
    SATURDAY(PayType.WEEKEND),
    SUNDAY(PayType.WEEKEND);
    

    private final PayType payType;
    
    PayrollDay(PayType payType) {
        this.payType = payType;
    }
    
    int pay(int minutesWorked, int payRate) {
        return payType.pay(minutesWorked, payRate);
    }
    
    // ���� ���� Ÿ��
    enum PayType {
        WEEKDAY {
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked <= MINS_PER_SHIFT ? 0 :
                        (minsWorked - MINS_PER_SHIFT) * payRate / 2;
            }
        },
        WEEKEND {
            @Override
            int overtimePay(int minsWorked, int payRate) {
                return minsWorked * payRate / 2;
            }
        };
        
        abstract int overtimePay(int mins, int payRate);
        private static final int MINS_PER_SHIFT = 8 * 60;
        
        int pay(int minsWorked, int payRate) {
            int basePay = minsWorked * payRate;
            return basePay + overtimePay(minsWorked, payRate);
        }
    }
}
```

�� �ڵ�� ���߰� �ָ��� ���� �ܾ� ������ �����ϴ� �ӱ��� ����ϴ� ���� Ÿ���Դϴ�. �ܾ����� ����� private ��ø ���� Ÿ��(PayType)���� �����ϰ�, PayrollDay ���� Ÿ���� �����ڿ��� ���� ������ ���� �����մϴ�. �� ����� PayrollDay ���� Ÿ���� �ܾ����� ����� �� ���� ���� Ÿ�Կ� �����Ͽ�, switch ���̳� ����� �޼��� ������ �ʿ� ���� �˴ϴ�.

switch ���� ���� Ÿ���� ����� ������ �����ϴ� �� �������� �ʽ��ϴ�. 
������ **���� ���� Ÿ�Կ� ����� ������ ȥ���� ���� ���� switch ���� ���� ������ �� �� �ֽ��ϴ�.**
���� ��� ������Ƽ���� ������ Operations ���� Ÿ���� �ִµ�, �� ������ �ݴ� ������ ��ȯ�ϴ� �޼��尡 �ʿ��ϴٸ� �Ʒ��� ���� ������ �� �ֽ��ϴ�.

```java
public static Operations inverse(Operations op) {
    switch(op) {
        case PLUS: return Operations.MINUS;
        case MINUS: return Operations.PLUS;
        case TIMES: return Operations.DIVIDE;
        case DIVIDE: return Operations.TIMES;
        
        default: throw new AssertionError("�� �� ���� ����: " + op);
    }
}

```



> ���� Ÿ���� ����ؾ� �� ��

** �ʿ��� ���Ҹ� ������Ÿ�ӿ� �� �� �� �ִ� ��� �����̶�� �׻� ���� Ÿ���� ����ؾ� �մϴ�. **