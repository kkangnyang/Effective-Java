# ITEM42. �͸� Ŭ�������ٴ� ���ٸ� ����϶�.

���ٰ� ������ �������� �ڹٿ��� �Լ� Ÿ���� ǥ���� �� �߻� �޼��带 �ϳ��� ���� �������̽��� ����ߴ�. 
�� �������̽��� �͸�Ŭ������ �����ؼ� �Լ� ��ü�� ����ߴ�.

```java
Collections.sort(words, new Comparator<String>() {
    public int compare(String s1, String s2) {
      return Integer.compare(s1.length(), s2.length());
    }
});
```


���ٷ� ���� ���� �ڵ带 �ξ� ª�� ���� �� �ִ�.

```java
Collections.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
```

���⼭ ���ٰ�ü, �Ű����� (s1,s2)�� ��ȯ���� Ÿ���� ���� (Comparator), String, int ���� �ڵ忡���� ��������� �ʾҴ�. �����Ϸ��� ������ ���� Ÿ���� �߷��߱� �����̴�.

> �����Ϸ��� Ÿ���� �߷��ϴµ� �ʿ��� Ÿ�� ���� ��κ��� ���׸����� ��� ������, ���׸��� ���ٸ� �Բ� �� ���� ���׸��� ���� �� �߿�������.


���� �ڸ��� ���� ���� �޼��带 ����ϸ� �ڵ带 �� �����ϰ� �� �� �ִ�.

```java
Collections.sort(words, comparingInt(String::length));
```


������34���� Operation ����Ÿ���� ����� Ŭ���� ��ü�� �� ������� �ٸ��� �����ϴ� apply �޼��带 �ۼ��ߴ� ���� �ٽú���

```java
public enum BasicOperation implements Operation{

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

    private final String symbol;

    BasicOperation(String symbol) {
        this.symbol = symbol;
    }

    @Override public String toString() {
        return symbol;
    }

}
```

�� ���캸��, �� ����� apply �޼��带 �����ϰ��ִ�. ���ٸ� �̿��ϸ� �̷��� ������ �Ķ����ȭ �ؼ� ���� �����ϰ� ���� �� �ִ�.


```java
public enum Operation {

	PLUS("+", (x,y) -> x + y),
	MINUS("-", (x,y) -> x - y),
	TIMES("*", (x,y) -> x*y),
	DIVICE("/", (x,y) -> x/y);

	private final String symbol;
	private final DoubleBinaryOperator op;

	Operation(String symbol, DoubleBinaryOperator op) {
		this.symbol = symbol;
		this.op = op;
	}

	@Override
	public String toString() { return symbol; }

	public double apply(double x, double y) {
		return op.applyAsDouble(x, y);
	}

}
```


���� ū ���������δ�
- ����� Ŭ���� ��ü�� �޼��带 ������ ���, �߻� �޼��带 �����ϰ� �����Ѵ�.
- ���ٸ� DoubleBinaryOperator�� �����ؼ� �Ķ���ͷ� ������ ���, �߻� �޼��带 �������� �ʾҴ�. ������ �����ڷ� DoubleBinaryOperator�� ������ �޾ƾ��ϱ� ������ �ٸ� Operator�� ���͵� �׻� ���ٸ� �����ϰ� �ȴ�.


���ٴ� �Լ��� �������̽������� ���δ�. ���� ���, �߻� Ŭ������ �ν��Ͻ��� ���� ��, ���ٸ� �� �� ������ �͸� Ŭ������ ����Ѵ�. ����, ���ٴ� �ڽ��� ������ �� ����. ���ٿ����� this�� �ٱ� �ν��Ͻ��� ����Ų��. �ݸ� �͸� Ŭ���������� this�� �͸� Ŭ������ �ν��Ͻ� �ڽ��� ����Ų��. ���� �Լ� ��ü�� �ڽ��� �����ؾ� �Ѵٸ� �ݵ�� �͸� Ŭ������ ��� �Ѵ�.