package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.*;
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
	
	
	//Costruttori
	public DiaDia(IO io) {
		this.partita = new Partita();
		this.io= io;
		this.fabbrica=new FabbricaDiComandiFisarmonica();
	}
	
	/* PARTE ESERCIZIO 6
	public DiaDia(Labirinto labirinto, IO io) {
        this.partita = new Partita(labirinto);   
        this.io = io;
        this.fabbrica = new FabbricaDiComandiFisarmonica();
    }
	*/
	
	public void gioca() {
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
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = this.fabbrica.costruisciComando(istruzione, io);
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
	public static void main(String[] argc) {
		IO io= new IOConsole();
		
		
		
		DiaDia gioco = new DiaDia(io);
		gioco.gioca();
	}
}
