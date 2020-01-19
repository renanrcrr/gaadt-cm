package gaadt.util;

import gaadt.Ambiente;
import gaadt.Cromossomo;
import gaadt.Gene;

import java.util.ArrayList;
import java.util.concurrent.Callable;


public class InsereCromossomosThread implements Callable<ArrayList<Cromossomo>>{

	private ArrayList<Gene> genes_melhores;
	private ArrayList<Cromossomo> cromossomos = new ArrayList<Cromossomo>();
	private int inicio;
	private int fim;
	
	public InsereCromossomosThread(ArrayList<Gene> genes_melhores, int inicio,
			int fim) {
		this.genes_melhores = genes_melhores;
		this.inicio = inicio;
		this.fim = fim;
	}

	public ArrayList<Cromossomo> call() throws Exception {
		for (int i = inicio; i <= fim; i++) {
			Cromossomo c = Ambiente.geraCromossomoAFC(this.genes_melhores);
			cromossomos.add(c);
		}
		return cromossomos;
	}

}