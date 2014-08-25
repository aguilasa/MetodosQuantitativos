package br.furb.mq.polinomial;

import br.furb.mq.fractions.Fraction;

public class PolynomialFraction {
	private Fraction[] coef; // coefficients
	private int deg; // degree of PolynomialFraction (0 for the zero polynomial)

	// a * x^b
	public PolynomialFraction(Fraction a, int b) {
		coef = new Fraction[b + 1];
		for (int i = 0; i <= b; i++)
			coef[i] = new Fraction("0");
		coef[b] = a;
		deg = degree();
	}

	// return the degree of this PolynomialFraction (0 for the zero polynomial)
	public int degree() {
		int d = 0;
		for (int i = 0; i < coef.length; i++)
			if (coef[i] != null)
				d = i;
		return d;
	}

	// return c = a + b
	public PolynomialFraction plus(PolynomialFraction b) {
		PolynomialFraction a = this;
		PolynomialFraction c = new PolynomialFraction(new Fraction("0"), Math.max(a.deg, b.deg));
		for (int i = 0; i <= a.deg; i++) {
			if ((c.coef[i] != null) && (a.coef[i] != null))
				c.coef[i] = c.coef[i].add(a.coef[i]);
		}
		for (int i = 0; i <= b.deg; i++) {
			if ((c.coef[i] != null) && (b.coef[i] != null))
				c.coef[i] = c.coef[i].add(b.coef[i]);
		}
		c.deg = c.degree();
		return c;
	}

	// return (a - b)
	public PolynomialFraction minus(PolynomialFraction b) {
		PolynomialFraction a = this;
		PolynomialFraction c = new PolynomialFraction(new Fraction("0"), Math.max(a.deg, b.deg));

		for (int i = 0; i <= a.deg; i++) {
			if ((c.coef[i] != null) && (a.coef[i] != null))
				c.coef[i] = c.coef[i].subtract(a.coef[i]);
		}
		for (int i = 0; i <= b.deg; i++) {
			if ((c.coef[i] != null) && (b.coef[i] != null))
				c.coef[i] = c.coef[i].subtract(b.coef[i]);
		}

		c.deg = c.degree();
		return c;
	}

	// return (a * b)
	public PolynomialFraction times(PolynomialFraction b) {
		PolynomialFraction a = this;
		PolynomialFraction c = new PolynomialFraction(new Fraction("0"), a.deg + b.deg);
		for (int i = 0; i <= a.deg; i++)
			for (int j = 0; j <= b.deg; j++)
				c.coef[i + j] = c.coef[i + j].add(a.coef[i].multiply(b.coef[j]));
		c.deg = c.degree();
		return c;
	}

	// return a(b(x)) - compute using Horner's method
	public PolynomialFraction compose(PolynomialFraction b) {
		PolynomialFraction a = this;
		PolynomialFraction c = new PolynomialFraction(new Fraction("0"), 0);
		for (int i = a.deg; i >= 0; i--) {
			PolynomialFraction term = new PolynomialFraction(a.coef[i], 0);
			c = term.plus(b.times(c));
		}
		return c;
	}

	// do a and b represent the same polynomial?
	public boolean eq(PolynomialFraction b) {
		PolynomialFraction a = this;
		if (a.deg != b.deg)
			return false;
		for (int i = a.deg; i >= 0; i--)
			if (a.coef[i] != b.coef[i])
				return false;
		return true;
	}

	// use Horner's method to compute and return the PolynomialFraction
	// evaluated at x
	public Fraction evaluate(Fraction x) {
		Fraction p = new Fraction("0");
		for (int i = deg; i >= 0; i--)
			p = coef[i].add((x.multiply(p)));
		return p;
	}

	// differentiate this PolynomialFraction and return it
	public PolynomialFraction differentiate() {
		if (deg == 0)
			return new PolynomialFraction(new Fraction("0"), 0);
		PolynomialFraction deriv = new PolynomialFraction(new Fraction("0"), deg - 1);
		deriv.deg = deg - 1;
		for (int i = 0; i < deg; i++)
			deriv.coef[i] = new Fraction(String.valueOf(i + 1)).multiply(coef[i + 1]);
		return deriv;
	}

	// convert to string representation
	public String toString() {
		if (deg == 0)
			return "" + coef[0];
		if (deg == 1)
			return coef[1] + "x + " + coef[0];
		String s = coef[deg] + "x^" + deg;
		for (int i = deg - 1; i >= 0; i--) {
			if (coef[i] == 0)
				continue;
			else if (coef[i] > 0)
				s = s + " + " + (coef[i]);
			else if (coef[i] < 0)
				s = s + " - " + (-coef[i]);
			if (i == 1)
				s = s + "x";
			else if (i > 1)
				s = s + "x^" + i;
		}
		return s;
	}

	// test client
	public static void main(String[] args) {
		PolynomialFraction zero = new PolynomialFraction(new Fraction("0"), 0);

		PolynomialFraction p1 = new PolynomialFraction(new Fraction("4"), 3);
		PolynomialFraction p2 = new PolynomialFraction(new Fraction("3"), 2);
		PolynomialFraction p3 = new PolynomialFraction(new Fraction("1"), 0);
		PolynomialFraction p4 = new PolynomialFraction(new Fraction("2"), 1);
		PolynomialFraction p = p1.plus(p2).plus(p3).plus(p4); // 4x^3 + 3x^2 + 1

		PolynomialFraction q1 = new PolynomialFraction(new Fraction("3"), 2);
		PolynomialFraction q = q1.plus(q2); // 3x^2 + 5

		PolynomialFraction r = p.plus(q);
		PolynomialFraction s = p.times(q);
		PolynomialFraction t = p.compose(q);

		PolynomialFraction o = new PolynomialFraction(1, 3).minus(new PolynomialFraction(2, 2)).minus(new PolynomialFraction(5, 1)).minus(new PolynomialFraction(6, 0));

		System.out.println("zero(x) =     " + zero);
		System.out.println("p(x) =        " + p);
		System.out.println("q(x) =        " + q);
		System.out.println("p(x) + q(x) = " + r);
		System.out.println("p(x) * q(x) = " + s);
		System.out.println("p(q(x))     = " + t);
		System.out.println("0 - p(x)    = " + zero.minus(p));
		System.out.println("p(3)        = " + p.evaluate(new Fraction("3")));
		System.out.println("p'(x)       = " + p.differentiate());
		System.out.println("p''(x)      = " + p.differentiate().differentiate());
		System.out.println("o(x)        = " + o);
		System.out.println("o(3)        = " + o.evaluate(new Fraction("3")));
	}
}
