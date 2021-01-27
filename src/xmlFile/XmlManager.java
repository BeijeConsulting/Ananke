package xmlFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;




public class XmlManager {
	
	public List<Contatto>readXml(String path)throws ParserConfigurationException, IOException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(path);
        Element docElement = document.getDocumentElement();       
        System.out.println(docElement.getTagName());
        NodeList elementiContatto = docElement.getElementsByTagName("contatto");
        System.out.println("contatti num " + elementiContatto.getLength());
       	
        List<Contatto> contatti = new ArrayList<Contatto>();
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
          	System.out.println("Sto per leggere l'email");
        	NodeList email = c.getElementsByTagName("email");
          	System.out.println("email letta");
          	System.out.println(email.getLength());
          	
        	if (email != null && email.getLength()>0) {
              	System.out.println(email.getLength());
        		contatto.setEmail(email.item(0).getTextContent());
        	}
        	NodeList tel = c.getElementsByTagName("telefono");
        	if (tel != null ) {
        		contatto.setTel(tel.item(0).getTextContent());
        	}
       
       contatti.add(contatto);
        	
        	
        }
		return contatti;
		
	}

	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException{
		XmlManager manager=new XmlManager();
		List<Contatto> contatti=new ArrayList<>();
		contatti=manager.readXml("C:/Users/Padawan07/Desktop/rubrica.xml");
		for(Contatto c: contatti) {
			System.out.println(c.toString());
			
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