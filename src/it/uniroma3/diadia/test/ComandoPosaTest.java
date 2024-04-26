package it.uniroma3.diadia.test;


import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.BaseComando;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.ioconsole.IOConsole;

public class ComandoPosaTest {

	@Test
	public void testDanielePosato() {
		Partita partita = new Partita();
		IOConsole ioConsole = new IOConsole();
		
		Attrezzo danipanz = new Attrezzo("Daniele", 1); // Questo attrezzo attrae a te tutte le chiamate di spam
		
		partita.getGiocatore().getBorsa().addAttrezzo(danipanz);
		
		BaseComando comandoPosa = new ComandoPosa(ioConsole);
		comandoPosa.setParametro("Daniele");
		
		comandoPosa.esegui(partita);
		
		
		ioConsole.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}
	
}
