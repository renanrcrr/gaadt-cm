package gaadt.util;

public class Util 
{	
	public static int random(int lim_inf,
			int lim_sup) 
	{
		int intervalo = lim_sup - lim_inf;
		int indice = (int)(Math.random() * intervalo);
		return lim_inf + indice;
	}
	
	public static int random(int lim_inf,
			int lim_sup,
			int excecao) 
	{
		int intervalo = lim_sup - lim_inf;
		int indice = excecao - lim_inf;
		while((lim_inf + indice) == excecao)
			indice = (int)(Math.random() * intervalo);
		return lim_inf + indice;
	}
	
	public static boolean flip(double fator) 
	{
		int n = random(0, 100);
		if(fator == 0)
			return false;
		else if(n <= fator * 100)
			return true;
		return false;
	}
	
}
