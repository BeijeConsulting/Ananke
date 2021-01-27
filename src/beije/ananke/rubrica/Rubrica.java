package beije.ananke.rubrica;

import java.util.*;
import java.io.*;

public class Rubrica {

	public static void main(String[] args) throws IOException {
		
		File rub = new File("C:/Users/Padawan03/Desktop/rubrica.txt");
		File rub2 = new File("C:/Users/Padawan03/Desktop/rubrica2.txt");
		
		
		//Testing readContacts
//		ArrayList<Contatto> contatti = new Rubrica().readContacts(rub);
//		
//		for(Contatto el : contatti)
//		{
//			System.out.println(el);
//		}
//		
//		//Testing writeContacts
//		new Rubrica().writeContacts(rub2,contatti);
//		new Rubrica().printFile(rub2);
		
		new Rubrica().openMenu();
		
		

	}
	
	
	//READ CONTACTS FROM FILE
	 ArrayList<Contatto> readContacts(File file) throws IOException
	{
		ArrayList<Contatto> cont = new ArrayList<>();
		ArrayList<String> lines = new ArrayList<>();
	    BufferedReader br = new BufferedReader(new FileReader(file));
	    	
		while(br.ready())
		{
			String[] str = br.readLine().split(";");
			Contatto contatto = new Contatto();
			
			contatto.setName(str[0]);
			contatto.setLastName(str[1]);
			contatto.setPhone(str[2]);
			contatto.setEmail(str[3]);
			cont.add(contatto);			
		}				
		br.close();
	    
	    return cont;
	}
	
	
	//WRITE CONTACTS ON FILE
	 void writeContacts(File file, ArrayList<Contatto> list) throws IOException
	{
		FileWriter fw = new FileWriter(file);
		
		for( Contatto el : list)
		{
			fw.write(el.getName()+";"+el.getLastName()+";"+el.getPhone()+";"+el.getEmail()+";");
			fw.write("\n");
		}
		fw.close();
	}
	
	
	//Interaction menu
	 void openMenu() throws IOException
	{
		Scanner input = new Scanner(System.in);
		
		System.out.println("Ciao! Seleziona una funzionalità inserendo il numero corrispondente:");
		
		System.out.println("[1] aggiungiContatto\n[2] modificaContatto\n[3] eliminaContatto\n[4] "
				+ "apri menu\n[5] esci");
		
		switch(input.nextInt())
		{
		case 1: aggiungiContatto(); break;
		//case 2: Rubrica.modificaContatto();
		case 3: eliminaContatto(); break;
		case 4: openMenu(); break;
		case 5: input.close();
				break;
		default: break;
		}
	}
	
	//Add contact to "Rubrica"
	 void aggiungiContatto() throws IOException

	{
		Scanner input = new Scanner(System.in);
		
		System.out.print("Inserisci il path in cui si trova la rubrica: ");
		
		String path = input.next();
		
		Contatto toInsert = insertData();
		
		File file = new File(path);
		
		ArrayList<Contatto> contacts = readContacts(file);
		
		contacts.add(toInsert);
		
		writeContacts(file,contacts);
		
		System.out.println("Contatto aggiunto.");
		
		printFile(file);

	}

	 void eliminaContatto() throws IOException
	 {
		 	Scanner input = new Scanner(System.in);
		 	String email;
			
			System.out.print("Inserisci il path in cui si trova la rubrica: ");
			
			String path = input.next();
			
			File file = new File(path);
			
			ArrayList<Contatto> contacts = readContacts(file);
			
			System.out.println("Inserisci l'email del contatto che vuoi eliminare:");
			
			email=input.next();
			
			input.close();
			
			for(Contatto co : contacts)
			{
				if(co.getEmail().equalsIgnoreCase(email))
				{
					contacts.remove(co);
					System.out.println("Cotatto eliminato");
					System.out.println("Elenco contatti aggiornato:");
					break;
				}	
			}			
			writeContacts(file,contacts);
			printFile(file);					
			System.exit(0);		
	 }
	 
	 Contatto insertData()
	{
		Contatto cont = new Contatto();
		Scanner takeIn = new Scanner(System.in);
		System.out.print("Inserisci il nome del contatto: ");
		cont.setName(capitalize(takeIn.next()));
		System.out.print("Inserisci il cognome del contatto: ");
		cont.setLastName(capitalize(takeIn.next()));
		System.out.print("Inserisci il numero di telefono del contatto: ");
		cont.setPhone(takeIn.next());
		System.out.print("Inserisci l'email del contatto: ");
		cont.setEmail(takeIn.next().toLowerCase());  
		
		takeIn.close();
		
		return cont;
	}
	
	 String formatContact(String...data)
	{
		String formatted="";
		
		for(String el : data)
		{
			formatted+= el+";";
		}
		return formatted;
	}
	
	 void printFile(File f) throws IOException
	{
			FileReader reader = new FileReader(f);
			BufferedReader br = new BufferedReader(reader);
			
			while(br.ready())
			{
				System.out.println(br.readLine());		
			}				
			br.close();
			reader.close();	
	}
	
	 String capitalize(String word)
		{
		 	String lower = word.toLowerCase();
			String result = lower.substring(0, 1).toUpperCase() + lower.substring(1);
			
			return result;	
		}

}
