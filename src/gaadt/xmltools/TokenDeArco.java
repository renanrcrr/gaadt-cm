package gaadt.xmltools;

public class TokenDeArco extends Token {
	
	private String tipo;
	private String origem;
	private String alvo;
	
	public TokenDeArco(String tipo,
			String origem,
			String alvo) {
		this.tipo = tipo;
		this.origem = origem;
		this.alvo = alvo;
	}
	
	public String tipo() {
		if (this.tipo.equals("0"))
			return "d";
		return "r";
	}
	
	public String origem() {
		return this.origem;
	}
	
	public String alvo() {
		return this.alvo;
	}
	
}