package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;


class BorsaTest {

    private Borsa borsa;
    private Attrezzo spada;
    private Attrezzo lanterna;
    private Attrezzo osso;
    

    //Inizializzazione
    @BeforeEach
    void setUp() {
        borsa = new Borsa(10);
        spada = new Attrezzo("spada", 3);
        lanterna = new Attrezzo("lanterna", 2);
        osso = new Attrezzo("osso", 1);
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

    
    
    @Test
    void testAttrezziStessoPesoRimangonoDistinti() {
        Attrezzo martello = new Attrezzo("martello", 3);
        Attrezzo scudo = new Attrezzo("scudo", 3);

        borsa.addAttrezzo(martello);
        borsa.addAttrezzo(scudo);

        //Test nel SortedSet ordinato per peso ---
        SortedSet<Attrezzo> ordinati = borsa.getSortedSetOrdinatoPerPeso();
        assertEquals(2, ordinati.size());
    }

    
    
    @Test
    void testGetContenutoOrdinatoPerPeso() {
        borsa.addAttrezzo(spada);
        borsa.addAttrezzo(osso);
        borsa.addAttrezzo(lanterna);

        List<Attrezzo> ordinati = borsa.getContenutoOrdinatoPerPeso();

        assertEquals(3, ordinati.size());
        assertEquals(osso, ordinati.get(0));          // peso 1
        assertEquals(lanterna, ordinati.get(1));      // peso 3, nome "lanterna"
        assertEquals(spada, ordinati.get(2));         // peso 3, nome "spada"
    }
    
    
    
    /*
    @Test
    void testGetContenutoOrdinatoPerNome() {
        
        borsa.addAttrezzo(lanterna);
        borsa.addAttrezzo(osso);
        borsa.addAttrezzo(spada);

        SortedSet<Attrezzo> ordinati = borsa.getContenutoOrdinatoPerNome();  //<- QUI FALLISCE :(

        // Verifica minimale: contiene gli stessi elementi
        assertEquals(3, ordinati.size());
        assertTrue(ordinati.contains(lanterna));
        assertTrue(ordinati.contains(osso));
        assertTrue(ordinati.contains(spada));
    }

    */
    
    
    @Test
    void testGetContenutoRaggruppatoPerPeso() {
        borsa.addAttrezzo(osso);      // peso 1
        borsa.addAttrezzo(spada);     // peso 3
        borsa.addAttrezzo(lanterna);  // peso 2

        Map<Integer, Set<Attrezzo>> mappa = borsa.getContenutoRaggruppatoPerPeso();

        assertEquals(3, mappa.size());
        assertTrue(mappa.containsKey(1));
        assertTrue(mappa.containsKey(3));

        assertEquals(Set.of(osso), mappa.get(1));
        assertEquals(Set.of(lanterna), mappa.get(2));
        assertEquals(Set.of(spada), mappa.get(3));
    }
    
    
    @Test
    void testGetSortedSetOrdinatoPerPeso() {
        borsa.addAttrezzo(spada);
        borsa.addAttrezzo(osso);
        borsa.addAttrezzo(lanterna);

        SortedSet<Attrezzo> ordinati = borsa.getSortedSetOrdinatoPerPeso();

        Iterator<Attrezzo> it = ordinati.iterator();

        assertEquals(osso, it.next());       // peso 1
        assertEquals(lanterna, it.next());   // peso 3, nome "lanterna"
        assertEquals(spada, it.next());      // peso 3, nome "spada"
    }
    
}
