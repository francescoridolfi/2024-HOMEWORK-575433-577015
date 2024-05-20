package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ioconsole.IO;

public class ComandoAiuto implements BaseComando {

	private IO ioConsole;
	private String parametro;
	
	static final private String[] elencoComandi = {"vai <nome stanza>", "aiuto", "fine", "prendi <nome attrezzo>", "posa <nome attrezzo>", "guarda"};
	
	public ComandoAiuto(IO ioConsole) {
		this.ioConsole = ioConsole;
	}
	
	@Override
	public String getNome() {
		return "aiuto";
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
		StringBuilder help = new StringBuilder("Ecco i comandi che potrai eseguire:\n");
		for(String comando : elencoComandi)
			help.append("> " + comando + "\n");
		
		ioConsole.mostraMessaggio(help.toString());
	}
}
