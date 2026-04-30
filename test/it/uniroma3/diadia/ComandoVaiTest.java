package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.ComandoVai;
public class ComandoVaiTest {

	private Partita partita;
	private Stanza stanzaIniziale;
	private Stanza stanzaNord;
	private ComandoVai comando;
	private IO io;
	
	@BeforeEach
	void setUp() {
		partita = new Partita();
		stanzaIniziale = new Stanza("Atrio");
		stanzaNord = new Stanza("Biblioteca");

		stanzaIniziale.impostaStanzaAdiacente("nord", stanzaNord);
		partita.setStanzaCorrente(stanzaIniziale);

		comando = new ComandoVai();
		this.io=new IOConsole();
		comando.setIo(this.io);
	}

	
	@Test
	void testDirezioneNull() {
		comando.setParametro(null);

		// Non deve cambiare stanza
		comando.esegui(partita);

		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
	}

	
	@Test
	void testDirezioneInesistente() {
		comando.setParametro("sud");

		comando.esegui(partita);

		// Rimane nella stanza iniziale
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
	}

	
	@Test
	void testVaiNord() {
		comando.setParametro("nord");

		comando.esegui(partita);

		assertEquals("Biblioteca", partita.getStanzaCorrente().getNome());
	}
	@Test
	void testVaiNordFallito() {
		comando.setParametro("sud");

		comando.esegui(partita);

		assertNotEquals("Biblioteca", partita.getStanzaCorrente().getNome());
	}


	
	@Test
	void testCfuDiminuisce() {
		int cfuIniziali = partita.getGiocatore().getCfu();

		comando.setParametro("nord");
		comando.esegui(partita);

		assertEquals(cfuIniziali - 1, partita.getGiocatore().getCfu());
	}
}

