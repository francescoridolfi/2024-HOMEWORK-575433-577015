package it.uniroma3.diadia.comandi;

import java.util.Scanner;

import it.uniroma3.diadia.ioconsole.IOConsole;

public class FabbricaDiComandiFisarmonica implements FabbricaDiComandi {

	@Override
	public BaseComando fabbricaComando(String istruzione, IOConsole ioConsole) {
		
		Scanner scanner = new Scanner(istruzione);
		
		String nome = null;
		String parametro = null;
		
		BaseComando comando = null;
		
		if(scanner.hasNext())
			nome = scanner.next();
		
		if(scanner.hasNext())
			parametro = scanner.next();
		
		scanner.close();
		
		if(nome == null) return new ComandoNonValido(ioConsole);
		
		switch(nome) {
		case "aiuto":
			comando = new ComandoAiuto(ioConsole);
			break;
		case "fine":
			comando = new ComandoFine(ioConsole);
			break;
		case "guarda":
			comando = new ComandoGuarda(ioConsole);
			break;
		case "posa":
			comando = new ComandoPosa(ioConsole);
			break;
		case "prendi":
			comando = new ComandoPrendi(ioConsole);
			break;
		case "vai":
			comando = new ComandoVai(ioConsole);
			break;
		default:
			comando = new ComandoNonValido(ioConsole);
			break;
		}
		
		comando.setParametro(parametro);
		
		return comando;
	}

}
