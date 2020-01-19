package gaadt.ontologia;

public class Conceito 
{
	
	private String descricao;
	
	public Conceito(String descricao) 
	{
		this.descricao = descricao;
	}
	
	public String getDescricao() {
		return this.descricao;
	}
	

	@Override
	public boolean equals(Object obj) {
		if (getClass() != obj.getClass())
			return false;
		Conceito other = (Conceito) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}

	public String toString() {
		return this.descricao;
	}
	
}