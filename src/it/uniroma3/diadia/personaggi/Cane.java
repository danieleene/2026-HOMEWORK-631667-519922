package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {

	//variabili d'istanza
	private static final String MESSAGGIO_MORSO = 
			"Il Cane ti ha morso: perdi CFU!";


	private int danno;   // quanti CFU toglie ogni morso

	//Costruttore
	public Cane(String nome, String presentazione, int danno) {
		super(nome, presentazione);
		this.danno = danno;
	}


	@Override
	public String agisci(Partita partita) {
		//Cane morde = diminuisce i CFU
		partita.getGiocatore().setCfu(
				partita.getGiocatore().getCfu() - this.danno
				);

		return MESSAGGIO_MORSO;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if (attrezzo.getNome().equals("osso")) {
			// Il cane accetta il regalo e lascia un attrezzo nella stanza
			Attrezzo premio = new Attrezzo("collare", 1);
			partita.getStanzaCorrente().addAttrezzo(premio);
			return "Gnam! Buono l’osso! In cambio ti lascio qualcosa...";
		}

		// Altrimenti morde
		partita.getGiocatore().setCfu(
				partita.getGiocatore().getCfu() - 1
				);
		return "GRRR! Non mi piace questo oggetto! Ti mordo!";
	}


}
