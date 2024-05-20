package it.uniroma3.diadia.giocatore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezzoPerNome;
import it.uniroma3.diadia.attrezzi.ComparatoreAttrezzoPerPeso;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private List<Attrezzo> attrezzi;
	private int pesoMax;
	
	public Borsa() {
		this(DEFAULT_PESO_MAX_BORSA);
	}
	
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new ArrayList<Attrezzo>();
	}
	
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax())
			return false;
		
		this.attrezzi.add(attrezzo);
		
		return true;
	}
	
	public int getPesoMax() {
		return pesoMax;
	}
	
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		for(Attrezzo attrezzo : this.attrezzi)
			if(attrezzo.getNome().equals(nomeAttrezzo)) return attrezzo;
		
		return null;
	}
	
	public int getPeso() {
		int peso = 0;
		for(Attrezzo attrezzo : this.attrezzi)
			peso += attrezzo.getPeso();
		
		return peso;
	}
	
	public boolean isEmpty() {
		return this.attrezzi.size() == 0;
	}
	
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo) != null;
	}
	
	public Attrezzo removeAttrezzo(String nomeAttrezzo) {
		
		for(Attrezzo attrezzo : this.attrezzi) {
			if(!attrezzo.getNome().equals(nomeAttrezzo)) continue;
			
			this.attrezzi.remove(attrezzo);
			return attrezzo;
		}
		
		return null;
	}
	
	public List<Attrezzo> getContenutoOrdinatoPerPeso() {
		List<Attrezzo> result = new ArrayList<Attrezzo>(this.attrezzi);
		
		Collections.sort(result, new ComparatoreAttrezzoPerPeso());
		
		return result;
	}
	
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso() {
		SortedSet<Attrezzo> result = new TreeSet<Attrezzo>(new ComparatoreAttrezzoPerPeso());
		for(Attrezzo a : this.attrezzi)
			result.add(a);
		
		return result;
	}
	
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome() {
		SortedSet<Attrezzo> result = new TreeSet<Attrezzo>(new ComparatoreAttrezzoPerNome());
		for(Attrezzo a : this.attrezzi)
			result.add(a);
		
		return result;
	}
	
	public Map<Integer, Set<Attrezzo>> getContenutoRaggruppatoPerPeso() {
		Map<Integer, Set<Attrezzo>> result = new HashMap<Integer, Set<Attrezzo>>();
		
		for(Attrezzo attrezzo : this.attrezzi) {
			if(!result.containsKey(attrezzo.getPeso())) {
				result.put(attrezzo.getPeso(), new LinkedHashSet<Attrezzo>());
			}
			
			result.get(attrezzo.getPeso()).add(attrezzo);
		}
		
		return result;
	}
	
	public String toString() {
		if (this.isEmpty()) return "Borsa vuota";
		
		StringBuilder s = new StringBuilder();
		s.append("Contenuto borsa (" + this.getPeso() + "kg/" + this.getPesoMax() + "kg): ");
		
		for(Attrezzo attrezzo : this.attrezzi)
			s.append(attrezzo.toString() + " ");
		
		return s.toString();
	}
}
