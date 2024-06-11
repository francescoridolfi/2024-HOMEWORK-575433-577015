package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public abstract class AbstractPersonaggio {
	
	private String nome;
	private String dialogo;
	private boolean salutato;
	
	public AbstractPersonaggio(String nome, String dialogo) {
		this.nome = nome;
		this.dialogo = dialogo;
		this.salutato = false;
	}

	public String getNome() {
		return nome;
	}

	public boolean haSalutato() {
		return salutato;
	}
	
	public String messaggio(String msg) {
		return new StringBuilder(this.getNome()).append(": ").append(msg).toString();
	}
	
	public String messaggio(StringBuilder builder) {
		return this.messaggio(builder.toString());
	}
	
	public String getSaluto() {
		String saluto;
		
		if(!this.haSalutato()) {
			saluto = this.dialogo;
			salutato = true;
		} else
			saluto = "Già di ritorno?";
		
		return this.messaggio(saluto);
	}
	
	public abstract String agisci(Partita partita);
	
	public abstract String riceviRegalo(Attrezzo attrezzo, Partita partita);
	
	@Override
	public String toString() {
		return this.getNome();
	}
	

}
