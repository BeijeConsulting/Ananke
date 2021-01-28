package it.beije.ananke.rubrica;

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
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sun.org.apache.bcel.internal.generic.NEW;

import it.beije.ananke.rubrica.*;

public class XmlManager {
	static List<Contatto> contattiXml = new ArrayList<Contatto>();

	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException{
		try {
			contattiXml.add(new Contatto("Marco", "Imper", "345", "ghn@mail.com"));
			contattiXml.add(new Contatto("Marco", "Imper", "345", "ghn@mail.com"));
			//read();
			write();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void read() throws ParserConfigurationException, IOException, SAXException{
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		// Load the input XML document, parse it and return an instance of the Document class.
		Document document = builder.parse("/Users/Padawan08/Desktop/rubrica.xml");
		//qua stiamo prendendo il nome del tag principale che in questo caso è rubrica
		Element docElement = document.getDocumentElement();       
		System.out.println(docElement.getTagName());
		//mentre qua stiamo indicando quanti elementi ci sono all' interno della rubrica, ognunocon i suoi attributi nome ecc
		NodeList elementiContatto = docElement.getElementsByTagName("contatto");
		System.out.println("contatti num " + elementiContatto.getLength());

		//creo l' arraylist nel quale andrò a salvare i miei dati della rubrica
		List<Contatto> contatti = new ArrayList<Contatto>();
		for (int i = 0; i < elementiContatto.getLength(); i++) {
			Contatto contatto = new Contatto();
			Element c = (Element)elementiContatto.item(i);

			//        	//NON FARE, SFORZA INUTILMENTE LA MACCHINA
			//        	if (c.getElementsByTagName("email") != null && c.getElementsByTagName("email").getLength() > 0) {
			//        		c.getElementsByTagName("email").item(0).getTextContent();
			//        	}

			//        	//OK, COSI' POSSIAMO FARLO
			//        	NodeList emails = c.getElementsByTagName("email");
			//        	if (emails != null && emails.getLength() > 0) {
			//        		emails.item(0).getTextContent();
			//        	}

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
			contattiXml.add(contatto);
		}

		System.out.println("contatti size : " + contattiXml.size());
	}






	public static void write() throws ParserConfigurationException, IOException, SAXException, TransformerException {
		Contatto contatto = new Contatto();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = factory.newDocumentBuilder();

		Document document = builder.newDocument();
		Element utenti = document.createElement("utenti");
		document.appendChild(utenti);

		//...
		Element utente = null;
		Element name = null;
		Element surname = null;
		Element phone = null;
		Element email = null;

		for (int i = 0; i < contattiXml.size(); i++) {
			utente = document.createElement("utente");
			utente.setAttribute("id", Integer.toString(i));//meglio che i + ""

			name = document.createElement("nome");
			name.setTextContent(contattiXml.get(i).getName());
			surname = document.createElement("cognome");
			surname.setTextContent(contattiXml.get(i).getSurname());
			phone = document.createElement("telefono");
			phone.setTextContent(contattiXml.get(i).getTelephone());
			email = document.createElement("email");
			email.setTextContent(contattiXml.get(i).getEmail());
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

		StreamResult result = new StreamResult(new File("/Users/Padawan08/Desktop/rubrica.xml"));

		// Output to console for testing
		StreamResult syso = new StreamResult(System.out);

		transformer.transform(source, result);
		transformer.transform(source, syso);

		System.out.println("File saved!");

	}
}