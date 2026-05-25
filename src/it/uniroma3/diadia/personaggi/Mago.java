package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;


public class Mago extends AbstractPersonaggio {

	//variabili d'istanza
	private static final String MESSAGGIO_DONO = "Sei un vero simpaticone, " +
			"con una mia magica azione, troverai un nuovo oggetto " +
			"per il tuo borsone!";

	private static final String MESSAGGIO_SCUSE = "Mi spiace, ma non ho piu' nulla...";

	private Attrezzo attrezzo;

	//Costruttore
	public Mago(String nome, String presentazione, Attrezzo attrezzo) {
		super(nome, presentazione);
		this.attrezzo = attrezzo;
	}


	@Override
	public String agisci(Partita partita) {
		String msg;
		if (this.attrezzo!=null) {
			partita.getStanzaCorrente().addAttrezzo(this.attrezzo);
			this.attrezzo = null;
			msg = MESSAGGIO_DONO;
		}
		else {
			msg = MESSAGGIO_SCUSE;
		}
		return msg;
	}


	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		int pesoOriginale = attrezzo.getPeso();
		int pesoDimezzato = Math.max(1, pesoOriginale / 2);

		Attrezzo attrezzoMagico = new Attrezzo(attrezzo.getNome(), pesoDimezzato);

		partita.getStanzaCorrente().addAttrezzo(attrezzoMagico);

		return "Grazie per il tuo dono! Ho usato un incantesimo per alleggerirlo e lasciarlo qui.";
	}


}