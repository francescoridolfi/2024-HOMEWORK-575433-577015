package it.uniroma3.diadia.test;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.ioconsole.IO;

class GiocatoreTest {

	/* Test per verificare che i metodi di giocatore e gestione
	 * borsa del giocatore siano funzionanti
	 * */
	@Test
	void test() {
		IO console = DiaDia.getIOConsole();
		
		Giocatore giocatore = new Giocatore(20);
		console.mostraMessaggio(giocatore.toString());
		console.mostraMessaggio("");
		
		giocatore.setCFU(10);
		giocatore.getBorsa().addAttrezzo(new Attrezzo("Blocco appunti", 2));
		
		console.mostraMessaggio(giocatore.toString());
		console.mostraMessaggio("-------------------");
	}

}
