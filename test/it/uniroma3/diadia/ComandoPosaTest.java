
package it.uniroma3.diadia;


import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;


class ComandoPosaTest {
	
	private ComandoPosa comando;
	private IO io;
	

	@BeforeEach
	void setUp() {
		this.comando = new ComandoPosa();
		// Iniezione di un IO inerte per l'automazione dei test
		this.io = new IOSimulator(new ArrayList<>());
		this.comando.setIo(this.io);
	}

	@Test
	void testPosa_AttrezzoPresenteInBorsa() {
		// FIXTURE: Stanza iniziale vuota
		Labirinto monolocale = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		Partita partita = new Partita(monolocale);
		
		// PRECONDIZIONE: Inseriamo forzatamente la chiave nella borsa del giocatore
		Attrezzo chiave = new Attrezzo("chiave", 1);
		partita.getGiocatore().getBorsa().addAttrezzo(chiave);

		// ESECUZIONE
		comando.setParametro("chiave");
		comando.esegui(partita);

		// ASSERZIONE: La chiave è passata dalla borsa alla stanza
		assertNull(partita.getGiocatore().getBorsa().getAttrezzo("chiave"), 
				"L'attrezzo è ancora nella borsa del giocatore!");
		assertTrue(partita.getStanzaCorrente().hasAttrezzo("chiave"), 
				"L'attrezzo non si trova nella stanza!");
	}

	@Test
	void testPosa_AttrezzoAssenteInBorsa() {
		Labirinto monolocale = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		Partita partita = new Partita(monolocale);
		
		// PRECONDIZIONE: La borsa è vuota. Non aggiungiamo nulla.

		// ESECUZIONE: Proviamo a posare una spada che non abbiamo
		comando.setParametro("spada");
		comando.esegui(partita);

		// ASSERZIONE: La stanza deve rimanere senza l'attrezzo inventato
		assertFalse(partita.getStanzaCorrente().hasAttrezzo("spada"), 
				"Nella stanza è comparso un attrezzo dal nulla!");
	}

	@Test
	void testPosa_SenzaParametro() {
		Labirinto monolocale = new LabirintoBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		Partita partita = new Partita(monolocale);
		
		// Inseriamo un attrezzo in borsa per sicurezza
		Attrezzo chiave = new Attrezzo("chiave", 1);
		partita.getGiocatore().getBorsa().addAttrezzo(chiave);

		// ESECUZIONE: Il giocatore digita solo "posa" (parametro null)
		comando.setParametro(null);
		comando.esegui(partita);

		// ASSERZIONE: Non succede nulla, la borsa mantiene il suo contenuto
		assertNotNull(partita.getGiocatore().getBorsa().getAttrezzo("chiave"), 
				"La chiave è sparita nonostante il comando fosse invalido!");
	}

}