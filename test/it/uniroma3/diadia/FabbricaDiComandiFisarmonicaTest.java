package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

public class FabbricaDiComandiFisarmonicaTest {

	private FabbricaDiComandiFisarmonica factory;
	private IO io;

	@BeforeEach
	void setUp() {
		this.factory = new FabbricaDiComandiFisarmonica();
		this.io=new IOConsole();
	}

	@Test
	void testComandoVai() {
		Comando c = factory.costruisciComando("vai nord", this.io);
		assertEquals("vai", c.getNome());
		assertEquals("nord", c.getParametro());
	}
	

	@Test
	void testComandoAiuto() {
		Comando c = factory.costruisciComando("aiuto",this.io);
		assertEquals("aiuto", c.getNome());
		assertNull(c.getParametro());
	}


	@Test
	void testComandoFine() {
		Comando c = factory.costruisciComando("fine",this.io);
		assertEquals("fine", c.getNome());
		assertNull(c.getParametro());
	}

	@Test
	void testComandoGuarda() {
		Comando c = factory.costruisciComando("guarda",this.io);
		assertEquals("guarda", c.getNome());
		assertNull(c.getParametro());
	}

	@Test
	void testComandoNonValido() {
		Comando c = factory.costruisciComando("xyz",this.io);
		assertEquals("Non valido", c.getNome());
		assertNull(c.getParametro());
	}

	
	
	
	@Test
	void testComandoPrendi() {
	    Comando c = factory.costruisciComando("prendi chiave",this.io);
	    assertEquals("prendi", c.getNome());
	    assertEquals("chiave", c.getParametro());
	}

	@Test
	void testComandoPosa() {
	    Comando c = factory.costruisciComando("posa lanterna",this.io);
	    assertEquals("posa", c.getNome());
	    assertEquals("lanterna", c.getParametro());
	}

}