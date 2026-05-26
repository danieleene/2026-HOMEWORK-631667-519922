package it.uniroma3.diadia;

import java.io.*;
import java.util.*;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.*;

public class CaricatoreLabirinto {

	/* prefisso di una singola riga di testo contenente tutti i nomi delle stanze */
	private static final String STANZE_MARKER = "Stanze:";             

	/* prefisso di una singola riga contenente il nome della stanza iniziale */
	private static final String STANZA_INIZIALE_MARKER = "Inizio:";    

	/* prefisso della riga contenente il nome stanza vincente */
	private static final String STANZA_VINCENTE_MARKER = "Vincente:";  
	/* prefisso della riga contenente il nome stanza bloccata */
	private static final String STANZE_BLOCCATE_MARKER = "StanzeBloccate:"; 
	/* prefisso della riga contenente il nome stanza magica */
	private static final String STANZE_MAGICHE_MARKER = "StanzeMagiche:";  
	/* prefisso della riga contenente il nome stanza buia */
	private static final String STANZE_BUIE_MARKER = "StanzeBuie:";
	/* prefisso della riga contenente le specifiche dei personaggi*/
	private static final String PERSONAGGI_MARKER = "Personaggi:";  
	/* prefisso della riga contenente le specifiche degli attrezzi da collocare nel formato <nomeAttrezzo> <peso> <nomeStanza> */
	private static final String ATTREZZI_MARKER = "Attrezzi:";

	/* prefisso della riga contenente le specifiche dei collegamenti tra stanza nel formato <nomeStanzaDa> <direzione> <nomeStanzaA> */
	private static final String USCITE_MARKER = "Uscite:";

	/*
	 *  Esempio di un possibile file di specifica di un labirinto (vedi POO-26-eccezioni-file.pdf)

		Stanze: biblioteca, N10, N11
		Inizio: N10
		Vincente: N11
		Attrezzi: martello 10 biblioteca, pinza 2 N10
		Uscite: biblioteca nord N10, biblioteca sud N11

	 */
	private LineNumberReader reader;
	LabirintoBuilder builder;



	public CaricatoreLabirinto(Reader reader) throws FileNotFoundException {
		this.builder=Labirinto.newBuilder();
		this.reader = new LineNumberReader(reader);
	}
	

	public void carica() throws FormatoFileNonValidoException {
		try {
			this.leggiECreaStanze();
			this.leggiECreaStanzeMagiche();
			this.leggiECreaStanzeBuie();
			this.leggiECreaStanzeBloccate();
			this.leggiInizialeEvincente();
			this.leggiECollocaAttrezzi();
			this.leggiEImpostaUscite();
			this.leggiECollocaPersonaggi();
		} finally {
			try {
				reader.close();
			} catch (IOException e) {
				e.printStackTrace();
				throw new RuntimeException(e);
			}
		}

	}

	private String leggiRigaCheCominciaPer(String marker) throws FormatoFileNonValidoException {
		try {
			String riga = this.reader.readLine();
			check(riga.startsWith(marker),"era attesa una riga che cominciasse per "+marker);
			return riga.substring(marker.length()).trim();
		} catch (IOException e) {
			throw new FormatoFileNonValidoException(e.getMessage());
		}
	}

	private void leggiECreaStanze() throws FormatoFileNonValidoException  {
		String nomiStanze = this.leggiRigaCheCominciaPer(STANZE_MARKER);
		for(String nomeStanza: separaStringheAlleVirgole(nomiStanze)) {
			this.builder.addStanza(nomeStanza);
		}
	}
	
	private List<String> separaStringheAlleVirgole(String string) {
		List<String> result = new LinkedList<>();
		Scanner scanner = new Scanner(string);
		scanner.useDelimiter(",");
		try (Scanner scannerDiParole = scanner) {
			while(scannerDiParole.hasNext()) {
				result.add(scannerDiParole.next().trim());
			}
		}
		return result;
	}


