
package it.uniroma3.diadia;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.ComandoPosa;


class ComandoPosaTest {

	private IO io;
	@Test
    void testPosaAttrezzoPresenteInBorsa() {
		
		this.io=new IOConsole();
        Partita partita = new Partita();
        
        

        Stanza stanza = new Stanza("aula");
        partita.setStanzaCorrente(stanza);

        Attrezzo chiave = new Attrezzo("chiave", 1);
        partita.getGiocatore().getBorsa().addAttrezzo(chiave);

        ComandoPosa comando = new ComandoPosa();
        comando.setIo(this.io);
        comando.setParametro("chiave");
        comando.esegui(partita);

        assertNotNull(stanza.getAttrezzo("chiave"));
        assertNull(partita.getGiocatore().getBorsa().getAttrezzo("chiave"));
        
    }

}