package gaadt;

public class AprendizagemRelacao {
	
	private String nome;
	private String sigla;
	
	public AprendizagemRelacao(String nome,
			String sigla) {
		this.nome = nome;
		this.sigla = sigla;
	}
	
	public AprendizagemRelacao(String sigla) {
		this.nome = "";
		this.sigla = sigla;
	}
	
	public String getNome() {
		return this.nome;
	}
	
	public String getSigla() {
		return this.sigla;
	}
	
	public String toString() {
		return this.sigla;
	}
	
}