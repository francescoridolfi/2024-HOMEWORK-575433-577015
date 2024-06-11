package it.uniroma3.diadia.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import org.junit.Test;

import it.uniroma3.diadia.comandi.BaseComando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;
import it.uniroma3.diadia.ioconsole.IOConsole;

public class FabbricaDiComandiFisarmonicaTest {
	
	@Test
	public void testFabbricaComando() {
		IOConsole ioConsole = new IOConsole();
		
		BaseComando comandoDaEseguire = null;
		FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
		
		try {
			comandoDaEseguire = factory.fabbricaComando("prendi osso", ioConsole);
		} catch (Exception e) {
			fail(e.toString());
		}
		
		assertEquals("prendi", comandoDaEseguire.getNome());
	}
	
}
