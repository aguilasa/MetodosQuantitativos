package br.furb.mq.main;

import java.math.BigInteger;
import java.util.List;

import br.furb.mq.prime.Prime;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Métodos quantitativos");
		
		List<BigInteger> primes =  Prime.getInstance().getPrimes();
		System.out.println(primes);
	}

}
