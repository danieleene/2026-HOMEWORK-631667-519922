package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;


class BorsaTest {

    private Borsa borsa;
    private Attrezzo spada;
    private Attrezzo lanterna;
    

    //Inizializzazione
    @BeforeEach
    void setUp() {
        borsa = new Borsa(10);
        spada = new Attrezzo("spada", 3);
        lanterna = new Attrezzo("lanterna", 2);
    }

    
    

    @Test
    void testGetAttrezzoPresente() {
        borsa.addAttrezzo(spada);
        assertEquals(spada, borsa.getAttrezzo("spada"));
    }

    @Test
    void testGetAttrezzoAssente() {
        assertNull(borsa.getAttrezzo("osso"));
    }

    @Test
    void testGetAttrezzoDopoAggiuntaMultipla() {
        borsa.addAttrezzo(spada);
        borsa.addAttrezzo(lanterna);
        assertEquals(lanterna, borsa.getAttrezzo("lanterna"));
    }

 

   

    @Test
    void testGetPesoVuota() {
        assertEquals(0, borsa.getPeso());
    }

    @Test
    void testGetPesoConUnAttrezzo() {
        borsa.addAttrezzo(spada);
        assertEquals(3, borsa.getPeso());
    }

    @Test
    void testGetPesoConPiuAttrezzi() {
        borsa.addAttrezzo(spada);
        borsa.addAttrezzo(lanterna);
        assertEquals(5, borsa.getPeso());
    }

  

    

    @Test
    void testIsEmptyTrue() {
        assertTrue(borsa.isEmpty());
    }

    @Test
    void testIsEmptyFalse() {
        borsa.addAttrezzo(spada);
        assertFalse(borsa.isEmpty());
    }

    @Test
    void testIsEmptyDopoRimozione() {
        borsa.addAttrezzo(spada);
        borsa.removeAttrezzo("spada");
        assertTrue(borsa.isEmpty());
    }

    
}
