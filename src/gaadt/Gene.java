package gaadt;


import gaadt.ontologia.Conceito;
import gaadt.ontologia.Frase;
import gaadt.ontologia.Ontologia;
import gaadt.ontologia.Relacao;

import java.math.BigDecimal;
import java.util.StringTokenizer;
import java.util.Vector;


public class Gene {

	private AprendizagemRelacao aprendizagem;
	private Conceito conceitoOrigem;
	private Conceito conceitoDestino;
	private Frase frase;
	private String cadeia;

	public Gene() {

	}

	public Gene(String conceito_origem, String conceito_destino, String frase,
			String cadeia) {
		Conceito objConceitoOrigem = new Conceito(conceito_origem);
		Conceito objConceitoDestino = new Conceito(conceito_destino);
		Frase objFrase = new Frase(frase);

		this.conceitoOrigem = objConceitoOrigem;
		this.conceitoDestino = objConceitoDestino;
		this.frase = objFrase;
		this.cadeia = cadeia;
	}

	public Gene(String conceito_origem, String conceito_destino, String frase,
			String cadeia, String aprendizagem) {
		Conceito objConceitoOrigem = new Conceito(conceito_origem);
		Conceito objConceitoDestino = new Conceito(conceito_destino);
		Frase objFrase = new Frase(frase);

		this.conceitoOrigem = objConceitoOrigem;
		this.conceitoDestino = objConceitoDestino;
		this.frase = objFrase;
		this.cadeia = cadeia;
		this.aprendizagem = new AprendizagemRelacao(aprendizagem);
	}
	
	

	public Gene(Conceito conceitoOrigem, Conceito conceitoDestino,
			Frase frase, String cadeia,AprendizagemRelacao aprendizagem) {
		this.aprendizagem = aprendizagem;
		this.conceitoOrigem = conceitoOrigem;
		this.conceitoDestino = conceitoDestino;
		this.frase = frase;
		this.cadeia = cadeia;
	}

	public void setConceitoOrigem(Conceito conceito_origem) {
		this.conceitoOrigem = conceito_origem;
	}

	public void setConceitoDestino(Conceito conceito_destino) {
		this.conceitoDestino = conceito_destino;
	}

	public void setFrase(Frase frase) {
		this.frase = frase;
	}

	public void setCadeia(String cadeia) {
		this.cadeia = cadeia;
	}

	public void setAprendizagem(AprendizagemRelacao aprendizagem) {
		this.aprendizagem = aprendizagem;
	}

	public void setAprendizagem(String aprendizagem, String sigla) {
		this.aprendizagem = new AprendizagemRelacao(aprendizagem, sigla);
		;
	}

	public Conceito getConceitoOrigem() {
		return this.conceitoOrigem;
	}

	public Conceito getConceitoDestino() {
		return this.conceitoDestino;
	}

	public Frase getFrase() {
		return this.frase;
	}

	public String getCadeia() {

		return this.cadeia;
	}

	public String getSimetriaAssimetria() {
		StringTokenizer tokens = new StringTokenizer(cadeia, ".");
		return tokens.nextToken();
	}

	public String getCadeia_reduzida() {
		StringTokenizer tokens = new StringTokenizer(cadeia, ".");
		Vector<String> s = new Vector<String>();
		String d, r;
		d = "";
		r = "";
		while (tokens.hasMoreTokens()) {
			d = tokens.nextToken();
			if ((d.equalsIgnoreCase("sentido_inverso"))
					|| (d.equalsIgnoreCase("sentido_direto")))
				break;
			s.add(d);
		}
		for (int i = 0; i < s.size(); i++) {
			if (i == s.size() - 1) {
				r = r + s.get(i);
				break;
			}
			r = r + s.get(i) + ".";
		}

		return r;
	}
	public String getSentidoDaFraseDeEnlace() {
		String ultimoToken = new String();
		StringTokenizer tokens = new StringTokenizer(cadeia, ".");
		while (tokens.hasMoreTokens()) {
			ultimoToken = tokens.nextToken();
		}
		return ultimoToken;
	}

	public AprendizagemRelacao getAprendizagem() {
		return this.aprendizagem;
	}

