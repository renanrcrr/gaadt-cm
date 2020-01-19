package gaadt.xmltools;

public class TokenDeFrase extends Token {
	
	private String texto;
	private String supertipo;
	private String id;
	
	public TokenDeFrase(String texto,
			String supertipo,
			String id) {
		this.texto = texto;
		this.supertipo = supertipo;
		this.id = id;
	}
	
	public String texto() {
		return this.texto;
	}
	
	public String supertipo() {
    	return this.supertipo;
    }
	
	public String id() {
		return this.id;
	}
	
}