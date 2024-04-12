package it.uniroma3.diadia.test;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;


class StanzaTest {
	
	
	/* Test 1
	 * Abbiamo creato delle stranze connesse linearmente e scritto in console
	 * le corrispettive adiacenze
	*/
	@Test
	void testStanzaAdiacenti() {
		IOConsole console = DiaDia.getIOConsole();
		
		Stanza stanza1 = new Stanza("N10");
		Stanza stanza2 = new Stanza("N11");
		Stanza stanza3 = new Stanza("N12");
		
		stanza1.impostaStanzaAdiacente("sinistra",stanza2);
		stanza2.impostaStanzaAdiacente("destra",stanza1);
		stanza2.impostaStanzaAdiacente("sinistra",stanza3);
		stanza3.impostaStanzaAdiacente("destra",stanza2);
		
		console.mostraMessaggio(stanza1.getNome() +" confina a sinistra con: " + stanza1.getStanzaAdiacente("sinistra").getNome());
		console.mostraMessaggio(stanza2.getNome() +" confina a sinistra con: " + stanza2.getStanzaAdiacente("sinistra").getNome());
		console.mostraMessaggio(stanza3.getNome() +" confina a destra con: " + stanza3.getStanzaAdiacente("destra").getNome());
		console.mostraMessaggio("-------------------");
	}
	
	
	/* Test 2
	 * Abbiamo creato un oggetto "stanza", ci abbiamo inserito un attrezzo "ammazzaDraghi" di 50 kg
	 * e abbiamo scritto in console la descrizione della stanza	
	*/
	@Test
	void testAttrezzi() {
		IOConsole console = DiaDia.getIOConsole();
		Stanza stanza = new Stanza("Stanza del Tesoro");
		
		stanza.addAttrezzo(new Attrezzo("ammazzaDraghi", 50));
		console.mostraMessaggio(stanza.getDescrizione());
		console.mostraMessaggio("-------------------");
	}
	
	
	/* Test 3
	 * Abbiamo creato due stanze ed un attezzo, è stato inizialmente
	 * assegnato l'attrezzo ad una stanza, successivamente poi spostato
	 * nella seconda (quindi rimosso dalla precedente)
	*/
	@Test
	void testSpostaAttrezzi() {
		IOConsole console = DiaDia.getIOConsole();
		Stanza stanza1 = new Stanza ("Landa degli Evocatori");
		Stanza stanza2 = new Stanza ("Calabria Saudita");
		Attrezzo attrezzo = new Attrezzo ("Drago di komodo", 80);
		
		stanza1.addAttrezzo(attrezzo);
		
		console.mostraMessaggio("PRIMA: ");
		console.mostraMessaggio(stanza1.toString());
		console.mostraMessaggio(stanza2.toString());
		
		stanza1.removeAttrezzo(attrezzo);
		stanza2.addAttrezzo(attrezzo);
		
		console.mostraMessaggio("DOPO: ");
		console.mostraMessaggio(stanza1.toString());
		console.mostraMessaggio(stanza2.toString());
		
		console.mostraMessaggio("-------------------");
	}
	
}
