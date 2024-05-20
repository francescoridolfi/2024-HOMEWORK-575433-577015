package it.uniroma3.diadia.ambienti;

import java.util.LinkedList;
import java.util.List;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder extends Labirinto {
	
	private Stanza buffer;
	private List<Stanza> rooms;
	
	public LabirintoBuilder() {
		super();
		this.buffer = null;
		this.rooms = new LinkedList<Stanza>();
	}
	
	public LabirintoBuilder addStanzaIniziale(String nome) {
		this.buffer = new Stanza(nome);
		
		this.setStanzaIniziale(buffer);
		
		this.rooms.add(buffer);
		
		return this;
	}
	
	public LabirintoBuilder addStanzaVincente(String nome) {
		this.buffer = new Stanza(nome);
		
		this.setStanzaVincente(buffer);
		
		this.rooms.add(buffer);
		
		return this;
	}
	
	public LabirintoBuilder addStanza(String nome) {
		if(this.hasStanza(nome)) return this;
		
		this.buffer = new Stanza(nome);
		this.rooms.add(buffer);
		
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String partenza, String arrivo, String direzione) {
		Stanza stanzaPartenza = null, stanzaArrivo = null;
		
		for(Stanza stanza : this.rooms) {
			if(stanza.getNome().equals(partenza)) stanzaPartenza = stanza;
			
			if(stanza.getNome().equals(arrivo)) stanzaArrivo = stanza;
		}
		
		if(stanzaPartenza == null || stanzaArrivo == null) 
			return this;
		
		stanzaPartenza.impostaStanzaAdiacente(direzione, stanzaArrivo);
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nome, int peso) {
		if(this.hasAttrezzo(nome)) return this;
		
		Attrezzo attrezzo = new Attrezzo(nome, peso);
		this.buffer.addAttrezzo(attrezzo);
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(Attrezzo attrezzo) {
		if(this.hasAttrezzo(attrezzo.getNome())) return this;
		
		
		this.buffer.addAttrezzo(attrezzo);
		return this;
	}
	
	@Override
	public boolean hasAttrezzo(String nomeAttrezzo) {
		for(Stanza s : this.rooms) {
			if(s.hasAttrezzo(nomeAttrezzo)) return true;
		}
		return false;
	}
	
	public Labirinto getLabirinto() {
		return this;
	}
	
	public List<Stanza> getListaStanze() {
		return this.rooms;
	}
	
	public boolean hasStanza(String nome) {
		for(Stanza s : this.getListaStanze())
			if(s.getNome().equals(nome)) return true;
		return false;
	}
	
	
}
