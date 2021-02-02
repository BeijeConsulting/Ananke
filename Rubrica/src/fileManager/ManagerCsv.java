package fileManager;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.*;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import database.JDBCManager;
import entità.Contatto;

public class ManagerCsv {
	
	private ArrayList<Contatto> contatti;
	
	JDBCManager managerJDBC = new JDBCManager();
	
	File file;
	
	public ManagerCsv() throws IOException {
		
		file = new File("/Users/davidepersico/Desktop/Beije/Ananke/rubrica.csv");
		
		/*
		System.out.println("file : " + file.getAbsolutePath());
		System.out.println("file exists ? " + file.exists());
		System.out.println("is a dir ?" + file.isDirectory());
		System.out.println("");
		*/
		
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
	 
	 public ArrayList<Contatto> leggiCsv()   {
		 
		 ArrayList<Contatto> result=new ArrayList<>();
		 
		 FileReader fileReader;
		 
		try {
			fileReader = new FileReader(file);
		
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			List<String> rows = new ArrayList<String>();
			
			try {
				bufferedReader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				while (bufferedReader.ready()) {
					rows.add(bufferedReader.readLine());
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
				try {
					bufferedReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				try {
					fileReader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				for (String row : rows) {
				String[] rs = row.split(";");
					result.add(new Contatto(rs[0],rs[1],rs[3],rs[2]));		
				
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
			
			return result;
	 }
	 
	 public void daCsvInDB() throws SQLException {

			ArrayList<Contatto> contatti = leggiCsv();
			
				for(Contatto tempContatto: contatti) {
					
					managerJDBC.inserisciContattoDb(tempContatto);
					
				}
			
			System.out.println("I contatti presenti nella rubrica csv sono stati inseriti nel Database");

	 }
	 
	 public void daDBinCsv() throws SQLException, IOException{
			
			ArrayList<Contatto> contatti = managerJDBC.listaContattiDb();
			
			if (contatti.size() != 0) {
				System.out.println("I contatti presenti nel DB sono stati esportati sul file csv");
			}
			
			salva(contatti);
			
		}
}