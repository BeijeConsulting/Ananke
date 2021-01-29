package it.beije.ananke.rubrica;

//                   import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
import java.util.Scanner;

public class Main {
	
	public static void main(String[] args) throws Exception {
		
		Scanner s = new Scanner(System.in);
		String parolaChiave;
		Rubrica R = new Rubrica();
		//Contatto c;
		String nome;
		String cognome;
		String numeroTel;
		String mail;
		
		R.caricaRubricaDaSCV("C:\\Users\\Padawan06\\Desktop\\A\\rubrica.txt");
	//	R.visualizzaRubrica();
		//R.leggiRubricaXML("C:\\Users\\Padawan06\\Desktop\\A\\rubrica2.xml");
		//R.getSize();
		RubricaSQL.provaConnessione();
		stampaMenu();
		String comando = s.next();
		
		while (!comando.equalsIgnoreCase("E")) {
			
			//stampaMenu();
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
				
				
				R.aggiungiContatto(nome, cognome, numeroTel, mail);
				//R.aggiungiContatto(c);
				break;
				
			case "F":
				
				System.out.println("Inserisci Nome:");
				nome = s.next();
				System.out.println("Inserisci Cognome:");
				cognome = s.next();
				System.out.println("Inserisci Num di telefono:");
				numeroTel = s.next();
				System.out.println("Inserisci Email:");
				mail = s.next();
				
				//c = new Contatto(nome, cognome, numeroTel, mail);
				R.aggiungiContatto(nome, cognome, numeroTel, mail);
				
				break;
				
			case "V":
				
				R.visualizzaRubrica();
				break;
				
			case "C":
				
				System.out.println("Inserisci Parola chiave da cercare:");
				parolaChiave = s.next();
				R.cercaContatti(parolaChiave);
				break;
				
			case "D":
				
				System.out.println("Inserisci parola chiave per il contatto da eliminare:");
				parolaChiave = s.next();
				R.eliminaContatto(parolaChiave);
				break;
				
			case "M":
				
				System.out.println("Inserisci parola chiave per il contatto da eliminare:");
				parolaChiave = s.next();
				R.modificaContatto(parolaChiave);
				break;
				
			default: 
				
				System.out.println("IL COMANDO SELZIONATO NON ESISTE! Riprova\n\n");
				break;
			
			}
			
			stampaMenu();
			comando = s.next();
			
		}
		
		R.scriviRubricaCSV();
		//R.scriviRubricaXML();
		s.close();
	}
	
	
	
	
	
	
	
	public static void stampaMenu() {
		
	System.out.println("\n");
	System.out.println("MENU:");
	System.out.println("-----------------------------------------------------------");
	System.out.println("N: Inserisci nuovo contatto");
	System.out.println("F: Inserisci nuovo contatto e salva su file immediatamente");
	System.out.println("C: Cerca contatto");
	System.out.println("V: Visualizza intera Rubrica");
	System.out.println("D: Elimina un contatto da rubrica ");
	System.out.println("M: Modifica un contatto in rubrica ");
	System.out.println("E: Per Terminare ");
	System.out.println("-----------------------------------------------------------");
	System.out.println();
	
	}

}
