package tempistiche;

import java.io.*;
import java.util.ArrayList;

public class CsvManagerMia {
	
	public static void main(String[] args) throws Exception {
/*		File file = new File("C:\\Users\\Padawan04\\Desktop\\Snippets\\a\\csvstyle.txt");
		System.out.println("file: " + file.getAbsolutePath());
		System.out.println("file: " + file.exists());
		System.out.println("file: " + file.isDirectory());
		
		FileReader fr = new FileReader(file);
//		
//		while (fr.ready()) {
//			System.out.print((char) fr.read());
//		}
		BufferedReader br = new BufferedReader(fr);
		int var = 1;
		ArrayList<String> rows = new ArrayList<>();
		while (br.ready()) {
			System.out.println(var++ + " : " + br.readLine());
			rows.add(br.readLine());
		}
		
		br.close();
		fr.close();
		//ricordati di chiudere file reader e simili
		//quando leggi pace, quando scrivi è più complicato
		
		System.out.println("rows size : " + rows.size());
		System.out.print(rows);
		
		for(String row : rows) {
			String[] rs = row.split(";"); //restituisce un array
			//separatori se stai leggendo cvs
			for(String r : rs) {
				System.out.println(r);
			}
		}
*/
		
		//scrivere nuovo file
/*		File newFile = new File("C:\\Users\\Padawan04\\Desktop\\Snippets\\a\\scrittura.txt");
		System.out.println("new file : " + newFile.getAbsolutePath());
		System.out.println("new file exists ? " + newFile.exists());
		
		FileWriter fw = new FileWriter(newFile);
		fw.write("questa è una prova");
		
		//scarica quello che in memoria effettivamente nel file
		fw.flush();
		//di solito con close viene fatto anche il flush
		//ma tu metticelo comunque che male non ti fa
		fw.close();
*/		
		
		FileReader reader = new FileReader(args[0]);
		FileWriter writer = new FileWriter(args[1]);
				
		while (reader.ready()) {
			writer.write(reader.read());
		}
		
		writer.flush();
		writer.close();
		reader.close();
		
		
//		BufferedWriter bw = new BufferedWriter(fw);
		//il write all'atto pratico è come quello del FileWriter
//		bw.write();
		//per ora non credo che questa classe ci sia utile
		
		//per copiare un file in un altro, filereader e filewriter su ogni carattere
		//coi file di solito si usano i decorator
		
	}
}