	private void leggiInizialeEvincente() throws FormatoFileNonValidoException {
		String nomeStanzaIniziale = this.leggiRigaCheCominciaPer(STANZA_INIZIALE_MARKER);
		check(this.isStanzaValida(nomeStanzaIniziale), nomeStanzaIniziale +" non definita");
		this.builder.addStanzaIniziale(nomeStanzaIniziale);
		String nomeStanzaVincente = this.leggiRigaCheCominciaPer(STANZA_VINCENTE_MARKER);
		check(this.isStanzaValida(nomeStanzaVincente), nomeStanzaVincente + " non definita");
		this.builder.addStanzaVincente(nomeStanzaVincente);

	}
// ---- NUOVI METODI AGGIUNTIVI ---- //
	private void leggiECreaStanzeMagiche() throws FormatoFileNonValidoException{
		String stanze=this.leggiRigaCheCominciaPer(STANZE_MAGICHE_MARKER);
		for(String specifica: separaStringheAlleVirgole(stanze)) {
			if(specifica.isEmpty()) continue;
			try(Scanner scanner = new Scanner(specifica)){
					check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza magica."));
					String nome =scanner.next();
					check(scanner.hasNext(),msgTerminazionePrecoce("la soglia magica."));
					int soglia=Integer.parseInt(scanner.next());
					this.builder.addStanzaMagica(nome, soglia);
			}catch(NumberFormatException e) {
				    check(false, "Soglia magica non valida");
			}
			
		}
	}
	
	private void leggiECreaStanzeBuie() throws FormatoFileNonValidoException{
		String stanze=this.leggiRigaCheCominciaPer(STANZE_BUIE_MARKER);
		for(String specifica: separaStringheAlleVirgole(stanze)) {
			if(specifica.isEmpty()) continue;
			try(Scanner scanner = new Scanner(specifica)){
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza buia."));
				String nome =scanner.next();
				check(scanner.hasNext(),msgTerminazionePrecoce("l'attrezzo illuminante."));
				String attrezzo=scanner.next();
				this.builder.addStanzaBuia(nome, attrezzo);
			}
		
		}
	}
	
	private void leggiECreaStanzeBloccate() throws FormatoFileNonValidoException{
		String stanze=this.leggiRigaCheCominciaPer(STANZE_BLOCCATE_MARKER);
		for(String specifica: separaStringheAlleVirgole(stanze)) {
			if(specifica.isEmpty()) continue;
			try(Scanner scanner = new Scanner(specifica)){
				check(scanner.hasNext(), msgTerminazionePrecoce("il nome della stanza bloccata."));
				String nome =scanner.next();
				check(scanner.hasNext(),msgTerminazionePrecoce("direzione sbloccante."));
				String dirString=scanner.next();
				check(scanner.hasNext(),msgTerminazionePrecoce("l'attrezzo sbloccante."));
				String attrezzo=scanner.next();
				try {
					Direzione dir=Direzione.valueOf(dirString.toUpperCase());
					this.builder.addStanzaBloccata(nome,dir, attrezzo);
				} catch (IllegalArgumentException e){
					check(false, "Direzione bloccata non valida: " + dirString);
				}
			}
		
		}
	}
	private void leggiECollocaAttrezzi() throws FormatoFileNonValidoException {
		String specificheAttrezzi = this.leggiRigaCheCominciaPer(ATTREZZI_MARKER);

		for(String specificaAttrezzo : separaStringheAlleVirgole(specificheAttrezzi)) {
			if(specificaAttrezzo.isEmpty()) continue;
			String nomeAttrezzo = null;
			String pesoAttrezzo = null; 
			String nomeStanza = null;
			try (Scanner scannerLinea = new Scanner(specificaAttrezzo)) {
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome di un attrezzo."));
				nomeAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il peso dell'attrezzo "+nomeAttrezzo+"."));
				pesoAttrezzo = scannerLinea.next();
				check(scannerLinea.hasNext(),msgTerminazionePrecoce("il nome della stanza in cui collocare l'attrezzo "+nomeAttrezzo+"."));
				nomeStanza = scannerLinea.next();
			}				
			posaAttrezzo(nomeAttrezzo, pesoAttrezzo, nomeStanza);
		}
	}
	
	
	private void leggiECollocaPersonaggi()  throws FormatoFileNonValidoException{
		String personaggi=this.leggiRigaCheCominciaPer(PERSONAGGI_MARKER);
		for(String spec: separaStringheAlleVirgole(personaggi)) {
			if(spec.isEmpty())continue;
			try(Scanner scanner=new Scanner(spec)){
				check(scanner.hasNext(),msgTerminazionePrecoce("il tipo del personaggio."));
				String tipo=scanner.next();
				check(scanner.hasNext(),msgTerminazionePrecoce("il nome del personaggio."));
				String nome=scanner.next();
				check(scanner.hasNext(),msgTerminazionePrecoce("la presentazione del personaggio."));
				String present=scanner.next();
				
				if(tipo.equalsIgnoreCase("Mago")) {
					check(scanner.hasNext(),msgTerminazionePrecoce("l'attrezzo del mago."));
					String attrNome=scanner.next();
					check(scanner.hasNext(),msgTerminazionePrecoce("peso dell'attrezzo del mago."));
					int attrPeso=Integer.parseInt(scanner.next());
					check(scanner.hasNext(),msgTerminazionePrecoce("stanza del mago."));
					String stanza=scanner.next();
					
					check(isStanzaValida(stanza),msgTerminazionePrecoce("Stanza inesistente per il mago."));
					this.builder.addPersonaggio(stanza, new Mago(nome, present, new Attrezzo(attrNome, attrPeso)));
					
				}else if(tipo.equalsIgnoreCase("Strega")) {
					check(scanner.hasNext(),msgTerminazionePrecoce("stanza della strega."));
					String stanza=scanner.next();
		
					check(isStanzaValida(stanza),msgTerminazionePrecoce("Stanza inesistente per la strega."));
					this.builder.addPersonaggio(stanza, new Strega(nome, present));
					
				}else if(tipo.equalsIgnoreCase("Cane")) {
					check(scanner.hasNext(),msgTerminazionePrecoce("danno del cane."));
					int danno=Integer.parseInt(scanner.next());
					check(scanner.hasNext(),msgTerminazionePrecoce("stanza del cane."));
					String stanza=scanner.next();
		
					check(isStanzaValida(stanza),msgTerminazionePrecoce("Stanza inesistente per il cane."));
					this.builder.addPersonaggio(stanza, new Cane(nome, present, danno));
				}else {
					check(false, "Tipo personaggio sconosciuto: "+ tipo);
					}
				}
			catch (NumberFormatException e) {
				check(false, "Parametro numerico (peso attrezzo o danno cane): INTERO!");
			}
		}
	}
	// --------------------- //
	private void posaAttrezzo(String nomeAttrezzo, String pesoAttrezzo, String nomeStanza) throws FormatoFileNonValidoException {
		int peso;
		try {
			peso = Integer.parseInt(pesoAttrezzo);
			check(isStanzaValida(nomeStanza), "Attrezzo "+ nomeAttrezzo +" non collocabile: stanza "+ nomeStanza+ " inesistente");
			this.builder.addAttrezzo(nomeStanza,nomeAttrezzo, peso);
			
		}
		catch (NumberFormatException e) {
			check(false, "Peso attrezzo "+nomeAttrezzo+" non valido");
		}
	}


