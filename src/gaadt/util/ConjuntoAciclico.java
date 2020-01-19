package gaadt.util;

import java.util.Vector;

public class ConjuntoAciclico {
	
	private Vector elementos = new Vector();
	private Vector vizinhanca = new Vector();
	
	private void addElemento(Object elemento) {
		if(!this.elementos.contains(elemento)) {
			this.elementos.add(elemento);
			Vector vizinhos = new Vector();
			vizinhos.add(elemento);
			
			int index = this.elementos.indexOf(elemento);
			this.vizinhanca.add(index, vizinhos);
		}
	}
	
	public boolean setVizinho(Object elemento,
			Object vizinho) {
		this.addElemento(elemento);
		
		this.addElemento(vizinho);
		
		int index = this.elementos.indexOf(elemento);
		
		Vector vizinhos = (Vector)this.vizinhanca.get(index);
		if(vizinhos.contains(vizinho))
			return false;
		else {
			vizinhos.add(vizinho);
			int index_vizinho = this.elementos.indexOf(vizinho);
			Vector vizinhos_vizinho = new Vector();
			vizinhos_vizinho = (Vector)this.vizinhanca.get(index_vizinho);
			
			for(int i = 1; i < vizinhos_vizinho.size(); i++) {
				Object vizinho_vizinho = vizinhos_vizinho.get(i);
				
				boolean flag = this.setVizinho(elemento, vizinho_vizinho);
				if(!flag)
					return false;
			}
			return true;
		}
	}
	
}
