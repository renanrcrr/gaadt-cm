package gaadt.ontologia;

import java.util.ArrayList;

public class Cadeia {
	
	private String descricao;
	
	private ArrayList<Frase> frases;
	
	public Cadeia(String descricao) {
		this.descricao = descricao;
		this.frases = new ArrayList<Frase>();
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	
	public Frase[] getFrases() {
		return frases.toArray(new Frase[frases.size()]);
	}
	
	public void addFrase(Frase frase) {
		if(!this.frases.contains(frase))
			this.frases.add(frase);
	}
	
	public String[] getPartes() {
		return this.descricao.split("[.]");
    }
	
	public String toString() {
		String resposta = this.descricao;
		for(int i = 0; i < this.frases.size(); i++)
			resposta = resposta + "\n" + ((Frase)this.frases.get(i)).toString();
		return resposta;
	}
	
}