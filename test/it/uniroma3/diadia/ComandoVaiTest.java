package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;

import it.uniroma3.diadia.comandi.ComandoVai;
public class ComandoVaiTest {

	private ComandoVai comando;
	private IO io;
	
	@BeforeEach
	void setUp() {
		this.comando=new ComandoVai();
		this.io=new IOSimulator(new ArrayList<>());
		this.comando.setIo(this.io);
	}

	@Test
	void testVaiMonolocaleDirInesistente() {
		Labirinto monolocale=Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.getLabirinto();
		Partita partita=new Partita(monolocale);
		comando.setParametro("sud");
		comando.esegui(partita);
		
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
	}
	@Test
	void testVai_Bilocale_DirezioneNull() {
		// FIXTURE 2: Bilocale
		Labirinto bilocale = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanza("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.getLabirinto();
		Partita partita = new Partita(bilocale);
		
		comando.setParametro(null);
		comando.esegui(partita);

		// ASSERZIONE: Senza direzione il giocatore non si muove
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
	}
	@Test
	void testVai_Bilocale_VaiNord() {
		Labirinto bilocale =Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanza("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.getLabirinto();
		Partita partita = new Partita(bilocale);

		comando.setParametro("nord");
		comando.esegui(partita);

		// ASSERZIONE: Il giocatore si è spostato con successo
		assertEquals("Biblioteca", partita.getStanzaCorrente().getNome());
	}
	@Test
	void testVai_Bilocale_VaiNordFallito() {
		Labirinto bilocale = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanza("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.getLabirinto();
		Partita partita = new Partita(bilocale);

		// Passiamo una direzione sbagliata rispetto alla planimetria
		comando.setParametro("sud");
		comando.esegui(partita);

		// ASSERZIONE: Non è arrivato in Biblioteca
		assertNotEquals("Biblioteca", partita.getStanzaCorrente().getNome());
		// Anzi, verifichiamo che sia rimasto esattamente dov'era
		assertEquals("Atrio", partita.getStanzaCorrente().getNome());
	}
	@Test
	void testCfuDiminuisce() {
		Labirinto bilocale = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanza("Biblioteca")
				.addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
				.getLabirinto();
		Partita partita = new Partita(bilocale);
		
		int cfuIniziali = partita.getGiocatore().getCfu();

		comando.setParametro("nord");
		comando.esegui(partita);

		// ASSERZIONE: Il costo dello spostamento è stato detratto
		assertEquals(cfuIniziali - 1, partita.getGiocatore().getCfu());
	}
}

