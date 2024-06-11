package it.uniroma3.diadia;


import java.io.FileNotFoundException;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.BaseComando;
import it.uniroma3.diadia.comandi.ComandoNonValido;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.ioconsole.IO;
import it.uniroma3.diadia.ioconsole.IOConsole;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.TipologiaPersonaggio;

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
	

	private Partita partita;
	private static IO ioConsole;

	public DiaDia() {
		this.partita = new Partita();
		ioConsole = new IOConsole();
	}
	
	public DiaDia(Labirinto labirinto, IO io) {
		this.partita = new Partita(labirinto, PropertiesLoader.getCFU(), PropertiesLoader.getPesoMaxBorsa());
		ioConsole = io;
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
		BaseComando comandoDaEseguire;
		
		FabbricaDiComandi fabbrica = new FabbricaDiComandiRiflessiva();
		
		try {
			comandoDaEseguire = fabbrica.fabbricaComando(istruzione, ioConsole);
		} catch (Exception e) {
			comandoDaEseguire = new ComandoNonValido(ioConsole);
		}
		
		comandoDaEseguire.esegui(partita);
	
		if (this.partita.vinta()) {
			ioConsole.mostraMessaggio("Hai vinto!");
			return true;
		}
		
		return false;
	}   

	
	public static IO getIOConsole() {
		if(ioConsole == null)
			ioConsole = new IOConsole();
		return ioConsole;
	}

	public static void main(String[] argc) {
		
		Labirinto test = Labirinto.newBuilder()
				.addStanzaIniziale("Atrio")
				.addAttrezzo("osso", 10)
				.setPersonaggio(TipologiaPersonaggio.CANE, "Dexter")
				.addStanza("n10")
				.setPersonaggio(TipologiaPersonaggio.STREGA, "Daniele Panzeri")
				.addAdiacenza("Atrio", "n10", "nord")
				.addStanza("n11")
				.setPersonaggio(new Mago("Professore"))
				.addAdiacenza("n10", "n11", "ovest")
				.addStanzaVincente("n12")
				.addAdiacenza("n11", "n12", "est")
				.getLabirinto();
		
		CaricatoreLabirinto loader;
		try {
			loader = new CaricatoreLabirinto("Labirinto.txt");
			loader.carica();
		} catch (FileNotFoundException | FormatoFileNonValidoException e) {
			e.printStackTrace();
			return;
		}
		
		
		Labirinto caricato = Labirinto.fromLoader(loader);
		
		
		DiaDia gioco = new DiaDia(test, new IOConsole());
		gioco.gioca();
	}
}