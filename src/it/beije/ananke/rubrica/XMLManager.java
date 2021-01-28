package it.beije.ananke.rubrica;
import java.io.File;
import java.io.IOException;
import java.util.*;

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

public class XMLManager {
List <Contatti> contatti = new ArrayList<>();

	public XMLManager() {
		
	}
	
public static List<Contatti> readFile(String path) throws IOException, SAXException, ParserConfigurationException {
		
	    List<Contatti> contatti = new ArrayList<>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        
		Document document = builder.parse(path);
		Element docElement = document.getDocumentElement();
		NodeList elementiContatto = docElement.getElementsByTagName("contatto");
		for (int i = 0; i < elementiContatto.getLength(); i++) {
        	Contatti contatto = new Contatti();
        	Element c = (Element)elementiContatto.item(i);
        	NodeList valori = c.getChildNodes();
            
            for (int j = 0; j < valori.getLength(); j++) {
            	Node n = valori.item(j);
            	if (n instanceof Element) {
            		Element valore = (Element) n;
            		
            		switch (valore.getTagName()) {
					case "name":
						contatto.setName(valore.getTextContent());
						break;
					case "surname":
						contatto.setSurname(valore.getTextContent());
						break;
					case "telephone":
						contatto.setCell(valore.getTextContent());
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

public static void writeXML(List<Contatti> l) throws ParserConfigurationException, IOException, SAXException, TransformerException {
	Scanner sc = new Scanner(System.in);
	String s;
	System.out.println("Inserisci il nome del file da generare");
	s = "/Users/Gianni/Desktop/" + sc.next() + ".xml";
	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
		
        Document document = builder.newDocument();
        Element rubrica = document.createElement("rubrica");
        document.appendChild(rubrica);
        
        //...
        Element contatto = null;
        Element name = null;
        Element surname = null;
        Element email = null;
        Element telephone = null;
        int i=0;
        for(Contatti c : l ) {
        	contatto = document.createElement("contatto");
        	contatto.setAttribute("id", Integer.toString(i++));
        	
        	name = document.createElement("name");
        	name.setTextContent(c.getName());
        	contatto.appendChild(name);
        	
        	surname = document.createElement("surname");
        	surname.setTextContent(c.getSurname());
        	contatto.appendChild(surname);
        	
        	email = document.createElement("email");
        	email.setTextContent(c.getEmail());
        	contatto.appendChild(email);
        	
        	telephone = document.createElement("telephone");
        	telephone.setTextContent(c.getCell());
        	contatto.appendChild(telephone);
        	rubrica.appendChild(contatto);
        	}
        
        
        System.out.println(rubrica.getChildNodes().getLength());
        
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		
		StreamResult result = new StreamResult(new File(s));

		// Output to console for testing
		StreamResult syso = new StreamResult(System.out);

		transformer.transform(source, result);
		transformer.transform(source, syso);

		System.out.println("File salvato!");
        
	}

}
