//package it.beije.ananke.file;
//
//import java.io.File;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.Transformer;
//import javax.xml.transform.TransformerConfigurationException;
//import javax.xml.transform.TransformerException;
//import javax.xml.transform.TransformerFactory;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamResult;
//
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.SAXException;
//
//import it.beije.ananke.hib.prova.Contatto;
//
//
//public class XmlManager {
//
//	public static void read() throws ParserConfigurationException, IOException, SAXException{
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//		
//        // Load the input XML document, parse it and return an instance of the Document class.
//        Document document = builder.parse("/temp/rubrica.xml");
//        
//        Element docElement = document.getDocumentElement();       
//        System.out.println(docElement.getTagName());
//        
//        NodeList elementiContatto = docElement.getElementsByTagName("contatto");
//        System.out.println("contatti num " + elementiContatto.getLength());
//        
////        NodeList nodeList = docElement.getChildNodes();
////        System.out.println("nodeList " + nodeList.getLength());
////        for (int i = 0; i < nodeList.getLength(); i++) {
////        	Node node = nodeList.item(i);
////        	if (node instanceof Element) {
////        		System.out.println("elemento " + ((Element)node).getTagName());
////        	} else {
////        		System.out.println("NO Element");
////        	}
////        }
//        
//        List<Contatto> contatti = new ArrayList<Contatto>();
//        for (int i = 0; i < elementiContatto.getLength(); i++) {
//        	Contatto contatto = new Contatto();
//        	Element c = (Element)elementiContatto.item(i);
//        	
////        	//NON FARE
////        	if (c.getElementsByTagName("email") != null && c.getElementsByTagName("email").getLength() > 0) {
////        		c.getElementsByTagName("email").item(0).getTextContent();
////        	}
////        	//OK
////        	NodeList emails = c.getElementsByTagName("email");
////        	if (emails != null && emails.getLength() > 0) {
////        		emails.item(0).getTextContent();
////        	}
//      	
//        	//System.out.println("c.getTextContent : " + c.getTextContent());
//           	NodeList valori = c.getChildNodes();
//            //System.out.println(valori.getLength());
//            for (int j = 0; j < valori.getLength(); j++) {
//            	Node n = valori.item(j);
//            	if (n instanceof Element) {
//            		Element valore = (Element) n;
//            		System.out.println(valore.getTagName() + " : " + valore.getTextContent());
//            		switch (valore.getTagName()) {
//					case "nome":
//						contatto.setName(valore.getTextContent());
//						break;
//					case "cognome":
//						contatto.setSurname(valore.getTextContent());
//						break;
//					case "telefono":
//						contatto.setTelephone(valore.getTextContent());
//						break;
//					case "email":
//						contatto.setEmail(valore.getTextContent());
//						break;
//
//					default:
//						System.out.println("elemento in contatto non riconosciuto");
//						break;
//					}
//            	}
//            }
//            contatti.add(contatto);
//        }
//        
//        System.out.println("contatti size : " + contatti.size());
//	}
//
//	
//	public static void write() throws ParserConfigurationException, IOException, SAXException, TransformerException {
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//        DocumentBuilder builder = factory.newDocumentBuilder();
//		
//        Document document = builder.newDocument();
//        Element utenti = document.createElement("utenti");
//        document.appendChild(utenti);
//        
//        //...
//        Element utente = null;
//        Element name = null;
//        for (int i = 0; i < 5; i++) {
//        	utente = document.createElement("utente");
//        	utente.setAttribute("id", Integer.toString(i));//meglio che i + ""
//        	
//        	name = document.createElement("name");
//        	name.setTextContent("nome"+i);
//        	utente.appendChild(name);
//        	
//        	utenti.appendChild(utente);
//        	/*
//        	 * <utenti>
//        	 *   <utente id="0">
//        	 *     <name>nome0</name>
//        	 *   </utente>
//        	 *   <utente id="1">
//        	 *     <name>nome1</name>
//        	 *   </utente>
//        	 *   ...
//        	 * </utenti>
//        	 */
//        }
//        
//        System.out.println(utenti.getChildNodes().getLength());
//        
//		// write the content into xml file
//		TransformerFactory transformerFactory = TransformerFactory.newInstance();
//		Transformer transformer = transformerFactory.newTransformer();
//		DOMSource source = new DOMSource(document);
//		
//		StreamResult result = new StreamResult(new File("/temp/utenti.xml"));
//
//		// Output to console for testing
//		StreamResult syso = new StreamResult(System.out);
//
//		transformer.transform(source, result);
//		transformer.transform(source, syso);
//
//		System.out.println("File saved!");
//        
//	}
//	
//	public static void main(String[] args) {
//		try {
//			//read();
//			write();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}
//}
//
///*
//<?xml version="1.0" encoding="UTF-8"?>
//<rubrica>
//    <contatto>
//        <nome>Carlo</nome>
//        <cognome>Bianchi</cognome>
//        <telefono>3337658231</telefono>
//    </contatto>
//    <contatto>
//        <nome>Mario</nome>
//        <cognome>Rossi</cognome>
//        <telefono>3337658390</telefono>
//        <email>mario.rossi@tim.it</email>
//    </contatto>
//</rubrica>
//*/