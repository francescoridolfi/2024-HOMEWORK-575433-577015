package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.ioconsole.IOConsole;

public interface FabbricaDiComandi {
	
	public BaseComando fabbricaComando(String istruzione, IOConsole ioConsole);
	
}
