package gaadt;

import gaadt.ontologia.Conceito;
import gaadt.ontologia.Ontologia;
import gaadt.util.Util;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;

public class Cromossomo {
	
	protected List<Gene> genes = new LinkedList<Gene>();
	
	
	public void setGene(Gene gene) {
		this.genes.add(gene);
	}
	
	public boolean existeGene(Gene g){
		boolean existe_no_cromossomo = false;
		for (int j = 0; j < this.getTamanho(); j++) {
			if(g.equals(this.getGene(j))) {
				existe_no_cromossomo = true;
				break;
			}		
		}
		
		
		return existe_no_cromossomo;
	}

	
	public void removeGene(int pos) {
		if (pos < this.genes.size())
			this.genes.remove(pos);
	}
	
	
	public int getTamanho() {
		return this.genes.size();
	}
	
	
	public List<Gene> getGenes() {
		return this.genes;
	}
	
	public void setGenes(ArrayList<Gene> conj_genes) {
		this.genes.clear();
		this.genes.addAll(conj_genes);
	}
	
	public Conceito getConceitoAleatorio() {
		int posCromossomo = Util.random(0, this.genes.size() - 1);
		boolean posConceito = Util.flip(0.5);
		if (posConceito)
			return this.genes.get(posCromossomo).getConceitoOrigem();
		else
			return this.genes.get(posCromossomo).getConceitoDestino();
	}
	
	public Gene getGene(int i) {
		if (i < this.genes.size())
			return this.genes.get(i);
		else
			return null;
	}
	
	public boolean existeConexao(Conceito conceito_1,
			Conceito conceito_2) {
		for (int i = 0; i < this.genes.size(); i++) {
			Gene gene = this.genes.get(i);
			if ((gene.getConceitoOrigem().getDescricao().equalsIgnoreCase(conceito_1.getDescricao()) &&
					gene.getConceitoDestino().getDescricao().equalsIgnoreCase(conceito_2.getDescricao())) ||
					(gene.getConceitoOrigem().getDescricao().equalsIgnoreCase(conceito_2.getDescricao()) &&
					gene.getConceitoDestino().getDescricao().equalsIgnoreCase(conceito_1.getDescricao()))) 
				return true;
		}
		return false;
	}
	
	public boolean existeConceito(Conceito conceito) {
		for(int i = 0; i < this.genes.size(); i++) {
			Gene gene = this.genes.get(i);
			if(conceito.getDescricao().equalsIgnoreCase(gene.getConceitoOrigem().getDescricao()) ||
					conceito.getDescricao().equalsIgnoreCase(gene.getConceitoDestino().getDescricao())) {
				return true;
			}
		}
		return false;
	}
	
	public boolean existeConceito(String conceito) {
		for(int i = 0; i < this.genes.size(); i++) {
			Gene gene = this.genes.get(i);
			if(conceito.equalsIgnoreCase(gene.getConceitoOrigem().getDescricao()) ||
					conceito.equalsIgnoreCase(gene.getConceitoDestino().getDescricao())) {
				return true;
			}
		}
		return false;
	}
	
	private int getPeso(MapaAprendiz mpAprendiz,
			Gene gene) {
		int peso = 0;
		if(mpAprendiz.existeConceito(gene.getConceitoOrigem()))
			peso++;
		if(mpAprendiz.existeConceito(gene.getConceitoDestino()))
			peso++;
		if ((peso == 2) &&
				(mpAprendiz.existeConexao(gene.getConceitoOrigem(), gene.getConceitoDestino())))
			peso++;
		return peso;
	}
	
	
	public int getAdaptacao(Ontologia ontologia,
			MapaAprendiz mpAprendiz) {
		int somatorio = 0;
		int diferenca = 0;
		int adaptacao = 0;
		int grau = 0;
	

		for(int i = 0; i < this.genes.size(); i++) {
			//Gene g = new Gene();
			Gene g = this.genes.get(i);
			int peso = this.getPeso(mpAprendiz, g);
			grau = g.getAdaptacao(ontologia, mpAprendiz);

				somatorio += peso * grau;
		}
		
		Conceito[] conceitos_mp = mpAprendiz.getConjuntoDeConceitos();
		diferenca = 0;
		for (int k = 0; k < conceitos_mp.length; k++){
			if (!(this.existeConceito(conceitos_mp[k].getDescricao()))) {
				diferenca++;
			}
		}
		adaptacao = somatorio - diferenca;
	
		return adaptacao;
	}
	
