package it.uniroma3.diadia.ioconsole;

import java.util.Scanner;

public class IOConsole implements IO {

	@Override
	public void mostraMessaggio(String msg) {
		System.out.println(msg);
	}
	
	@Override
	public String leggiRiga() {
		
		@SuppressWarnings("resource")
		Scanner scannerDiLinee = new Scanner(System.in);
		String riga = scannerDiLinee.nextLine();
		
		// scannerDiLinee.close();
		
		return riga;
	}
	
}
