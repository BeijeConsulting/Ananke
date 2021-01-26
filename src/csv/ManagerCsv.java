package csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
public class ManagerCsv {
	
	private List<Contatto> contatti;
	File file;
	public ManagerCsv() throws IOException {
		
		 file= new File("rubrica.csv");
		
		
		contatti=new ArrayList<>();
	if(file.exists()) {
		contatti=readCsv("rubrica.csv");
	}else {
		creazione(file);
		
		
	}
		
	}
	
	public void creazione(File file) throws IOException {
		FileWriter fileWriter = new FileWriter(file);
		fileWriter.close();
		
	}
	 public void aggiungiContatto(Contatto c) {
		 contatti.add(c);
		 
	 }
	
	 public void salvaContatti() throws IOException {
		 FileWriter fileWriter = new FileWriter(file);
		 fileWriter.write("Nome;Cognome;Email;Tel;\n");
		 for(Contatto c:contatti) {
			fileWriter.write(c.toString()+"\n");
			 
		 }
		 fileWriter.close();
	 }
	
	 public void salvaListaContatti(ArrayList<Contatto> x, String nome) throws IOException {
		 File	 file= new File(nome);
		 FileWriter fileWriter = new FileWriter(file);
		 fileWriter.write("Nome;Cognome;Email;Tel;\n");
		 for(Contatto c:x) {
			fileWriter.write(c.toString()+"\n");
			 
		 }
		 fileWriter.close();
	 }
	 
	 public ArrayList<Contatto> readCsv(String nome) throws IOException {
		 ArrayList<Contatto> result=new ArrayList<>();
	File	 file= new File(nome);
		 FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			List<String> rows = new ArrayList<String>();
			bufferedReader.readLine();
			while (bufferedReader.ready()) {
//				System.out.println(r++ + " : " + bufferedReader.readLine());
				rows.add(bufferedReader.readLine());
			}
				bufferedReader.close();
				fileReader.close();
				for (String row : rows) {
				String[] rs = row.split(";");
					result.add(new Contatto(rs[0],rs[1],rs[3],rs[2]));
					
					
				
			}
			
			return result;
	 }
	
	 public void stampaDaFile(String nome) throws IOException {
		 ArrayList<Contatto> result=new ArrayList<>();
	File	 file= new File(nome);
		 FileReader fileReader = new FileReader(file);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			List<String> rows = new ArrayList<String>();
			System.out.println(bufferedReader.readLine());
			while (bufferedReader.ready()) {
//				System.out.println(r++ + " : " + bufferedReader.readLine());
				rows.add(bufferedReader.readLine());
			}
				bufferedReader.close();
				fileReader.close();
				for (String row : rows) {
				String[] rs = row.split(";");
					new Contatto(rs[0],rs[1],rs[3],rs[2]).toString();
					
					
				
			}
			
	 }
	 
	
	

}
