package gaadt.gerador;
import gaadt.ontologia.*;
import gaadt.*;
import java.io.*;
import java.net.MalformedURLException;

import gaadt.xmltools.*;

public class GeradorMP {
	
	private Taxonomia taxonomia;
	private Ontologia ontologia;
	private MapaAprendiz mpAprendiz;
	private GAADT gaadt;
	int corridas = 2;
	int tam_pop = 90;
	
	
	public void setOntologia(String file) throws MalformedURLException 
	{
		TradutorOntologiaXML traducaoOntologia = new TradutorOntologiaXML(file);
		this.ontologia = traducaoOntologia.getOntologia();

	}
	

	public void setTaxonomia(String file) throws MalformedURLException {
		TradutorTaxonomiaXML traducaoTaxo = new TradutorTaxonomiaXML(file);
		this.taxonomia = traducaoTaxo.getTaxonomia();

	}
	
	public void setMCAprendiz(String file) throws MalformedURLException {
		TradutorMapaAprendizXML traducaoMP = new TradutorMapaAprendizXML(file);
		this.mpAprendiz = traducaoMP.getMapaAprendiz();

	}
	
	public void executa() throws IOException 
	{		

		//this.setTaxonomia("taxonomia.xml");
		
		//this.setOntologia(ontologia);
		
		//this.setMCAprendiz(mapa);
		
		this.gaadt = new GAADT();
		this.gaadt.aplicaGAADT(this.ontologia, this.taxonomia, this.mpAprendiz, this.corridas, this.tam_pop);		
	}
	
	public MapaAprendiz getMPAprendiz()
	{
		return this.mpAprendiz;
	}

	public static void main(String[] args) 
	{
		//GeradorMP gerador = new GeradorMP();
		/*
		try 
		{
			gerador.executa();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		*/
	}
	
}