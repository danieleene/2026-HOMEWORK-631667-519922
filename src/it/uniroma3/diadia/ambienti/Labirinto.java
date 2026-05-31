package it.uniroma3.diadia.ambienti;

import java.io.FileReader;
import java.io.Reader;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class Labirinto {

	//variabili d'istanza
	private Stanza stanzaIniziale;
	private Stanza stanzaFinale;

	//Costruttore
	private Labirinto() {
	}

	public static LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
	}

	public Labirinto(String nomeFile) throws Exception {
		Reader reader = new FileReader(nomeFile);
		CaricatoreLabirinto caricatore = new CaricatoreLabirinto(reader);
		caricatore.carica();
		Labirinto lab = caricatore.getLabirinto();

		this.stanzaIniziale = lab.getStanzaIniziale();
		this.stanzaFinale = lab.getStanzaVincente();
	}


	//Getter & Setter
	public Stanza getStanzaVincente() {
		return stanzaFinale;
	}

	public void setStanzaIniziale(Stanza stanzaIniziale) {
		this.stanzaIniziale = stanzaIniziale;
	}

	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}


	public void setStanzaFinale(Stanza stanzaFinale) {
		this.stanzaFinale = stanzaFinale;
	}



	public static class LabirintoBuilder{
		private Labirinto labirinto;
		private Map<String, Stanza> stanze;
		private Stanza ultimaStanzaAggiunta;
		public LabirintoBuilder() {
			this.labirinto=new Labirinto();
			this.stanze=new HashMap<>();
		}
		private void aggiungiMapComeLast(Stanza stanza) {
			this.ultimaStanzaAggiunta=stanza;
			this.stanze.put(stanza.getNome(), stanza);
		}

		public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
			Stanza iniziale=new Stanza(nomeStanza);
			this.labirinto.setStanzaIniziale(iniziale);
			this.aggiungiMapComeLast(iniziale);
			return this;
		}
		public LabirintoBuilder addStanzaBloccata(String nomeStanza, Direzione direzioneBloccata, String attrezzoSbloccante) {
			StanzaBloccata bloccata=new StanzaBloccata(nomeStanza, direzioneBloccata, attrezzoSbloccante);
			this.aggiungiMapComeLast(bloccata);
			return this;
		}
		public LabirintoBuilder addStanzaMagica(String nome, int soglia) {
			StanzaMagica magica=new StanzaMagica(nome, soglia);
			this.aggiungiMapComeLast(magica);
			return this;
		}
		public LabirintoBuilder addStanzaBuia(String nome, String attrezzoIlluminante) {
			StanzaBuia magica=new StanzaBuia(nome, attrezzoIlluminante);
			this.aggiungiMapComeLast(magica);
			return this;
		}
		public LabirintoBuilder addStanzaVincente(String nomeStanza) {
			Stanza vincente=new Stanza(nomeStanza);
			this.labirinto.setStanzaFinale(vincente);
			this.aggiungiMapComeLast(vincente);
			return this;
		}
		public LabirintoBuilder addStanza(String nomeStanza) {
			Stanza stanza=new Stanza(nomeStanza);
			this.aggiungiMapComeLast(stanza);
			return this;
		}
		public LabirintoBuilder addAdiacenza(String stanzaPartenza, String stanzaArrivo, Direzione direzione) {
			Stanza partenza = this.stanze.get(stanzaPartenza);
			Stanza arrivo = this.stanze.get(stanzaArrivo);
			if (partenza == null || arrivo == null) {
				return this;
			}
			if (partenza.getMapStanzeAdiacenti().containsKey(direzione)) {
				return this;
			}
			// Se il vincolo è rispettato, delega il cablaggio alla stanza
			partenza.impostaStanzaAdiacente(direzione, arrivo);

			return this;
		}
		public LabirintoBuilder addAttrezzo(String nomeAttrezzo, int peso) {
			if(this.ultimaStanzaAggiunta!=null) {
				this.ultimaStanzaAggiunta.addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
			}
			return this;
		}
		public LabirintoBuilder addAttrezzo(String nomeStanza, String nomeAttrezzo,int peso) {
			Stanza stanza=this.stanze.get(nomeStanza);
			if(stanza!=null) {
				stanza.addAttrezzo(new Attrezzo(nomeAttrezzo, peso));
			}
			return this;
		}
		public Labirinto getLabirinto() {
			return this.labirinto;
		}
		public Map<String, Stanza> getListaStanze(){
			return Collections.unmodifiableMap(this.stanze);
		}


		public LabirintoBuilder addPersonaggio(AbstractPersonaggio personaggio) {
			if (this.ultimaStanzaAggiunta != null) {
				this.ultimaStanzaAggiunta.setPersonaggio(personaggio);
			}
			return this;
		}
		public LabirintoBuilder addPersonaggio(String nomeStanza, AbstractPersonaggio personaggio) {
			Stanza stanza=this.stanze.get(nomeStanza);
			if(stanza!=null) {
				stanza.setPersonaggio(personaggio);
			}
			return this;
		}
	}
}
