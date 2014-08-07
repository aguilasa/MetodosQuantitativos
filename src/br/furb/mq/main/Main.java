package br.furb.mq.main;

import br.furb.mq.fractions.Fraction;
import br.furb.mq.functions.FunctionTest01;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("Métodos quantitativos");

		// List<BigInteger> primes = Prime.getInstance().getPrimes();
		// System.out.println(primes);

		Fraction fracao = new Fraction("5595", "4096");

		FunctionTest01 f = new FunctionTest01();

		System.out.println(f.f(fracao));
	}

}
