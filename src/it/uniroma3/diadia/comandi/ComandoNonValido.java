package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ioconsole.IOConsole;

public class ComandoNonValido implements BaseComando {
	
	private IOConsole ioConsole;
	private String parametro;

	public ComandoNonValido(IOConsole ioConsole) {
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
	public void setParametro(String parametro) {
		this.parametro = parametro;
	}
	
	@Override
	public void esegui(Partita partita) {
		ioConsole.mostraMessaggio("Comando NON Valido");
	}
}
