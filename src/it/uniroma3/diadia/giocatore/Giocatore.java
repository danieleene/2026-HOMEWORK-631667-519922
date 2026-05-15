package it.uniroma3.diadia.giocatore;

public class Giocatore {

	//variabili d'istanza
	static final private int CFU_INIZIALI = 20;
	private int cfu;
	private Borsa borsa;
	
	
	//Getter & Setter
	
	public int getCfu() {
		return this.cfu;
	}

	public void setCfu(int cfu) {
		this.cfu = cfu;		
	}
	
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	//Costruttore
	public Giocatore() {
		this.cfu = CFU_INIZIALI;
		this.borsa = new Borsa();
	}
}
