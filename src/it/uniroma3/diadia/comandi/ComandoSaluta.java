package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {


	private static final String MESSAGGIO_CHI = 
			"Chi dovrei salutare?...";

	private String messaggio;
	private IO io;


	@Override
	public void esegui(Partita partita) {

		AbstractPersonaggio personaggio = 
				partita.getStanzaCorrente().getPersonaggio();

		if (personaggio == null) {
			io.mostraMessaggio(MESSAGGIO_CHI);
			return;
		}

		this.messaggio = personaggio.saluta();
		
	}

	@Override
	public void setParametro(String parametro) {
		// Il comando saluta NON usa parametri, ma non possiamo toglierlo!
	}

	@Override
	public boolean sconosciuto() {
		return false;
	}

	public String getMessaggio() {
		return this.messaggio;
	}

	@Override
	public void setIo(IO io) {
		this.io = io;
	}
}
