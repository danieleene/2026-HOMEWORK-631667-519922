package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaTest {

	private Stanza stanza;
	private Attrezzo attrezzo;
	
	@BeforeEach
	public void setUp() {
		this.stanza=new Stanza("Stanza test");
		this.attrezzo=new Attrezzo("Attrezzo test", 2);
	}
	
	
	
	@Test
	void testAddAttrezzo_StanzaVuota() {
		assertTrue(this.stanza.addAttrezzo(this.attrezzo));
	}
	@Test
	void testAddAttrezzo_VerificaIncremento() {
		this.stanza.addAttrezzo(this.attrezzo);
		
		assertFalse(this.stanza.getAttrezzi().isEmpty(), 
		        "La collezione di attrezzi non deve essere vuota");
		
		assertTrue(this.stanza.getAttrezzi().contains(this.attrezzo),
		        "La stanza deve contenere l'attrezzo inserito");
		
		
		assertNotNull(this.stanza.getAttrezzo("Attrezzo test"),
		        "L'attrezzo deve essere recuperabile tramite il nome");
	}
	@Test
	void testAddAttrezzo_StanzaPiena() {
		for(int i=0; i<10; i++) {
			this.stanza.addAttrezzo(new Attrezzo("attrezzo" +i, 1));
		}
		Attrezzo attrezzoExtra= new Attrezzo("Trapano", 6);
		assertFalse(this.stanza.addAttrezzo(attrezzoExtra), "L'aggiunta dell'attrezzo extra non deve essere possibile");
	}
	@Test
	void testAddAttrezzo_InserimentoFallito() {
		boolean result=this.stanza.addAttrezzo(null);
		
		assertFalse(result, "Metodo deve restituire false e rifiutare l'inserimento di un attrezzo non esistente (null) per evitare di creare errori");
		
		assertTrue(this.stanza.getAttrezzi().isEmpty(),
		        "La collezione deve rimanere vuota dopo un inserimento fallito");
	}
	
	
	
	
	@Test
	void testHasAttrezzo_Presente() {
		this.stanza.addAttrezzo(this.attrezzo);
		assertTrue(this.stanza.hasAttrezzo("Attrezzo test"), "Il metodo deve restituire true se l'attrezzo e' presente");
	}
	@Test
	void testHasAttrezzo_NonPresente() {
		this.stanza.addAttrezzo(this.attrezzo);
		assertFalse(this.stanza.hasAttrezzo("Spada"), "Il metodo deve restituire falso poiche' non ");
	}
	@Test
	void testHasAttrezzo_StanzaVuota(){
		assertFalse(this.stanza.hasAttrezzo("Attrezzo test"), "Il metodo deve ritornare true poiche' non e' presente nessun attrezzo al'inizio");
		
	}
	@Test
	void testHasAttrezzo_UltimaPosizione() {
		for(int i=0;i<9; i++){
			this.stanza.addAttrezzo(new Attrezzo("test"+i,2));
		}
		Attrezzo attrezzoFitt=new Attrezzo("Fittizzio", 4);
		this.stanza.addAttrezzo(attrezzoFitt);
		assertTrue(this.stanza.hasAttrezzo("Fittizzio"), "Il metodo deve ritornare true poiche' c'e' uno spazio nell'array");
	}


	@Test
    void testGetStanzaAdiacenteInesistente() {
        assertNull(stanza.getStanzaAdiacente("n18"));
    }

	
	
	@Test
	void testSetStanzaAdiacenteMaxDirezioni() {
	    stanza.impostaStanzaAdiacente("nord", new Stanza("A"));
	    stanza.impostaStanzaAdiacente("sud", new Stanza("B"));
	    stanza.impostaStanzaAdiacente("est", new Stanza("C"));
	    stanza.impostaStanzaAdiacente("ovest", new Stanza("D"));
	    stanza.impostaStanzaAdiacente("alto", new Stanza("E"));
	    assertNull(stanza.getStanzaAdiacente("alto")); 
	}
		
	@Test 
	void testAddStanzaAdiacente() {
	    Stanza stanza = new Stanza("N11");
	    Stanza bagno = new Stanza("Bagno");
	    stanza.impostaStanzaAdiacente("nord", bagno);
	    assertEquals(bagno, stanza.getStanzaAdiacente("nord"));
	}
	
}