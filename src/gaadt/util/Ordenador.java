package gaadt.util;

import java.util.Arrays;

public abstract class Ordenador {
	
	public static void ordena(double vetor[]) {
		Arrays.sort(vetor);
	}
	
	public static void ordena(double vetor[],
			int lim_inf,
			int lim_sup) {
		Arrays.sort(vetor, lim_inf, lim_sup);
	}
	
}
