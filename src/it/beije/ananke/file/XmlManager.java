package it.beije.ananke.file;

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

import it.beije.ananke.Contatto;


public class XmlManager {

	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
		
        // Load the input XML document, parse it and return an instance of the Document class.
        Document document = builder.parse("/temp/rubrica.xml");
        
        Element docElement = document.getDocumentElement();       
        System.out.println(docElement.getTagName());
        
        NodeList elementiContatto = docElement.getElementsByTagName("contatto");
        System.out.println("contatti num " + elementiContatto.getLength());
        
//        NodeList nodeList = docElement.getChildNodes();
//        System.out.println("nodeList " + nodeList.getLength());
//        for (int i = 0; i < nodeList.getLength(); i++) {
//        	Node node = nodeList.item(i);
//        	if (node instanceof Element) {
//        		System.out.println("elemento " + ((Element)node).getTagName());
//        	} else {
//        		System.out.println("NO Element");
//        	}
//        }
        
        List<Contatto> contatti = new ArrayList<Contatto>();
        for (int i = 0; i < elementiContatto.getLength(); i++) {
        	Contatto contatto = new Contatto();
        	Element c = (Element)elementiContatto.item(i);
        	
//        	//NON FARE
//        	if (c.getElementsByTagName("email") != null && c.getElementsByTagName("email").getLength() > 0) {
//        		c.getElementsByTagName("email").item(0).getTextContent();
//        	}
//        	//OK
//        	NodeList emails = c.getElementsByTagName("email");
//        	if (emails != null && emails.getLength() > 0) {
//        		emails.item(0).getTextContent();
//        	}
      	
        	//System.out.println("c.getTextContent : " + c.getTextContent());
           	NodeList valori = c.getChildNodes();
            //System.out.println(valori.getLength());
            for (int j = 0; j < valori.getLength(); j++) {
            	Node n = valori.item(j);
            	if (n instanceof Element) {
            		Element valore = (Element) n;
            		System.out.println(valore.getTagName() + " : " + valore.getTextContent());
            		switch (valore.getTagName()) {
					case "nome":
						contatto.setName(valore.getTextContent());
						break;
					case "cognome":
						contatto.setSurname(valore.getTextContent());
						break;
					case "telefono":
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
        
        System.out.println("contatti size : " + contatti.size());
	}

}

/*
<?xml version="1.0" encoding="UTF-8"?>
<rubrica<>
    contatto>
        <nome>Carlo</nome>
        <cognome>Bianchi</cognome>
        <telefono>3337658231</telefono>
    </contatto>
    <contatto>
        <nome>Mario</nome>
        <cognome>Rossi</cognome>
        <telefono>3337658390</telefono>
        <email>mario.rossi@tim.it</email>
    </contatto>
</rubrica>
*/