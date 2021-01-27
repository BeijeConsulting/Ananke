import java.util.*;
import java.io.*;
public class CsvManager {

	public XMLManager xmlm;

	public CsvManager(XMLManager xmlm) {
	this.xmlm = xmlm;
	try {
		writeFile();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	}
	
	public void writeFile() throws IOException {
		String s = "/Users/Gianni/Desktop/";
		System.out.println("Inserisci nome del file csv");
		Scanner c = new Scanner(System.in);
		s += c.next() + ".csv";
		File f = new File(s);
		FileWriter fw = new FileWriter(f);
		fw.write("NOME,COGNOME,EMAIL,CELLULARE");
		fw.write("\n");
		for(Contatto contatto: this.xmlm.contatti) {
			fw.write(contatto.toString());
			fw.write("\n");
		}
		fw.flush();
		fw.close();
	
	}
	
	public static void main(String[] args) {
		String s = "/Users/Gianni/Desktop/";
		System.out.println("Inserisci il nome del file da convertire: ");
		Scanner c = new Scanner(System.in);
		s += c.next() + ".xml";
		CsvManager csv = new CsvManager(new XMLManager(s));
		
	}
}
