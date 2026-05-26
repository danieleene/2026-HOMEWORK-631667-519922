package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.StringReader;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
class CaricatoreLabirintoTest {
	private CaricatoreLabirinto caricatore;
	private final String MARKERS_VUOTI = "StanzeMagiche:\nStanzeBuie:\nStanzeBloccate:\n";
	//-- LABIRINTO MONOLOCALE -- //
	@Test
	public void testMono()throws Exception {
		String fixture ="Stanze: N10\n"+
				         MARKERS_VUOTI+
						"Inizio: N10\n"+
						"Vincente: N10\n"+
						"Attrezzi:\n"+
						"Uscite:\n"+
						"Personaggi:\n";
		this.caricatore=new CaricatoreLabirinto(new StringReader(fixture));
		this.caricatore.carica();
		Labirinto lab=this.caricatore.getLabirinto();
		assertEquals("N10", lab.getStanzaIniziale().getNome());
		assertEquals("N10", lab.getStanzaVincente().getNome());
	}
	//-- LABIRINTO BILOCALE -- //
	@Test
	public void testBil()throws Exception {
		String fixture ="Stanze: N10,N11\n"+
						MARKERS_VUOTI+
						"Inizio:N10\n"+
						"Vincente:N11\n"+
						"Attrezzi:\n"+
						"Uscite:N10 nord N11\n"+
						"Personaggi:\n";
		this.caricatore=new CaricatoreLabirinto(new StringReader(fixture));
		this.caricatore.carica();
		Labirinto lab=this.caricatore.getLabirinto();
		assertEquals("N10", lab.getStanzaIniziale().getNome());
		assertEquals("N11", lab.getStanzaVincente().getNome());
		assertEquals("N11", lab.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).getNome());
	}
	//-- LABIRINTO BILOCALE CON ATTREZZO-- //
	@Test
	public void testBilAtt()throws Exception {
		String fixture ="Stanze: N10,N11\n"+
						MARKERS_VUOTI+
						"Inizio:N10\n"+
						"Vincente:N11\n"+
						"Attrezzi:martello 10 N11\n"+
						"Uscite:N10 nord N11\n"+
						"Personaggi:\n";
		this.caricatore=new CaricatoreLabirinto(new StringReader(fixture));
		this.caricatore.carica();
		Labirinto lab=this.caricatore.getLabirinto();
		assertTrue(lab.getStanzaVincente().hasAttrezzo("martello"));
	}
	//-- GESTIONE ERRORI: STANZA INIZIALE INESISTENTE-- //
		@Test
		public void testInizioInes()throws Exception {
			String fixture ="Stanze: N10\n"+
							MARKERS_VUOTI+
							"Inizio: StanzaFittizia\n"+
							"Vincente: N10\n"+
							"Attrezzi:\n"+
							"Uscite:\n"+
							"Personaggi:\n";
			this.caricatore=new CaricatoreLabirinto(new StringReader(fixture));
			assertThrows(FormatoFileNonValidoException.class, () ->{
				this.caricatore.carica();
				});
		}
		//-- GESTIONE ERRORI: ATTREZZO POSATO: FALLITO-- //
		@Test
		void testAttrezzoNonPosato()throws Exception {
			String fixture ="Stanze: N10,N11\n"+
							 MARKERS_VUOTI+
							"Inizio:N10\n"+
							"Vincente:N11\n"+
							"Attrezzi:chiave 2 StanzaFittizia\n"+
							"Uscite:N10 nord N11\n"+
							"Personaggi:\n";
			this.caricatore=new CaricatoreLabirinto(new StringReader(fixture));
			assertThrows(FormatoFileNonValidoException.class, () ->{
			this.caricatore.carica();
			});
		}
		//-- LABIRINTO COMPLETO -- //
				@Test
				void testLabirintoCompleto()throws Exception {
					String fixture ="Stanze: Atrio, Biblioteca, Bagno, Studio\n"+
									MARKERS_VUOTI+
									"Inizio:Atrio\n"+
									"Vincente:Studio\n"+
									"Attrezzi:osso 5 Atrio, libro 2 Biblioteca\n"+
									"Uscite:Atrio nord Biblioteca, Biblioteca sud Atrio, Atrio est Bagno, Bagno ovest Atrio, Biblioteca est Studio\n"+
									"Personaggi:\n";
					this.caricatore=new CaricatoreLabirinto(new StringReader(fixture));
					this.caricatore.carica();
					Labirinto lab=this.caricatore.getLabirinto();
					// -- Controllo stato stanze -- //
					assertEquals("Atrio", lab.getStanzaIniziale().getNome());
					assertEquals("Atrio", lab.getStanzaIniziale().getNome());
					// -- Controllo stato attrezzi -- //
					assertTrue(lab.getStanzaIniziale().hasAttrezzo("osso"));
					assertTrue(lab.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).hasAttrezzo("libro"));
					// -- Controllo adiacenze -- //
					assertEquals("Biblioteca", lab.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).getNome());
			        assertEquals("Bagno", lab.getStanzaIniziale().getStanzaAdiacente(Direzione.EST).getNome());
			        assertEquals("Studio", lab.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).getStanzaAdiacente(Direzione.EST).getNome());
				}
				
		//---- LABIRINTO COMPLETO CON PERSONAGGI ----//
				@Test
				public void testLabirinti()throws Exception{
					String fixture="Stanze: N10\n"+
								   "StanzeMagiche: Magica 3\n"+
							       "StanzeBuie: Buia lanterna\n"+
								   "StanzeBloccate: Bloccata nord chiave\n"+
								   "Inizio: N10\n"+
							       "Vincente: Magica\n"+
								   "Attrezzi: lanterna 2 N10\n"+
							       "Uscite: N10 nord Buia, Buia est Bloccata, Bloccata sud Magica\n"+
								   "Personaggi: Mago Merlino saluti bacchetta 2 N10, Cane Fuffi grrr 5 Buia, Strega Morgana ciao Bloccata\n";
					this.caricatore=new CaricatoreLabirinto(new StringReader(fixture));
					this.caricatore.carica();
					Labirinto lab=this.caricatore.getLabirinto();
					
					assertEquals("N10", lab.getStanzaIniziale().getNome());
					assertEquals("Buia", lab.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).getNome());
					assertEquals("Bloccata", lab.getStanzaIniziale().getStanzaAdiacente(Direzione.NORD).getStanzaAdiacente(Direzione.EST).getNome());
				}
}
