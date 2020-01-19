package gaadt.xmltools;

import java.util.Vector;

public class VectorToken {
	
	private Vector<Token> tokens;
	
	public VectorToken() {
		this.tokens = new Vector<Token>();
	}
	
	public void add(Token token) {
		this.tokens.add(token);
	}
	
	public Token getTokenId(String id) {
		for(int i = 0; i < this.tokens.size(); i++) {
			Token token = (Token)this.tokens.get(i);
			if (token instanceof TokenDeConceito) {
				TokenDeConceito token_conceito = (TokenDeConceito)token;
				if (token_conceito.id().equals(id))
					return token_conceito;
            }
			if (token instanceof TokenDeFrase) {
				TokenDeFrase token_frase = (TokenDeFrase)token;
				if (token_frase.id().equals(id))
					return token_frase;
			}
		}
		return null;
	}
	
	public TokenValorRelacao getTokenValorRelacaoId(String id) {
		for(int i = 0; i < this.tokens.size(); i++) {
			Token token = (Token)this.tokens.get(i);
			if (token instanceof TokenValorRelacao) {
				TokenValorRelacao token_vr = (TokenValorRelacao)token;
				if (token_vr.getId().equals(id))
					return token_vr;
			}
		}
		return null;
	}
	
}