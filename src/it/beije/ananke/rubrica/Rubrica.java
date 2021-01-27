package it.beije.ananke.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class Rubrica {
	
	List<Contatto> list;

	public void aggiungiContattoSulFile(Contatto c) throws Exception {
		
			this.aggiungiContatto(c);
			this.salvaRubricaSuFile();
	}

	private void salvaRubricaSuFile() throws Exception {
		
		File f1 = new File("C:\\Users\\Padawan06\\Desktop\\rubrica.csv");
		FileWriter fw = new FileWriter(f1);
		String s;
		
		for(Contatto c : list) {
			s=c.getNome() + ";" + c.getCognome() + ";" + c.getNumeoroTel() + ";" + c.getMail();
			fw.write(s);
		}
		
		fw.close();
		
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
	
	public List<Contatto> leggiRubricaXML()  throws ParserConfigurationException, IOException, SAXException {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        List<Contatto> contatti = new ArrayList<Contatto>();
		
        Document document = builder.parse("/temp/rubrica.xml");
        
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



