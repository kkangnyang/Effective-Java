package effective.java.chapter6.item38;

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