	public int getAlfa(Ontologia ontologia) {
		Relacao relacoes[] = ontologia.getRelacoes();
		for (int i = 0; i < relacoes.length; i++) {
			Relacao relacao = relacoes[i];
			boolean temConceitoOrigem = this.conceitoOrigem.getDescricao()
					.equalsIgnoreCase(
							relacao.getConceitoOrigem().getDescricao())
					|| this.conceitoOrigem.getDescricao().equalsIgnoreCase(
							relacao.getConceitoDestino().getDescricao());
			boolean temConceitoDestino = this.conceitoDestino.getDescricao()
					.equalsIgnoreCase(
							relacao.getConceitoOrigem().getDescricao())
					|| this.conceitoDestino.getDescricao().equalsIgnoreCase(
							relacao.getConceitoDestino().getDescricao());
			boolean temMesmoSupertimo = this.cadeia.equalsIgnoreCase(relacao
					.getCadeia().getDescricao());

			if ((temConceitoOrigem || temConceitoDestino) && temMesmoSupertimo) {
				Frase[] frases = relacao.getCadeia().getFrases();
				for (int j = 0; j < frases.length; j++) {
					if (this.frase.getDescricao().trim()
							.equalsIgnoreCase(frases[j].getDescricao().trim())) {
						return 2;
					}
				}
			}
		}
		return 1;
	}

	
	public int getBeta(MapaAprendiz mpAprendiz) {
		for (int i = 0; i < mpAprendiz.getTamanho(); i++) {
			String cadeia_reduzida = new String();
			String g_cadeia_reduzida = new String();
			boolean temMesmoSupertipo;
			Gene g = mpAprendiz.getGene(i);

			boolean temConceitoOrigem = this.conceitoOrigem.getDescricao()
					.equalsIgnoreCase(g.getConceitoOrigem().getDescricao())
					|| this.conceitoOrigem.getDescricao().equalsIgnoreCase(
							g.getConceitoDestino().getDescricao());
			boolean temConceitoDestino = this.conceitoDestino.getDescricao()
					.equalsIgnoreCase(g.getConceitoOrigem().getDescricao())
					|| this.conceitoDestino.getDescricao().equalsIgnoreCase(
							g.getConceitoDestino().getDescricao());

			if (((this.getSentidoDaFraseDeEnlace()
					.equalsIgnoreCase("sentido_inverso")) && (g
					.getSentidoDaFraseDeEnlace()
					.equalsIgnoreCase("sentido_direto")))
					|| ((this.getSentidoDaFraseDeEnlace()
							.equalsIgnoreCase("sentido_direto")) && (g
							.getSentidoDaFraseDeEnlace()
							.equalsIgnoreCase("sentido_inverso")))) {
				cadeia_reduzida = this.getCadeia_reduzida();
				g_cadeia_reduzida = g.getCadeia_reduzida();
				temMesmoSupertipo = cadeia_reduzida
						.equalsIgnoreCase(g_cadeia_reduzida);
			} else {
				temMesmoSupertipo = this.cadeia.equalsIgnoreCase(g.getCadeia());
			}
		
			if ((temConceitoOrigem && temConceitoDestino) && temMesmoSupertipo) {
		
				return 2;
		
			}
		}
		return 1;
	}

	
	public int getAdaptacao(Ontologia ontologia, MapaAprendiz mpAprendiz) {
		int grau = this.getAlfa(ontologia) + (2 * this.getBeta(mpAprendiz));
		return grau;
	}

	
	public String getRelatorio(Ontologia ontologia, MapaAprendiz mpAprendiz) 
	{
	
			return	"\n" 
				+ "     {" + this.getConceitoOrigem().getDescricao() + ", "
				+ this.getCadeia() + "." + this.getFrase() + ", " 
				+ this.getConceitoDestino().getDescricao() + "}\n"
				+ "     Tipo de aprendizagem: {" + this.getAprendizagem().getSigla() 
				+ " - " + buscarAprendizagem(this.getAprendizagem().getSigla()) + "}\n"
				+ "     Conceitos usados: {" + this.getConceitoOrigem().getDescricao() + ", " + 
				this.getConceitoDestino().getDescricao() + "}\n" 
				+ "     Dimensão semântica: {" + this.getCadeia() + "}\n"
				+ "     Frase usada: {" + this.getFrase() + "}\n";			
	}
	
	
	public boolean FrasesIguais(Gene gene) {
		if (frase == null) {
			if (gene.frase != null)
				return false;
		} else if (!frase.equals(gene.frase))
			return false;
		return true;
	}
	
	
	public boolean ConceitosIguais(Gene gene) {
		boolean conceitosDestinoIguais = true;
		boolean conceitoOrigemIguais = true;
		
		if (conceitoDestino == null) {
			if (gene.conceitoDestino != null)
				conceitosDestinoIguais =  false;
		} else if (!conceitoDestino.equals(gene.conceitoDestino))
			conceitosDestinoIguais = false;

		if (conceitoOrigem == null) {
			if (gene.conceitoOrigem != null)
				conceitoOrigemIguais = false;
		} else if (!conceitoOrigem.equals(gene.conceitoOrigem))
			conceitoOrigemIguais = false;
		return (conceitoOrigemIguais && conceitosDestinoIguais);
	}

	
	@Override
	public boolean equals(Object obj) {
		if (getClass() != obj.getClass())
			return false;
		Gene other = (Gene) obj;

		if (conceitoDestino == null) {
			if (other.conceitoDestino != null)
				return false;
		} else if (!conceitoDestino.equals(other.conceitoDestino))
			return false;

		if (conceitoOrigem == null) {
			if (other.conceitoOrigem != null)
				return false;
		} else if (!conceitoOrigem.equals(other.conceitoOrigem))
			return false;

		if (frase == null) {
			if (other.frase != null)
				return false;
		} else if (!frase.equals(other.frase))
			return false;

		return true;
	}

	
	public String toString(int i) {
		return "     Proposição " + i + ":\n" 
				+ "     {" + this.conceitoOrigem.toString() + ", "
				+ this.cadeia.toString() + "." + this.frase.toString() + ", " 
				+ this.conceitoDestino.toString() + "}\n"
				+ "     Tipo de aprendizagem: {" + this.aprendizagem.toString() 
				+ " - " + buscarAprendizagem(this.aprendizagem.toString()) + "}\n"
				+ "     Conceitos usados: {" + this.conceitoOrigem.toString() + ", " + 
				this.conceitoDestino.toString() + "}\n" 
				+ "     Dimensão semântica: {" + this.cadeia.toString() + "}\n"
				+ "     Frase usada: {" + this.frase.toString() + "}\n";				
	}
	
