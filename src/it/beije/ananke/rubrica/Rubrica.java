package it.beije.ananke.rubrica;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rubrica {
		
	String filePath;
	List<Contatto> contatti = new ArrayList();
	
	public static void main(String[] args) throws IOException {
		Rubrica rubrica = new Rubrica();
		System.out.println("Seleziona l'ope");
		//rubrica.aggiungiContatto();
		rubrica.listaContatti();
		rubrica.creaCsv();
	}
	
	// C://Users//Padawan09//Desktop//rubrica.txt
	//C://Users//Padawan09//Desktop//rubrica2.txt
	
	public void aggiungiContatto() throws IOException {
		Scanner sc = new Scanner(System.in);
		//FileWriter fw = new FileWriter(file);
		
		String header = "nome;cognome;email;telefono";
		Contatto contatto = new Contatto();
		boolean stop = false;	
		
		System.out.println("Inserire il file");
		filePath = sc.next();
		
		while (!stop) {
		
			System.out.println("Inserire il nome");
			contatto.setNome(sc.next()); 
			
			System.out.println("Inserire il cognome");
			contatto.setCognome(sc.next());
			
			System.out.println("Inserire il email");
			contatto.setEmail(sc.next()); 
			
			System.out.println("Inserire il telefono");
			contatto.setTelefono(sc.next());
			
			//fw.append(contatto.toString());
			Files.write(Paths.get(filePath), contatto.toString().getBytes(),
					StandardOpenOption.APPEND);
			
			System.out.println("Continuare? (s/n)");
			if(sc.next().equalsIgnoreCase("n")) {
				stop = true;
			}else {
				Files.write(Paths.get(filePath), "\n".getBytes(),
						StandardOpenOption.APPEND);
			}
			
		}
		//fw.close();
		sc.close();
	}
	
	public void listaContatti() throws IOException {
		Scanner sc = new Scanner(System.in);

		System.out.println("Inserire il file da cui leggere");
		filePath = sc.next();
		
		File file = new File(filePath);
		
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		
		String[] dati = new String[4];
		
		while(br.ready()) {
			dati = br.readLine().split(";");
			Contatto contatto = new Contatto();
			contatto.setNome(dati[0]);
			contatto.setCognome(dati[1]);
			contatto.setEmail(dati[2]);
			contatto.setTelefono(dati[3]);
			contatti.add(contatto);
		}
		
		System.out.println(contatti.toString());
	}
	
	public void creaCsv() throws IOException {
		Scanner sc = new Scanner(System.in);

		System.out.println("Inserire il file su cui scrivere");
		filePath = sc.next();
		
		File file = new File(filePath);
		FileWriter fw = new FileWriter(file);
		
		for(Contatto contatto : contatti) {
			fw.write(contatto.toString() + "\n");
		}
		
		fw.close();
	}
	

}
