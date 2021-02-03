package it.beije.ananke.altro.tombolone;
import java.util.*;

public class Tombola {
	private ArrayList<String> cartelle = new ArrayList<>();
	
	public ArrayList<String> generaFoglio() {
		Cartella cartella = new Cartella();
		for (int i = 0; i< 6; i++) {
			int[][] crt = cartella.riempiCartella();
			System.out.println(crt);
			cartelle.add(cartella.toString(crt));
		}
		return cartelle;
	}
	
	
	public void generaCartelle(int numeroCartelle) {
		for (int i = 0; i< 6; i++) {
			Cartella cartella = new Cartella();
			cartelle.add(cartella.toString());
		}
	}
	
	

}
