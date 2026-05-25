package it.uniroma3.diadia.comandi;


import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala extends AbstractComando {

	private static final String MESSAGGIO_CHI = 
			"A chi dovrei regalare qualcosa? Qui non c'è nessuno...";
	private static final String MESSAGGIO_COSA = 
			"Cosa dovrei regalare? Devi specificare un attrezzo presente nella tua borsa.";


	@Override
	public void esegui(Partita partita) {

		// 1. Controllo se c’è un personaggio nella stanza
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio == null) {
			this.getIo().mostraMessaggio(MESSAGGIO_CHI);
			return;
		}

		// 2. Controllo se è stato specificato un attrezzo
		String nomeAttrezzo = this.getParametro();
		if (nomeAttrezzo == null) {
			this.getIo().mostraMessaggio(MESSAGGIO_COSA);
			return;
		}

		// 3. Recupero attrezzo dalla borsa
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo);
		if (attrezzo == null) {
			this.getIo().mostraMessaggio("Non hai questo attrezzo nella borsa.");
			return;
		}

		// 4. Rimuovo l’attrezzo dalla borsa
		partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);

		// 5. Lo consegno al personaggio
		String messaggio = personaggio.riceviRegalo(attrezzo, partita);

		// 6. Mostro il risultato
		this.getIo().mostraMessaggio(messaggio);
	}



	@Override
	public boolean sconosciuto() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public String getMessaggio() {

		return "";
	}
}
