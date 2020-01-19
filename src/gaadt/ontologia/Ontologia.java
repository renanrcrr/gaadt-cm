package gaadt.ontologia;

import java.util.ArrayList;
import java.util.Vector;

public class Ontologia 
{
	
	private String dominio;
	private ArrayList<Relacao> relacoes;
	private ArrayList<Conceito> conceitos;
	
	public Ontologia(String dominio) {
		this.dominio = dominio;
		this.relacoes = new ArrayList<Relacao>();
		this.conceitos = new ArrayList<Conceito>();
	}
	
	public String getDominio() {
		return this.dominio;
	}
	
	public Relacao[] getRelacoes() {
		return relacoes.toArray(new Relacao[relacoes.size()]);
	}
	
	public void addRelacao(Relacao relacao) {
		if(!this.relacoes.contains(relacao)) {
			this.relacoes.add(relacao);
			this.addConceito(relacao);
		}
	}
	
	private void addConceito(Relacao relacao) {
		Conceito conceito_novo_org = relacao.getConceitoOrigem();
		Conceito conceito_novo_dst = relacao.getConceitoDestino();
		boolean achou_conceito_org = false;
		boolean achou_conceito_dst = false;

		int i = 0;
		while((!achou_conceito_org ||
				!achou_conceito_dst) &&
				i < this.conceitos.size()) {
			Conceito conceito_existente = conceitos.get(i);
            if(conceito_existente.getDescricao().equals(conceito_novo_org.getDescricao()))
                achou_conceito_org = true;                    
            if(conceito_existente.getDescricao().equals(conceito_novo_dst.getDescricao()))                    
                achou_conceito_dst = true;
            i++;
        }
		if (!achou_conceito_org)
			this.conceitos.add(conceito_novo_org);
		if (!achou_conceito_dst)
			this.conceitos.add(conceito_novo_dst);
	}
	
	public Conceito[] getConjuntoDeConceitos() {
		return conceitos.toArray(new Conceito[conceitos.size()]);
	}
	
	public boolean existeConexao(Conceito conceito_1,
			Conceito conceito_2) {
		for (int i = 0; i < this.relacoes.size(); i++) {
			Relacao relacao = this.relacoes.get(i);
			if ((relacao.getConceitoOrigem().getDescricao().equalsIgnoreCase(conceito_1.getDescricao()) &&
					relacao.getConceitoDestino().getDescricao().equalsIgnoreCase(conceito_2.getDescricao())) ||
					(relacao.getConceitoOrigem().getDescricao().equalsIgnoreCase(conceito_2.getDescricao()) &&
					relacao.getConceitoDestino().getDescricao().equalsIgnoreCase(conceito_1.getDescricao()))) 
				return true;
		}
		return false;
	}
	
	public boolean existeRelacaoCadeia(String cadeia) {
		for (int i = 0; i < this.relacoes.size(); i++) {
			Relacao relacao = this.relacoes.get(i);
			if ((relacao.getCadeia().getDescricao().equalsIgnoreCase(cadeia))) 
				return true;
		}
		return false;
	}
	public String toString() {
		String resposta = this.dominio;
		for(int i = 0; i < this.relacoes.size(); i++)
			resposta = resposta + "\n" + this.relacoes.get(i).toString();
		return resposta;
	}
    

}