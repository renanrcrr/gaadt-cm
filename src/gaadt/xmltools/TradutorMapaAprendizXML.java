package gaadt.xmltools;

import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.Vector;

import javax.swing.JOptionPane;

import org.w3c.dom.*;

import gaadt.*;


public class TradutorMapaAprendizXML extends XMLParser {
	
	private MapaAprendiz mapaAprendiz;
	private VectorToken tokens_nodo;
	private Vector<TokenDeArco> tokens_arco;
	
	
	public TradutorMapaAprendizXML(String path) throws MalformedURLException 
	{
		this.parse(path);
		
		this.tokens_nodo = new VectorToken();
		this.tokens_arco = new Vector<TokenDeArco>();
		
		this.interpretaConceitos();
		
		this.interpretaFrases();
		
		this.interpretaArcos();
		this.interpretaMapaAprendiz(); 
	}
	
	
	public MapaAprendiz getMapaAprendiz(){
		return this.mapaAprendiz;
	}
	
	
	private void interpretaConceitos() throws NullPointerException {
		NodeList nodo_conceitos = this.elementosXML.getElementsByTagName("conceito");
		for (int i = 0; i < nodo_conceitos.getLength(); i++){
			Element tag_conceito = (Element)nodo_conceitos.item(i);
			String id = tag_conceito.getAttribute("id");
			String texto = tag_conceito.getAttribute("texto");
			
			this.tokens_nodo.add(new TokenDeConceito(texto, id));
		}
	}
	
	private void interpretaFrases() 
	{
		NodeList nodo_frases = this.elementosXML.getElementsByTagName("frasecontextualizada");
		
		try
		{
			for (int i = 0; i < nodo_frases.getLength(); i++) 
			{
				Element tag_frase = (Element)nodo_frases.item(i);
				String id = tag_frase.getAttribute("id");
				String texto = tag_frase.getAttribute("texto");
				String supertipo = tag_frase.getAttribute("supertipo");
				this.tokens_nodo.add(new TokenDeFrase(texto, supertipo, id));
			}
		}
		catch(NullPointerException e)
		{
			JOptionPane.showMessageDialog(null, "Um único conceito não é considerado um mapa conceitual, " +
					"logo, não pode ser avaliado!", "Erro de Avaliação", JOptionPane.ERROR_MESSAGE);
			
			e.printStackTrace();
			
		}
		
	}
	
	
	private void interpretaArcos() throws NullPointerException 
	{
		NodeList nodo_arcos = this.elementosXML.getElementsByTagName("arco");
		for (int i = 0; i < nodo_arcos.getLength(); i++) 
		{
			Element tag_arco = (Element)nodo_arcos.item(i);
			String origem = tag_arco.getAttribute("origem");
			String alvo = tag_arco.getAttribute("alvo");
			String tipo = tag_arco.getAttribute("tipo");
			this.tokens_arco.add(new TokenDeArco(tipo, origem, alvo));
		}
	}
	
	
	private void interpretaMapaAprendiz() 
	{
		this.mapaAprendiz = new MapaAprendiz();
		for(int i = 0; i < this.tokens_arco.size(); i++ ) 
		{ 
			TokenDeArco arco = (TokenDeArco)this.tokens_arco.get(i);
			Token nodo_origem = this.tokens_nodo.getTokenId(arco.origem());
			Token nodo_alvo = this.tokens_nodo.getTokenId(arco.alvo());
			
	
			if(nodo_origem instanceof TokenDeConceito &&
					nodo_alvo instanceof TokenDeFrase) 
			{
				TokenDeConceito conceito_origem = (TokenDeConceito)nodo_origem;
				for(int j = 0; j < this.tokens_arco.size(); j++) 
				{
					TokenDeArco arco_prox = (TokenDeArco)this.tokens_arco.get(j);
					Token nodo_origem_prox = this.tokens_nodo.getTokenId(arco_prox.origem());
					Token nodo_alvo_prox = this.tokens_nodo.getTokenId(arco_prox.alvo());
					
	
					if(arco.alvo().equals(arco_prox.origem()) &&
							(nodo_alvo_prox instanceof TokenDeConceito)) 
					{
						TokenDeFrase frase = (TokenDeFrase)nodo_origem_prox;
						TokenDeConceito conceito_destino = (TokenDeConceito)nodo_alvo_prox;
						
						Gene gene = new Gene(conceito_origem.texto(),
								conceito_destino.texto(),
								frase.texto(),
								frase.supertipo(),
								arco.tipo());
						this.mapaAprendiz.setGene(gene);
					}
				}
			}
		}
	}
	
	/*
	public static void main(String[] args) {
		TradutorMapaAprendizXML t = new TradutorMapaAprendizXML("mapa_ciclo_agua.xml");
		ArrayList<Gene> genes = t.getMapaAprendiz().getGenes();
		for (int i = 0; i < genes.size(); i++) {
			if (genes.get(i) instanceof Gene) {
				Gene gene = (Gene)genes.get(i);
				System.out.println(gene.toString());
			}
		}
	}*/
	
}
