package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ioconsole.IO;


public class ComandoFine extends AbstractComando {

	private IO ioConsole;
	private String parametro;
	
	public ComandoFine(IO ioConsole) {
		this.ioConsole = ioConsole;
	}
	
	@Override
	public String getNome() {
		return "fine";
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}
	
	@Override
	public void esegui(Partita partita) {
		partita.setFinita();
		ioConsole.mostraMessaggio("Grazie di aver giocato!");
	}
	
}
