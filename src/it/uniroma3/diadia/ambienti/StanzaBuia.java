package it.uniroma3.diadia.ambienti;

public class StanzaBuia extends Stanza {
	
	private String nomeAttrezzoLuce;

	public StanzaBuia(String nome, String nomeAttrezzo) {
		super(nome);
		this.nomeAttrezzoLuce = nomeAttrezzo;
	}

	@Override
	public String getDescrizione() {
		
		String output = "la stanza e' illuminata da: " + this.nomeAttrezzoLuce + "\n" + super.getDescrizione();
		
		if(!isIlluminato()) {
			output += "\nQui c'è buio pesto!";
		}
		

		return output;
	}
	
	public Boolean isIlluminato() {
		if (hasAttrezzo(nomeAttrezzoLuce) == true) {
			return true;
		}
		return false;
	}

}
