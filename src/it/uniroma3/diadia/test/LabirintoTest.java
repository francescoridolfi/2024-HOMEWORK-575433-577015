package it.uniroma3.diadia.test;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ioconsole.IO;

class LabirintoTest {

	/* Test che crea un labirinto e scorre tutte le stanze di esso fino
	 * a giungere alla fine (stanza vincente)
	 */
	@Test
	void test() {
		IO console = DiaDia.getIOConsole();
		
		Labirinto labirinto = new Labirinto();
		console.mostraMessaggio("Stanza iniziale: " + labirinto.getStanzaCorrente().getNome());
		
		String direzione = labirinto.getStanzaCorrente().getDirezioni().get(0);
		labirinto.setStanzaCorrente(labirinto.getStanzaCorrente().getStanzaAdiacente(direzione));
		
		console.mostraMessaggio("Ti sei spostato a " + direzione + " nella stanza: " + labirinto.getStanzaCorrente().getNome());
		
		while(!labirinto.labirintoVinto()) {
			String toAvoid = labirinto.nextDirection(direzione);
			
			direzione = labirinto.getStanzaCorrente().getDirezioni().get(1);
			if(direzione == toAvoid) direzione = labirinto.getStanzaCorrente().getDirezioni().get(0);
			
			labirinto.setStanzaCorrente(labirinto.getStanzaCorrente().getStanzaAdiacente(direzione));
			
			console.mostraMessaggio("Ti sei spostato a " + direzione + " nella stanza: " + labirinto.getStanzaCorrente().getNome());

		}
		
		console.mostraMessaggio("Sei arrivato all'ultima stanza!");
		console.mostraMessaggio("-------------------");
	}

}
