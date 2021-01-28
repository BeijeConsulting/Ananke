package it.beije.ananke.rubrica;
import java.io.File;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;
public class Application {
	
	public Application() {
		System.out.println("Benvenuto nell'applicativo della rubrica\n");
		System.out.println("Inserisci il nome del file da leggere/scrivere: ");
		Scanner c = new Scanner(System.in);
		 String s = c.next();
		 menu(s,c);
	}

	public void menu(String path,Scanner sc) {
		
		Rubrica r1 = new Rubrica("/Users/Gianni/Desktop/" + path);
		
		String s;
		do {
			System.out.println("Inserisci un numero per svolgere un comando: ");
			System.out.println("1.Visualizza Rubrica\n2.Inserisci Contatto\n3.Elimina Contatto\n4.Esporta XML\n5.Esporta CSV\n6.Chiudi Programma");
			 s = sc.next();

			 switch(s) {
			 case "1":
				 System.out.println("Funzione ancora da sviluppare.");
				 break;
			 case "2":
				 if(r1.addContact()) {
				 r1.writeFile(new File(r1.path));
				 }
				 break;
			 case "3":
				 System.out.println("Inserisci email del contatto da eliminare: ");
				 String e = sc.next();
				 r1.deleteContact(e);
				 break;
			 case "4":
				 try {
					XMLManager.writeXML(r1.r);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				} 
				 break;
			 case "5":
				 try {
					CSVManager.writeCSV(r1.r);
				} catch (Exception e1) {
					
					e1.printStackTrace();
				} 
				 break;
			 case "6":
				 break;
			 default:
				 System.out.println("Il numero inserito non è corretto");
				 break;
			 }
			 
			 System.out.println("Vuoi chiudere? Se si clicca 5");
			  s = sc.next();

			 }while(!s.equals("5"));
	}
	
	
	public static void main(String[] args) {
		Application app = new Application();
	}
}
