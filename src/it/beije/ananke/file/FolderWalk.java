package it.beije.ananke.file;

import java.io.*;
import java.nio.file.*;

public class FolderWalk {
	public static void main(String[] args) throws FileNotFoundException {
		File cartella1 = new File("C:\\Users\\Padawan04\\Desktop");
		System.out.println(cartella1.isDirectory());
		stampaCartelle(cartella1);
		
	}
	
	public static void stampaCartelle(File folder) {
		File[] listOfFiles = folder.listFiles();
		for (File file : listOfFiles) {
			if(file.isDirectory()) {
				System.out.println("Cartella : " + file.getName());
				stampaCartelle(file);
			}else {
		        System.out.println("File : " + file.getName());
		    }
		}
	}
}
