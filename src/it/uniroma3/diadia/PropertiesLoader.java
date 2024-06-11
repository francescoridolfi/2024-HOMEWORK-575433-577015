package it.uniroma3.diadia;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesLoader {
	private static final String PESO_MAX = "pesoMaxBorsa";
	private static final String CFU = "startCFU";
	private static Properties prop = null;
	
	public static int getCFU() {
		if(prop == null)
			load();
		
		return Integer.parseInt(prop.getProperty(CFU));
	}
	
	public static int getPesoMaxBorsa() {
		if(prop == null)
			load();
		
		return Integer.parseInt(prop.getProperty(PESO_MAX));
	}

	private static void load() {
		prop = new Properties();
		
		try {
			FileInputStream input= new FileInputStream("diadia.properties");
			prop.load(input);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
