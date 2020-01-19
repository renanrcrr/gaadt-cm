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
			
			if(geneLista.getAprendizagem().getNome().equals("Diferenciação Progressiva") &&
					geneLista.getAprendizagem().getSigla().equals("d"))
				gene_inverso_d.setAprendizagem("Diferenciação Progressiva", "d");
			else if(geneLista.getAprendizagem().getNome().equals("Reconciliação Integrativa") &&
					geneLista.getAprendizagem().getSigla().equals("r"))
				gene_inverso_d.setAprendizagem("Reconciliação Integrativa", "r");
			
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
		if(geneLista.getCadeia().equals("assimetria.temporalidade.ordenação.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.temporalidade.ordenação.sentido_direto");
			
				Frase[] t = new Frase[8];
				t[0] = new Frase("ocorre antes de");
				t[1] = new Frase("precede");
				t[2] = new Frase("antecede");
				t[3] = new Frase("é anterior a");
				t[4] = new Frase("é primeiro que");
				t[5] = new Frase("existe antes que");
				t[6] = new Frase("é antes de");
				t[7] = new Frase("é sucedido por");
				
				if((geneLista.getFrase().getDescricao().equals("ocorre depois de")))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("é precedido por"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("é antecedido por"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("sucede"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("segue"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("é posterior que"))
					gene_inverso.setFrase(t[5]);
				else if(geneLista.getFrase().getDescricao().equals("existe depois de"))
					gene_inverso.setFrase(t[6]);
				else if(geneLista.getFrase().getDescricao().equals("é depois de"))
					gene_inverso.setFrase(t[7]);
		}
		else if(geneLista.getCadeia().equals("assimetria.temporalidade.ordenação.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.temporalidade.ordenação.sentido_inverso");
			
				Frase[] t = new Frase[8];
				t[0] = new Frase("ocorre depois de");
				t[1] = new Frase("é precedido por");
				t[2] = new Frase("é antecedido por");
				t[3] = new Frase("sucede");
				t[4] = new Frase("segue");
				t[5] = new Frase("é posterior que");
				t[6] = new Frase("existe depois de");
				t[7] = new Frase("é depois de");
				
				if((geneLista.getFrase().getDescricao().equals("ocorre antes de")))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("precede"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("antecede"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("é anterior a"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("é primeiro que"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("existe antes que"))
					gene_inverso.setFrase(t[5]);
				else if(geneLista.getFrase().getDescricao().equals("é antes de"))
					gene_inverso.setFrase(t[6]);
				else if(geneLista.getFrase().getDescricao().equals("é sucedido por"))
					gene_inverso.setFrase(t[7]);
		}
		else if(geneLista.getCadeia().equals("assimetria.processo.transformação.controle_interno.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.processo.transformação.controle_interno.sentido_inverso");
			
				Frase[] t = new Frase[7];
				t[0] = new Frase("é transformação de");
				t[1] = new Frase("é conversão de");
				t[2] = new Frase("é alteração de");
				t[3] = new Frase("é mutação de");
				t[4] = new Frase("é modificação de");
				t[5] = new Frase("resulta em");
				t[6] = new Frase("é consequência de");
				
				if((geneLista.getFrase().getDescricao().equals("é transformado em")))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("é convertido em"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("é alterado em"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("é mutado em"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("é modificado em"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("resulta de"))
					gene_inverso.setFrase(t[5]);
				else if(geneLista.getFrase().getDescricao().equals("tem como consequência"))
					gene_inverso.setFrase(t[6]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.processo.transformação.controle_interno.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.processo.transformação.controle_interno.sentido_direto");
			
				Frase[] t = new Frase[7];
				t[0] = new Frase("é transformado em");
				t[1] = new Frase("é convertido em");
				t[2] = new Frase("é alterado em");
				t[3] = new Frase("é mutado em");
				t[4] = new Frase("é modificado em");
				t[5] = new Frase("resulta em");
				t[6] = new Frase("tem como consequência");
				
				if(geneLista.getFrase().getDescricao().equals("é transformação de"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("é conversão de") || geneLista.getFrase().getDescricao().equals("provém de"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("é alteração de"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("é mutação de")) 
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("é modificação de"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("resulta de") || geneLista.getFrase().getDescricao().equals("surge de") || geneLista.getFrase().getDescricao().equals("tem como origem"))
					gene_inverso.setFrase(t[5]);
				else if(geneLista.getFrase().getDescricao().equals("é consequência de"))
					gene_inverso.setFrase(t[6]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.processo.saída.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.processo.saída.sentido_inverso");
			
				Frase[] t = new Frase[8];
				t[0] = new Frase("é gerado por");
				t[1] = new Frase("é causado por");
				t[2] = new Frase("é produzido por");
				t[3] = new Frase("é provocado por");
				t[4] = new Frase("é resultado de");
				t[5] = new Frase("é formado por");
				t[6] = new Frase("é consequência de");
				t[7] = new Frase("é saída de");
				
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
				else if(geneLista.getFrase().getDescricao().equals("tem como consequência"))
					gene_inverso.setFrase(t[6]);
				else if(geneLista.getFrase().getDescricao().equals("tem como saída"))
					gene_inverso.setFrase(t[7]);
		}
		else if(geneLista.getCadeia().equals("assimetria.processo.saída.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.processo.saída.sentido_direto");
			
				Frase[] t = new Frase[8];
				t[0] = new Frase("gera");
				t[1] = new Frase("causa");
				t[2] = new Frase("produz");
				t[3] = new Frase("provoca");
				t[4] = new Frase("resulta em");
				t[5] = new Frase("forma");
				t[6] = new Frase("tem como consequência");
				t[7] = new Frase("tem como saída");
				
				if((geneLista.getFrase().getDescricao().equals("é gerado por")))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("é causado por"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("é produzido por"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("é provocado por"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("é resultado de"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("é formado por"))
					gene_inverso.setFrase(t[5]);				
				else if(geneLista.getFrase().getDescricao().equals("é consequência de"))
					gene_inverso.setFrase(t[6]);
				else if(geneLista.getFrase().getDescricao().equals("é saída de"))
					gene_inverso.setFrase(t[7]);
		}
		else if(geneLista.getCadeia().equals("assimetria.processo.entrada.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.processo.saida.sentido_inverso");
			
				Frase[] t = new Frase[7];
				t[0] = new Frase("é usado por");
				t[1] = new Frase("é entrada de");
				t[2] = new Frase("é utilizado por");
				t[3] = new Frase("é transformado por");
				t[4] = new Frase("é convertido por");
				t[5] = new Frase("é alterado por");
				t[6] = new Frase("é modificado por");
				
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
				
				if(geneLista.getFrase().getDescricao().equals("é usado por"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("é entrada de"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("é utilizado por"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("é transformado por"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("é convertido por"))
					gene_inverso.setFrase(t[4]);				
				else if(geneLista.getFrase().getDescricao().equals("é alterado por"))
					gene_inverso.setFrase(t[5]);
				else if(geneLista.getFrase().getDescricao().equals("é modificado por"))
					gene_inverso.setFrase(t[6]);
		}
		else if(geneLista.getCadeia().equals("assimetria.temporalidade.ordenação.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.temporalidade.ordenação.sentido_inverso");
			
				Frase[] t = new Frase[6];
				t[0] = new Frase("é precedido por");
				t[1] = new Frase("é antecedido por");
				t[2] = new Frase("ocorre depois que");
				t[3] = new Frase("é posterior que");
				t[4] = new Frase("existe depois que");
				t[5] = new Frase("é depois que");
				
				if(geneLista.getFrase().getDescricao().equals("precede"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("antecede") || geneLista.getFrase().getDescricao().equals("é sucedido por"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("ocorre antes de"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("é anterior a") || geneLista.getFrase().getDescricao().equals("é primeiro que"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("existe antes que"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("é antes de"))
					gene_inverso.setFrase(t[5]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.temporalidade.ordenação.sentido_invero"))
		{
			gene_inverso.setCadeia("assimetria.temporalidade.ordenação.sentido_direto");
			
				Frase[] t = new Frase[6];
				t[0] = new Frase("precede");
				t[1] = new Frase("antecede");
				t[2] = new Frase("ocorre antes de");
				t[3] = new Frase("é anterior a");
				t[4] = new Frase("existe antes que");
				t[5] = new Frase("é antes de");
				
				if(geneLista.getFrase().getDescricao().equals("é precedido por"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("é antecedido por"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("ocorre depois que"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("é posterior que"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("existe depois que"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("é depois que"))
					gene_inverso.setFrase(t[5]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.temporalidade.comparação.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.temporalidade.comparação.sentido_inverso");
			
				Frase[] t = new Frase[5];
				t[0] = new Frase("é mais curto que");
				t[1] = new Frase("é menor que");
				t[2] = new Frase("leva menos tempo que");
				t[3] = new Frase("é menos duradouro que");
				t[4] = new Frase("dura menos tempo que");
				
				if(geneLista.getFrase().getDescricao().equals("é mais longo que"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("é maior que"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("leva mais tempo que"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("é mais duradouro que"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("dura mais tempo que"))
					gene_inverso.setFrase(t[4]);								
		}
		else if(geneLista.getCadeia().equals("assimetria.temporalidade.comparação.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.temporalidade.comparação.sentido_direto");
			
				Frase[] t = new Frase[5];
				t[0] = new Frase("é mais longo que");
				t[1] = new Frase("é maior que");
				t[2] = new Frase("leva mais tempo que");
				t[3] = new Frase("é mais duradouro que");
				t[4] = new Frase("dura mais tempo que");
				
				if(geneLista.getFrase().getDescricao().equals("é mais curto que"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("é menor que"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("leva menos tempo que"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("é menos duradouro que"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("dura menos tempo que"))
					gene_inverso.setFrase(t[4]);								
		}
		else if(geneLista.getCadeia().equals("assimetria.temporalidade.duração.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.temporalidade.duração.sentido_inverso");
			
				Frase[] t = new Frase[4];
				t[0] = new Frase("é a duração de");
				t[1] = new Frase("é o tempo que leva o");
				t[2] = new Frase("é o tempo de existência de");
				t[3] = new Frase("é o intervalo de tempo do");
				
				if(geneLista.getFrase().getDescricao().equals("dura"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("leva"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("existe por"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("ocorre por"))
					gene_inverso.setFrase(t[3]);												
		}
		else if(geneLista.getCadeia().equals("assimetria.temporalidade.duração.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.temporalidade.duração.sentido_direto");
			
				Frase[] t = new Frase[4];
				t[0] = new Frase("dura");
				t[1] = new Frase("leva");
				t[2] = new Frase("existe por");
				t[3] = new Frase("ocorre por");
				
				if(geneLista.getFrase().getDescricao().equals("é a duração de") || geneLista.getFrase().getDescricao().equals("é o tempo que dura o"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("é o tempo que leva o"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("é o tempo de existência de"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("é o intervalo de tempo do"))
					gene_inverso.setFrase(t[3]);												
		}
		else if(geneLista.getCadeia().equals("assimetria.definição.definidor.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.definição.definidor.sentido_inverso");
			
				Frase[] t = new Frase[6];
				t[0] = new Frase("é definido por");
				t[1] = new Frase("é delimitado por");
				t[2] = new Frase("é determinado por");
				t[3] = new Frase("é decidido por");
				t[4] = new Frase("é demarcado por");
				t[5] = new Frase("é decretado por");
				
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
		else if(geneLista.getCadeia().equals("assimetria.definição.definidor.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.definição.definidor.sentido_direto");
			
				Frase[] t = new Frase[6];
				t[0] = new Frase("define");
				t[1] = new Frase("delimita");
				t[2] = new Frase("determina");
				t[3] = new Frase("decide");
				t[4] = new Frase("demarca");
				t[5] = new Frase("decreta");
				
				if(geneLista.getFrase().getDescricao().equals("é definido por"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("é delimitado por"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("é determinado por"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("é decidido por"))
					gene_inverso.setFrase(t[3]);												
				else if(geneLista.getFrase().getDescricao().equals("é demarcado por"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("é decretado por"))
					gene_inverso.setFrase(t[5]);
		}
		else if(geneLista.getCadeia().equals("assimetria.definição.definido.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.definição.definido.sentido_inverso");
			
				Frase[] t = new Frase[1];
				t[0] = new Frase("é definição de");
				
				if(geneLista.getFrase().getDescricao().equals("é definido como") ||
						geneLista.getFrase().getDescricao().equals("é delimitado como") ||
						geneLista.getFrase().getDescricao().equals("é determinado como") ||
						geneLista.getFrase().getDescricao().equals("é decidido como") ||
						geneLista.getFrase().getDescricao().equals("é demarcado como") ||
						geneLista.getFrase().getDescricao().equals("é decretado como") ||
						geneLista.getFrase().getDescricao().equals("é"))
					gene_inverso.setFrase(t[0]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.definição.definido.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.definição.definido.sentido_direto");
			
				Frase[] t = new Frase[1];
				t[0] = new Frase("é definido como");
				
				if(geneLista.getFrase().getDescricao().equals("é definição de"))
					gene_inverso.setFrase(t[0]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.definição.inclusão.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.definição.inclusão.sentido_inverso");
			
				Frase[] t = new Frase[6];
				t[0] = new Frase("possui definição de");
				t[1] = new Frase("possui delimitação de");
				t[2] = new Frase("possui determinação de");
				t[3] = new Frase("possui decisão de");
				t[4] = new Frase("possui demarcação de");
				t[5] = new Frase("tem definição de");
				
				if(geneLista.getFrase().getDescricao().equals("é definido em"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("é delimitado em"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("é determinado em"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("é decidido em"))
					gene_inverso.setFrase(t[3]);												
				else if(geneLista.getFrase().getDescricao().equals("é demarcado em"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("é decretado em"))
					gene_inverso.setFrase(t[5]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.definição.inclusão.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.definição.inclusão.sentido_direto");
			
				Frase[] t = new Frase[5];
				t[0] = new Frase("é definido em");
				t[1] = new Frase("é delimitado em");
				t[2] = new Frase("é determinado em");
				t[3] = new Frase("é decidido em");
				t[4] = new Frase("é demarcado em");
				
				if(geneLista.getFrase().getDescricao().equals("possui definição de") ||
						geneLista.getFrase().getDescricao().equals("tem definição de"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("possui delimitação de") ||
						geneLista.getFrase().getDescricao().equals("tem delimitação de"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("possui determinação de") ||
						geneLista.getFrase().getDescricao().equals("tem determinação de"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("possui decisão de") ||
						geneLista.getFrase().getDescricao().equals("tem decisão de"))
					gene_inverso.setFrase(t[3]);												
				else if(geneLista.getFrase().getDescricao().equals("possui demarcação de") ||
						geneLista.getFrase().getDescricao().equals("tem demarcação de"))
					gene_inverso.setFrase(t[4]);								
		}
		else if(geneLista.getCadeia().equals("assimetria.classificação.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.classificação.sentido_inverso");
			
				Frase[] t = new Frase[8];
				t[0] = new Frase("é forma de");
				t[1] = new Frase("é tipo de");//
				t[2] = new Frase("é classificado como");
				t[3] = new Frase("é subtipo de");//
				t[4] = new Frase("é classe de");
				t[5] = new Frase("é subclasse de");
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
				else if(geneLista.getFrase().getDescricao().equals("é supertipo de"))
					gene_inverso.setFrase(t[6]);
				else if(geneLista.getFrase().getDescricao().equals("é superclasse de"))
					gene_inverso.setFrase(t[7]);
		}
		else if(geneLista.getCadeia().equals("assimetria.classificação.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.classificação.sentido_direto");
			
				Frase[] t = new Frase[8];
				t[0] = new Frase("pode aparecer como");
				t[1] = new Frase("tem tipo");
				t[2] = new Frase("pode ser");
				t[3] = new Frase("tem subtipo");
				t[4] = new Frase("tem classe");
				t[5] = new Frase("tem subclasse");
				t[6] = new Frase("é supertipo de");
				t[7] = new Frase("é superclasse de");
				
				if(geneLista.getFrase().getDescricao().equals("é forma de"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("é tipo de"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("é classificado como"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("é subtipo de"))
					gene_inverso.setFrase(t[3]);												
				else if(geneLista.getFrase().getDescricao().equals("é classe de"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("é subclasse de"))
					gene_inverso.setFrase(t[5]);				
				else if(geneLista.getFrase().getDescricao().equals("tem supertipo"))
					gene_inverso.setFrase(t[6]);
				else if(geneLista.getFrase().getDescricao().equals("tem superclasse"))
					gene_inverso.setFrase(t[7]);
		}
		else if(geneLista.getCadeia().equals("assimetria.caracterização.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.caracterização.sentido_inverso");
			
				Frase[] t = new Frase[3];
				t[0] = new Frase("é atributo de");
				t[1] = new Frase("é característica de");
				t[2] = new Frase("é propriedade de");				
				
				if(geneLista.getFrase().getDescricao().equals("tem atributo") ||
						geneLista.getFrase().getDescricao().equals("tem como atributo"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("tem característica") ||
						geneLista.getFrase().getDescricao().equals("tem como característica"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("tem propriedade") ||
						geneLista.getFrase().getDescricao().equals("tem como propriedade"))
					gene_inverso.setFrase(t[2]);
		}
		else if(geneLista.getCadeia().equals("assimetria.caracterização.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.caracterização.sentido_direto");
			
				Frase[] t = new Frase[3];
				t[0] = new Frase("tem como atributo");
				t[1] = new Frase("tem como característica");
				t[2] = new Frase("tem como propriedade");				
				
				if(geneLista.getFrase().getDescricao().equals("é atributo de"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("é característica de"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("é propriedade de"))
					gene_inverso.setFrase(t[2]);
		}
		else if(geneLista.getCadeia().equals("assimetria.exemplificação.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.exemplificação.sentido_inverso");
			
				Frase[] t = new Frase[2];
				t[0] = new Frase("é exemplo de");
				t[1] = new Frase("é instância de");								
				
				if(geneLista.getFrase().getDescricao().equals("tem exemplo") ||
						geneLista.getFrase().getDescricao().equals("tem como exemplo") ||
						geneLista.getFrase().getDescricao().equals("é exemplificado por"))
					gene_inverso.setFrase(t[0]);				
				else if(geneLista.getFrase().getDescricao().equals("tem instância") ||
						geneLista.getFrase().getDescricao().equals("tem como instância"))
					gene_inverso.setFrase(t[1]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.exemplificação.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.exemplificação.sentido_direto");
			
				Frase[] t = new Frase[2];
				t[0] = new Frase("tem exemplo");
				t[1] = new Frase("tem instância");								
				
				if(geneLista.getFrase().getDescricao().equals("é exemplo de"))
					gene_inverso.setFrase(t[0]);				
				else if(geneLista.getFrase().getDescricao().equals("é instância de"))
					gene_inverso.setFrase(t[1]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.partição.temporalidade.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.partição.temporalidade.sentido_inverso");
			
				Frase[] t = new Frase[9];
				t[0] = new Frase("é parte de");
				t[1] = new Frase("é etapa de");
				t[2] = new Frase("é fase de");
				t[3] = new Frase("é estágio de");
				t[4] = new Frase("é sessão de");
				t[5] = new Frase("é passo de");
				t[6] = new Frase("é componente de");
				t[7] = new Frase("é incluído em");
				t[8] = new Frase("é contido em");
				
				if(geneLista.getFrase().getDescricao().equals("tem") ||
						geneLista.getFrase().getDescricao().equals("tem parte"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("tem etapa"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("tem fase"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("tem estágio"))
					gene_inverso.setFrase(t[3]);												
				else if(geneLista.getFrase().getDescricao().equals("tem sessão"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("tem passo"))
					gene_inverso.setFrase(t[5]);				
				else if(geneLista.getFrase().getDescricao().equals("é composto de") ||
						geneLista.getFrase().getDescricao().equals("possui") ||
						geneLista.getFrase().getDescricao().equals("é agregado de"))
					gene_inverso.setFrase(t[6]);
				else if(geneLista.getFrase().getDescricao().equals("inclui"))
					gene_inverso.setFrase(t[7]);
				else if(geneLista.getFrase().getDescricao().equals("contém"))
					gene_inverso.setFrase(t[8]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.partição.temporalidade.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.partição.temporalidade.sentido_direto");
			
				Frase[] t = new Frase[9];
				t[0] = new Frase("tem parte");
				t[1] = new Frase("tem etapa");
				t[2] = new Frase("tem fase");
				t[3] = new Frase("tem estágio");
				t[4] = new Frase("tem sessão");
				t[5] = new Frase("tem passo");
				t[6] = new Frase("possui");
				t[7] = new Frase("inclui");
				t[8] = new Frase("contém");
				
				if(geneLista.getFrase().getDescricao().equals("é parte de"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("é etapa de"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("é fase de"))
					gene_inverso.setFrase(t[2]);
				else if(geneLista.getFrase().getDescricao().equals("é estágio de"))
					gene_inverso.setFrase(t[3]);												
				else if(geneLista.getFrase().getDescricao().equals("é sessão de"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("é passo de"))
					gene_inverso.setFrase(t[5]);				
				else if(geneLista.getFrase().getDescricao().equals("é componente de"))
					gene_inverso.setFrase(t[6]);
				else if(geneLista.getFrase().getDescricao().equals("é incluído em"))
					gene_inverso.setFrase(t[7]);
				else if(geneLista.getFrase().getDescricao().equals("é contido em"))
					gene_inverso.setFrase(t[8]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.partição.física.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.partição.física.sentido_inverso");
			
				Frase[] t = new Frase[6];
				t[0] = new Frase("é parte de");
				t[1] = new Frase("é pedaço de");
				t[2] = new Frase("é seção de");
				t[3] = new Frase("é componente de");
				t[4] = new Frase("é incluído em");
				t[5] = new Frase("é contido em");
				
				if(geneLista.getFrase().getDescricao().equals("tem") ||
						geneLista.getFrase().getDescricao().equals("tem parte") ||
						geneLista.getFrase().getDescricao().equals("é agregado de") ||
						geneLista.getFrase().getDescricao().equals("é composto de"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("tem pedaço"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("tem seção"))
					gene_inverso.setFrase(t[2]);												
				else if(geneLista.getFrase().getDescricao().equals("possui"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("inclui"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("contém"))
					gene_inverso.setFrase(t[5]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.partição.física.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.partição.física.sentido_direto");
			
				Frase[] t = new Frase[6];
				t[0] = new Frase("tem parte");
				t[1] = new Frase("tem pedaço");
				t[2] = new Frase("tem seção");
				t[3] = new Frase("possui");
				t[4] = new Frase("inclui");
				t[5] = new Frase("contém");
				
				if(geneLista.getFrase().getDescricao().equals("é parte de"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("é pedaço de"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("é seção de"))
					gene_inverso.setFrase(t[2]);												
				else if(geneLista.getFrase().getDescricao().equals("é componente de"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("é incluído em"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("é contido em"))
					gene_inverso.setFrase(t[5]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.partição.organização.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.partição.organização.sentido_inverso");
			
				Frase[] t = new Frase[6];
				t[0] = new Frase("é componente de");
				t[1] = new Frase("é seção de");
				t[2] = new Frase("é camada de");
				t[3] = new Frase("é nível de");
				t[4] = new Frase("é incluído em");
				t[5] = new Frase("é contido em");
				
				if(geneLista.getFrase().getDescricao().equals("tem") || 
						geneLista.getFrase().getDescricao().equals("é agregado de") ||
						geneLista.getFrase().getDescricao().equals("é composto de") ||
						geneLista.getFrase().getDescricao().equals("possui"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("tem seção") ||
						geneLista.getFrase().getDescricao().equals("é organizado em"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("tem camada"))
					gene_inverso.setFrase(t[2]);												
				else if(geneLista.getFrase().getDescricao().equals("tem nível"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("inclui"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("contém"))
					gene_inverso.setFrase(t[5]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.partição.organização.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.partição.organização.sentido_direto");
			
				Frase[] t = new Frase[6];
				t[0] = new Frase("possui");
				t[1] = new Frase("tem seção");
				t[2] = new Frase("tem camada");
				t[3] = new Frase("tem nível");
				t[4] = new Frase("inclui");
				t[5] = new Frase("contém");
				
				if(geneLista.getFrase().getDescricao().equals("é componente de"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("é seção de"))
					gene_inverso.setFrase(t[1]);
				else if(geneLista.getFrase().getDescricao().equals("é camada de"))
					gene_inverso.setFrase(t[2]);												
				else if(geneLista.getFrase().getDescricao().equals("é nível de"))
					gene_inverso.setFrase(t[3]);
				else if(geneLista.getFrase().getDescricao().equals("é incluído em"))
					gene_inverso.setFrase(t[4]);
				else if(geneLista.getFrase().getDescricao().equals("é contido em"))
					gene_inverso.setFrase(t[5]);				
		}
		else if(geneLista.getCadeia().equals("assimetria.objetivaÃ§Ã£o.sentido_direto"))
		{
			gene_inverso.setCadeia("assimetria.objetivaÃ§Ã£o.sentido_inverso");
			
				Frase[] t = new Frase[2];
				t[0] = new Frase("é finalidade de");
				t[1] = new Frase("é objetivo de");
				
				if(geneLista.getFrase().getDescricao().equals("tem a finalidade") ||
						geneLista.getFrase().getDescricao().equals("tem como finalidade"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("tem o objetivo") ||
						geneLista.getFrase().getDescricao().equals("tem como objetivo") ||
						geneLista.getFrase().getDescricao().equals("objetiva"))
					gene_inverso.setFrase(t[1]);												
		}
		else if(geneLista.getCadeia().equals("assimetria.objetivaÃ§Ã£o.sentido_inverso"))
		{
			gene_inverso.setCadeia("assimetria.objetivaÃ§Ã£o.sentido_direto");
			
				Frase[] t = new Frase[2];
				t[0] = new Frase("tem como finalidade");
				t[1] = new Frase("tem como objetivo");
				
				if(geneLista.getFrase().getDescricao().equals("é finalidade de"))
					gene_inverso.setFrase(t[0]);
				else if(geneLista.getFrase().getDescricao().equals("é objetivo de"))
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
					
					if(geneLista.getAprendizagem().getNome().equals("DiferenciaÃ§Ã£o Progressiva") &&
							geneLista.getAprendizagem().getSigla().equals("d"))
						gene_transitivo.setAprendizagem("DiferenciaÃ§Ã£o Progressiva", "d");
					else if(geneLista.getAprendizagem().getNome().equals("ReconciliaÃ§Ã£o Integrativa") &&
							geneLista.getAprendizagem().getSigla().equals("r"))
						gene_transitivo.setAprendizagem("ReconciliaÃ§Ã£o Integrativa", "r");								
					
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
			
			if(geneLista.getAprendizagem().getNome().equals("Diferenciação Progressiva") &&
					geneLista.getAprendizagem().getSigla().equals("d"))
				geneNegativo.setAprendizagem("Diferenciação Progressiva", "d");
			else if(geneLista.getAprendizagem().getNome().equals("Reconciliação Integrativa") &&
					geneLista.getAprendizagem().getSigla().equals("r"))
				geneNegativo.setAprendizagem("Reconciliação Integrativa", "r");								
			
			setFraseNegativa(geneLista, geneNegativo);
			
			if(!(geneNegativo.getFrase().equals(geneLista.getFrase())) && 
					geneNegativo.getConceitoOrigem().equals(geneLista.getConceitoOrigem()) &&
					geneNegativo.getConceitoDestino().equals(geneLista.getConceitoDestino()))
			{
				Gene geneSilogismoDisjuntivo = geneListaAuxiliar;
				
				if(geneListaAuxiliar.getAprendizagem().getNome().equals("Diferenciação Progressiva") &&
						geneListaAuxiliar.getAprendizagem().getSigla().equals("d"))
					geneSilogismoDisjuntivo.setAprendizagem("Diferenciação Progressiva", "d");
				else if(geneListaAuxiliar.getAprendizagem().getNome().equals("Reconciliação Integrativa") &&
						geneListaAuxiliar.getAprendizagem().getSigla().equals("r"))
					geneSilogismoDisjuntivo.setAprendizagem("Reconciliação Integrativa", "r");								
				
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
			
			if(geneLista.getAprendizagem().getNome().equals("Diferenciação Progressiva") &&
					geneLista.getAprendizagem().getSigla().equals("d"))
				geneNegativo.setAprendizagem("Diferenciação Progressiva", "d");
			else if(geneLista.getAprendizagem().getNome().equals("Reconciliação Integrativa") &&
					geneLista.getAprendizagem().getSigla().equals("r"))
				geneNegativo.setAprendizagem("Reconciliação Integrativa", "r");
			
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
				
				if(geneConsequenteNegativo.getFrase().getDescricao().contains("não") &&
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
		if(geneNegativo.getCadeia().equals("assimetria.objetivaçao.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.objetivação.sentido_inverso"))
		{
			
			//Map<Integer,String> m = new HashMap<Integer,String>();
			//m.put(33, "não tem como finalidade");
			//m.put(34, "não tem como objetivo");
			
			Frase[] g_negativo = new Frase[7];
			g_negativo[0] = new Frase("não tem como finalidade");
			g_negativo[1] = new Frase("não tem como objetivo");
			g_negativo[2] = new Frase("não é finalidade de");
			g_negativo[3] = new Frase("não é objetivo de");
			g_negativo[4] = new Frase("não tem a finalidade");
			g_negativo[5] = new Frase("não tem o objetivo");
			g_negativo[6] = new Frase("não objetiva");
			
			if(geneLista.getFrase().getDescricao().equals("tem como finalidade"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("tem como objetivo"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("é finalidade de"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("é objetivo de"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("tem a finalidade"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("tem o objetivo"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("objetiva"))
				geneNegativo.setFrase(g_negativo[6]);
		}
		else if(geneNegativo.getCadeia().equals("assimetria.partição.organização.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.partição.organização.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[16];
			g_negativo[0] = new Frase("não possui");
			g_negativo[1] = new Frase("não tem seção");
			g_negativo[2] = new Frase("não tem camada");
			g_negativo[3] = new Frase("não tem nível");
			g_negativo[4] = new Frase("não inclui");
			g_negativo[5] = new Frase("não contém");
			g_negativo[6] = new Frase("não é componente de");
			g_negativo[7] = new Frase("não é seção de");
			g_negativo[8] = new Frase("não é camada de");
			g_negativo[9] = new Frase("não é nível de");
			g_negativo[10] = new Frase("não é incluído em");
			g_negativo[11] = new Frase("não é contido em");
			g_negativo[12] = new Frase("não é composto de");
			g_negativo[13] = new Frase("não é agregado de");
			g_negativo[14] = new Frase("não é organizado");
			g_negativo[15] = new Frase("não é tem");
			
			if(geneLista.getFrase().getDescricao().equals("possui"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("tem seção"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("tem camada"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("tem nível"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("inclui"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("contém"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("é componente de"))
				geneNegativo.setFrase(g_negativo[6]);
			else if(geneLista.getFrase().getDescricao().equals("é seção de"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("é camada de"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("é nível de"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("é incluído em"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("é contido em"))
				geneNegativo.setFrase(g_negativo[11]);			
			else if(geneLista.getFrase().getDescricao().equals("é composto de"))
				geneNegativo.setFrase(g_negativo[12]);
			else if(geneLista.getFrase().getDescricao().equals("é agregado de"))
				geneNegativo.setFrase(g_negativo[13]);
			else if(geneLista.getFrase().getDescricao().equals("é organizado em"))
				geneNegativo.setFrase(g_negativo[14]);
			else if(geneLista.getFrase().getDescricao().equals("tem"))
				geneNegativo.setFrase(g_negativo[15]);
		}
		else if(geneNegativo.getCadeia().equals("assimetria.partição.física.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.partição.física.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[15];
			g_negativo[0] = new Frase("não tem");
			g_negativo[1] = new Frase("não tem parte");
			g_negativo[2] = new Frase("não tem pedaço");
			g_negativo[3] = new Frase("não tem seção");
			g_negativo[4] = new Frase("não é agregado de");
			g_negativo[5] = new Frase("não é composto de");
			g_negativo[6] = new Frase("não inclui");
			g_negativo[7] = new Frase("não contém");
			g_negativo[8] = new Frase("não possui");
			g_negativo[9] = new Frase("não é parte de");
			g_negativo[10] = new Frase("não é pedaço de");
			g_negativo[11] = new Frase("não é seção de");
			g_negativo[12] = new Frase("não é componente de");
			g_negativo[13] = new Frase("não é incluído em");
			g_negativo[14] = new Frase("não é contido em");
			
			if(geneLista.getFrase().getDescricao().equals("tem"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("tem parte"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("tem pedaço"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("tem seção"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("é agregado de"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("é composto de"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("inclui"))
				geneNegativo.setFrase(g_negativo[6]);
			else if(geneLista.getFrase().getDescricao().equals("contém"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("possui"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("é parte de"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("é pedaço de"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("é seção de"))
				geneNegativo.setFrase(g_negativo[11]);			
			else if(geneLista.getFrase().getDescricao().equals("é componente de"))
				geneNegativo.setFrase(g_negativo[12]);
			else if(geneLista.getFrase().getDescricao().equals("é incluído em"))
				geneNegativo.setFrase(g_negativo[13]);
			else if(geneLista.getFrase().getDescricao().equals("é contido em"))
				geneNegativo.setFrase(g_negativo[14]);
		}
		else if(geneNegativo.getCadeia().equals("assimetria.partição.temporalidade.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.partição.temporalidade.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[21];
			g_negativo[0] = new Frase("não tem");
			g_negativo[1] = new Frase("não tem etapa");
			g_negativo[2] = new Frase("não tem fase");
			g_negativo[3] = new Frase("não tem estágio");
			g_negativo[4] = new Frase("não tem sessão");
			g_negativo[5] = new Frase("não tem passo");
			g_negativo[6] = new Frase("não tem parte");
			g_negativo[7] = new Frase("não é agregado de");
			g_negativo[8] = new Frase("não é composto de");
			g_negativo[9] = new Frase("não inclui");
			g_negativo[10] = new Frase("não contém");
			g_negativo[11] = new Frase("não possui");
			g_negativo[12] = new Frase("não é etapa de");
			g_negativo[13] = new Frase("não é fase de");
			g_negativo[14] = new Frase("não é estágio de");
			g_negativo[15] = new Frase("não é sessão de");
			g_negativo[16] = new Frase("não é passo de");
			g_negativo[17] = new Frase("não é parte de");
			g_negativo[18] = new Frase("não é componente de");
			g_negativo[19] = new Frase("não é incluído em");
			g_negativo[20] = new Frase("não é contido em");
			
			if(geneLista.getFrase().getDescricao().equals("tem"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("tem etapa"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("tem fase"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("tem estágio"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("tem sessão"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("tem passo"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("tem parte"))
				geneNegativo.setFrase(g_negativo[6]);
			else if(geneLista.getFrase().getDescricao().equals("é agregado de"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("é composto de"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("inclui"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("contém"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("possui"))
				geneNegativo.setFrase(g_negativo[11]);			
			else if(geneLista.getFrase().getDescricao().equals("é etapa de"))
				geneNegativo.setFrase(g_negativo[12]);
			else if(geneLista.getFrase().getDescricao().equals("é fase de"))
				geneNegativo.setFrase(g_negativo[13]);
			else if(geneLista.getFrase().getDescricao().equals("é estágio de"))
				geneNegativo.setFrase(g_negativo[14]);
			else if(geneLista.getFrase().getDescricao().equals("é sessão de"))
				geneNegativo.setFrase(g_negativo[15]);
			else if(geneLista.getFrase().getDescricao().equals("é passo de"))
				geneNegativo.setFrase(g_negativo[16]);
			else if(geneLista.getFrase().getDescricao().equals("é parte de"))
				geneNegativo.setFrase(g_negativo[17]);
			else if(geneLista.getFrase().getDescricao().equals("é componente de"))
				geneNegativo.setFrase(g_negativo[18]);
			else if(geneLista.getFrase().getDescricao().equals("é incluído em"))
				geneNegativo.setFrase(g_negativo[19]);
			else if(geneLista.getFrase().getDescricao().equals("é contido em"))
				geneNegativo.setFrase(g_negativo[20]);
		}
		else if(geneNegativo.getCadeia().equals("assimetria.exemplificação.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.exemplificação.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[7];
			g_negativo[0] = new Frase("não tem exemplo");
			g_negativo[1] = new Frase("não tem como exemplo");
			g_negativo[2] = new Frase("não tem instância");
			g_negativo[3] = new Frase("não tem como instância");
			g_negativo[4] = new Frase("não é exemplificado por");
			g_negativo[5] = new Frase("não é exemplo de");
			g_negativo[6] = new Frase("não é instância de");
			
			if(geneLista.getFrase().getDescricao().equals("tem exemplo"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("tem como exemplo"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("tem instância"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("tem como instância"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("é exemplificado por"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("é exemplo de"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("é instância de"))
				geneNegativo.setFrase(g_negativo[6]);			
		}
		else if(geneNegativo.getCadeia().equals("assimetria.caracterização.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.caracterização.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[9];
			g_negativo[0] = new Frase("não tem atributo");
			g_negativo[1] = new Frase("não tem como atributo");
			g_negativo[2] = new Frase("não tem característica");
			g_negativo[3] = new Frase("não tem como característica");
			g_negativo[4] = new Frase("não tem propriedade");
			g_negativo[5] = new Frase("não tem como propriedade");
			g_negativo[6] = new Frase("não é atributo");
			g_negativo[7] = new Frase("não é característica de");
			g_negativo[8] = new Frase("não é propriedade de");
			
			if(geneLista.getFrase().getDescricao().equals("tem atributo"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("tem como atributo"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("tem característica"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("tem como característica"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("tem propriedade"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("tem como propriedade"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("é atributo de"))
				geneNegativo.setFrase(g_negativo[6]);			
			else if(geneLista.getFrase().getDescricao().equals("é característica de"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("é propriedade de"))
				geneNegativo.setFrase(g_negativo[8]);
		}
		else if(geneNegativo.getCadeia().equals("assimetria.classificação.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.classificação.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[15];
			g_negativo[0] = new Frase("não pode aparecer como");
			g_negativo[1] = new Frase("não tem tipo");
			g_negativo[2] = new Frase("não pode ser");
			g_negativo[3] = new Frase("não tem subtipo");
			g_negativo[4] = new Frase("não tem classe");
			g_negativo[5] = new Frase("não tem subclasse");
			g_negativo[6] = new Frase("não é supertipo de");
			g_negativo[7] = new Frase("não é superclasse de");
			g_negativo[8] = new Frase("não é forma de");
			g_negativo[9] = new Frase("não é tipo de");
			g_negativo[10] = new Frase("não é subtipo de");
			g_negativo[11] = new Frase("não é superclasse de");
			g_negativo[12] = new Frase("não é classificado como");
			g_negativo[13] = new Frase("não tem supertipo");
			g_negativo[14] = new Frase("não tem superclasse");
			
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
			else if(geneLista.getFrase().getDescricao().equals("é supertipo de"))
				geneNegativo.setFrase(g_negativo[6]);			
			else if(geneLista.getFrase().getDescricao().equals("é superclasse de"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("é forma de"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("é tipo de"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("é subtipo de"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("é subclasse de"))
				geneNegativo.setFrase(g_negativo[11]);
			else if(geneLista.getFrase().getDescricao().equals("é classificado como"))
				geneNegativo.setFrase(g_negativo[12]);
			else if(geneLista.getFrase().getDescricao().equals("tem supertipo"))
				geneNegativo.setFrase(g_negativo[13]);
			else if(geneLista.getFrase().getDescricao().equals("tem superclasse"))
				geneNegativo.setFrase(g_negativo[14]);
		}
		else if(geneNegativo.getCadeia().equals("assimetria.definição.definidor.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.definição.definidor.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[12];
			g_negativo[0] = new Frase("não define");
			g_negativo[1] = new Frase("não delimita");
			g_negativo[2] = new Frase("não determina");
			g_negativo[3] = new Frase("não decide");
			g_negativo[4] = new Frase("não demarca");
			g_negativo[5] = new Frase("não decreta");
			g_negativo[6] = new Frase("não é definido por");
			g_negativo[7] = new Frase("não é delimitado por");
			g_negativo[8] = new Frase("não é determinado por");
			g_negativo[9] = new Frase("não é decidido por");
			g_negativo[10] = new Frase("não é demarcado por");
			g_negativo[11] = new Frase("não é decretado por");
			
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
			else if(geneLista.getFrase().getDescricao().equals("é definido por"))
				geneNegativo.setFrase(g_negativo[6]);			
			else if(geneLista.getFrase().getDescricao().equals("é delimitado por"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("é determinado por"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("é decidido por"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("é demarcado por"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("é decretado por"))
				geneNegativo.setFrase(g_negativo[11]);
			
		}
		else if(geneNegativo.getCadeia().equals("assimetria.definição.inclusão.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.definição.inclusão.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[16];
			g_negativo[0] = new Frase("não é definido em");
			g_negativo[1] = new Frase("não é delimitado em");
			g_negativo[2] = new Frase("não é determinado em");
			g_negativo[3] = new Frase("não é decidido em");
			g_negativo[4] = new Frase("não é demarcado em");
			g_negativo[5] = new Frase("não é decretado em");
			g_negativo[6] = new Frase("não possui definição de");
			g_negativo[7] = new Frase("não possui delimitação de");
			g_negativo[8] = new Frase("não possui determinação de");
			g_negativo[9] = new Frase("não possui decisão de");
			g_negativo[10] = new Frase("não possui demarcação de");
			g_negativo[11] = new Frase("não tem definição de");
			g_negativo[12] = new Frase("não tem delimitação de");
			g_negativo[13] = new Frase("não tem determinação de");
			g_negativo[14] = new Frase("não tem decisão de");
			g_negativo[15] = new Frase("não tem demarcação de");
			
			if(geneLista.getFrase().getDescricao().equals("é definido em"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("é delimitado em"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("é determinado em"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("é decidido em"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("é demarcado em"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("é decretado em"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("possui definição de"))
				geneNegativo.setFrase(g_negativo[6]);			
			else if(geneLista.getFrase().getDescricao().equals("possui delimitação de"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("possui determinação de"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("possui decisão de"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("possui demarcação de"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("tem definição de"))
				geneNegativo.setFrase(g_negativo[11]);
			else if(geneLista.getFrase().getDescricao().equals("tem delimitação de"))
				geneNegativo.setFrase(g_negativo[12]);
			else if(geneLista.getFrase().getDescricao().equals("tem determinação de"))
				geneNegativo.setFrase(g_negativo[13]);
			else if(geneLista.getFrase().getDescricao().equals("tem decisão de"))
				geneNegativo.setFrase(g_negativo[14]);
			else if(geneLista.getFrase().getDescricao().equals("tem demarcação de"))
				geneNegativo.setFrase(g_negativo[15]);
		}
		else if(geneNegativo.getCadeia().equals("assimetria.temporalidade.ordenação.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.temporalidade.ordenação.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[16];
			g_negativo[0] = new Frase("não precede");
			g_negativo[1] = new Frase("não antecede");
			g_negativo[2] = new Frase("não ocorre antes de");
			g_negativo[3] = new Frase("não é anterior a");
			g_negativo[4] = new Frase("não é primeiro que");
			g_negativo[5] = new Frase("não existe antes que");
			g_negativo[6] = new Frase("não é antes de");
			g_negativo[7] = new Frase("não é sucedido por");
			g_negativo[8] = new Frase("não é precedido por");
			g_negativo[9] = new Frase("não é antecedido por");
			g_negativo[10] = new Frase("não sucede");
			g_negativo[11] = new Frase("não segue");
			g_negativo[12] = new Frase("não é posterior que");
			g_negativo[13] = new Frase("não ocorre depois de");
			g_negativo[14] = new Frase("não existe depois que");
			g_negativo[15] = new Frase("não é depois de");
			
			if(geneLista.getFrase().getDescricao().equals("precede"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("antecede"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("ocorre antes de"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("é anterior a"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("é primeiro que"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("existe antes que"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("é antes de"))
				geneNegativo.setFrase(g_negativo[6]);			
			else if(geneLista.getFrase().getDescricao().equals("é sucedido por"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("é precedido por"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("é antecedido por"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("sucede"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("segue"))
				geneNegativo.setFrase(g_negativo[11]);
			else if(geneLista.getFrase().getDescricao().equals("é posterior que"))
				geneNegativo.setFrase(g_negativo[12]);
			else if(geneLista.getFrase().getDescricao().equals("ocorre depois de"))
				geneNegativo.setFrase(g_negativo[13]);
			else if(geneLista.getFrase().getDescricao().equals("existe depois que"))
				geneNegativo.setFrase(g_negativo[14]);
			else if(geneLista.getFrase().getDescricao().equals("é depois de"))
				geneNegativo.setFrase(g_negativo[15]);
		}
		else if(geneNegativo.getCadeia().equals("assimetria.temporalidade.comparação.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.temporalidade.comparação.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[10];
			g_negativo[0] = new Frase("não é mais longo que");
			g_negativo[1] = new Frase("não é maior que");
			g_negativo[2] = new Frase("não leva mais tempo que");
			g_negativo[3] = new Frase("não é mais duradouro que");
			g_negativo[4] = new Frase("não dura mais tempo que");
			g_negativo[5] = new Frase("não é mais curto que");
			g_negativo[6] = new Frase("não é menos que");
			g_negativo[7] = new Frase("não leva menos tempo que");
			g_negativo[8] = new Frase("não menos duradouro que");
			g_negativo[9] = new Frase("não dura menos tempo que");
			
			if(geneLista.getFrase().getDescricao().equals("é mais longo que"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("é maior que"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("leva mais tempo que"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("é mais duradouro que"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("dura mais tempo que"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("é mais curto que"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("é menos que"))
				geneNegativo.setFrase(g_negativo[6]);			
			else if(geneLista.getFrase().getDescricao().equals("leva menos tempo que"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("é menos duradouro que"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("dura menos tempo que"))
				geneNegativo.setFrase(g_negativo[9]);			
		}
		else if(geneNegativo.getCadeia().equals("assimetria.temporalidade.duração.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.temporalidade.duração.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[9];
			g_negativo[0] = new Frase("não dura");
			g_negativo[1] = new Frase("não leva");
			g_negativo[2] = new Frase("não existe por");
			g_negativo[3] = new Frase("não ocorre por");
			g_negativo[4] = new Frase("não é o tempo que dura o");
			g_negativo[5] = new Frase("não é o tempo que leva o");
			g_negativo[6] = new Frase("não é a duração de");
			g_negativo[7] = new Frase("não é o intervalo de tempo do");
			g_negativo[8] = new Frase("não é o tempo de existência de");
			
			if(geneLista.getFrase().getDescricao().equals("dura"))
				geneNegativo.setFrase(g_negativo[0]);
			else if(geneLista.getFrase().getDescricao().equals("leva"))
				geneNegativo.setFrase(g_negativo[1]);
			else if(geneLista.getFrase().getDescricao().equals("existe por"))
				geneNegativo.setFrase(g_negativo[2]);
			else if(geneLista.getFrase().getDescricao().equals("ocorre por"))
				geneNegativo.setFrase(g_negativo[3]);
			else if(geneLista.getFrase().getDescricao().equals("é o tempo que dura o"))
				geneNegativo.setFrase(g_negativo[4]);
			else if(geneLista.getFrase().getDescricao().equals("é o tempo que leva o"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("é a duração de"))
				geneNegativo.setFrase(g_negativo[6]);			
			else if(geneLista.getFrase().getDescricao().equals("é o intervalo de tempo do"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("é o tempo de existência de"))
				geneNegativo.setFrase(g_negativo[8]);
						
		}
		else if(geneNegativo.getCadeia().equals("assimetria.processo.entrada.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.processo.entrada.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[17];
			g_negativo[0] = new Frase("não usa");
			g_negativo[1] = new Frase("não usa como entrada");
			g_negativo[2] = new Frase("não utiliza");
			g_negativo[3] = new Frase("não utiliza como");
			g_negativo[4] = new Frase("não transforma");
			g_negativo[5] = new Frase("não converte");
			g_negativo[6] = new Frase("não altera");
			g_negativo[7] = new Frase("não modifica");
			g_negativo[8] = new Frase("não faz o uso de");
			g_negativo[9] = new Frase("não é entrada de");
			g_negativo[10] = new Frase("não é usado por");
			g_negativo[11] = new Frase("não é utilizado por");
			g_negativo[12] = new Frase("não é transformado por");
			g_negativo[13] = new Frase("não é convertido por");
			g_negativo[14] = new Frase("não é alterado por");
			g_negativo[15] = new Frase("não é modificado por");
			g_negativo[16] = new Frase("não é mutado por");
			
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
			else if(geneLista.getFrase().getDescricao().equals("é entrada de"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("é usado por"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("é utilizado por"))
				geneNegativo.setFrase(g_negativo[11]);
			else if(geneLista.getFrase().getDescricao().equals("é transformado por"))
				geneNegativo.setFrase(g_negativo[12]);
			else if(geneLista.getFrase().getDescricao().equals("é convertido por"))
				geneNegativo.setFrase(g_negativo[13]);
			else if(geneLista.getFrase().getDescricao().equals("é alterado por"))
				geneNegativo.setFrase(g_negativo[14]);
			else if(geneLista.getFrase().getDescricao().equals("é modificado por"))
				geneNegativo.setFrase(g_negativo[15]);
			else if(geneLista.getFrase().getDescricao().equals("é mutado por"))
				geneNegativo.setFrase(g_negativo[16]);						
		}
		else if(geneNegativo.getCadeia().equals("assimetria.processo.saída.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.processo.saída.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[17];
			g_negativo[0] = new Frase("não gera");
			g_negativo[1] = new Frase("não causa");
			g_negativo[2] = new Frase("não produz");
			g_negativo[3] = new Frase("não provoca");
			g_negativo[4] = new Frase("não resulta em");
			g_negativo[5] = new Frase("não tem como resultado");
			g_negativo[6] = new Frase("não forma");
			g_negativo[7] = new Frase("não tem como consequência");
			g_negativo[8] = new Frase("não tem como saída");
			g_negativo[9] = new Frase("não é gerado por");
			g_negativo[10] = new Frase("não é causado por");
			g_negativo[11] = new Frase("não é produzido por");
			g_negativo[12] = new Frase("não é provocado por");
			g_negativo[13] = new Frase("não é resultado por");
			g_negativo[14] = new Frase("não é formado por");
			g_negativo[15] = new Frase("não é consequência de");
			g_negativo[16] = new Frase("não é saída de");
			
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
			else if(geneLista.getFrase().getDescricao().equals("tem como consequência"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("tem como saída"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("é gerado por"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("é causado por"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("é produzido por"))
				geneNegativo.setFrase(g_negativo[11]);
			else if(geneLista.getFrase().getDescricao().equals("é provocado por"))
				geneNegativo.setFrase(g_negativo[12]);
			else if(geneLista.getFrase().getDescricao().equals("é resultado por"))
				geneNegativo.setFrase(g_negativo[13]);
			else if(geneLista.getFrase().getDescricao().equals("é formado por"))
				geneNegativo.setFrase(g_negativo[14]);
			else if(geneLista.getFrase().getDescricao().equals("é consequência de"))
				geneNegativo.setFrase(g_negativo[15]);
			else if(geneLista.getFrase().getDescricao().equals("é saída de"))
				geneNegativo.setFrase(g_negativo[16]);						
		}
		else if(geneNegativo.getCadeia().equals("assimetria.processo.transformação.controle_interno.sentido_direto") ||
				geneNegativo.getCadeia().equals("assimetria.processo.transformação.controle_interno.sentido_inverso"))
		{
			Frase[] g_negativo = new Frase[22];
			g_negativo[0] = new Frase("não transforma-se em");
			g_negativo[1] = new Frase("não converte-se em");
			g_negativo[2] = new Frase("não modifica-se em");
			g_negativo[3] = new Frase("não altera-se para");
			g_negativo[4] = new Frase("não muta-se para");
			g_negativo[5] = new Frase("não provém de");
			g_negativo[6] = new Frase("não surge de");
			g_negativo[7] = new Frase("não tem como origem");
			g_negativo[8] = new Frase("não resulta de");
			g_negativo[9] = new Frase("não é consequência de");
			g_negativo[10] = new Frase("não é alteração de");
			g_negativo[11] = new Frase("não é transformação de");
			g_negativo[12] = new Frase("não é conversão de");
			g_negativo[13] = new Frase("não é mutação de");
			g_negativo[14] = new Frase("não é modificação de");
			g_negativo[15] = new Frase("não é transformado em");
			g_negativo[16] = new Frase("não é convertido em");
			g_negativo[17] = new Frase("não é alterado em");
			g_negativo[18] = new Frase("não é mutado em");
			g_negativo[19] = new Frase("não é modificado em");
			g_negativo[20] = new Frase("não resulta em");
			g_negativo[21] = new Frase("não tem como consequência");
			
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
			else if(geneLista.getFrase().getDescricao().equals("provém de"))
				geneNegativo.setFrase(g_negativo[5]);
			else if(geneLista.getFrase().getDescricao().equals("surge de"))
				geneNegativo.setFrase(g_negativo[6]);
			else if(geneLista.getFrase().getDescricao().equals("tem como origem"))
				geneNegativo.setFrase(g_negativo[7]);
			else if(geneLista.getFrase().getDescricao().equals("resulta de"))
				geneNegativo.setFrase(g_negativo[8]);
			else if(geneLista.getFrase().getDescricao().equals("é consequência de"))
				geneNegativo.setFrase(g_negativo[9]);
			else if(geneLista.getFrase().getDescricao().equals("é alteração de"))
				geneNegativo.setFrase(g_negativo[10]);
			else if(geneLista.getFrase().getDescricao().equals("é transformação de"))
				geneNegativo.setFrase(g_negativo[11]);
			else if(geneLista.getFrase().getDescricao().equals("é conversão de"))
				geneNegativo.setFrase(g_negativo[12]);
			else if(geneLista.getFrase().getDescricao().equals("é mutação de"))
				geneNegativo.setFrase(g_negativo[13]);
			else if(geneLista.getFrase().getDescricao().equals("é modificação de"))
				geneNegativo.setFrase(g_negativo[14]);
			else if(geneLista.getFrase().getDescricao().equals("é transformado em"))
				geneNegativo.setFrase(g_negativo[15]);
			else if(geneLista.getFrase().getDescricao().equals("é convertido em"))
				geneNegativo.setFrase(g_negativo[16]);						
			else if(geneLista.getFrase().getDescricao().equals("é alterado em"))
				geneNegativo.setFrase(g_negativo[17]);
			else if(geneLista.getFrase().getDescricao().equals("é mutado em"))
				geneNegativo.setFrase(g_negativo[18]);
			else if(geneLista.getFrase().getDescricao().equals("é modificado em"))
				geneNegativo.setFrase(g_negativo[19]);
			else if(geneLista.getFrase().getDescricao().equals("resulta em"))
				geneNegativo.setFrase(g_negativo[20]);
			else if(geneLista.getFrase().getDescricao().equals("tem como consequência"))
				geneNegativo.setFrase(g_negativo[21]);
		}
		
	}
	
}
			