package br.furb.mq.methods;

import java.util.ArrayList;
import java.util.List;

import br.furb.mq.fractions.Fraction;
import br.furb.mq.functions.IMathFunction;

public class Bissection {
	private Fraction a;
	private Fraction b;
	private IMathFunction f;
	private Fraction error;

	public Bissection(Fraction a, Fraction b, IMathFunction f, Fraction error) {
		super();
		this.a = a;
		this.b = b;
		this.f = f;
		this.error = error;
	}

	public Fraction getA() {
		return a;
	}

	public void setA(Fraction a) {
		this.a = a;
	}

	public Fraction getB() {
		return b;
	}

	public void setB(Fraction b) {
		this.b = b;
	}

	public IMathFunction getF() {
		return f;
	}

	public void setF(IMathFunction f) {
		this.f = f;
	}

	public Fraction getError() {
		return error;
	}

	public void setError(Fraction error) {
		this.error = error;
	}

	private Fraction media(Fraction fractionA, Fraction fractionB) {
		return fractionA.add(fractionB).divide(new Fraction("2"));
	}

	private Fraction calcError(Fraction fractionA, Fraction fractionB) {
		Fraction f = fractionA.subtract(fractionB).divide(fractionA);
		
		if (f.isNegative()) {
			f = f.multiply(new Fraction("-1"));
		}
		
		return f;
	}

	private List<Fraction> listA = new ArrayList<Fraction>();
	private List<Fraction> listB = new ArrayList<Fraction>();
	private List<Fraction> listM = new ArrayList<Fraction>();
	private List<Fraction> listFa = new ArrayList<Fraction>();
	private List<Fraction> listFb = new ArrayList<Fraction>();
	private List<Fraction> listFm = new ArrayList<Fraction>();
	private List<Fraction> listError = new ArrayList<Fraction>();

	private void clearLists() {
		listA.clear();
		listB.clear();
		listM.clear();
		listFa.clear();
		listFb.clear();
		listFm.clear();
		listError.clear();
	}

	public void process() {
		clearLists();

		boolean achou = false;
		int index = 0;

		listA.add(a);
		listB.add(b);
		listError.add(new Fraction());

		while (!achou) {
			Fraction m = media(listA.get(index), listB.get(index));
			listM.add(m);

			Fraction fA = f.f(listA.get(index));
			listFa.add(fA);

			Fraction fM = f.f(listM.get(index));
			listFm.add(fM);

			Fraction fB = f.f(listB.get(index));
			listFb.add(fB);
			
			if (index > 0) {
				Fraction error = calcError(listM.get(index),
						listM.get(index - 1));
				listError.add(error);
			}
			
			if (index > 18)
				break;

			if (!(fA.isNegative() & fM.isNegative())) {
				listA.add(listA.get(index));
				listB.add(listM.get(index));
			} else {
				listA.add(listM.get(index));
				listB.add(listB.get(index));
			}			

			index++;		
		}
	}

	public String toString() {
		StringBuilder sb = new StringBuilder();

		int cont = listA.size() - 1;
		for (int i = 0; i <= cont; i++) {
			sb.append("a:").append(listA.get(i)).append(" ");
			sb.append("m:").append(listM.get(i)).append(" ");
			sb.append("b:").append(listB.get(i)).append(" ");
			sb.append("fa:").append(listFa.get(i)).append(" ");
			sb.append("fm:").append(listFm.get(i)).append(" ");
			sb.append("fb:").append(listFb.get(i)).append(" ");
			sb.append("erro:").append(listError.get(i)).append("\r\n");
		}

		return sb.toString();
	}

}
