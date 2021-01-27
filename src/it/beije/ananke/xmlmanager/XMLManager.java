package it.beije.ananke.xmlmanager;

import it.beije.ananke.Contatto;
import it.beije.ananke.rubrica.Contact;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class XMLManager {
    private String root = "C:\\Users\\Padawan01\\IdeaProjects\\git\\Ananke\\src\\it\\beije\\ananke\\xmlmanager\\rubrica.xml";

    public List<Contact> openXML() throws IOException, ParserConfigurationException, SAXException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(root);
        //cerco il primo tag del documento, che è il document element
        Element element = document.getDocumentElement();

        //inserisco in una nodelist gli elementi che hanno il tag "contatto"
        NodeList listContatto = element.getElementsByTagName("contatto");

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
                        case "nome":
                            contact.setFirstname(subElement.getTextContent());
                            break;
                        case "cognome":
                            contact.setLastName(subElement.getTextContent());
                            break;
                        case "telefono":
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

    public void writeXML() throws ParserConfigurationException {
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

    }

}
