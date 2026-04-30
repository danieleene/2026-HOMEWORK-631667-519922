package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza{
	
	private String attrezzoCheIllumina;

	public StanzaBuia(String nome, String attrezzoCheIllumina) {
		super(nome);
		this.attrezzoCheIllumina = attrezzoCheIllumina;
	}

	@Override
	public String getDescrizione() {
		if (!this.hasAttrezzo(this.attrezzoCheIllumina))  //se NON presente...
			return "qui c'è un buio pesto";
		return super.getDescrizione();
	}
}
