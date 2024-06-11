package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Cane extends AbstractPersonaggio {
	public static String DEFAULT_DIALOGO = "WOOF WOOF sono giorni che non mangio WOOF potrei sentirmi male...";
	public static String DEFAULT_INTERAZIONE = "WOOF WOOF amo gli ossi... e anche togliere CFU.. WOOOOOF";
	public static String LIKED_ITEM = "osso";
	public static Attrezzo DEFAULT_GIFT = new Attrezzo("Esonero", 30);

	
	public Cane(String nome, String dialogo) {
		super(nome, dialogo);
	}
	
	public Cane(String nome) {
		super(nome, DEFAULT_DIALOGO);
	}
	
	@Override
	public String agisci(Partita partita) {
		partita.getGiocatore().setCFU(partita.getGiocatore().getCFU()-1);
		return this.messaggio(DEFAULT_INTERAZIONE);
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		StringBuilder risp = new StringBuilder("WOOF? Vediamo cosa mi hai dato...");
		
		if(attrezzo.getNome().equals(LIKED_ITEM)) {
			risp.append("\nWOOOF! è proprio ciò che mi piace!");
			partita.getLabirinto().getStanzaCorrente().addAttrezzo(DEFAULT_GIFT);
			risp.append("\nHo lasciato qualcosa per te a terra... WOOOFF!");
		} else {
			risp.append("\nMa che schifo! Spiega cosa me ne faccio con " + attrezzo.getNome() + "... Woff??");
			partita.getGiocatore().setCFU(partita.getGiocatore().getCFU()-1);
			risp.append("\nTi tolgo un CFU per questo scempio! WOOF!");
		}
		
		return this.messaggio(risp);
	}

}
