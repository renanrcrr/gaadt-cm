package gaadt;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.io.IOException;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ToolTipManager;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import org.jgraph.graph.DefaultEdge;

import cmeditor.CMEditor;
import cmeditor.desenho.AreaDesenho;
import cmeditor.desenho.elemento.Vertice;
import cmeditor.gui.Acoes;
import cmeditor.gui.BarraFerramentas;
import cmeditor.gui.BarraMenuDoResultado;
import cmeditor.gui.BarraMenus;
import cmeditor.gui.dialogo.Dialogos;
import cmeditor.gui.dialogo.PainelPreferencias;
import cmeditor.gui.dialogo.SeletorArquivo;
import cmeditor.io.ManipuladorXML;
import cmeditor.io.Mapa;

import gaadt.ontologia.Ontologia;
import gaadt.ontologia.Taxonomia;

public class Relatorio extends JFrame 
implements PropertyChangeListener, WindowListener 
{
	AreaDesenho areaResultado;
	
	Acoes acoes;
	
	BarraMenuDoResultado barraMenus;
	
	public Relatorio()
	{
		super();
		this.setTitle("Resultado");
		this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		Dimension dimensao = Toolkit.getDefaultToolkit().getScreenSize();
		this.setSize(dimensao.width, dimensao.height - 30);
		this.getContentPane().setLayout(new BorderLayout());
		areaResultado = new AreaDesenho();
		this.getContentPane().add(areaResultado.pegaBarrasRolagem());
		acoes = new Acoes(areaResultado);
		areaResultado.mudaAcoes(acoes);
		barraMenus = new BarraMenuDoResultado(acoes);
		this.setJMenuBar(barraMenus);
		this.addWindowListener(this);
		this.areaResultado.nomeArquivo.addPropertyChangeListener(this);
		PainelPreferencias.carregarPreferencias(areaResultado);
		ToolTipManager.sharedInstance().registerComponent(areaResultado);
		ToolTipManager.sharedInstance().setInitialDelay(1500); 
		ToolTipManager.sharedInstance().setDismissDelay(20000);
	}
	
	public void imprimirRelatorio()
	{
		try 
		{
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		Relatorio r = new Relatorio();
		r.setVisible(true);
		r.setEnabled(true);
	}
	
	public static void main(String[] args)
	{
		try 
		{
			UIManager.setLookAndFeel(
					UIManager.getSystemLookAndFeelClassName());
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
		Relatorio r = new Relatorio();
		r.setVisible(true);
		r.setEnabled(true);				
	}

	public void imprimirMelhorCromossomoCorrida(double[] fitness, 
			int corridas, 
			Ontologia ontologia,
			Taxonomia taxonomia, 
			MapaAprendiz mpAprendiz, 
			int tam_pop, 
			Cromossomo melhor_cromossomo)
	{
		
		try
		{
			String resultado = null;
			//UIManager.setLookAndFeel(looks[2].getClassName());
			JFrame.setDefaultLookAndFeelDecorated(true);
			JFrame frame = new JFrame("Resultado da Avaliação");
		    frame.setLayout(new FlowLayout());
		    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    
			resultado = "[" + "Cromossomo Gabarito" + "]:" + " " + melhor_cromossomo.getRelatorio(ontologia, mpAprendiz) + "\n";
			    		    
			JTextArea textAreal = new JTextArea(resultado, 15, 100);
		    textAreal.setPreferredSize(new Dimension(40, 40));  
		    JScrollPane scrollPane = new JScrollPane(textAreal, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
		            JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		    textAreal.setLineWrap(true);    
		
		    frame.add(scrollPane);
		    frame.pack();
		    frame.setVisible(true);
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
	}
	
	public void imprimirMelhorMapaCorrida(Cromossomo melhor_cromossomo)
	{
		
		ManipuladorXML h = new ManipuladorXML();
		
		areaResultado.getGraphLayoutCache().insert(
				h.getMapa().pegarVertices().toArray());
		
		for (int i = 0; i < h.getMapa().pegarLigacoesArcos().size(); i++) 
		{
			Vector ligacao = (Vector)h.getMapa().pegarLigacoesArcos().get(i);
			((DefaultEdge)h.getMapa().pegarArcos().get(i)).setSource(
					((Vertice)h.getMapa().pegarVertices().get(((Integer)
							ligacao.get(0)).intValue())).pegaPortPadrao());
			((DefaultEdge)h.getMapa().pegarArcos().get(i)).setTarget(
					((Vertice)h.getMapa().pegarVertices().get(((Integer)
							ligacao.get(1)).intValue())).pegaPortPadrao());
		}
		areaResultado.getGraphLayoutCache().insert(
				h.getMapa().pegarArcos().toArray());
		
		areaResultado.setSelectionCells(new Object[] {});
		
	}
		
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		
		
	}

	@Override
	public void windowActivated(WindowEvent e) {
		
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
		
		
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
		
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		
		
	}
	
} 
