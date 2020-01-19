package gaadt.xmltools;

import java.net.MalformedURLException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import gaadt.ontologia.Cadeia;
import gaadt.ontologia.Frase;
import gaadt.ontologia.Taxonomia;


public class TradutorTaxonomiaXML extends XMLParser{
	
	private Taxonomia taxonomia;
	
	public TradutorTaxonomiaXML(String path) throws MalformedURLException 
	{
		this.parse(path);
		this.taxonomia = new Taxonomia();
		this.interpretaTaxonomia(this.elementosXML);
	}
	
	private void interpretaTaxonomia(Element elementoSupertipoRaiz) {
		NodeList nodosSupertipo = elementoSupertipoRaiz.getElementsByTagName("supertipo");
		for (int i = 0; i < nodosSupertipo.getLength(); i++) {
			Element elementoSupertipo = (Element)nodosSupertipo.item(i);
			NodeList nodosFilhosSupertipo = elementoSupertipo.getChildNodes();
			if (nodosFilhosSupertipo.getLength() > 0) {
				String strCadeia = elementoSupertipo.getAttribute("valor");
				Element elementoSupertipoPai = (Element)elementoSupertipo.getParentNode();
				while (elementoSupertipoPai.getNodeName().equals("supertipo")) {
					strCadeia = elementoSupertipoPai.getAttribute("valor") + "." + strCadeia;
					elementoSupertipoPai = (Element)elementoSupertipoPai.getParentNode();
				}
				Cadeia cadeia = new Cadeia(strCadeia);
				for (int j = 0 ; j < nodosFilhosSupertipo.getLength(); j++) {
					Node nodoFilhoSupertipo = nodosFilhosSupertipo.item(j);
					if (nodoFilhoSupertipo.getNodeName() == "frase")
						cadeia.addFrase(new Frase(nodoFilhoSupertipo.getFirstChild().getNodeValue()));
				}
				if (cadeia.getFrases().length > 0)
					this.taxonomia.addCadeia(cadeia);
			}
		}
	}
	
	public Taxonomia getTaxonomia() {
		return this.taxonomia;
	}
	
	public static void main(String args[]) throws MalformedURLException {
		TradutorTaxonomiaXML tradutor = new TradutorTaxonomiaXML("taxonomia.xml");
		Cadeia[] cadeias = tradutor.getTaxonomia().getCadeias();
		for(int i = 0; i < cadeias.length; i++) {
			System.out.println();
			System.out.println(i + ") ___________________");
			System.out.println(cadeias[i].toString());
		}
	}
	
}
