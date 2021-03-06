# ITEM27. 비검사 경고(Unchecked Warning)을 제거하라

제너릭을 사용하면서 Unchecked Error를 많이 접할 수 있다. 가능한 모든 Unchecked Error를 제거해야 타입 안정성이 보장된다.

|구분    |Checked Exception | Unchecked Exception |
|----   |------------------|----------------------|
|확인 시점|컴파일 시점           |런타임 시점             |
|처리 여부|반드시 예외 처리해야 한다.(컴파일 안됨)|명시적으로 하지 않아도 된다.(특정 런타임 때 에러가 발생할 가능성이 있다.|
|종류    |IOException, ClassNotFoundException 등 | NullPointerException, ClassCastException 등 |

## @SuppressWarnings(“unchecked”)

경고를 제거할 수는 없지만 타입 안전하다고 확신할 수 있다면 @SuppressWarnings("unchecked") 어노테이션을 달아서 경고를 숨길 수 있다.

개별 지역변수 부터 클래스 전체까지 어디에도 선언할 수 있지만, 항상 가능한 좁은 범위에 적용하자. 자칫 심각한 경고를 놓칠 수 있으니 절대로 클래스 전체에 적용해서는 안된다.

책에서는 ArrayList.class 의 toArray 메서드를 예로 든다.

```java
@SuppressWarnings("unchecked")
public <T> T[] toArray(T[] a) {
    if (a.length < size)
        // Make a new array of a's runtime type, but my contents:
        return (T[]) Arrays.copyOf(elementData, size, a.getClass());
    System.arraycopy(elementData, 0, a, 0, size);
    if (a.length > size)
        a[size] = null;
    return a;
}
```

Object[] elementData를 T[]로 형변환하는 데서 Type Safety 경고가 뜬다
하지만 여기서 매개변수로 받은 배열의 타입으로 Array를 새로 생성하기 때문에 모두 T[]로 같으므로 올바른 형변환이다. 
따라서 @SuppressWarnings 어노테이션으로 경고를 숨길 수 있다.

@SuppressWarnings(“unchecked”) 어노테이션을 사용하고, 그 경고를 무시해도 안전한 이유를 항상 주석으로 남기도록 하자!

또한, 위의 코드처럼, 메소드 toArray의 전체에 어노테이션을 선언하는 것이 아니라, 지역변수에도 지정할 수 있으니 가능한 좁은 범위로 선언하도록 하자!

