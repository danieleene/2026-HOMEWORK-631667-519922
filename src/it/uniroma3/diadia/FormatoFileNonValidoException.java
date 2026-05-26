package it.uniroma3.diadia;

public class FormatoFileNonValidoException extends Exception{
	private static final long serialVersionUID=1L;
	
	public FormatoFileNonValidoException() {
		super();
	}
	public FormatoFileNonValidoException(Throwable causa) {
		super(causa);
	}
	public FormatoFileNonValidoException(String messaggio) {
		super(messaggio);
	}
	public FormatoFileNonValidoException(String messaggio,Throwable causa) {
		super(messaggio,causa);
	}
}