	private boolean isStanzaValida(String nomeStanza) {
		return this.builder.getListaStanze().containsKey(nomeStanza);
	}

	private void leggiEImpostaUscite() throws FormatoFileNonValidoException {
		String specificheUscite = this.leggiRigaCheCominciaPer(USCITE_MARKER);
		for(String specificaUscita: separaStringheAlleVirgole(specificheUscite)) {
			try (Scanner scannerDiLinea = new Scanner(specificaUscita)) {						
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("le uscite di una stanza."));
					String stanzaPartenza = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la direzione di una uscita della stanza "+stanzaPartenza));
					String dirString = scannerDiLinea.next();
					check(scannerDiLinea.hasNext(),msgTerminazionePrecoce("la destinazione di una uscita della stanza "+stanzaPartenza+" nella direzione "+dirString));
					String stanzaDestinazione = scannerDiLinea.next();
				
					try {
						Direzione dir=Direzione.valueOf(dirString.toUpperCase());
						impostaUscita(stanzaPartenza, dir, stanzaDestinazione);
					} catch (IllegalArgumentException e){
						check(false, "Direzione non valida nelle uscite: " + dirString);
					}
			
		} 
	  }
	}
	
	private String msgTerminazionePrecoce(String msg) {
		return "Terminazione precoce del file prima di leggere "+msg;
	}

	private void impostaUscita(String stanzaDa, Direzione dir, String nomeA) throws FormatoFileNonValidoException {
		
		check(isStanzaValida(stanzaDa),"Stanza di partenza sconosciuta "+dir);
		check(isStanzaValida(nomeA),"Stanza di destinazione sconosciuta "+ dir);
		this.builder.addAdiacenza(stanzaDa, nomeA, dir);
	}


	final private void check(boolean condizioneCheDeveEsseraVera, String messaggioErrore) throws FormatoFileNonValidoException {
		if (!condizioneCheDeveEsseraVera)
			throw new FormatoFileNonValidoException("Formato file non valido [" + this.reader.getLineNumber() + "] "+messaggioErrore);		
	}

	public Labirinto getLabirinto() {
		return this.builder.getLabirinto();
	}
}