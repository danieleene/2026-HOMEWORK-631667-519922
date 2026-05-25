package it.uniroma3.diadia;

import java.io.InputStream;
import java.util.Properties;

public class Configurazione {

	private static final String DIADIA_PROPERTIES  = "diadia.properties";
	private static Properties props = new Properties();

	static {
		try (InputStream input = Configurazione.class.getClassLoader().getResourceAsStream(DIADIA_PROPERTIES )) {
			if (input == null)
				throw new RuntimeException("File di configurazione non trovato: " + DIADIA_PROPERTIES );
			props.load(input);
		} catch (Exception e) {
			throw new RuntimeException("Errore nel caricamento del file di configurazione", e);
		}
	}

	public static int getCFUIniziali() {
		return Integer.parseInt(props.getProperty("cfu_iniziali"));
	}

	public static int getPesoMaxBorsa() {
		return Integer.parseInt(props.getProperty("peso_max_borsa"));
	}
	
	public static int getSogliaMagicaDefault() {
	    return Integer.parseInt(props.getProperty("soglia_magica_default"));
	}

}

