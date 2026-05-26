package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;

class ComandoPrendiTest {

	private ComandoPrendi comando;
	private IO io;

	@BeforeEach
	void setUp() {
		this.comando = new ComandoPrendi();
		// Iniezione di un IO fittizio (Mock) per non bloccare JUnit
		this.io = new IOSimulator(new ArrayList<>());
		this.comando.setIo(this.io);
	}

	@Test
	void testPrendi_AttrezzoPresenteInStanza() {
		// FIXTURE: Creiamo un monolocale e usiamo il metodo addAttrezzo del Builder 
		// per posizionare l'oggetto direttamente sulla mappa valida.
		Labirinto monolocale = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("chiave", 1) 
				.getLabirinto();
		Partita partita = new Partita(monolocale);

		// ESECUZIONE
		comando.setParametro("chiave");
		comando.esegui(partita);

		// ASSERZIONE: Trasferimento avvenuto con successo
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("chiave"), 
				"L'attrezzo si trova ancora sul pavimento della stanza!");
		assertNotNull(partita.getGiocatore().getBorsa().getAttrezzo("chiave"), 
				"L'attrezzo non è stato inserito nella borsa del giocatore!");
	}

	@Test
	void testPrendi_AttrezzoAssenteInStanza() {
		// FIXTURE: Monolocale completamente vuoto
		Labirinto monolocale = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		Partita partita = new Partita(monolocale);

		// ESECUZIONE: Tentativo di raccogliere un oggetto inesistente
		comando.setParametro("spada");
		comando.esegui(partita);

		// ASSERZIONE: La borsa del giocatore deve rimanere intonsa
		assertNull(partita.getGiocatore().getBorsa().getAttrezzo("spada"), 
				"Il giocatore ha magicamente raccolto un attrezzo che non esisteva!");
	}

	@Test
	void testPrendi_SenzaParametro() {
		// FIXTURE: Monolocale con attrezzo a terra
		Labirinto monolocale =Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("chiave", 1)
				.getLabirinto();
		Partita partita = new Partita(monolocale);

		// ESECUZIONE: L'utente digita "prendi" senza specificare cosa
		comando.setParametro(null);
		comando.esegui(partita);

		// ASSERZIONE: Nessuna operazione di sistema deve aver alterato lo stato del gioco
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("chiave"), 
				"L'attrezzo è sparito dalla stanza a causa di un comando malformato!");
		assertNull(partita.getGiocatore().getBorsa().getAttrezzo("chiave"), 
				"L'attrezzo è entrato in borsa da solo!");
	}
}
