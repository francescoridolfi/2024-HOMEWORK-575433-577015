package it.uniroma3.diadia.personaggi;

import java.util.Random;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Mago extends AbstractPersonaggio {
	public static String DEFAULT_DIALOGO = "Ciao io sono un mago, se mi darai un oggetto dimezzerò il suo peso!";
	public static String[] QUOTES = {
            "La felicità può essere trovata anche negli attimi più tenebrosi, se solo ci si ricorda di accendere la luce. - Albus Silente",
            "Non è importante quello che pensiamo di essere, ma quello che facciamo che conta. - Albus Silente",
            "Non smettere mai di credere nei tuoi sogni, per quanto impossibili possano sembrare. - Gandalf",
            "È nei momenti più bui che dobbiamo concentrarci per vedere la luce. - Albus Silente",
            "Il coraggio non è la mancanza di paura, ma la capacità di affrontarla. - Merlino"
        };
	
	public Mago(String nome, String dialogo) {
		super(nome, dialogo);
	}
	
	public Mago(String nome) {
		super(nome, DEFAULT_DIALOGO);
	}

	@Override
	public String agisci(Partita partita) {
		return this.messaggio(QUOTES[new Random().nextInt(QUOTES.length)]);
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		StringBuilder risp = new StringBuilder("Grazie per il pensiero, farò si che quest'ultimo peserà la metà di quanto è ora...");
		
		Attrezzo mod = new Attrezzo(attrezzo.getNome(), attrezzo.getPeso() / 2);
		partita.getLabirinto().getStanzaCorrente().addAttrezzo(mod);
		
		risp.append("\nGuarda ora nella stanza!");
		
		return this.messaggio(risp);
	}

	
	
}
