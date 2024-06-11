package it.uniroma3.diadia.test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.BaseComando;
import it.uniroma3.diadia.comandi.ComandoNonValido;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiRiflessiva;
import it.uniroma3.diadia.ioconsole.IO;
import it.uniroma3.diadia.ioconsole.IOConsole;

class FabbricaDiComandiRiflessivaTest {
	

	@Test
	void testRiflessiva() {
		IO ioConsole = new IOConsole();
		FabbricaDiComandi factory = new FabbricaDiComandiRiflessiva();
		BaseComando command;
		
		try {
			command = factory.fabbricaComando("prendi osso", ioConsole);
		} catch (Exception e) {
			command = new ComandoNonValido(ioConsole);
		}
		
		assertEquals("prendi", command.getNome());
		
		
	}

}
