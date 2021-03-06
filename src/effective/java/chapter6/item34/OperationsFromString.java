package effective.java.chapter6.item34;

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

    // 지정한 문자열에 해당하는 Operations을 (존재한다면) 반환한다.
    public static Optional<OperationsFromString> fromString(String symbol) {
        return Optional.ofNullable(stringToEnum.get(symbol));
    }

    public abstract double apply(double x, double y);
}
