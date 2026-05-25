package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoInteragisci extends AbstractComando {
	
	//variabili d'istanza
	private static final String MESSAGGIO_CON_CHI = 
			"Con chi dovrei interagire?...";
	
	private String messaggio;
	
	
	
	@Override
	public void esegui(Partita partita) {
		
		AbstractPersonaggio personaggio;
		personaggio = partita.getStanzaCorrente().getPersonaggio();
		IO io = this.getIo();
		if (personaggio!=null) {
			this.messaggio = personaggio.agisci(partita);
			
		} else io.mostraMessaggio(MESSAGGIO_CON_CHI);
	}
	
	
	//Getter & Setter
	public String getMessaggio() {
		return this.messaggio;
	}
	
	
	@Override
	public void setParametro(String parametro) {}


	@Override
	public boolean sconosciuto() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}