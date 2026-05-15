package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

	//variabili d'istanza
	private String direzioneBloccata;
	private String attrezzoSbloccante;

	//Costruttore
	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;
	}

	// Restituisce la stanza adiacente nella direzione indicata.
	// Se la direzione è bloccata e manca l'attrezzo sbloccante,
	// il giocatore rimane nella stanza corrente.
	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (direzione.equals(this.direzioneBloccata) && !this.hasAttrezzo(this.attrezzoSbloccante))
			return this; // rimane nella stanza corrente!
		return super.getStanzaAdiacente(direzione);
	}

	
	
	// Restituisce la descrizione della stanza.
	// Se la direzione è bloccata e manca l'attrezzo sbloccante,
	// aggiunge un messaggio che indica il blocco e l'attrezzo necessario.
	@Override
	public String getDescrizione() {
		StringBuilder descrizione = new StringBuilder(super.getDescrizione());
		if (!this.hasAttrezzo(this.attrezzoSbloccante)) {
			descrizione.append("\nLa direzione '")
			.append(this.direzioneBloccata)
			.append("' Ã¨ bloccata. Serve l'attrezzo: ")
			.append(this.attrezzoSbloccante);
		}
		return descrizione.toString();
	}
}
