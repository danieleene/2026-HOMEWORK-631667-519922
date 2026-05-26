package it.uniroma3.diadia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.ambienti.StanzaBloccata;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaBloccataTest {
	/*
	 Inseriamo due luoghi diversi, quindi labirinto minimale <<bilocale>> poiche' abbiamo bisogno di una partenza e destinazione
	 per controllare che effettivamente veniamo 'rimbalzati' dalla stanza di destinazione perche' non abbiamo la chiave richiesta
	 */
    private StanzaBloccata stanzaBloccata;
    private Stanza stanzaNord;

    @Before
    public void setUp() {
        stanzaBloccata = new StanzaBloccata("atrio", Direzione.NORD, "chiave");
        stanzaNord = new Stanza("magazzino");
        stanzaBloccata.impostaStanzaAdiacente(Direzione.NORD, stanzaNord);
    }

    @Test
    public void testDirezioneBloccataSenzaAttrezzo() {
        assertSame(stanzaBloccata, stanzaBloccata.getStanzaAdiacente(Direzione.NORD));
    }

    @Test
    public void testDirezioneSbloccataConAttrezzo() {
        stanzaBloccata.addAttrezzo(new Attrezzo("chiave", 1));
        assertSame(stanzaNord, stanzaBloccata.getStanzaAdiacente(Direzione.NORD));
    }

    @Test
    public void testDescrizioneMostraBlocco() {
        String descrizione = stanzaBloccata.getDescrizione();
        assertTrue(descrizione.contains("bloccata"));
        assertTrue(descrizione.contains("chiave"));
    }

    @Test
    public void testDescrizioneNonMostraBloccoQuandoSbloccata() {
        stanzaBloccata.addAttrezzo(new Attrezzo("chiave", 1));
        String descrizione = stanzaBloccata.getDescrizione();
        assertFalse(descrizione.contains("bloccata"));
    }
	
	@Test
	public void testDirezioneLiberaSempreAccessibile() {
	    Stanza stanzaSud = new Stanza("giardino");
	    stanzaBloccata.impostaStanzaAdiacente(Direzione.SUD, stanzaSud);
	    
	    // Il blocco è a "nord". Andare a "sud" DEVE funzionare senza chiave.
	    assertSame("Le direzioni diverse da quella bloccata devono essere sempre accessibili",
	               stanzaSud, 
	               stanzaBloccata.getStanzaAdiacente(Direzione.SUD));
	}
	@Test
    public void testDirezioneBloccataConAttrezzoSbagliato() {
        // Inserisco un attrezzo diverso dalla "chiave"
        stanzaBloccata.addAttrezzo(new Attrezzo("grimaldello", 2));
        
        // Il passaggio verso nord deve rimanere bloccato (restituire this)
        assertSame("La direzione deve rimanere bloccata se si inserisce l'attrezzo sbagliato",
                   stanzaBloccata, 
                   stanzaBloccata.getStanzaAdiacente(Direzione.NORD));
    }
}

