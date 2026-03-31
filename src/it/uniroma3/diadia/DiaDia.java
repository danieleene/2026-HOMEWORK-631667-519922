package it.uniroma3.diadia;


import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

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

	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine", "prendi", "posa"};
	private IOConsole console;
	private Partita partita;
	public DiaDia(IOConsole console) {
		this.partita = new Partita();
		this.console= console;
	}

	public void gioca() {
		String istruzione; 

		this.console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		//scannerDiLinee = new Scanner(System.in);		
		do		
			istruzione = console.leggiRiga();
		while (!processaIstruzione(istruzione));
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine(); 
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if(comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			this.console.mostraMessaggio("Comando sconosciuto");
		if (this.partita.vinta()) {
			this.console.mostraMessaggio("Hai vinto!");
			return true;
		} else if(this.partita.isFinita()){
			this.console.mostraMessaggio("Game Over!");
			return true;
		}
			return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			this.console.mostraMessaggio(elencoComandi[i]+" ");
		this.console.mostraMessaggio(" ");
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			this.console.mostraMessaggio("Dove vuoi andare ?");
		Stanza prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione); 
		if (prossimaStanza == null)
			this.console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu - 1);
		}
		this.console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		this.console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}
	/**
	 * Comando "Prendi".
	 */
	private void prendi(String nomeAttrezzo) {
	    if (nomeAttrezzo == null) {
	    	this.console.mostraMessaggio("Cosa vuoi prendere?");
	        return;
	    }

	    Stanza stanzaCorrente = this.partita.getStanzaCorrente();
	    Attrezzo attrezzo = stanzaCorrente.getAttrezzo(nomeAttrezzo);

	    if (attrezzo == null) {
	    	this.console.mostraMessaggio("Attrezzo inesistente nella stanza.");
	        return;
	    }

	    if (this.partita.getGiocatore().getBorsa().addAttrezzo(attrezzo)) {
	        stanzaCorrente.removeAttrezzo(attrezzo);
	        this.console.mostraMessaggio("Hai preso: " + nomeAttrezzo);
	    } else {
	    	this.console.mostraMessaggio("Non puoi prendere questo attrezzo.");
	    }
	}

	
	
	
	
	/**
	 * Comando "Posa".
	 */
	private void posa(String nomeAttrezzo) {
	    if (nomeAttrezzo == null) {
	    	this.console.mostraMessaggio("Cosa vuoi posare?");
	        return;
	    }

	    Attrezzo attrezzo = this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo);

	    if (attrezzo == null) {
	    	this.console.mostraMessaggio("Non hai questo attrezzo nella borsa.");
	        return;
	    }

	    Stanza stanzaCorrente = this.partita.getStanzaCorrente();
	    stanzaCorrente.addAttrezzo(attrezzo);

	    this.console.mostraMessaggio("Hai posato: " + nomeAttrezzo);
	}
	public static void main(String[] argc) {
		IOConsole console= new IOConsole();
		DiaDia gioco = new DiaDia(console);
		gioco.gioca();
	}
}
