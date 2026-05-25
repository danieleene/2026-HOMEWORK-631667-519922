package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;
import java.util.Scanner;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.AbstractComando;

class AbstractComandoTest {

	private AbstractComando comando;
	private Partita partita;

	/* Classe concreta fittizia per testare AbstractComando */
	private static class ComandoTest extends AbstractComando {
		@Override
		public void esegui(Partita partita) {
			partita.setFinita();
		}

		@Override
		public boolean sconosciuto() {
			// TODO Auto-generated method stub
			return false;
		}

		@Override
		public String getMessaggio() {
			// TODO Auto-generated method stub
			return null;
		}
	}


	@BeforeEach
	void setUp() {
		Labirinto labirinto = new Labirinto();   // uso il labirinto predefinito
		partita = new Partita(labirinto);
		comando = new ComandoTest();
	}


	@Test
	void testNomeComandoDerivatoDalNomeClasse() {
		assertEquals("test", comando.getNome());
	}

	@Test
	void testRegistroStaticoContieneIlComando() {
		List<String> registrati = AbstractComando.getNomiComandi();
		assertTrue(registrati.contains("test"));
	}

	@Test
	void testParametro() {
		comando.setParametro("chiave");
		assertEquals("chiave", comando.getParametro());
	}

	@Test
	void testSetEGetIO() {
		IO io = new IOConsole(new Scanner(""));
		comando.setIo(io);
		assertSame(io, comando.getIo());
	}

	@Test
	void testEseguiImpostaPartitaFinita() {
		assertFalse(partita.isFinita());
		comando.esegui(partita);
		assertTrue(partita.isFinita());
	}

	@Test
	void testRegistroNonContieneDuplicati() {
		int sizeBefore = AbstractComando.getNomiComandi().size();
		new ComandoTest(); // crea un altro comando con stesso nome
		int sizeAfter = AbstractComando.getNomiComandi().size();
		assertEquals(sizeBefore, sizeAfter);
	}



}
