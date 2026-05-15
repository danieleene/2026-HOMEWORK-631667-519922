package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {

	//variabili d'istanza
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Map<String, Attrezzo> attrezzi;
	private int pesoMax;

	//Costruttori
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}


	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashMap<>(); // speriamo bastino...

	}


	// Aggiunge un attrezzo alla borsa se il peso totale non supera il limite.
	// Restituisce true se l'inserimento va a buon fine, false altrimenti.
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		return this.attrezzi.put(attrezzo.getNome(), attrezzo) == null; 


	}

	//Getter

	public int getPesoMax() {
		return pesoMax;
	}


	public Attrezzo getAttrezzo(String wanted) {
		return this.attrezzi.get(wanted);
	}

	public int getPeso() {
		return this.attrezzi.values().stream()
				.mapToInt(Attrezzo::getPeso)
				.sum();
	}



	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}


	// Verifica se nella borsa è presente un attrezzo con il nome indicato.
	// Restituisce true se esiste, false altrimenti.
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.attrezzi.containsKey(nomeAttrezzo);
	}


	//Da fare la prossima volta!
	/*
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
	    if (nomeAttrezzo == null)
	        return null;

	    for (int i = 0; i < this.numeroAttrezzi; i++) {
	        if (this.attrezzi[i].getNome().equals(nomeAttrezzo)) {

	            Attrezzo rimosso = this.attrezzi[i];

	            // sposto l'ultimo attrezzo nella posizione liberata
	            this.attrezzi[i] = this.attrezzi[this.numeroAttrezzi - 1];
	            this.attrezzi[this.numeroAttrezzi - 1] = null;

	            this.numeroAttrezzi--;

	            return rimosso;
	        }
	    }

	    return null;
	}

	 */


	//Variante remove con le Collezioni!
	// Rimuove e restituisce l'attrezzo con il nome indicato dalla borsa.
	// Se non esiste, restituisce null.
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {

		return this.attrezzi.remove(nomeAttrezzo);

	}


	//Metodo toString
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		if (!this.isEmpty()) {
			s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");

			Iterator<Attrezzo> it = this.attrezzi.values().iterator();
			while(it.hasNext())
				s.append(it.next() + " ");
		}
		else
			s.append("Borsa vuota");
		return s.toString();
	}



	//restituisce la lista degli attrezzi nella borsa ordinati per peso e quindi, 
	//a parità di peso, per nome
	public List<Attrezzo> getContenutoOrdinatoPerPeso(){

		ComparatorePesoNome perPeso = new ComparatorePesoNome();

		final List<Attrezzo> inOrdine = new ArrayList<>(this.attrezzi.values());

		Collections.sort(inOrdine, perPeso);

		return inOrdine;
	}





	//restituisce l'insieme degli attrezzi nella borsa ordinati per nome
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){

		return  new TreeSet<>(this.attrezzi.values());
	}






	//restituisce una mappa che associa un intero (rappresentante un 
	//peso) con l’insieme (comunque non  vuoto) degli attrezzi di tale peso: 
    //tutti gli attrezzi dell'insieme che figura come valore hanno lo stesso 
	//peso pari all'intero che figura come chiave
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> mappa = new HashMap<>();

		for (Attrezzo a : this.attrezzi.values()) {
			int peso = a.getPeso();

			// Se non esiste ancora un Set per quel peso, lo creo
			mappa.putIfAbsent(peso, new HashSet<>());

			// Aggiungo l'attrezzo nel Set corrispondente
			mappa.get(peso).add(a);
		}

		return mappa;
	}




	//restituisce l'insieme gli attrezzi nella borsa ordinati 
	//per peso e quindi, a parità di peso, per nome
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		
		SortedSet<Attrezzo> ordinati = new TreeSet<>(new ComparatorePesoNome());
		ordinati.addAll(this.attrezzi.values());
		
		return ordinati;
	}



}