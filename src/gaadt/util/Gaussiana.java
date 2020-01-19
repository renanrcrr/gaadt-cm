package gaadt.util;

public class Gaussiana {
	
	private double dominio[];
	
	public Gaussiana(double vetor[]) {
		this.dominio = vetor;
	}
	
	private double getMedia() {
		double media = 0;
		double somatorio = 0;
		int N = this.dominio.length;
		for(int x = 0; x < N; x++)
			somatorio = somatorio + this.dominio[x];
		media = somatorio/N;
		return media;
	}
	
	private double getVariancia(double media) {
		double variancia = 0;
		double somatorio = 0;
		int N = this.dominio.length;
		for(int x = 0; x < N; x++)
			somatorio = somatorio + Math.pow(this.dominio[x], 2);
		variancia = (somatorio - N * Math.pow(media, 2)) / (N - 1);
		return variancia;
	}
	
	public double[] getDistribuicao(){
		int N = this.dominio.length;
		double media = getMedia();
		double variancia = getVariancia(media);
		double probabilidade[] = new double[N];
		double denominador = Math.sqrt(2 * Math.PI * variancia);
		for(int x = 0; x < N; x++) {
			double expoente = (-1) * (Math.pow((this.dominio[x] - media), 2) / (2 * variancia));
			probabilidade[x] = (1 / denominador) * Math.exp(expoente);
		}
		return probabilidade;
	}
	public String getRelatorio() {
		String resposta = new String("");
		int N = this.dominio.length;
		double probabilidade[] = getDistribuicao();
		resposta += " Media = " + getMedia() + " ;\n ";
		resposta += " Variancia = " + getVariancia(getMedia()) + " ;\n ";
		resposta += " x=[ ";
		for(int x = 0; x < N; x++)
			resposta += String.valueOf(this.dominio[x]) + " ";
		resposta += " ];\n ";
		resposta += " y=[ ";
		for(int x = 0; x < N; x++)
			resposta += String.valueOf(probabilidade[x]) + " ";
		resposta += " ];\n ";
		return resposta;
	}
	
}