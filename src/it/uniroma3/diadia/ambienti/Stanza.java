package it.uniroma3.diadia.ambienti;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza {
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;
	
	private String nome;
    private List<Attrezzo> attrezzi;
    private Map<Direzione, Stanza> stanzeAdiacenti;
    private AbstractPersonaggio personaggio;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
        this.stanzeAdiacenti = new HashMap<Direzione, Stanza>();

        this.attrezzi = new ArrayList<Attrezzo>();
    }
    
    public void setPersonaggio(AbstractPersonaggio personaggio) {
    	this.personaggio = personaggio;
    }
    
    public AbstractPersonaggio getPersonaggio() {
    	return this.personaggio;
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(Direzione direzione, Stanza stanza) {
    	
    	if(this.stanzeAdiacenti.containsKey(direzione)) {
    		this.stanzeAdiacenti.put(direzione, stanza);
    	}
    	
    	if(this.stanzeAdiacenti.size() >= NUMERO_MASSIMO_DIREZIONI) return;
    	
    	this.stanzeAdiacenti.put(direzione, stanza);
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(Direzione direzione) {
        if(this.stanzeAdiacenti.containsKey(direzione)) return this.stanzeAdiacenti.get(direzione);
        
        return null;
	}
	
	public Map<Direzione, Stanza> getStanzeAdiacenti() {
		return this.stanzeAdiacenti;
	}

	@Override
	public boolean equals(Object obj) {
		if(obj == null) return false;
		if(!(obj instanceof Stanza)) return false;
		
		Stanza that = (Stanza) obj;
		
		return this.getNome().equals(that.getNome());
	}
	
    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public List<Attrezzo> getAttrezzi() {
        return this.attrezzi;
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
    	if(this.attrezzi.size() >= NUMERO_MASSIMO_ATTREZZI) return false;
    	
    	if(this.hasAttrezzo(attrezzo.getNome())) return false;
    	
    	this.attrezzi.add(attrezzo);
    	return true;
    }

   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder(); // assemblatore di stringhe
    	
    	risultato.append(this.nome);
    	
    	risultato.append("\n- Uscite: ");
    	boolean has_exits = false;
    	for (Direzione direzione : this.getDirezioni()) {  //foreach
    		if (direzione == null) continue;
    		
    		risultato.append(" " + direzione);
    		has_exits = true;
    	}
    	if(!has_exits) {
    		risultato.append("Nessun Uscita Trovata");
    	}
    	
    	risultato.append("\n- Attrezzi nella stanza: ");
    	boolean has_items = false;
    	for (Attrezzo attrezzo : this.attrezzi) {
    		if(attrezzo == null) continue;
    		
    		risultato.append(attrezzo.toString()+" ");
    		has_items = true;
    	}
    	if(!has_items) {
    		risultato.append("Nessun Attrezzo Trovato");
    	}
    	
    	risultato.append("\nPersonaggio nella stanza: ");
    	risultato.append((this.getPersonaggio() != null) ? this.getPersonaggio().getNome() : null);
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		for(Attrezzo attrezzo : this.attrezzi) {
			if(attrezzo.getNome().equals(nomeAttrezzo)) return true;
		}
		return false;
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for (Attrezzo attrezzo : this.attrezzi) {
			if (attrezzo.getNome().equals(nomeAttrezzo))
				return attrezzo;
		}
		return null;	
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		for(Attrezzo a : this.attrezzi) {
			if(a != attrezzo) continue;
			
			this.attrezzi.remove(a);
			return true;
		}
		
		return false;
	}


	public List<Direzione> getDirezioni() {
		return new ArrayList<Direzione>(this.stanzeAdiacenti.keySet());
    }
	
	public boolean hasDirezione(Direzione direzione) {
		return this.stanzeAdiacenti.containsKey(direzione);
	}

}