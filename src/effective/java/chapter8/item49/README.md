# ITEM49. 매개변수가 유효한지 검사하라

메서드와 생성자 대부분은 입력 매개변수의 값이 특정 조건을 만족하기를 바란다. “오류는 가능한 한 빨리 잡아야 한다” 따라서 메서드 혹은 생성자로 인입되는 `파라미터`의 유효성을 검사하자


## 매개변수 검사를 제대로 하지 못하면 생기는 문제점

메서드가 수행되는 중간에 모호한 예외를 던지며 실패할 수 있다. 더 나쁜 상황은 메서드는 문제없이 수행됐지만, 어떤 객체를 이상한 상태로 만들어놓아서 미래의 알 수 없는 시점에 이 메서드와 관련 없는 오류를 낼 때이다.


## 공개 API를 반드시 문서화하라

public과 protected 메서드는 매개변수 값이 잘못됐을 떄 던지는 예외를 문서화해야 한다.(@throws 자바독 태그를 사용하면 된다.)
보통은 **IllegalArgumentException, IndexOutOfBoundsException, NullPointerException** 중 하나가 될 것이다.
매개변수의 제약을 문서화한다면 그 제약을 어겼을 떄 발생하는 예외도 함께 기술해야 한다.

### BigInteger 클래스의 mod() 메서드 예시

```java
/**
 * Returns a BigInteger whose value is {@code (this mod m}).  This method
 * differs from {@code remainder} in that it always returns a
 * <i>non-negative</i> BigInteger.
 *
 * @param  m the modulus.
 * @return {@code this mod m}
 * @throws ArithmeticException {@code m} &le; 0
 * @see    #remainder
 */
public BigInteger mod(BigInteger m) {
    if (m.signum <= 0)
        throw new ArithmeticException("BigInteger: modulus not positive");

    BigInteger result = this.remainder(m);
    return (result.signum >= 0 ? result : result.add(m));
}
```


위 메서드는 m이 null이면 m.signum() 호출 때 NullPointerException을 던진다.
그런데 "m이 null일 때 NullPointerException을 던진다"라는 말은 메서드 설명 어디에도 없다.
그 이유는 **이 설명을 (개별 메서드가 아닌) BigInteger 클래스 수준에서 기술했기 때문이다.**


클래스 수준 주석은 그 클래스의 모둔 public 메서드에 적용되므로 각 메서드에 일일이 기술하는 것보다 훨씬 깔끔한 방법.


@Nullable이나 이와 비슷한 애너테이션을 사용해 특정 매개변수는 null이 될 수 있다고 알려줄 수도 있지만, 표준적인 방법은 아니다.

**자바 7에 추가된 java.util.Objects.requireNonNull 메서드는 유연하고 사용하기도 편하니, 더 이상 null 검사를 수동으로 하지 않아도 된다.**

```java
this.strategy = Objects.requireNonNull(strategy, "ErrorMessage");
```

## 단언문(assert)

공개되지 않은 메서드라면 패키지 제작자인 작성자가 메서드가 호출되는 상황을 통제할 수 있다. 
따라서 오직 유효한 값만이 메서드에 넘겨지리라는 것을 작성자가 보증할 수 있다.
다시 말해 public이 아닌 메서드라면 단언문(assert)을 사용해 매개변수 유효성을 검증할 수 있다.

```java
private static void sort(long a[], int offset, int length) {
    assert a != null;
    assert offset >= 0 && offset <= a.length;
    assert length >= 0 && length <= a.length - offset;
    //계산 수행 ...
}
```

> 일반적은 유효성 검사와 단언문이 다른 점

- 실패하면 AssertionError를 던진다.
- 런타임에는 아무런 효과도, 아무런 성능 저하도 없다. (단, java를 실행할 때 명령줄에서 -ea 혹은 --enableas sertions 플래그를 설정하면 런타임에 영향을 준다.)


## 규칙의 예외 사항

메서드 몸체 실행 전에 매개변수 유효성을 검사해야 한다는 규칙에도 예외는 있다. 유효성 검사 비용이 지나치게 높거나 실용적이지 않을 때, 혹은 계산 과정에서 암묵적으로 검사가 수행될 때.


예를 들어 Collections.sort(List)처럼 객체 리스트를 정렬하는 메서드를 생각해 보겠다. 리스트 안의 객체들은 모두 상호 비교될 수 있어야 하며, 정렬 과정에서 이 비교가 이뤄진다. 만약 상호 비교될 수 없는 타입의 객체가 들어있다면 그 객체와 비교할 때 ClassCastException을 던질 것이고, 따라서 비교하기 앞서 리스트 안의 모든 객체가 상호 비교될 수 있는지 검사해봐야 별다른 실익이 없다.
