# ITEM53. 가변인수는 신중히 사용하라

`int... args`와 같이 가변인수를 메서드에 파라미터로 넘길 수 있다. 메서드가 호출되면 가장 먼저 인수의 개수와 길이가 같은 배열을 만들고, 인수들을 이 배열에 저장하여 메서드에 건네준다.

- 인수가 1개 이상이어야 하는 가변인수 메서드

```java
static int min(int... args) {
    if (args.length == 0)
        throw new IllegalArgumentException("인수가 1개 이상 필요합니다.");
    int min = args[0];
    for (int i = 1; i < args.length; i++)
        if (args[i] < min)
            min = args[i];
    return min;
}
```

이 방식에는 메서드가 실행됬을 때 (런타임) 파라미터의 유효성을 검사해서 Exception 을 던진다. 이런 방식 보다 명시적으로 필수로 받아야하는 파라미터는 받고, 옵셔널한 인수들을 가변인수로 받도록 구현하면 더 깔끔한 코드가 된다.

- 인수가 1개 이상이어야 하는 가변인수 메서드 (올바른 예)

```java
static int min(int firstArg, int... remainingArgs) {
    int min = firstArg;
    for (int arg : remainingArgs)
        if (arg < min)
            min = arg;
    return min;
}
```

성능에 민감한 상황이라면 가변인수가 좋은 방법은 아니다. 왜냐하면 메서드가 호출될 때마다 배열을 새로 하나 할당하고 초기화하기 때문이다. 만약 성능을 고려해야한다면 **다중정의를 하자**

```java
public void foo() {}
public void foo(int a1) {}
public void foo(int a1, int a2) {}
public void foo(int a1, int a2, int... rest) {}
```
