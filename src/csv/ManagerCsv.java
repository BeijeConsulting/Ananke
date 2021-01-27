package csv;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
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
	
	public void creazione(File file)  {
		FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
		
		fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 public void aggiungiContatto(Contatto c) {
		 contatti.add(c);
		 
	 }
	
	 public void salvaContatti()  {
		 FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
		
		 fileWriter.write("Nome;Cognome;Email;Tel;\n");
		 for(Contatto c:contatti) {
			fileWriter.write(c.toString()+"\n");
			 
		 }
		 fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	
	 public void salvaListaContatti(ArrayList<Contatto> x, String nome)  {
		// ordinaLista();
		 File	 file= new File(nome);
		
		 FileWriter fileWriter;
		try {
			fileWriter = new FileWriter(file);
		
		 fileWriter.write("Nome;Cognome;Email;Tel;\n");
		 for(Contatto c:x) {
			fileWriter.write(c.toString()+"\n");
			 
		 }
		 fileWriter.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
	 
	 public ArrayList<Contatto> readCsv(String nome)   {
		 ArrayList<Contatto> result=new ArrayList<>();
	File file= new File(nome);
		 FileReader fileReader;
		try {
			fileReader = new FileReader(file);
		
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			List<String> rows = new ArrayList<String>();
			try {
				bufferedReader.readLine();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while (bufferedReader.ready()) {
//				System.out.println(r++ + " : " + bufferedReader.readLine());
					rows.add(bufferedReader.readLine());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					fileReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (String row : rows) {
				String[] rs = row.split(";");
					result.add(new Contatto(rs[0],rs[1],rs[3],rs[2]));
					
					
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
			return result;
	 }
	
	 public void stampaDaFile(String nome)  {
		 ArrayList<Contatto> result=new ArrayList<>();
	File	 file= new File(nome);
		 FileReader fileReader;
		try {
			fileReader = new FileReader(file);
		
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			List<String> rows = new ArrayList<String>();
			try {
				System.out.println(bufferedReader.readLine());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				while (bufferedReader.ready()) {
//				System.out.println(r++ + " : " + bufferedReader.readLine());
					rows.add(bufferedReader.readLine());
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				try {
					bufferedReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					fileReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				for (String row : rows) {
				String[] rs = row.split(";");
					new Contatto(rs[0],rs[1],rs[3],rs[2]).toString();
					
					
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("File non trovato ");
			e.printStackTrace();
		}
	 }
	 
	/*
	public void ordinaLista() {
	contatti.sort(null);	
	
		
	}
*/

}
