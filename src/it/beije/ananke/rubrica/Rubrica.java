package it.beije.ananke.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
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
	
	List<Contatto> list;

	public void aggiungiContattoSulFile(Contatto c) throws Exception {
			aggiungiContatto(c);
			scriviRubricaCSV();
			scriviRubricaXML();
	}
	
	public void caricaRubricaDaSCV(String path) throws IOException {
		this.list = leggiRubricaCSV(path);
		
	}
	
	public void caricaRubricaDaXML() throws IOException, ParserConfigurationException, SAXException {
		this.list = leggiRubricaXML();
	}
	
	private List<Contatto> leggiRubricaCSV(String path) throws IOException{
		
		File file = new File(path);
		FileReader fileReader = new FileReader(file);	
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		
		List<String> rows = new ArrayList<String>();
		List<Contatto> lR = new ArrayList<>();
		
		while (bufferedReader.ready()) {

			rows.add(bufferedReader.readLine());
			
		}
		
				
		for (String row : rows) {
			
			String[] rs = row.split(";");
			Contatto c = new Contatto();		
			c.setNome(rs[0]);
			c.setCognome(rs[1]);
			c.setNumeroTel(rs[2]);
			c.setMail(rs[3]);
					
			lR.add(c);
			
		}
		
		
		bufferedReader.close();
		return lR;
	}
		
		
	

	
	
	public void scriviRubricaCSV() throws Exception {
		
		File f1 = new File("C:\\Users\\Padawan06\\Desktop\\rubrica.csv");
		FileWriter fw = new FileWriter(f1);
		String s;
		
		for(Contatto c : list) {
			s=c.getNome() + ";" + c.getCognome() + ";" + c.getNumeoroTel() + ";" + c.getMail()+ "\n";
			fw.write(s);
		}
		
		fw.flush();
		fw.close();
		
	}

	
	
	
	public void scriviRubricaXML() throws ParserConfigurationException, IOException, SAXException, TransformerException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
		
        Document document = builder.newDocument();
        Element utenti = document.createElement("Rubrica");
        document.appendChild(utenti);
        
        //...
        Element utente = null;
        Element nome = null;
        Element cognome = null;
        Element tel = null;
        Element mail = null;
        
        for (int i = 0; i < this.list.size(); i++) {
        	
        	utente = document.createElement("contatto");
        	//utente.setAttribute("id", Integer.toString(i));//meglio che i + ""
        	
        	nome = document.createElement("nome");
        	nome.setTextContent(list.get(i).getNome());
        	utente.appendChild(nome);
        	
        	cognome = document.createElement("cognome");
        	cognome.setTextContent(list.get(i).getCognome());
        	utente.appendChild(cognome);
        	
        	tel = document.createElement("telefono");
        	tel.setTextContent(list.get(i).getNumeroTel());
        	utente.appendChild(tel);
        	
        	mail = document.createElement("email");
        	mail.setTextContent(list.get(i).getMail());
        	utente.appendChild(mail);
        	
        	utenti.appendChild(utente);
        	
        	
        }
        
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		
		StreamResult result = new StreamResult(new File("/temp/utenti.xml"));

		
		StreamResult syso = new StreamResult(System.out);

		transformer.transform(source, result);
		transformer.transform(source, syso);

		System.out.println("File saved!");
		
		
}
	
	public List<Contatto> leggiRubricaXML()  throws ParserConfigurationException, IOException, SAXException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        List<Contatto> contatti = new ArrayList<Contatto>();
		
        Document document = builder.parse("C:\\Users\\Padawan06\\Desktop\\rubrica.xml");
        
        Element docElement = document.getDocumentElement();  
        
        NodeList elementiContatto = docElement.getElementsByTagName("contatto");
        
        
        for (int i = 0; i < elementiContatto.getLength(); i++) {
        	
        	Contatto contatto = new Contatto();
        	
        	Element c = (Element)elementiContatto.item(i);
 
           	NodeList valori = c.getChildNodes();
           
            for (int j = 0; j < valori.getLength(); j++) {
            	
            	Node n = valori.item(j);
            	
            	if (n instanceof Element) {
            		Element valore = (Element) n;
            		
            		//System.out.println(valore.getTagName() + " : " + valore.getTextContent());
            		
            		switch (valore.getTagName()) {
					case "nome":
						contatto.setNome(valore.getTextContent());
						break;
					case "cognome":
						contatto.setCognome(valore.getTextContent());
						break;
					case "telefono":
						contatto.setNumeroTel(valore.getTextContent());
						break;
					case "email":
						contatto.setMail(valore.getTextContent());
						break;

					default:
						System.out.println("elemento in contatto non riconosciuto");
						break;
					}
            	}
            }
            contatti.add(contatto);
        }
        
        System.out.println("contatti size : " + contatti.size());
        return contatti;
        
	}

		
	public void aggiungiContatto(Contatto c) {
		list.add(c);
	}

	
	public void visualizzaRubrica() {
		System.out.println("-----------------Rubrica-----------------");
		
		for(Contatto c:list) {
			System.out.println(c.getNome() + ";" + c.getCognome() + ";" + c.getNumeoroTel() + ";" + c.getMail());
		}
		
		System.out.println("-----------------------------------------");
		
	}
		
		
}
	



/* File f1 = new File("C:\\Users\\Padawan06\\Desktop\\temp.csv");
FileWriter fw = new FileWriter(f1);
File f2 = new File("C:\\Users\\Padawan06\\Desktop\\Rubrica.csv");
FileReader fr = new FileReader(f2);
BufferedReader br = new BufferedReader(fr);		
String s; */

//String[] contatto;
	
/*	while (br.ready()) {
	 	s =	br.readLine();
		fw.write(s);
	}
	fw.write(c.getNome() + ";" + c.getCognome() + ";" + c.getNumeoroTel() + ";" + c.getMail()); */



