package it.uniroma3.diadia.test;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaBuia;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {

	@Test
	void testGlowstone() {
		StanzaBuia stanza = new StanzaBuia("Stanza Solitamente Buia", "glowstone");
		
		Attrezzo glowstone = new Attrezzo("glowstone", 25);
		
		stanza.addAttrezzo(glowstone);
		
		assertTrue(stanza.isIlluminato());
	}

}
