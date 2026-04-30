package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {

	private String direzioneBloccata;
	private String attrezzoSbloccante;

	public StanzaBloccata(String nome, String direzioneBloccata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneBloccata;
		this.attrezzoSbloccante = attrezzoSbloccante;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (direzione.equals(this.direzioneBloccata) && !this.hasAttrezzo(this.attrezzoSbloccante))
			return this; // rimane nella stanza corrente!
		return super.getStanzaAdiacente(direzione);
	}

	@Override
	public String getDescrizione() {
		StringBuilder descrizione = new StringBuilder(super.getDescrizione());
		if (!this.hasAttrezzo(this.attrezzoSbloccante)) {
			descrizione.append("\nLa direzione '")
			.append(this.direzioneBloccata)
			.append("' è bloccata. Serve l'attrezzo: ")
			.append(this.attrezzoSbloccante);
		}
		return descrizione.toString();
	}
}
