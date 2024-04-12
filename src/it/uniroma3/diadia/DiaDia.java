package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = "Benvenuto nel gioco!"+
			" Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai <nome stanza>", "aiuto", "fine", "prendi <nome attrezzo>", "posa <nome attrezzo>"};

	private Partita partita;
	private static IOConsole ioConsole;

	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 

		ioConsole.mostraMessaggio(MESSAGGIO_BENVENUTO);
				
		do		
			istruzione = ioConsole.leggiRiga();
		while (!processaIstruzione(istruzione));
		
	}   


	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);
		
		if(comandoDaEseguire.getNome() == null) {
			ioConsole.mostraMessaggio("Comando sconosciuto");
			return false;
		}

		switch(comandoDaEseguire.getNome()) {
		case "fine":
			this.fine();
			return true;
		case "vai":
			this.vai(comandoDaEseguire.getParametro());
			break;
		case "aiuto":
			this.aiuto();
			break;
		case "prendi":
			this.prendi(comandoDaEseguire.getParametro());
			break;
		case "posa":
			this.posa(comandoDaEseguire.getParametro());
			break;
		default:
			ioConsole.mostraMessaggio("Comando sconosciuto");
			break;
		}
	
		if (this.partita.vinta()) {
			ioConsole.mostraMessaggio("Hai vinto!");
			return true;
		}
		
		return false;
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		ioConsole.mostraMessaggio("Ecco i comandi che potrai eseguire:");
		for(int i=0; i< elencoComandi.length; i++) 
			ioConsole.mostraMessaggio("> " + elencoComandi[i]);
		
	}
	
	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null)
			ioConsole.mostraMessaggio("Dove vuoi andare ?");
		
		Stanza prossimaStanza = null;
		Labirinto labirinto = this.partita.getLabirinto();
		Giocatore giocatore = this.partita.getGiocatore();
		
		prossimaStanza = labirinto.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			ioConsole.mostraMessaggio("Direzione inesistente");
		else {
			labirinto.setStanzaCorrente(prossimaStanza);
			giocatore.setCFU(giocatore.getCFU() - 1);
		}
		ioConsole.mostraMessaggio(labirinto.getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		ioConsole.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}
	
	private void prendi(String nomeAttrezzo) {
		if(nomeAttrezzo == null) {
			ioConsole.mostraMessaggio("Cosa vuoi prendere?");
			return;
		}
		
		Labirinto labirinto = this.partita.getLabirinto();
		
		Attrezzo attrezzo = labirinto.getStanzaCorrente().getAttrezzo(nomeAttrezzo);
		
		if (attrezzo == null) {
			ioConsole.mostraMessaggio("Attrezzo non trovato!");
			return;
		}
		
		if(!labirinto.getStanzaCorrente().removeAttrezzo(attrezzo)) {
			ioConsole.mostraMessaggio("Qualcosa non ha funzionato correttamente");
			return;
		}
		
		Giocatore giocatore = this.partita.getGiocatore();
		
		if(!giocatore.getBorsa().addAttrezzo(attrezzo)) {
			ioConsole.mostraMessaggio("Attenzione hai superato l'eccesso di attrezzi/peso in borsa");
		}
		ioConsole.mostraMessaggio(giocatore.getBorsa().toString());
	}
	
	private void posa(String nomeAttrezzo) {
		if(nomeAttrezzo == null) {
			ioConsole.mostraMessaggio("Cosa vuoi prendere?");
			return;
		}
		
		Giocatore giocatore = this.partita.getGiocatore();
		
		Attrezzo attrezzo = giocatore.getBorsa().getAttrezzo(nomeAttrezzo);
		
		if(attrezzo == null) {
			ioConsole.mostraMessaggio("Attrezzo non trovato nella borsa!");
			return;
		}
		
		Labirinto labirinto = this.partita.getLabirinto();
		
		if(!labirinto.getStanzaCorrente().addAttrezzo(attrezzo)) {
			ioConsole.mostraMessaggio("Numero massimo di attrezzi per stanza raggiunto");
			return;
		}
		
		giocatore.getBorsa().removeAttrezzo(nomeAttrezzo);
		ioConsole.mostraMessaggio("Oggetto posato a terra nella stanza: " + labirinto.getStanzaCorrente().getNome());
		
		ioConsole.mostraMessaggio(giocatore.getBorsa().toString());
	}
	
	public static IOConsole getIOConsole() {
		// Caso di console nulla solo durante i test
		if(ioConsole == null)
			ioConsole = new IOConsole();
		
		return ioConsole;
	}

	public static void main(String[] argc) {
		ioConsole = new IOConsole();
		
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}