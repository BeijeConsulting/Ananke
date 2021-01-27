package it.beije.ananke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class ProvaFile {
	static File secondoFile = new File("/users/Padawan08/Desktop/PC marco/INSUBRIA/4^ anno/1^ semestre/ENGLISH/Orale");

	public static void main(String[] args) throws Exception{
		File file = new File("/Users/Padawan08/Documents/provafile.txt");
		System.out.println("file : " + file.getAbsolutePath());
		System.out.println("file exists ? " + file.exists());
		System.out.println("is a dir ? " + file.isDirectory());
		FileReader fileReader = new FileReader(file);
		BufferedReader bf = new BufferedReader(fileReader);
		int contatore =0;
		while(bf.ready()) {
			System.out.println(++contatore + " : " + bf.readLine());
		}
		System.out.println("------------------------------------------");
		controllaDir(secondoFile);
	}

	public static void controllaDir(File path) {
		if (path.isDirectory()) {
			File[] percorsi = path.listFiles();
			for(int i=0;i<percorsi.length;i++) {
				if(percorsi[i].isDirectory()) {
					System.out.println("Il nome della cartella è "+ percorsi[i].getName());
					String str = percorsi[i].getAbsolutePath();
					path = new File(str);
					controllaDir(path);
				}
				else {
					System.out.println("Il nome del file è "+ percorsi[i].getName());
				}
			}
		}
		else {
			System.out.println("Il nome del file è "+ path.getName());
		}
	}
}
