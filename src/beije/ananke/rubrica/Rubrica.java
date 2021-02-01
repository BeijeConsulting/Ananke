package beije.ananke.rubrica;

import java.util.*;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

import java.io.*;

public class Rubrica {

	
	
	//READ CONTACTS FROM FILE
	 static ArrayList<Contatto> readContacts(File file) throws IOException
	{
		ArrayList<Contatto> cont = new ArrayList<>();
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
	static void  aggiungiContatto(Scanner input) throws IOException

	{	
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
			 
		}
		
			
	}
	 
	//Update a contact of rubrica
	static void modificaContatto(Scanner input)throws IOException
	{
	 	String result = null;
		
		System.out.print("Inserisci il path in cui si trova la rubrica: ");
		
		result = input.next();
		
		File file = new File(result);
		
		ArrayList<Contatto> contacts = readContacts(file);
		
		ArrayList<Contatto> found = new ArrayList<>();
		
		System.out.println("Scegli come effettuare la ricerca:\n"
				+ "[1] Cerca per nome\n[2] Cerca per cognome\n[3] Cerca per nome e cognome\n"
				+ "[4] Cerca per email");
		
		result = input.next();
		
		switch(result)
		{
		case "1": System.out.println("Inserisci nome: ");
				  result = input.next();
				  for(Contatto con : contacts)
				  {
					  if(result.equals(con.getName()))
					  {
						  found.add(con);
					  }
				  }
				  System.out.println("Contatti trovati:\n");
				  stampaContatti(found);break;
		 
		case "2": System.out.println("Inserisci cognome: ");
		  		  result = input.next();
		  		  for(Contatto con : contacts)
		  		  {
		  			  if(result.equals(con.getLastName()))
		  		      {
		  			  found.add(con);
			          }
		          }
		  		  System.out.println("Contatti trovati:\n");
		          stampaContatti(found);break;
		          
		case "3": System.out.println("Inserisci nome: ");
  		  		  result = input.next();
  		  		  String res2;
  		  		  System.out.println("Inserisci cognome: ");
  		  		  res2 = input.next();
  		          for(Contatto con : contacts)
  		          {
  			       if(result.equals(con.getName()) && res2.equals(con.getLastName()))
  		           {
  			       found.add(con);
	               }
                  }
  		           System.out.println("Contatti trovati:\n");
                   stampaContatti(found);break;
                   
		case "4": System.out.println("Inserisci email: ");
  		  		  result = input.next();
  		  		  for(Contatto con : contacts)
  		  		  {
  		  			if(result.equals(con.getEmail()))
                    {
	                 found.add(con);
                    }
  		  		  }
  		  		  System.out.println("Contatti trovati:\n");
  		  		  stampaContatti(found);break;
		}
	 
	 System.out.println("Seleziona il contatto che vuoi modificare:");
	 for(Contatto con : found)
	 {
		 System.out.println("["+found.indexOf(con)+"]: " +con);
	 }
	 
	 String res = input.next();
	 Contatto cont = found.get(Integer.parseInt(res));
	 
	 int posCont = contacts.indexOf(cont);

	 System.out.println("Inserisci il nome da cambiare o 0 per non effettuare la modifica:");
	 res = input.next();
	 if(res.equals("0"))
	 contacts.get(posCont).setName(contacts.get(posCont).getName());
	 else contacts.get(posCont).setName(res);
	 System.out.println("Inserisci il cognome da cambiare o 0 per non effettuare la modifica:");
	 res = input.next();
	 if(res.equals("0"))
	 contacts.get(posCont).setLastName(contacts.get(posCont).getLastName());
	 else contacts.get(posCont).setLastName(res);
	 System.out.println("Inserisci il numero da cambiare o 0 per non effettuare la modifica:");
	 res = input.next();
	 if(res.equals("0"))
	 contacts.get(posCont).setPhone(contacts.get(posCont).getPhone());
	 else contacts.get(posCont).setPhone(res);
	 System.out.println("Inserisci l'email da cambiare o 0 la modifica:");
	 res = input.next();
	 if(res.equals("0"))
	 contacts.get(posCont).setEmail(contacts.get(posCont).getEmail());
	 else contacts.get(posCont).setEmail(res);
	 
	 System.out.println("Contatto modificato");
	 writeContacts(file, contacts);
	 printFile(file);
	}
	 //Support method for "aggiungiContatto"
	 static Contatto insertData()
	{
		ArrayList<String> temp = new ArrayList<>();
		int counter = 0;
		String val;
		Contatto cont = new Contatto();
		Scanner takeIn = new Scanner(System.in);
		System.out.print("Inserisci il nome del contatto: ");
		val = takeIn.nextLine();
		if(val.length()>0)
		cont.setName(val);
		temp.add(cont.getName());
		System.out.print("Inserisci il cognome del contatto: ");
		val = takeIn.nextLine();
		if(val.length()>0)
		cont.setLastName(val);
		temp.add(cont.getLastName());
		System.out.print("Inserisci il numero di telefono del contatto: ");
		val = takeIn.nextLine();
		if(val.length()>0)
		cont.setPhone(val);
		temp.add(cont.getPhone());
		System.out.print("Inserisci l'email del contatto: ");
		val = takeIn.nextLine();
		if(val.length()>0)
		cont.setEmail(val);  
		temp.add(cont.getEmail());
		
		for(String el : temp)
		{
			if(!el.equals(""))
			{
				counter++;
			}
		}
		
		if(counter>0)
		{
			return cont;
		}
		else
		{
			return null;
		}
	
	}

