# ITEM48. 스트림 병렬화는 주의해서 사용하라



동시성 프로그래밍을 할 때는 안정성(safety)과 응답 가능(liveness) 상태를 유지하기 위해 애써야 하는데, 병렬 스트림 파이프라인 프로그래밍에서도 다를 바 없다.


아래 코드는 아이템45에서 다루었던 메르센 소수를 생성하는 프로그램을 `parallel()` 메서드를 넣어서 병렬화한것이다. 이 코드는 종료되지 않는다

```java
public static void main(String[] args) {
    primes().map(p -> (BigInteger.TWO).pow(p.intValueExact()).subtract(BigInteger.ONE))
            .parallel() // 스트림 병렬화
            .filter(mersenne -> mersenne.isProbablePrime(50))
            .limit(20)
            .forEach(System.out::println);
}

static Stream<BigInteger> primes() {
    return Stream.iterate(BigInteger.TWO, BigInteger::nextProbablePrime);
}
```

데이터 소스가 Stream.iterate (primes()) 이거나 중간 연산으로 limit을 쓰면 파이프라인 병렬화로는 성능 개선을 기대할 수 없다.


왜냐하면 limit은 CPU 코어가 남는다면 원소를 몇 개 더 처리한 후 제한된 개수 이후의 결과를 버려도 아무런 해가 없다고 가정한다. 그런데 이 코드의 경우 새롭게 메르센 소수를 찾을 때마다 그 전 소수를 찾을때보다 두배 정도 오래걸린다. (CPU코어가 남는다면 계속 찾기 때문이다)


따라서 스트림 파이프라인을 마구잡이로 병렬화하면 안된다. 성능이 오히려 끔찍하게 나빠질 수도 있다.


## 언제 스트림 병렬화를 쓸 수 있을까?

### 자료구조

스트림의 소스가 ArrayList, HashMap, HashSet, ConcurrentHashMap의 인스턴스거나 배열, int범위, long범위일때 병렬화의 효과가 가장 좋다. 이 자료구조들의 공통점은 “참조 지역성”이 뛰어나다는 것이다. 이웃한 원소의 참조들이 메모리에 연속해서 저장되어 있다는 뜻이다. 하지만 참조들이 가리키는 실제 객체가 메모리에서 서로 떨어져 있을 수 있는데, 이 경우에는 데이터가 주 메모리에서 캐시 메모리로 전송되어 대부분 시간을 멍하니 보내게 된다.


참조 지역성이 뛰어난 자료구조는 기본 타입의 배열이다. 참조가 아닌 데이터 자체가 메모리에 연속해서 저장되기 때문이다.

### 종단연산

reduction 종단 연산이 병렬화에 가장 적합하다. reduction은 파이프라인에서 만들어진 모든 원소를 하나로 합치는 작업으로, Stream의 reduce 메서드, min, max, count, sum 과 같은 메서드다. 또한, anyMatch, allMatch, noneMatch 등 조건에 맞으면 바로 반환되는 메서드도 병렬화에 적합하다.


반면, 축소를 수행하는 Stream 의 collect 메서드는 병렬화에 적합하지 않다. 컬렉션들을 합치는 부담이 크기 때문이다.