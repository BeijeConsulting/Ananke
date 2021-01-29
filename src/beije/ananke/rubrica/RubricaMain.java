package beije.ananke.rubrica;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class RubricaMain {

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		File rub = new File("C:/Users/Padawan03/eclipse-workspace/files/rubrica.txt");
		File rub2 = new File("C:/Users/Padawan03/eclipse-workspace/files/rubrica2.txt");
		
		Scanner input = new Scanner(System.in);
		
		String result=null;
		
		System.out.println("Ciao! Seleziona una funzionalità inserendo il numero corrispondente:");
		
		System.out.println("[1] aggiungiContatto\n[2] modificaContatto\n[3] eliminaContatto\n[4] "
				+ "Converti rubrica .xml in .csv\n[5] Converti rubrica .csv in .xml\n[6] esci");
			
		 result = input.next();
		 
		while(!result.equals("6"))
		{
		switch(result)
		{
		case "1": Rubrica.aggiungiContatto(); break;
		//case 2: Rubrica.modificaContatto();
		case "3": Rubrica.eliminaContatto(); break;
		//case 4: fromXmltoCsv(); break;
		//case 5: fromCsvtoXml(); break;
		case "6": input.close(); break;
		default: break;
		}
		
		System.out.println("[1] aggiungiContatto\n[2] modificaContatto\n[3] eliminaContatto\n[4] "
				+ "Converti rubrica .xml in .csv\n[5] Converti rubrica .csv in .xml\n[6] esci");
		 result = input.next();
		}
		
}
}
