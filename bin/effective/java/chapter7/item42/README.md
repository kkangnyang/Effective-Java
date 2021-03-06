# ITEM42. 익명 클래스보다는 람다를 사용하라.

람다가 나오기 이전에는 자바에서 함수 타입을 표현할 때 추상 메서드를 하나만 담은 인터페이스를 사용했다. 
이 인터페이스를 익명클래스로 구현해서 함수 객체로 사용했다.

```java
Collections.sort(words, new Comparator<String>() {
    public int compare(String s1, String s2) {
      return Integer.compare(s1.length(), s2.length());
    }
});
```


람다로 위와 같은 코드를 훨씬 짧게 만들 수 있다.

```java
Collections.sort(words, (s1, s2) -> Integer.compare(s1.length(), s2.length()));
```

여기서 람다객체, 매개변수 (s1,s2)와 반환값의 타입은 각각 (Comparator), String, int 지만 코드에서는 명시해주지 않았다. 컴파일러가 문맥을 살펴 타입을 추론했기 때문이다.

> 컴파일러가 타입을 추론하는데 필요한 타입 정보 대부분을 제네릭에서 얻기 때문에, 제네릭과 람다를 함께 쓸 때는 제네릭의 힘이 더 중요해진다.


람다 자리에 비교자 생성 메서드를 사용하면 코드를 더 간결하게 할 수 있다.

```java
Collections.sort(words, comparingInt(String::length));
```


아이템34에서 Operation 열거타입을 상수별 클래스 몸체에 각 상수별로 다르게 동작하는 apply 메서드를 작성했던 것을 다시보자

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

잘 살펴보면, 각 상수는 apply 메서드를 정의하고있다. 람다를 이용하면 이러한 동작을 파라미터화 해서 보다 간결하게 만들 수 있다.


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


가장 큰 차이점으로는
- 상수별 클래스 몸체에 메서드를 구현한 경우, 추상 메서드를 선언하고 구현한다.
- 람다를 DoubleBinaryOperator로 구현해서 파라미터로 전달한 경우, 추상 메서드를 선언하지 않았다. 하지만 생성자로 DoubleBinaryOperator를 무조건 받아야하기 때문에 다른 Operator가 들어와도 항상 람다를 전달하게 된다.


람다는 함수형 인터페이스에서만 쓰인다. 예를 들어, 추상 클래스의 인스턴스를 만들 때, 람다를 쓸 수 없으니 익명 클래스를 써야한다. 또한, 람다는 자신을 참조할 수 없다. 람다에서의 this는 바깥 인스턴스를 가리킨다. 반면 익명 클래스에서의 this는 익명 클래스의 인스턴스 자신을 가리킨다. 따라서 함수 객체가 자신을 참조해야 한다면 반드시 익명 클래스를 써야 한다.