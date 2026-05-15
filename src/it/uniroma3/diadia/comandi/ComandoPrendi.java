package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class ComandoPrendi implements Comando {
	
	//variabili d'istanza
	private String messaggio;
	private String object;
	private IO io;
	
	
	@Override
	public void setIo(IO io) {
		this.io=io;
	}
	@Override
	public void esegui(Partita partita) {
		if (this.object == null) {
			this.io.mostraMessaggio("Cosa vuoi prendere?");
			return;
		}
		Stanza stanzaCorrente = partita.getStanzaCorrente();
		Attrezzo attrezzo = stanzaCorrente.getAttrezzo(object);

		if (attrezzo == null) {
			this.io.mostraMessaggio("Attrezzo inesistente nella stanza.");
			return;
		}

		if (partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) {
			stanzaCorrente.removeAttrezzo(attrezzo);
			this.io.mostraMessaggio("Hai preso: " + object);
		} else {
			this.io.mostraMessaggio("Non puoi prendere questo attrezzo.");
		}

	}
	@Override
	public void setParametro(String parametro) {
		this.object = parametro;

	}

	@Override
	public String getNome() {
		return "prendi";
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

}
