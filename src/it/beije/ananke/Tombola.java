package it.beije.ananke;

import java.util.ArrayList;

public class Tombola {

	int count=0;
	ArrayList<Cartella> listaCartelle = new ArrayList<>();
	public static void main(String[] args) {
		Tombola tombola = new Tombola();
		for(int i = 0; i<6; i++) {
			tombola.creaCartella();
		}
	}
	
	public void creaCartella() {
		Cartella c = new Cartella();
		if(controlloUguaglianza(c)) {
			c.stampaCartella();
			count++;
			listaCartelle.add(c);
		}
	}
	
	public boolean controlloUguaglianza(Cartella nuovaCartella) {
		if(listaCartelle.isEmpty()) {
			return true;
		}else {
			for(Cartella cartella:listaCartelle) {
				if(nuovaCartella.equals(cartella)) {
					return false;
				}
			}
		}
		return true;
	}

}
