package gaadt.ontologia;

public class Relacao {
	
	private Conceito conceito_origem;
	private Conceito conceito_destino;
	private Cadeia cadeia;
	public Relacao(Conceito conceito_origem,
			Conceito conceito_destino,
			Cadeia cadeia) {
		this.conceito_origem = conceito_origem;
		this.conceito_destino = conceito_destino;
		this.cadeia = cadeia;
	}
	public Conceito getConceitoOrigem() {
		return this.conceito_origem;
	}
	public Conceito getConceitoDestino() {
		return this.conceito_destino;
	}
	public Cadeia getCadeia(){
		return this.cadeia;
	}
	public boolean ConceitosIguais(Relacao relacao) {
		boolean conceitosDestinoIguais = true;
		boolean conceitoOrigemIguais = true;
		
		if (conceito_destino == null) {
			if (relacao.conceito_destino != null)
				conceitosDestinoIguais =  false;
		} else if (!conceito_destino.equals(relacao.conceito_destino))
			conceitosDestinoIguais = false;

		if (conceito_origem == null) {
			if (relacao.conceito_origem != null)
				conceitoOrigemIguais = false;
		} else if (!conceito_origem.equals(relacao.conceito_origem))
			conceitoOrigemIguais = false;
		return (conceitoOrigemIguais && conceitosDestinoIguais);
	}
	public String toString() {
		String resposta = this.conceito_origem.toString() + " - " + this.conceito_destino.toString();
		resposta = resposta + "\n" + this.cadeia.toString();
		return resposta;
	}
	
}