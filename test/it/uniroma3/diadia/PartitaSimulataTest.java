package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class PartitaSimulataTest {

	@Test
    public void testPartitaVintaSubito() {
        // 1. Setup del copione: l'utente digita "vai nord" (verso la Biblioteca)
        String[] comandiSimulati = {"vai nord"};
        
        // 2. Iniezione del simulatore
        IOSimulator simulatore = new IOSimulator(comandiSimulati);
        DiaDia gioco = new DiaDia(simulatore);
        
        // 3. Esecuzione end-to-end
        gioco.gioca();
        
        // 4. Asserzione di Accettazione: Il sistema ha comunicato la vittoria?
        assertTrue(simulatore.hasMessaggio("Hai vinto!"), 
            "Errore: Il gioco non ha riconosciuto la vittoria entrando nella stanza vincente.");
    }

	@Test
	public void testPartitaUscitaManuale() {
	    // 1. Setup del copione: l'utente digita "fine" appena avvia il gioco
	    String[] comandiSimulati = {"fine"};
	    
	    IOSimulator simulatore = new IOSimulator(comandiSimulati);
	    DiaDia gioco = new DiaDia(simulatore);
	    
	    gioco.gioca();
	    
	    // 4. Asserzione: Il sistema ha salutato l'utente e chiuso correttamente?
	    assertTrue(simulatore.hasMessaggio("Grazie per aver giocato!"), 
	        "Errore: Il gioco non ha gestito correttamente il comando di uscita.");
	}

	@Test
	public void testEsplorazioneSemplice() {
    	// 1. Setup del copione: l'utente va a est, prende un attrezzo fittizio (che fallisce) e poi esce
    	String[] comandiSimulati = {"vai est", "prendi osso", "fine"};
    
    	IOSimulator simulatore = new IOSimulator(comandiSimulati);
    	DiaDia gioco = new DiaDia(simulatore);
    
    	gioco.gioca();
    
    	// 4. Asserzioni multiple sul flusso
    	assertTrue(simulatore.hasMessaggio("Aula N11"), 
        "Errore: Il simulatore non è entrato nell'Aula N11 andando a est.");
	    assertTrue(simulatore.hasMessaggio("Attrezzo inesistente nella stanza."), 
	        "Errore: Il simulatore non ha segnalato l'assenza dell'attrezzo richiesto.");
	}
}