package it.beije.ananke.rubrica;

//                   import java.io.File;
//import java.io.FileReader;
//import java.io.FileWriter;
import java.util.Scanner;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

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
		
		//Session ses =SessionManager.apriSessione();
		//R.caricaRubricaDaSCV("C:\\Users\\Padawan06\\Desktop\\A\\rubrica.txt");
		//R.caricaRubricaDaDB();
		//R.cercaContattiDB("Mario");
		//RubricaSQL.cercaContattoSuDB("Gino");
		//R.visualizzaRubrica();
		//R.leggiRubricaXML("C:\\Users\\Padawan06\\Desktop\\A\\rubrica2.xml");
		//R.daXMLaDB("C:\\Users\\Padawan06\\Desktop\\A\\rubrica2.xml");
		//R.getSize();
		//RubricaSQL.provaConnessione();
		//RubricaHQL.cercaContatti("Mario");
		//RubricaHQL.inserisciContatto("Rossi","Rossi","3437866","rossi.r@gmail.com");
		stampaMenu();
		String comando = s.next();
		
		while (!comando.equalsIgnoreCase("E")) {
			
			
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
				
				R.aggiungiContatto(nome, cognome, numeroTel, mail);
				
				break;
				
			case "V":
				
				R.visualizzaRubrica();
				break;
				
			case "C":
				
				System.out.println("Inserisci Parola chiave da cercare:");
				parolaChiave = s.next();
				RubricaSQL.cercaContattoSuDB(parolaChiave);
				//R.cercaContatti(parolaChiave);
				break;
				
			case "D":
				
				System.out.println("Inserisci parola chiave per il contatto da eliminare:");
				parolaChiave = s.next();
				RubricaSQL.eliminaContattoSuDB(parolaChiave);
				//R.eliminaContatto(parolaChiave);
				break;
				
			case "M":
				
				System.out.println("Inserisci parola chiave per il contatto da eliminare:");
				parolaChiave = s.next();
				R.modificaContatto(parolaChiave);
				break;
				
			case "1":
				
				R.daCSVaDB("C:\\Users\\Padawan06\\Desktop\\A\\rubrica.txt");
				break;
				
			case "2":
				
				R.daXMLaDB("C:\\Users\\Padawan06\\Desktop\\A\\rubrica2.xml");
				break;
				
			case "3":
				
				R.caricaRubricaDaDB();
				break;
				
			default: 
				
				System.out.println("IL COMANDO SELZIONATO NON ESISTE! Riprova\n\n");
				break;
			
			}
			
		
			stampaMenu();
			//s.close();
			//s = new Scanner(System.in);
			comando = s.next();
			
		}
		
		//R.scriviRubricaCSV();
		//R.scriviRubricaXML();
		s.close();
	}
	
	
	
	
	
	
	
	public static void stampaMenu() {
		
	System.out.println("\n");
	System.out.println("MENU:");
	System.out.println("Digita un comando");
	System.out.println("-----------------------------------------------------------");
	System.out.println("N: Inserisci nuovo contatto");
	System.out.println("F: Inserisci nuovo contatto e salva su file immediatamente");
	System.out.println("C: Cerca contatto");
	System.out.println("V: Visualizza intera Rubrica");
	System.out.println("D: Elimina un contatto da rubrica ");
	System.out.println("M: Modifica un contatto in rubrica ");
	System.out.println("1: Per copiare da .CSV a DB");
	System.out.println("2: Per copiare da .XML a DB");
	System.out.println("3: Per caricare i contatti da DB");
	System.out.println("E: Per Terminare ");
	System.out.println("-----------------------------------------------------------");
	System.out.println();
	
	}

}
