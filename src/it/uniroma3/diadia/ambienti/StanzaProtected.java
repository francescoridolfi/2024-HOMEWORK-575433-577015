package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaProtected {
	static final protected int NUMERO_MASSIMO_DIREZIONI = 4;
	static final protected int NUMERO_MASSIMO_ATTREZZI = 10;
	
	protected String nome;
	protected List<Attrezzo> attrezzi;
	protected Map<String, Stanza> stanzeAdiacenti;
	protected String[] direzioni;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public StanzaProtected(String nome) {
        this.nome = nome;
        this.stanzeAdiacenti = new HashMap<String, Stanza>();

        this.attrezzi = new ArrayList<Attrezzo>();
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
    	
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
	public Stanza getStanzaAdiacente(String direzione) {
        if(this.stanzeAdiacenti.containsKey(direzione)) return this.stanzeAdiacenti.get(direzione);
        
        return null;
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
    	for (String direzione : this.direzioni) {  //foreach
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


	public List<String> getDirezioni() {
		return new ArrayList<String>(this.stanzeAdiacenti.keySet());
    }
	
	public boolean hasDirezione(String direzione) {
		return this.stanzeAdiacenti.containsKey(direzione);
	}
}
