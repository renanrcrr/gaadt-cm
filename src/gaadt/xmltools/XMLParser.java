package gaadt.xmltools;

import javax.xml.parsers.*;
import org.w3c.dom.*;
import org.xml.sax.*;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;


public abstract class XMLParser {
	
	protected Element elementosXML;
	
	protected void parse(String address, String xmlPath) 
	{
		Element elem = null;
		String err = null;
		
		String path = address + "\\" + xmlPath;
		System.out.println(path);
		
		URL url = XMLParser.class.getClassLoader().getResource(path);
		try 
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
 			Document doc = db.parse(url.getFile());
 			
			System.out.println(doc);
			elem = doc.getDocumentElement();
			this.elementosXML = elem;
		}
		
		catch(ParserConfigurationException pce) 
		{
			err = pce.toString();
			System.out.println("ParserConfigurationException" + "\n" + err);
		}
		
		catch(SAXParseException spe) 
		{
			StringBuffer sb = new StringBuffer(spe.toString());
			sb.append("\n  Numero Linha: " + spe.getLineNumber());
			sb.append("\n Numero Coluna: " + spe.getColumnNumber());
			sb.append("\n ID : " + spe.getPublicId());
			sb.append("\n ID Sistema: " + spe.getSystemId() + "\n");
			err = sb.toString();
			System.out.println("SAXParseException" + "\n" + err);
		}
		catch(SAXException se) 
		{
			err = se.toString();
			if(se.getException() != null)
				err += " CAUSADO POR: " + se.getException().toString();
			System.out.println("SAXException" + "\n" + err);
		}
		catch(IOException ie) 
		{
			err = ie.toString();
			System.out.println("IOException" + "\n" + err);
		}
	}
	
	protected void parse(String xmlPath) throws MalformedURLException 
	{
		Element elem = null;
		String err = null;
		String path = null;
		
		//path  = "gaadt/xml/"  + xmlPath;
		path = "file://" + System.getProperty("user.home") + System.getProperty("file.separator")  + xmlPath;
		
		//URL url = XMLParser.class.getClassLoader().getResource(path);
		URL url = new URL(path);
		try 
		{
			DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
			DocumentBuilder db = dbf.newDocumentBuilder();
 			Document doc = db.parse(url.getPath());
			
 			elem = doc.getDocumentElement();
			this.elementosXML = elem;
		}
		
		catch(ParserConfigurationException pce) 
		{
			err = pce.toString();
			System.out.println("ParserConfigurationException" + "\n" + err);
		}
		
		catch(SAXParseException spe) 
		{
			StringBuffer sb = new StringBuffer(spe.toString());
			sb.append("\n  N� Linha: " + spe.getLineNumber());
			sb.append("\n N� Coluna: " + spe.getColumnNumber());
			sb.append("\n ID : " + spe.getPublicId());
			sb.append("\n ID Sistema: " + spe.getSystemId() + "\n");
			err = sb.toString();
			System.out.println("SAXParseException" + "\n" + err);
		}
		
		catch(SAXException se) 
		{
			err = se.toString();
			if(se.getException() != null)
				err += " CAUSADO POR: " + se.getException().toString();
			System.out.println("SAXException" + "\n" + err);
		}
		
		catch(IOException ie) 
		{
			err = ie.toString();
			System.out.println("IOException" + "\n" + err);
		}
	}	
}

