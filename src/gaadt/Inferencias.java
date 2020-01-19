package gaadt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import gaadt.ontologia.Frase;


public class Inferencias 
{
	
	static Map<Integer,String> m = new HashMap<Integer,String>();
	
	public static void silogismoHipotetico(List<Gene> conj_genes)
	{
		
	}
	
	
	public static void inferirGeneInverso(ArrayList<Gene> conj_genes)
	{
		ArrayList<Gene> conj_auxiliarInverso = new ArrayList<Gene>();
		
		for(int i = 0; i < conj_genes.size() ; i++)
		{
			Gene geneLista = conj_genes.get(i);
			
			Gene gene_inverso_d = new Gene();			
			
			gene_inverso_d.setConceitoOrigem(geneLista.getConceitoDestino());
			gene_inverso_d.setConceitoDestino(geneLista.getConceitoOrigem());
			
			if(geneLista.getAprendizagem().getNome().equals("Diferencia��o Progressiva") &&
					geneLista.getAprendizagem().getSigla().equals("d"))
				gene_inverso_d.setAprendizagem("Diferencia��o Progressiva", "d");
			else if(geneLista.getAprendizagem().getNome().equals("Reconcilia��o Integrativa") &&
					geneLista.getAprendizagem().getSigla().equals("r"))
				gene_inverso_d.setAprendizagem("Reconcilia��o Integrativa", "r");
			
			setFraseCadeia(geneLista, gene_inverso_d);
			
			if(!(conj_auxiliarInverso.contains(gene_inverso_d)))
				conj_auxiliarInverso.add(gene_inverso_d);					
		}
		for(int j = 0; j < conj_auxiliarInverso.size(); j++)
		{
			Gene geneAuxiliar = conj_auxiliarInverso.get(j);
			conj_genes.add(conj_genes.size(), geneAuxiliar);
		}
		

		conj_auxiliarInverso = null;
	}
	
