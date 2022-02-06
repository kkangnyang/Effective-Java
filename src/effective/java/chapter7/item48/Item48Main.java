package effective.java.chapter7.item48;

import java.math.BigInteger;
import java.util.stream.Stream;

public class Item48Main {

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

}
