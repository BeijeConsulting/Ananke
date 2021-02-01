package beije.ananke.rubrica;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class RubricaMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		File rub = new File("C:/Users/Padawan03/eclipse-workspace/files/rubrica.txt");
		File rub2 = new File("C:/Users/Padawan03/eclipse-workspace/files/rubrica2.txt");
		
		Scanner input =  new Scanner(System.in);
		String result="";
		
		System.out.println("Ciao! Seleziona una funzionalità inserendo il numero corrispondente:");
		
		do {
			
			System.out.println("[1] Aggiungi un contatto\n[2] Modifica un contatto\n[3] elimina un contatto\n[4] "
					+ "Importa rubrica da file xml su file csv\n[5] Importa rubrica da file csv su file xml\n[6] Cerca un contatto\n"
					+ "[7] Esci");
		
			 result = input.next();
			 
			 switch(result)
			 {
			 case "1": Rubrica.aggiungiContatto(input); break;
			 case "2": Rubrica.modificaContatto(input); break;
			 case "3": Rubrica.eliminaContatto(input); break;
			 case "4": Rubrica.fromXmlToCsv(input); break;
			 case "5": Rubrica.fromCsvToXml(input); break;
			 case "6": Rubrica.cercaContatto(input);break;
			 case "7": System.out.println("Rubrica chiusa.");
			 		   input.close(); break;
			 }
			 }while(!result.equals("7"));
		
		}	
}

