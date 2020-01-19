package gaadt.ontologia;

public class Frase 
{
    private String descricao; 
    
    public Frase(String descricao) 
    {
        this.descricao = descricao;
    }
    
    public String getDescricao()
    {
        return this.descricao;
    }
    
    @Override
	public boolean equals(Object obj) {
		if (getClass() != obj.getClass())
			return false;
		Frase other = (Frase) obj;
		if (descricao == null) {
			if (other.descricao != null)
				return false;
		} else if (!descricao.equals(other.descricao))
			return false;
		return true;
	}
    

    public String toString() 
    {
        return this.getDescricao();
    }
 }