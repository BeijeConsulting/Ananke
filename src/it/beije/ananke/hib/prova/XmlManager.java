//package it.beije.ananke.hib.prova;
//
//import java.io.File;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Scanner;
//
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.parsers.ParserConfigurationException;
//import javax.xml.transform.Transformer;
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
//import com.sun.org.apache.bcel.internal.generic.NEW;
//
//import it.beije.ananke.rubrica.*;
//
//public class XmlManager {
//	static ArrayList<Contatto> contattiXml = new ArrayList<Contatto>();
//	private File file;
//	//	public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException{
//	//		try {
//	//			
//	//		} catch (Exception e) {
//	//			e.printStackTrace();
//	//		}	
//	//	}
//
//	public XmlManager(String filePath) {
//		file = new File(filePath);
//		//contattiXml.add(new Contatto("Marco", "Imper", "345", "ghn@mail.com"));
//		if (file.exists()) {
//			System.out.println("Il file " + file + " esiste già");
//			try {		
//				read(file);
//			}catch (ParserConfigurationException | SAXException e) {
//				e.printStackTrace();
//			}		
//			catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//		else {
//			try {
//				FileWriter fileWriter = new FileWriter(file);
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public static void read(File file) throws ParserConfigurationException, IOException, SAXException{
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder builder = factory.newDocumentBuilder();
//
//		// Load the input XML document, parse it and return an instance of the Document class.
//		Document document = builder.parse(file);
//		//qua stiamo prendendo il nome del tag principale che in questo caso è rubrica
//		Element docElement = document.getDocumentElement();       
//		System.out.println(docElement.getTagName());
//		//mentre qua stiamo indicando quanti elementi ci sono all' interno della rubrica, ognunocon i suoi attributi nome ecc
//		NodeList elementiContatto = docElement.getElementsByTagName("utente");
//
//		System.out.println("contatti num " + elementiContatto.getLength());
//
//		//creo l' arraylist nel quale andrò a salvare i miei dati della rubrica
//		//List<Contatto> contatti = new ArrayList<Contatto>();
//		for (int i = 0; i < elementiContatto.getLength(); i++) {
//			Contatto contatto = new Contatto();
//			Element c = (Element)elementiContatto.item(i);
//			NodeList valori = c.getChildNodes();
//			//System.out.println(valori.getLength());
//			for (int j = 0; j < valori.getLength(); j++) {
//				Node n = valori.item(j);
//				if (n instanceof Element) {
//					Element valore = (Element) n;
//					System.out.println(valore.getTagName() + " : " + valore.getTextContent());
//					switch (valore.getTagName()) {
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
//				}
//			}
//			System.out.println("------------------------------------------");
//			contattiXml.add(contatto);
//		}
//
//		System.out.println("contatti size : " + contattiXml.size());
//	}
//
//	public void aggiungiInRubricaXml() throws IOException {
//		Scanner scanner = new Scanner(System.in);
//		String parola="";
//		while(!(parola.equalsIgnoreCase("no"))) {
//			String name="";
//			String surname="";
//			String telephone="";
//			String email="";
//			while(name.equals("") && surname.equals("") && telephone.equals("") && email.equals("")) {
//				System.out.println("Inserisci Nome");
//				name = scanner.nextLine();
//				System.out.println("Inserisci Cognome");
//				surname = scanner.nextLine();
//				System.out.println("Inserisci Telefono");
//				telephone = scanner.nextLine();
//				System.out.println("Inserisci Mail");
//				email = scanner.nextLine();
//				aggiungiContattoInRubricaXml(new Contatto(name,surname,telephone,email));
//			}
//			if(email.equals("")) {
//				email += " ";
//			}
//			System.out.println("Vuoi proseguire? IN CASO CONTRARIO DIGITA 'NO' ");
//			parola = scanner.nextLine();
//			System.out.println("------------------------------------------");
//		}
//		System.out.println("Ora che sei uscito dall' inserimento, vuoi salvare i dati aggiunti?");
//		parola = scanner.nextLine();
//		if(parola.equalsIgnoreCase("si")){
//			try {
//				write();
//			} catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
//				e.printStackTrace();
//			}
//		}
//	}
//
//	public void aggiungiContattoInRubricaXml(Contatto cont) {
//		contattiXml.add(cont);
//	}
//
//	public static void write() throws ParserConfigurationException, IOException, SAXException, TransformerException {
//		//Contatto contatto = new Contatto();
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder builder = factory.newDocumentBuilder();
//
//		Document document = builder.newDocument();
//		Element utenti = document.createElement("utenti");
//		document.appendChild(utenti);
//
//		//...
//		Element utente = null;
//		Element name = null;
//		Element surname = null;
//		Element phone = null;
//		Element email = null;
//
//		for (int i = 0; i < contattiXml.size(); i++) {
//			utente = document.createElement("utente");
//			utente.setAttribute("id", Integer.toString(i));//meglio che i + ""
//			name = document.createElement("nome");
//			name.setTextContent(contattiXml.get(i).getName());
//			surname = document.createElement("cognome");
//			surname.setTextContent(contattiXml.get(i).getSurname());
//			phone = document.createElement("telefono");
//			phone.setTextContent(contattiXml.get(i).getTelephone());
//			email = document.createElement("email");
//			email.setTextContent(contattiXml.get(i).getEmail());
//			utente.appendChild(name);
//			utente.appendChild(surname);
//			utente.appendChild(phone);
//			utente.appendChild(email);
//			utenti.appendChild(utente);
//		}
//
//		System.out.println(utenti.getChildNodes().getLength());
//		// write the content into xml file
//		TransformerFactory transformerFactory = TransformerFactory.newInstance();
//		Transformer transformer = transformerFactory.newTransformer();
//		DOMSource source = new DOMSource(document);
//		StreamResult result = new StreamResult(new File("/Users/Padawan08/Desktop/rubrica.xml"));
//		// Output to console for testing
//		StreamResult syso = new StreamResult(System.out);
//		transformer.transform(source, result);
//		transformer.transform(source, syso);
//		System.out.println("File saved!");
//	}
//
//	public static void importaCsvinXml(ArrayList<Contatto> arr) throws ParserConfigurationException, IOException, SAXException, TransformerException {
//		Contatto contatto = new Contatto();
//		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		DocumentBuilder builder = factory.newDocumentBuilder();
//
//		Document document = builder.newDocument();
//		Element utenti = document.createElement("utenti");
//		document.appendChild(utenti);
//
//		//...
//		Element utente = null;
//		Element name = null;
//		Element surname = null;
//		Element phone = null;
//		Element email = null;
//
//		for (int i = 0; i < arr.size(); i++) {
//			utente = document.createElement("utente");
//			utente.setAttribute("id", Integer.toString(i));//meglio che i + ""
//			name = document.createElement("nome");
//			name.setTextContent(arr.get(i).getName());
//			surname = document.createElement("cognome");
//			surname.setTextContent(arr.get(i).getSurname());
//			phone = document.createElement("telefono");
//			phone.setTextContent(arr.get(i).getTelephone());
//			email = document.createElement("email");
//			email.setTextContent(arr.get(i).getEmail());
//			utente.appendChild(name);
//			utente.appendChild(surname);
//			utente.appendChild(phone);
//			utente.appendChild(email);
//
//			utenti.appendChild(utente);
//		}
//
//		// write the content into xml file
//		TransformerFactory transformerFactory = TransformerFactory.newInstance();
//		Transformer transformer = transformerFactory.newTransformer();
//		DOMSource source = new DOMSource(document);
//		StreamResult result = new StreamResult(new File("/Users/Padawan08/Desktop/esportatoDaCsv.xml"));
//
//		//StreamResult result = new StreamResult(new File("/Users/Padawan08/Desktop/rubrica.xml"));
//
//		// Output to console for testing
//		StreamResult syso = new StreamResult(System.out);
//
//		transformer.transform(source, result);
//		transformer.transform(source, syso);
//
//		System.out.println("File esportato correttamente!");
//	}
//
//	public void eliminaContattoRubricaXml() {
//		Scanner scanner = new Scanner(System.in);
//		for(int i=0;i<contattiXml.size();i++) {
//			System.out.println(i +") "+ contattiXml.get(i).toString());
//		}
//		System.out.println("Quale contatto vuoi cancellare? INSERISCI LA SUA POSIZIONE");
//		String indice = scanner.nextLine();
//		Integer ind = Integer.valueOf(indice);
//		contattiXml.remove(contattiXml.get(ind));
//		System.out.println("Il contatto selezionato è stato rimosso");
//		System.out.println("Ora che hai effettuato l' eliminazione, vuoi salvare i dati aggiunti?");
//		//		for(int i=0;i<contattiXml.size();i++) {
//		//			System.out.println(i +") "+ contattiXml.get(i).toString());
//		//		}
//		String	parola = scanner.nextLine();
//		if(parola.equalsIgnoreCase("si")){
//			try {
//				write();
//			} catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
//				e.printStackTrace();
//			}
//			System.out.println("------------------------------------------");
//		}
//	}
//
//	public void modificaRubricaXml() {
//		Scanner scanner = new Scanner(System.in);
//		for(int i=0;i<contattiXml.size();i++) {
//			System.out.println(i +") "+ contattiXml.get(i).toString());
//		}
//		System.out.println("Quale contatto vuoi modificare? INSERISCI LA SUA POSIZIONE");
//		String indice = scanner.nextLine();
//		Integer ind = Integer.parseInt(indice);
//
//		String name="";
//		String surname="";
//		String telephone="";
//		String email="";
//		String parola="";
//
//		System.out.println("Inserisci Nome");
//		name = scanner.nextLine();
//		contattiXml.get(ind).setName(name);
//		System.out.println("Inserisci Cognome");
//		surname = scanner.nextLine();
//		contattiXml.get(ind).setSurname(surname);
//		System.out.println("Inserisci Telefono");
//		telephone = scanner.nextLine();
//		contattiXml.get(ind).setTelephone(telephone);
//		System.out.println("Inserisci Mail");
//		email = scanner.nextLine();
//		contattiXml.get(ind).setEmail(email);
//		System.out.println("Il nuovo contatto aggiunto e' " + contattiXml.get(ind).toString());
//		System.out.println("Ora che hai effettuato la modifica, vuoi salvare i dati aggiunti?");
//		parola = scanner.nextLine();
//		if(parola.equalsIgnoreCase("si")){
//			try {
//				write();
//			} catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
//				e.printStackTrace();
//			}
//		}
//		System.out.println("------------------------------------------");
//	}
//
//	public void ricercaContattoXml(){
//		Scanner scanner = new Scanner(System.in);
//		System.out.println("Vuoi cercare un contatto per email o per numero telefonico?");
//		System.out.println("Inserisci la parola 'email' oppure 'telefono'");
//		String ricerca = scanner.nextLine();
//		if(ricerca.equalsIgnoreCase("email")) {
//			System.out.println("Inserisci l' indirizzo 'email' ");
//			String em= scanner.nextLine();
//			for(int i=0;i<contattiXml.size();i++) {
//				if(contattiXml.get(i).getEmail().equals(em)) {
//					System.out.println(contattiXml.get(i).toString());
//					// break perchè tanto al massimo trova una persona con quella mail se ottimizzato... no mail duplicabili, ma univoche.
//					break;
//				}
//			}
//			System.out.println("------------------------------------------");
//			//System.out.println("Nessun contatto con questa email in rubrica");
//		}
//		else if (ricerca.equalsIgnoreCase("telefono")){
//			System.out.println("Inserisci il recapito telefonico");
//			String tel= scanner.nextLine();
//			for(int i=0;i<contattiXml.size();i++) {
//				if(contattiXml.get(i).getTelephone().equals(tel)) {
//					System.out.println(contattiXml.get(i).toString());
//					break;
//				}
//			}
//			System.out.println("------------------------------------------");
//		}
//		
//		else {
//			System.out.println("DEVI PER FORZA RICERCARE PER EMAIL O TELEFONO.");
//		}
//	}
//
//	public void esportaDaXmlInCsv() {
//		CsvManager csvm = new CsvManager("/Users/Padawan08/Desktop/esportatoDaXmlInCsv.csv");
//		try {
//			csvm.ImportaContattiDaXml_InCsv(contattiXml);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//	}
//
//	public void esportaDaXmlInDb() {
//		JDBCmanager esp = new JDBCmanager();
//		esp.ImportaContattiDaXml_InDb(contattiXml);
//	}
//}