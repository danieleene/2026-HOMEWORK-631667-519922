package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPrendi;

class ComandoPrendiTest {
	private IO io;
	@Test
    void testPrendiAttrezzoPresente() {
		
		this.io=new IOConsole();
        Partita partita = new Partita();

        Stanza stanza = new Stanza("aula");
        Attrezzo chiave = new Attrezzo("chiave", 1);
        stanza.addAttrezzo(chiave);

        partita.setStanzaCorrente(stanza);

        ComandoPrendi comando = new ComandoPrendi();
        comando.setIo(this.io);
        comando.setParametro("chiave");
        comando.esegui(partita);

        assertNull(stanza.getAttrezzo("chiave"));
        assertNotNull(partita.getGiocatore().getBorsa().getAttrezzo("chiave"));
        
    }

}
