package it.uniroma3.diadia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBuiaTest {
	/*
	 * Il labirinto miniamale e' <<monolocale>> poiche' ci basta sapere solo se il contenuto della stanza sia uguale a quello richiesto
	 * per l'illuminazione
	 */
    private StanzaBuia stanzaBuia;

    @Before
    public void setUp() {
        stanzaBuia = new StanzaBuia("cantina", "lanterna");
    }

    @Test
    public void testDescrizioneSenzaAttrezzoIlluminante() {
        assertEquals("qui c'è un buio pesto", stanzaBuia.getDescrizione());
    }

    @Test
    public void testDescrizioneConAttrezzoIlluminante() {
        stanzaBuia.addAttrezzo(new Attrezzo("lanterna", 1));
        assertNotEquals("qui c'è un buio pesto", stanzaBuia.getDescrizione());
        assertTrue(stanzaBuia.getDescrizione().contains("cantina"));
    }

    @Test
    public void testStanzaBuiaSiComportaComeStanzaNormale() {
        stanzaBuia.addAttrezzo(new Attrezzo("chiave", 1));
        assertTrue(stanzaBuia.hasAttrezzo("chiave"));
    }
}
