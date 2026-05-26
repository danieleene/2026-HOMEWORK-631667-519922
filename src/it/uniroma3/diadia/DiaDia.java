package it.uniroma3.diadia;


import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;

import it.uniroma3.diadia.comandi.*;
import it.uniroma3.diadia.personaggi.*;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	//variabili d'istanza
	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";


	private IO io;
	private Partita partita;
	private FabbricaDiComandi fabbrica;

	//PARTE ESERCIZIO 6
	//Costruttori
	public DiaDia(Labirinto labirinto,IO io) {
		this.partita = new Partita(labirinto);
		this.io= io;
		this.fabbrica=new FabbricaDiComandiFisarmonica(io);
	}

	public void gioca() throws Exception {
		String istruzione; 

		io.mostraMessaggio(MESSAGGIO_BENVENUTO);
		//scannerDiLinee = new Scanner(System.in);		
		do		
			istruzione = io.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 * @throws Exception 
	 */
	private boolean processaIstruzione(String istruzione) throws Exception {
		Comando comandoDaEseguire = this.fabbrica.costruisciComando(istruzione);
		comandoDaEseguire.esegui(this.partita);
		if(comandoDaEseguire.getMessaggio()!=null) {
			io.mostraMessaggio(comandoDaEseguire.getMessaggio());
		}
		if (this.partita.vinta()) {
			io.mostraMessaggio("Hai vinto!");
			return true;
		} else if(this.partita.isFinita()){
			io.mostraMessaggio("Game Over!");
			return true;
		}
		return false;
	}   
	public static void main(String[] argc) throws Exception {

		try (Scanner scanner = new Scanner(System.in)){

			IO io= new IOConsole(scanner);

			Labirinto labirinto= Labirinto.newBuilder()
					.addStanzaIniziale("LabCampusOne")
					.addStanzaVincente("Biblioteca")
					.addStanza("corridoio")
					.addPersonaggio(new Strega("Morgana", "Non disturbarmi, umano..."))
					//.addPersonaggio(new Mago("Merlino", "Sono un mago molto potente!", new Attrezzo("anello", 1)))
					.addAttrezzo("chiave", 1)
					.addAttrezzo("lanterna", 1)
					.addStanzaBloccata("corridoio bloccato",Direzione.NORD,"chiave")
					.addStanzaMagica("stanza magica", 1)
					.addPersonaggio(new Strega("Morgana", "Non disturbarmi, umano..."))
					.addStanzaBuia("stanza buia","lanterna")
					.addPersonaggio(new Cane("Fido", "Grrrr... dammi un osso!",2))
					.addStanza("Aula 1")
					.addAdiacenza("LabCampusOne", "corridoio", Direzione.NORD)
					.addAdiacenza("corridoio", "LabCampusOne", Direzione.SUD)
					.addAdiacenza("corridoio", "corridoio bloccato", Direzione.NORD)
					.addAdiacenza("corridoio bloccato", "corridoio", Direzione.SUD)
					.addAdiacenza("corridoio bloccato", "Aula 1", Direzione.NORD)
					.addAdiacenza("Aula 1", "corridoio bloccato", Direzione.SUD)
					.addAdiacenza("Aula 1", "Biblioteca",Direzione.NORD)
					.addAdiacenza("Biblioteca", "Aula 1", Direzione.SUD)
					.addAdiacenza("corridoio", "stanza magica", Direzione.EST)
					.addAdiacenza("stanza magica", "corridoio", Direzione.OVEST)
					.addAdiacenza("corridoio", "stanza buia", Direzione.OVEST)
					.addAdiacenza("stanza buia", "corridoio", Direzione.EST)
					.getLabirinto();

			DiaDia gioco = new DiaDia(labirinto,io);
			gioco.gioca();
		}
	}
}
