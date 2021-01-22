package it.beije.ananke.file;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class CopyFile {

	public static void main(String[] args) throws IOException {

		FileReader reader = new FileReader(args[0]);
		FileWriter writer = new FileWriter(args[1]);
				
		while (reader.ready()) {
			writer.write(reader.read());
		}
		
		writer.flush();
		writer.close();
		reader.close();
	}

}
