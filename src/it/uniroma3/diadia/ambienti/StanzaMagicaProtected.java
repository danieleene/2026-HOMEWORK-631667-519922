package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaMagicaProtected extends StanzaProtected{
	
	//variabili d'istanza
	final static private int SOGLIA_MAGICA_DEFAULT = 3;
    private int contatoreAttrezziPosati;
    private int sogliaMagica;

    
    //Costruttori
    public StanzaMagicaProtected(String nome) {
        this(nome, SOGLIA_MAGICA_DEFAULT);
    }

    public StanzaMagicaProtected(String nome, int soglia) {
        super(nome);
        this.contatoreAttrezziPosati = 0;
        this.sogliaMagica = soglia;
    }

    
    // Aggiunge un attrezzo alla stanza.
 	// Dopo aver superato la soglia magica, gli attrezzi vengono modificati
 	// (nome invertito e peso raddoppiato) prima dell'inserimento.
    @Override
    public boolean addAttrezzo(Attrezzo attrezzo) {
        this.contatoreAttrezziPosati++;
        if (this.contatoreAttrezziPosati > this.sogliaMagica) {
            attrezzo = this.modificaAttrezzo(attrezzo);
        }

        // DIFFERENZA ARCHITETTURALE:
        // Nessun super.addAttrezzo(). Accediamo ai campi ereditati violando l'information hiding.
        if (this.numeroAttrezzi < this.attrezzi.length) {
            this.attrezzi[this.numeroAttrezzi] = attrezzo;
            this.numeroAttrezzi++;
            return true;
        } else {
            return false;
        }
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

