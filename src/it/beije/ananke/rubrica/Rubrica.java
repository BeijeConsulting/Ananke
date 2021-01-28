package it.beije.ananke.rubrica;


import java.util.*;
import java.io.*;
import java.sql.*;

public class Rubrica {

	public List<Contatti> r = new ArrayList<Contatti>();
	public String path;
	
	public Rubrica(String path) {
		this.path = path;
		try {
			if(path.substring(path.length()-4,path.length()).equals(".xml")) {
				
				this.r = XMLManager.readFile(path);
			}else if(path.substring(path.length()-4,path.length()).equals(".csv")) {
			this.r = CSVManager.readCSV(path);
			}
		} catch (Exception e) {
			
			
		}
	}
	

	
	public void writeFile(File f)  {
		
		try {
			FileWriter fw= new FileWriter(f);
			if(!f.exists()) {
				fw.write("NOME,COGNOME,EMAIL,CELLULARE");
				fw.write("\n");
			}
		
		
		for(Contatti c: this.r) {
			
			fw.write(c.toString());
			fw.write("\n");
			
		}
		fw.close();
		}catch(IOException e) {
			System.out.println("Errore in scrittura");
		}
	}
	
	
	public  List<Contatti> readFile(String path) throws IOException {
		File f = new File(path);
		List<String> row = new ArrayList<>();
		List<Contatti> rubrica = new ArrayList<>();
		
		if(!f.exists()) {
		writeFile(f);
		return rubrica;
		} else {
			BufferedReader br = new BufferedReader(new FileReader(f));
			while(br.ready()) {
				row.add(br.readLine());
			}
			for(String r : row) {
				String[]rs = r.split(",");
				rubrica.add(new Contatti(rs[0],rs[1],rs[2],rs[3]));
			}
			return rubrica;
		}
	}
	
	public boolean addContact() {
		String n,s,e,phone;
		Scanner sc = new Scanner(System.in);
		System.out.println("Inserisci nome: ");
		 n =sc.nextLine();
		System.out.println("Inserisci cognome: ");
		 s = sc.nextLine();
		 
		 System.out.println("Inserisci email: ");
		 e = sc.nextLine();
		 
		
		System.out.println("Inserisci cellulare: ");
		
		phone = sc.nextLine();
		
		
		if(!(n.isEmpty() && s.isEmpty() && e.isEmpty() && phone.isEmpty())) {
		this.r.add(new Contatti(n,s,e,phone));
		return true;
		} else {
		System.out.println("Mi dispiace, l'utente deve contenere almeno un campo.");
		return false;
		}
		
	}
	
	public List<Contatti> searchContact(String campo) {
		List<Contatti> rubrica = new ArrayList<>();
		for(Contatti c: this.r) {
			if(c.searchProperties(campo)) {
				rubrica.add(c);
			}
		}
		return rubrica;
	}
	
	public void deleteContact(String email) {
		int i=0;
		Iterator<Contatti> it = this.r.iterator();
		while(it.hasNext()) {
			Contatti c = it.next();
			if(c.email.equals(email)) {
				i++;
				
			}
		}
		if(i == 0) {
		System.out.println("Mi dispiace, ma il contatto non è presente in lista");
		} else {
			System.out.println("Il contatto è stato eliminato");
			writeFile(new File(this.path));
		}
		
		
	}
	
	

}
