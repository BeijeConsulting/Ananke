package it.beije.ananke.rubrica;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;
public class Application {
	
	public Application() {
		System.out.println("Benvenuto in iRubrica\n");
		JDBCManager db = new JDBCManager();
		
		 menu();
	}
	
	public void importFromFile() {
		List<Contatti> l = new ArrayList<>();
		Scanner sc = new Scanner(System.in);
		String s;
		System.out.println("Inserisci il nome del file");
		 s = "/Users/Gianni/Desktop/" + sc.next();
		 try {
		 if(s.substring(s.length()-3).equals("csv")) {
			 l = CSVManager.readCSV(s);
			 
			 
		 } else if(s.substring(s.length()-3).equals("xml")) {
			 l = XMLManager.readFile(s);
			 
		 } else {
			 System.out.println("Il formato non è valido");
		 }
		 JDBCManager.addContacts(l);
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
		
	}
	public void exportFile(List<Contatti> l) {
		Scanner sc = new Scanner(System.in);
		String s;
		
		System.out.println("Inserisci il nome del file");
		 s = "/Users/Gianni/Desktop/" + sc.next().trim();
		 System.out.println(s.substring(s.length()-3, s.length()));
		 try {
		 if(s.substring(s.length()-3).equals("csv")) {
			 CSVManager.writeCSV(l,s);
		 } else if(s.substring(s.length()-3).equals("xml")) {
			 XMLManager.writeXML(l,s);
		 } else {
			 System.out.println("Il formato non è valido");
		 }
		 }catch(Exception e) {
			 e.printStackTrace();
		 }
	}

	public void menu() {
		Scanner sc = new Scanner(System.in);
		Rubrica r1 = new Rubrica();
		HDBManager.readDB();
		String s,addmore;
		do {
			System.out.println("Inserisci un numero per svolgere un comando: ");
			System.out.println("1.Ricerca Contatti\n2.Inserisci Contatto\n3.Elimina Contatto\n4.Importa da file\n5.Esporta file\n6.Importa dal dB\n7.Chiudi");
			 s = sc.next();

			 switch(s) {
			 case "1":
				 System.out.println("Inserisci email dei contatti da cercare: ");
				 String e1 = sc.next();
				try {
					JDBCManager.containContacts(e1);
				} catch (SQLException e3) {
					// TODO Auto-generated catch block
					e3.printStackTrace();
				}
				 break;
			 case "2":
				try {
					JDBCManager.addContacts(r1.addContact());
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				 break;
			 case "3":
				 System.out.println("Inserisci email del contatto da eliminare: ");
				 String e = sc.next();
				 JDBCManager.deleteContact(e);
				 break;
			 case "4":
				
					importFromFile();
				
				 break;
			 case "5":
				exportFile(r1.r);
				 break;
			 case "6":
				 try {
					r1.r = JDBCManager.readDb();
				} catch (SQLException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
				 break;
			 case "7":
				 break;
			 default:
				 System.out.println("Il numero inserito non è corretto");
				 break;
			 }
			 
			 System.out.println("Vuoi chiudere? Se si clicca 7");
			  s = sc.next();

			 }while(!s.equals("7"));
	 
	}
	
	
	public static void main(String[] args) {
		Application app = new Application();
	}
}
