package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
public class ComandoPosa implements Comando {
	private String messaggio;
	private String object;
	private IO io;
	@Override
	public void setIo(IO io) {
		this.io=io;
	}
	@Override
	public void esegui(Partita partita) {

		if (object == null) {
			this.io.mostraMessaggio("Cosa vuoi posare?");
			return;
		}

		Attrezzo attrezzo = partita.getGiocatore().getBorsa().removeAttrezzo(object);

		if (attrezzo == null) {
			this.io.mostraMessaggio("Non hai questo attrezzo nella borsa.");
			return;
		}

		Stanza stanzaCorrente = partita.getStanzaCorrente();
		stanzaCorrente.addAttrezzo(attrezzo);

		this.io.mostraMessaggio("Hai posato: " + object);
	}

	@Override
	public void setParametro(String parametro) {
		this.object=parametro;

	}

	@Override
	public String getNome() {
		return "posa";
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
