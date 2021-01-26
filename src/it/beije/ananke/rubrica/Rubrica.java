package it.beije.ananke.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class Rubrica {
	
	List<Contatto> list;

	public void aggiungiContattoSulFile(Contatto c) throws Exception {
		
			this.aggiungiContatto(c);
			this.salvaRubricaSuFile();
	}

	private void salvaRubricaSuFile() throws Exception {
		
		File f1 = new File("C:\\Users\\Padawan06\\Desktop\\rubrica.csv");
		FileWriter fw = new FileWriter(f1);
		String s;
		
		for(Contatto c : list) {
			s=c.getNome() + ";" + c.getCognome() + ";" + c.getNumeoroTel() + ";" + c.getMail();
			fw.write(s);
		}
		
		fw.close();
		
	}

	
	public void aggiungiContatto(Contatto c) {
		list.add(c);
	}

	
	public void visualizzaRubrica() {
		System.out.println("-----------------Rubrica-----------------");
		
		for(Contatto c:list) {
			System.out.println(c.getNome() + ";" + c.getCognome() + ";" + c.getNumeoroTel() + ";" + c.getMail());
		}
		
		System.out.println("-----------------------------------------");
		
	}
	
}

/* File f1 = new File("C:\\Users\\Padawan06\\Desktop\\temp.csv");
FileWriter fw = new FileWriter(f1);
File f2 = new File("C:\\Users\\Padawan06\\Desktop\\Rubrica.csv");
FileReader fr = new FileReader(f2);
BufferedReader br = new BufferedReader(fr);		
String s; */

//String[] contatto;
	
/*	while (br.ready()) {
	 	s =	br.readLine();
		fw.write(s);
	}
	fw.write(c.getNome() + ";" + c.getCognome() + ";" + c.getNumeoroTel() + ";" + c.getMail()); */



