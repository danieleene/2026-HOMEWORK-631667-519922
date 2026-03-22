package it.uniroma3.diadia;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PartitaTest {
	
	private Partita partita;
	@BeforeEach
	public void setUp() {
		this.partita=new Partita();
	}
	@Test
	public void testVintaFinePartita() {
		Stanza vincente= this.partita.getStanzaVincente();
		this.partita.setStanzaCorrente(vincente);
		assertTrue(this.partita.vinta(),"La partita dovrebbe risultare vinta quando si raggiunge la stanza finale");
	}
	@Test
	public void testVinta_InizioPartitaFalse() {
		assertFalse(this.partita.vinta(), "La partita appena iniziata NON deve essere vinta");
	}
	@Test
	public void testVinta_StanzaCorrDiversa() {
		Stanza stanzaFitt= new Stanza("Stanza a caso");
		this.partita.setStanzaCorrente(stanzaFitt);
		assertFalse(this.partita.vinta(), "Se la stanza 'fittizzia' non e' uguale a quella vincente, il giocatore perde");
	}
	
	/*@Test
	void testIsFinita() {
	
	}
*/
	@Test
	public void testGetCfu_InizioPartita() {
		assertEquals(20, this.partita.getCfu());
	}
	@Test
	public void testSetCfu_CreditiInRuntime() {
		int cfuRuntime=12;
		this.partita.setCfu(cfuRuntime);
		assertEquals(cfuRuntime, this.partita.getCfu(), "Il metodo deve restituire il valore dei CFU aggiornato in mezzo alla partita");
	}
	@Test
	public void testCfu_Perso() {
		this.partita.setCfu(0);
		assertEquals(0, this.partita.getCfu(), "A fine runtime, il giocatore deve avere 0 CFU");
	}
}