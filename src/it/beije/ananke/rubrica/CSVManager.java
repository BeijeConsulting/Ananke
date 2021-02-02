package it.beije.ananke.rubrica;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class CSVManager {

public CSVManager() {
		
	}

public static List<Contatti> readCSV(String path) throws IOException {
	File f = new File(path);
	List<String> row = new ArrayList<>();
	List<Contatti> rubrica = new ArrayList<>();
	
	if(!f.exists()) {
	initCSV(path);
	return rubrica;
	} else {
		BufferedReader br = new BufferedReader(new FileReader(f));
		while(br.ready()) {
			row.add(br.readLine());
		}
		row.remove(0);
		for(String r : row) {
			String[]rs = r.split(",");
			Contatti c = new Contatti();
			c.setName(rs[0]);
			c.setSurname(rs[1]);
			c.setEmail(rs[2]);
			c.setTelephone(rs[3]);
			rubrica.add(c);
			
		}
		return rubrica;
	}
}
public static void initCSV(String path) {
	File f = new File(path);
	
	try {
		FileWriter fw= new FileWriter(f);
		fw.write("NOME,COGNOME,EMAIL,CELLULARE");
		fw.write("\n");
		fw.close();
	} catch (IOException e) {
		
		e.printStackTrace();
	}
}
public static void writeCSV(List<Contatti> list, String s)  {
	
	File f = new File(s);
	try {
		FileWriter fw= new FileWriter(f);
		
		initCSV(s);
	
	
	for(Contatti c: list) {
		
		fw.write(c.toString());
		fw.write("\n");
		
	}
	fw.close();
	}catch(IOException e) {
		System.out.println("Errore in scrittura");
	}
}


	
	
}
