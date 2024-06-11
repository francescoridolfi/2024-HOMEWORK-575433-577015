package it.uniroma3.diadia.ambienti;

public enum Direzione {

	nord("nord"), sud("sud"), est("est"), ovest("ovest"), nordEst("nord-est");
	
	private String value;
	
	Direzione(String value) {
		this.value = value;
	}
	
	@Override
	public String toString() {
		return this.value;
	}
	
}
