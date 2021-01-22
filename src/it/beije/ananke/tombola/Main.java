
package it.beije.ananke.tombola;


public class Main {

	public static void main(String... args){
		
		// Cartella c = new Cartella();
		// c.visualizzaCartella();
		
		Tombola t1 = new Tombola();
		//t1.nuovaCartella();
		t1.generaCartelleBase();
		t1.stampaCartelle();
		
		
		/*t1.nuovaCartella();
		System.out.println("\n\n");
		t1.stampaCartelle();*/
		
		for(int i = 0; i < 44; i++)
			t1.nuovaCartella();
		
		
		System.out.println("\n\n");
		t1.stampaCartelle();
		System.out.println("NUMERO CARTELLE DELLA TOMBOLA:" + t1.getNumCartelle());
		/*
		Cartella c1 = new Cartella();
		c1 = Cartella.generaCartella(Controllo.colDaDue());
		c1.visualizzaCartella();
		
		Cartella c2 = new Cartella();
		c2 = Cartella.generaCartella(Controllo.colDaDue());
		c2.visualizzaCartella();
		
		Cartella c3 = new Cartella();
		c3 = Cartella.generaCartella(Controllo.colDaDue());
		c3.visualizzaCartella();
		*/
		
	}
}
