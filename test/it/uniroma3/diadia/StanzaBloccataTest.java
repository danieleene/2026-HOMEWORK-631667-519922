package it.uniroma3.diadia;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

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
        stanzaBloccata = new StanzaBloccata("atrio", "nord", "chiave");
        stanzaNord = new Stanza("magazzino");
        stanzaBloccata.impostaStanzaAdiacente("nord", stanzaNord);
    }

    @Test
    public void testDirezioneBloccataSenzaAttrezzo() {
        assertSame(stanzaBloccata, stanzaBloccata.getStanzaAdiacente("nord"));
    }

    @Test
    public void testDirezioneSbloccataConAttrezzo() {
        stanzaBloccata.addAttrezzo(new Attrezzo("chiave", 1));
        assertSame(stanzaNord, stanzaBloccata.getStanzaAdiacente("nord"));
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
}
