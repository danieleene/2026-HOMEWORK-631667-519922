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
	
	
	
	@Test
	public void testIsFinita_FlagFinitaTrue() {
	    this.partita.setFinita();
	    assertTrue(this.partita.isFinita(), 
	        "La partita deve risultare finita quando viene chiamato setFinita()");
	}

	
	@Test
	public void testIsFinita_CfuZero() {
	    this.partita.getGiocatore().setCfu(0);
	    assertTrue(this.partita.isFinita(),
	        "La partita deve risultare finita quando i CFU del giocatore sono 0");
	}

	
	@Test
	public void testIsFinita_InizioPartitaFalse() {
	    assertFalse(this.partita.isFinita(),
	        "All'inizio la partita non deve essere finita");
	}
	

	
	/*
	 Tolgo i test dei cfu poiche li abbiamo tolti da partita a giocatore
	 */
	
}
