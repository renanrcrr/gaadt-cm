package gaadt;

public class MapaAprendiz extends Cromossomo 
{
	public MapaAprendiz() {
		
	}
	
	public int getNrReconciliacao() {
		int cont_nr_r = 0;
		for (int i = 0; i < this.getGenes().size(); i++) {
			if (this.getGene(i).getAprendizagem().getSigla().equals("r")) {
				cont_nr_r++;
			}
		}
		return cont_nr_r;
	}

}
