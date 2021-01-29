package beije.ananke.rubrica;

import java.util.*;
import java.io.*;

public class Rubrica {

	
	
	//READ CONTACTS FROM FILE
	 static ArrayList<Contatto> readContacts(File file) throws IOException
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
	
	
	//WRITE CONTACTS ON FILE csv
	static  void writeContacts(File file, ArrayList<Contatto> list) throws IOException
	{
		FileWriter fw = new FileWriter(file);
		
		for( Contatto el : list)
		{
			fw.write(el.getName()+";"+el.getLastName()+";"+el.getPhone()+";"+el.getEmail()+";");
			fw.write("\n");
		}
		fw.close();
	}
	
	
	
	//Add contact into rubrica
	static void aggiungiContatto() throws IOException

	{
		Scanner input = new Scanner(System.in);
		String path = null;
					
		System.out.print("Inserisci il path in cui si trova la rubrica: ");
		
		path = input.next();
		
		Contatto toInsert = insertData();
		
		File file = new File(path);
		
		ArrayList<Contatto> contacts = readContacts(file);
		
		if(toInsert!=null)
		{
		contacts.add(toInsert);
		writeContacts(file,contacts);
		
		System.out.println("Contatto aggiunto.");	
		printFile(file);
		
		}
		else {
			  System.out.println("Devi inserire almeno un campo.\nRitorno al menu...\n");	
			  input.close();
		}
		
			
	}
	 
	 //Support method for "aggiungiContatto"
	 static Contatto insertData()
	{
		ArrayList<String> temp = new ArrayList<>();
		int counter = 0;
		Contatto cont = new Contatto();
		Scanner takeIn = new Scanner(System.in);
		System.out.print("Inserisci il nome del contatto: ");
		cont.setName(takeIn.nextLine());
		temp.add(cont.getName());
		System.out.print("Inserisci il cognome del contatto: ");
		cont.setLastName(takeIn.nextLine());
		temp.add(cont.getLastName());
		System.out.print("Inserisci il numero di telefono del contatto: ");
		cont.setPhone(takeIn.nextLine());
		temp.add(cont.getPhone());
		System.out.print("Inserisci l'email del contatto: ");
		cont.setEmail(takeIn.nextLine());  
		temp.add(cont.getEmail());
		
		for(String el : temp)
		{
			if(el.equals(""))
			{
				counter++;
			}
		}
		
		takeIn.close();
		
		if(counter!=temp.size())
		{
			return cont;
		}
		else
		{
			return null;
		}
	
	}

	 //Delete contact from rubrica
	 static void eliminaContatto() throws IOException
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
					System.out.println("Contatto eliminato");
					System.out.println("Elenco contatti aggiornato:");
					break;
				}	
			}			
			writeContacts(file,contacts);
			printFile(file);					
			System.exit(0);		
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
	
	 //Printing a file
	 static void printFile(File f) throws IOException
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


}
