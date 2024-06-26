package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ioconsole.IO;

public class ComandoGuarda extends AbstractComando {

	private IO ioConsole;
	private String parametro;
	
	public ComandoGuarda(IO ioConsole) {
		this.ioConsole = ioConsole;
	}
	
	@Override
	public String getNome() {
		return "guarda";
	}
	
	@Override
	public String getParametro() {
		return this.parametro;
	}
	
	
	@Override
	public void esegui(Partita partita) {
		ioConsole.mostraMessaggio("Attualmente ti trovi nella stanza: ");
		ioConsole.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().toString());
		ioConsole.mostraMessaggio("Questo è il tuo inventario: ");
		ioConsole.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		ioConsole.mostraMessaggio("Lo stato della partita attualmente è " + (partita.isFinita() ? "concluso" : "ancora in gioco"));
	}
}
