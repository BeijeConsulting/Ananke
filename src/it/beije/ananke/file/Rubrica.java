package it.beije.ananke.file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import it.beije.ananke.file.ContattoMio;

public class Rubrica {
	
	public static void main(String[] args) throws Exception {
		String path = "C:\\Users\\Padawan04\\Desktop\\Snippets\\rub.csv";
		String format = "csv";
		Rubrica r = new Rubrica();
		menu();
		Scanner s = new Scanner(System.in);
		String st = s.next();

		while (!st.equalsIgnoreCase("6")) {
			r.choice(st, path, s, format);
			menu();
			st = s.next();
		}
		
		
		System.out.println("Arrivederci!");
		s.close();
	}
	
	public static void menu() {
		System.out.println("Benvenuto nella tua rubrica! Cosa vuoi fare?");
		System.out.println("-Digita 0 per vedere lo stato della tua rubrica");
		System.out.println("-Digita 1 per aggiungere una nuova voce");
		System.out.println("-Digita 2 per creare una nuova rubrica");
		System.out.println("-Digita 3 per modificare una voce");
		System.out.println("-Digita 4 per cancellare una voce");
		System.out.println("-Digita 5 per cercare un contatto");
		System.out.println("-Digita 6 per uscire");
		System.out.println("-Digita 7 per ristampare il menù");
		
	}
	
	
	
	public void choice(String i, String path, Scanner s, String format) throws Exception{
		ArrayList<ContattoMio> contatti = read(path, s, format);
		switch(i) {
			case "0": stampa(path, contatti); break;
			case "1": aggiungiVoce(path, s, contatti); break;
			case "2": inizializzaNuova(s, path); break;
			case "3": modificaContatto(path, s, contatti); break;
			case "4": break;
			case "5": ricerca(path, s, contatti); break;
			case "6": break;
			case "7": break;
			default: System.out.println("L'opzione selezionata non è valida.");
		}
	}
	
	
	public void stampa(String path, ArrayList<ContattoMio> contatti) throws Exception {
		//ArrayList<Contatto> contatti = letturaCsv(path);
/*		StringBuilder sb = new StringBuilder();
		sb.append(contatti.get(0).getName()).append(" ")
		  .append(contatti.get(0).getSurname()).append(" ")
		  .append(contatti.get(0).getEmail()).append(" ")
		  .append(contatti.get(0).getTelephone()).append(" ");
		System.out.println(sb.toString());
	*/	

		if(contatti.size() == 0) {
			System.out.println("Reindirizzamento al menù.");
		}else {
			for(int i = 0; i < contatti.size(); i++) {
				System.out.println(i + " " + contatti.get(i).toString());
			}
		}
	}
	
	
	public ArrayList<ContattoMio> read(String path, Scanner s, String st) throws Exception{
		//System.out.println("Formato file da leggere: ");
		//String st = s.next();
		
		if(st.equalsIgnoreCase("xml")) {
			return letturaXml(path);
		}else if(st.equalsIgnoreCase("csv")) {
			return letturaCsv(path);
		}else {
			System.out.println("Il formato inserito non è valido.\nSarai reidirizzato al menù.");
			return null;
		}
	}
	
	public ArrayList<ContattoMio> letturaCsv(String path) throws FileNotFoundException, IOException{
		System.out.println("Sto leggendo un csv");
		File file = new File(path);
		FileReader fr = new FileReader(file);	
		BufferedReader br = new BufferedReader(fr);
		
		ArrayList<String> rows = new ArrayList<String>();
		while (br.ready()) {
			rows.add(br.readLine());
		}
		
		ArrayList<ContattoMio> lista = new ArrayList<ContattoMio>();
		for(int i = 0; i < rows.size(); i++) {
			
			String[] rs = rows.get(i).split(";");
			
			ContattoMio con = new ContattoMio();
			con.setName(rs[0]);
			con.setSurname(rs[1]);
			con.setEmail(rs[2]);
			con.setTelephone(rs[3]);
			lista.add(con);
		}
		lista.remove(0);
		
		br.close();
		fr.close();
		return lista;
	}
	
