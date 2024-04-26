package it.uniroma3.diadia.test;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import it.uniroma3.diadia.comandi.BaseComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.ioconsole.IOConsole;

public class FabbricaDiComandiFisarmonicaTest {
	
	@Test
	public void testFabbricaComando() {
		IOConsole ioConsole = new IOConsole();
		
		BaseComando comandoDaEseguire;
		FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
		
		comandoDaEseguire = factory.fabbricaComando("prendi osso", ioConsole);
		
		assertEquals("prendi", comandoDaEseguire.getNome());
	}
	
}
