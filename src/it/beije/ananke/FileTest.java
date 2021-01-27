package it.beije.ananke;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;



public class FileTest {

	public static void main(String[] args) throws Exception {
		File file = new File("C://Users//Padawan09//Downloads//Sample100.csv");
		FileReader fr = new FileReader(file);
		FileWriter fw = new FileWriter("C://Users//Padawan09//Downloads//Sample100Copy.csv");
		
		BufferedReader br = new BufferedReader(fr);
		
		List<String> rows = new ArrayList<String>();
		while(br.ready()) {
			rows.add(br.readLine());
		}
		br.close();
		fr.close();
		
		for(String row : rows) {
			fw.write(row);
			fw.write("\n");
		}	
		fw.close();
	}

}
