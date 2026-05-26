package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import java.util.Arrays;
import java.util.List;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;

import org.junit.jupiter.api.Test;

public class PartiteSimulate {

	@Test
	public void testPartitaMonolocaleVittoriaStart() throws Exception {
		Labirinto labirinto=Labirinto.newBuilder()
				.addStanzaIniziale("Biblioteca")
				.addStanzaVincente("Biblioteca")
				.getLabirinto();
		List<String> comandiLab=Arrays.asList("guarda");
		IOSimulator io=new IOSimulator(comandiLab);
		DiaDia gioco=new DiaDia(labirinto, io);
		gioco.gioca();
		assertTrue(io.getMessaggiStampati().contains("Hai vinto!"), "Che succede qui?");
	}
	@Test
    public void testPartitaBilocaleSpostamentoEVittoria() throws Exception {
        // 1. Costruiamo un bilocale collegato
        Labirinto labirinto = Labirinto.newBuilder()
                .addStanzaIniziale("Atrio")
                .addStanzaVincente("Biblioteca")
                .addAdiacenza("Atrio", "Biblioteca", Direzione.NORD)
                .getLabirinto();

        // 2. Sceneggiatura: il giocatore guarda la stanza, va a nord, e vince
        List<String> comandiSceneggiatura = Arrays.asList("guarda", "vai nord");
        IOSimulator io = new IOSimulator(comandiSceneggiatura);

        DiaDia gioco = new DiaDia(labirinto, io);
        gioco.gioca();

        // 3. Verifichiamo il comportamento accoppiato comando-risposta (punto avanzato slide)
        // Il comando al numero 1 ("vai nord") deve aver prodotto il messaggio di vittoria
        List<String> risposteAlVaiNord = io.getMessaggiDiComandoAlNumero(1);
        
        assertTrue(risposteAlVaiNord.contains("Hai vinto!"));
    }
	@Test
	public void testEsplorazioneSemplice() throws Exception {
		Labirinto labirinto=Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addStanza("Aula N11")
				.addStanzaVincente("Segreteria")
				.addAdiacenza("Atrio", "Aula N11", Direzione.EST)
				.getLabirinto();
		List<String> comandiSimulati=Arrays.asList("vai est", "prendi osso", "fine");
		IOSimulator io=new IOSimulator(comandiSimulati);
		DiaDia gioco= new DiaDia(labirinto, io);
		
		gioco.gioca();
        // Le asserzioni separate
        assertTrue(io.hasMessaggio("Aula N11"), "Errore: Il simulatore non trova 'Aula N11'.");
        assertTrue(io.hasMessaggio("Attrezzo inesistente nella stanza."), "Errore: Il simulatore non trova 'Attrezzo inesistente nella stanza.'.");
	}
}