	public String getRelatorio(Ontologia ontologia,
			MapaAprendiz mpAprendiz) {
		String resposta = new String();
		for (int i = 0; i < this.getTamanho(); i++)
		{
			resposta += "\n     Proposição " + i + ":" + this.genes.get(i).getRelatorio(ontologia,mpAprendiz);
		
		}
		
		resposta += "\n\r     Valor de adaptação do MC = " + this.getAdaptacao(ontologia, mpAprendiz) 
				+ "\n______________________________________________________________________________________________________";
		return resposta;
	}
	
	public Conceito[] getConjuntoDeConceitos() {
		Vector<Conceito> conceitos = new Vector<Conceito>();
		for(int i = 0; i < this.genes.size(); i++) {
			Gene gene = this.genes.get(i);
			Conceito conceito_origem = gene.getConceitoOrigem();
			Conceito conceito_destino = gene.getConceitoDestino();
			boolean encontrou_conceito_orig = false;
			boolean encontrou_conceito_dest = false;
			
			int k = 0;
			while(k < conceitos.size() &&
					!(encontrou_conceito_orig &&
							encontrou_conceito_dest)) {
				if (conceitos.get(k).getDescricao().equalsIgnoreCase(conceito_origem.getDescricao())) {
					encontrou_conceito_orig = true;
				}
				if (conceitos.get(k).getDescricao().equalsIgnoreCase(conceito_destino.getDescricao())) {
					encontrou_conceito_dest = true;
				}
				k++;
			}
			
			if(!encontrou_conceito_orig) {
				conceitos.add(conceito_origem);
			}
			if(!encontrou_conceito_dest) {
				conceitos.add(conceito_destino);
			}
		}
		return conceitos.toArray(new Conceito[conceitos.size()]);
	}
	
	public int getNumeroGenes()
	{
		return this.qtdGenes;
	}
	public Gene getUltimoGene()
	{
		return this.ultimoGene;
	}
	
	public double getQtdGenesIncorretos()
	{
		Gene g = getUltimoGene();
		double result = g.getGenesIncorretos();
		System.out.println("Valor semantico de genes considerados incorretos: " + result);
		return result;
	}
	
	public int getQtdRelacoesOntologia()
	{
		return this.qtdRelacoesOntologia;
	}
	
	public String toString() {
		String  resposta = new String();
		for(int i = 0; i < this.genes.size(); i++)
			resposta = resposta + this.genes.get(i).toString(i) + "\n";
		return resposta;
	}
	
	private String retornaCadeia;
	private int qtdGenes;
	private Gene ultimoGene;
	private int qtdRelacoesOntologia;
	private String descricaoFrase;
	
	public String concatenarString(Ontologia o)
	{
		String  resposta = new String();
		for(int i = 0; i < this.genes.size(); i++)
		{
			this.retornaCadeia = this.genes.get(i).getCadeia();
			this.qtdGenes = this.genes.size();
			descricaoFrase = this.genes.get(i).getFrase().getDescricao();
			resposta = resposta + this.genes.get(i).getRelatorioDimensaoSemantica(o, i, descricaoFrase) + "\n";
			int u = ((this.genes.size())-1);
			this.ultimoGene = this.genes.get(u);
			qtdRelacoesOntologia = o.getRelacoes().length;	
			System.out.println("Relacoes: " + qtdRelacoesOntologia);
			
		}
			
		return resposta;
	}
	
}