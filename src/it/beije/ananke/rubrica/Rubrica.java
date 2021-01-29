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
		
	List<Contatto> contatti = new ArrayList<Contatto>();

	public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException, TransformerException {
		Rubrica rubrica = new Rubrica();
		Scanner sc = new Scanner(System.in);

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
						+ "8 = Modifica contatto\n");
				try {
					opzioneScelta = Integer.parseInt(sc.next());
					controllo = true;
				}catch(NumberFormatException e) {
					System.out.println("Opzione non disponibile");
				}
			}
			if(opzioneScelta != 0 && opzioneScelta != 6 && opzioneScelta !=7 && opzioneScelta != 8) {
				System.out.println("Inserire il path del file");
				filePath = sc.next();
			}
			
			switch(opzioneScelta) {
				case 0: System.exit(0);
				case 1: {
					rubrica.aggiungiContatto(filePath);
					break;
				}
				case 2: {
					rubrica.importaCsv(filePath);
					break;
				}
				case 3: {
					rubrica.esportaCsv(filePath);
					break;
				}
				case 4: {
					rubrica.importaXml(filePath);
					break;
				}
				case 5: {
					rubrica.esportaXml(filePath);
					break;
				}
				case 6: {
					rubrica.ricercaContatto();
					break;
				}
				case 7: {
					rubrica.eliminaContatto();
					break;
				}
				case 8: {
					rubrica.modificaContatto();
					break;
				}
			}
			controllo = false;
			System.out.println("Continuare? (s/n)");
			if(sc.next().equalsIgnoreCase("s")) {			
			}else {
				stop = true;
			}
		}
		sc.close();
	}
	
	public void stampaContatti() {
		for(Contatto contatto : contatti) {
			System.out.println(contatto.toString());
		}
	}
	
	public void aggiungiContatto(String filePath) throws IOException {
		
		Contatto contatto = new Contatto();
		boolean stop = false;	
		Scanner sc = new Scanner(System.in);
		
		while (!stop) {
		
			System.out.println("Inserire il nome");
			contatto.setNome(sc.next()); 
			
			System.out.println("Inserire il cognome");
			contatto.setCognome(sc.next());
			
			System.out.println("Inserire il email");
			contatto.setEmail(sc.next()); 
			
			System.out.println("Inserire il telefono");
			contatto.setTelefono(sc.next());
			
			//fw.append(contatto.toString());
			Files.write(Paths.get(filePath), contatto.toString().getBytes(),
					StandardOpenOption.APPEND);
			
			System.out.println("Continuare? (s/n)");
			if(sc.next().equalsIgnoreCase("s")) {
				Files.write(Paths.get(filePath), "\n".getBytes(),
						StandardOpenOption.APPEND);			
			}else {
				stop = true;
			}
			
		}
		//fw.close();
		sc.close();
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
		
		stampaContatti();
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
	
	public List<Contatto> ricercaContatto() {
		
		Scanner sc = new Scanner(System.in);
		List<Contatto> contattiTrovati = new ArrayList<Contatto>();

		String risposta = "";
		String nome = "";
		String cognome = "";
		String email = "";
		String telefono = "";
		int opzioneScelta = 0;
		boolean controllo = false;
		boolean stop = false;
		Contatto contattoCercato = new Contatto();
		
		System.out.println("Ricerca contatto");
		
		while(!stop) {
			while(!controllo) {
				System.out.println("1 = Inserisci il nome\n"
						+ "2 = Inserisci il cognome\n"
						+ "3 = Inserisci l' email\n"
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
				case 1:{
					System.out.println("Inserire il nome");
					contattoCercato.setNome(sc.next());
					break;
				}
				case 2:{
					System.out.println("Inserire il cognome");
					contattoCercato.setCognome(sc.next());
					break;
				}
				case 3:{
					System.out.println("Inserire il l'email");
					contattoCercato.setEmail(sc.next());
					break;
				}
				case 4:{
					System.out.println("Inserire il telefono");
					contattoCercato.setTelefono(sc.next());
					break;
				}
				case 5:{
					stop = true;
					break;
				}
			}		
		}
		
		for(Contatto contatto : contatti) {
			if(contatto.equals(contattoCercato)) {
				contattiTrovati.add(contatto);
			}
		}
		
		if(contattiTrovati.isEmpty()) {
			System.out.println("Nessun contatto trovato");
		}else {
			stampaContatti();
		}
		return contattiTrovati;
	}
	
	public void eliminaContatto() {
		List<Contatto> contattiTrovati = new ArrayList<Contatto>();
		Scanner sc = new Scanner(System.in);
		contattiTrovati = ricercaContatto();
		System.out.println("Eliminare i contatti? (s/n)");
		if(sc.next().equalsIgnoreCase("s")) {
			contatti.removeAll(contattiTrovati);
		}
		stampaContatti();
	}
	
	public void modificaContatto() {
		String nome = "";
		String cognome = "";
		String email = "";
		String telefono = "";
		boolean controllo = false;
		int opzioneScelta = 0;
		List<Contatto> contattiTrovati = new ArrayList<Contatto>();
		Scanner sc = new Scanner(System.in);
		
		contattiTrovati = ricercaContatto();
		
		if(!contattiTrovati.isEmpty()) {
			System.out.println("Modificare i contatti? (s/n)");
		
		if(sc.next().equalsIgnoreCase("s")) {
			while(!controllo) {
				System.out.println("1 = Modifica nome\n"
						+ "2 = Modifica cognome\n"
						+ "3 = Modifica email\n"
						+ "4 = Modifica telefono\n");
				
				try {
					opzioneScelta = Integer.parseInt(sc.next());
					controllo = true;
				}catch(NumberFormatException e) {
					System.out.println("Opzione non disponibile");
				}
			}
			
			switch(opzioneScelta) {
				case 1: {
					System.out.println("Inserire il nome");
					nome = sc.next();
					for(Contatto contatto : contattiTrovati) {
						contatto.setNome(nome);
					}
					break;
				}
				case 2:{
					System.out.println("Inserire il cognome");
					cognome = sc.next();
					for(Contatto contatto : contattiTrovati) {
						contatto.setCognome(cognome);
					}
					break;
				}
				case 3:{
					System.out.println("Inserire l'email");
					email = sc.next();
					for(Contatto contatto : contattiTrovati) {
						contatto.setEmail(email);
					}
					break;
				}
				case 4:{
					System.out.println("Inserire il telefono");
					telefono = sc.next();
					for(Contatto contatto : contattiTrovati) {
						contatto.setTelefono(telefono);
					}
					break;
				}
			}
		}
		stampaContatti();
	}
	
	}
}
