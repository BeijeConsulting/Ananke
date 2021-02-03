package it.beije.ananke.database;

import beije.ananke.rubrica.Contatto;
import beije.ananke.rubrica.Rubrica;
import beije.ananke.rubrica.RubricaXML;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import javax.persistence.EntityManager;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class HibernateManager {

	public static void main(String[] args) throws Exception {
		
		Scanner input = new Scanner(System.in);
		
		menuDB(input);
	}
	
	
	//open the menu
	public static void menuDB(Scanner input) throws Exception
 	{
		String result;
		
		System.out.println("Sei nel menu del database. Scegli una funzionalita:");

			do {			
				System.out.println("[1] Inserisci un nuovo contatto\n[2] Modifica un contatto\n[3] Cerca un contatto\n[4] "
						+ "Elimina un contatto\n[5] Esporta rubrica su file xml o csv\n[6] Importa una rubrica su db."
						+ "\n[7] Esci");
			
				 result = input.next();
				 
				 switch(result)
				 {
				 case "1": HibernateManager.aggiungiContatto(input); break;
				 case "2": HibernateManager.modificaContatto(input); break;
				 case "3": HibernateManager.cercaContatto(input); break;
				 case "4": HibernateManager.eliminaContatto(input); break;
				 case "5": HibernateManager.exportDB(input);break;
				 case "6": HibernateManager.importOnDB(input);break;
				 case "7": System.out.println("Uscita database..");break;		 
				 }
				 }while(!result.equals("7"));		
 	}
	
	public static void aggiungiContatto(Scanner input)
	{
		Session session = HybernateSessionManager.getSession();
		
		//apro transazione
		Transaction transaction = session.beginTransaction();
		String val;
		Contatto cont = new Contatto();
		System.out.println("Inserisci il nome o 0 per lasciarlo vuoto:");
		val = input.next();
		if(!val.equals("0"))
		cont.setName(val);
		System.out.println("Inserisci il cognome o 0 per lasciarlo vuoto:");
		val = input.next();
		if(!val.equals("0"))
		cont.setLastName(val);
		System.out.println("Inserisci il numero di telefono o 0 per lasciarlo vuoto:");
		val = input.next();
		if(!val.equals("0"))
		cont.setPhone(val);
		System.out.println("Inserisci l'email  0 per lasciarlo vuoto:");
		val = input.next();
		if(!val.equals("0"))
		cont.setEmail(val);  	
		
		if(!(cont.getEmail().trim().length()==0 && cont.getName().trim().length()==0 && cont.getLastName().trim().length()==0
				&& cont.getPhone().trim().length()==0))
		{
			session.save(cont);
			transaction.commit();
			session.close();
			System.out.println("Contatto aggiunto al db");
		}
		else
		{
			System.out.println("Devi inserire almeno un campo. Ritorno al menu..");
			transaction.rollback();
			session.close();
			//sessionFactory.close();
		}			
		
	}
	
	public static void cercaContatto(Scanner input)
	{
		Session session = HybernateSessionManager.getSession();
		
		String val;
		System.out.println("Inserisci un campo(nome,cognome,email,ecc) per effettuare la ricerca:");
		val = input.next();
		String hqlSelect = "SELECT c FROM Contatto as c WHERE name='"+val+"' OR surname='"+val+"' OR telephone='"+val+"' OR email='"+val+"'";
		Query<Contatto> query = session.createQuery(hqlSelect);
		List<Contatto> contatti = query.list();
		
		System.out.println("Contatti trovati:");
		for (Contatto contatto : query.list()) {
			System.out.println("id : " + contatto.getId());
			System.out.println("name : " + contatto.getName());
			System.out.println("surname : " + contatto.getLastName());
			System.out.println("telephone : " + contatto.getPhone());
			System.out.println("email : " + contatto.getEmail());
					
			session.close();
		}
		
					
	}

	public static void modificaContatto(Scanner input)
	{
		
			cercaContatto(input);
			
			Session session = HybernateSessionManager.getSession();
			int id; 
			String result;
			Transaction transaction = session.beginTransaction();
			
			System.out.println("Inserisci l'id del contatto da modificare:");
			id = input.nextInt();
			
			String hqlSelect = "SELECT c FROM Contatto as c WHERE id="+id;
			Query<Contatto> query = session.createQuery(hqlSelect);
			List<Contatto> contatti = query.list();
			Contatto cont = null;
			if(contatti.size()>0)
			 {cont = contatti.get(0);
				
			
			System.out.println("Inserisci il campo che vuoi modificare:");
			result = input.next();
			
			switch(result)
			{
			  case "name": System.out.println("Inserisci il nuovo campo:");
			  			   result=input.next();
			  			   cont.setName(result);
			  			   session.save(cont);
			  			   transaction.commit();break;
			  			
			  case "surname":System.out.println("Inserisci il nuovo campo:");
			   				result=input.next();
			   				cont.setLastName(result);
				  			session.save(cont);
				  			transaction.commit();break;
			   				
			  case "telephone":System.out.println("Inserisci il nuovo campo:");
  							   result=input.next();
  							   cont.setPhone(result);
  			  			       session.save(cont);
  			  			       transaction.commit();break;
  							   
			  case "email":System.out.println("Inserisci il nuovo campo:");
			  			   cont.setEmail(result);
 			               session.save(cont);
 			               transaction.commit();break;
 			           	  
			}
			 }
			else {
				   System.out.println("Contatto non presente");
			}
			session.close();					
	}

	public static void eliminaContatto(Scanner input)
	{

			cercaContatto(input);
			
			Session session = HybernateSessionManager.getSession();
			int id; 
			String result;
			Transaction transaction = session.beginTransaction();
			
			System.out.println("Inserisci l'id del contatto da eliminare:");
			id = input.nextInt();
			
			String hqlSelect = "SELECT c FROM Contatto as c WHERE id="+id;
			Query<Contatto> query = session.createQuery(hqlSelect);
			List<Contatto> contatti = query.list();
			Contatto cont = contatti.get(0);
			
			session.delete(cont);
			transaction.commit();
			System.out.println("Contatto eliminato");
			session.close();
			
			
		
	}

	public static void exportDB(Scanner input) throws Exception
	{
			Session session = HybernateSessionManager.getSession();
			String result,last3,xml="xml",csv="txt";
			String hqlSelect = "SELECT c FROM Contatto as c";
			Query<Contatto> query = session.createQuery(hqlSelect);
			List<Contatto> contatti = query.list();
			
			System.out.println("Inserisci il path in cui esportare la rubrica:");
			result = input.next();
			last3 = result.substring(result.length()-3);
			
			 if(last3.equals(xml))
			 {
           		 RubricaXML.writeContactXML((ArrayList<Contatto>)contatti,result);
			 }
			 else if(last3.equals(csv))
			 {
				 Rubrica.writeContacts(new File(result),(ArrayList<Contatto>)contatti);
			 }
			 
		      session.close();					
	}

	public static void importOnDB(Scanner input) throws ParserConfigurationException, SAXException, IOException
	{
		String path,last3,xml="xml",csv="txt";
		 System.out.println("Inserisci il path della rubrica da importare:");
		 path = input.next(); 
		 last3 = path.substring(path.length()-3);
		 ArrayList<Contatto> contatti = null;
		 
		 if(last3.equals(xml))
		 {
			 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		     DocumentBuilder builder = factory.newDocumentBuilder();
			 Document document = builder.parse(path);
			 contatti = RubricaXML.readContactsXML(document);
		 }
		 else if(last3.equals(csv))
		 {
			 contatti = Rubrica.readContacts(new File(path));
		 }
		 
		 		Session session = HybernateSessionManager.getSession();
		 		Transaction transaction = session.beginTransaction();

				    for(Contatto con : contatti)
				    {
					  session.save(con);
				    }
				    System.out.println("Rubrica importata su db.");
				    transaction.commit();
				    session.close();						
}
	
}

 	