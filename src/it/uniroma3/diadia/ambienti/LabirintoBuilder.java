package it.uniroma3.diadia.ambienti;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class LabirintoBuilder {
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
	public LabirintoBuilder addStanzaBloccata(String nomeStanza, String direzioneBloccata, String attrezzoSbloccante) {
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
	public LabirintoBuilder addAdiacenza(String stanzaPartenza, String stanzaArrivo, String direzione) {
		Stanza partenza = this.stanze.get(stanzaPartenza);
        Stanza arrivo = this.stanze.get(stanzaArrivo);
        
        if (!partenza.getDirezioni().contains(direzione) && partenza.getDirezioni().size() >= 4) {
            return this; // Ignora l'aggiunta della quinta porta per le regole di DiaDia
        }
        
        // Se il vincolo è rispettato, delega il cablaggio alla stanza
        partenza.impostaStanzaAdiacente(direzione, arrivo);
        
        return this;
	}
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		if(this.ultimaStanzaAggiunta!=null) {
			this.ultimaStanzaAggiunta.addAttrezzo(new Attrezzo(nome, peso));
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

	
}
