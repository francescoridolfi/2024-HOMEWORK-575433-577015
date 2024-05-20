package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.comandi.BaseComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.ioconsole.IO;
import it.uniroma3.diadia.ioconsole.IOConsole;

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
		this.partita = new Partita(labirinto);
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
		
		FabbricaDiComandi fabbrica = new FabbricaDiComandiFisarmonica();
		
		comandoDaEseguire = fabbrica.fabbricaComando(istruzione, ioConsole);
		
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
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}