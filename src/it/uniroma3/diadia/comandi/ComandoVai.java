package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.IO;


public class ComandoVai extends AbstractComando{
	
	//variabili d'istanza
	private String direzione;
	private String messaggio;
	private IO io;
	
	
	//Costruttore
	public ComandoVai() {
		
	}
	
	
	
	@Override
	public void setIo(IO io) {
		this.io=io;
	}
	@Override
	public void esegui(Partita partita) {
		Stanza stanzaCorrente=partita.getStanzaCorrente();
		Stanza prossimaStanza=null;
		if(this.direzione==null) {
			this.io.mostraMessaggio("Dove vuoi andare?" + "Devi specificare una direzione");
			return;
		}
		Direzione dir=null;
		try {
			dir=Direzione.valueOf(this.direzione.toUpperCase());
		}catch(IllegalArgumentException e) {
			this.io.mostraMessaggio("Direzione non valida! Scegli tra: NORD, EST, SUD, OVEST ");
			return;
		}
		prossimaStanza=stanzaCorrente.getStanzaAdiacente(dir);
		if(prossimaStanza==null) {
			this.io.mostraMessaggio("Direzione inesistente (nessuna uscita)");
			return;
		}
		partita.setStanzaCorrente(prossimaStanza);
		
		AbstractPersonaggio personaggio = partita.getStanzaCorrente().getPersonaggio();
		if (personaggio != null)
		    this.io.mostraMessaggio("Personaggio presente: " + personaggio.getNome());
		else
		    this.io.mostraMessaggio("Non c'� nessuno nella stanza.");

		this.io.mostraMessaggio(partita.getStanzaCorrente().toString());
		partita.getGiocatore().setCfu(partita.getGiocatore().getCfu()-1);
	}
	
	@Override 
	public void setParametro(String parametro) {
		this.direzione=parametro;
	}
	@Override
	public String getNome() {
		return "vai";
	}
	@Override
	public String getParametro() {
		return this.direzione;
	}
	@Override
	public boolean sconosciuto(){
		return false;
	}
	@Override
	public String getMessaggio() {
		return this.messaggio;
	}
}
