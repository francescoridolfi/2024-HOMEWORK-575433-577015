package it.uniroma3.diadia.ambienti;

import java.util.Random;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	//                                      0       1      2       3
	private static String directions[] = {"nord", "sud", "est", "ovest"};
	
	private Stanza starting;
	private Stanza current;
	private Stanza ending;
	
	public Labirinto() {
		// Se l'istanza è stata definita senza parametri, genero labirinto con solo 5 stanze random
		this(0);
	}
	
	/**
	 * Sistema elementare per la generazione di labirinto random,
	 * arriva fino ad un massimo di due connessioni per stanza tenendo
	 * conto delle direzioni al retroso
	 */
	public Labirinto(int roomsCounter) {
		this.current = new Stanza("Atrio");
		this.current.addAttrezzo(new Attrezzo("osso", 1));
		this.starting = this.current;
		this.ending = new Stanza("Biblioteca");
		this.ending.addAttrezzo(new Attrezzo("lanterna", 3));
		
		roomsCounter = (roomsCounter > 0 ? roomsCounter : 5);
		
		Stanza lastRoom = null;
		String directions[] = new String[2];
		for(int i=0; i < roomsCounter; i++) {
			 directions = this.getRandomDirection();
			 
			 // Controlliamo che non siano già state inserite queste direzioni nella stanza
			 while(true) {
				 if(i == 0) break;
				 
				 if(lastRoom == null) break;
				 
				 if(lastRoom.hasDirezione(directions[0]) || lastRoom.hasDirezione(directions[1])) {
					 directions = this.getRandomDirection();
					 continue;
				 }
				 
				 break;
			 }
			
			// Genero nuova stanza
			Stanza room = new Stanza("Stanza N" + (i+1));
			if (i == 0) {
				// Collego all'atrio se è la prima stanza
				this.current.impostaStanzaAdiacente(directions[0], room);
				room.impostaStanzaAdiacente(directions[1], this.current);
			} else if(lastRoom != null) {
				// Collego alla stanza precedente la nuova stanza
				room.impostaStanzaAdiacente(directions[0], lastRoom);
				lastRoom.impostaStanzaAdiacente(directions[1], room);
			}
			
			lastRoom = room;
		}
		
		directions = this.getRandomDirection();
		// Controlliamo che non siano già state inserite queste direzioni nella stanza
		while(true) {
			if(lastRoom == null) break;
			 
			if(lastRoom.hasDirezione(directions[0]) || lastRoom.hasDirezione(directions[1])) {
				directions = this.getRandomDirection();
				continue;
			}
			 
			break;
		}
		// concludo i collegamenti con l'ultima stanza
		lastRoom.impostaStanzaAdiacente(directions[0], this.ending);
		this.ending.impostaStanzaAdiacente(directions[1], lastRoom);
	}
	
	public Stanza getStanzaVincente() {
		return this.ending;
	}
	
	public void setStanzaVincente(Stanza stanza) {
		this.ending = stanza;
	}
	
	public Stanza getStanzaIniziale() {
		return this.starting;
	}
	
	public void setStanzaIniziale(Stanza stanza) {
		this.starting = stanza;
		this.current = this.starting;
	}
	
	public Stanza getStanzaCorrente() {
		return this.current;
	}
	
	public void setStanzaCorrente(Stanza stanza) {
		this.current = stanza;
	}
	
	public boolean labirintoVinto() {
		return this.current == this.ending;
	}
	/**
	 * Controlla che un attrezzo sia presente all'interno delle stanze del labirinto
	 * @param attrezzo
	 * @return true se presente in una stanza, false se non presente in nessuna.
	 */
	public boolean hasAttrezzo(String nomeAttrezzo) {
		if(this.starting.hasAttrezzo(nomeAttrezzo)) return true;
		
		Stanza next = this.starting.getStanzaAdiacente(this.starting.getDirezioni().get(0));
		String direzione = this.starting.getDirezioni().get(0);
		
		while(next != this.getStanzaVincente()) {
			
			if(next.hasAttrezzo(nomeAttrezzo)) return true;
			
			String toAvoid = this.nextDirection(direzione);
			direzione = next.getDirezioni().get(1);
			
			if(direzione == toAvoid) direzione = next.getDirezioni().get(0);
			
			next = next.getStanzaAdiacente(direzione);
		}
		
		return next.hasAttrezzo(nomeAttrezzo);
	}
	
	/**
	 * Restituisce una coppia di stringhe casuale (due direzioni opposte tra loro)
	 * @return Array String di 2 elementi
	 */
	public String[] getRandomDirection() {
		int index = new Random().nextInt(directions.length);
		
		
		return new String[]{directions[index], directions[index + (index%2==0 ? 1 : -1)]};
	}
	
	public String nextDirection(String direction) {
		for(int i = 0; i < directions.length; i++) {
			if(!directions[i].equals(direction)) continue;
			
			return directions[i + (i%2 == 0 ? 1 : -1)];
		}
		
		return directions[0];
	}
}
