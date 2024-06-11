package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ioconsole.IO;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta implements BaseComando {

	private IO ioConsole;
	private String parametro;
	
	public ComandoSaluta(IO ioConsole) {
		this.ioConsole = ioConsole;
	}
	
	@Override
	public String getNome() {
		return "saluta";
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
		if(this.parametro == null) {
			ioConsole.mostraMessaggio("Chi vuoi salutare?");
			return;
		}
		
		AbstractPersonaggio personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if (personaggio == null || !personaggio.getNome().equals(this.getParametro())) {
			ioConsole.mostraMessaggio(this.getParametro() + " non è presente nella stanza!");
			return;
		}
		
		ioConsole.mostraMessaggio(personaggio.getSaluto());
	}

}
