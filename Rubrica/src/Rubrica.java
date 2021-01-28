import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Rubrica {

	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {
	
		ManagerCsv managerCsv = new ManagerCsv();
		ManagerXml managerXml = new ManagerXml();
		
		Scanner scanner = new Scanner(System.in);
		
		String scelta = "";
		String tipoRubrica = "";
	
	do {
		System.out.println("Su quale rubrica vuoi operare? ");
		System.out.println("| xml | csv |");
		
		tipoRubrica = scanner.next();
		
	}while(!(tipoRubrica.equalsIgnoreCase("xml") || tipoRubrica.equalsIgnoreCase("csv")));
		
	
	do {
		
		System.out.println("Digita per effettuare l' operazione : ");
		System.out.println("");
		System.out.println("-  exit - se vuoi uscire");
		System.out.println("-   1   - se vuoi inserire un contatto");
		System.out.println("-   2   - se vuoi cercare un contatto");
		System.out.println("-   3   - se vuoi modificare un contatto");
		System.out.println("-   4   - se vuoi eliminare un contatto");
		
		
		scelta = scanner.next();
		
		
	if(scelta.equals("1")) {
		
		System.out.println("inserisci il nome");
		String nome = scanner.next();
		
		System.out.println("inserisci il cognome");
		String cognome = scanner.next();
		
		System.out.println("inserisci l' email");
		String email = scanner.next();
		
		System.out.println("inserisci il numero di telefono");
		String telefono = scanner.next();
		
		if (tipoRubrica.equalsIgnoreCase("csv")) {
			
			managerCsv.aggiungiContatto(new Contatto(nome,cognome,telefono,email));
			
		} else {
			
			managerXml.aggiungiContatto(new Contatto(nome, cognome, telefono, email));
		
		}
		
		
	}
	
	if(scelta.contentEquals("2")) {
		
		System.out.println("inserisci il nome del contatto che vuoi cercare");
		String nomeDaCercare = scanner.next();
		
		System.out.println("inserisci il cognome del contatto che vuoi cercare");
		String cognomeDaCercare = scanner.next();
		
		if (tipoRubrica.equalsIgnoreCase("csv")) {
			
			managerCsv.cerca(nomeDaCercare, cognomeDaCercare);
			
		} else {
			
			managerXml.cerca(nomeDaCercare, cognomeDaCercare);
		
		}
		
	}
	
	if(scelta.contentEquals("3")) {
		
		System.out.println("inserisci il nome del contatto che vuoi modificare");
		String nomeDaModificare = scanner.next();
		
		System.out.println("inserisci il cognome del contatto che vuoi modificare");
		String cognomeDaModificare = scanner.next();
		
		System.out.println("");
		
		Contatto tempContatto;
		
		if (tipoRubrica.equalsIgnoreCase("csv")) {
			
			tempContatto = managerCsv.cerca(nomeDaModificare, cognomeDaModificare);
			
		} else {
			
			tempContatto = managerXml.cerca(nomeDaModificare, cognomeDaModificare);
		
		}
		
		System.out.println("inserisci ora il nuovo nome del contatto");
		String nomeNuovo = scanner.next();
		
		System.out.println("inserisci ora il nuovo cognome del contatto");
		String cognomeNuovo = scanner.next();
		
		System.out.println("inserisci ora il nuovo numero di telefono");
		String telefonoNuovo = scanner.next();
		
		System.out.println("inserisci ora la nuova email");
		String emailNuova = scanner.next();
		
		if (tipoRubrica.equalsIgnoreCase("csv")) {
			
			managerCsv.modifica(tempContatto, nomeNuovo, cognomeNuovo, telefonoNuovo, emailNuova);
			
		} else {
			
			managerXml.modifica(tempContatto, nomeNuovo, cognomeNuovo, telefonoNuovo, emailNuova);
		
		}
		
	}
	
	if(scelta.contentEquals("4")) {
		
		System.out.println("inserisci il nome del contatto che vuoi eliminare");
		String nomeDaEliminare = scanner.next();
		
		System.out.println("inserisci il cognome del contatto che vuoi eliminare");
		String cognomeDaEliminare = scanner.next();
		
		if (tipoRubrica.equalsIgnoreCase("csv")) {
			
			managerCsv.elimina(nomeDaEliminare, cognomeDaEliminare);
			
		} else {
			
			managerXml.elimina(nomeDaEliminare, cognomeDaEliminare);
		
		}
		
		
	}

	}while(!scelta.equalsIgnoreCase("exit"));
	
		System.out.println("");
		System.out.println("Hai chiuso correttamente la rubrica, arrivederci");
	
		scanner.close();

	}
	
	

}