	 //Delete contact from rubrica
	 static void eliminaContatto(Scanner input) throws IOException
	 {
		 	String result = null;
			
			System.out.print("Inserisci il path in cui si trova la rubrica: ");
			
			result = input.next();
			
			File file = new File(result);
			
			ArrayList<Contatto> contacts = readContacts(file);
			
			ArrayList<Contatto> found = new ArrayList<>();
			
			System.out.println("Scegli come effettuare la ricerca:\n"
					+ "[1] Cerca per nome\n[2] Cerca per cognome\n[3] Cerca per nome e cognome\n"
					+ "[4] Cerca per email");
			
			result = input.next();
			
			switch(result)
			{
			case "1": System.out.println("Inserisci nome: ");
					  result = input.next();
					  for(Contatto con : contacts)
					  {
						  if(result.equals(con.getName()))
						  {
							  found.add(con);
						  }
					  }
					  System.out.println("Contatti trovati:\n");
					  stampaContatti(found);break;
			 
			case "2": System.out.println("Inserisci cognome: ");
			  		  result = input.next();
			  		  for(Contatto con : contacts)
			  		  {
			  			  if(result.equals(con.getLastName()))
			  		      {
			  			  found.add(con);
				          }
			          }
			  		  System.out.println("Contatti trovati:\n");
			          stampaContatti(found);break;
			          
			case "3": System.out.println("Inserisci nome: ");
	  		  		  result = input.next();
	  		  		  String res2;
	  		  		  System.out.println("Inserisci cognome: ");
	  		  		  res2 = input.next();
	  		          for(Contatto con : contacts)
	  		          {
	  			       if(result.equals(con.getName()) && res2.equals(con.getLastName()))
	  		           {
	  			       found.add(con);
		               }
	                  }
	  		           System.out.println("Contatti trovati:\n");
	                   stampaContatti(found);break;
	                   
			case "4": System.out.println("Inserisci email: ");
	  		  		  result = input.next();
	  		  		  for(Contatto con : contacts)
	  		  		  {
	  		  			if(result.equals(con.getEmail()))
	                    {
		                 found.add(con);
	                    }
	  		  		  }
	  		  		  System.out.println("Contatti trovati:\n");
	  		  		  stampaContatti(found);break;
			}
		 
		 System.out.println("Seleziona il contatto che vuoi eliminare:");
		 for(Contatto con : found)
		 {
			 System.out.println("["+found.indexOf(con)+"]: " +con);
		 }
		 
		 String res = input.next();
		 Contatto cont = found.get(Integer.parseInt(res));
		 contacts.remove(cont);
		 System.out.println("Il contatto è stato eliminato.\nRitorno al menu.. ");
		 writeContacts(file, contacts);
	 }
	 
	 
	 //Import from file xml to csv
	 static void fromXmlToCsv(Scanner input) throws IOException, ParserConfigurationException, SAXException
	 {
		 String path,last3,compare="xml";
		 System.out.println("Inserisci il path della rubrica da importare:");
		 path = input.next(); 
		 last3 = path.substring(path.length()-3);
		 
		 if(last3.equals(compare))
		 {
			 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		     DocumentBuilder builder = factory.newDocumentBuilder();
			 Document document = builder.parse(path);
			 ArrayList<Contatto> contatti = RubricaXML.readContactsXML(document);
			 
			 System.out.println("Inserisci il path in cui salvare il nuovo file:");
			 path = input.next();
			 
			 File file = new File(path);
			 
			 writeContacts(file,contatti);
			 
			 System.out.println("Rubrica importata correttamente");		 
		 }
		 
		 else
		 {
			 System.out.println("Il file che vuoi importare non è xml. Ritorno al menu..");
		 }
	 }

