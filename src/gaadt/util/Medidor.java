package gaadt.util;

import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Medidor {

	private long inicio;
	private long fim;
	private int acessos;

	public Medidor() {}
	
	private static final Date TEMPOFILTRO = new Date(0);

	public void tempoInicial() {
		inicio = System.currentTimeMillis();
	}

	public void tempoFinal(String nomeDoMetodo) {
		fim = System.currentTimeMillis();		
		long tempoDoMetodo = fim - inicio;
		SimpleDateFormat df = new SimpleDateFormat("mm:ss:SS");

		Date tempo = new Date(tempoDoMetodo);

		if (tempo.after(TEMPOFILTRO)) {
				System.out.println("Nome do Método= " + nomeDoMetodo
						+ "; Tempo do método = " + df.format(tempo));
		}
	}
}
