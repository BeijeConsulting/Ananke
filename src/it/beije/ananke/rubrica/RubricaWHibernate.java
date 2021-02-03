package it.beije.ananke.rubrica;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class RubricaWHibernate {

	private static final String PATH = "C:\\Users\\Padawan02\\Desktop\\esercizietti\\rubrica";
	
	public static void main(String[] args) {
		
		Scanner inputKeyword = new Scanner(System.in);
		String command;
		
		//prendo il primo comando che mi porta nel ciclo
		showCommandList();
		command = inputKeyword.nextLine().trim();
		
		AZIONI: do{
		
		    switch (command){
				
				case "i":
				
					insertContacts();
				
				    break;
				
				case "u":
				
					modifyContacts();
				
				    break;
				
				case "d":
				
					deleteContacts();
				
				    break;
				
				case "s":
				
				    List<String> fields = fieldsForSearch();
				    searchContacts(fields);
				
				    break;
				    
				case "p":
					
				    printDB();
				
				    break;
				
				case "q":
				
					break AZIONI;
				
				default:
				
				    break;
				
			}
				
			//prendo un nuovo comando
		    showCommandList();
		    command = inputKeyword.nextLine().trim();
		
		}while(!command.equals("q"));
		
		System.out.println("Vuoi esportare qualche contatto da databese su file?" +
		"\n\tyes?" +
		"\n\tno?");
		command = inputKeyword.nextLine().trim();
		
		if(command.equals("yes"))
			try {
				writeOnFile();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			} catch (SQLException e) {
				e.printStackTrace();
			}

	}
	
	private static void insertContacts() {
		
		 Scanner inputKeyword = new Scanner(System.in);
	     String command = null;
	     
	     System.out.println("Come vuoi inserire i contatti?" +
                 "\n\t[1] Importa contatti da file" +
                 "\n\t[2] Inserisci contatti da tastiera");
	     
	     command = inputKeyword.nextLine().trim();
	     
	     switch (command) {

	         case "1":
	
	             insertFromFile();
	
	             break;
	
	         case "2":
	
	             insertFromKeyWord();
	
	             break;
	
	         default:

	     }
	     
	}

	private static void insertFromKeyWord() {
		
		Scanner inputKeyWord = new Scanner(System.in);
        List<Contact> rubric = new ArrayList<>();
        String command = null;

        do {

            Contact contact = new Contact();

            System.out.println("\nInserisci i dati del nuovo contatto.");
            System.out.println("\nNome: ");
            contact.setName(inputKeyWord.nextLine().trim());
            System.out.println("\nCognome: ");
            contact.setSurname(inputKeyWord.nextLine().trim());
            System.out.println("\nTelefono: ");
            contact.setTelephone(inputKeyWord.nextLine().trim());
            System.out.println("\nE-mail: ");
            contact.setEmail(inputKeyWord.nextLine().trim());

            if(contact.getName().length() == 0  &&
                    contact.getSurname().length() == 0  &&
                    contact.getTelephone().length() == 0  &&
                    contact.getEmail().length() == 0){

                System.out.println("Attenzione! Almeno un campo del contatto deve essere non vuoto");

            }
            else
                rubric.add(contact);

            System.out.println("Vuoi continuare a inserire contatti?" +
                    "\n\tyes?" +
                    "\n\tno?");
            command = inputKeyWord.nextLine().trim();

        }while(!command.equals("no"));

        System.out.println("Questi sono i contatti che hai aggiunto:\n");
        printContacts(rubric);
        System.out.println("\nVuoi aggiungere questi contatti al database?" +
                "\n\tyes?" +
                "\n\tno?");

        HDataBaseContact.openSession();
        HDataBaseContact.insert(rubric);
        HDataBaseContact.closeSession();
        
	}
	
	private static void insertFromFile() {

		Scanner inputTastiera = new Scanner(System.in);
        String path;

        File directory = new File(PATH);
        File[] files = directory.listFiles();
        int i=0;

        if(files == null) {
            System.out.println("C'� stato un problema nel caricare la directory");
        }
        else{
        	if(files.length > 0) {
        		//esiste gi� un file dal quale leggere

        		System.out.println("Quale file vuoi leggere?\n");

        		for (File file : files) {
        			System.out.println("[" + i + "]\t" + file.getName());
        			i++;
        		}

        		//faccio scegliere il file da leggere
        		int numeroFile = Integer.parseInt(inputTastiera.nextLine().trim());
        		String nomeFile = files[numeroFile].getName();

        		//capisco se � un file xml o csv
        		String nome = nomeFile.split("\\.")[0];
        		String estensione = (nomeFile.split("\\."))[1];

        		path = PATH + "\\" + nomeFile;

        		if (estensione.equals("xml")) {
        			try {
        				readContactFromXml(path);
        			} catch (Exception e) {
        				System.out.println("C'è stato un problema nella lettura del file.xml");
        				e.printStackTrace();
        			}
        		} else {
    				if (estensione.equals("csv")) {
    					try {
    						readContactFromCsv(path);
    					} catch (SQLException | ClassNotFoundException throwables) {
    						System.out.println("C'è stato un problema nella lettura del file.csv");
    						throwables.printStackTrace();
    					}
    				}
    				else {
    					System.out.println("Mi dispiace ma non riesco a leggere un file di tale estensione");
    				}
        		}
        	}
        	else{
        		//non esistono file presenti nella directory
        		System.out.println("Non sono ancora presenti file nella directory." +
        					"\nUna volta completate le operazioni potrai crearne uno\n");

        		//non essendoci un file da cui leggere non faccio nulla a rubrica
        		//che è gà stata inizializzata in linea
        	}
	    }

	}

	private static void readContactFromCsv(String path) throws SQLException, ClassNotFoundException {

		List<Contact> rubric = CsvFile.readFromCsv(path);

        System.out.println("Ora aggiungo questi contatti al database");
        printContacts(rubric);

        HDataBaseContact.openSession();

        HDataBaseContact.insert(rubric);

        HDataBaseContact.closeSession();

    }

    private static void readContactFromXml(String path) throws ParserConfigurationException, IOException, SAXException, SQLException, ClassNotFoundException {

    	List<Contact> rubric = XmlFile.readFromFile(path);

        System.out.println("Ora aggiungo questi contatti al database");
        printContacts(rubric);

        HDataBaseContact.openSession();

        HDataBaseContact.insert(rubric);

        HDataBaseContact.closeSession();

    }

    private static void deleteContacts(){

        Scanner inputKeyword = new Scanner(System.in);
        String command = null;
        List<String> values = null;
        List<String> fields = null;

        do {

            fields = fieldsForSearch();
            values = searchContacts(fields);

            System.out.println("Sei sicuro di voler eliminare i precedenti contatti?" +
                    "\n\tyes?" +
                    "\n\tno? ");
            command = inputKeyword.nextLine().trim();

            if(command.equals("no")) {
                //l'utente vuole cambiare i field di ricerca
                fields.clear();
                values.clear();
            }

        } while(!command.equals("yes"));

        //finisco che ho la lista di campi e la lista dei rispettivi valori

        HDataBaseContact.openSession();

        HDataBaseContact.deleteWhere(fields, values);

        HDataBaseContact.closeSession();

    }

    private static void modifyContacts(){

        Scanner inputKeyword = new Scanner(System.in);
        String command = null;
        List<String> fields = null;
        List<String> values = null;
        List<String> modifyFields = new ArrayList<>();
        List<String> modifyValues = new ArrayList<>();

        do {

            fields = fieldsForSearch();
            values = searchContacts(fields);

            System.out.println("Sei sicuro di voler modificare i precedenti contatti? ");
            command = inputKeyword.nextLine().trim();

            if(command.equals("no")) {
                //l'utente vuole cambiare i field di ricerca
                fields.clear();
                values.clear();
            }

        } while(!command.equals("yes"));

        //ho trovato dei contatti tramite certi campi
        //ora devo chiedere all'utente quali campi e come
        //vuole modificare

        do {

            String input = null;

            System.out.println("Quali campi vuoi modificare?" +
                    "\n\t -n: name" +
                    "\n\t -s: surname" +
                    "\n\t -t: telephone" +
                    "\n\t -e: email" +
                    "\nScrivi il comando con - seguito dalla lettera");

            input = inputKeyword.nextLine().trim();

            switch (input){
                case "-n":
                    input = "name";
                    break;
                case "-s":
                    input = "surname";
                    break;
                case "-t":
                    input = "telephone";
                    break;
                case "-e":
                    input = "email";
                    break;
                default:
            }

            //aggiungo solo se il campo non l'ho già aggiunto
            if (!modifyFields.contains(input))
                modifyFields.add(input);
            else {
                System.out.println("\nHai già aggiunto questo campo per la ricerca");
            }

            System.out.println("Inserisci la modifica del campo");
            input = inputKeyword.nextLine().trim();
            modifyValues.add(input);

            if(fields.size() != 4) {
                System.out.println("Vuoi modificare altri campi?");
                command = inputKeyword.nextLine().trim();
            }

        }while(!command.equals("no"));

        //finisco che ho la lista di campi da modificare e la lista dei rispettivi valori

        HDataBaseContact.openSession();

        HDataBaseContact.updateWhere(fields, values, modifyFields, modifyValues);

        HDataBaseContact.closeSession();

    }

	private static List<String> fieldsForSearch(){

        Scanner inputKeyWord = new Scanner(System.in);
        String command = null;
        List<String> fields = new ArrayList<>();

        System.out.println("==================================\n");
        System.out.println("Quanti campi vuoi specificare per trovare i contatti che ti interessano?" +
                "\n\t[1] -un solo campo" +
                "\n\t[2] -due campi solamente" +
                "\n\t[3] -tre campi" +
                "\n\t[4] -tutti i campi" +
                "\nDigita solamente il numero.");

        command = inputKeyWord.nextLine().trim();
        System.out.println("==================================\n");

        int numberOfFields = Integer.parseInt(command);
        if(numberOfFields > 4) {
            System.out.println("Nel db sono presenti solo 4 campi");
            numberOfFields = 4;
        }

        //riempiamo l'arraylist che poi farò tornare che mi serve per la delete e per la modify
        //con i campi che voglio specificare
        for (int i = 0; i < numberOfFields; i++) {

            System.out.println("Campo numero " + (i+1) + ": ");
            String value = null;

            System.out.println("==================================\n");
            System.out.println("Tramite quale campo vuoi cercare?" +
                    "\n\t -n: name" +
                    "\n\t -s: surname" +
                    "\n\t -t: telephone" +
                    "\n\t -e: email" +
                    "\nScrivi il comando con - seguito dalla lettera");
           

            value = inputKeyWord.nextLine().trim();
            System.out.println("==================================\n");
            
            switch (value){
                case "-n":
                    value = "name";
                    break;
                case "-s":
                    value = "surname";
                    break;
                case "-t":
                    value = "telephone";
                    break;
                case "-e":
                    value = "email";
                    break;
                default:
            }

            //aggiungo solo se il campo non l'ho già aggiunto
            if (!fields.contains(value))
                fields.add(value);
            else {
                System.out.println("\nHai già aggiunto questo campo per la ricerca");
                i--;
            }

        }

        return fields;

    }

	private static List<String> searchContacts(List<String> fields){
	
		Scanner inputKeyWord = new Scanner(System.in);
		ArrayList<String> values = new ArrayList<>();
	
		for (int i = 0; i < fields.size(); i++) {
	
			//Chiedo all'utente di inserire il valore specfico del primo campo
			System.out.println(fields.get(i) + ":\t");
			String value = inputKeyWord.nextLine().trim();
			values.add(value);
		}
		
		
		HDataBaseContact.openSession();
		List<Contact> rubric = HDataBaseContact.selectWhere(fields, values);
		HDataBaseContact.closeSession();
	       
		printContacts(rubric);
	
	   
		return values;
	}
	 
	private static void writeOnFile() throws SQLException, ClassNotFoundException {

		List<Contact> rubric = new ArrayList<>();

		System.out.println("Vuoi esportare tutti i contatti su un file?" +
				"\n\ty/n?");
		String command = new Scanner(System.in).nextLine().trim();

		if(command.equals("n")) {
			//cerco i contatti e creo un arrayList che poi passo alle altre funzioni
			List<String> fields = fieldsForSearch();
			List<String> values = searchContacts(fields);

			HDataBaseContact.openSession();

			rubric = HDataBaseContact.selectWhere(fields, values);

			HDataBaseContact.closeSession();
			
		}
		else{
			HDataBaseContact.openSession();

			rubric = HDataBaseContact.select();

			HDataBaseContact.closeSession();
		}

		Scanner inputTastiera = new Scanner(System.in);
		String path;

		File directory = new File(PATH);
		File[] files = directory.listFiles();
		int i=0;

		System.out.println("==================================\n");
		System.out.println("Su quale file vuoi salvare questi contatti?" +
				"\nDigita il numero corrispondente al file che vuoi,\no un numero non presente per creare un nuovo file.");
		

		//listo tutti i file presenti nella directory

		String nomeFile = null;

		if(files != null) {

			for (File file : files) {
				System.out.println("[" + i + "]\t" + file.getName());
				i++;
			}

			//faccio scegliere il file su cui salvare
			int numeroFile = Integer.parseInt(inputTastiera.nextLine());
			System.out.println("==================================\n");
			if(numeroFile < files.length)
				nomeFile = files[numeroFile].getName();
			else {
				System.out.println("Digita il nome del nuovo file con l'estensione csv/xml.");
				nomeFile = inputTastiera.nextLine();
				System.out.println("==================================\n");
			}

		}

		//capisco se è un file xml o csv

		String nome = null;
		String estensione = null;
	        
		if(nomeFile != null) {
			nome = nomeFile.split("\\.")[0];
			estensione = (nomeFile.split("\\."))[1];
		}

		path = PATH + "\\" + nomeFile;

		if(estensione.equals("xml")) {
			try {
				writeOnFileXml(path, rubric);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			if (estensione.equals("csv"))
				writeOnFileCsv(path, rubric);
		}
			
	}

	private static void writeOnFileCsv(String path, List<Contact> rubric) {

		CsvFile.writeOnCsv(path, rubric);

	}
		
	private static void writeOnFileXml(String path, List<Contact> rubric) throws ParserConfigurationException, TransformerException {
	    			
		XmlFile.writeOnFile(path, rubric);

	}
	
	private static void printDB() {
		
		HDataBaseContact.openSession();
		List<Contact> rubric = HDataBaseContact.select();
		HDataBaseContact.closeSession();
	       
		printContacts(rubric);
		
	}
	
	private static void printContacts(List<Contact> rubric) {
		System.out.println("==================================\n");
		for (Contact contact : rubric) {
			System.out.println("[" + contact.getId() + "]\t" + contact.toString());
		}
		System.out.println("==================================\n");
	}

	private static void showCommandList() {
		System.out.println("==================================\n");
        System.out.println("\nEcco cosa puoi fare:\n" +
                "\t- i : inserisci un nuovo contatto;\n" +
                "\t- u : aggiorna un contatto nella rubrica;\n" +
                "\t- d : cancella un contatto esistente;\n" +
                "\t- s : cerca e stampa contatti esistente;\n" + 
                "\t- p : stampa tutto il contenuto del DB;\n" +
                "\t- q : salva ed esci.\n");
        System.out.println("==================================\n");
    }

}
