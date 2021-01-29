import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
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

public class ManagerXml{
	
	static List<Contatto> contatti = new ArrayList<Contatto>();

	public static void read() throws ParserConfigurationException, IOException, SAXException{
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.parse("/Users/davidepersico/Desktop/Beije/Ananke/rubrica.xml");
		
		Element docElement = document.getDocumentElement();       
		System.out.println(docElement.getTagName());
		
		NodeList elementiContatto = docElement.getElementsByTagName("contatto");
		System.out.println("contatti num " + elementiContatto.getLength());

		
		List<Contatto> contatti = new ArrayList<Contatto>();
		
		for (int i = 0; i < elementiContatto.getLength(); i++) {
			Contatto contatto = new Contatto();
			Element c = (Element)elementiContatto.item(i);

			NodeList valori = c.getChildNodes();
			
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

		System.out.println("contatti presenti : " + contatti.size());
	}

	public static void write() throws ParserConfigurationException, IOException, SAXException, TransformerException {
		
		// Contatto contatto = new Contatto();
		
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.newDocument();
		Element utenti = document.createElement("utenti");
		document.appendChild(utenti);

		Element utente = null;
		Element name = null;
		Element surname = null;
		Element phone = null;
		Element email = null;

		for (int i = 0; i < contatti.size(); i++) {
			utente = document.createElement("utente");
			utente.setAttribute("id", Integer.toString(i));

			name = document.createElement("nome");
			name.setTextContent(contatti.get(i).getName());
			
			surname = document.createElement("cognome");
			surname.setTextContent(contatti.get(i).getSurname());
			
			phone = document.createElement("telefono");
			phone.setTextContent(contatti.get(i).getTelephone());
			
			email = document.createElement("email");
			email.setTextContent(contatti.get(i).getEmail());
			
			utente.appendChild(name);
			utente.appendChild(surname);
			utente.appendChild(phone);
			utente.appendChild(email);

			utenti.appendChild(utente);
		}

		System.out.println(utenti.getChildNodes().getLength());

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(document);

		StreamResult result = new StreamResult(new File("/Users/davidepersico/Desktop/Beije/Ananke/rubrica.xml"));

		// Output to console for testing
		StreamResult syso = new StreamResult(System.out);

		transformer.transform(source, result);
		transformer.transform(source, syso);

		System.out.println("File saved!");

	}
	
	public void aggiungiContatto(Contatto contatto) throws ParserConfigurationException, IOException, SAXException, TransformerException {
		
		contatti.add(contatto);
		
		write();
		
	}
	
	 public Contatto cerca(String nome, String cognome) {
		 
		 	Contatto tempContatto = null;
		 
	        for (Contatto contatto : contatti) {
	            if (contatto.getName().equalsIgnoreCase(nome) && contatto.getSurname().equalsIgnoreCase(cognome)) {
	                tempContatto = contatto;
	            }
	        }
	        
	        if (tempContatto != null) {
	        	
	        	System.out.println("Il contatto che cercavi è : ");
		        System.out.println("Nome: " + tempContatto.getName());
		        System.out.println("Cognome: " + tempContatto.getSurname());
		        System.out.println("Email: " + tempContatto.getEmail());
		        System.out.println("Numero di telefono: " + tempContatto.getTelephone());
		        
	        } else {
	        	
	        	System.out.println("Non è stato trovato un contatto con questo nome e cognome");
	        	
	        }

	        System.out.println("");
	        
	        
			return tempContatto;
	        
	    }
	 
	 public void modificaNome(Contatto tempContatto, String nome) throws ParserConfigurationException, IOException, SAXException, TransformerException {
		 
		 tempContatto.setName(nome);
		 
		 write();
		 
	 }
	 
	 public void modificaCognome(Contatto tempContatto, String cognome) throws ParserConfigurationException, IOException, SAXException, TransformerException {
		 
		 tempContatto.setSurname(cognome);
		 
		 write();
		 
	 }
	 
	 public void modificaTelefono(Contatto tempContatto, String telefono) throws ParserConfigurationException, IOException, SAXException, TransformerException {
		 
		 tempContatto.setTelephone(telefono);
		 
		 write();
		 
	 }
	 
	 public void modificaEmail(Contatto tempContatto, String email) throws ParserConfigurationException, IOException, SAXException, TransformerException {
		 
		 tempContatto.setTelephone(email);
		 
		 write();
		 
	 }
	 
	 
	 
	 public void elimina(String nome, String cognome) throws IOException, ParserConfigurationException, SAXException, TransformerException, SQLException {
		 
		 	Contatto tempContatto = null;
		 	
		 	for (Contatto contatto : contatti) {
	            if (contatto.getName().equalsIgnoreCase(nome) && contatto.getSurname().equalsIgnoreCase(cognome)) {
	                tempContatto = contatto;
	            }
	        }
		 	
		 	if( tempContatto != null) {
		 		
		 		contatti.remove(tempContatto);
		 		
		 		JDBCManager.eliminaContattoDb(tempContatto);
		 		
		 		System.out.println("Il contatto ");
		 		System.out.println("");
		        System.out.println("Nome: " + tempContatto.getName());
		        System.out.println("Cognome: " + tempContatto.getSurname());
		        System.out.println("Email: " + tempContatto.getEmail());
		        System.out.println("Numero di telefono: " + tempContatto.getTelephone());
		        System.out.println("");
		        System.out.println("E' stato eliminato correttamente");
		        
		        write();
		 		
		 	}
		 	
		 	if( tempContatto == null) {
		 		
		 		System.out.println("Non è stato trovato un contatto con questo nome e cognome");
		 		
		 	}
		 	
	 }
	 
	 	public void visualizzaContatti() {
		 
			for(Contatto contatto : contatti) {
				
				System.out.println(contatto.stampa());
				
			}
		}
	
}