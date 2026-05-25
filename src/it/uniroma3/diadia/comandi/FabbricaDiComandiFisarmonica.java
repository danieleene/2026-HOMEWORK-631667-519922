package it.uniroma3.diadia.comandi;
import it.uniroma3.diadia.IO;
import java.util.Scanner;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi{


	private IO io;

	public FabbricaDiComandiFisarmonica(IO io) {
		this.io = io;
	}

	@Override
	public Comando costruisciComando(String istruzione) {
		Scanner scannerDiParole=new Scanner(istruzione);
		String nomeComando=null;
		String parametro=null;
		Comando comando=null;

		if(scannerDiParole.hasNext()) {
			nomeComando=scannerDiParole.next();
		}
		if(scannerDiParole.hasNext()) {
			parametro=scannerDiParole.next();
		}
		if(nomeComando==null) {
			comando=new ComandoNonValido();
		}
		else if (nomeComando.equals("vai")) {

			comando = new ComandoVai(); 
		}
		else if (nomeComando.equals("prendi")) {

			comando = new ComandoPrendi();
		}
		else if (nomeComando.equals("posa")) {

			comando = new ComandoPosa();
		}
		else if (nomeComando.equals("aiuto")) {

			comando = new ComandoAiuto();
		}
		else if (nomeComando.equals("fine")) {

			comando = new ComandoFine();
		}
		else if (nomeComando.equals("guarda")) {

			comando = new ComandoGuarda();
		}

		else if (nomeComando.equals("saluta")) {

			comando = new ComandoSaluta();
		}

		else if (nomeComando.equals("interagisci")) {

			comando = new ComandoInteragisci();
		}

		else if (nomeComando.equals("regala")) {
			comando = new ComandoRegala();
		}

		else {comando = new ComandoNonValido();} 

		comando.setParametro(parametro); 
		comando.setIo(io);
		return comando; 
	}
}