	public static void setFraseCadeia(Gene geneLista, Gene gene_inverso)
	{
		if(geneLista.getCadeia().equals("assimetria.temporalidade.ordena��o.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.temporalidade.ordena��o.sentido_direto");
			
				Frase[] t = new Frase[8];
				t[0] = new Frase("ocorre antes de");
				t[1] = new Frase("precede");
				t[2] = new Frase("antecede");
				t[3] = new Frase("� anterior a");
				t[4] = new Frase("� primeiro que");
				t[5] = new Frase("existe antes que");
				t[6] = new Frase("� antes de");
				t[7] = new Frase("� sucedido por");
				
				if((geneLista.getFrase().getDescricao().equals("ocorre depois de")))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("� precedido por"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("� antecedido por"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("sucede"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("segue"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("� posterior que"))
					gene_inverso.setFrase(t[5]);
				else if(geneLista.getFrase().getDescricao().equals("existe depois de"))
					gene_inverso.setFrase(t[6]);
				else if(geneLista.getFrase().getDescricao().equals("� depois de"))
					gene_inverso.setFrase(t[7]);
		}
		else if(geneLista.getCadeia().equals("assimetria.temporalidade.ordena��o.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.temporalidade.ordena��o.sentido_inverso");
			
				Frase[] t = new Frase[8];
				t[0] = new Frase("ocorre depois de");
				t[1] = new Frase("� precedido por");
				t[2] = new Frase("� antecedido por");
				t[3] = new Frase("sucede");
				t[4] = new Frase("segue");
				t[5] = new Frase("� posterior que");
				t[6] = new Frase("existe depois de");
				t[7] = new Frase("� depois de");
				
				if((geneLista.getFrase().getDescricao().equals("ocorre antes de")))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("precede"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("antecede"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("� anterior a"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("� primeiro que"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("existe antes que"))
					gene_inverso.setFrase(t[5]);
				else if(geneLista.getFrase().getDescricao().equals("� antes de"))
					gene_inverso.setFrase(t[6]);
				else if(geneLista.getFrase().getDescricao().equals("� sucedido por"))
					gene_inverso.setFrase(t[7]);
		}
		else if(geneLista.getCadeia().equals("assimetria.processo.transforma��o.controle_interno.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.processo.transforma��o.controle_interno.sentido_inverso");
			
				Frase[] t = new Frase[7];
				t[0] = new Frase("� transforma��o de");
				t[1] = new Frase("� convers�o de");
				t[2] = new Frase("� altera��o de");
				t[3] = new Frase("� muta��o de");
				t[4] = new Frase("� modifica��o de");
				t[5] = new Frase("resulta em");
				t[6] = new Frase("� consequ�ncia de");
				
				if((geneLista.getFrase().getDescricao().equals("� transformado em")))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("� convertido em"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("� alterado em"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("� mutado em"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("� modificado em"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("resulta de"))
					gene_inverso.setFrase(t[5]);
				else if(geneLista.getFrase().getDescricao().equals("tem como consequ�ncia"))
					gene_inverso.setFrase(t[6]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.processo.transforma��o.controle_interno.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.processo.transforma��o.controle_interno.sentido_direto");
			
				Frase[] t = new Frase[7];
				t[0] = new Frase("� transformado em");
				t[1] = new Frase("� convertido em");
				t[2] = new Frase("� alterado em");
				t[3] = new Frase("� mutado em");
				t[4] = new Frase("� modificado em");
				t[5] = new Frase("resulta em");
				t[6] = new Frase("tem como consequ�ncia");
				
				if(geneLista.getFrase().getDescricao().equals("� transforma��o de"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("� convers�o de") || geneLista.getFrase().getDescricao().equals("prov�m de"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("� altera��o de"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("� muta��o de")) 
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("� modifica��o de"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("resulta de") || geneLista.getFrase().getDescricao().equals("surge de") || geneLista.getFrase().getDescricao().equals("tem como origem"))
					gene_inverso.setFrase(t[5]);
				else if(geneLista.getFrase().getDescricao().equals("� consequ�ncia de"))
					gene_inverso.setFrase(t[6]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.processo.sa�da.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.processo.sa�da.sentido_inverso");
			
				Frase[] t = new Frase[8];
				t[0] = new Frase("� gerado por");
				t[1] = new Frase("� causado por");
				t[2] = new Frase("� produzido por");
				t[3] = new Frase("� provocado por");
				t[4] = new Frase("� resultado de");
				t[5] = new Frase("� formado por");
				t[6] = new Frase("� consequ�ncia de");
				t[7] = new Frase("� sa�da de");
				
				if((geneLista.getFrase().getDescricao().equals("gera")))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("causa"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("produz"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("provoca"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("resulta em") || geneLista.getFrase().getDescricao().equals("tem como resultado"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("forma"))
					gene_inverso.setFrase(t[5]);				
				else if(geneLista.getFrase().getDescricao().equals("tem como consequ�ncia"))
					gene_inverso.setFrase(t[6]);
				else if(geneLista.getFrase().getDescricao().equals("tem como sa�da"))
					gene_inverso.setFrase(t[7]);
		}
		else if(geneLista.getCadeia().equals("assimetria.processo.sa�da.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.processo.sa�da.sentido_direto");
			
				Frase[] t = new Frase[8];
				t[0] = new Frase("gera");
				t[1] = new Frase("causa");
				t[2] = new Frase("produz");
				t[3] = new Frase("provoca");
				t[4] = new Frase("resulta em");
				t[5] = new Frase("forma");
				t[6] = new Frase("tem como consequ�ncia");
				t[7] = new Frase("tem como sa�da");
				
				if((geneLista.getFrase().getDescricao().equals("� gerado por")))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("� causado por"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("� produzido por"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("� provocado por"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("� resultado de"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("� formado por"))
					gene_inverso.setFrase(t[5]);				
				else if(geneLista.getFrase().getDescricao().equals("� consequ�ncia de"))
					gene_inverso.setFrase(t[6]);
				else if(geneLista.getFrase().getDescricao().equals("� sa�da de"))
					gene_inverso.setFrase(t[7]);
		}
		else if(geneLista.getCadeia().equals("assimetria.processo.entrada.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.processo.saida.sentido_inverso");
			
				Frase[] t = new Frase[7];
				t[0] = new Frase("� usado por");
				t[1] = new Frase("� entrada de");
				t[2] = new Frase("� utilizado por");
				t[3] = new Frase("� transformado por");
				t[4] = new Frase("� convertido por");
				t[5] = new Frase("� alterado por");
				t[6] = new Frase("� modificado por");
				
				if(geneLista.getFrase().getDescricao().equals("usa") || geneLista.getFrase().getDescricao().equals("faz uso de"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("usa como entrada"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("utiliza") || geneLista.getFrase().getDescricao().equals("utiliza como entrada"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("transforma"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("converte"))
					gene_inverso.setFrase(t[4]);				
				else if(geneLista.getFrase().getDescricao().equals("altera"))
					gene_inverso.setFrase(t[5]);
				else if(geneLista.getFrase().getDescricao().equals("modifica"))
					gene_inverso.setFrase(t[6]);
		}
		else if(geneLista.getCadeia().equals("assimetria.processo.entrada.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.processo.saida.sentido_direto");
			
				Frase[] t = new Frase[7];
				t[0] = new Frase("faz uso de");
				t[1] = new Frase("usa como entrada");
				t[2] = new Frase("utiliza");
				t[3] = new Frase("transforma");
				t[4] = new Frase("converte");
				t[5] = new Frase("altera");
				t[6] = new Frase("modifica");
				
				if(geneLista.getFrase().getDescricao().equals("� usado por"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("� entrada de"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("� utilizado por"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("� transformado por"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("� convertido por"))
					gene_inverso.setFrase(t[4]);				
				else if(geneLista.getFrase().getDescricao().equals("� alterado por"))
					gene_inverso.setFrase(t[5]);
				else if(geneLista.getFrase().getDescricao().equals("� modificado por"))
					gene_inverso.setFrase(t[6]);
		}
		else if(geneLista.getCadeia().equals("assimetria.temporalidade.ordena��o.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.temporalidade.ordena��o.sentido_inverso");
			
				Frase[] t = new Frase[6];
				t[0] = new Frase("� precedido por");
				t[1] = new Frase("� antecedido por");
				t[2] = new Frase("ocorre depois que");
				t[3] = new Frase("� posterior que");
				t[4] = new Frase("existe depois que");
				t[5] = new Frase("� depois que");
				
				if(geneLista.getFrase().getDescricao().equals("precede"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("antecede") || geneLista.getFrase().getDescricao().equals("� sucedido por"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("ocorre antes de"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("� anterior a") || geneLista.getFrase().getDescricao().equals("� primeiro que"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("existe antes que"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("� antes de"))
					gene_inverso.setFrase(t[5]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.temporalidade.ordena��o.sentido_invero"))
		{
			gene_inverso.setCadeia("assimetria.temporalidade.ordena��o.sentido_direto");
			
				Frase[] t = new Frase[6];
				t[0] = new Frase("precede");
				t[1] = new Frase("antecede");
				t[2] = new Frase("ocorre antes de");
				t[3] = new Frase("� anterior a");
				t[4] = new Frase("existe antes que");
				t[5] = new Frase("� antes de");
				
				if(geneLista.getFrase().getDescricao().equals("� precedido por"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("� antecedido por"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("ocorre depois que"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("� posterior que"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("existe depois que"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("� depois que"))
					gene_inverso.setFrase(t[5]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.temporalidade.compara��o.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.temporalidade.compara��o.sentido_inverso");
			
				Frase[] t = new Frase[5];
				t[0] = new Frase("� mais curto que");
				t[1] = new Frase("� menor que");
				t[2] = new Frase("leva menos tempo que");
				t[3] = new Frase("� menos duradouro que");
				t[4] = new Frase("dura menos tempo que");
				
				if(geneLista.getFrase().getDescricao().equals("� mais longo que"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("� maior que"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("leva mais tempo que"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("� mais duradouro que"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("dura mais tempo que"))
					gene_inverso.setFrase(t[4]);								
		}
		else if(geneLista.getCadeia().equals("assimetria.temporalidade.compara��o.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.temporalidade.compara��o.sentido_direto");
			
				Frase[] t = new Frase[5];
				t[0] = new Frase("� mais longo que");
				t[1] = new Frase("� maior que");
				t[2] = new Frase("leva mais tempo que");
				t[3] = new Frase("� mais duradouro que");
				t[4] = new Frase("dura mais tempo que");
				
				if(geneLista.getFrase().getDescricao().equals("� mais curto que"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("� menor que"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("leva menos tempo que"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("� menos duradouro que"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("dura menos tempo que"))
					gene_inverso.setFrase(t[4]);								
		}
		else if(geneLista.getCadeia().equals("assimetria.temporalidade.dura��o.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.temporalidade.dura��o.sentido_inverso");
			
				Frase[] t = new Frase[4];
				t[0] = new Frase("� a dura��o de");
				t[1] = new Frase("� o tempo que leva o");
				t[2] = new Frase("� o tempo de exist�ncia de");
				t[3] = new Frase("� o intervalo de tempo do");
				
				if(geneLista.getFrase().getDescricao().equals("dura"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("leva"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("existe por"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("ocorre por"))
					gene_inverso.setFrase(t[3]);												
		}
		else if(geneLista.getCadeia().equals("assimetria.temporalidade.dura��o.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.temporalidade.dura��o.sentido_direto");
			
				Frase[] t = new Frase[4];
				t[0] = new Frase("dura");
				t[1] = new Frase("leva");
				t[2] = new Frase("existe por");
				t[3] = new Frase("ocorre por");
				
				if(geneLista.getFrase().getDescricao().equals("� a dura��o de") || geneLista.getFrase().getDescricao().equals("� o tempo que dura o"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("� o tempo que leva o"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("� o tempo de exist�ncia de"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("� o intervalo de tempo do"))
					gene_inverso.setFrase(t[3]);												
		}
		else if(geneLista.getCadeia().equals("assimetria.defini��o.definidor.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.defini��o.definidor.sentido_inverso");
			
				Frase[] t = new Frase[6];
				t[0] = new Frase("� definido por");
				t[1] = new Frase("� delimitado por");
				t[2] = new Frase("� determinado por");
				t[3] = new Frase("� decidido por");
				t[4] = new Frase("� demarcado por");
				t[5] = new Frase("� decretado por");
				
				if(geneLista.getFrase().getDescricao().equals("define"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("delimita"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("determina"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("decide"))
					gene_inverso.setFrase(t[3]);												
				else if(geneLista.getFrase().getDescricao().equals("demarca"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("decreta"))
					gene_inverso.setFrase(t[5]);
		}
		else if(geneLista.getCadeia().equals("assimetria.defini��o.definidor.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.defini��o.definidor.sentido_direto");
			
				Frase[] t = new Frase[6];
				t[0] = new Frase("define");
				t[1] = new Frase("delimita");
				t[2] = new Frase("determina");
				t[3] = new Frase("decide");
				t[4] = new Frase("demarca");
				t[5] = new Frase("decreta");
				
				if(geneLista.getFrase().getDescricao().equals("� definido por"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("� delimitado por"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("� determinado por"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("� decidido por"))
					gene_inverso.setFrase(t[3]);												
				else if(geneLista.getFrase().getDescricao().equals("� demarcado por"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("� decretado por"))
					gene_inverso.setFrase(t[5]);
		}
		else if(geneLista.getCadeia().equals("assimetria.defini��o.definido.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.defini��o.definido.sentido_inverso");
			
				Frase[] t = new Frase[1];
				t[0] = new Frase("� defini��o de");
				
				if(geneLista.getFrase().getDescricao().equals("� definido como") ||
						geneLista.getFrase().getDescricao().equals("� delimitado como") ||
						geneLista.getFrase().getDescricao().equals("� determinado como") ||
						geneLista.getFrase().getDescricao().equals("� decidido como") ||
						geneLista.getFrase().getDescricao().equals("� demarcado como") ||
						geneLista.getFrase().getDescricao().equals("� decretado como") ||
						geneLista.getFrase().getDescricao().equals("�"))
					gene_inverso.setFrase(t[0]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.defini��o.definido.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.defini��o.definido.sentido_direto");
			
				Frase[] t = new Frase[1];
				t[0] = new Frase("� definido como");
				
				if(geneLista.getFrase().getDescricao().equals("� defini��o de"))
					gene_inverso.setFrase(t[0]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.defini��o.inclus�o.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.defini��o.inclus�o.sentido_inverso");
			
				Frase[] t = new Frase[6];
				t[0] = new Frase("possui defini��o de");
				t[1] = new Frase("possui delimita��o de");
				t[2] = new Frase("possui determina��o de");
				t[3] = new Frase("possui decis�o de");
				t[4] = new Frase("possui demarca��o de");
				t[5] = new Frase("tem defini��o de");
				
				if(geneLista.getFrase().getDescricao().equals("� definido em"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("� delimitado em"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("� determinado em"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("� decidido em"))
					gene_inverso.setFrase(t[3]);												
				else if(geneLista.getFrase().getDescricao().equals("� demarcado em"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("� decretado em"))
					gene_inverso.setFrase(t[5]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.defini��o.inclus�o.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.defini��o.inclus�o.sentido_direto");
			
				Frase[] t = new Frase[5];
				t[0] = new Frase("� definido em");
				t[1] = new Frase("� delimitado em");
				t[2] = new Frase("� determinado em");
				t[3] = new Frase("� decidido em");
				t[4] = new Frase("� demarcado em");
				
				if(geneLista.getFrase().getDescricao().equals("possui defini��o de") ||
						geneLista.getFrase().getDescricao().equals("tem defini��o de"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("possui delimita��o de") ||
						geneLista.getFrase().getDescricao().equals("tem delimita��o de"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("possui determina��o de") ||
						geneLista.getFrase().getDescricao().equals("tem determina��o de"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("possui decis�o de") ||
						geneLista.getFrase().getDescricao().equals("tem decis�o de"))
					gene_inverso.setFrase(t[3]);												
				else if(geneLista.getFrase().getDescricao().equals("possui demarca��o de") ||
						geneLista.getFrase().getDescricao().equals("tem demarca��o de"))
					gene_inverso.setFrase(t[4]);								
		}
		else if(geneLista.getCadeia().equals("assimetria.classifica��o.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.classifica��o.sentido_inverso");
			
				Frase[] t = new Frase[8];
				t[0] = new Frase("� forma de");
				t[1] = new Frase("� tipo de");//
				t[2] = new Frase("� classificado como");
				t[3] = new Frase("� subtipo de");//
				t[4] = new Frase("� classe de");
				t[5] = new Frase("� subclasse de");
				t[6] = new Frase("tem supertipo");
				t[7] = new Frase("tem superclasse");
				
				if(geneLista.getFrase().getDescricao().equals("pode aparecer como"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("tem tipo"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("pode ser"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("tem subtipo"))
					gene_inverso.setFrase(t[3]);												
				else if(geneLista.getFrase().getDescricao().equals("tem classe"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("tem subclasse"))
					gene_inverso.setFrase(t[5]);				
				else if(geneLista.getFrase().getDescricao().equals("� supertipo de"))
					gene_inverso.setFrase(t[6]);
				else if(geneLista.getFrase().getDescricao().equals("� superclasse de"))
					gene_inverso.setFrase(t[7]);
		}
		else if(geneLista.getCadeia().equals("assimetria.classifica��o.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.classifica��o.sentido_direto");
			
				Frase[] t = new Frase[8];
				t[0] = new Frase("pode aparecer como");
				t[1] = new Frase("tem tipo");
				t[2] = new Frase("pode ser");
				t[3] = new Frase("tem subtipo");
				t[4] = new Frase("tem classe");
				t[5] = new Frase("tem subclasse");
				t[6] = new Frase("� supertipo de");
				t[7] = new Frase("� superclasse de");
				
				if(geneLista.getFrase().getDescricao().equals("� forma de"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("� tipo de"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("� classificado como"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("� subtipo de"))
					gene_inverso.setFrase(t[3]);												
				else if(geneLista.getFrase().getDescricao().equals("� classe de"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("� subclasse de"))
					gene_inverso.setFrase(t[5]);				
				else if(geneLista.getFrase().getDescricao().equals("tem supertipo"))
					gene_inverso.setFrase(t[6]);
				else if(geneLista.getFrase().getDescricao().equals("tem superclasse"))
					gene_inverso.setFrase(t[7]);
		}
		else if(geneLista.getCadeia().equals("assimetria.caracteriza��o.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.caracteriza��o.sentido_inverso");
			
				Frase[] t = new Frase[3];
				t[0] = new Frase("� atributo de");
				t[1] = new Frase("� caracter�stica de");
				t[2] = new Frase("� propriedade de");				
				
				if(geneLista.getFrase().getDescricao().equals("tem atributo") ||
						geneLista.getFrase().getDescricao().equals("tem como atributo"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("tem caracter�stica") ||
						geneLista.getFrase().getDescricao().equals("tem como caracter�stica"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("tem propriedade") ||
						geneLista.getFrase().getDescricao().equals("tem como propriedade"))
					gene_inverso.setFrase(t[2]);
		}
		else if(geneLista.getCadeia().equals("assimetria.caracteriza��o.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.caracteriza��o.sentido_direto");
			
				Frase[] t = new Frase[3];
				t[0] = new Frase("tem como atributo");
				t[1] = new Frase("tem como caracter�stica");
				t[2] = new Frase("tem como propriedade");				
				
				if(geneLista.getFrase().getDescricao().equals("� atributo de"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("� caracter�stica de"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("� propriedade de"))
					gene_inverso.setFrase(t[2]);
		}
		else if(geneLista.getCadeia().equals("assimetria.exemplifica��o.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.exemplifica��o.sentido_inverso");
			
				Frase[] t = new Frase[2];
				t[0] = new Frase("� exemplo de");
				t[1] = new Frase("� inst�ncia de");								
				
				if(geneLista.getFrase().getDescricao().equals("tem exemplo") ||
						geneLista.getFrase().getDescricao().equals("tem como exemplo") ||
						geneLista.getFrase().getDescricao().equals("� exemplificado por"))
					gene_inverso.setFrase(t[0]);				
				else if(geneLista.getFrase().getDescricao().equals("tem inst�ncia") ||
						geneLista.getFrase().getDescricao().equals("tem como inst�ncia"))
					gene_inverso.setFrase(t[1]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.exemplifica��o.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.exemplifica��o.sentido_direto");
			
				Frase[] t = new Frase[2];
				t[0] = new Frase("tem exemplo");
				t[1] = new Frase("tem inst�ncia");								
				
				if(geneLista.getFrase().getDescricao().equals("� exemplo de"))
					gene_inverso.setFrase(t[0]);				
				else if(geneLista.getFrase().getDescricao().equals("� inst�ncia de"))
					gene_inverso.setFrase(t[1]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.parti��o.temporalidade.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.parti��o.temporalidade.sentido_inverso");
			
				Frase[] t = new Frase[9];
				t[0] = new Frase("� parte de");
				t[1] = new Frase("� etapa de");
				t[2] = new Frase("� fase de");
				t[3] = new Frase("� est�gio de");
				t[4] = new Frase("� sess�o de");
				t[5] = new Frase("� passo de");
				t[6] = new Frase("� componente de");
				t[7] = new Frase("� inclu�do em");
				t[8] = new Frase("� contido em");
				
				if(geneLista.getFrase().getDescricao().equals("tem") ||
						geneLista.getFrase().getDescricao().equals("tem parte"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("tem etapa"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("tem fase"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("tem est�gio"))
					gene_inverso.setFrase(t[3]);												
				else if(geneLista.getFrase().getDescricao().equals("tem sess�o"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("tem passo"))
					gene_inverso.setFrase(t[5]);				
				else if(geneLista.getFrase().getDescricao().equals("� composto de") ||
						geneLista.getFrase().getDescricao().equals("possui") ||
						geneLista.getFrase().getDescricao().equals("� agregado de"))
					gene_inverso.setFrase(t[6]);
				else if(geneLista.getFrase().getDescricao().equals("inclui"))
					gene_inverso.setFrase(t[7]);
				else if(geneLista.getFrase().getDescricao().equals("cont�m"))
					gene_inverso.setFrase(t[8]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.parti��o.temporalidade.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.parti��o.temporalidade.sentido_direto");
			
				Frase[] t = new Frase[9];
				t[0] = new Frase("tem parte");
				t[1] = new Frase("tem etapa");
				t[2] = new Frase("tem fase");
				t[3] = new Frase("tem est�gio");
				t[4] = new Frase("tem sess�o");
				t[5] = new Frase("tem passo");
				t[6] = new Frase("possui");
				t[7] = new Frase("inclui");
				t[8] = new Frase("cont�m");
				
				if(geneLista.getFrase().getDescricao().equals("� parte de"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("� etapa de"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("� fase de"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("� est�gio de"))
					gene_inverso.setFrase(t[3]);												
				else if(geneLista.getFrase().getDescricao().equals("� sess�o de"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("� passo de"))
					gene_inverso.setFrase(t[5]);				
				else if(geneLista.getFrase().getDescricao().equals("� componente de"))
					gene_inverso.setFrase(t[6]);
				else if(geneLista.getFrase().getDescricao().equals("� inclu�do em"))
					gene_inverso.setFrase(t[7]);
				else if(geneLista.getFrase().getDescricao().equals("� contido em"))
					gene_inverso.setFrase(t[8]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.parti��o.f�sica.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.parti��o.f�sica.sentido_inverso");
			
				Frase[] t = new Frase[6];
				t[0] = new Frase("� parte de");
				t[1] = new Frase("� peda�o de");
				t[2] = new Frase("� se��o de");
				t[3] = new Frase("� componente de");
				t[4] = new Frase("� inclu�do em");
				t[5] = new Frase("� contido em");
				
				if(geneLista.getFrase().getDescricao().equals("tem") ||
						geneLista.getFrase().getDescricao().equals("tem parte") ||
						geneLista.getFrase().getDescricao().equals("� agregado de") ||
						geneLista.getFrase().getDescricao().equals("� composto de"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("tem peda�o"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("tem se��o"))
					gene_inverso.setFrase(t[2]);												
				else if(geneLista.getFrase().getDescricao().equals("possui"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("inclui"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("cont�m"))
					gene_inverso.setFrase(t[5]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.parti��o.f�sica.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.parti��o.f�sica.sentido_direto");
			
				Frase[] t = new Frase[6];
				t[0] = new Frase("tem parte");
				t[1] = new Frase("tem peda�o");
				t[2] = new Frase("tem se��o");
				t[3] = new Frase("possui");
				t[4] = new Frase("inclui");
				t[5] = new Frase("cont�m");
				
				if(geneLista.getFrase().getDescricao().equals("� parte de"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("� peda�o de"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("� se��o de"))
					gene_inverso.setFrase(t[2]);												
				else if(geneLista.getFrase().getDescricao().equals("� componente de"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("� inclu�do em"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("� contido em"))
					gene_inverso.setFrase(t[5]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.parti��o.organiza��o.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.parti��o.organiza��o.sentido_inverso");
			
				Frase[] t = new Frase[6];
				t[0] = new Frase("� componente de");
				t[1] = new Frase("� se��o de");
				t[2] = new Frase("� camada de");
				t[3] = new Frase("� n�vel de");
				t[4] = new Frase("� inclu�do em");
				t[5] = new Frase("� contido em");
				
				if(geneLista.getFrase().getDescricao().equals("tem") || 
						geneLista.getFrase().getDescricao().equals("� agregado de") ||
						geneLista.getFrase().getDescricao().equals("� composto de") ||
						geneLista.getFrase().getDescricao().equals("possui"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("tem se��o") ||
						geneLista.getFrase().getDescricao().equals("� organizado em"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("tem camada"))
					gene_inverso.setFrase(t[2]);												
				else if(geneLista.getFrase().getDescricao().equals("tem n�vel"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("inclui"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("cont�m"))
					gene_inverso.setFrase(t[5]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.parti��o.organiza��o.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.parti��o.organiza��o.sentido_direto");
			
				Frase[] t = new Frase[6];
				t[0] = new Frase("possui");
				t[1] = new Frase("tem se��o");
				t[2] = new Frase("tem camada");
				t[3] = new Frase("tem n�vel");
				t[4] = new Frase("inclui");
				t[5] = new Frase("cont�m");
				
				if(geneLista.getFrase().getDescricao().equals("� componente de"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("� se��o de"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("� camada de"))
					gene_inverso.setFrase(t[2]);												
				else if(geneLista.getFrase().getDescricao().equals("� n�vel de"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("� inclu�do em"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("� contido em"))
					gene_inverso.setFrase(t[5]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.objetivação.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.objetivação.sentido_inverso");
			
				Frase[] t = new Frase[2];
				t[0] = new Frase("� finalidade de");
				t[1] = new Frase("� objetivo de");
				
				if(geneLista.getFrase().getDescricao().equals("tem a finalidade") ||
						geneLista.getFrase().getDescricao().equals("tem como finalidade"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("tem o objetivo") ||
						geneLista.getFrase().getDescricao().equals("tem como objetivo") ||
						geneLista.getFrase().getDescricao().equals("objetiva"))
					gene_inverso.setFrase(t[1]);												
		}
		else if(geneLista.getCadeia().equals("assimetria.objetivação.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.objetivação.sentido_direto");
			
				Frase[] t = new Frase[2];
				t[0] = new Frase("tem como finalidade");
				t[1] = new Frase("tem como objetivo");
				
				if(geneLista.getFrase().getDescricao().equals("� finalidade de"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("� objetivo de"))
					gene_inverso.setFrase(t[1]);												
		}
	}
	
	
	public static void inferirGeneTransitivo(ArrayList<Gene> conj_genes)
	{
		ArrayList<Gene> conj_auxiliarTransitivo = new ArrayList<Gene>();
		for(int i = 0; i < conj_genes.size(); i++)
		{
			Gene geneLista = conj_genes.get(i);
			
			for(int j = (i+1); j < conj_genes.size(); j++)
			{
				Gene geneListaAuxiliar = conj_genes.get(j);
				
				if(geneListaAuxiliar.getFrase().equals(geneLista.getFrase()) && 
						geneLista.getConceitoDestino().equals(geneListaAuxiliar.getConceitoOrigem()) &&
						geneLista.getCadeia().equals(geneListaAuxiliar.getCadeia()))
				{
					Gene gene_transitivo = new Gene();			
					
					gene_transitivo.setConceitoOrigem(geneLista.getConceitoOrigem());
					gene_transitivo.setConceitoDestino(geneListaAuxiliar.getConceitoDestino());
					gene_transitivo.setCadeia(geneLista.getCadeia());
					gene_transitivo.setFrase(geneLista.getFrase());
					
					if(geneLista.getAprendizagem().getNome().equals("Diferenciação Progressiva") &&
							geneLista.getAprendizagem().getSigla().equals("d"))
						gene_transitivo.setAprendizagem("Diferenciação Progressiva", "d");
					else if(geneLista.getAprendizagem().getNome().equals("Reconciliação Integrativa") &&
							geneLista.getAprendizagem().getSigla().equals("r"))
						gene_transitivo.setAprendizagem("Reconciliação Integrativa", "r");								
					
					if(!(conj_auxiliarTransitivo.contains(gene_transitivo)))
						conj_auxiliarTransitivo.add(gene_transitivo);					
										
				}
			}
		}
		
		while(!(conj_auxiliarTransitivo.isEmpty()))
		{
			for(int j = 0; j < conj_auxiliarTransitivo.size(); j++)
			{
				Gene geneAuxiliar = conj_auxiliarTransitivo.get(j);
				conj_genes.add(conj_genes.size(), geneAuxiliar);
			}
		}
		
		conj_auxiliarTransitivo = null;
		
	}
	
	public static void inferirGeneSilogismoDisjuntivo(ArrayList<Gene> conj_genes)
	{
		ArrayList<Gene> conj_auxiliarDisjuntivo = new ArrayList<Gene>();
		
		for(int i = 0; i < (conj_genes.size()-1); i++)
		{
			Gene geneLista = conj_genes.get(i);
	
			Gene geneListaAuxiliar = conj_genes.get(i+1);
			
			Gene geneNegativo = conj_genes.get(i);
			
			geneNegativo.setConceitoOrigem(geneLista.getConceitoOrigem());
			geneNegativo.setConceitoDestino(geneLista.getConceitoDestino());
			geneNegativo.setCadeia(geneLista.getCadeia());//gene negativo pega a mesma cadeia do geneLista, mudar a taxonomia?
			
			if(geneLista.getAprendizagem().getNome().equals("Diferencia��o Progressiva") &&
					geneLista.getAprendizagem().getSigla().equals("d"))
				geneNegativo.setAprendizagem("Diferencia��o Progressiva", "d");
			else if(geneLista.getAprendizagem().getNome().equals("Reconcilia��o Integrativa") &&
					geneLista.getAprendizagem().getSigla().equals("r"))
				geneNegativo.setAprendizagem("Reconcilia��o Integrativa", "r");								
			
			setFraseNegativa(geneLista, geneNegativo);
			
			if(!(geneNegativo.getFrase().equals(geneLista.getFrase())) && 
					geneNegativo.getConceitoOrigem().equals(geneLista.getConceitoOrigem()) &&
					geneNegativo.getConceitoDestino().equals(geneLista.getConceitoDestino()))
			{
				Gene geneSilogismoDisjuntivo = geneListaAuxiliar;
				
				if(geneListaAuxiliar.getAprendizagem().getNome().equals("Diferencia��o Progressiva") &&
						geneListaAuxiliar.getAprendizagem().getSigla().equals("d"))
					geneSilogismoDisjuntivo.setAprendizagem("Diferencia��o Progressiva", "d");
				else if(geneListaAuxiliar.getAprendizagem().getNome().equals("Reconcilia��o Integrativa") &&
						geneListaAuxiliar.getAprendizagem().getSigla().equals("r"))
					geneSilogismoDisjuntivo.setAprendizagem("Reconcilia��o Integrativa", "r");								
				
				if(!(conj_auxiliarDisjuntivo.contains(geneSilogismoDisjuntivo)))
					conj_auxiliarDisjuntivo.add(geneSilogismoDisjuntivo);				
			}
		}
		
		for(int j = 0; j < conj_auxiliarDisjuntivo.size(); j++)
		{
			Gene geneAuxiliar = conj_auxiliarDisjuntivo.get(j);
			conj_genes.add(conj_genes.size(), geneAuxiliar);
		}
		conj_auxiliarDisjuntivo = null;
	}
	
	
	public static void setOperacaoNegacao(ArrayList<Gene> conj_genes)
	{
		ArrayList<Gene> conj_auxiliarNegativo = new ArrayList<Gene>();
		
		for(int i = 0; i < conj_genes.size(); i++)
		{
			Gene geneLista = conj_genes.get(i);
			
			Gene geneNegativo = geneLista;
			
			setFraseNegativa(geneLista, geneNegativo);
			
			if(geneLista.getAprendizagem().getNome().equals("Diferencia��o Progressiva") &&
					geneLista.getAprendizagem().getSigla().equals("d"))
				geneNegativo.setAprendizagem("Diferencia��o Progressiva", "d");
			else if(geneLista.getAprendizagem().getNome().equals("Reconcilia��o Integrativa") &&
					geneLista.getAprendizagem().getSigla().equals("r"))
				geneNegativo.setAprendizagem("Reconcilia��o Integrativa", "r");
			
			if(!(conj_auxiliarNegativo.contains(geneNegativo)))
				conj_auxiliarNegativo.add(geneNegativo);			
		}
	}
	
	
	public static void setOperacaoCondional(ArrayList<Gene> conj_genes)
	{
		ArrayList<Gene> conj_auxiliarCondicional = new ArrayList<Gene>();
		ArrayList<Gene> conj_auxiliarCondicional2 = new ArrayList<Gene>();
		
		for(int i = 0; i < conj_genes.size(); i++)
		{
			Gene geneFato = conj_genes.get(i);
			
			Gene geneAuxiCondicinal = conj_genes.get(i);
			
			conj_auxiliarCondicional.add(geneAuxiCondicinal);
			
			setOperacaoNegacao(conj_auxiliarCondicional);
			
			for(int j = 0; j < conj_auxiliarCondicional.size(); j++)
			{
				Gene geneCondicional = conj_auxiliarCondicional.get(j);
				
				if(geneFato.getConceitoOrigem().equals(geneCondicional.getConceitoOrigem()) &&
						!(geneFato.getFrase().equals(geneCondicional.getFrase())))
				{
					conj_auxiliarCondicional2.add(geneCondicional);
				}
			}	
		}
		
		for(int j = 0; j < conj_auxiliarCondicional2.size(); j++)
		{
			Gene geneAuxilia = conj_auxiliarCondicional2.get(j);
			if(!(conj_auxiliarCondicional2.contains(geneAuxilia)))
				conj_genes.add(conj_genes.size(), geneAuxilia);			
		}
		
		conj_auxiliarCondicional = null;
		conj_auxiliarCondicional2 = null;
		
	}
	
	
	public static void inferirGeneModusTollens(ArrayList<Gene> conj_genes)
	{
		ArrayList<Gene> conj_auxiliarTolles = new ArrayList<Gene>();
		
		for(int i = 0; i < conj_genes.size(); i++)
		{
			Gene geneAntecedente_1 = conj_genes.get(i);
			
				Gene geneAntecedenteNegativo_1 = geneAntecedente_1;
			
			Gene geneAntecedente_2 = conj_genes.get(i+1);
			
				Gene geneAntecedenteNegativo_2 = geneAntecedente_2;
			
			if(geneAntecedente_1 != geneAntecedente_2)
			{
				Gene geneConsequente = conj_genes.get(i+2);
				
					Gene geneConsequenteNegativo = geneConsequente;
				
				setFraseNegativa(geneAntecedente_1, geneConsequenteNegativo);
				
				if(geneConsequenteNegativo.getFrase().getDescricao().contains("n�o") &&
						geneConsequenteNegativo.getConceitoOrigem().equals(geneConsequenteNegativo.getConceitoOrigem()) &&
						geneConsequenteNegativo.getConceitoDestino().equals(geneConsequenteNegativo.getConceitoDestino()))
				{
					setFraseNegativa(geneAntecedente_1, geneAntecedenteNegativo_1);
					
					setFraseNegativa(geneAntecedente_2, geneAntecedenteNegativo_2);
					
					conj_auxiliarTolles.add(geneAntecedenteNegativo_1);
					conj_auxiliarTolles.add(geneAntecedenteNegativo_2);
					
				}
			}			
		}
		for(int j = 0; j < conj_auxiliarTolles.size(); j++)
		{
			Gene geneAuxilia = conj_auxiliarTolles.get(j);
			if(!(conj_auxiliarTolles.contains(geneAuxilia)))
				conj_genes.add(conj_genes.size(), geneAuxilia);			
		}
		
		conj_auxiliarTolles = null;		
	}
	
	
	public static void setFraseNegativa(Gene geneLista, Gene geneNegativo)
	{
		if(geneNegativo.getCadeia().equals("assimetria.objetiva�ao.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.objetiva��o.sentido_inverso"))
		{
			
			//Map<Integer,String> m = new HashMap<Integer,String>();
			//m.put(33, "n�o tem como finalidade");
			//m.put(34, "n�o tem como objetivo");
			
			Frase[] g_negativo = new Frase[7];
			g_negativo[0] = new Frase("n�o tem como finalidade");
			g_negativo[1] = new Frase("n�o tem como objetivo");
			g_negativo[2] = new Frase("n�o � finalidade de");
			g_negativo[3] = new Frase("n�o � objetivo de");
			g_negativo[4] = new Frase("n�o tem a finalidade");
			g_negativo[5] = new Frase("n�o tem o objetivo");
			g_negativo[6] = new Frase("n�o objetiva");
			
			if(geneLista.getFrase().getDescricao().equals("tem como finalidade"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("tem como objetivo"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("� finalidade de"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("� objetivo de"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("tem a finalidade"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("tem o objetivo"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("objetiva"))
				geneNegativo.setFrase(g_negativo[6]);
		}
		else if(geneNegativo.getCadeia().equals("assimetria.parti��o.organiza��o.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.parti��o.organiza��o.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[16];
			g_negativo[0] = new Frase("n�o possui");
			g_negativo[1] = new Frase("n�o tem se��o");
			g_negativo[2] = new Frase("n�o tem camada");
			g_negativo[3] = new Frase("n�o tem n�vel");
			g_negativo[4] = new Frase("n�o inclui");
			g_negativo[5] = new Frase("n�o cont�m");
			g_negativo[6] = new Frase("n�o � componente de");
			g_negativo[7] = new Frase("n�o � se��o de");
			g_negativo[8] = new Frase("n�o � camada de");
			g_negativo[9] = new Frase("n�o � n�vel de");
			g_negativo[10] = new Frase("n�o � inclu�do em");
			g_negativo[11] = new Frase("n�o � contido em");
			g_negativo[12] = new Frase("n�o � composto de");
			g_negativo[13] = new Frase("n�o � agregado de");
			g_negativo[14] = new Frase("n�o � organizado");
			g_negativo[15] = new Frase("n�o � tem");
			
			if(geneLista.getFrase().getDescricao().equals("possui"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("tem se��o"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("tem camada"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("tem n�vel"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("inclui"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("cont�m"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("� componente de"))
				geneNegativo.setFrase(g_negativo[6]);
			else if(geneLista.getFrase().getDescricao().equals("� se��o de"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("� camada de"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("� n�vel de"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("� inclu�do em"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("� contido em"))
				geneNegativo.setFrase(g_negativo[11]);			
			else if(geneLista.getFrase().getDescricao().equals("� composto de"))
				geneNegativo.setFrase(g_negativo[12]);
			else if(geneLista.getFrase().getDescricao().equals("� agregado de"))
				geneNegativo.setFrase(g_negativo[13]);
			else if(geneLista.getFrase().getDescricao().equals("� organizado em"))
				geneNegativo.setFrase(g_negativo[14]);
			else if(geneLista.getFrase().getDescricao().equals("tem"))
				geneNegativo.setFrase(g_negativo[15]);
		}
		else if(geneNegativo.getCadeia().equals("assimetria.parti��o.f�sica.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.parti��o.f�sica.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[15];
			g_negativo[0] = new Frase("n�o tem");
			g_negativo[1] = new Frase("n�o tem parte");
			g_negativo[2] = new Frase("n�o tem peda�o");
			g_negativo[3] = new Frase("n�o tem se��o");
			g_negativo[4] = new Frase("n�o � agregado de");
			g_negativo[5] = new Frase("n�o � composto de");
			g_negativo[6] = new Frase("n�o inclui");
			g_negativo[7] = new Frase("n�o cont�m");
			g_negativo[8] = new Frase("n�o possui");
			g_negativo[9] = new Frase("n�o � parte de");
			g_negativo[10] = new Frase("n�o � peda�o de");
			g_negativo[11] = new Frase("n�o � se��o de");
			g_negativo[12] = new Frase("n�o � componente de");
			g_negativo[13] = new Frase("n�o � inclu�do em");
			g_negativo[14] = new Frase("n�o � contido em");
			
			if(geneLista.getFrase().getDescricao().equals("tem"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("tem parte"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("tem peda�o"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("tem se��o"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("� agregado de"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("� composto de"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("inclui"))
				geneNegativo.setFrase(g_negativo[6]);
			else if(geneLista.getFrase().getDescricao().equals("cont�m"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("possui"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("� parte de"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("� peda�o de"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("� se��o de"))
				geneNegativo.setFrase(g_negativo[11]);			
			else if(geneLista.getFrase().getDescricao().equals("� componente de"))
				geneNegativo.setFrase(g_negativo[12]);
			else if(geneLista.getFrase().getDescricao().equals("� inclu�do em"))
				geneNegativo.setFrase(g_negativo[13]);
			else if(geneLista.getFrase().getDescricao().equals("� contido em"))
				geneNegativo.setFrase(g_negativo[14]);
		}
		else if(geneNegativo.getCadeia().equals("assimetria.parti��o.temporalidade.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.parti��o.temporalidade.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[21];
			g_negativo[0] = new Frase("n�o tem");
			g_negativo[1] = new Frase("n�o tem etapa");
			g_negativo[2] = new Frase("n�o tem fase");
			g_negativo[3] = new Frase("n�o tem est�gio");
			g_negativo[4] = new Frase("n�o tem sess�o");
			g_negativo[5] = new Frase("n�o tem passo");
			g_negativo[6] = new Frase("n�o tem parte");
			g_negativo[7] = new Frase("n�o � agregado de");
			g_negativo[8] = new Frase("n�o � composto de");
			g_negativo[9] = new Frase("n�o inclui");
			g_negativo[10] = new Frase("n�o cont�m");
			g_negativo[11] = new Frase("n�o possui");
			g_negativo[12] = new Frase("n�o � etapa de");
			g_negativo[13] = new Frase("n�o � fase de");
			g_negativo[14] = new Frase("n�o � est�gio de");
			g_negativo[15] = new Frase("n�o � sess�o de");
			g_negativo[16] = new Frase("n�o � passo de");
			g_negativo[17] = new Frase("n�o � parte de");
			g_negativo[18] = new Frase("n�o � componente de");
			g_negativo[19] = new Frase("n�o � inclu�do em");
			g_negativo[20] = new Frase("n�o � contido em");
			
			if(geneLista.getFrase().getDescricao().equals("tem"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("tem etapa"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("tem fase"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("tem est�gio"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("tem sess�o"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("tem passo"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("tem parte"))
				geneNegativo.setFrase(g_negativo[6]);
			else if(geneLista.getFrase().getDescricao().equals("� agregado de"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("� composto de"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("inclui"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("cont�m"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("possui"))
				geneNegativo.setFrase(g_negativo[11]);			
			else if(geneLista.getFrase().getDescricao().equals("� etapa de"))
				geneNegativo.setFrase(g_negativo[12]);
			else if(geneLista.getFrase().getDescricao().equals("� fase de"))
				geneNegativo.setFrase(g_negativo[13]);
			else if(geneLista.getFrase().getDescricao().equals("� est�gio de"))
				geneNegativo.setFrase(g_negativo[14]);
			else if(geneLista.getFrase().getDescricao().equals("� sess�o de"))
				geneNegativo.setFrase(g_negativo[15]);
			else if(geneLista.getFrase().getDescricao().equals("� passo de"))
				geneNegativo.setFrase(g_negativo[16]);
			else if(geneLista.getFrase().getDescricao().equals("� parte de"))
				geneNegativo.setFrase(g_negativo[17]);
			else if(geneLista.getFrase().getDescricao().equals("� componente de"))
				geneNegativo.setFrase(g_negativo[18]);
			else if(geneLista.getFrase().getDescricao().equals("� inclu�do em"))
				geneNegativo.setFrase(g_negativo[19]);
			else if(geneLista.getFrase().getDescricao().equals("� contido em"))
				geneNegativo.setFrase(g_negativo[20]);
		}
		else if(geneNegativo.getCadeia().equals("assimetria.exemplifica��o.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.exemplifica��o.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[7];
			g_negativo[0] = new Frase("n�o tem exemplo");
			g_negativo[1] = new Frase("n�o tem como exemplo");
			g_negativo[2] = new Frase("n�o tem inst�ncia");
			g_negativo[3] = new Frase("n�o tem como inst�ncia");
			g_negativo[4] = new Frase("n�o � exemplificado por");
			g_negativo[5] = new Frase("n�o � exemplo de");
			g_negativo[6] = new Frase("n�o � inst�ncia de");
			
			if(geneLista.getFrase().getDescricao().equals("tem exemplo"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("tem como exemplo"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("tem inst�ncia"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("tem como inst�ncia"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("� exemplificado por"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("� exemplo de"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("� inst�ncia de"))
				geneNegativo.setFrase(g_negativo[6]);			
		}
		else if(geneNegativo.getCadeia().equals("assimetria.caracteriza��o.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.caracteriza��o.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[9];
			g_negativo[0] = new Frase("n�o tem atributo");
			g_negativo[1] = new Frase("n�o tem como atributo");
			g_negativo[2] = new Frase("n�o tem caracter�stica");
			g_negativo[3] = new Frase("n�o tem como caracter�stica");
			g_negativo[4] = new Frase("n�o tem propriedade");
			g_negativo[5] = new Frase("n�o tem como propriedade");
			g_negativo[6] = new Frase("n�o � atributo");
			g_negativo[7] = new Frase("n�o � caracter�stica de");
			g_negativo[8] = new Frase("n�o � propriedade de");
			
			if(geneLista.getFrase().getDescricao().equals("tem atributo"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("tem como atributo"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("tem caracter�stica"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("tem como caracter�stica"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("tem propriedade"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("tem como propriedade"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("� atributo de"))
				geneNegativo.setFrase(g_negativo[6]);			
			else if(geneLista.getFrase().getDescricao().equals("� caracter�stica de"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("� propriedade de"))
				geneNegativo.setFrase(g_negativo[8]);
		}
		else if(geneNegativo.getCadeia().equals("assimetria.classifica��o.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.classifica��o.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[15];
			g_negativo[0] = new Frase("n�o pode aparecer como");
			g_negativo[1] = new Frase("n�o tem tipo");
			g_negativo[2] = new Frase("n�o pode ser");
			g_negativo[3] = new Frase("n�o tem subtipo");
			g_negativo[4] = new Frase("n�o tem classe");
			g_negativo[5] = new Frase("n�o tem subclasse");
			g_negativo[6] = new Frase("n�o � supertipo de");
			g_negativo[7] = new Frase("n�o � superclasse de");
			g_negativo[8] = new Frase("n�o � forma de");
			g_negativo[9] = new Frase("n�o � tipo de");
			g_negativo[10] = new Frase("n�o � subtipo de");
			g_negativo[11] = new Frase("n�o � superclasse de");
			g_negativo[12] = new Frase("n�o � classificado como");
			g_negativo[13] = new Frase("n�o tem supertipo");
			g_negativo[14] = new Frase("n�o tem superclasse");
			
			if(geneLista.getFrase().getDescricao().equals("pode aparecer como"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("tem tipo"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("pode ser"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("tem subtipo"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("tem classe"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("tem subclasse"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("� supertipo de"))
				geneNegativo.setFrase(g_negativo[6]);			
			else if(geneLista.getFrase().getDescricao().equals("� superclasse de"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("� forma de"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("� tipo de"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("� subtipo de"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("� subclasse de"))
				geneNegativo.setFrase(g_negativo[11]);
			else if(geneLista.getFrase().getDescricao().equals("� classificado como"))
				geneNegativo.setFrase(g_negativo[12]);
			else if(geneLista.getFrase().getDescricao().equals("tem supertipo"))
				geneNegativo.setFrase(g_negativo[13]);
			else if(geneLista.getFrase().getDescricao().equals("tem superclasse"))
				geneNegativo.setFrase(g_negativo[14]);
		}
		else if(geneNegativo.getCadeia().equals("assimetria.defini��o.definidor.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.defini��o.definidor.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[12];
			g_negativo[0] = new Frase("n�o define");
			g_negativo[1] = new Frase("n�o delimita");
			g_negativo[2] = new Frase("n�o determina");
			g_negativo[3] = new Frase("n�o decide");
			g_negativo[4] = new Frase("n�o demarca");
			g_negativo[5] = new Frase("n�o decreta");
			g_negativo[6] = new Frase("n�o � definido por");
			g_negativo[7] = new Frase("n�o � delimitado por");
			g_negativo[8] = new Frase("n�o � determinado por");
			g_negativo[9] = new Frase("n�o � decidido por");
			g_negativo[10] = new Frase("n�o � demarcado por");
			g_negativo[11] = new Frase("n�o � decretado por");
			
			if(geneLista.getFrase().getDescricao().equals("define"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("delimita"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("determina"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("decide"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("demarca"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("decreta"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("� definido por"))
				geneNegativo.setFrase(g_negativo[6]);			
			else if(geneLista.getFrase().getDescricao().equals("� delimitado por"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("� determinado por"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("� decidido por"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("� demarcado por"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("� decretado por"))
				geneNegativo.setFrase(g_negativo[11]);
			
		}
		else if(geneNegativo.getCadeia().equals("assimetria.defini��o.inclus�o.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.defini��o.inclus�o.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[16];
			g_negativo[0] = new Frase("n�o � definido em");
			g_negativo[1] = new Frase("n�o � delimitado em");
			g_negativo[2] = new Frase("n�o � determinado em");
			g_negativo[3] = new Frase("n�o � decidido em");
			g_negativo[4] = new Frase("n�o � demarcado em");
			g_negativo[5] = new Frase("n�o � decretado em");
			g_negativo[6] = new Frase("n�o possui defini��o de");
			g_negativo[7] = new Frase("n�o possui delimita��o de");
			g_negativo[8] = new Frase("n�o possui determina��o de");
			g_negativo[9] = new Frase("n�o possui decis�o de");
			g_negativo[10] = new Frase("n�o possui demarca��o de");
			g_negativo[11] = new Frase("n�o tem defini��o de");
			g_negativo[12] = new Frase("n�o tem delimita��o de");
			g_negativo[13] = new Frase("n�o tem determina��o de");
			g_negativo[14] = new Frase("n�o tem decis�o de");
			g_negativo[15] = new Frase("n�o tem demarca��o de");
			
			if(geneLista.getFrase().getDescricao().equals("� definido em"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("� delimitado em"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("� determinado em"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("� decidido em"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("� demarcado em"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("� decretado em"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("possui defini��o de"))
				geneNegativo.setFrase(g_negativo[6]);			
			else if(geneLista.getFrase().getDescricao().equals("possui delimita��o de"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("possui determina��o de"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("possui decis�o de"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("possui demarca��o de"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("tem defini��o de"))
				geneNegativo.setFrase(g_negativo[11]);
			else if(geneLista.getFrase().getDescricao().equals("tem delimita��o de"))
				geneNegativo.setFrase(g_negativo[12]);
			else if(geneLista.getFrase().getDescricao().equals("tem determina��o de"))
				geneNegativo.setFrase(g_negativo[13]);
			else if(geneLista.getFrase().getDescricao().equals("tem decis�o de"))
				geneNegativo.setFrase(g_negativo[14]);
			else if(geneLista.getFrase().getDescricao().equals("tem demarca��o de"))
				geneNegativo.setFrase(g_negativo[15]);
		}
		else if(geneNegativo.getCadeia().equals("assimetria.temporalidade.ordena��o.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.temporalidade.ordena��o.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[16];
			g_negativo[0] = new Frase("n�o precede");
			g_negativo[1] = new Frase("n�o antecede");
			g_negativo[2] = new Frase("n�o ocorre antes de");
			g_negativo[3] = new Frase("n�o � anterior a");
			g_negativo[4] = new Frase("n�o � primeiro que");
			g_negativo[5] = new Frase("n�o existe antes que");
			g_negativo[6] = new Frase("n�o � antes de");
			g_negativo[7] = new Frase("n�o � sucedido por");
			g_negativo[8] = new Frase("n�o � precedido por");
			g_negativo[9] = new Frase("n�o � antecedido por");
			g_negativo[10] = new Frase("n�o sucede");
			g_negativo[11] = new Frase("n�o segue");
			g_negativo[12] = new Frase("n�o � posterior que");
			g_negativo[13] = new Frase("n�o ocorre depois de");
			g_negativo[14] = new Frase("n�o existe depois que");
			g_negativo[15] = new Frase("n�o � depois de");
			
			if(geneLista.getFrase().getDescricao().equals("precede"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("antecede"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("ocorre antes de"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("� anterior a"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("� primeiro que"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("existe antes que"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("� antes de"))
				geneNegativo.setFrase(g_negativo[6]);			
			else if(geneLista.getFrase().getDescricao().equals("� sucedido por"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("� precedido por"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("� antecedido por"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("sucede"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("segue"))
				geneNegativo.setFrase(g_negativo[11]);
			else if(geneLista.getFrase().getDescricao().equals("� posterior que"))
				geneNegativo.setFrase(g_negativo[12]);
			else if(geneLista.getFrase().getDescricao().equals("ocorre depois de"))
				geneNegativo.setFrase(g_negativo[13]);
			else if(geneLista.getFrase().getDescricao().equals("existe depois que"))
				geneNegativo.setFrase(g_negativo[14]);
			else if(geneLista.getFrase().getDescricao().equals("� depois de"))
				geneNegativo.setFrase(g_negativo[15]);
		}
		else if(geneNegativo.getCadeia().equals("assimetria.temporalidade.compara��o.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.temporalidade.compara��o.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[10];
			g_negativo[0] = new Frase("n�o � mais longo que");
			g_negativo[1] = new Frase("n�o � maior que");
			g_negativo[2] = new Frase("n�o leva mais tempo que");
			g_negativo[3] = new Frase("n�o � mais duradouro que");
			g_negativo[4] = new Frase("n�o dura mais tempo que");
			g_negativo[5] = new Frase("n�o � mais curto que");
			g_negativo[6] = new Frase("n�o � menos que");
			g_negativo[7] = new Frase("n�o leva menos tempo que");
			g_negativo[8] = new Frase("n�o menos duradouro que");
			g_negativo[9] = new Frase("n�o dura menos tempo que");
			
			if(geneLista.getFrase().getDescricao().equals("� mais longo que"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("� maior que"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("leva mais tempo que"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("� mais duradouro que"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("dura mais tempo que"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("� mais curto que"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("� menos que"))
				geneNegativo.setFrase(g_negativo[6]);			
			else if(geneLista.getFrase().getDescricao().equals("leva menos tempo que"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("� menos duradouro que"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("dura menos tempo que"))
				geneNegativo.setFrase(g_negativo[9]);			
		}
		else if(geneNegativo.getCadeia().equals("assimetria.temporalidade.dura��o.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.temporalidade.dura��o.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[9];
			g_negativo[0] = new Frase("n�o dura");
			g_negativo[1] = new Frase("n�o leva");
			g_negativo[2] = new Frase("n�o existe por");
			g_negativo[3] = new Frase("n�o ocorre por");
			g_negativo[4] = new Frase("n�o � o tempo que dura o");
			g_negativo[5] = new Frase("n�o � o tempo que leva o");
			g_negativo[6] = new Frase("n�o � a dura��o de");
			g_negativo[7] = new Frase("n�o � o intervalo de tempo do");
			g_negativo[8] = new Frase("n�o � o tempo de exist�ncia de");
			
			if(geneLista.getFrase().getDescricao().equals("dura"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("leva"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("existe por"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("ocorre por"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("� o tempo que dura o"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("� o tempo que leva o"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("� a dura��o de"))
				geneNegativo.setFrase(g_negativo[6]);			
			else if(geneLista.getFrase().getDescricao().equals("� o intervalo de tempo do"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("� o tempo de exist�ncia de"))
				geneNegativo.setFrase(g_negativo[8]);
						
		}
		else if(geneNegativo.getCadeia().equals("assimetria.processo.entrada.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.processo.entrada.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[17];
			g_negativo[0] = new Frase("n�o usa");
			g_negativo[1] = new Frase("n�o usa como entrada");
			g_negativo[2] = new Frase("n�o utiliza");
			g_negativo[3] = new Frase("n�o utiliza como");
			g_negativo[4] = new Frase("n�o transforma");
			g_negativo[5] = new Frase("n�o converte");
			g_negativo[6] = new Frase("n�o altera");
			g_negativo[7] = new Frase("n�o modifica");
			g_negativo[8] = new Frase("n�o faz o uso de");
			g_negativo[9] = new Frase("n�o � entrada de");
			g_negativo[10] = new Frase("n�o � usado por");
			g_negativo[11] = new Frase("n�o � utilizado por");
			g_negativo[12] = new Frase("n�o � transformado por");
			g_negativo[13] = new Frase("n�o � convertido por");
			g_negativo[14] = new Frase("n�o � alterado por");
			g_negativo[15] = new Frase("n�o � modificado por");
			g_negativo[16] = new Frase("n�o � mutado por");
			
			if(geneLista.getFrase().getDescricao().equals("usa"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("usa como entrada"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("utiliza"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("utiliza como"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("transforma"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("converte"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("altera"))
				geneNegativo.setFrase(g_negativo[6]);			
			else if(geneLista.getFrase().getDescricao().equals("modifica"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("faz o uso de"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("� entrada de"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("� usado por"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("� utilizado por"))
				geneNegativo.setFrase(g_negativo[11]);
			else if(geneLista.getFrase().getDescricao().equals("� transformado por"))
				geneNegativo.setFrase(g_negativo[12]);
			else if(geneLista.getFrase().getDescricao().equals("� convertido por"))
				geneNegativo.setFrase(g_negativo[13]);
			else if(geneLista.getFrase().getDescricao().equals("� alterado por"))
				geneNegativo.setFrase(g_negativo[14]);
			else if(geneLista.getFrase().getDescricao().equals("� modificado por"))
				geneNegativo.setFrase(g_negativo[15]);
			else if(geneLista.getFrase().getDescricao().equals("� mutado por"))
				geneNegativo.setFrase(g_negativo[16]);						
		}
		else if(geneNegativo.getCadeia().equals("assimetria.processo.sa�da.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.processo.sa�da.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[17];
			g_negativo[0] = new Frase("n�o gera");
			g_negativo[1] = new Frase("n�o causa");
			g_negativo[2] = new Frase("n�o produz");
			g_negativo[3] = new Frase("n�o provoca");
			g_negativo[4] = new Frase("n�o resulta em");
			g_negativo[5] = new Frase("n�o tem como resultado");
			g_negativo[6] = new Frase("n�o forma");
			g_negativo[7] = new Frase("n�o tem como consequ�ncia");
			g_negativo[8] = new Frase("n�o tem como sa�da");
			g_negativo[9] = new Frase("n�o � gerado por");
			g_negativo[10] = new Frase("n�o � causado por");
			g_negativo[11] = new Frase("n�o � produzido por");
			g_negativo[12] = new Frase("n�o � provocado por");
			g_negativo[13] = new Frase("n�o � resultado por");
			g_negativo[14] = new Frase("n�o � formado por");
			g_negativo[15] = new Frase("n�o � consequ�ncia de");
			g_negativo[16] = new Frase("n�o � sa�da de");
			
			if(geneLista.getFrase().getDescricao().equals("gera"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("causa"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("produz"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("provoca"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("resulta em"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("tem como resultado"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("forma"))
				geneNegativo.setFrase(g_negativo[6]);
			else if(geneLista.getFrase().getDescricao().equals("tem como consequ�ncia"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("tem como sa�da"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("� gerado por"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("� causado por"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("� produzido por"))
				geneNegativo.setFrase(g_negativo[11]);
			else if(geneLista.getFrase().getDescricao().equals("� provocado por"))
				geneNegativo.setFrase(g_negativo[12]);
			else if(geneLista.getFrase().getDescricao().equals("� resultado por"))
				geneNegativo.setFrase(g_negativo[13]);
			else if(geneLista.getFrase().getDescricao().equals("� formado por"))
				geneNegativo.setFrase(g_negativo[14]);
			else if(geneLista.getFrase().getDescricao().equals("� consequ�ncia de"))
				geneNegativo.setFrase(g_negativo[15]);
			else if(geneLista.getFrase().getDescricao().equals("� sa�da de"))
				geneNegativo.setFrase(g_negativo[16]);						
		}
		else if(geneNegativo.getCadeia().equals("assimetria.processo.transforma��o.controle_interno.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.processo.transforma��o.controle_interno.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[22];
			g_negativo[0] = new Frase("n�o transforma-se em");
			g_negativo[1] = new Frase("n�o converte-se em");
			g_negativo[2] = new Frase("n�o modifica-se em");
			g_negativo[3] = new Frase("n�o altera-se para");
			g_negativo[4] = new Frase("n�o muta-se para");
			g_negativo[5] = new Frase("n�o prov�m de");
			g_negativo[6] = new Frase("n�o surge de");
			g_negativo[7] = new Frase("n�o tem como origem");
			g_negativo[8] = new Frase("n�o resulta de");
			g_negativo[9] = new Frase("n�o � consequ�ncia de");
			g_negativo[10] = new Frase("n�o � altera��o de");
			g_negativo[11] = new Frase("n�o � transforma��o de");
			g_negativo[12] = new Frase("n�o � convers�o de");
			g_negativo[13] = new Frase("n�o � muta��o de");
			g_negativo[14] = new Frase("n�o � modifica��o de");
			g_negativo[15] = new Frase("n�o � transformado em");
			g_negativo[16] = new Frase("n�o � convertido em");
			g_negativo[17] = new Frase("n�o � alterado em");
			g_negativo[18] = new Frase("n�o � mutado em");
			g_negativo[19] = new Frase("n�o � modificado em");
			g_negativo[20] = new Frase("n�o resulta em");
			g_negativo[21] = new Frase("n�o tem como consequ�ncia");
			
			if(geneLista.getFrase().getDescricao().equals("transforma-se em"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("converte-se em"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("modifica-se para"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("altera-se para"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("muta-se para"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("prov�m de"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("surge de"))
				geneNegativo.setFrase(g_negativo[6]);
			else if(geneLista.getFrase().getDescricao().equals("tem como origem"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("resulta de"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("� consequ�ncia de"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("� altera��o de"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("� transforma��o de"))
				geneNegativo.setFrase(g_negativo[11]);
			else if(geneLista.getFrase().getDescricao().equals("� convers�o de"))
				geneNegativo.setFrase(g_negativo[12]);
			else if(geneLista.getFrase().getDescricao().equals("� muta��o de"))
				geneNegativo.setFrase(g_negativo[13]);
			else if(geneLista.getFrase().getDescricao().equals("� modifica��o de"))
				geneNegativo.setFrase(g_negativo[14]);
			else if(geneLista.getFrase().getDescricao().equals("� transformado em"))
				geneNegativo.setFrase(g_negativo[15]);
			else if(geneLista.getFrase().getDescricao().equals("� convertido em"))
				geneNegativo.setFrase(g_negativo[16]);						
			else if(geneLista.getFrase().getDescricao().equals("� alterado em"))
				geneNegativo.setFrase(g_negativo[17]);
			else if(geneLista.getFrase().getDescricao().equals("� mutado em"))
				geneNegativo.setFrase(g_negativo[18]);
			else if(geneLista.getFrase().getDescricao().equals("� modificado em"))
				geneNegativo.setFrase(g_negativo[19]);
			else if(geneLista.getFrase().getDescricao().equals("resulta em"))
				geneNegativo.setFrase(g_negativo[20]);
			else if(geneLista.getFrase().getDescricao().equals("tem como consequ�ncia"))
				geneNegativo.setFrase(g_negativo[21]);
		}
		
	}
	
}
			