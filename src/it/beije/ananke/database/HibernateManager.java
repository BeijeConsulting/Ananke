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

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class HibernateManager {

	public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
		
		Scanner input = new Scanner(System.in);
		
		menuDB(input);
	}
	
	
	//open the menu
	public static void menuDB(Scanner input) throws ParserConfigurationException, SAXException, IOException
 	{
		Configuration configuration = new Configuration().configure();
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		String result;
		
		System.out.println("Sei nel menu del database. Scegli una funzionalita:");

			do {
			
			System.out.println("[1] Inserisci un nuovo contatto\n[2] Modifica un contatto\n[3] Cerca un contatto\n[4] "
					+ "Elimina un contatto\n[5] Esporta rubrica su file xml\n[6] Esporta rubrica su file csv\n"
					+ "[7] Importa una rubrica su db.\n[8] Esci");
		
			 result = input.next();
			 
			 switch(result)
			 {
			 case "1": HibernateManager.aggiungiContatto(sessionFactory,input); break;
			 case "2": HibernateManager.modificaContatto(sessionFactory,input); break;
			 case "3": HibernateManager.cercaContatto(sessionFactory,input); break;
//			 case "4": HibernateManager.eliminaContatto(sessionFactory,input); break;
//			 case "5": HibernateManager.exportOnXml(sessionFactory,input); break;
//			 case "6": HibernateManager.exportOnCsv(sessionFactory,input);break;
//			 case "7": HibernateManager.importOnDB(sessionFactory,input);break;
			 case "8": System.out.println("Uscita database..");break;		 
			 }
			 }while(!result.equals("8"));
			
			sessionFactory.close();
 	}
	
	public static void aggiungiContatto(SessionFactory sessionFactory,Scanner input)
	{
		Session session = sessionFactory.openSession();
		
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
			sessionFactory.close();
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
	
	public static void cercaContatto(SessionFactory sessionFactory,Scanner input)
	{
		Session session = sessionFactory.openSession();
		
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

	public static void modificaContatto(SessionFactory sessionFactory,Scanner input)
	{
		
			cercaContatto(sessionFactory,input);
			
			Session session = sessionFactory.openSession();
			int id; 
			String result;
			Transaction transaction = session.beginTransaction();
			
			System.out.println("Inserisci l'id del contatto da modificare:");
			id = input.nextInt();
			
			String hqlSelect = "SELECT c FROM Contatto as c WHERE id="+id;
			Query<Contatto> query = session.createQuery(hqlSelect);
			List<Contatto> contatti = query.list();
			Contatto cont = contatti.get(0);
			
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
			
			session.close();					
	}

	public static void eliminaContatto(SessionFactory sessionFactory,Scanner input)
	{

			cercaContatto(sessionFactory,input);
			
			Session session = sessionFactory.openSession();
			int id; 
			String result;
			Transaction transaction = session.beginTransaction();
			
			System.out.println("Inserisci l'id del contatto da eliminare:");
			id = input.nextInt();
			
			if(statement.execute("DELETE FROM contatti WHERE id="+id+""))
			{
				System.out.println("Contatto eliminato dal database..");
			}
			else
			{
				System.out.println("Qualcosa è andato storto. Ritorno al menu..");
			}
			
			
		
	}

//	public static void exportOnXml(SessionFactory sessionFactory,Scanner input)
//	{
//		Connection connection = null;
//		Statement statement = null;
//		ResultSet rs = null;	
//		String result;
//
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");	
//			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//			statement = connection.createStatement();
//			ArrayList<Contatto> contatti = new ArrayList<>();
//			Contatto cont= new Contatto();
//			
//			System.out.println("Inserisci il path in cui esportare la rubrica:");
//			result = input.next();
//			
//			rs = statement.executeQuery("SELECT * FROM contatti ");
//					 
//			
//			while (rs.next()) {
//				cont.setLastName(rs.getString("surname"));
//				cont.setName(rs.getString("name"));
//				cont.setEmail(rs.getString("email"));
//				cont.setPhone(rs.getString("telephone"));
//				contatti.add(cont);
//				
//			}
//			
//			RubricaXML.writeContactXML(contatti,result);
//			
//						
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				connection.close();
//				System.out.println(connection.isClosed());
//					
//				rs.close();
//				statement.close();
//			
//			} catch (SQLException sqlEx) {
//				sqlEx.printStackTrace();
//			}
//		}
//	}
//
//	public static void exportOnCsv(SessionFactory sessionFactory,Scanner input)
//	{
//		Connection connection = null;
//		Statement statement = null;
//		ResultSet rs = null;	
//		String result;
//
//		try {
//			Class.forName("com.mysql.cj.jdbc.Driver");	
//			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//			statement = connection.createStatement();
//			ArrayList<Contatto> contatti = new ArrayList<>();
//			Contatto cont= new Contatto();
//			
//			System.out.println("Inserisci il path in cui esportare la rubrica:");
//			result = input.next();
//			
//			rs = statement.executeQuery("SELECT * FROM contatti ");
//					 
//			
//			while (rs.next()) {
//				cont.setLastName(rs.getString("surname"));
//				cont.setName(rs.getString("name"));
//				cont.setEmail(rs.getString("email"));
//				cont.setPhone(rs.getString("telephone"));
//				contatti.add(cont);
//				
//			}
//			
//			RubricaXML.writeContactXML(contatti,result);
//			
//			Rubrica.writeContacts(new File(result),contatti);
//			
//			System.out.println("Rubrica esportata");
//			
//			System.out.println("Stampo il file:");
//			
//			Rubrica.printFile(new File(result));
//		
//						
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				connection.close();
//				System.out.println(connection.isClosed());
//					
//				rs.close();
//				statement.close();
//			
//			} catch (SQLException sqlEx) {
//				sqlEx.printStackTrace();
//			}
//		}
//	}
//
//	public static void importOnDB(SessionFactory sessionFactory,Scanner input) throws ParserConfigurationException, SAXException, IOException
//	{
//		String path,last3,xml="xml",csv="txt";
//		 System.out.println("Inserisci il path della rubrica da importare:");
//		 path = input.next(); 
//		 last3 = path.substring(path.length()-3);
//		 ArrayList<Contatto> contatti = null;
//		 
//		 if(last3.equals(xml))
//		 {
//			 DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
//		     DocumentBuilder builder = factory.newDocumentBuilder();
//			 Document document = builder.parse(path);
//			 contatti = RubricaXML.readContactsXML(document);
//		 }
//		 else if(last3.equals(csv))
//		 {
//			 contatti = Rubrica.readContacts(new File(path));
//		 }
//		 
//		 	Connection connection = null;
//			Statement statement = null;
//			PreparedStatement preparedStatement = null;
//			ResultSet rs = null;	
//
//			try {
//				Class.forName("com.mysql.cj.jdbc.Driver");
//				connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//				String psInsert = "INSERT INTO contatti (name,surname,telephone,email) VALUES (?,?,?,?)";
//				preparedStatement = connection.prepareStatement(psInsert);
//				
//				statement = connection.createStatement();
//				
//				    for(Contatto con : contatti)
//				    {
//					preparedStatement.setString(1, con.getName());
//					preparedStatement.setString(2, con.getLastName());
//					preparedStatement.setString(3, con.getPhone());
//					preparedStatement.setString(4, con.getEmail());	
//					preparedStatement.execute();
//				    }
//				
//							
//			} catch (Exception e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					connection.close();
//					System.out.println(connection.isClosed());
//					
//					
//					statement.close();
//					preparedStatement.close();
//				} catch (SQLException sqlEx) {
//					sqlEx.printStackTrace();
//				}
//			}
//	}
}




 	