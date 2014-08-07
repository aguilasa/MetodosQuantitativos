package br.furb.mq.functions;

import br.furb.mq.fractions.Fraction;

public class FunctionTest01 implements IMathFunction {

	@Override
	public Fraction f(Fraction x) {
		Fraction f1 = new Fraction("2").multiply(x.exp(3));
		Fraction f2 = new Fraction("2").multiply(x.exp(2));
		Fraction f3 = new Fraction("1").multiply(x);
		return f1.subtract(f2).subtract(f3);
	}

}
