# ITEM48. ��Ʈ�� ����ȭ�� �����ؼ� ����϶�



���ü� ���α׷����� �� ���� ������(safety)�� ���� ����(liveness) ���¸� �����ϱ� ���� �ֽ�� �ϴµ�, ���� ��Ʈ�� ���������� ���α׷��ֿ����� �ٸ� �� ����.


�Ʒ� �ڵ�� ������45���� �ٷ���� �޸��� �Ҽ��� �����ϴ� ���α׷��� `parallel()` �޼��带 �־ ����ȭ�Ѱ��̴�. �� �ڵ�� ������� �ʴ´�

```java
public static void main(String[] args) {
    primes().map(p -> (BigInteger.TWO).pow(p.intValueExact()).subtract(BigInteger.ONE))
            .parallel() // ��Ʈ�� ����ȭ
            .filter(mersenne -> mersenne.isProbablePrime(50))
            .limit(20)
            .forEach(System.out::println);
}

static Stream<BigInteger> primes() {
    return Stream.iterate(BigInteger.TWO, BigInteger::nextProbablePrime);
}
```

������ �ҽ��� Stream.iterate (primes()) �̰ų� �߰� �������� limit�� ���� ���������� ����ȭ�δ� ���� ������ ����� �� ����.


�ֳ��ϸ� limit�� CPU �ھ ���´ٸ� ���Ҹ� �� �� �� ó���� �� ���ѵ� ���� ������ ����� ������ �ƹ��� �ذ� ���ٰ� �����Ѵ�. �׷��� �� �ڵ��� ��� ���Ӱ� �޸��� �Ҽ��� ã�� ������ �� �� �Ҽ��� ã�������� �ι� ���� �����ɸ���. (CPU�ھ ���´ٸ� ��� ã�� �����̴�)


���� ��Ʈ�� ������������ �������̷� ����ȭ�ϸ� �ȵȴ�. ������ ������ �����ϰ� ������ ���� �ִ�.


## ���� ��Ʈ�� ����ȭ�� �� �� ������?

### �ڷᱸ��

��Ʈ���� �ҽ��� ArrayList, HashMap, HashSet, ConcurrentHashMap�� �ν��Ͻ��ų� �迭, int����, long�����϶� ����ȭ�� ȿ���� ���� ����. �� �ڷᱸ������ �������� ������ ���������� �پ�ٴ� ���̴�. �̿��� ������ �������� �޸𸮿� �����ؼ� ����Ǿ� �ִٴ� ���̴�. ������ �������� ����Ű�� ���� ��ü�� �޸𸮿��� ���� ������ ���� �� �ִµ�, �� ��쿡�� �����Ͱ� �� �޸𸮿��� ĳ�� �޸𸮷� ���۵Ǿ� ��κ� �ð��� ���ϴ� ������ �ȴ�.


���� �������� �پ �ڷᱸ���� �⺻ Ÿ���� �迭�̴�. ������ �ƴ� ������ ��ü�� �޸𸮿� �����ؼ� ����Ǳ� �����̴�.

### ���ܿ���

reduction ���� ������ ����ȭ�� ���� �����ϴ�. reduction�� ���������ο��� ������� ��� ���Ҹ� �ϳ��� ��ġ�� �۾�����, Stream�� reduce �޼���, min, max, count, sum �� ���� �޼����. ����, anyMatch, allMatch, noneMatch �� ���ǿ� ������ �ٷ� ��ȯ�Ǵ� �޼��嵵 ����ȭ�� �����ϴ�.


�ݸ�, ��Ҹ� �����ϴ� Stream �� collect �޼���� ����ȭ�� �������� �ʴ�. �÷��ǵ��� ��ġ�� �δ��� ũ�� �����̴�.