package it.uniroma3.diadia;


import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.*;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;
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

			Labirinto labirinto= new LabirintoBuilder()
					.addStanzaIniziale("LabCampusOne")
					.addStanzaVincente("Biblioteca")
					.addStanza("corridoio")
					.addPersonaggio(new Strega("Magò", "Non disturbarmi, umano..."))
					//.addPersonaggio(new Mago("Merlino", "Sono un mago molto potente!", new Attrezzo("anello", 1)))
					.addAttrezzo("chiave", 1)
					.addAttrezzo("lanterna", 1)
					.addStanzaBloccata("corridoio bloccato","nord","chiave")
					.addStanzaMagica("stanza magica", 1)
					.addPersonaggio(new Strega("Morgana", "Non disturbarmi, umano..."))
					.addStanzaBuia("stanza buia","lanterna")
					.addPersonaggio(new Cane("Fido", "Grrrr... dammi un osso!",2))
					.addStanza("Aula 1")
					.addAdiacenza("LabCampusOne", "corridoio", "nord")
					.addAdiacenza("corridoio", "LabCampusOne", "sud")
					.addAdiacenza("corridoio", "corridoio bloccato", "nord")
					.addAdiacenza("corridoio bloccato", "corridoio", "sud")
					.addAdiacenza("corridoio bloccato", "Aula 1", "nord")
					.addAdiacenza("Aula 1", "corridoio bloccato", "sud")
					.addAdiacenza("Aula 1", "Biblioteca","nord")
					.addAdiacenza("Biblioteca", "Aula 1", "sud")
					.addAdiacenza("corridoio", "stanza magica", "est")
					.addAdiacenza("stanza magica", "corridoio", "ovest")
					.addAdiacenza("corridoio", "stanza buia", "ovest")
					.addAdiacenza("stanza buia", "corridoio", "est")
					.getLabirinto();

			DiaDia gioco = new DiaDia(labirinto,io);
			gioco.gioca();
		}
	}
}
