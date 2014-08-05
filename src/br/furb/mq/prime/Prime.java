package br.furb.mq.prime;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Prime {
	/**
	 * Initial value for the final range of prime numbers
	 */
	private static int DEFFINALRANGE = 10000;
	private static Prime instance = new Prime();
	private List<BigInteger> primes = new ArrayList<BigInteger>();
	private int lastRange = 0;

	private Prime() {
	}

	public static Prime getInstance() {
		return instance;
	}

	private boolean isPrime(int aNumber) {
		if ((aNumber < 2) || (aNumber > 2 && aNumber % 2 == 0))
			return false;
		for (int i = 3; i * i <= aNumber; i += 2) {
			if (aNumber % i == 0)
				return false;
		}
		return true;
	}

	public List<BigInteger> getPrimes(int aRange) {
		if (aRange > this.lastRange) {
			for (int x = this.lastRange; x <= aRange; x++) {
				if (isPrime(x)) {
					this.primes.add(new BigInteger(String.valueOf(x)));
				}
			}
			this.lastRange = aRange + 1;
		}
		return this.primes;
	}

	public List<BigInteger> getPrimes() {
		return getPrimes(DEFFINALRANGE);
	}

}
