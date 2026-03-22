package it.uniroma3.diadia;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;

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
	
	/*
	 Tolgo i test dei cfu poiche li abbiamo tolti da partita a giocatore
	 */
	
}