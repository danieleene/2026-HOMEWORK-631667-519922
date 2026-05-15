package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {
	
	//variabili d'istanza
	private String messaggio;
	private IO io;
	
	
	@Override
	public void setIo(IO io) {
		this.io=io;
	}
	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Grazie per aver giocato!");
		partita.setFinita();
	}

	@Override
	public void setParametro(String parametro) {
		
	}

	@Override
	public String getNome() {
		return "fine";
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
