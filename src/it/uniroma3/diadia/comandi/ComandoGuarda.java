package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;


public class ComandoGuarda extends AbstractComando {

	//variabili d'istanza
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
		AbstractPersonaggio personaggio = stanza.getPersonaggio();
		if (personaggio != null)
			this.io.mostraMessaggio("Personaggio presente: " + personaggio.getNome());
		else
			this.io.mostraMessaggio("Non c'è nessuno nella stanza.");

		this.io.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		this.io.mostraMessaggio("CFU rimanenti :" + partita.getGiocatore().getCfu());

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
