package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.ioconsole.IO;

public class ComandoVai implements BaseComando {
	
	private IO ioConsole;
	private String parametro;
	
	public ComandoVai(IO ioConsole) {
		this.ioConsole = ioConsole;
	}
	
	@Override
	public String getNome() {
		return "vai";
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
		if(this.parametro == null)
			ioConsole.mostraMessaggio("Dove vuoi andare ?");
		
		Stanza prossimaStanza = null;
		Labirinto labirinto = partita.getLabirinto();
		Giocatore giocatore = partita.getGiocatore();
		
		prossimaStanza = labirinto.getStanzaCorrente().getStanzaAdiacente(this.parametro);
		if (prossimaStanza == null)
			ioConsole.mostraMessaggio("Direzione inesistente");
		else {
			labirinto.setStanzaCorrente(prossimaStanza);
			giocatore.setCFU(giocatore.getCFU() - 1);
		}
		
		ioConsole.mostraMessaggio(labirinto.getStanzaCorrente().getDescrizione());
		
		return;
	}
	
}
