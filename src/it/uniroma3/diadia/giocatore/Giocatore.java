package it.uniroma3.diadia.giocatore;

public class Giocatore {
	
	private int cfu;
	private Borsa borsa;
	
	public Giocatore(int cfu) {
		this.cfu = cfu;
		this.borsa = new Borsa();
	}
	
	public Giocatore(int cfu, int pesoMaxBorsa) {
		this.cfu = cfu;
		this.borsa = new Borsa(pesoMaxBorsa);
	}
	
	public int getCFU() {
		return this.cfu;
	}
	
	public void setCFU(int cfu) {
		this.cfu = cfu;
	}
	
	public Borsa getBorsa() {
		return this.borsa;
	}
	
	public String toString() {
		StringBuilder builder = new StringBuilder("Giocatore con CFU: " + cfu);
		builder.append("\nInformazioni Borsa del giocatore:\n");
		builder.append(this.borsa.toString());
		
		return builder.toString();
	}
}
