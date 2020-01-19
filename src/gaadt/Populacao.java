package gaadt;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Vector;
import gaadt.ontologia.*;

public class Populacao 
{	
	private ArrayList<Cromossomo> cromossomos = new ArrayList<Cromossomo>();
	
	public static Populacao geraPopulacao(Vector<Gene> genes,
			int tam_pop) 
	{
		Populacao resposta = new Populacao();
		for(int i = 0; i < tam_pop; i++)
			resposta.cromossomos.add(Ambiente.geraCromossomoAFC(genes));
		return resposta;
	}
	
	public int getNr_cromossomos() 
	{
		return this.cromossomos.size();
	}
	
	public Cromossomo getCromossomo(int i) 
	{
		if(i < this.cromossomos.size())
			return this.cromossomos.get(i);
		return null;
	}
	
	public Cromossomo[] selecionaMelhores(Ontologia ontologia,
			MapaAprendiz mp_aprendiz) {
		ArrayList<Cromossomo> cromossomos_selecionados = new ArrayList<Cromossomo>();
		double mediaAdaptacao = this.getMediaAdaptacao(ontologia, mp_aprendiz);
		
		for(int i = 0 ; i < this.cromossomos.size(); i++) {
			Cromossomo cromossomo = this.cromossomos.get(i);
			int adaptacaoCromossomo = cromossomo.getAdaptacao(ontologia, mp_aprendiz);
			if(adaptacaoCromossomo > mediaAdaptacao)
				cromossomos_selecionados.add(cromossomo);
		}
		
		return cromossomos_selecionados.toArray(new Cromossomo[cromossomos_selecionados.size()]);
	}
	
	public void insereCromossomo(Cromossomo cromossomo) 
	{
		this.cromossomos.add(cromossomo);
	}
	
	public void insereCromossomos(ArrayList<Cromossomo> cromossomos) {
		this.cromossomos.addAll(cromossomos);
	}
	

	public void removeCromossomo(int posicao) 
	{
		if (posicao < this.cromossomos.size())
			this.cromossomos.remove(posicao);
	}
	
	public int getPosicao(Cromossomo cromossomo) {
		return cromossomos.indexOf(cromossomo);
	}
	
	public void esvazia() {
		this.cromossomos.removeAll(cromossomos);
	}
	
	public double getMediaAdaptacao(Ontologia ontologia,
			MapaAprendiz mpAprendiz) 
	{
		double media = 0.0;
		for(int i = 0; i < cromossomos.size(); i++)
			media += cromossomos.get(i).getAdaptacao(ontologia, mpAprendiz);
		media /= cromossomos.size();
		return media;
	}
	
	private ArrayList<Cromossomo> melhoresCromossomo;	
	
	public void setMelhoresCromossomos(ArrayList<Cromossomo> respostaMelhores)
	{
		this.melhoresCromossomo = respostaMelhores;
	}
	
	public ArrayList<Cromossomo> getMelhoresCromossomos()
	{
		return melhoresCromossomo; 
	}
	
	public Cromossomo getMelhor(Ontologia ontologia,
			MapaAprendiz mpAprendiz) 
	{
		Cromossomo respostaMelhor = this.getCromossomo(0);
		int adapt = respostaMelhor.getAdaptacao(ontologia, mpAprendiz);
				
		for(int i = 1; i < this.cromossomos.size(); i++) 
		{
			int temp = this.getCromossomo(i).getAdaptacao(ontologia, mpAprendiz);
			if(adapt < temp) 
			{
				adapt = temp;				
				respostaMelhor = this.getCromossomo(i);
			}
		}
		return respostaMelhor;
	}
	
	public List<Cromossomo> getMelhores(Ontologia ontologia,
			MapaAprendiz mpAprendiz) 
	{
		Cromossomo respostaMelhor = this.getCromossomo(0);
		int adapt = respostaMelhor.getAdaptacao(ontologia, mpAprendiz);
		List<Cromossomo> melhores = new LinkedList<Cromossomo>();		
		
		for(int i = 1; i < this.cromossomos.size(); i++) 
		{
			int temp = this.getCromossomo(i).getAdaptacao(ontologia, mpAprendiz);
			if(adapt < temp) 
			{
				adapt = temp;				
				respostaMelhor = this.getCromossomo(i);									
			}
			else if(adapt == temp) 
			{
				melhores.add(this.getCromossomo(i));
				//setMelhoresCromossomos(melhores);
			}
		}
						
		return melhores;
	}
	
	public Cromossomo getPior(Ontologia ontologia,
			MapaAprendiz mpAprendiz) {
		Cromossomo resposta = this.getCromossomo(0);
		int adapt = resposta.getAdaptacao(ontologia, mpAprendiz);
		
		for(int i = 1; i < this.cromossomos.size(); i++) {
			int temp = this.getCromossomo(i).getAdaptacao(ontologia, mpAprendiz);
			if(adapt > temp) {
				adapt = temp;
				resposta = this.getCromossomo(i);
			}
		}
		return resposta;
	}
	
	
	public String toSring() {
		String resposta = new String();
		for(int i = 0; i < this.cromossomos.size(); i++)
			resposta = resposta + ((Cromossomo)this.cromossomos.get(i)).toString() + "\n";
		return resposta;
	}
	
}