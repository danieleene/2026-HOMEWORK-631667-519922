package it.uniroma3.diadia.giocatore;

import java.util.Comparator;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class ComparatorePesoNome implements Comparator<Attrezzo>{

	@Override
	public int compare(Attrezzo a1, Attrezzo a2) {

		int cmp = a1.getPeso() - a2.getPeso();
		if(cmp == 0) {
			cmp = a1.getNome().compareTo(a2.getNome());

		}

		/* SOLO DI ESEMPIO */
		//		if(cmp == 0) {
		//			cmp = a1.getCognome().compareTo(a2.getCognome());
		//		}


		return cmp;

	}


}
