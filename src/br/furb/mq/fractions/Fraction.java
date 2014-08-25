package br.furb.mq.fractions;

import java.math.BigInteger;

public class Fraction implements Comparable<Fraction> {
	// member variables
	private BigInteger numerator, denominator; // stores the fraction data

	public Fraction() {
		numerator = new BigInteger("1");
		denominator = new BigInteger("1");
	}

	public Fraction(String aN, String aD) {
		numerator = new BigInteger(aN);
		denominator = new BigInteger(aD);
	}

	public Fraction(String aN) {
		numerator = new BigInteger(aN);
		denominator = new BigInteger("1");
	}

	public BigInteger getNumerator() {
		return numerator;
	}

	public void setNumerator(BigInteger num) {
		numerator = num;
	}

	public BigInteger getDenominator() {
		return denominator;
	}

	public void setDenominator(BigInteger den) {
		denominator = den;
	}

	public Fraction add(Fraction b) {
		// check preconditions
		if ((denominator == BigInteger.ZERO)
				|| (b.denominator == BigInteger.ZERO))
			throw new IllegalArgumentException("invalid denominator");
		// find lowest common denominator
		BigInteger common = lcd(denominator, b.denominator);
		// convert fractions to lcd
		Fraction commonA = new Fraction();
		Fraction commonB = new Fraction();
		commonA = convert(common);
		commonB = b.convert(common);
		// create new fraction to return as sum
		Fraction sum = new Fraction();
		// calculate sum
		sum.numerator = commonA.numerator.add(commonB.numerator);
		sum.denominator = common;
		// reduce the resulting fraction
		sum = sum.reduce();
		return sum;
	}

	public Fraction subtract(Fraction b) {
		// check preconditions
		if ((denominator == BigInteger.ZERO)
				|| (b.denominator == BigInteger.ZERO))
			throw new IllegalArgumentException("invalid denominator");
		// find lowest common denominator
		BigInteger common = lcd(denominator, b.denominator);
		// convert fractions to lcd
		Fraction commonA = new Fraction();
		Fraction commonB = new Fraction();
		commonA = convert(common);
		commonB = b.convert(common);
		// create new fraction to return as difference
		Fraction diff = new Fraction();
		// calculate difference
		diff.numerator = commonA.numerator.subtract(commonB.numerator);
		diff.denominator = common;
		// reduce the resulting fraction
		diff = diff.reduce();
		return diff;
	}

	public Fraction multiply(Fraction b) {
		// check preconditions
		if ((denominator == BigInteger.ZERO)
				|| (b.denominator == BigInteger.ZERO))
			throw new IllegalArgumentException("invalid denominator");
		// create new fraction to return as product
		Fraction product = new Fraction();
		// calculate product
		product.numerator = numerator.multiply(b.numerator);
		product.denominator = denominator.multiply(b.denominator);
		// reduce the resulting fraction
		product = product.reduce();
		return product;
	}

	public Fraction divide(Fraction b) {
		// check preconditions
		if ((denominator == BigInteger.ZERO)
				|| (b.numerator == BigInteger.ZERO))
			throw new IllegalArgumentException("invalid denominator");
		// create new fraction to return as result
		Fraction result = new Fraction();
		// calculate result
		result.numerator = numerator.multiply(b.denominator);
		result.denominator = denominator.multiply(b.numerator);
		// reduce the resulting fraction
		result = result.reduce();
		return result;
	}

	public Fraction exp(int p) {
		Fraction result = new Fraction();

		result.setNumerator(numerator);
		result.setDenominator(denominator);

		for (int i = 1; i < p; i++) {
			result = result.multiply(this);
		}

		return result;
	}
	
	public boolean isNegative()
	{
		return (this.numerator.compareTo(BigInteger.ZERO) < 0);
	}

	public void output() {
		System.out.print(this);
	}

	public String toString() {
		StringBuilder buffer = new StringBuilder();
		buffer.append(numerator);
		if (!denominator.equals(BigInteger.ONE)) {
			buffer.append("/").append(denominator);
		}

		return buffer.toString();
	}

	private BigInteger lcd(BigInteger denom1, BigInteger denom2) {
		BigInteger factor = denom1;
		while ((denom1.mod(denom2)) != BigInteger.ZERO)
			denom1 = denom1.add(factor);
		return denom1;
	}

	private BigInteger gcd(BigInteger denom1, BigInteger denom2) {
		BigInteger factor = denom2;
		while (denom2 != BigInteger.ZERO) {
			factor = denom2;
			denom2 = denom1.mod(denom2);
			denom1 = factor;
		}
		return denom1;
	}

	private Fraction convert(BigInteger common) {
		Fraction result = new Fraction();
		BigInteger factor = common.divide(denominator);
		result.numerator = numerator.multiply(factor);
		result.denominator = common;
		return result;
	}

	public Fraction reduce() {
		Fraction result = new Fraction();
		BigInteger common = BigInteger.ZERO;
		// get absolute values for numerator and denominator
		BigInteger num = numerator.abs();
		BigInteger den = denominator.abs();
		// figure out which is less, numerator or denominator
		if (num.compareTo(den) == 1)
			common = gcd(num, den);
		else if (num.compareTo(den) == -1)
			common = gcd(den, num);
		else
			// if both are the same, don't need to call gcd
			common = num;

		// set result based on common factor derived from gcd
		result.numerator = numerator.divide(common);
		result.denominator = denominator.divide(common);
		return result;
	}

	public static void main(String args[]) {
		Fraction f1 = new Fraction(); // local fraction objects
		Fraction f2 = new Fraction(); // used to test methods

		// one way to set up fractions is simply to hard-code some values
		f1.setNumerator(new BigInteger("1"));
		f1.setDenominator(new BigInteger("3"));
		f2.setNumerator(new BigInteger("1"));
		f2.setDenominator(new BigInteger("6"));

		// try some arithmetic on these fractions
		Fraction result = new Fraction();
		// test addition
		result = f1.add(f2);
		// one way to output results, using toString method directly
		System.out.println(f1 + " + " + f2 + " = " + result);
		// test addition going the other way - should be same result
		result = f2.add(f1);
		// output results
		System.out.println(f2 + " + " + f1 + " = " + result);
		System.out.println();

		// test subtraction
		result = f1.subtract(f2);
		// output results
		System.out.println(f1 + " - " + f2 + " = " + result);
		// test subtraction going the other way - should be different result
		result = f2.subtract(f1);
		// output results
		System.out.println(f2 + " - " + f1 + " = " + result);
	}

	@Override
	public int compareTo(Fraction o) {
		if (this == o) return 0;
		
		if (this.numerator.divide(this.denominator).doubleValue() < o.getNumerator().divide(this.getDenominator()).doubleValue()) return -1;
		if (this.numerator.divide(this.denominator).doubleValue() > o.getNumerator().divide(this.getDenominator()).doubleValue()) return 1;
		
		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((denominator == null) ? 0 : denominator.hashCode());
		result = prime * result
				+ ((numerator == null) ? 0 : numerator.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (!(obj instanceof Fraction)) {
			return false;
		}
		Fraction other = (Fraction) obj;
		if (denominator == null) {
			if (other.denominator != null) {
				return false;
			}
		} else if (!denominator.equals(other.denominator)) {
			return false;
		}
		if (numerator == null) {
			if (other.numerator != null) {
				return false;
			}
		} else if (!numerator.equals(other.numerator)) {
			return false;
		}
		return true;
	}
}
