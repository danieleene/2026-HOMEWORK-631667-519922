package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
public class ComandoNonValido implements Comando {
	private String messaggio;
	private IO io;
	@Override
	public void setIo(IO io) {
		this.io=io;
	}
	@Override
	public void esegui(Partita partita) {
		this.io.mostraMessaggio("Comando non valido");

	}
	@Override
	public void setParametro(String parametro) {
	}

	@Override
	public String getNome() {
		return "Non valido";
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
