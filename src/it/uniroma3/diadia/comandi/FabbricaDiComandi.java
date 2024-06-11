package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ioconsole.IO;

public interface FabbricaDiComandi {
	
	public BaseComando fabbricaComando(String istruzione, IO ioConsole) throws Exception;
	
}
