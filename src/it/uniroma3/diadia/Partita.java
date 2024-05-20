package it.uniroma3.diadia;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {

	static final private int CFU_INIZIALI = 20;

	private Labirinto labirinto;
	private Giocatore giocatore;
	private boolean finita;
	
	public Partita() {
		this(new Labirinto());
	}
	
	public Partita(Labirinto labirinto) {
		this.labirinto = labirinto;
		this.giocatore = new Giocatore(CFU_INIZIALI);
		this.finita = false;
	}

	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	
	public void setLabirinto(Labirinto labirinto) {
		this.labirinto = labirinto;
	}
	
	
	public Giocatore getGiocatore() {
		return this.giocatore;
	}
	
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * Il controllo viene gestito dall'istanza di Labirinto corrente
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		// In futuro si pensa di congiungere più labirinti.
		return labirinto.labirintoVinto();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCFU() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}	
}
