package it.uniroma3.diadia.comandi;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;

import it.uniroma3.diadia.ioconsole.IO;

public class FabbricaDiComandiRiflessiva implements FabbricaDiComandi {

	private String capitalize(String input) {
		return Character.toUpperCase(input.charAt(0)) + input.substring(1);
	}
	
	@Override
	public BaseComando fabbricaComando(String istruzione, IO ioConsole) throws ClassNotFoundException, NoSuchMethodException, SecurityException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		Scanner scanner = new Scanner(istruzione);
		
		String commandName = (scanner.hasNext() ? scanner.next() : null );
		String commandArg = (scanner.hasNext() ? scanner.next() : null );
		
		while(scanner.hasNext()) {
			commandArg += " " + scanner.next();
		}
		
		scanner.close();
		
		if(commandName == null) return new ComandoNonValido(ioConsole);
		
		StringBuilder classPath = new StringBuilder("it.uniroma3.diadia.comandi.Comando");
		
		classPath.append(this.capitalize(commandName));
		
		Class<?> clazz = Class.forName(classPath.toString());
		Constructor<?> constructor = clazz.getConstructor(IO.class);
		BaseComando comando = (BaseComando) constructor.newInstance(ioConsole);
		
		comando.setParametro(commandArg);
		
		return comando;
	}

}
