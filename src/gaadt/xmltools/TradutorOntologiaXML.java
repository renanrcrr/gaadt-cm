package gaadt.xmltools;

import java.io.File;
import java.net.MalformedURLException;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import gaadt.ontologia.Cadeia;
import gaadt.ontologia.Conceito;
import gaadt.ontologia.Frase;
import gaadt.ontologia.Ontologia;
import gaadt.ontologia.Relacao;

public class TradutorOntologiaXML extends XMLParser {
	
	private Ontologia ontologia;
	
	private VectorToken tokens_valor_relacao;
	
	public TradutorOntologiaXML(String address, String path) 
	{
		this.parse(address, path);
		this.tokens_valor_relacao = new VectorToken();
		this.interpretaValorRelacao();
		this.interpretaOntologia();
	}
	
	public TradutorOntologiaXML(String path) throws MalformedURLException 
	{
		this.parse(path);
		this.tokens_valor_relacao = new VectorToken();
		this.interpretaValorRelacao();
		this.interpretaOntologia();
	}
	
	private void interpretaValorRelacao() throws NullPointerException 
	{
		NodeList valor_relacao = this.elementosXML.getElementsByTagName("valor-relacao");
		for (int i = 0; i < valor_relacao.getLength(); i++) 
		{
			String cadeia = null;
			Element tag_valor_relacao = (Element)valor_relacao.item(i);
			String id = tag_valor_relacao.getAttribute("cod");
			NodeList supertipo = tag_valor_relacao.getElementsByTagName("supertipo");
			Element tag_supertipo = (Element)supertipo.item(0);
			cadeia = tag_supertipo.getAttribute("cadeia");
			TokenValorRelacao token = new TokenValorRelacao(id, cadeia);
			NodeList lista_frases = tag_valor_relacao.getElementsByTagName("frase");
			for (int j = 0; j < lista_frases.getLength(); j++) {
				Element tag_lista_frases = (Element)lista_frases.item(j);
				String frase = tag_lista_frases.getFirstChild().getNodeValue();
				token.addFrase(frase);
			}
			this.tokens_valor_relacao.add(token);
		}
	}
	
	private void interpretaOntologia() throws NullPointerException {
		NodeList nodo_dominio = this.elementosXML.getElementsByTagName("dominio");
		Element tag_dominio = (Element)nodo_dominio.item(0);
		String dominio = tag_dominio.getFirstChild().getNodeValue();
		
		this.ontologia = new Ontologia(dominio);
		
		NodeList relacao_binaria = this.elementosXML.getElementsByTagName("relacao-binaria");
		
		for (int i = 0; i < relacao_binaria.getLength(); i++) 
		{
			Element tag_relacao_binaria = (Element)relacao_binaria.item(i);
			NodeList nodo_conceito_origem = tag_relacao_binaria.getElementsByTagName("conceito-origem");
			NodeList nodo_conceito_destino = tag_relacao_binaria.getElementsByTagName("conceito-destino");
			NodeList nodo_cod_valor_relacao = tag_relacao_binaria.getElementsByTagName("cod-valor-relacao");
			
			Element tag_conceito_origem = (Element)nodo_conceito_origem.item(0);
			Element tag_conceito_destino  = (Element)nodo_conceito_destino.item(0);
			Element tag_cod_valor_relacao = (Element)nodo_cod_valor_relacao.item(0);
			
			String str_conceito_origem = tag_conceito_origem.getFirstChild().getNodeValue();
			String str_conceito_destino = tag_conceito_destino.getFirstChild().getNodeValue();
			String str_cod_valor_relacao = tag_cod_valor_relacao.getFirstChild().getNodeValue();
			
			TokenValorRelacao token_valor_relacao = this.tokens_valor_relacao.getTokenValorRelacaoId(str_cod_valor_relacao);
			Cadeia cadeia = new Cadeia(token_valor_relacao.getCadeia());
			String frases[] = token_valor_relacao.getFrases();
			
			for(int j = 0; j < frases.length; j++) 
			{
				Frase frase = new Frase(frases[j]);
				cadeia.addFrase(frase);
			}
			
			Conceito conceito_origem = new Conceito(str_conceito_origem);
			Conceito conceito_destino = new Conceito(str_conceito_destino);
			
			Relacao relacao = new Relacao(conceito_origem, conceito_destino, cadeia);
			 
			this.ontologia.addRelacao(relacao);
		}
	}
	public Ontologia getOntologia() {
		return this.ontologia;
	}
	/*
	public static void main(String[] args) {
		TradutorOntologiaXML t = new TradutorOntologiaXML("ontologia_ciclo_agua.xml");
		Relacao[] relacoes = t.getOntologia().getRelacoes();
		for (int i = 0; i < relacoes.length; i++) {
			System.out.println(i + ")" + relacoes[i].toString() + "\n");
		}
	}
	*/
}
