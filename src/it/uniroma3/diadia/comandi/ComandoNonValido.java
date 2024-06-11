package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ioconsole.IO;

public class ComandoNonValido extends AbstractComando {
	
	private IO ioConsole;
	private String parametro;

	public ComandoNonValido(IO ioConsole) {
		this.ioConsole = ioConsole;
	}

	@Override
	public String getNome() {
		return "non-valido";
	}

	@Override
	public String getParametro() {
		return this.parametro;
	}

	
	@Override
	public void esegui(Partita partita) {
		ioConsole.mostraMessaggio("Comando NON Valido");
	}
}
