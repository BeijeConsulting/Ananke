package it.beije.ananke.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class Rubrica {
		
	final Scanner sc = new Scanner(System.in);
	List<Contatto> contatti = new ArrayList<Contatto>();
	DBManager dbManager = new DBManager("root","Beije09","jdbc:mysql://localhost:3306/ananke?serverTimezone=CET");

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		Rubrica rubrica = new Rubrica();
		rubrica.menuPrincipale();		
		
	}
	
	public void menuPrincipale() throws IOException, ParserConfigurationException, SAXException, TransformerException {

		boolean controllo = false;
		int opzioneScelta = 0;
		boolean stop = false;
		String filePath = "";
		
		while(!stop) {
			while(!controllo) {
				System.out.println("Seleziona l'operazione da svolgere \n"
						+ "0 = Esci\n"
						+ "1 = Aggiungi un contatto\n"
						+ "2 = Importa la lista dei contatti da csv\n"
						+ "3 = Esporta la lista dei contatti in un file csv\n"
						+ "4 = Importa la lista dei contatti da un file xml\n"
						+ "5 = Esporta la lista dei contatti in un file xml\n"
						+ "6 = Ricerca contatto\n"
						+ "7 = Elimina contatto\n"
						+ "8 = Modifica contatto\n"
						+ "9 = Operazioni DB");
				try {
					opzioneScelta = Integer.parseInt(sc.next());
					controllo = true;
				}catch(NumberFormatException e) {
					System.out.println("Opzione non disponibile");
				}
			}
			if(opzioneScelta >1 && opzioneScelta < 6) {
				System.out.println("Inserire il path del file");
				filePath = sc.next();
			}
			
			switch(opzioneScelta) {
				case 0: System.exit(0);
				case 1: {
					aggiungiContatto(filePath);
					break;
				}
				case 2: {
					importaCsv(filePath);
					break;
				}
				case 3: {
					esportaCsv(filePath);
					break;
				}
				case 4: {
					importaXml(filePath);
					break;
				}
				case 5: {
					esportaXml(filePath);
					break;
				}
				case 6: {
					ricercaContatto();
					break;
				}
				case 7: {
					eliminaContatto();
					break;
				}
				case 8: {
					modificaContatto();
					break;
				}
				case 9: {
					menuDB();
					break;
				}
			}
			controllo = false;
			System.out.println("Continuare? (s/n)");
			if(sc.next().equalsIgnoreCase("n")) {			
				stop = true;
			}
		}
		sc.close();
	}
	
	public void menuDB() throws IOException, ParserConfigurationException, SAXException, TransformerException {
		boolean controllo = false;
		int opzioneScelta = 0;
		
		while(!controllo) {
			System.out.println("Seleziona l'operazione da svolgere\n"
					+ "0 = Torna al menu principale\n"
					+ "1 = Aggiungi contatto\n"
					+ "2 = Ricerca contatto\n"
					+ "3 = Elimina contatto\n"
					+ "4 = Modifica contatto");
		
		
		try {
			opzioneScelta = Integer.parseInt(sc.next());
			controllo = true;
		}catch(NumberFormatException e) {
			System.out.println("Opzione non disponibile");
		}
		
		
		switch(opzioneScelta) {
			case 0:{
				menuPrincipale();
				break;
			}
			case 1: {
				dbManager.aggiungiContattoDB(letturaContatto());
				break;
			}
			case 2: {
				dbManager.ricercaContattoDB(letturaContatto());
				break;
			}
			case 3: {
				dbManager.eliminaContattoDB(letturaEmail());
				break;
			}
			case 4: {
				break;
			}
			
		}
		}
	}
	
	public void stampaContatti(List<Contatto> contatti) {
		for(Contatto contatto : contatti) {
			System.out.println(contatto.toString());
		}
	}
	
	public Contatto letturaContatto() {
		Contatto contatto = new Contatto();
		boolean stop = false;	
		boolean controllo = false;
		int opzioneScelta = 0;
		
		do {
			while(!controllo) {
				controllo = false;
				System.out.println("1 = Inserisci il nome\n"
						+ "2 = Inserisci il cognome\n"
						+ "3 = Inserisci l'email\n"
						+ "4 = Inserisci il telefono\n"
						+ "5 = Procedi");		
				
				try {
					opzioneScelta = Integer.parseInt(sc.next());
					controllo = true;
				}catch(NumberFormatException e) {
					System.out.println("Opzione non disponibile");
				}
			}
			controllo = false;
			
			switch(opzioneScelta) {
				case 1: {
					System.out.println("Inserire il nome");
					contatto.setNome(sc.next()); 
					break;
				}
				case 2:{
					System.out.println("Inserire il cognome");
					contatto.setCognome(sc.next());
					break;
				}
				case 3:{
					System.out.println("Inserire l'email");
					contatto.setEmail(sc.next()); 
					break;
				}
				case 4:{
					System.out.println("Inserire il telefono");
					contatto.setTelefono(sc.next());
					break;
				}
				case 5:{
					stop = true;
					break;
				}
			}
			
		
			if(contatto.getNome().equals("") && contatto.getCognome().equals("") &&
					contatto.getEmail().equals("") && contatto.getTelefono().equals("")) {
					System.out.println("E' necessario riempire almeno un campo");
					stop = false;
			}
		}while(!stop);
		return contatto;
	}
	
	public void aggiungiContatto(String filePath) throws IOException {
		
		Contatto contatto = new Contatto();
					
		System.out.println("Aggiungi contatto");
		contatto = letturaContatto();
		Files.write(Paths.get(filePath), contatto.toString().getBytes(),
								StandardOpenOption.APPEND);
					
						//dbManager.aggiungiContattoDB(contatto);				
			
		System.out.println("Continuare? (s/n)");
		if(sc.next().equalsIgnoreCase("s")) {
			Files.write(Paths.get(filePath), "\n".getBytes(),StandardOpenOption.APPEND);		
		}
	}
	
	public void importaCsv(String filePath) throws IOException {
		
		File file = new File(filePath);
		
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String[] dati = new String[4];
		
		while(br.ready()) {
			dati = br.readLine().split(";");
			Contatto contatto = new Contatto();
			contatto.setNome(dati[0]);
			contatto.setCognome(dati[1]);
			contatto.setEmail(dati[2]);
			contatto.setTelefono(dati[3]);
			contatti.add(contatto);
		}
		
		stampaContatti(contatti);
	}
	
	public void esportaCsv(String filePath) throws IOException {
		
		File file = new File(filePath);
		FileWriter fw = new FileWriter(file);
		
		for(Contatto contatto : contatti) {
			fw.write(contatto.toString() + "\n");
		}
		fw.close();
	}
	
	public void importaXml(String filePath) throws ParserConfigurationException, SAXException, IOException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        Document document = builder.parse(filePath);
        
        Element docElement = document.getDocumentElement();       

        NodeList elementiContatto = docElement.getElementsByTagName("contatto");
        
        for (int i = 0; i < elementiContatto.getLength(); i++) {
        	Contatto contatto = new Contatto();
        	Element c = (Element)elementiContatto.item(i);
        
    	NodeList valori = c.getChildNodes();
        //System.out.println(valori.getLength());
        for (int j = 0; j < valori.getLength(); j++) {
        	Node n = valori.item(j);
        	if (n instanceof Element) {
        		Element valore = (Element) n;
        		System.out.println(valore.getTagName() + " : " + valore.getTextContent());
        		switch (valore.getTagName()) {
				case "nome":
					contatto.setNome(valore.getTextContent());
					break;
				case "cognome":
					contatto.setCognome(valore.getTextContent());
					break;
				case "telefono":
					contatto.setTelefono(valore.getTextContent());
					break;
				case "email":
					contatto.setEmail(valore.getTextContent());
					break;

				default:
					System.out.println("elemento in contatto non riconosciuto");
					break;
				}
        	}
        }
        contatti.add(contatto);
        }
	}
	
	public void esportaXml(String filePath) throws ParserConfigurationException, TransformerException {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
		
        Document document = builder.newDocument();
        Element elContatti = document.createElement("contatti");
        document.appendChild(elContatti);
        
        //...
        Element elContatto = null;
        Element nome = null;
        Element cognome = null;
        Element email = null;
        Element telefono = null;

        for (Contatto contatto : contatti) {
        	elContatto = document.createElement("contatto");
        	
        	nome = document.createElement("nome");
        	nome.setTextContent(contatto.getNome());
        	elContatto.appendChild(nome);
        	
        	cognome = document.createElement("cognome");
        	cognome.setTextContent(contatto.getCognome());
        	elContatto.appendChild(cognome);
        	
        	email = document.createElement("email");
        	email.setTextContent(contatto.getEmail());
        	elContatto.appendChild(email);
        	
        	telefono = document.createElement("telefono");
        	telefono.setTextContent(contatto.getTelefono());
        	elContatto.appendChild(telefono);	
        	
        	elContatti.appendChild(elContatto);
        }
        
        
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		
		StreamResult result = new StreamResult(new File(filePath));

		try {
			transformer.transform(source, result);
		} catch (TransformerException e) {
			e.printStackTrace();
		}
        
	}
	
	public String letturaEmail() {
		String email = "";
		System.out.println("Ricerca contatto");
		System.out.println("Inserisci l'email");
		email = sc.next();
		return email;
	}
	
	public List<Contatto> ricercaContatto() {
		List<Contatto> contattiTrovati = new ArrayList<Contatto>();
		Contatto contattoCercato = new Contatto();
		
		System.out.println("Ricerca contatto");
		contattoCercato = letturaContatto();
		
		for(Contatto contatto : contatti) {
			if(contatto.equals(contattoCercato)) {
				contattiTrovati.add(contatto);
			}
		}
		
		if(contattiTrovati.isEmpty()) {
			System.out.println("Nessun contatto trovato");
		}else {
			stampaContatti(contattiTrovati);
		}
		return contattiTrovati;
	}
	
	public void eliminaContatto() {
		List<Contatto> contattiTrovati = new ArrayList<Contatto>();
		contattiTrovati = ricercaContatto();
		System.out.println("Eliminare i contatti? (s/n)");
		if(sc.next().equalsIgnoreCase("s")) {
			contatti.removeAll(contattiTrovati);
		}
		stampaContatti(contatti);
	}
	
	
	public void modificaContatto() {
		String nome = null;
		String cognome = null;
		String email = null;
		String telefono = null;
		List<Contatto> contattiTrovati = new ArrayList<Contatto>();
		Contatto contattoModificato = null;
		
		contattiTrovati = ricercaContatto();
		
		if(!contattiTrovati.isEmpty()) {
			System.out.println("Modificare i contatti? (s/n)");
		
		if(sc.next().equalsIgnoreCase("s")) {
			contattoModificato = letturaContatto();
			nome = contattoModificato.getNome();
			cognome = contattoModificato.getCognome();
			email = contattoModificato.getEmail();
			telefono = contattoModificato.getTelefono();
			
			if(!nome.equals("")) {
				for(Contatto contatto : contattiTrovati) {
					contatto.setNome(nome);
				}
			}
			if(cognome.equals("")) {
				for(Contatto contatto : contattiTrovati) {
					contatto.setCognome(cognome);
				}
			}
			if(email.equals("")) {
				for(Contatto contatto : contattiTrovati) {
					contatto.setEmail(email);
				}
			}
			if(telefono.equals("")) {
				for(Contatto contatto : contattiTrovati) {
					contatto.setTelefono(telefono);
				}
			}	
		}
		stampaContatti(contatti);
	}
	}
}
