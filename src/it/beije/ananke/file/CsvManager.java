package it.beije.ananke.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class CsvManager {

	public static void main(String[] args) throws Exception {
		
		File file = new File("/temp/rubrica.txt");
		System.out.println("file : " + file.getAbsolutePath());
		System.out.println("file exists ? " + file.exists());
		System.out.println("is a dir ? " + file.isDirectory());
		
		FileReader fileReader = new FileReader(file);	
//		FileReader fileReader = new FileReader("/temp/rubrica.txt");
//		System.out.println(fileReader);		
//		while (fileReader.ready()) {
//			System.out.print((char)fileReader.read());
//		}
		
		BufferedReader bufferedReader = new BufferedReader(fileReader);
//		int r = 1;
		List<String> rows = new ArrayList<String>();
		while (bufferedReader.ready()) {
//			System.out.println(r++ + " : " + bufferedReader.readLine());
			rows.add(bufferedReader.readLine());
		}
		
		bufferedReader.close();
		fileReader.close();

		System.out.println("rows size : " + rows.size());
		System.out.println(rows);
		
//		for (String row : rows) {
//			String[] rs = row.split(";");
////			for (String r : rs) {
////				System.out.println(r);
//			for (int i = rs.length-1; i >= 0; i--) {
//				System.out.println(rs[i]);
//			}
//			System.out.println("--------");
//
//			StringTokenizer tokenizer = new StringTokenizer(row, ";");
//			while (tokenizer.hasMoreTokens()) {
//				String r = tokenizer.nextToken();
//				System.out.println(r);
//			}
//			System.out.println("========");
//		}
		
		//Scrivo nuovo file
		File newFile = new File("/temp/nuovo.txt");
		System.out.println("new file : " + newFile.getAbsolutePath());
		System.out.println("new file exists ? " + newFile.exists());
		
		FileWriter fileWriter = new FileWriter(newFile);
//		fileWriter.write("questa è una prova");
		
		for (String row : rows) {
			String[] rs = row.split(";");
			for (int i = rs.length-1; i >= 0; i--) {
				System.out.println(rs[i]);
				fileWriter.write(rs[i]);
				fileWriter.write(';');
			}
			fileWriter.write('\n');
			System.out.println("--------");

		}
		
		fileWriter.flush();
		fileWriter.close();		
	}

}

/*
paperina;3484334654;paperina@pluto.net;
paperino;34843489654;paperino@pippo.it;
pippo;346789654;pippo@pippo.it;
pluto;346782312;pluto@pluto.com;
*/
