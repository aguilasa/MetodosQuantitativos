package br.furb.mq.main;

import br.furb.mq.fractions.Fraction;
import br.furb.mq.functions.FunctionTest01;
import br.furb.mq.methods.Bissection;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		System.out.println("M�todos quantitativos");

		// List<BigInteger> primes = Prime.getInstance().getPrimes();
		// System.out.println(primes);

		Fraction a = new Fraction("1");
		Fraction b = new Fraction("2");
		Fraction e = new Fraction("1", "1000000");

		FunctionTest01 f = new FunctionTest01();

		Bissection biss = new Bissection(a, b, f, e);
		biss.process();

		System.out.println(biss);
		Fraction f1 = new Fraction("-12", "10");
		Fraction f2 = new Fraction("3");
		Fraction f3 = f2.subtract(f1);

		System.out.println(f3);
	}

}
