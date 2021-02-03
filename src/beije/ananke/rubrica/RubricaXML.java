package beije.ananke.rubrica;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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

public class RubricaXML {

	public static void main(String[] args) throws Exception {
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
		
       // Load the input XML document, parse it and return an instance of the Document class.
        Document document = builder.parse("C:/Users/Padawan03/eclipse-workspace/files/rubrica.xml");
        
        //READ CONCTACTS FROM XML FILE
       ArrayList<Contatto> contatti = readContactsXML(document);
       System.out.println(contatti);
     
       writeContactXML(contatti,"C:/Users/Padawan03/eclipse-workspace/files/contacts.xml");
       
	}
	
	//read contacts from XML
	public static ArrayList<Contatto> readContactsXML(Document doc)
	{
		 Element docElement = doc.getDocumentElement();       
	     //System.out.println(docElement.getTagName());
	        
	        NodeList elementiContatto = docElement.getElementsByTagName("contatto");
	        //System.out.println("contatti num " + elementiContatto.getLength());
	        ArrayList<Contatto> contatti = new ArrayList<Contatto>();
	        for (int i = 0; i < elementiContatto.getLength(); i++) {
	        	Contatto contatto = new Contatto();
	        	Element c = (Element)elementiContatto.item(i);
	        
	        	//System.out.println("c.getTextContent : " + c.getTextContent());
	           	NodeList valori = c.getChildNodes();
	            //System.out.println(valori.getLength());
	            for (int j = 0; j < valori.getLength(); j++) {
	            	Node n = valori.item(j);
	            	if (n instanceof Element) {
	            		Element valore = (Element) n;
	            		//System.out.println(valore.getTagName() + " : " + valore.getTextContent());
	            		switch (valore.getTagName()) {
						case "nome":
							contatto.setName(valore.getTextContent());
							break;
						case "cognome":
							contatto.setLastName(valore.getTextContent());
							break;
						case "telefono":
							contatto.setPhone(valore.getTextContent());
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
		
	//write contacts on XML
	public static void writeContactXML(ArrayList<Contatto> list, String path) throws Exception
	{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
		
        Document document = builder.newDocument();
        Element rubrica = document.createElement("rubrica");
        document.appendChild(rubrica);
        
        //...
        Element contatto = null;
        Element name = null;
        Element lastName = null;
        Element phone = null;
        Element email = null;
        for(Contatto cont : list) {
        	contatto = document.createElement("contatto");
        	
        	//add name element
        	name = document.createElement("nome");
        	name.setTextContent(cont.getName());
        	contatto.appendChild(name);
        	
        	//add last name element
        	lastName = document.createElement("cognome");
        	lastName.setTextContent(cont.getLastName());
        	contatto.appendChild(lastName);
        	
        	//add phone element
        	phone = document.createElement("telefono");
        	phone.setTextContent(cont.getPhone());
        	contatto.appendChild(phone);
        	
        	//add mail element
        	email = document.createElement("email");
        	email.setTextContent(cont.getEmail());
        	contatto.appendChild(email);
        	
        	rubrica.appendChild(contatto);
        	
        }
        
        //System.out.println(utenti.getChildNodes().getLength());
        
		// write the content into xml file
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
////NON FARE
//if (c.getElementsByTagName("email") != null && c.getElementsByTagName("email").getLength() > 0) {
//	c.getElementsByTagName("email").item(0).getTextContent();
//}
////OK
//NodeList emails = c.getElementsByTagName("email");
//if (emails != null && emails.getLength() > 0) {
//	emails.item(0).getTextContent();
//}

