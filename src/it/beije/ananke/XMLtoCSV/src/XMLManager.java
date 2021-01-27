
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.*;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;



public class XMLManager {

	public List<Contatto> contatti = new ArrayList<>();
	
	String path;
	
	public XMLManager(String path) {
		this.path = path;
		try {
			this.contatti = readFile(path);
		} catch (IOException e) {
			
			e.printStackTrace();
		} catch (SAXException e) {
			
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			
			e.printStackTrace();
		}
		System.out.println();
	}
	
	public static List<Contatto> readFile(String path) throws IOException, SAXException, ParserConfigurationException {
		
	    List<Contatto> contatti = new ArrayList<>();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
		Document document = builder.parse(path);
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
		return contatti;
	}
	
	
}