	private double qtdGenesIncorretos;
	
	public double getGenesIncorretos()
	{
		return this.qtdGenesIncorretos;
	}
	
	
	public String getRelatorioDimensaoSemantica(Ontologia ontologia, int i, String descricaoFrase) 
	{
		if(ontologia.existeConexao(this.conceitoOrigem, this.conceitoDestino))
		{
			if(ontologia.existeRelacaoCadeia(this.cadeia) && frase.getDescricao().equalsIgnoreCase(descricaoFrase))
			{
				qtdGenesIncorretos += 1.0;
				return "     Relação = (MCest, MCag) = 2" + 
				" (Os conceitos " + this.conceitoOrigem + " e " + this.conceitoDestino + " da proposição " 
				+ i + " do MCest, possuem uma relação semântica entre conceitos e frases de ligação com os MCag gerados)\n";
			}
			qtdGenesIncorretos += 0.5;
			return "     Relação = (MCest, MCag) = 1" + 
			" (Os conceitos " + this.conceitoOrigem + " e " + this.conceitoDestino + " da proposição " 
			+ i + " do MCest, possuem uma relação semântica entre conceitos com os MCag gerados, mas não possui a mesma frase de ligação utilizada)\n";			
		}
		else
		{
			qtdGenesIncorretos += 0.0;
			return "     Relação = (MCest, MCag) = 0" + 
			" (Os conceitos " + this.conceitoOrigem + " e " + this.conceitoDestino + " da proposição " 
			+ i + " do MCest, não possuem uma relação semântica com os MCag gerados)\n";
		}
										
	}
	
	public String buscarAprendizagem(String aprendizagem)
	{
		if(aprendizagem.equals("d"))
			return "diferenciação progressiva";
		else
			return "reconciliação progressiva";
	}
	
	
	public void numeroDeAcertos()
	{
		
	}

}