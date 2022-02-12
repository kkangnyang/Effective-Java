# ITEM50. 적시에 방어적 복사본을 만들라

## 기간을 표현하는 클래스 - 불변식을 지키지 못했다.

```java
public final class Period {
  private final Date start;

  public Period(Date start) {
    this.start = start;
  }

  public Date start() {
    return start;
  }
}
```

## Period 인스턴스의 내부 공격

```java
Date start = new Date();
Period p = new Period(start);
start.setYear(78);  // p의 내부를 수정했다
```

- Date가 가변이라서 발생 한 문제
	- 자바 8 이후로는 Date 대신 불변인 Instant를 사용하면 된다.(LocalDateTime이나 ZonedDateTime 사용 가능)
	- Date는 낡은 API이니 새로운 코드 작성시 더 이상 사용하면 안된다.
- 생성자에서 받은 가변 매개변수 각각을 방어적으로 복사(defensive copy)해야 한다.

## Period 인스턴스를 향한 두 번째 공격

```java
Date start = new Date();
Period p = new Period(start);
p.start.setYear(78); // p의 내부를 변경했다.
```

## 수정한 접근자 - 필드의 방어적 복사본을 반환한다

```java
public Date start() {
  return new Date(start.getTime());
}
```

- 생성자와 달리 접근자 메서드에서는 방어적 복사에 clone을 사용해도 된다.
	- Period가 가지고 있는 Date 객체는 java.util.Date임이 확실하기 때문
	- 그래도 인스턴슬르 복사하는 데는 일반적으로 생성자나 정적 팩터리를 쓰는게 좋다.


