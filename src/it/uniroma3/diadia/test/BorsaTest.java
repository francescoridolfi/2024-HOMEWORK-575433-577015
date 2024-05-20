package it.uniroma3.diadia.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.DiaDia;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.ioconsole.IO;

class BorsaTest {
	
	
	Attrezzo piombo = new Attrezzo("piombo", 10);
	Attrezzo ps = new Attrezzo("ps", 5);
	Attrezzo piuma = new Attrezzo("piuma", 1);
	Attrezzo libro = new Attrezzo("libro", 5);
	
	IO console = DiaDia.getIOConsole();

	/* Test per controllare se il sistema di controllo delle quantità e del
	 * peso totale della borsa funziona ed impedisca l'aggiunta di tali attrezzi
	 * nella lista
	 */
	@Test
	void test() {
		Borsa borsa = new Borsa();
		
		console.mostraMessaggio(borsa.toString());

		for(int i = 0; i < 10; i++) {
			Attrezzo a = new Attrezzo("Ascia N." + (i+1), (i+1)*2);
			
			if(!borsa.addAttrezzo(a))
				console.mostraMessaggio("Non posso aggiungere attrezzo " + a + " per eccessività di spazio/peso");
		}
		
		console.mostraMessaggio(borsa.toString());
		console.mostraMessaggio("-------------------");
	}
	
	boolean isSameOrder(List<Attrezzo> firstList, List<Attrezzo> secondList) {
		
		if(firstList.size() != secondList.size()) return false;
		
		for(int i = 0; i < firstList.size(); i++)
			if(firstList.get(i) != secondList.get(i)) return false;
		
		return true;
	}
	
	@Test
	void testOrdinamenti() {
		Borsa borsa = new Borsa(50);
		
		borsa.addAttrezzo(piombo);
		borsa.addAttrezzo(ps);
		borsa.addAttrezzo(piuma);
		borsa.addAttrezzo(libro);
		
		List<Attrezzo> ordinataPerPeso = new ArrayList<Attrezzo>();
		ordinataPerPeso.add(piuma);
		ordinataPerPeso.add(libro);
		ordinataPerPeso.add(ps);
		ordinataPerPeso.add(piombo);
		
		assertTrue(isSameOrder(borsa.getContenutoOrdinatoPerPeso(), ordinataPerPeso));
		assertTrue(isSameOrder(new ArrayList<Attrezzo>(borsa.getSortedSetOrdinatoPerPeso()), ordinataPerPeso));
		
		List<Attrezzo> ordinataPerNome = new ArrayList<Attrezzo>();
		ordinataPerNome.add(libro);
		ordinataPerNome.add(piombo);
		ordinataPerNome.add(piuma);
		ordinataPerNome.add(ps);
		
		assertTrue(isSameOrder(new ArrayList<Attrezzo>(borsa.getContenutoOrdinatoPerNome()), ordinataPerNome));
		
		
		for(Entry<Integer, Set<Attrezzo>> entry : borsa.getContenutoRaggruppatoPerPeso().entrySet()) {
			StringBuilder output = new StringBuilder("(");
			output.append(entry.getKey());
			output.append(", {");
			for(Attrezzo a : entry.getValue()) {
				output.append(a.getNome()+", ");
			}
			output.append("})");
			console.mostraMessaggio(output.toString());
		}
		
	}
	
	

}
