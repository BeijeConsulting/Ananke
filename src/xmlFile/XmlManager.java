package xmlFile;
import java.io.File;
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
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import csv.*;

public class XmlManager {
List<Contatto> listContatti;
	
	
	public XmlManager() {
	super();
	listContatti=new ArrayList<>();
}

	public ArrayList<Contatto>readXml(String path)throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(path);
        Element docElement = document.getDocumentElement();       
        System.out.println(docElement.getTagName());
        NodeList elementiContatto = docElement.getElementsByTagName("contatto");
        System.out.println("contatti num " + elementiContatto.getLength());
       	
        ArrayList<Contatto>contatti = new ArrayList<>();
        for (int i = 0; i < elementiContatto.getLength(); i++) {
        	Contatto contatto = new Contatto();
        	Element c = (Element)elementiContatto.item(i);
        	//Nome
         	NodeList nome = c.getElementsByTagName("nome");
         
        	if (nome != null ) {
        		contatto.setNome(nome.item(0).getTextContent());
        	}
        	NodeList cognome = c.getElementsByTagName("cognome");
        	if (cognome != null ) {
        		contatto.setCognome(cognome.item(0).getTextContent());
        	}
        	NodeList email = c.getElementsByTagName("email");
        
          	
        	if (email != null && email.getLength()>0) {
        		contatto.setEmail(email.item(0).getTextContent());
        	}
        	NodeList tel = c.getElementsByTagName("telefono");
        	if (tel != null ) {
        		contatto.setTel(tel.item(0).getTextContent());
        	}
       
       contatti.add(contatto);
        	
        	
        } 
        listContatti=contatti;
		return contatti;
		
		
	}

	public  void write(String path) throws ParserConfigurationException, IOException, SAXException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
		
        Document document = builder.newDocument();
        Element utenti = document.createElement("utenti");
        document.appendChild(utenti);
        
        //...
        Element utente = null;
        Element nome = null;
        Element cognome = null;
        Element email = null;
        Element tel = null;
        for (Contatto c:listContatti)  {
        	utente = document.createElement("contatto");        	
        	nome = document.createElement("nome");
        	nome.setTextContent(c.getNome());
        	cognome = document.createElement("cognome");
        	cognome.setTextContent(c.getCognome());
        	email = document.createElement("email");
        	email.setTextContent(c.getEmail());
        	tel = document.createElement("tel");
        	tel.setTextContent(c.getTel());
        	utente.appendChild(nome);   
        	utente.appendChild(cognome);    
        	utente.appendChild(email);    
        	utente.appendChild(tel);    
        	utenti.appendChild(utente);
        	/*
        	 * <utenti>
        	 *   <utente id="0">
        	 *     <name>nome0</name>
        	 *   </utente>
        	 *   <utente id="1">
        	 *     <name>nome1</name>
        	 *   </utente>
        	 *   ...
        	 * </utenti>
        	 */
        }
        
        System.out.println(utenti.getChildNodes().getLength());
        
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		
		StreamResult result = new StreamResult(new File( path));

		// Output to console for testing
		StreamResult syso = new StreamResult(System.out);

		transformer.transform(source, result);
		transformer.transform(source, syso);

		System.out.println("File saved!");
        
	}
	
	public  void writeArray(String path,List<Contatto> copy) throws ParserConfigurationException, IOException, SAXException, TransformerException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
		
        Document document = builder.newDocument();
        Element utenti = document.createElement("utenti");
        document.appendChild(utenti);
        
        //...
//       
       
        Element contatto = null;
        Element nome = null;
        Element cognome = null;
        Element email = null;
        Element tel = null;
        for (Contatto c:copy)  {
        	contatto = document.createElement("contatto");        	
        	nome = document.createElement("nome");
        	nome.setTextContent(c.getNome());
        	cognome = document.createElement("cognome");
        	cognome.setTextContent(c.getCognome());
        	email = document.createElement("email");
        	email.setTextContent(c.getEmail());
        	tel = document.createElement("telefono");
        	tel.setTextContent(c.getTel());
        	contatto.appendChild(nome);   
        	contatto.appendChild(cognome);    
        	contatto.appendChild(email);    
        	contatto.appendChild(tel);    
        	utenti.appendChild(contatto);
        	/*
        	 * <utenti>
        	 *   <utente id="0">
        	 *     <name>nome0</name>
        	 *   </utente>
        	 *   <utente id="1">
        	 *     <name>nome1</name>
        	 *   </utente>
        	 *   ...
        	 * </utenti>
        	 */
        }
        
        
		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);
		
		StreamResult result = new StreamResult(new File( path));

		// Output to console for testing
		StreamResult syso = new StreamResult(System.out);

		transformer.transform(source, result);
		transformer.transform(source, syso);

		System.out.println("\nFile saved!");
        
	}
	
	public void addContatto(Contatto c) {
		listContatti.add(c);
		
	}
	
	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException{
		XmlManager manager=new XmlManager();
		List<Contatto> contatti=new ArrayList<>();
		contatti=manager.readXml("C:/Users/Padawan07/Desktop/rubrica.xml");
		for(Contatto c: contatti) {
			System.out.println(c.toString());
			
		}
		try {
			manager.write("C:/Users/Padawan07/Desktop/rubrica.xml");
		} catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

/*
<?xml version="1.0" encoding="UTF-8"?>
<rubrica>
    <contatto>
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