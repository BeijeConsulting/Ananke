package it.beije.ananke.rubrica;

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

public class XmlFile {

    public static ArrayList<Contact> readFromFile(String path) throws ParserConfigurationException, IOException, SAXException {

        ArrayList<Contact> rubric = new ArrayList<>();

        //servono per leggere e scrivere file xml.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //creo il documento virtuale
        Document document = builder.parse(path);

        Element docElement = document.getDocumentElement();
        NodeList elementiContatto = docElement.getElementsByTagName("contatto");

        for (int i = 0; i < elementiContatto.getLength(); i++) {

            Contact contact = new Contact();
            Element c = (Element) elementiContatto.item(i);

            NodeList valori = c.getChildNodes();
            //System.out.println(valori.getLength());
            for (int j = 0; j < valori.getLength(); j++) {
                Node n = valori.item(j);
                if (n instanceof Element) {
                    Element valore = (Element) n;
                    //System.out.println(valore.getTagName() + " : " + valore.getTextContent());
                    switch (valore.getTagName()) {
                        case "nome":
                            contact.setName(valore.getTextContent().trim());
                            break;
                        case "cognome":
                            contact.setSurname(valore.getTextContent().trim());
                            break;
                        case "telefono":
                            contact.setTelephone(valore.getTextContent().trim());
                            break;
                        case "email":
                            contact.setEmail(valore.getTextContent().trim());
                            break;

                        default:
                            System.out.println("elemento in contatto non riconosciuto");
                            break;
                    }
                }
            }
            rubric.add(contact);
        }

        return rubric;
    }

    public static boolean writeOnFile(String path, ArrayList<Contact> rubric) throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.newDocument();
        Element listaContatti = document.createElement("rubrica");
        document.appendChild(listaContatti);

        Element contatto = null;
        Element nome = null;
        Element cognome = null;
        Element telefono = null;
        Element email = null;

        for (int i = 0; i < rubric.size(); i++) {

            contatto = document.createElement("contatto");
            //potrei fare anche contatto.setAttribte

            nome = document.createElement("nome");
            nome.setTextContent(rubric.get(i).getName());
            contatto.appendChild(nome);

            cognome = document.createElement("cognome");
            cognome.setTextContent(rubric.get(i).getSurname());
            contatto.appendChild(cognome);

            telefono = document.createElement("telefono");
            telefono.setTextContent(rubric.get(i).getTelephone());
            contatto.appendChild(telefono);

            email = document.createElement("email");
            email.setTextContent(rubric.get(i).getEmail());
            contatto.appendChild(email);

            listaContatti.appendChild(contatto);
            //lui non aggiunge i tag con le identazioni corrette
        }

        //per scriverlo, bisogna prendere quello che c'è in memoria
        //e realizzare uno stream

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        //lo stream può essere sì un file, ma anche il s.out, ma anche la risposta alla chiamata http
        StreamResult result = new StreamResult(new File(path));

        transformer.transform(source, result);

        return true;

    }

}
