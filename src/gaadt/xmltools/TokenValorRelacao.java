package gaadt.xmltools;

import java.util.Vector;


public class TokenValorRelacao extends Token {
	

	private String cadeia;

	private Vector<String> frases;
	
	private String id;
	
	public TokenValorRelacao(String id,
			String cadeia) {
		this.cadeia = cadeia;
		this.frases = new Vector<String>();
		this.id = id;
	}
	
	public String getId() {
		return this.id;
	}
	
	public String getCadeia() {
		return this.cadeia;
	}
	
	public String[] getFrases() {
		return frases.toArray(new String[frases.size()]);
	}
	
	public void addFrase(String frase) {
		if(!this.frases.contains(frase))
			this.frases.add(frase);
	}
	
}