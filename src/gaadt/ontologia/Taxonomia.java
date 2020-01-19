package gaadt.ontologia;

import java.util.ArrayList;


public class Taxonomia {
	
	private ArrayList<Cadeia> cadeias;
	
	public Taxonomia() {
		this.cadeias = new ArrayList<Cadeia>();
	}
	
	public Cadeia[] getCadeias() {
		return cadeias.toArray(new Cadeia[cadeias.size()]);
	}
	
	public void addCadeia(Cadeia cadeia){
		if(!this.cadeias.contains(cadeia))
			this.cadeias.add(cadeia);
	}
	
	public Frase[] getFrasesCadeia(Cadeia cadeia_chave) {
		return cadeia_chave.getFrases();
	}
	
	public String toString() {
		String resposta = null;
		Cadeia[] cadeias = this.getCadeias();
		for(int i = 0; i < cadeias.length; i++) {
			resposta = resposta + "\n" + i + ") ___________________\n" + cadeias[i].toString();
		}
		return resposta;
	}
	
}