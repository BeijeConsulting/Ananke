package it.beije.ananke.xmlmanager;

import it.beije.ananke.file.XmlManager;
import it.beije.ananke.rubrica.Contact;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.List;

public class XMLTest {
    public static void main(String[] args) {
        XMLManager x = new XMLManager();
        try {
            List<Contact> contacts = x.openXML();
            for (Contact c : contacts) {
                System.out.println(c);
            }
        } catch (IOException | SAXException | ParserConfigurationException e) {
            System.out.println(e.getMessage());
        }

    }
}
