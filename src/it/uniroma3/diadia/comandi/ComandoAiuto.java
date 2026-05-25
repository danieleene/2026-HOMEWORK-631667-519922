package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;


public class ComandoAiuto extends AbstractComando {

	private IO io;
	private String messaggio;

	@Override
	public void setIo(IO io) {
		this.io = io;
	}

	@Override
	public void esegui(Partita partita) {
		for (String nome : AbstractComando.getNomiComandi()) {
			this.io.mostraMessaggio(nome + " ");
		}
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
