package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.ioconsole.IO;

public class ComandoPrendi implements BaseComando {
	
	private IO ioConsole;
	private String parametro;
	
	public ComandoPrendi(IO ioConsole) {
		this.ioConsole = ioConsole;
	}
	
	@Override
	public String getNome() {
		return "prendi";
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
			ioConsole.mostraMessaggio("Cosa vuoi prendere?");
			return;
		}
		
		Labirinto labirinto = partita.getLabirinto();
		
		Attrezzo attrezzo = labirinto.getStanzaCorrente().getAttrezzo(this.parametro);
		
		if (attrezzo == null) {
			ioConsole.mostraMessaggio("Attrezzo non trovato!");
			return;
		}
		
		if(!labirinto.getStanzaCorrente().removeAttrezzo(attrezzo)) {
			ioConsole.mostraMessaggio("Qualcosa non ha funzionato correttamente");
			return;
		}
		
		Giocatore giocatore = partita.getGiocatore();
		
		if(!giocatore.getBorsa().addAttrezzo(attrezzo)) {
			ioConsole.mostraMessaggio("Attenzione hai superato l'eccesso di attrezzi/peso in borsa");
		}
		ioConsole.mostraMessaggio(giocatore.getBorsa().toString());
		
		return;
	}

}
