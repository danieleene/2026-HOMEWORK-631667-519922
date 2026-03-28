package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.Giocatore;




class GiocatoreTest {

	
    private Giocatore giocatore;
    

    
    @BeforeEach
    void setUp() {
        this.giocatore = new Giocatore();
    }
    @Test
    void testCostruttoreInizializzaBorsa() {
        assertNotNull(this.giocatore.getBorsa(), "Il giocatore deve possedere un istanza non nulla all'inizio della partita");
    }

    @Test
    void testCostruttoreCreaBorsaVuota() {
        assertTrue(this.giocatore.getBorsa().isEmpty(), "Controlla che la creazione di una borsa vuota avviene con successo");
    }

     
    
    @Test
    void testSetCfuAZero() {
        this.giocatore.setCfu(0);
        assertEquals(0, this.giocatore.getCfu());
    }

    @Test
    void testSetCfuAValoreNegativo() {
        this.giocatore.setCfu(-5);
        assertEquals(-5, this.giocatore.getCfu()); //Nota: il metodo setCfu() non impedisce
                                             // valori negativi!
    }
    
    @Test
    void testSetCfu_CreditiInRuntime() {
		int cfuRuntime=12;
		this.giocatore.setCfu(cfuRuntime);
		assertEquals(cfuRuntime, this.giocatore.getCfu(), "Il metodo deve restituire il valore dei CFU aggiornato in mezzo alla partita");
	}

    @Test
    void testGetCfuDopoModificaNegativa() {
    	this.giocatore.setCfu(-3);                //anche qui...Nota: il metodo setCfu() non impedisce
                                            // valori negativi!
        assertEquals(-3, this.giocatore.getCfu());
    }

    
    
}
