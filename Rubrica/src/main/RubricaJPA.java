package main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import database.JPAManager;
import entità.Contatto;
import fileManager.ManagerCsv;
import fileManager.ManagerXml;

public class RubricaJPA {
	
	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException, SQLException {
		
		ManagerCsv managerCsv = new ManagerCsv();
		ManagerXml managerXml = new ManagerXml();
		JPAManager managerJPA = new JPAManager();
		
		Scanner scanner = new Scanner(System.in);
		
		String scelta = "";
		String tipoRubrica = "";
	
	do {
		System.out.println("");
		System.out.println("Su quale rubrica vuoi operare? ");
		System.out.println("| xml | csv | db |");
		
		tipoRubrica = scanner.next();
		
	}while(!(tipoRubrica.equalsIgnoreCase("xml") || tipoRubrica.equalsIgnoreCase("csv") || tipoRubrica.equalsIgnoreCase("db")));
		
	
	do {
		
		System.out.println("Digita per effettuare l' operazione : ");
		System.out.println("");
		System.out.println("-  exit - se vuoi uscire");
		System.out.println("-   0   - torna alla scelta della rubrica");
		System.out.println("-   1   - se vuoi inserire un contatto");
		System.out.println("-   2   - se vuoi cercare un contatto");
		System.out.println("-   3   - se vuoi modificare un contatto");
		System.out.println("-   4   - se vuoi eliminare un contatto");
		
		if(tipoRubrica.equalsIgnoreCase("xml") || tipoRubrica.equalsIgnoreCase("csv")) {
			System.out.println("-   5   - se vuoi visualizzare la lista dei contatti nel file");
		}

		System.out.println("-   6   - per visualizzare i contatti nel database");
		
		if(tipoRubrica.equalsIgnoreCase("csv") || tipoRubrica.equalsIgnoreCase("db")) {
			System.out.println("-   7   - esporta da csv a database");
			System.out.println("-   8   - importa da database a csv");
		}
		
		if(tipoRubrica.equalsIgnoreCase("xml") || tipoRubrica.equalsIgnoreCase("db")) {
			System.out.println("-   9   - esporta da xml a database");
			System.out.println("-   10  - importa da database a xml");
		}
		
		scelta = scanner.next();
		
	
	if(scelta.equals("0")) {
		do {
			System.out.println("Su quale rubrica vuoi operare? ");
			System.out.println("| xml | csv | db |");
			
			tipoRubrica = scanner.next();
			
		}while(!(tipoRubrica.equalsIgnoreCase("xml") || tipoRubrica.equalsIgnoreCase("csv") || tipoRubrica.contentEquals("db")));
	}
		
	if(scelta.equals("1")) {
		
		String nome;
		String cognome;
		String email;
		String telefono;
		
		boolean ok = true;
		
		do {
			
		if (!ok) {
			System.out.println("");
			System.out.println("Devi inserire almeno un campo valido!");
			System.out.println("Riinserisci i valori");
			System.out.println("");
		}
		
		ok = false;
			
		System.out.println("inserisci il nome");
		System.out.println("digita un punto  .  se non vuoi inserire alcun nome");
		nome = scanner.next();
		
		System.out.println("inserisci il cognome");
		System.out.println("digita un punto  .  se non vuoi inserire alcun cognome");
		cognome = scanner.next();
		
		System.out.println("inserisci l' email");
		System.out.println("digita un punto  .  se non vuoi inserire alcun email");
		email = scanner.next();
		
		System.out.println("inserisci il numero di telefono");
		System.out.println("digita un punto  .  se non vuoi inserire alcun numero di telefono");
		telefono = scanner.next();
		
		} while((nome.equalsIgnoreCase(".")) && (cognome.equalsIgnoreCase(".")) && (email.equalsIgnoreCase(".")) && (telefono.equalsIgnoreCase(".")));
		
		Contatto tempContatto = new Contatto(nome,cognome,telefono,email);
		
		if (tipoRubrica.equalsIgnoreCase("csv")) {
			
			managerCsv.aggiungiContatto(tempContatto);
			
		} else if (tipoRubrica.equalsIgnoreCase("xml")){
			
			managerXml.aggiungiContatto(tempContatto);
			
		}else managerJPA.inserisciContattoDb(tempContatto);;				
		
	}
	
	if(scelta.contentEquals("2")) {
		
		System.out.println("inserisci il nome del contatto che vuoi cercare");
		String nomeDaCercare = scanner.next();
		
		System.out.println("inserisci il cognome del contatto che vuoi cercare");
		String cognomeDaCercare = scanner.next();
		
		if (tipoRubrica.equalsIgnoreCase("csv")) {
			
			managerCsv.cerca(nomeDaCercare, cognomeDaCercare);
			
		} else if (tipoRubrica.equalsIgnoreCase("xml")){
			
			managerXml.cerca(nomeDaCercare, cognomeDaCercare);
		
		}
		
		else {
			
			managerJPA.cercaContattoDb(nomeDaCercare, cognomeDaCercare);
			
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
			
		} else if (tipoRubrica.equalsIgnoreCase("xml")){
			
			tempContatto = managerXml.cerca(nomeDaModificare, cognomeDaModificare);
		
		}else {
			
			tempContatto = managerJPA.cercaContattoDb(nomeDaModificare, cognomeDaModificare);
			
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
					
				} else if (tipoRubrica.equalsIgnoreCase("csv")){
					
					managerXml.modificaNome(tempContatto, nomeNuovo);
				
				}else {
					
					managerJPA.modificaNomeContattoDb(tempContatto, nomeNuovo);
					
				}
				
				
				
			}
			
			if(sceltaModifica.contentEquals("2")) {
				
				String cognomeNuovo = null;
				
				System.out.println("inserisci ora il nuovo cognome del contatto");
				cognomeNuovo = scanner.next();
				
				if (tipoRubrica.equalsIgnoreCase("csv")) {
					
					managerCsv.modificaCognome(tempContatto, cognomeNuovo);
					
				} else if (tipoRubrica.equalsIgnoreCase("xml")){
					
					managerXml.modificaCognome(tempContatto, cognomeNuovo);
				
				} else {
					
					managerJPA.modificaCognomeContattoDb(tempContatto, cognomeNuovo);
					
				}
				
			}

			
			if(sceltaModifica.contentEquals("4")) {
				
				String telefonoNuovo = null;
				
				System.out.println("inserisci ora il nuovo numero di telefono");
				telefonoNuovo = scanner.next();
				
				if (tipoRubrica.equalsIgnoreCase("csv")) {
					
					managerCsv.modificaTelefono(tempContatto, telefonoNuovo);
					
				} else if (tipoRubrica.equalsIgnoreCase("xml")){
					
					managerXml.modificaTelefono(tempContatto, telefonoNuovo);
				
				} else {
					
					managerJPA.modificaTelefonoContattoDb(tempContatto, telefonoNuovo);
					
				}
				
			}
			
			if(sceltaModifica.contentEquals("3")) {
				
				String emailNuova = null;
				
				System.out.println("inserisci ora la nuova email");
				emailNuova = scanner.next();
				
				if (tipoRubrica.equalsIgnoreCase("csv")) {
					
					managerCsv.modificaEmail(tempContatto, emailNuova);
					
				} else if (tipoRubrica.equalsIgnoreCase("xml")){
					
					managerXml.modificaEmail(tempContatto, emailNuova);
				
				} else {
					
					managerJPA.modificaEmailContattoDb(tempContatto, emailNuova);
					
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
			
		} else if (tipoRubrica.equalsIgnoreCase("xml")){
			
			managerXml.elimina(nomeDaEliminare, cognomeDaEliminare);
		
		} else managerJPA.eliminaContattoDb(nomeDaEliminare, cognomeDaEliminare);
		
		
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
		
		managerJPA.visualizzaContattiDb();
		
	}
	
	if(scelta.contentEquals("7")) {
		
		managerCsv.daCsvInDB();
		
	}
	
	if(scelta.contentEquals("8")) {
		
		managerCsv.daDBinCsv();
		
	}
	
	if(scelta.contentEquals("9")) {
		
		managerXml.daXMLinDB();
		
	}
	
	if(scelta.contentEquals("10")) {
		
		managerXml.daDBinXml();
		
	}

	}while(!scelta.equalsIgnoreCase("exit"));
	
		System.out.println("");
		System.out.println("Hai chiuso correttamente la rubrica, arrivederci");
	
		scanner.close();
		
		// managerHDB.chiudiConnessioneDb();

	}
	
	

}