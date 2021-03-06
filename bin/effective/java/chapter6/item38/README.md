# ITEM38. 확장할 수 있는 열거타입이 필요하면 인터페이스를 사용하라

열거 타입은 확장할 수 없다. 그도 그럴 것이, 열거 타입과, 확장 타입이 있을 경우 이 원소 모두를 순회할 방법도 마땅치 않기 때문에 열거 타입을 확장할 수 없게 해놨다. 이 경우, 열거 타입이 특정 인터페이스를 구현할 수 있게 하면 된다.


즉, 열거 타입이 인터페이스의 구현체 역할을 하는 것이다.


## 인터페이스를 확장한 열거타입Permalink

- Operation 인터페이스

```java
public interface Operation {
	double apply(double x, double y);
}
```

- Operation 인터페이스를 확장한 BasicOperation 과 ExtendedOperation

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

아이템34에서 봤던 상수별 메서드 구현을 다르게 만든다면 이렇게 인터페이스를 확장한 구조가 된다.

Operation 인터페이스를 만들고, 하나의 추상메서드를 가지며, 각 확장된 Enum Type이 이것을 구현하게 만드는 것이다.

## 열거타입끼리 구현 상속 불가

위와 같이 인터페이스를 이용해 열거 타입을 확장했지만, 이 열거 타입끼리 구현을 공유할 수는 없다. 
만약 공유하는 기능이 많고 중복된 부분이 많다면 별도의 도우미 클래스나 정적 도우미 메서드로 분리하는 방식을 고려해야 한다.

