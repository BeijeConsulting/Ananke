import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

public class ManagerCsv {
	
	private ArrayList<Contatto> contatti;
	
	File file;
	
	public ManagerCsv() throws IOException {
		
		file = new File("/Users/davidepersico/Desktop/Beije/Ananke/rubrica.csv");
		
		System.out.println("file : " + file.getAbsolutePath());
		System.out.println("file exists ? " + file.exists());
		System.out.println("is a dir ?" + file.isDirectory());
		System.out.println("");
		
		contatti = new ArrayList<>();
		
		if(file.exists()) {
			
			FileReader fileReader = new FileReader(file);
			
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			List<String> rows = new ArrayList<String>();
			
			bufferedReader.readLine();
			
		while (bufferedReader.ready()) {
			
			rows.add(bufferedReader.readLine());
			
		}
		
			bufferedReader.close();
			
			fileReader.close();
			
			for (String row : rows) {
				
			String[] rs = row.split(";");
			aggiungiContatto(new Contatto(rs[0],rs[1],rs[3],rs[2]));
				
		}
		
	}else {
		
		nuovoFile(file);
		
	}
		
	}
	
	public void nuovoFile(File file) throws IOException {
		
		System.out.println("new file : " + file.getAbsolutePath());
		System.out.println("new file exists ? " + file.exists());
		System.out.println("");
		
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.close();
		
	}
	
	 public void aggiungiContatto(Contatto contatto) throws IOException {
		 
		 contatti.add(contatto);
		 
		 salva(contatti);
		 
	 }
	 
	 public void salva(ArrayList<Contatto> contatti) throws IOException {
		 
		 FileWriter fileWriter = new FileWriter(file);
		 fileWriter.write("Nome;Cognome;Email;Tel;\n");
		 
		 for(Contatto contatto:contatti) {
			 
			fileWriter.write(contatto.toString()+"\n");
			 
		 }
		 
		 fileWriter.close();
		 
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
		 
		 salva(contatti);
		 
	 }
	 
	 public void modificaCognome(Contatto tempContatto, String cognome) throws ParserConfigurationException, IOException, SAXException, TransformerException {
		 
		 tempContatto.setSurname(cognome);
		 
		 salva(contatti);
		 
	 }
	 
	 public void modificaTelefono(Contatto tempContatto, String telefono) throws ParserConfigurationException, IOException, SAXException, TransformerException {
		 
		 tempContatto.setTelephone(telefono);
		 
		 salva(contatti);
		 
	 }
	 
	 public void modificaEmail(Contatto tempContatto, String email) throws ParserConfigurationException, IOException, SAXException, TransformerException {
		 
		 tempContatto.setTelephone(email);
		 
		 salva(contatti);
		 
	 }
	 
	 public void elimina(String nome, String cognome) throws IOException {
		 
		 	Contatto tempContatto = null;
		 	
		 	for (Contatto contatto : contatti) {
	            if (contatto.getName().equalsIgnoreCase(nome) && contatto.getSurname().equalsIgnoreCase(cognome)) {
	                tempContatto = contatto;
	            }
	        }
		 	
		 	if( tempContatto != null) {
		 		
		 		contatti.remove(tempContatto);
		 		
		 		System.out.println("Il contatto ");
		 		System.out.println("");
		        System.out.println("Nome: " + tempContatto.getName());
		        System.out.println("Cognome: " + tempContatto.getSurname());
		        System.out.println("Email: " + tempContatto.getEmail());
		        System.out.println("Numero di telefono: " + tempContatto.getTelephone());
		        System.out.println("");
		        System.out.println("E' stato eliminato correttamente");
		        
		        this.salva(contatti);
		 		
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