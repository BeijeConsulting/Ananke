import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class Rubrica {

	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException, SQLException {
	
		ManagerCsv managerCsv = new ManagerCsv();
		ManagerXml managerXml = new ManagerXml();
		// JDBCManager managerJdbc = new JDBCManager();
		
		JDBCManager.connettiDb();
		
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
		System.out.println("-   0   - torna alla scelta della rubrica");
		System.out.println("-   1   - se vuoi inserire un contatto");
		System.out.println("-   2   - se vuoi cercare un contatto");
		System.out.println("-   3   - se vuoi modificare un contatto");
		System.out.println("-   4   - se vuoi eliminare un contatto");
		System.out.println("-   5   - se vuoi visualizzare la lista dei contatti nel file");
		System.out.println("-   6   - per visualizzare i contatti nel database");
		
		
		scelta = scanner.next();
		
	
	if(scelta.equals("0")) {
		do {
			System.out.println("Su quale rubrica vuoi operare? ");
			System.out.println("| xml | csv |");
			
			tipoRubrica = scanner.next();
			
		}while(!(tipoRubrica.equalsIgnoreCase("xml") || tipoRubrica.equalsIgnoreCase("csv")));
	}
		
	if(scelta.equals("1")) {
		
		System.out.println("inserisci il nome");
		String nome = scanner.next();
		
		System.out.println("inserisci il cognome");
		String cognome = scanner.next();
		
		System.out.println("inserisci l' email");
		String email = scanner.next();
		
		System.out.println("inserisci il numero di telefono");
		String telefono = scanner.next();
		
		Contatto tempContatto = new Contatto(nome,cognome,telefono,email);
		
		if (tipoRubrica.equalsIgnoreCase("csv")) {
			
			managerCsv.aggiungiContatto(tempContatto);
			JDBCManager.inserisciContattoDb(tempContatto);
			
		} else {
			
			managerXml.aggiungiContatto(tempContatto);
			JDBCManager.inserisciContattoDb(tempContatto);
		
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
		
		String sceltaModifica = null;
		
		do {
			System.out.println("Che cosa vuoi modificare? ");
			System.out.println("-   0   - termina modifiche");
			System.out.println("-   1   - modifica nome");
			System.out.println("-   2   - modifica cognome");
			System.out.println("-   3   - modifica numero di telefono");
			System.out.println("-   4   - modifica email");
			
			sceltaModifica = scanner.next();
			
			if(sceltaModifica.contentEquals("1")) {
				
				String nomeNuovo = null;
				
				System.out.println("inserisci ora il nuovo nome del contatto");
				nomeNuovo = scanner.next();
				
				if (tipoRubrica.equalsIgnoreCase("csv")) {
					
					managerCsv.modificaNome(tempContatto, nomeNuovo);
					
				} else {
					
					managerXml.modificaNome(tempContatto, nomeNuovo);
				
				}
				
				JDBCManager.modificaNomeContattoDb(tempContatto, nomeNuovo);
				
			}
			
			if(sceltaModifica.contentEquals("2")) {
				
				String cognomeNuovo = null;
				
				System.out.println("inserisci ora il nuovo cognome del contatto");
				cognomeNuovo = scanner.next();
				
				if (tipoRubrica.equalsIgnoreCase("csv")) {
					
					managerCsv.modificaNome(tempContatto, cognomeNuovo);
					
				} else {
					
					managerXml.modificaNome(tempContatto, cognomeNuovo);
				
				}
				
			}

			if(sceltaModifica.contentEquals("3")) {
				
				String telefonoNuovo = null;
				
				System.out.println("inserisci ora il nuovo numero di telefono");
				telefonoNuovo = scanner.next();
				
				if (tipoRubrica.equalsIgnoreCase("csv")) {
					
					managerCsv.modificaNome(tempContatto, telefonoNuovo);
					
				} else {
					
					managerXml.modificaNome(tempContatto, telefonoNuovo);
				
				}
				
			}
			
			if(sceltaModifica.contentEquals("3")) {
				
				String emailNuova = null;
				
				System.out.println("inserisci ora la nuova email");
				emailNuova = scanner.next();
				
				if (tipoRubrica.equalsIgnoreCase("csv")) {
					
					managerCsv.modificaNome(tempContatto, emailNuova);
					
				} else {
					
					managerXml.modificaNome(tempContatto, emailNuova);
				
				}
				
			}
			
		}while(!(sceltaModifica.contentEquals("0")));
		
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
	
	if(scelta.contentEquals("5")) {
		
		System.out.println("Lista dei contatti:");
		System.out.println("");
		
		if (tipoRubrica.equalsIgnoreCase("csv")) {
			
			managerCsv.visualizzaContatti();
			
		} else {
			
			managerXml.visualizzaContatti();
		
		}
		
		
	}
	
	if(scelta.contentEquals("6")) {
		
		System.out.println("Lista dei contatti presenti nel DB");
		
		JDBCManager.visualizzaContattiDb();
		
	}

	}while(!scelta.equalsIgnoreCase("exit"));
	
		System.out.println("");
		System.out.println("Hai chiuso correttamente la rubrica, arrivederci");
	
		scanner.close();
		
		JDBCManager.chiudiConnessioneDb();

	}
	
	

}
