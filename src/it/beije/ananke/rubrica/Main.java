package it.beije.ananke.rubrica;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		Scanner s = new Scanner(System.in);
		String comando = s.next();
		Rubrica R = new Rubrica();
		
		Contatto c;
		String nome;
		String cognome;
		String numeroTel;
		String mail;
		
		stampaMenu();
		
		while (!comando.equalsIgnoreCase("exit")) {
			
			stampaMenu();
			switch(comando) {
				
			case "N":
				
				System.out.println("Inserisci Nome:");
				nome = s.next();
				System.out.println("Inserisci Cognome:");
				cognome = s.next();
				System.out.println("Inserisci Num di telefono:");
				numeroTel = s.next();
				System.out.println("Inserisci Email:");
				mail = s.next();
				
				c = new Contatto(nome, cognome, numeroTel, mail);
				R.aggiungiContattoSulFile(c);
				//R.aggiungiContatto(c);
				break;
				
			case "V":
				
				R.visualizzaRubrica();
				break;
				
			default: 
				
				System.out.println("IL COMANDO SELZIONATO NON ESISTE! Riprova\n\n");
				break;
			
			}
			
			comando = s.next();
			
		}
		
		s.close();
	}
	
	
	
	
	
	
	
	public static void stampaMenu() {
	
	System.out.println("MENU:");
	System.out.println("______________________________________");
	System.out.println("N: Inserisci nuovo contatto");
	System.out.println("V: Visualizza intera Rubrica ");}

}
