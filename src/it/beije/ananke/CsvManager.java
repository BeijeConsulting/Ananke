package it.beije.ananke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
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
//			//			for (String r : rs) {
//			//				System.out.println(r);
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

	}
}
