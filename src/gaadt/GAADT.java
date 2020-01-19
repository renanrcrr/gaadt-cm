package gaadt;


import gaadt.ontologia.Ontologia;
import gaadt.ontologia.Taxonomia;
import gaadt.util.Gaussiana;
import gaadt.util.Medidor;
import gaadt.util.Ordenador;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import javax.swing.text.Segment;

import cmeditor.gui.dialogo.Dialogos;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class GAADT {
	
	private Ambiente ambiente;
	int gap = 2;
	double prob_mutacao = 0.25; /*0.25*/
	int geracoes = 15;
	private double acumula_corridas[] = new double [geracoes];
	private double fitnesses[];
	
	String segundo_melhor_cromossomo;
	String primeiro_melhor_cromossomo;
	String melhor_corrida;
	
	List<Cromossomo> lista_melhores;
	
	MapaAprendiz mpAprendiz;
	
	String relatorioMelhores = "";
	
	public void aplicaGAADT(Ontologia ontologia, Taxonomia taxonomia, MapaAprendiz mpAprendiz, int corridas, int tam_pop) throws IOException {
		
		this.fitnesses = new double[corridas];
		int adaptacao_melhor_cromossomo = 0;
		Cromossomo melhor_cromossomo = new Cromossomo();
		String relatorio = "";
		String dir = "";
		
		this.mpAprendiz = mpAprendiz;
		
		File outputFile3 = new File(dir + "ResultadoAvaliacao.txt");
		FileWriter out3 = new FileWriter(outputFile3);

		this.ambiente = new Ambiente(ontologia, taxonomia, mpAprendiz, tam_pop);
		
		for(int i = 0; i < corridas; i++) {
			Medidor med = new Medidor();
			med.tempoInicial();
			
			System.out.println("inicio corrida: " + i);	
			this.ambiente = new Ambiente(ontologia, taxonomia, mpAprendiz, tam_pop);
			
			relatorio += "\n||||||||||||||||| " + (i+1) + " CORRIDA DO GAADT-CM |||||||||||||||||";
			relatorio += "\n=========== 1 POPULACAO ===========";
			
			Populacao pop_ancestral = ambiente.getPopulacao_ancestral();
			relatorio += ambiente.RelatorioPopulacao(pop_ancestral);
			
			melhor_cromossomo = ambiente.getPopulacao_ancestral().getMelhor(ontologia, mpAprendiz);
			
			adaptacao_melhor_cromossomo = melhor_cromossomo.getAdaptacao(ontologia, mpAprendiz);
			
			relatorio += "\nMelhor cromossomo da populacao:";
			relatorio += melhor_cromossomo.getRelatorio(ontologia, mpAprendiz);
			out3.write(relatorio);
			relatorio = "";
			acumula_corridas[0] = acumula_corridas[0] + adaptacao_melhor_cromossomo;
			
			for(int j = 1; j < this.geracoes; j++) {
				boolean convergiu = ambiente.aplicaSelecao();
				if(convergiu) {
					break;
				}
				ambiente.Fecundacao();
				ambiente.Cruzamento(tam_pop, ambiente.getPopulacao_descendente(),null);
				ambiente.Mutacao(prob_mutacao);
				ambiente.aplicaEstadoEstacionario(ontologia, mpAprendiz, this.gap);
				
				relatorio += "\n=========== " + (j + 1) + " POPULACAO ===========";
				relatorio += ambiente.RelatorioPopulacao(ambiente.getPopulacao_descendente());
				
				melhor_cromossomo = ambiente.getPopulacao_descendente().getMelhor(ontologia, mpAprendiz);
				primeiro_melhor_cromossomo = melhor_cromossomo.getRelatorio(ontologia, mpAprendiz);
				adaptacao_melhor_cromossomo = melhor_cromossomo.getAdaptacao(ontologia, mpAprendiz);
				
				lista_melhores = ambiente.getPopulacao_descendente().getMelhores(ontologia, mpAprendiz);
				
			
				relatorio += "\nMelhor cromossomo da populacao:";
				relatorio += "\n" + melhor_cromossomo.getRelatorio(ontologia, mpAprendiz);

				out3.write(relatorio);
				relatorio = "";
				acumula_corridas[j] = acumula_corridas[j] + adaptacao_melhor_cromossomo;
				
			
				ambiente.RenovaGeracao();
			}

			
			relatorio += "\nMelhor cromossomo da corrida:";
			relatorio += melhor_cromossomo.getRelatorio(ontologia, mpAprendiz);
			
			melhor_corrida = melhor_cromossomo.getRelatorio(ontologia, mpAprendiz);
			
			out3.write(relatorio);
			relatorio = "";

			fitnesses[i] = adaptacao_melhor_cromossomo;
			
			med.tempoFinal("----Corrida---");
		} 
		
		File outputFile = new File(dir + "melhores_mapasexp4.txt");
		FileWriter out = new FileWriter(outputFile);
		for (int m = 0; m < geracoes; m++) out.write(String.valueOf(acumula_corridas[m] / corridas) + " ");
		out.close();
		
		File outputFile2 = new File(dir + "probabilidades_GAADTCMexp4.txt");
		FileWriter out2 = new FileWriter(outputFile2);
		Ordenador.ordena(this.fitnesses);
		Gaussiana gauss = new Gaussiana(this.fitnesses);
		out2.write(gauss.getRelatorio());
		out2.close();
		
		double acumulador = 0.0;
		for (int n = 0; n < corridas; n++) acumulador += fitnesses[n];
		relatorio += "\n Performance do AG com parametros definidos: " + (acumulador/corridas);
		relatorio += gauss.getRelatorio();
		
		relatorio += "\n Mapa do aprendiz:";
		relatorio += "\n" + mpAprendiz.toString();
		
		String relacaoSemantica = mpAprendiz.concatenarString(ontologia);		
		String mapaAprendiz = mpAprendiz.toString();		
		
		for(Cromossomo c : lista_melhores)
		{
			relatorioMelhores += c.getRelatorio(ontologia, mpAprendiz) + "\n ";
		}
		
		calcularNotaEmPercentualGenesFeitos(mpAprendiz.getQtdRelacoesOntologia());
		
		calcularNotaEmPercentualGenesIncorretos(mpAprendiz.getQtdRelacoesOntologia());
		
		calcularNotaEmPercentualGenesCorretos();
		
		
		gerarPdfAvaliacao(this.relatorioMelhores, mapaAprendiz, ontologia, mpAprendiz, relacaoSemantica, notaAvaliacaoGenesCorretos, notaAvaliacaoGenesIncorretos, notaAvaliacaoGenesFeitos);
		
		out3.write(relatorio);
		out3.close();
		System.out.println("Fim de execu\u00e7\u00e3o");
	}
		

	private BigDecimal notaEmPercentualGenesFeitos,
					notaEmPercentualGenesIncorretos,
					notaEmPercentualGenesCorretos;
	
	private double notaEmDecimal, 
				notaAvaliacaoGenesFeitos,
				notaAvaliacaoGenesIncorretos,
				notaAvaliacaoGenesCorretos;
		
		
	public void calcularNotaEmPercentualGenesFeitos(int quantidadeDeRelacoesOntologia)
	{		
		
		double valor = (((double)mpAprendiz.getNumeroGenes() / quantidadeDeRelacoesOntologia) * 100);
		
		System.out.println("Número de genes presentes na lista: " + mpAprendiz.getNumeroGenes());
		System.out.println("Relações da ontologia: " + quantidadeDeRelacoesOntologia);
		System.out.println("Razão entre genes da lista e relações: " + valor);
		
		this.notaEmPercentualGenesFeitos = new BigDecimal(valor);
		this.notaEmPercentualGenesFeitos = notaEmPercentualGenesFeitos.setScale(2,BigDecimal.ROUND_HALF_UP);
		notaAvaliacaoGenesFeitos = notaEmPercentualGenesFeitos.doubleValue();
		System.out.println("Resultado do percentual dos genes feitos em relação a relações da ontologia: " 
				+ notaAvaliacaoGenesFeitos + "\n");
	}
	
	public void calcularNotaEmPercentualGenesIncorretos(int quantidadeDeRelacoesOntologia)
	{		
		
		double valor = (mpAprendiz.getQtdGenesIncorretos() / quantidadeDeRelacoesOntologia) * 100;
		
		System.out.println("Número de genes da lista que estão incorretos: " 
				+ mpAprendiz.getQtdGenesIncorretos());
		System.out.println("Relações da ontologia: " + quantidadeDeRelacoesOntologia);
		System.out.println("Razão entre genes incorretos e as relações da onologia: " 
				+ valor);
		
		this.notaEmPercentualGenesIncorretos = new BigDecimal(valor);
		this.notaEmPercentualGenesIncorretos = notaEmPercentualGenesIncorretos.setScale(2,BigDecimal.ROUND_HALF_UP);
		notaAvaliacaoGenesIncorretos = notaEmPercentualGenesIncorretos.doubleValue();
		System.out.println("Resultado do percentual dos genes avaliados considerados incorretos: " 
				+ notaAvaliacaoGenesIncorretos + "\n");
	}
	
	public void calcularNotaEmPercentualGenesCorretos()
	{		
		notaAvaliacaoGenesCorretos = notaAvaliacaoGenesFeitos - notaAvaliacaoGenesIncorretos;
		System.out.println("notaAvaliacaoGenesCorretos = notaAvaliacaoGenesFeitos - notaAvaliacaoGenesIncorretos: " + notaAvaliacaoGenesCorretos);
		System.out.println("Resultado do percentual dos genes avaliados considerados corretos: " 
				+ notaAvaliacaoGenesCorretos + "\n");
	}
	
	public BigDecimal getNotaEmPercentual()
	{
		return this.notaEmPercentualGenesFeitos;
	}	
	
	public void calcularNotaEmDecimal(int qtdGenes)
	{
		this.notaEmDecimal = (mpAprendiz.getQtdGenesIncorretos() * 10) / qtdGenes;
		
	}
	
	
	public static void gerarPdfAvaliação(String relatorioMelhores, 
			String mapaAluno, Ontologia ontologia, MapaAprendiz mpAprendiz, String relacaoSemantica, double notaAvaliacaoGenesCorretos, double notaAvaliacaoGenesIncorretos, double notaAvaliacaoGenesFeitos) throws IOException
	{
		Document document = new Document(PageSize.A4, 50, 50, 50, 50);
		
		try 
		{
			String dir = System.getProperty("user.home");
			PdfWriter.getInstance(document, new FileOutputStream(dir + "/Resultado_Avaliação.pdf"));
			
			Dialogos dg = new Dialogos();
			String inclusao = dg.getNiveisDeInclusao();
			String niveisDeInclusao = "02. Níveis Hieráquicos"+":"+"\n" + inclusao;
			
			String mapaAprendiz = "(a)Estrutura Hierarquica e Tipos de Aprendizagem\n" 
				+ "01. MC avaliado: MCest = " + "\n " + mapaAluno;
			
			String similaridadeSemantica = "(b)Similaridade Semântica\n" 
				+ "01. Relacao semantica das proposicoes" + ":\n"  
				+ relacaoSemantica
				+ "_______________________________________________________________________________________________________" + "\n"
				+ "\n02. Percentual de proposicoes criadas no MCest em relacao a ontologia de dominio" + ":\n"
				+ "\tResultado = " + notaAvaliacaoGenesFeitos + "% do MCest foi construido levando em conta os 100% das relacoes binarias da ontologia\n"
				+ "_______________________________________________________________________________________________________" + "\n"
				+ "\n03. Percentual de proposicoes corretas avaliadas em relacao as proposicoes criadas no MCest" + ":\n"
				+ "\tResultado = " + notaAvaliacaoGenesCorretos + "% do MCest esta correto levando em conta os " + notaAvaliacaoGenesFeitos + "% das proposicoes criadas\n"
				+ "______________________________________________________________________________________________________" + "\n"
				+ "\n04. Percentual de proposicoes incorretas avaliadas em relacao as proposicoes criadas no MCest" + ":\n" 
				+ "\tResultado = " + notaAvaliacaoGenesIncorretos + "% do MCest esta incorreto levando em conta os " + notaAvaliacaoGenesFeitos + "% das proposicoes criadas\n";
			
			String melhoresCromossomos = "\n(c)MCs gerados pelo GAADT-CM\n" 
				+ "01. Mapas conceituais encontrados pelo AG" + ":"
				+ relatorioMelhores;
			
			
			document.open();
			
			document.add(new Paragraph("                    RESULTADO DA AVALIACAO", 
					FontFactory.getFont(FontFactory.COURIER_OBLIQUE, 14f, Font.NORMAL, BaseColor.RED)));
			
			document.add(new Paragraph(mapaAprendiz, 
					FontFactory.getFont(FontFactory.COURIER_OBLIQUE, 8f, Font.BOLD, BaseColor.BLACK)));
			
			document.add(new Paragraph(niveisDeInclusao, 
					FontFactory.getFont(FontFactory.COURIER_OBLIQUE, 8f, Font.BOLD, BaseColor.BLACK)));
						
			document.add(new Paragraph(similaridadeSemantica, 
					FontFactory.getFont(FontFactory.COURIER_OBLIQUE, 8f, Font.BOLD, BaseColor.RED)));
			
			document.add(new Paragraph(melhoresCromossomos, 
					FontFactory.getFont(FontFactory.COURIER_OBLIQUE, 8f, Font.BOLD, BaseColor.DARK_GRAY)));
			
			
			Runtime.getRuntime().exec("cmd.exe /C"+ 
					System.getProperty("user.home") + System.getProperty("file.separator") + 
					"Resultado_Avaliacao.pdf" );
			
			document.close();					
		} 
		catch (FileNotFoundException e) 
		{
			e.printStackTrace();
		} 
		catch (DocumentException e) 
		{
			e.printStackTrace();
		}		
	}
	
}