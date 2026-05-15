package it.uniroma3.diadia.ambienti;
import it.uniroma3.diadia.attrezzi.Attrezzo;


class StanzaMagica extends Stanza{
	
	//variabili d'istanza
	final static private int SOGLIA_MAGICA_DEFAULT=3;
	private int contatoreAttrezziPosati;
	private int sogliaMagica;
	
	
	//Costruttori
	public StanzaMagica(String nome) {
		this(nome, SOGLIA_MAGICA_DEFAULT);
	}
	
	
	public StanzaMagica(String nome, int soglia) {
		super(nome);
		this.contatoreAttrezziPosati=0;
		this.sogliaMagica=soglia;
	}
	
	
	
	// Aggiunge un attrezzo alla stanza.
	// Dopo aver superato la soglia magica, gli attrezzi vengono modificati
	// (nome invertito e peso raddoppiato) prima dell'inserimento.
	@Override
	public boolean addAttrezzo(Attrezzo attrezzo) {
		this.contatoreAttrezziPosati++;
		if(this.contatoreAttrezziPosati>this.sogliaMagica) {
			attrezzo=this.modificaAttrezzo(attrezzo);
		}
		return super.addAttrezzo(attrezzo);
	}
	
	
	
	// Trasforma l'attrezzo invertendone il nome e raddoppiandone il peso.
	// Usato automaticamente dopo aver superato la soglia magica.
	private Attrezzo modificaAttrezzo(Attrezzo attrezzo) {
		StringBuilder nomeInvertito;
		int pesoX2=attrezzo.getPeso()*2;
		nomeInvertito=new StringBuilder(attrezzo.getNome());
		nomeInvertito=nomeInvertito.reverse();
		attrezzo=new Attrezzo(nomeInvertito.toString(), pesoX2);
		return attrezzo;
	}
	
}
