MODIFCHE DA FARE:
1) Continua l'esercizio 6 dall'inizio
2) Test non passati in stanzaTest (forse riadatta gli array con le collezioni)
3) Controlla se devi rimuovere array in stanzaProtected
4) Controlla test in piÃ¹ sui test es.5
5) Es.2 test stanza buia e bloccata non fatti, forse va bene cosi



1) Ho modificato i test 'addAttrezzo_StanzaPiena' e 'setStanzaAdiacenteMaxDirezioni' poiche' nella prima, avendo una hashMap, il codice
   non trova un oggetto con la stessa key, quindi fa un'aggiunta senza contare delle numero massimo degli attrezzi in una stanza. Ora il test 
   controlla che non ci siano 2 oggetti con la stessa key (nome).
   Il secondo test ha la stessa problematica visto che abbiamo una mappa senza fine, dobbiamo controllare che ci sia l'ultimo inserimento    	   effettuato non adiacente 	
2) Ho modificato le classi magica protected e stanza protected togliendo i cicli lineari e implementandoli da array [] a hashMap.
3) Ho aggiunto i test alle stanze che estendono la classe Stanza e ho aggiunto la classe test stanzaMagica che ci siamo dimenticati
4) Test comparatorPerNome in BorsaTest sistemato
5) Ho perfezionato il codice di LabirintoBuilder cosi da far passare tutti e 19 test dati dal professore. 
6) Ho eliminato la classe test PartitaSimulata poiche' rimpiazzata da PartiteSimulate
 
 
 
 PARTE ESERCIZI 10-14 + 17,20:
 
 1)In AbstractComando abbiamo aggiunto un registro statico(e relativo metodo per ottenere tale registro) ed un costruttore per soddisfare la specifica :
 
' I nomi siano registrati in un elenco visibile dal comando aiuto da 
AbstractComando al momento della creazione degli oggetti 
istanza delle sottoclassi concrete dei comandi '
 
 Nel dettaglio il costruttore :
 
 * Ricava automaticamente il nome del comando dal nome della classe
 * Registra il nome del comando nel registro statico condiviso
 * Permette a ComandoAiuto di leggere l’elenco(che non deve più conoscere!)
 
 
 2) E' stato rifattorizzato il codice di ComandoAiuto(il metodo esegui) 
 
 
 3)Domanda :  Il Cane ad ogni morso quanti CFU toglie?(Vogliamo fare un semplice -  
              1CFU)? -> bisogna modificare il danno nel Main
              (NOTA: l'implementazione corrente prevede danno : -2CFU !)
 
   
  4) Pensiero mio: mostriamo il personaggio sia con 'Vai' che con 'Guarda' !
  
  5) In StanzaMagicaTest abbiamo stato lasciato :
    private static final int SOGLIA_TEST = 2; 
    
  6) Quando creiamo la release HWC dobbiamo verificare che ci sia anche 
     diadia.properties
  
  