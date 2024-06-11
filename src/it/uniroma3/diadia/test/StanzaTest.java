package it.uniroma3.diadia.test;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ioconsole.IO;


class StanzaTest {
	
	IO console = DiaDia.getIOConsole();
	/* Test 1
	 * Abbiamo creato delle stranze connesse linearmente e scritto in console
	 * le corrispettive adiacenze
	*/
	@Test
	void testStanzaAdiacenti() {
		
		Stanza stanza1 = new Stanza("N10");
		Stanza stanza2 = new Stanza("N11");
		Stanza stanza3 = new Stanza("N12");
		
		stanza1.impostaStanzaAdiacente(Direzione.sud,stanza2);
		stanza2.impostaStanzaAdiacente(Direzione.nord,stanza1);
		stanza2.impostaStanzaAdiacente(Direzione.sud,stanza3);
		stanza3.impostaStanzaAdiacente(Direzione.nord,stanza2);
		
		console.mostraMessaggio(stanza1.getNome() +" confina a sud con: " + stanza1.getStanzaAdiacente(Direzione.sud).getNome());
		console.mostraMessaggio(stanza2.getNome() +" confina a sud con: " + stanza2.getStanzaAdiacente(Direzione.sud).getNome());
		console.mostraMessaggio(stanza3.getNome() +" confina a nord con: " + stanza3.getStanzaAdiacente(Direzione.nord).getNome());
		console.mostraMessaggio("-------------------");
	}
	
	
	/* Test 2
	 * Abbiamo creato un oggetto "stanza", ci abbiamo inserito un attrezzo "ammazzaDraghi" di 50 kg
	 * e abbiamo scritto in console la descrizione della stanza	
	*/
	@Test
	void testAttrezzi() {
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
