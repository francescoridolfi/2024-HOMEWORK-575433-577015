package it.uniroma3.diadia.test;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertInstanceOf;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.BaseComando;
import it.uniroma3.diadia.comandi.AbstractComando;
import it.uniroma3.diadia.comandi.ComandoAiuto;
import it.uniroma3.diadia.comandi.ComandoVai;
import it.uniroma3.diadia.ioconsole.IO;
import it.uniroma3.diadia.ioconsole.IOConsole;

class AbstractComandoTest {

	@Test
	void testAbstractComando() {
		IO ioConsole = new IOConsole();
		
		BaseComando aiuto = new ComandoAiuto(ioConsole);
		BaseComando vai = new ComandoVai(ioConsole);
		
		assertInstanceOf(AbstractComando.class, aiuto);
		assertFalse(vai instanceof AbstractComando);
		
	}

}
