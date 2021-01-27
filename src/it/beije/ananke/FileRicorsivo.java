package it.beije.ananke;

import java.io.File;

public class FileRicorsivo {

	public static void main(String[] args) {
		File file = new File("C:\\Users\\Padawan09\\");
		if(file.isDirectory()) {
			FileRicorsivo.stampaFiles(file);
		}
	}
	
	public static void stampaFiles(File directory) {
		File[] files;
		files = directory.listFiles();
		
		if(files!=null) {
			
			for(File x:files) {
				System.out.println(x.getAbsolutePath());
			}
		
		
			for(File x:files) {
				if(x.isDirectory()) {
					FileRicorsivo.stampaFiles(x);
				}
			}
		}
	}
}
