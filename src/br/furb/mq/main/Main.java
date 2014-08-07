package br.furb.mq.main;

import java.math.BigInteger;
import java.util.List;

import br.furb.mq.fractions.Fraction;
import br.furb.mq.prime.Prime;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		System.out.println("Métodos quantitativos");
		
//		List<BigInteger> primes =  Prime.getInstance().getPrimes();
//		System.out.println(primes);
		
		Fraction fracao = new Fraction();
		fracao.setNumerator(new BigInteger("39"));
		fracao.setDenominator(new BigInteger("26"));
		
		System.out.println(fracao.reduce());
	}

}
