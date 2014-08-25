package br.furb.mq.derivative;

public class PolinomialDerivative {
	private double[] polinomial = null;
	private double[] derivative = null;

	public void setPolinomial(double[] p) {
		polinomial = p;
	}

	public double[] getPolinomial() {
		return polinomial;
	}

	public double[] getDerivative() {
		calculaDerivative();
		return derivative;
	}

	private void calculaDerivative() {
		derivative = new double[polinomial.length - 1];

		for (int i = 1; i < polinomial.length; i++)
			derivative[i - 1] = i * polinomial[i];
	}

	public void printPolinomial() {
		int i = 0;
		for (i = 0; i < polinomial.length - 1; i++)
			System.out.print(polinomial[i] + "x^" + i + " + ");
		System.out.println(polinomial[i] + "x^" + i);
	}

	public void printDerivative() {
		int i = 0;
		for (i = 0; i < derivative.length - 1; i++)
			System.out.print(derivative[i] + "x^" + i + " + ");
		System.out.println(derivative[i] + "x^" + i);
	}

	public static void main(String args[]) {
		PolinomialDerivative dp = new PolinomialDerivative();

//		// Polinomio = 1 + 2x + 3x^2 + 4x^3 + 5x^4
//		double polinomio[] = { 1, 2, 3, 4, 5 };
//
//		dp.setPolinomial(polinomio);
//		dp.getDerivative();
//		dp.printPolinomial();
//		dp.printDerivative();
		
		//1x^3-2x^2-5x^1-6
		double polinomio[] = { -6, -5, -2, 1 };

		dp.setPolinomial(polinomio);
		dp.getDerivative();
		dp.printPolinomial();
		dp.printDerivative();
	}
}
