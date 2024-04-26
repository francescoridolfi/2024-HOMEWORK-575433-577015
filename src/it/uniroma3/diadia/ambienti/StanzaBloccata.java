package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza {
	
	private String direzioneBloccata;
	private String nomeAttrezzoSbloccaStanza;

	public StanzaBloccata(String nome, String direzioneVietata, String attrezzoSbloccante) {
		super(nome);
		this.direzioneBloccata = direzioneVietata;
		this.nomeAttrezzoSbloccaStanza = attrezzoSbloccante;
	}

	@Override
	public Stanza getStanzaAdiacente(String direzione) {
		if (hasAttrezzo(nomeAttrezzoSbloccaStanza) == false && direzione.equals(direzioneBloccata)) {
			return StanzaBloccata.this;
		} else {
			return super.getStanzaAdiacente(direzione);
		}
	}

	@Override
	public String getDescrizione() {
		
		StringBuilder output = new StringBuilder();

		if(!hasAttrezzo(nomeAttrezzoSbloccaStanza)) {
			output.append("La stanza ha una direzione bloccata verso: " + this.direzioneBloccata + "\n");
			output.append("Attrezzo per sbloccare la stanza: " + this.nomeAttrezzoSbloccaStanza + "\n");
		} else {
			output.append("La stanza è sbloccata grazie all'attrezzo: "+this.nomeAttrezzoSbloccaStanza + "\n");
		}
		output.append(super.getDescrizione());
		
		
		return output.toString();
	}
	
}
