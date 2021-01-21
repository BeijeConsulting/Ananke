package it.beije.ananke.tombola;
import java.util.ArrayList;

public class Tombola{

	private ArrayList<Cartella> cartelle;
	private boolean cartelleBase = false;          // Reisgto se la tombola ha gia generato le cartelle base!
	private int numCartelle;
	
	public Tombola(){
		
		this.cartelle = new ArrayList<Cartella>();		
	}
	
	public void generaCartelleBase(){
		
		if(this.cartelleBase == true) {System.out.print("HAI GIA GENERATO LE CARTELLE BASE!"); return;}
		
		int tab[][] = { { 1, 2, 3, 4, 5, 6, 7, 8, 9},
						{10,11,12,13,14,15,16,17,18,19},
						{20,21,22,23,24,25,26,27,28,29},
						{30,31,32,33,34,35,36,37,38,39},
						{40,41,43,43,44,45,46,47,48,49},
						{50,51,52,53,54,55,56,57,58,59},
						{60,61,62,63,64,65,66,67,68,69},
						{70,71,72,73,74,75,76,77,78,79},
						{80,81,82,83,84,85,86,87,88,89,90} };
		
		this.addCartella(Cartella.generaCartellaBase(tab,0,1,3));
		this.addCartella(Cartella.generaCartellaBase(tab,2,4,6));
		this.addCartella(Cartella.generaCartellaBase(tab,2,4,7));
		this.addCartella(Cartella.generaCartellaBase(tab,1,6,8));
		this.addCartella(Cartella.generaCartellaBase(tab,0,5,7));
		this.addCartella(Cartella.generaCartellaBase(tab,0,3,5));
		this.cartelleBase = true;
		this.numCartelle = 6;
		/*for(int i = 0; i < 9; i++) {
			for(int j = 0; j < tab[i].length; j++) {
				System.out.print(tab[i][j] + " ");
			}
			System.out.println();
		}*/
		
	}
	
	public void nuovaCartella(){
		
		if(this.cartelleBase == false) {
			System.out.println("DEVI GENERARE LE CARTELLE BASE PRIMA DI AGGIUNGERE UNA NUOVA CARTELLA! \n");
			return;
		}
		
		int colDaDue[] = new int[3];
		
		
		Cartella c;
		do {
			
			colDaDue = Utility.colDaDue();
			c  = Cartella.generaCartella(colDaDue);
		
		}while(this.controllaElencoCartelle(c));
		
		this.numCartelle++;
		this.addCartella(c);
		
	}
	
	
	private boolean controllaElencoCartelle(Cartella contr) {
		
		for(Cartella c : this.cartelle ) {
			
			if(c.equals(contr))
				return true;
		}
		
		return false;
	}

	public void addCartella(Cartella c){
		
		this.cartelle.add(c);
	}
	
	public void stampaCartelle() {
		
		for(Cartella c : this.cartelle) {
			c.visualizzaCartella();
		}
		
		System.out.println("\n\n");
	}
	
	public int getNumCartelle() {
		
		return this.numCartelle;
	}
	
}
