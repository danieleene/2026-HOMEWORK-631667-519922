package it.uniroma3.diadia.comandi;



import java.util.ArrayList;
import java.util.List;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.Partita;

public abstract class AbstractComando implements Comando{

	private IO io;
	private String parametro;
	private String nome;


	abstract public void esegui(Partita partita);

	@Override
	public String getParametro() {
		return this.parametro;
	}

	@Override
	public void setParametro(String parametro) {
		this.parametro  = parametro;
	}

	@Override
	public void setIo(IO io) {
		this.io = io;
	}

	public IO getIo() {
		return io;
	}

	@Override
	public String getNome() {
		return this.nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}



	/* =========================
    REGISTRO STATICO DEI COMANDI
    ============================ */
	private static List<String> nomiComandi = new ArrayList<>();

	public AbstractComando() {
		String nomeClasse = this.getClass().getSimpleName(); // es. ComandoVai
		if (nomeClasse.startsWith("Comando")) {
			String nomeComando = nomeClasse.substring(7).toLowerCase();
			this.nome = nomeComando;

            //Non registro il comando non valido
			if (!nomeComando.equals("nonvalido") && !nomiComandi.contains(nomeComando)) {
				nomiComandi.add(nomeComando);

			}
		}
	}


	public static List<String> getNomiComandi() {
		return nomiComandi;
	}


	


}
