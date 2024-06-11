package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.ioconsole.IO;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala implements BaseComando {
	
	private IO ioConsole;
	private String parametro;
	
	public ComandoRegala(IO ioConsole) {
		this.ioConsole = ioConsole;
	}
	
	@Override
	public String getNome() {
		return "regala";
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
		AbstractPersonaggio personaggio = partita.getLabirinto().getStanzaCorrente().getPersonaggio();
		if (personaggio == null) {
			ioConsole.mostraMessaggio("Non è presente nessun personaggio nella stanza!");
			return;
		}
		
		if(this.parametro == null) {
			ioConsole.mostraMessaggio("Quale attrezzo vuoi regalare?");
			return;
		}
		
		Attrezzo attrezzo = partita.getGiocatore().getBorsa().removeAttrezzo(this.getParametro());
		if(attrezzo == null) {
			ioConsole.mostraMessaggio("Attrezzo non trovato nella borsa!");
			return;
		}
		
		ioConsole.mostraMessaggio(personaggio.riceviRegalo(attrezzo, partita));
	}

}
