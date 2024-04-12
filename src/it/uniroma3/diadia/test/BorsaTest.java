package it.uniroma3.diadia.test;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {

	/* Test per controllare se il sistema di controllo delle quantità e del
	 * peso totale della borsa funziona ed impedisca l'aggiunta di tali attrezzi
	 * nella lista
	 */
	@Test
	void test() {
		IOConsole console = DiaDia.getIOConsole();
		
		Borsa borsa = new Borsa();
		
		console.mostraMessaggio(borsa.toString());

		for(int i = 0; i < 10; i++) {
			Attrezzo a = new Attrezzo("Ascia N." + (i+1), (i+1)*2);
			
			if(!borsa.addAttrezzo(a))
				console.mostraMessaggio("Non posso aggiungere attrezzo " + a + " per eccessività di spazio/peso");
		}
		
		console.mostraMessaggio(borsa.toString());
		console.mostraMessaggio("-------------------");
	}

}
