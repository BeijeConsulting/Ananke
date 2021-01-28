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

import it.beije.ananke.Contatto;

public class Rubrica {
	
	public static void main(String[] args) throws Exception {
		String path = "C:\\Users\\Padawan04\\Desktop\\Snippets\\rubrica.xml";
		Rubrica r = new Rubrica();
		menu();
		Scanner s = new Scanner(System.in);
		String st = s.next();

		while (!st.equalsIgnoreCase("3")) {
			r.choice(st, path, s);
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
		System.out.println("-Digita 3 per uscire");
		System.out.println("-Digita 4 per ristampare il menù");
		
	}
	
	
	
	public void choice(String i, String path, Scanner s) throws Exception{
		switch(i) {
			case "0": stampa(path, s); break;
			case "1": aggiungiVoce(path, s); break;
			case "2": inizializzaNuova(s, path);
			case "3": break;
			case "4": break;
			default: System.out.println("L'opzione selezionata non è valida.");
		}
	}
	
	
	public void stampa(String path, Scanner s) throws Exception {
		//ArrayList<Contatto> contatti = letturaCsv(path);
/*		StringBuilder sb = new StringBuilder();
		sb.append(contatti.get(0).getName()).append(" ")
		  .append(contatti.get(0).getSurname()).append(" ")
		  .append(contatti.get(0).getEmail()).append(" ")
		  .append(contatti.get(0).getTelephone()).append(" ");
		System.out.println(sb.toString());
	*/	
		ArrayList<Contatto> contatti = read(path, s);
		if(contatti.size() == 0) {
			System.out.println("Reindirizzamento al menù.");
		}else {
			for(int i = 0; i < contatti.size(); i++) {
				System.out.println(i + " " + contatti.get(i).toString());
			}
		}
	}
	
	
	public ArrayList<Contatto> read(String path, Scanner s) throws Exception{
		System.out.println("Formato file da leggere: ");
		String st = s.next();
		if(st.equalsIgnoreCase("xml")) {
			return letturaXml(path);
		}else if(st.equalsIgnoreCase("csv")) {
			return letturaCsv(path);
		}else {
			System.out.println("Il formato inserito non è valido.\nSarai reidirizzato al menù.");
			return null;
		}
	}
	
	public ArrayList<Contatto> letturaCsv(String path) throws FileNotFoundException, IOException{
		System.out.println("Sto leggendo un csv");
		File file = new File(path);
		FileReader fr = new FileReader(file);	
		BufferedReader br = new BufferedReader(fr);
		
		ArrayList<String> rows = new ArrayList<String>();
		while (br.ready()) {
			rows.add(br.readLine());
		}
		
		ArrayList<Contatto> lista = new ArrayList<Contatto>();
		for(int i = 0; i < rows.size(); i++) {
			
			String[] rs = rows.get(i).split(";");
			
			Contatto con = new Contatto();
			con.setName(rs[0]);
			con.setSurname(rs[1]);
			con.setEmail(rs[2]);
			con.setTelephone(rs[3]);
			lista.add(con);
		}
		
		br.close();
		fr.close();
		return lista;
	}
	
	public ArrayList<Contatto> letturaXml(String path) throws ParserConfigurationException, SAXException, IOException {
		System.out.println("Sto leggendo un xml");
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        
        Document document = builder.parse(path);
        Element docElement = document.getDocumentElement();       
        NodeList elementiContatto = docElement.getElementsByTagName("utente");
        
        ArrayList<Contatto> contatti = new ArrayList<Contatto>();
        for (int i = 0; i < elementiContatto.getLength(); i++) {
        	
        	Contatto contatto = new Contatto();
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
		ArrayList<Contatto> contatti = new ArrayList<>();
		Contatto cont = defContatto(path, s);
		contatti.add(cont);
		write(path, contatti, s);
	}
	
	
	public void aggiungiVoce(String path, Scanner s) throws Exception {
		ArrayList<Contatto> contatti = read(path, s);
		if(contatti == null) {
			System.out.println("...");
		}else {
			Contatto cont = defContatto(path, s);
			contatti.add(cont);
			write(path, contatti, s);
		}
	}
	
	public Contatto defContatto(String path, Scanner s) throws IOException, FileNotFoundException {
		Contatto cont = new Contatto();
		
		System.out.println("Digita il nome da inserire in rubrica:");
		String st = s.next();
		cont.setName(st);
		
		System.out.println("Digita il cognome da inserire in rubrica:");
		st = s.next();		
		cont.setSurname(st);
		
		System.out.println("Digita l'email da inserire in rubrica:");
		st = s.next();		
		cont.setEmail(st);
		
		System.out.println("Digita il numero di telefono da inserire in rubrica:");
		st = s.next();		
		cont.setTelephone(st);
		
		return cont;
	}
	
	public void write(String path, ArrayList<Contatto> contatti, Scanner s) throws Exception {
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
	
	public void scriviCsv(String path, ArrayList<Contatto> contatti) throws IOException, FileNotFoundException {
		File newFile = new File(path);
		FileWriter fw = new FileWriter(newFile);
		
		contatti.remove(0);
		StringBuilder prima = new StringBuilder("NOME;COGNOME;EMAIL;TELEFONO\n");
		fw.write(prima.toString());
		
		for (Contatto con : contatti) {
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
	
	public void scriviXml(String path, ArrayList<Contatto> contatti) throws Exception {
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
	
	
/*	public void modificaContatto(String path, Scanner s) throws Exception {
		ArrayList<Contatto> contatti = read(path, s);
		System.out.println("Scegliere quale voce modificare: ");
		stampa(path, s);
		String st = s.next();
	
		
	}
*/			
}
