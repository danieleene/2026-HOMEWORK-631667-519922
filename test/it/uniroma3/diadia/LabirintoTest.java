package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;

class LabirintoTest {

	private Labirinto labirinto;
	@BeforeEach
	public void setUp() {
		this.labirinto= new Labirinto();
	}
	@Test
	void testGetStanzaIniziale_Esistente(){
		assertNotNull(this.labirinto.getStanzaIniziale(), "Il labirinto deve essere true per vedere che esiste la stanza iniziale");
	}

	@Test
	void testGetStanzaVincente_Esistente() {
		assertNotNull(this.labirinto.getStanzaVincente(), "Il labirinto deve essere true per vedere che esiste la stanza finale");
	}
	@Test
	void testGetStanzaIniziale_CorrettoInsert() {
		assertEquals("Atrio", this.labirinto.getStanzaIniziale().getNome(), "Il nome della stanza iniziale deve uguale a quella assegnata");
	}
	@Test
	void testGetStanzaFinale_CorrettoInsert() {
		assertEquals("Biblioteca", this.labirinto.getStanzaVincente().getNome(), "Il nome della stanza finale deve uguale a quella assegnata");
	}
	
}
