package gaadt;

import gaadt.ontologia.Conceito;
import gaadt.ontologia.Frase;
import gaadt.ontologia.Ontologia;
import gaadt.ontologia.Relacao;
import gaadt.ontologia.Taxonomia;
import gaadt.util.InsereCromossomosThread;
import gaadt.util.Medidor;
import gaadt.util.Util;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class Ambiente {
	
	private Populacao populacao_ancestral;
	private Populacao populacao_descendente;
	private Populacao populacao_intermediaria;
	private ArrayList<Gene> conj_genes = new ArrayList<Gene>();
	private ArrayList<Gene> genes_melhores = new ArrayList<Gene>();
	private static MapaAprendiz mp_aprendiz;
	private static Ontologia ontologia;
	private static double prob_r = 0.40; 
	
	public Ambiente(Ontologia ontologia, Taxonomia taxonomia, MapaAprendiz mp_aprendiz,	int tam_pop_inicial) {
		this.ontologia = ontologia;
		this.mp_aprendiz = mp_aprendiz;
		
		Medidor m2 = new Medidor();
		this.geraGenesAFG(taxonomia);
		this.populacao_ancestral = new Populacao();
		this.populacao_descendente = new Populacao();
		this.populacao_intermediaria = new Populacao();
		
		m2.tempoInicial();
		this.Cruzamento(tam_pop_inicial, this.populacao_ancestral, this.conj_genes);
		//m2.tempoFinal("----Cruzamento Inicial----");
		//this.populacao_ancestral     = Populacao.geraPopulacao(this.conj_genes, tam_pop_inicial);
		//this.RelatorioBaseGenes();
	}
	
	public void geraGenesAFG(Taxonomia taxonomia) {
		Relacao relacoes[] = this.ontologia.getRelacoes();
		
		for(int i = 0; i < relacoes.length ; i++) {
			Frase frases[] = taxonomia.getFrasesCadeia(relacoes[i].getCadeia());
			
			for(int j = 0; j < frases.length; j++) {
		
				Gene gene_d = new Gene(relacoes[i].getConceitoOrigem(),relacoes[i].getConceitoDestino(),frases[j],
						relacoes[i].getCadeia().getDescricao(),new AprendizagemRelacao("Diferenciação Progressiva", "d"));	
				
				this.conj_genes.add(gene_d);
				
		
				Gene gene_r = new Gene(relacoes[i].getConceitoOrigem(),relacoes[i].getConceitoDestino(),frases[j],
						relacoes[i].getCadeia().getDescricao(),new AprendizagemRelacao("Reconciliação Integrativa", "r"));

				this.conj_genes.add(gene_r);
			}
		}
		
		
	}
	

	
	public static Cromossomo geraCromossomoAFC(List<Gene> genes) {
		int tam_cromossomo = mp_aprendiz.getTamanho();
		Cromossomo cromossomo = new Cromossomo();
		
		do {
			cromossomo.getGenes().clear();
			int i = 0;
			

			while(i < tam_cromossomo) {
				Gene gene = getGeneAleatorioPri(genes);				
				if(!cromossomo.getGenes().contains(gene)){
					cromossomo.setGene(gene);
					i++;
				}
			}
		} while(verificaDesconexao(cromossomo.getGenes()) || verificaCiclo(cromossomo.getGenes()) );
		return cromossomo;
	}	
	
	
	private static boolean verificaCiclo(List<Gene> vetor_genes) {
		ArrayList<Gene> genes_descartados = new ArrayList<Gene>();
		
		for(int i=0; i<vetor_genes.size()-1; i++) {
			Conceito a = vetor_genes.get(i).getConceitoOrigem();
			Conceito b = vetor_genes.get(i).getConceitoDestino();
			for(int j=i+1; j<vetor_genes.size(); j++) {
				Conceito c = vetor_genes.get(j).getConceitoOrigem();
				Conceito d = vetor_genes.get(j).getConceitoDestino();
				
				if((a.getDescricao().equalsIgnoreCase(c.getDescricao()) && 
						b.getDescricao().equalsIgnoreCase(d.getDescricao())))
				{

					return true;
				}
			}
		}

		 for(int i=0; i<vetor_genes.size()-1; i++) {
			Conceito a = vetor_genes.get(i).getConceitoOrigem();
			Conceito b = vetor_genes.get(i).getConceitoDestino();
			for(int j=i+1; j<vetor_genes.size(); j++) {
				Conceito c = vetor_genes.get(j).getConceitoOrigem();
				Conceito d = vetor_genes.get(j).getConceitoDestino();
				
				if((a.getDescricao().equalsIgnoreCase(d.getDescricao()) && 
						b.getDescricao().equalsIgnoreCase(c.getDescricao())))
				{
					return true;
				}
			}
		}

		
		 ArrayList<Conceito> conceitos_visitados = new ArrayList<Conceito>();
		
		Conceito c = vetor_genes.get(0).getConceitoOrigem();
		conceitos_visitados.add(c);
		
		for(int i = 0; i < conceitos_visitados.size(); i++) {
			Conceito conceito_visitado = conceitos_visitados.get(i);
			
			for(int j = 0; j < vetor_genes.size(); j++) {
				Gene gene_J = vetor_genes.get(j);
				
				if(genes_descartados.contains(gene_J)) {
                    continue;
                }
				
				if ((conceito_visitado.getDescricao().equals(gene_J.getConceitoOrigem().getDescricao())) &&
						(gene_J.getAprendizagem().getSigla().equals("d"))) {
					boolean contem = false;
					for (int k = 0; k < conceitos_visitados.size(); k++) {
						if (conceitos_visitados.get(k).getDescricao().equalsIgnoreCase(
								gene_J.getConceitoDestino().getDescricao())) {
							contem = true;
							break;
						}
					}
					if (contem) {
						return true;
					}
					else {
						conceitos_visitados.add(gene_J.getConceitoDestino());
						genes_descartados.add(gene_J);
					}
				}
				
				if((conceito_visitado.getDescricao().equals(gene_J.getConceitoDestino().getDescricao())) &&
						(gene_J.getAprendizagem().getSigla().equals("d"))) {
					boolean contem = false;
					for (int k = 0; k < conceitos_visitados.size(); k++) {
						if (conceitos_visitados.get(k).getDescricao().equalsIgnoreCase(
								gene_J.getConceitoOrigem().getDescricao())) {
							contem = true;
							break;
						}
					}
					if (contem) {
						return true;
					}
					else {
						conceitos_visitados.add(gene_J.getConceitoOrigem());
						genes_descartados.add(gene_J);
					}
				}
			}
		}
		return false;
	}
	
	private static boolean verificaDesconexao(List<Gene> vetor_genes) 
	{
		for(int i = 0; i < (vetor_genes.size() - 1); i++) 
		{
			boolean achouLigacao = false;
			int j = i + 1;
			while(!achouLigacao && j < vetor_genes.size()) 
			{
				Gene gene_i = vetor_genes.get(i);
				Gene gene_j = vetor_genes.get(j);
				if (gene_i.getConceitoOrigem().getDescricao().equalsIgnoreCase(gene_j.getConceitoOrigem().getDescricao()) ||
						gene_i.getConceitoOrigem().getDescricao().equalsIgnoreCase(gene_j.getConceitoDestino().getDescricao()) ||
						gene_i.getConceitoDestino().getDescricao().equalsIgnoreCase(gene_j.getConceitoOrigem().getDescricao()) ||
						gene_i.getConceitoDestino().getDescricao().equalsIgnoreCase(gene_j.getConceitoDestino().getDescricao()))
					achouLigacao = true;
				else
					j++;
			}
			if (!achouLigacao) 
				return true;
			} 

		
		int k = 0;
		ArrayList<String> dest = new ArrayList<String>();
		ArrayList<String> org = new ArrayList<String>();
		ArrayList<Gene> genes_arrumados = new ArrayList<Gene>(); 
		
		
		for(int m = 0; m < vetor_genes.size(); m++) {
			Gene g = new Gene();
			if((vetor_genes.get(m).getSimetriaAssimetria().toLowerCase().equals("assimetria")) && 
					(vetor_genes.get(m).getSentidoDaFraseDeEnlace().toLowerCase().equals("sentido_inverso"))) {
				g.setConceitoOrigem(vetor_genes.get(m).getConceitoDestino());
				g.setConceitoDestino(vetor_genes.get(m).getConceitoOrigem());
			}
			else {
				g.setConceitoOrigem(vetor_genes.get(m).getConceitoOrigem());
				g.setConceitoDestino(vetor_genes.get(m).getConceitoDestino());
			}
			g.setFrase(vetor_genes.get(m).getFrase());
			g.setCadeia(vetor_genes.get(m).getCadeia());
			genes_arrumados.add(g);
			g = null;
		}

		for(int d = 0; d < genes_arrumados.size(); d++) { 
			String s = genes_arrumados.get(d).getConceitoDestino().getDescricao().toUpperCase();
			if (dest.indexOf(s)< 0) dest.add(s);
		}
		for(int d = 0; d < genes_arrumados.size(); d++){
		String o = genes_arrumados.get(d).getConceitoOrigem().getDescricao().toUpperCase();
		if (org.indexOf(o)< 0) org.add(o);
		}

		for(int d = 0; d < org.size(); d++) { 
			String s = org.get(d);
			if (dest.indexOf(s)< 0) k++;
		}			
		if (k > 1){
			
			return true;}
		
			
		return false;
	}
		
	public Populacao getPopulacao_descendente() {
		return this.populacao_descendente;
	}
	
	public Populacao getPopulacao_intermediaria() {
		return this.populacao_intermediaria;
	}
	
	public Populacao getPopulacao_ancestral() {
		return this.populacao_ancestral;
	}
	

	public String RelatorioPopulacao(Populacao populacao) {
		String resposta = new String();
		for (int i = 0; i < populacao.getNr_cromossomos(); i++) {
			resposta += "\nCromossomo " + (i+1);
			resposta += populacao.getCromossomo(i).getRelatorio(this.ontologia, this.mp_aprendiz);
		}
		resposta += "\nMédia de adaptação da população é: " + populacao.getMediaAdaptacao(this.ontologia, this.mp_aprendiz);
		return resposta;
	}
	
	public void RelatorioBaseGenes() {
		System.out.println( "Genes  == " + this.conj_genes.size() + " ==");
		for(int i = 0; i < this.conj_genes.size(); i++) {
			Gene gene = (Gene)this.conj_genes.get(i);
			String conceitoOrigem = gene.getConceitoOrigem().getDescricao();
			String conceitoDestino = gene.getConceitoDestino().getDescricao();
			String cadeia = gene.getCadeia();
			String frase = gene.getFrase().getDescricao();
			String aprendizagem = gene.getAprendizagem().getSigla();
			System.out.print( " < " + conceitoOrigem  + "  ,  ");
			System.out.print( " ( " + cadeia + ", " + frase + ", ");
			System.out.print( aprendizagem + " ), " + conceitoDestino + " >");
			System.out.println();
		}
	}
	

	public boolean aplicaSelecao() {
		this.populacao_intermediaria.esvazia();
		Cromossomo[] cromossomos_selecionados = this.populacao_ancestral.selecionaMelhores(this.ontologia, this.mp_aprendiz);
		for(int i = 0 ; i < cromossomos_selecionados.length; i++) {
			this.populacao_intermediaria.insereCromossomo(cromossomos_selecionados[i]);
		}
		
		if(cromossomos_selecionados.length == 0) {
			return true;
		}
		return false;
	}
	public void Fecundacao() {
		Cromossomo ancestral_1;
		Cromossomo ancestral_2;
		this.genes_melhores.clear();
		boolean tamanho_impar = false;
		int tamanho_pop = this.populacao_intermediaria.getNr_cromossomos();
		int controla_laco = tamanho_pop;
		if ((controla_laco % 2) == 1) {
			tamanho_impar = true;
			controla_laco--;
		}
		for(int i = 0; i < controla_laco; i = i+2) {
			ancestral_1 = this.populacao_intermediaria.getCromossomo(i);
			ancestral_2 = this.populacao_intermediaria.getCromossomo(i+1);
			this.identificaDominantes(ancestral_1, ancestral_2);
		}
		if(tamanho_impar) {
			ancestral_1 = this.populacao_intermediaria.getCromossomo(tamanho_pop-1);
			ancestral_2 = this.populacao_intermediaria.getCromossomo(tamanho_pop-1);
			this.identificaDominantes(ancestral_1, ancestral_2);
		}
	}
	
	public void identificaDominantes(Cromossomo ancestral_1, Cromossomo ancestral_2) {
		Cromossomo c1 = new Cromossomo();
		Cromossomo c2 = new Cromossomo();
		String s1;
		String s2;
		String s3;
		String s4;
		String s5;
		for(int i = 0; i < ancestral_1.getTamanho(); i++){
			s1 = ancestral_1.genes.get(i).getConceitoOrigem().getDescricao();
			s2 = ancestral_1.genes.get(i).getConceitoDestino().getDescricao();
			s3 = ancestral_1.genes.get(i).getCadeia();
			s4 = ancestral_1.genes.get(i).getFrase().getDescricao();
			s5 = ancestral_1.genes.get(i).getAprendizagem().getSigla();
			Gene g = new Gene(s1,s2,s4,s3,s5);
			c1.setGene(g);
		}
		for(int i = 0; i < ancestral_2.getTamanho(); i++){
			s1 = ancestral_2.genes.get(i).getConceitoOrigem().getDescricao();
			s2 = ancestral_2.genes.get(i).getConceitoDestino().getDescricao();
			s3 = ancestral_2.genes.get(i).getCadeia();
			s4 = ancestral_2.genes.get(i).getFrase().getDescricao();
			s5 = ancestral_2.genes.get(i).getAprendizagem().getSigla();
			Gene g = new Gene(s1,s2,s4,s3,s5);
			c2.setGene(g);
		}		

		this.mp_aprendiz.genes = this.mp_aprendiz.getGenes();		

		for(int i = 0; i < this.mp_aprendiz.genes.size(); i++){
			Gene g = new Gene();
			g = this.mp_aprendiz.genes.get(i);
			for(int j = 0; j < c1.getTamanho(); j++){
				if((c1.genes.get(j).getConceitoOrigem().getDescricao().equalsIgnoreCase
						(g.getConceitoOrigem().getDescricao())) && 
						((c1.genes.get(j).getConceitoDestino().getDescricao().equalsIgnoreCase
								(g.getConceitoDestino().getDescricao())))&& 
								((c1.genes.get(j).getAprendizagem().getSigla().equalsIgnoreCase
										(g.getAprendizagem().getSigla())))&& 
										((c1.genes.get(j).getCadeia().equalsIgnoreCase
												(g.getCadeia())))&& 
												(!(c1.genes.get(j).getFrase().getDescricao().equalsIgnoreCase
														(g.getFrase().getDescricao())))) {	
				c1.genes.remove(j);
				c1.genes.add(j, g);
		
				
				}
			}
		}
		
		ancestral_1 = c1;

		for(int i = 0; i < this.mp_aprendiz.genes.size(); i++){
			Gene g = new Gene();
			g = this.mp_aprendiz.genes.get(i);
			for(int j = 0; j < c2.getTamanho(); j++){
				if((c2.genes.get(j).getConceitoOrigem().getDescricao().equalsIgnoreCase
						(g.getConceitoOrigem().getDescricao())) && 
						((c2.genes.get(j).getConceitoDestino().getDescricao().equalsIgnoreCase
								(g.getConceitoDestino().getDescricao())))&& 
								((c2.genes.get(j).getAprendizagem().getSigla().equalsIgnoreCase
										(g.getAprendizagem().getSigla())))&& 
										((c2.genes.get(j).getCadeia().equalsIgnoreCase
												(g.getCadeia())))&& 
												(!(c2.genes.get(j).getFrase().getDescricao().equalsIgnoreCase
														(g.getFrase().getDescricao())))) {	
				c2.genes.remove(j);
				c2.genes.add(j, g);
		
				
				}
			}
		}
		
		ancestral_2 = c2;

				
		for(int i = 0; i < ancestral_1.getTamanho(); i++) {
			for(int j = 0; j < ancestral_2.getTamanho(); j++) {
				if(ancestral_1.getGene(i).getConceitoOrigem().getDescricao().equals(ancestral_2.getGene(j).getConceitoOrigem().getDescricao()) ||
						ancestral_1.getGene(i).getConceitoOrigem().getDescricao().equals(ancestral_2.getGene(j).getConceitoDestino().getDescricao()) ||
						ancestral_1.getGene(i).getConceitoDestino().getDescricao().equals(ancestral_2.getGene(j).getConceitoOrigem().getDescricao()) ||
						ancestral_1.getGene(i).getConceitoDestino().getDescricao().equals(ancestral_2.getGene(j).getConceitoDestino().getDescricao())) {
					if(ancestral_1.getGene(i).getAdaptacao(this.ontologia,this.mp_aprendiz) > ancestral_2.getGene(j).getAdaptacao(this.ontologia,this.mp_aprendiz)) {
						if(!genes_melhores.contains(ancestral_1.getGene(i))) {
							this.genes_melhores.add(ancestral_1.getGene(i));
						}
					}
					else {
						if(!genes_melhores.contains(ancestral_2.getGene(j))) {
							this.genes_melhores.add(ancestral_2.getGene(j));
						}
					}
				}
			}
		}
		
	}
		
	public void Cruzamento(int tamanho_pop, Populacao populacao,
			ArrayList<Gene> genes) {
		
		ExecutorService es = Executors.newCachedThreadPool();
		Future<ArrayList<Cromossomo>> parte1;
		if (genes != null) {
			 parte1 = es.submit(new InsereCromossomosThread(genes, 1, tamanho_pop));
		} else {
			parte1 = es.submit(new InsereCromossomosThread(this.genes_melhores, 1,
							tamanho_pop));
		}
		try {
			populacao.insereCromossomos(parte1.get());
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (ExecutionException e) {
			e.printStackTrace();
		}
		es.shutdown();

	}
	

	public void Mutacao(double prob_mutacao) {
		int pos;
		int limite_superior = (int)(this.mp_aprendiz.getTamanho()/2);
		for(int i = 0; i < this.populacao_descendente.getNr_cromossomos(); i++) {
			if(Util.flip(prob_mutacao)) {
				ArrayList<Gene> genes_cromossomo = new ArrayList<Gene>();
				genes_cromossomo.clear();
				for(int j = 0; j < populacao_descendente.getCromossomo(i).getTamanho(); j++) {
					genes_cromossomo.add(populacao_descendente.getCromossomo(i).getGene(j));
				}
				do {
					int numero_trocas = Util.random(1,limite_superior);
					for(int k = 0; k < numero_trocas; k++) {
						Gene gene = new Gene();
						pos = Util.random(0,genes_cromossomo.size()-1);
						boolean ja_existe = false;
						do {
							gene = getGeneAleatorioPri(this.conj_genes);
							if(genes_cromossomo.contains(gene)) {
								ja_existe = true;
							}
							else {
								ja_existe = false;
							}
						}while(ja_existe);
						genes_cromossomo.remove(pos);
						genes_cromossomo.add(pos, gene);
					}
				} while (verificaDesconexao(genes_cromossomo) || verificaCiclo(genes_cromossomo));
				this.populacao_descendente.getCromossomo(i).setGenes(genes_cromossomo);
			}
		}
	}
	
	public void RenovaGeracao() {
		this.populacao_ancestral.esvazia();
		for(int i = 0; i < this.populacao_descendente.getNr_cromossomos(); i++) {
			this.populacao_ancestral.insereCromossomo(this.populacao_descendente.getCromossomo(i));
		}
		this.populacao_descendente.esvazia();
	}
	
	public void mantemMelhor(Ontologia ontologia, MapaAprendiz mp_aprendiz) {
		Cromossomo melhor_populacao_ancestral = populacao_ancestral.getMelhor(ontologia, mp_aprendiz);
		populacao_ancestral.removeCromossomo(populacao_ancestral.getPosicao(melhor_populacao_ancestral));
		populacao_descendente.removeCromossomo(populacao_descendente.getPosicao(populacao_descendente.getPior(ontologia, mp_aprendiz)));
		this.populacao_descendente.insereCromossomo(melhor_populacao_ancestral);
	}
	
	
	public void aplicaEstadoEstacionario(Ontologia ontologia, MapaAprendiz mp_aprendiz, int gap) {
		for (int i = 1; i < gap; i++) {
			Cromossomo melhor_populacao_ancestral = this.populacao_ancestral.getMelhor(ontologia, mp_aprendiz);
			this.populacao_ancestral.removeCromossomo(this.populacao_ancestral.getPosicao(melhor_populacao_ancestral));
			this.populacao_descendente.removeCromossomo(this.populacao_descendente.getPosicao(this.populacao_descendente.getPior(ontologia, mp_aprendiz)));
			this.populacao_descendente.insereCromossomo(melhor_populacao_ancestral);
		}
	}
	
	private static Gene getGeneAleatorioPri(List<Gene> genes) {
		Conceito conceitos[] = ontologia.getConjuntoDeConceitos();
		ArrayList<Gene> genes_encontrados = new ArrayList<Gene>();
		int nr_conceitos = conceitos.length;
		int j = Util.random(0, nr_conceitos-1);
		int k = Util.random(0, nr_conceitos-1,j);
		Conceito c = conceitos[0];
		conceitos[0] = conceitos[j];
		conceitos[j] = c;
		c = conceitos[nr_conceitos-1];
		conceitos[nr_conceitos-1] = conceitos[k];
		conceitos[k] = c;
		while(true) {
			int posConc1 = Util.random(0, nr_conceitos -1);
			int posConc2 = Util.random(0, nr_conceitos -1, posConc1);
			Conceito conceito1 = conceitos[posConc1];
			Conceito conceito2 = conceitos[posConc2];
			genes_encontrados.clear();
			boolean existeGene = ontologia.existeConexao(conceito1, conceito2);
			if(existeGene) {
				for(int i = 0; i < genes.size(); i++) {
					boolean vale_verificar = true;
					if (genes.get(i).getAprendizagem().getSigla().equalsIgnoreCase("r") && !Util.flip(prob_r)) {
						vale_verificar = false;
					}
					else{
					}
					
					if (vale_verificar) {
						Gene gene_da_vez = genes.get(i);
						boolean c_1_e_orig  = gene_da_vez.getConceitoOrigem().getDescricao().equals(conceito1.getDescricao());
						boolean c_2_e_dest  = gene_da_vez.getConceitoDestino().getDescricao().equals(conceito2.getDescricao());
                        
						boolean c_2_e_orig  = gene_da_vez.getConceitoOrigem().getDescricao().equals(conceito2.getDescricao());
						boolean c_1_e_dest  = gene_da_vez.getConceitoDestino().getDescricao().equals(conceito1.getDescricao());
						
						boolean gene_valido = ((c_1_e_orig && c_2_e_dest) || (c_2_e_orig && c_1_e_dest));
						if(gene_valido) {
							genes_encontrados.add(genes.get(i));
						}
					}
				}
			}
			if(genes_encontrados.isEmpty()) {
				continue;
			}
			else {
				int pos_gene_encontrado = Util.random(0,genes_encontrados.size()-1);
				return genes_encontrados.get(pos_gene_encontrado);
			}
		}
	}
	
}