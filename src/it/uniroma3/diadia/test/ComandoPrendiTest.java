package it.uniroma3.diadia.test;

import org.junit.Test;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.comandi.BaseComando;
import it.uniroma3.diadia.comandi.ComandoPrendi;
import it.uniroma3.diadia.ioconsole.IOConsole;

public class ComandoPrendiTest {
	
	
	@Test
	public void testPrendiOsso() {
		
		Partita partita = new Partita();
		IOConsole ioConsole = new IOConsole();
		
		BaseComando prendi = new ComandoPrendi(ioConsole);
		prendi.setParametro("osso");
		prendi.esegui(partita);
		
		
		ioConsole.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
	}

}
