package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;
import it.uniroma3.diadia.ioconsole.IOConsole;

public class ComandoPosa implements BaseComando {
	
	private IOConsole ioConsole;
	private String parametro;
	
	public ComandoPosa(IOConsole ioConsole) {
		this.ioConsole = ioConsole;
	}
	
	@Override
	public String getNome() {
		return "posa";
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
		
		Giocatore giocatore = partita.getGiocatore();
		
		Attrezzo attrezzo = giocatore.getBorsa().getAttrezzo(this.parametro);
		
		if(attrezzo == null) {
			ioConsole.mostraMessaggio("Attrezzo non trovato nella borsa!");
			return;
		}
		
		Labirinto labirinto = partita.getLabirinto();
		
		if(!labirinto.getStanzaCorrente().addAttrezzo(attrezzo)) {
			ioConsole.mostraMessaggio("Numero massimo di attrezzi per stanza raggiunto");
			return;
		}
		
		giocatore.getBorsa().removeAttrezzo(this.parametro);
		ioConsole.mostraMessaggio("Oggetto posato a terra nella stanza: " + labirinto.getStanzaCorrente().getNome());
		
		ioConsole.mostraMessaggio(giocatore.getBorsa().toString());
	}

}
