package it.beije.ananke.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class CsvManager {
	private ArrayList<Contatto> contatti = new ArrayList<Contatto>();
	private File file;
	Scanner scanner = new Scanner(System.in);

	public CsvManager(String filePath) {
		file = new File(filePath);
		if (file.exists()) {
			System.out.println("Il file " + file + " esiste già");
			try {
				leggiRubrica(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else {
			try {
				FileWriter fileWriter = new FileWriter(file);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public void leggiRubrica(File file) throws IOException {
		ArrayList<String> listaDaLeggere = new ArrayList<String>();
		FileReader fileReader = new FileReader(file);	
		BufferedReader bufferedReader = new BufferedReader(fileReader);
		while(bufferedReader.ready()){
			listaDaLeggere.add(bufferedReader.readLine());
		}
		bufferedReader.close();
		fileReader.close();

		for (String str : listaDaLeggere) {
			String[] rs = str.split(";");
			aggiungiContattoInRubrica(new Contatto(rs[0], rs[1], rs[2], rs[3]));
		}
	}

	public void aggiungiInRubrica() throws IOException {
		String parola="";
		while(!(parola.equalsIgnoreCase("no"))) {
			String name="";
			String surname="";
			String telephone="";
			String email="";

			while(name.equals("") && surname.equals("") && telephone.equals("") && email.equals("")) {
				System.out.println("NON PUOI INSERIRE TUTTI I CAMPI VUOTI PER UN UTENTE.");
				System.out.println("Inserisci Nome");
				name = scanner.nextLine();
				System.out.println("Inserisci Cognome");
				surname = scanner.nextLine();
				System.out.println("Inserisci Telefono");
				telephone = scanner.nextLine();
				System.out.println("Inserisci Mail");
				email = scanner.nextLine();
			}
			if(email.equals("")) {
				email += " ";
			}
			aggiungiContattoInRubrica(new Contatto(name,surname,telephone,email));
			System.out.println("Vuoi proseguire? IN CASO CONTRARIO DIGITA 'NO' ");
			parola = scanner.nextLine();
		}
		System.out.println("Ora che sei uscito dall ' inserimento, vuoi salvare i dati aggiunti?");
		parola = scanner.nextLine();
		if(parola.equalsIgnoreCase("si")){
			aggiornaRubrica();
		}
	}
	public void aggiungiContattoInRubrica(Contatto cont) {
		contatti.add(cont);
	}

	public void aggiornaRubrica() {
		try{
			FileWriter fileWriter = new FileWriter(file);
			for (Contatto contatto : contatti) {
				fileWriter.write(contatto.toString());
				System.out.println(contatto);
			}
			fileWriter.flush();
			fileWriter.close();
			System.out.println("File .csv salvato");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}

	public void modificaRubrica() {
		for(int i=0;i<contatti.size();i++) {
			System.out.println(i +") "+ contatti.get(i).toString());
		}
		System.out.println("Quale contatto vuoi modificare? INSERISCI LA SUA POSIZIONE");
		String indice = scanner.nextLine();
		Integer ind = Integer.parseInt(indice);

		String name="";
		String surname="";
		String telephone="";
		String email="";
		String parola="";

		System.out.println("Inserisci Nome");
		name = scanner.nextLine();
		contatti.get(ind).setName(name);
		System.out.println("Inserisci Cognome");
		surname = scanner.nextLine();
		contatti.get(ind).setSurname(surname);
		System.out.println("Inserisci Telefono");
		telephone = scanner.nextLine();
		contatti.get(ind).setTelephone(telephone);
		System.out.println("Inserisci Mail");
		email = scanner.nextLine();
		contatti.get(ind).setEmail(email);

		System.out.println("Il nuovo contatto aggiunto e' " + contatti.get(ind).toString());
		System.out.println("Ora che hai effettuato la modifica, vuoi salvare i dati aggiunti?");
		parola = scanner.nextLine();
		if(parola.equalsIgnoreCase("si")){
			aggiornaRubrica();
		}
	}

	public void eliminaContattoRubrica() {
		for(int i=0;i<contatti.size();i++) {
			System.out.println(i +") "+ contatti.get(i).toString());
		}
		System.out.println("Quale contatto vuoi cancellare? INSERISCI LA SUA POSIZIONE");
		String indice = scanner.nextLine();
		Integer ind = Integer.valueOf(indice);
		contatti.remove(contatti.get(ind));
		System.out.println("Il contatto selezionato è stato rimosso");
		System.out.println("Ora che hai effettuato l' eliminazione, vuoi salvare i dati aggiunti?");
		System.out.println("------------------------------------------");
//		for(int i=0;i<contatti.size();i++) {
//			System.out.println(i +") "+ contatti.get(i).toString());
//		}
		String	parola = scanner.nextLine();
		if(parola.equalsIgnoreCase("si")){
			aggiornaRubrica();
		}
	}

	public void ricercaContatto(){

		System.out.println("Vuoi cercare un contatto per email o per numero telefonico?");
		System.out.println("Inserisci la parola 'email' oppure 'telefono'");
		String ricerca = scanner.nextLine();
		if(ricerca.equalsIgnoreCase("email")) {
			System.out.println("Inserisci l' indirizzo 'email' ");
			String em= scanner.nextLine();
			for(int i=0;i<contatti.size();i++) {
				if(contatti.get(i).getEmail().equals(em)) {
					System.out.println(contatti.get(i).toString());
					break;
				}
			}
		}
		else if (ricerca.equalsIgnoreCase("telefono")){
			System.out.println("Inserisci il recapito telefonico");
			String tel= scanner.nextLine();
			for(int i=0;i<contatti.size();i++) {
				if(contatti.get(i).getTelephone().equals(tel)) {
					System.out.println(contatti.get(i).toString());
					break;
				}
			}
			System.out.println("Nessun contatto con questo telefono in rubrica");
		}
		else {
			System.out.println("DEVI PER FORZA RICERCAREPER EMAIL O TELEFONO.");
		}
		System.out.println("------------------------------------------");
	}
	public void esportaDaCsvInXml() {
		XmlManager xm = new XmlManager("/Users/Padawan08/Desktop/esportatoDaCsv.xml");
		try {
			xm.importaCsvinXml(contatti);
		} catch (ParserConfigurationException | IOException | SAXException | TransformerException e) {
			e.printStackTrace();
		}
	}
	
	public void esportaDaCsvInDb() {
		JDBCmanager esp = new JDBCmanager();
		esp.ImportaContattiDaCsv_InDb(contatti);
	}

	public void importaRubricaDaDB(ArrayList<Contatto> arr) throws IOException {
		ArrayList<String> listaDaLeggere = new ArrayList<String>();
		
		for (Contatto str : arr) {
			String st = str.toString();
			//System.out.println(st);
			//System.out.println("--------------------");
			String[] rs = st.split(";");
			aggiungiContattoInRubrica(new Contatto(rs[0], rs[1], rs[2], rs[3]));
		}
		System.out.println("Vuoi salvare il file esportato dal db in csv?");
		System.out.println("in caso affermativo digita 'si', se vuoi salvarlo in xml, digita'xml'");
		String formatoDaSalvare = scanner.nextLine();
		if(formatoDaSalvare.equalsIgnoreCase("si")) {
			aggiornaRubrica();
		}
		else if (formatoDaSalvare.equalsIgnoreCase("xml")){
			esportaDaCsvInXml();
		}
		else {
			System.out.println("ERRORE");
		}
	}
	
	public void ImportaContattiDaXml_InCsv(ArrayList<Contatto> arr) throws IOException {
		for (Contatto str : arr) {
			String st = str.toString();
			String[] rs = st.split(";");
			aggiungiContattoInRubrica(new Contatto(rs[0], rs[1], rs[2], rs[3]));
		}
		System.out.println("Vuoi salvare il file esportato dal XML in csv?");
		String formatoDaSalvare = scanner.nextLine();
		if(formatoDaSalvare.equalsIgnoreCase("si")) {
			aggiornaRubricaImportXml(arr);
		}
		else {
			System.out.println("File non salvato");
		}
	}

	public void aggiornaRubricaImportXml(ArrayList<Contatto> arr) {
		try{
			FileWriter fileWriter = new FileWriter(file);
			for (Contatto contatto : arr) {
				fileWriter.write(contatto.toString());
			}
			fileWriter.flush();
			fileWriter.close();
			System.out.println("File .csv salvato");
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}