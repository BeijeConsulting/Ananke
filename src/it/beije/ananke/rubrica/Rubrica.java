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

public class Rubrica {
	private ArrayList<Contatto> contatti = new ArrayList<Contatto>();
	private File file;

	public Rubrica(String filePath) {
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
		Scanner scanner = new Scanner(System.in);
		String name="";
		String surname="";
		String telephone="";
		String email="";
		String parola="";
		while(!(parola.equalsIgnoreCase("si"))) {
			System.out.println("Inserisci Nome");
			name = scanner.nextLine();
			System.out.println("Inserisci Cognome");
			surname = scanner.nextLine();
			System.out.println("Inserisci Telefono");
			telephone = scanner.nextLine();
			System.out.println("Inserisci Mail");
			email = scanner.nextLine();
			aggiungiContattoInRubrica(new Contatto(name,surname,telephone,email));
			System.out.println("Vuoi proseguire?");
			parola = scanner.nextLine();
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
			}
			fileWriter.flush();
			fileWriter.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
