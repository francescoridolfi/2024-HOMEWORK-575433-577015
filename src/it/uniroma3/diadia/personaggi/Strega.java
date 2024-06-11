package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	public static String DEFAULT_DIALOGO = "Mmhh.. Non vedo la natura del tuo saluto ma per questa volta potrei lasciare stare...";
	public static String DEFAULT_INTERAZIONE = "MMMmmmhh... Insisti?.. Allora ti dimezzo i CFU!";
	
	private boolean dimezzaCFU;
	
	public Strega(String nome, String dialogo) {
		super(nome, dialogo);
		dimezzaCFU = false;
	}
	
	public Strega(String nome) {
		this(nome, DEFAULT_DIALOGO);
	}

	@Override
	public String agisci(Partita partita) {
		if(!this.haSalutato()) return this.getSaluto();
		
		if(!dimezzaCFU) {
			dimezzaCFU = true;
			return this.messaggio("MMmmhh... Prossima volta che insisti ti dimezzerò i CFU!");
		}
		
		partita.getGiocatore().setCFU((int) partita.getGiocatore().getCFU() / 2);
		return this.messaggio(DEFAULT_INTERAZIONE);
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return this.messaggio("HAHAHAHAHAHHAHAHAHAHAHHAHAHAHAHAHAHA");
	}

}
