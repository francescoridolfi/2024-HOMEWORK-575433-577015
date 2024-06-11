package it.uniroma3.diadia.ambienti;

import java.lang.reflect.Constructor;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;
import it.uniroma3.diadia.personaggi.TipologiaPersonaggio;

public class Labirinto {
	//                                      0       1      2       3
	private static String directions[] = {"nord", "sud", "est", "ovest"};
	
	private Stanza starting;
	private Stanza current;
	private Stanza ending;
	
	private Labirinto() {
		this.starting = null;
		this.current = null;
		this.ending = null;
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
		Direzione direzione = this.starting.getDirezioni().get(0);
		
		while(next != this.getStanzaVincente()) {
			
			if(next.hasAttrezzo(nomeAttrezzo)) return true;
			
			Direzione toAvoid = Direzione.valueOf(this.nextDirection(direzione.toString()));
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
	
	public static LabirintoBuilder newBuilder() {
        return new LabirintoBuilder();
    }
	
	public static Labirinto fromLoader(CaricatoreLabirinto loader) {
		Labirinto lab = new Labirinto();
		lab.setStanzaIniziale(loader.getStanzaIniziale());
		lab.setStanzaVincente(loader.getStanzaVincente());
		
		return lab;
	}

    public static class LabirintoBuilder {
        private Stanza buffer;
        private List<Stanza> rooms;
        private Labirinto labirinto;

        // Costruttore privato del builder
        private LabirintoBuilder() {
            this.labirinto = new Labirinto();
            this.buffer = null;
            this.rooms = new LinkedList<>();
        }

        public LabirintoBuilder addStanzaIniziale(String nome) {
            this.buffer = new Stanza(nome);
            this.labirinto.setStanzaIniziale(buffer);
            this.rooms.add(buffer);
            return this;
        }

        public LabirintoBuilder addStanzaVincente(String nome) {
            this.buffer = new Stanza(nome);
            this.labirinto.setStanzaVincente(buffer);
            this.rooms.add(buffer);
            return this;
        }

        public LabirintoBuilder addStanza(String nome) {
            if (this.hasStanza(nome)) return this;
            this.buffer = new Stanza(nome);
            this.rooms.add(buffer);
            return this;
        }

        public LabirintoBuilder addAdiacenza(String partenza, String arrivo, String direzione) {
        	return this.addAdiacenza(partenza, arrivo, Direzione.valueOf(direzione));
        }
        
        public LabirintoBuilder addAdiacenza(String partenza, String arrivo, Direzione direzione) {
            Stanza stanzaPartenza = null, stanzaArrivo = null;

            for (Stanza stanza : this.rooms) {
                if (stanza.getNome().equals(partenza)) stanzaPartenza = stanza;
                if (stanza.getNome().equals(arrivo)) stanzaArrivo = stanza;
            }

            if (stanzaPartenza == null || stanzaArrivo == null)
                return this;

            stanzaPartenza.impostaStanzaAdiacente(direzione, stanzaArrivo);
            return this;
        }

        public LabirintoBuilder addAttrezzo(String nome, int peso) {
            if (this.hasAttrezzo(nome)) return this;

            Attrezzo attrezzo = new Attrezzo(nome, peso);
            this.buffer.addAttrezzo(attrezzo);
            return this;
        }

        public LabirintoBuilder addAttrezzo(Attrezzo attrezzo) {
            if (this.hasAttrezzo(attrezzo.getNome())) return this;

            this.buffer.addAttrezzo(attrezzo);
            return this;
        }

        public LabirintoBuilder setPersonaggio(TipologiaPersonaggio tipologia, String nome) {
            if (this.buffer.getPersonaggio() != null) return this;

            Class<?> absClass = tipologia.getAbstractClass();
            if (absClass == null) return this;

            Constructor<?> constructor;

            try {
                constructor = absClass.getConstructor(String.class);
            } catch (Exception e) {
                return this;
            }

            AbstractPersonaggio personaggio;

            try {
                personaggio = (AbstractPersonaggio) constructor.newInstance(nome);
            } catch (Exception e) {
                return this;
            }

            this.buffer.setPersonaggio(personaggio);
            return this;
        }

        public LabirintoBuilder setPersonaggio(AbstractPersonaggio personaggio) {
            if (this.buffer.getPersonaggio() != null) return this;

            this.buffer.setPersonaggio(personaggio);
            return this;
        }

        public boolean hasAttrezzo(String nomeAttrezzo) {
            for (Stanza s : this.rooms) {
                if (s.hasAttrezzo(nomeAttrezzo)) return true;
            }
            return false;
        }

        public Labirinto getLabirinto() {
            return this.labirinto;
        }

        public List<Stanza> getListaStanze() {
            return this.rooms;
        }

        public boolean hasStanza(String nome) {
            for (Stanza s : this.getListaStanze())
                if (s.getNome().equals(nome)) return true;
            return false;
        }
    }
}
