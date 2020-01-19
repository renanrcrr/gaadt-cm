package gaadt.xmltools;

public class TokenDeConceito extends Token {
	
	private String texto;
	private String id;
	
	public TokenDeConceito(String texto, String id) {
		this.texto = texto;
		this.id = id;
	}
	
	public String texto() {
		return this.texto;
	}
	
	public String id() {
		return this.id;
	}
	
}