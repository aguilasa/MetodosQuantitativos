package br.furb.mq.newton;

import java.util.*;

public class Newton {
	public static void main(String[] args) {

		int v[];
		v = LePolinomio();
		int i;
		double chute;
		int maxinteracoes;
		double tolerancia;
		Scanner sc = new Scanner(System.in);
		double raiz;

		System.out.println("Digite o numero maximo de iteracoes: ");
		maxinteracoes = sc.nextInt();
		do {
			System.out.println("Digite a toleracia de diferença para a raiz: ");
			tolerancia = sc.nextDouble();
			if (tolerancia < 0)
				System.out.println("Digite uma tolerancia positiva\n");
		} while (tolerancia < 0);

		System.out.println("Digite sua aproximação inicial para a raiz: ");
		chute = sc.nextDouble();

		raiz = chute;

		double control = Fx(v, raiz);
		i = 1;

		while (control > tolerancia) { // /&& control<(-tolerancia)
			raiz = raiz - (Fx(v, raiz) / Flinhax(v, raiz));
			control = Fx(v, raiz);
			if (i == maxinteracoes)
				control = tolerancia;
			i++;
		}

		if (i >= maxinteracoes) {
			System.out
					.println("A 'raiz' mais proxima, usando o maximo de interacoes, é: "
							+ raiz);
		} else {
			System.out.println("A raiz, dentro da margem de tolerancia, é: "
					+ raiz);
		}

		System.out.println("");

	}

	public static double Flinhax(int v[], double a) {
		int i;
		double d = 0;

		for (i = 1; i < v.length; i++) {
			d = d + (i * v[i] * (Math.pow(a, i - 1)));
		}

		return d;
	}

	public static double Fx(int v[], double a) {
		int i;
		double d = 0;

		for (i = 1; i < v.length; i++) {
			d = d + (v[i] * (Math.pow(a, i)));
		}

		d = d + v[0];
		return d;
	}

	public static int[] LePolinomio() {
		Scanner sc = new Scanner(System.in);
		int v[], i;
		int as = 1;
		int g;
		int flag = 0;

		System.out.print("\nPolinomio de Newton\n");

		do {
			System.out.print("\nQual o maior grau do seu polinomio?\n: ");
			g = sc.nextInt();

			if (g < 2)
				System.out
						.println("\nO grau do Polinomio deve ser pelo menos 2\n");
			else
				as = 3;

		} while (as == 1);

		v = new int[g + 1];

		do {
			for (i = g; i > 0; i--) {
				System.out.printf("\nDigite o multiplo 'a' de aX^%d :", i);
				v[i] = sc.nextInt();
			}

			for (int y = g; y > 1; y--) {
				if (v[y] != 0)
					flag = 1;
				if (flag == 0) {
					System.out
							.println("O grau do Polinomio deve ser pelo menos 2\n");
					for (int p = 0; p < v.length; p++)
						v[p] = 0;
				}
			}

		} while (flag == 0);

		System.out.printf("\nDigite o termo independente :");
		v[0] = sc.nextInt();

		return v;
	}
}
