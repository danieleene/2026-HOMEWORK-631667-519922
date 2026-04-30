package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
public class ComandoAiuto implements Comando {
		private IO io;
		private static String[] elencoComandi= {"vai", "aiuto", "prendi", "posa", "guarda", "fine"};
		private String messaggio;
	@Override
	public void setIo(IO io)
	{ 
			this.io = io; 
	}
	@Override
	public void esegui(Partita partita) {
		for(int i=0;i<elencoComandi.length;i++) {
			this.io.mostraMessaggio(elencoComandi[i]+" ");
		}
	}

	@Override
	public void setParametro(String parametro) {

	}
	@Override
	public String getNome() {

		return "aiuto";
	}

	@Override
	public String getParametro() {
		return null;
	}

	@Override
	public boolean sconosciuto() {
		return false;
	}

	@Override
	public String getMessaggio() {
		return this.messaggio;
	}
}
