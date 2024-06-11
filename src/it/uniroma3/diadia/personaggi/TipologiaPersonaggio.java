package it.uniroma3.diadia.personaggi;

public enum TipologiaPersonaggio {

	CANE("Cane"),
	MAGO("Mago"),
	STREGA("Strega");
	
	private String className;
	
	TipologiaPersonaggio(String className) {
		this.className = className;
	}
	
	public Class<?> getAbstractClass() {
		try {
			return Class.forName("it.uniroma3.diadia.personaggi."+this.className);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		} 
	}
	
}