	public ArrayList<ContattoMio> letturaXml(String path) throws ParserConfigurationException, SAXException, IOException {
		System.out.println("Sto leggendo un xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        Document document = builder.parse(path);
        Element docElement = document.getDocumentElement();       
        NodeList elementiContatto = docElement.getElementsByTagName("utente");
        
        ArrayList<ContattoMio> contatti = new ArrayList<ContattoMio>();
        for (int i = 0; i < elementiContatto.getLength(); i++) {
        	
        	ContattoMio contatto = new ContattoMio();
        	Element c = (Element)elementiContatto.item(i);
        	NodeList valori = c.getChildNodes();
        	System.out.println(valori.getLength());
        	
        	for (int j = 0; j < valori.getLength(); j++) {
            	Node n = valori.item(j);
            	if (n instanceof Element) {
            		Element valore = (Element) n;
            		System.out.println(valore.getTagName() + " : " + valore.getTextContent());
            		
            		switch (valore.getTagName()) {
						case "name":
							contatto.setName(valore.getTextContent());
							break;
						case "surname":
							contatto.setSurname(valore.getTextContent());
							break;
						case "telephone":
							contatto.setTelephone(valore.getTextContent());
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
        
        return contatti;
 	}
	
	
	public void inizializzaNuova(Scanner s, String path) throws Exception {
		ArrayList<ContattoMio> contatti = new ArrayList<>();
		ContattoMio cont = defContatto(s);
		if(cont == null) {
			System.out.println("Inizializzazione fallita.");
		}else {
			contatti.add(cont);
			write(path, contatti, s);
		}
	}
	
	
	public void aggiungiVoce(String path, Scanner s, ArrayList<ContattoMio> contatti) throws Exception {
		if(contatti.size() == 0) {
			System.out.println("...");
		}else {
			ContattoMio cont = defContatto(s);
			if(cont != null) {
				contatti.add(cont);
				write(path, contatti, s);
			}
		}
	}
	
	public ContattoMio defContatto(Scanner s) throws IOException {
		ContattoMio cont = new ContattoMio();
		StringBuilder sb = new StringBuilder("");
		
		System.out.println("Digita il nome da inserire in rubrica: \n(Digitare 0 per lasciare il campo vuoto)");
		String st = s.next();
		sb.append(st);
		if(cont.getName().equals("0")) {
			cont.setName("");
		}else {
			cont.setName(st);
		}
		
		System.out.println("Digita il cognome da inserire in rubrica: \n(Digitare 0 per lasciare il campo vuoto)");
		st = s.next();	
		sb.append(st);
		if(cont.getSurname().equals("0")) {
			cont.setSurname("");
		}else {
			cont.setSurname(st);
		}
		
		System.out.println("Digita l'email da inserire in rubrica: \n(Digitare 0 per lasciare il campo vuoto)");
		st = s.next();	
		sb.append(st);
		if(cont.getEmail().equals("0")) {
			cont.setEmail("");
		}else {
			cont.setEmail(st);
		}
		
		System.out.println("Digita il numero di telefono da inserire in rubrica: \n(Digitare 0 per lasciare il campo vuoto)");
		st = s.next();
		sb.append(st);
		if(cont.getTelephone().equals("0")) {
			cont.setTelephone("");
		}else {
			cont.setTelephone(st);
		}
		
		
		if(sb.toString().equals("0000")) {
			System.out.println("Il contatto inserito è vuoto, non sarà aggiunto in rubrica.");
			cont = null;
		}
		
		return cont;
	}
	
	public void write(String path, ArrayList<ContattoMio> contatti, Scanner s) throws Exception {
		System.out.println("Formato output desiderato: ");
		String st = s.next();
		if(st.equalsIgnoreCase("csv")) {
			scriviCsv(path, contatti);
		}else if(st.equalsIgnoreCase("xml")) {
			scriviXml(path, contatti);
		}else {
			System.out.println("Formato non valido. Le tue modifiche non saranno salvate e sarai reindirizzato al menù.");
		}
	}
	
	public void scriviCsv(String path, ArrayList<ContattoMio> contatti) throws IOException, FileNotFoundException {
		File newFile = new File(path);
		FileWriter fw = new FileWriter(newFile);
		
		StringBuilder prima = new StringBuilder("NOME;COGNOME;EMAIL;TELEFONO\n");
		fw.write(prima.toString());
		
		for (ContattoMio con : contatti) {
			StringBuilder sb = new StringBuilder();
			sb.append(con.getName()).append(";")
			  .append(con.getSurname()).append(";")
			  .append(con.getEmail()).append(";")
			  .append(con.getTelephone()).append("\n");
			fw.write(sb.toString());
		}
		
		fw.flush();
		fw.close();		
	}
	
	public void scriviXml(String path, ArrayList<ContattoMio> contatti) throws Exception {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
		
        Document document = builder.newDocument();
        Element rubrica = document.createElement("rubrica");
        document.appendChild(rubrica);
        
        Element utente = null;
        Element name = null;
        Element surname = null;
        Element email = null;
        Element telephone = null;
        
        for(int i = 0; i < contatti.size(); i++) {
        	
        	//creazione elementi
        	utente = document.createElement("utente");
        	name = document.createElement("name");
        	name.setTextContent(contatti.get(i).getName());
        	surname = document.createElement("surname");
        	surname.setTextContent(contatti.get(i).getSurname());
        	email = document.createElement("email");
        	email.setTextContent(contatti.get(i).getEmail());
        	telephone = document.createElement("telephone");
        	telephone.setTextContent(contatti.get(i).getTelephone());
        	
        	//append
        	utente.appendChild(name);
        	utente.appendChild(surname);
        	utente.appendChild(email);
        	utente.appendChild(telephone);
        	rubrica.appendChild(utente);
        	
        	
        	TransformerFactory transformerFactory = TransformerFactory.newInstance();
    		Transformer transformer = transformerFactory.newTransformer();
    		DOMSource source = new DOMSource(document);
    		
    		
    		StreamResult result = new StreamResult(new File(path));

    		// Output to console for testing
    		StreamResult syso = new StreamResult(System.out);

    		transformer.transform(source, result);
    		transformer.transform(source, syso);

    		System.out.println("File saved!");
        	
        	
        }
	}
	
	
	public void modificaContatto(String path, Scanner s, ArrayList<ContattoMio> contatti) throws Exception {
		if(contatti == null) {
			System.out.println("La rubrica è vuota.");
		}else {
			stampa(path, contatti);
			System.out.println("Scegliere quale voce modificare: ");
			String st = s.next();
			
			try {
				int indice = Integer.parseInt(st);
				
				while(indice >= contatti.size()) {
					System.out.println("Voce non valida, provare di nuovo.");
					st = s.next();
					indice = Integer.parseInt(st);
				}
				
				ContattoMio cont = defContatto(s);
				contatti.set(indice, cont);
				write(path, contatti, s);
				
			}catch(NumberFormatException e) {
				System.out.println("Formato non valido. La voce non è stata modificata.");
			}
		}
	}
	
	
	public ArrayList<ContattoMio> scansione(String path, Scanner s, ArrayList<ContattoMio> contatti) throws Exception{
		ArrayList<ContattoMio> appoggio = new ArrayList<>();
		
		if(contatti.size() == 0) {
			
		}
		
		System.out.println("Scegliere in quale campo cercare:\n"
				+ "0: NOME\n1: COGNOME\n2: EMAIL\n3: TELEFONO\n"
				+ "(Attenzione il numero di telefono deve contenere eventuali prefissi)");
		String str = s.next();
		
		try {
			int indice = Integer.parseInt(str);
			
			while(indice >= 4) {
				System.out.println("Voce non valida, provare di nuovo.");
				str = s.next();
				indice = Integer.parseInt(str);
			}
			
			System.out.println("Inserire il valore da cercare: ");
			str = s.next();
			switch(indice) {
				case 0: for(ContattoMio c : contatti) {
							if(c.getName().equalsIgnoreCase(str))
								appoggio.add(c);
						}
						break;
				case 1: for(ContattoMio c : contatti) {
							if(c.getSurname().equalsIgnoreCase(str))
								appoggio.add(c);
						}
						break;
				case 2: for(ContattoMio c : contatti) {
							if(c.getEmail().equalsIgnoreCase(str))
								appoggio.add(c);
						}
						break;
				case 3:	for(ContattoMio c : contatti) {
							if(c.getTelephone().equalsIgnoreCase(str))
								appoggio.add(c);
						}
						break;
			}
			
			
		}catch(NumberFormatException e) {
			System.out.println("Formato non valido. La ricerca verrà interrotta.");
		}
		
		
		return appoggio;
	}
	
	public void ricerca(String path, Scanner s, ArrayList<ContattoMio> contatti) throws Exception {
		ArrayList<ContattoMio> stampa = scansione(path, s, contatti);
		for(ContattoMio c : stampa) {
			System.out.println(stampa.indexOf(c) + c.toString());
		}
	}
	
	public void rimuovi(String path, Scanner s, ArrayList<ContattoMio> contatti) throws Exception {
		stampa(path, contatti);
		if(contatti.size() == 0) {
			System.out.println("La rubrica è vuota.");
		}else {
			stampa(path, contatti);
			System.out.println("Scegliere quale voce eliminare: ");
			String st = s.next();
			
			try {
				int indice = Integer.parseInt(st);
				
				while(indice >= contatti.size()) {
					System.out.println("Voce non valida, provare di nuovo.");
					st = s.next();
					indice = Integer.parseInt(st);
				}
				
				
				write(path, contatti, s);
				
			}catch(NumberFormatException e) {
				System.out.println("Formato non valido. La voce non è stata modificata.");
			}
		}
	}
	
	
	
}
