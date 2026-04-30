package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.ambienti.Stanza;
public class ComandoGuarda implements Comando {
	private String object;
	private String messaggio;
	private IO io;
	@Override
	public void setIo(IO io) {
		this.io=io;
	}
	@Override
	public void esegui(Partita partita) {
		  Stanza stanza = partita.getStanzaCorrente(); 
		  this.io.mostraMessaggio(stanza.getDescrizione());
		  this.io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		  this.io.mostraMessaggio("CFU rimanenti :" + partita.getGiocatore().getCfu());

	}

	@Override
	public void setParametro(String parametro) {
		this.object = parametro;

	}

	@Override
	public String getNome() {
		return "guarda";
	}

	@Override
	public String getParametro() {
		return this.object;
	}

	@Override
	public boolean sconosciuto() {
		return false;
	}

	@Override
	public String getMessaggio() {
		return this.messaggio;
	}
	public void setMessaggio(String msg) {
		this.messaggio = msg;
	}

}
