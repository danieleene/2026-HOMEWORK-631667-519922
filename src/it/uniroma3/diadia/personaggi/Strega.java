package it.uniroma3.diadia.personaggi;


import java.util.ArrayList;
import java.util.List;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {

	private static final String MESSAGGIO_SPOSTA = 
			"La Strega ti ha spostato magicamente nella stanza: ";
	private static final String MESSAGGIO_NESSUNA_STANZA = 
			"La Strega non trova stanze adiacenti dove mandarti...";

	// Costruttore
	public Strega(String nome, String presentazione) {
		super(nome, presentazione);
	}


	@Override
	public String agisci(Partita partita) {

		Stanza stanzaCorrente = partita.getStanzaCorrente();
		List<Stanza> adiacenti = new ArrayList<>(stanzaCorrente.getMapStanzeAdiacenti().values());


		if (adiacenti.isEmpty()) {
			return MESSAGGIO_NESSUNA_STANZA;
		}

		Stanza destinazione;

		if (!this.haSalutato()) {
			// NON salutata = stanza con MENO attrezzi
			destinazione = adiacenti.stream()
					.min((s1, s2) -> Integer.compare(s1.getAttrezzi().size(), s2.getAttrezzi().size()))
					.orElse(stanzaCorrente);
		} else {
			// Salutata = stanza con PIÙ attrezzi
			destinazione = adiacenti.stream()
					.max((s1, s2) -> Integer.compare(s1.getAttrezzi().size(), s2.getAttrezzi().size()))
					.orElse(stanzaCorrente);
		}

		partita.setStanzaCorrente(destinazione);

		return MESSAGGIO_SPOSTA + destinazione.getNome();
	}


	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		// La strega trattiene l’attrezzo (non lo mette nella stanza, non lo restituisce)
		return "Ahahah! Grazie per il regalo, mortale! Lo terrò con me...";
	}

}
