package it.uniroma3.diadia.test;


import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.ioconsole.IOConsole;

class PartitaTest {

	/* Test 1
	 * Abbiamo creato una partita, spostato la stanza sulla stanza
	 * vincente e veriricato la vittoria tramite print in console 
	 */
	@Test
	void testControlloVittoria() {
		IOConsole console = DiaDia.getIOConsole();
		
		Partita game = new Partita();
		Labirinto labirinto = game.getLabirinto();
		
		console.mostraMessaggio("Gioco iniziato nella stanza: " + labirinto.getStanzaCorrente().getNome());
		labirinto.setStanzaCorrente(labirinto.getStanzaVincente());
		
		console.mostraMessaggio("Ti sei spostato nella stanza: " + labirinto.getStanzaCorrente().getNome());
		
		console.mostraMessaggio("Controllo vittoria: " + (game.vinta() ? "si" : "no"));
		
		console.mostraMessaggio("-------------------");
	}
	
	/* Test 2
	 * Abbiamo creato una partita, azzerati i CFU del gioco
	 * e controllato via console che si potesse continuare
	 * a giocare.
	 */
	@Test
	void testCFUInsufficienti() {
		IOConsole console = DiaDia.getIOConsole();
		
		Partita game = new Partita();
		Giocatore giocatore = game.getGiocatore();
		
		console.mostraMessaggio("Gioco iniziato con totale CFU: " + giocatore.getCFU());
		console.mostraMessaggio("....");
		
		giocatore.setCFU(0);
		
		console.mostraMessaggio("CFU rimasti: " + giocatore.getCFU());
		console.mostraMessaggio("Stato partita: " + (game.isFinita() ? "FINITA" : "IN GIOCO"));
		
		console.mostraMessaggio("-------------------");
	}

	/* Test 3
	 * Abbiamo creato una partita, e stampando in console
	 * le informazioni relative alla stanza corrente, spostati nella prima stanza adiacente
	 * ristampate le informazioni relative alla nuova stanza
	 */
	@Test
	void testInfoStanza() {
		IOConsole console = DiaDia.getIOConsole();
		
		Partita game = new Partita();
		Labirinto labirinto = game.getLabirinto();
		
		console.mostraMessaggio("Info Stanza Corrente:");
		console.mostraMessaggio(labirinto.getStanzaCorrente().toString());
		
		String direction = labirinto.getStanzaCorrente().getDirezioni()[0];
		labirinto.setStanzaCorrente(labirinto.getStanzaCorrente().getStanzaAdiacente(direction));
		
		console.mostraMessaggio("\nTi sei spostato nella Stanza Adiacente!");
		
		console.mostraMessaggio("\nInfo Stanza Corrente:");
		console.mostraMessaggio(labirinto.getStanzaCorrente().toString());
		
		console.mostraMessaggio("-------------------");
	}
}