	 //Import from file csv to xml
	 static void fromCsvToXml(Scanner input)throws Exception
	 {
		 String path,last3,compare="txt";
		 System.out.println("Inserisci il path della rubrica da importare:");
		 path = input.next(); 
		 last3 = path.substring(path.length()-3);
		 
		 if(last3.equals(compare))
		 {
			 File file = new File(path);
			 ArrayList<Contatto> contatti = readContacts(file);
			 
			 System.out.println("Inserisci il path in cui salvare il nuovo file:");
			 path = input.next();
			 
			 RubricaXML.writeContactXML(contatti,path);
			 
			 System.out.println("Rubrica importata correttamente");
		 }
		 else
		 {
			 System.out.println("Il file che vuoi importare non è csv. Ritorno al menu..");
		 }
	 }
	 
	 //Search contact
	 static void cercaContatto(Scanner input) throws IOException
	 {
		 	String result = null;
			
			System.out.print("Inserisci il path in cui si trova la rubrica: ");
			
			result = input.next();
			
			File file = new File(result);
			
			ArrayList<Contatto> contacts = readContacts(file);
			
			ArrayList<Contatto> found = new ArrayList<>();
			
			System.out.println("Scegli come effettuare la ricerca:\n"
					+ "[1] Cerca per nome\n[2] Cerca per cognome\n[3] Cerca per nome e cognome\n"
					+ "[4] Cerca per email");
			
			result = input.next();
			
			switch(result)
			{
			case "1": System.out.println("Inserisci nome: ");
					  result = input.next();
					  for(Contatto con : contacts)
					  {
						  if(result.equals(con.getName()))
						  {
							  found.add(con);
						  }
					  }
					  System.out.println("Contatti trovati:\n");
					  stampaContatti(found);
					  System.out.println("Ritorno al menu..");break;
					 
			 
			case "2": System.out.println("Inserisci cognome: ");
			  		  result = input.next();
			  		  for(Contatto con : contacts)
			  		  {
			  			  if(result.equals(con.getLastName()))
			  		      {
			  			  found.add(con);
				          }
			          }
			  		  System.out.println("Contatti trovati:\n");
			          stampaContatti(found);
			          System.out.println("Ritorno al menu..");break;
			         		          
			case "3": System.out.println("Inserisci nome: ");
	  		  		  result = input.next();
	  		  		  String res2;
	  		  		  System.out.println("Inserisci cognome: ");
	  		  		  res2 = input.next();
	  		          for(Contatto con : contacts)
	  		          {
	  			       if(result.equals(con.getName()) && res2.equals(con.getLastName()))
	  		           {
	  			       found.add(con);
		               }
	                  }
	  		           System.out.println("Contatti trovati:\n");
	                   stampaContatti(found);
	                   System.out.println("Ritorno al menu..");break;
	                   
			case "4": System.out.println("Inserisci email: ");
	  		  		  result = input.next();
	  		  		  for(Contatto con : contacts)
	  		  		  {
	  		  			if(result.equals(con.getEmail()))
	                    {
		                 found.add(con);
	                    }
	  		  		  }
	  		  		  System.out.println("Contatti trovati:\n");
	  		  		  stampaContatti(found);
	  		  		  System.out.println("Ritorno al menu..");break;
	  		  		 
			}
		
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

	 static void stampaContatti(List l)
	 {
		 for(Object o : l)
		 {
			 System.out.println(o);
		 }
	 }
}
