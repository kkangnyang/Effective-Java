package effective.java.chapter6.item34;

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

        throw new AssertionError("알 수 없는 연산: " + this);
    }
    
    public static Operations inverse(Operations op) {
        switch(op) {
            case PLUS: return Operations.MINUS;
            case MINUS: return Operations.PLUS;
            case TIMES: return Operations.DIVIDE;
            case DIVIDE: return Operations.TIMES;
            
            default: throw new AssertionError("알 수 없는 연산: " + op);
        }
    }
}
