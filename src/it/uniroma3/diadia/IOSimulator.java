package it.uniroma3.diadia;

public class IOSimulator implements IO{

	private String[] righeDaLeggere;     // INPUT 
	private String[] messaggiStampati;   // OUTPUT
	private int indiceLettura;           // posizione corrente INPUT
	private int indiceScrittura;         // posizione corrente OUTPUT
    static final private int MAX_MESSAGGI = 200;
	public IOSimulator(String[] righeDaLeggere) {
		this.righeDaLeggere = righeDaLeggere;
		this.messaggiStampati = new String[MAX_MESSAGGI]; //ad esempio 1000
		this.indiceLettura = 0;
		this.indiceScrittura = 0;
	}

	@Override
	public void mostraMessaggio(String messaggio) {
		if(this.indiceScrittura < MAX_MESSAGGI) {
			this.messaggiStampati[this.indiceScrittura]=messaggio;
			this.indiceScrittura++;
		}
	}
	@Override
	public String leggiRiga() {
		if (this.righeDaLeggere==null || this.indiceLettura >= this.righeDaLeggere.length) {
			return null;
		}
			String riga = this.righeDaLeggere[this.indiceLettura];
			this.indiceLettura++;
			return riga;
	}
	
	//Getter
	public String[] getMessaggiStampati() {
	    return this.messaggiStampati;
	}
	
	public boolean hasMessaggio(String messaggioAtteso) {
		if(messaggioAtteso==null)return false;
		for(int i=0; i<this.indiceScrittura;i++) {
			if(this.messaggiStampati[i]!=null && this.messaggiStampati[i].contains(messaggioAtteso)) return true;
		}
		return false;
	}

}
