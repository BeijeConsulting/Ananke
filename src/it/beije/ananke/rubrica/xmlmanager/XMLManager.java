package it.beije.ananke.rubrica.xmlmanager;

import it.beije.ananke.rubrica.Contact;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLManager {
    public static List<Contact> open(String root) throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(root);
        //cerco il primo tag del documento, che è il document element
        Element element = document.getDocumentElement();

        //inserisco in una nodelist gli elementi che hanno il tag "contatto"
        NodeList listContatto = element.getElementsByTagName("contacts");

        //creo una lista di contatti che va riempita con... dei contatti
        //devo ripetere il procedimento sopra per ogni nodo presente nella nodelist
        //quindi passo al ciclo for sotto
        List<Contact> contacts = new ArrayList<>();

        for (int i = 0; i < listContatto.getLength(); i++) {
            //creo il contatto da riempire che poi andrà nella lista
            Contact contact = new Contact();
            //trasformo i nodi presenti nella nodelist in elementi
            //ciò significa che il mio elemento sarà quanto racchiuso nei tag <contatto></contatto>
            Element e = (Element) listContatto.item(i);

            //adesso devo definire i tag dentro il mio contatto
            NodeList values = e.getChildNodes();

            for (int j = 0; j < values.getLength(); j++) {
                Node n = values.item(j);
                if (n instanceof Element) {
                    Element subElement = (Element) n;
                    switch (subElement.getTagName()) {
                        case "firstName":
                            contact.setFirstName(subElement.getTextContent());
                            break;
                        case "lastName":
                            contact.setLastName(subElement.getTextContent());
                            break;
                        case "phoneNumber":
                            contact.setPhoneNumber(subElement.getTextContent());
                            break;
                        case "email":
                            contact.setEmail(subElement.getTextContent());
                            break;
                        default:
                            System.out.println("elemento in contatto non riconosciuto");
                            break;
                    }
                }
            }
            contacts.add(contact);
        }
        return contacts;
    }

    public static void write(List<Contact> contacts, String root) throws ParserConfigurationException, TransformerException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.newDocument();
        Element utenti = document.createElement("contacts");
        document.appendChild(utenti);

        Element utente = null;
        Element firstName = null;
        Element lastName = null;
        Element phoneNumber = null;
        Element email = null;

        for (int i = 0; i < contacts.size(); i++) {
            utente = document.createElement("contact");
            utente.setAttribute("id", Integer.toString(i));

            firstName = document.createElement("firstName");
            firstName.setTextContent(contacts.get(i).getFirstName());
            utente.appendChild(firstName);

            lastName = document.createElement("lastName");
            lastName.setTextContent(contacts.get(i).getLastName());
            utente.appendChild(lastName);

            phoneNumber = document.createElement("phoneNumber");
            phoneNumber.setTextContent(contacts.get(i).getPhoneNumber());
            utente.appendChild(phoneNumber);

            email = document.createElement("email");
            email.setTextContent(contacts.get(i).getEmail());
            utente.appendChild(email);

            utenti.appendChild(utente);
        }
        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        StreamResult result = new StreamResult(new File(root));
        StreamResult syso = new StreamResult(System.out);

        transformer.transform(source, result);
        transformer.transform(source, syso);
    }

}
