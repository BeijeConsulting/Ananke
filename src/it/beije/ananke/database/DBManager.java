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

import org.w3c.dom.Document;
import org.xml.sax.SAXException;

public class DBManager {

	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "c6h12o6&";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/ananke?serverTimezone=CET";

	
	
	//open the menu
	public static  void menuDB(Scanner input) throws ParserConfigurationException, SAXException, IOException
 	{
		String result;
		
		System.out.println("Sei nel menu del database. Scegli una funzionalita:");

			do {
			
			System.out.println("[1] Inserisci un nuovo contatto\n[2] Modifica un contatto\n[3] Cerca un contatto\n[4] "
					+ "Elimina un contatto\n[5] Esporta rubrica su file xml\n[6] Esporta rubrica su file csv\n"
					+ "[7] Importa una rubrica su db.\n[8] Esci");
		
			 result = input.next();
			 
			 switch(result)
			 {
			 case "1": DBManager.aggiungiContatto(input); break;
			 case "2": DBManager.modificaContatto(input); break;
			 case "3": DBManager.cercaContatto(input); break;
			 case "4": DBManager.eliminaContatto(input); break;
			 case "5": DBManager.exportOnXml(input); break;
			 case "6": DBManager.exportOnCsv(input);break;
			 case "7": DBManager.importOnDB(input);break;
			 case "8": System.out.println("Uscita database..");;break;		 
			 }
			 }while(!result.equals("8"));
 	}
	
	public static void aggiungiContatto(Scanner input)
	{
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;	

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			

			String psInsert = "INSERT INTO contatti (name,surname,telephone,email) VALUES (?,?,?,?)";
			preparedStatement = connection.prepareStatement(psInsert);
			
			statement = connection.createStatement();
			
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
				preparedStatement.setString(1, cont.getName());
				preparedStatement.setString(2, cont.getLastName());
				preparedStatement.setString(3, cont.getPhone());
				preparedStatement.setString(4, cont.getEmail());	
				preparedStatement.execute();
				System.out.println("Contatto aggiunto al db");
			}
			else
			{
				System.out.println("Devi inserire almeno un campo. Ritorno al menu..");
			}		
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				System.out.println(connection.isClosed());
				
				//rs.close();
				statement.close();
				preparedStatement.close();
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
	}
	
	public static void cercaContatto(Scanner input)
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;	
		String result;

		try {
			//Class.forName("com.mysql.cj.jdbc.Driver");	
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();
			
			System.out.println("Inserisci un campo(name,surname,ecc..) del contatto da cercare:");
			result = input.next();
			
			rs = statement.executeQuery("SELECT * FROM contatti ");
					  
			
			System.out.println("Contatti trovati:");
			while (rs.next()) {
				System.out.println("id : " + rs.getInt("id"));
				System.out.println("cognome : " + rs.getString("surname"));
				System.out.println("nome : " + rs.getString("name"));
				System.out.println("email : " + rs.getString("email"));
				System.out.println("telefono : " + rs.getString("telephone"));
				System.out.println("-----");
			}
			
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				System.out.println(connection.isClosed());
					
				rs.close();
				statement.close();
			
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
	}

	public static void modificaContatto(Scanner input)
	{
		Connection connection = null;
		Statement statement = null;	
		int id; 
		String result;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();
			
			cercaContatto(input);
			
			System.out.println("Inserisci l'id del contatto da modificare:");
			id = input.nextInt();
			
			System.out.println("Inserisci il campo che vuoi modificare:");
			result = input.next();
			
			switch(result)
			{
			  case "name": System.out.println("Inserisci il nuovo campo:");
			  			   result=input.next();
			  			   statement.executeUpdate("UPDATE contatti set name = '"+result+"' WHERE id ="+id+"");break;
			  			
			  case "surname":System.out.println("Inserisci il nuovo campo:");
 			   				result=input.next();
 			   				statement.executeUpdate("UPDATE contatti set surname = '"+result+"' WHERE id ="+id+"");break;
 			   				
			  case "telephone":System.out.println("Inserisci il nuovo campo:");
  							   result=input.next();
  							   statement.executeUpdate("UPDATE contatti set telephone = '"+result+"' WHERE id ="+id+"");break;
  							   
			  case "email":System.out.println("Inserisci il nuovo campo:");
  						   result=input.next();
  				           statement.executeUpdate("UPDATE contatti set email = '"+result+"' WHERE id ="+id+"");break;
			  
			}
			
			
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				System.out.println(connection.isClosed());
					
				statement.close();
			
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
	}

	public static void eliminaContatto(Scanner input)
	{
		Connection connection = null;
		Statement statement = null;	
		int id; 

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();
			
			cercaContatto(input);
			
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
			
			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				System.out.println(connection.isClosed());
					
				statement.close();
			
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
	}

	public static void exportOnXml(Scanner input)
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;	
		String result;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();
			ArrayList<Contatto> contatti = new ArrayList<>();
			Contatto cont= new Contatto();
			
			System.out.println("Inserisci il path in cui esportare la rubrica:");
			result = input.next();
			
			rs = statement.executeQuery("SELECT * FROM contatti ");
					 
			
			while (rs.next()) {
				cont.setLastName(rs.getString("surname"));
				cont.setName(rs.getString("name"));
				cont.setEmail(rs.getString("email"));
				cont.setPhone(rs.getString("telephone"));
				contatti.add(cont);
				
			}
			
			RubricaXML.writeContactXML(contatti,result);
			
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				System.out.println(connection.isClosed());
					
				rs.close();
				statement.close();
			
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
	}

	public static void exportOnCsv(Scanner input)
	{
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;	
		String result;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");	
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();
			ArrayList<Contatto> contatti = new ArrayList<>();
			Contatto cont= new Contatto();
			
			System.out.println("Inserisci il path in cui esportare la rubrica:");
			result = input.next();
			
			rs = statement.executeQuery("SELECT * FROM contatti ");
					 
			
			while (rs.next()) {
				cont.setLastName(rs.getString("surname"));
				cont.setName(rs.getString("name"));
				cont.setEmail(rs.getString("email"));
				cont.setPhone(rs.getString("telephone"));
				contatti.add(cont);
				
			}
			
			RubricaXML.writeContactXML(contatti,result);
			
			Rubrica.writeContacts(new File(result),contatti);
			
			System.out.println("Rubrica esportata");
			
			System.out.println("Stampo il file:");
			
			Rubrica.printFile(new File(result));
		
						
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				System.out.println(connection.isClosed());
					
				rs.close();
				statement.close();
			
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
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
		 
		 	Connection connection = null;
			Statement statement = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;	

			try {
				Class.forName("com.mysql.cj.jdbc.Driver");
				connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				String psInsert = "INSERT INTO contatti (name,surname,telephone,email) VALUES (?,?,?,?)";
				preparedStatement = connection.prepareStatement(psInsert);
				
				statement = connection.createStatement();
				
				    for(Contatto con : contatti)
				    {
					preparedStatement.setString(1, con.getName());
					preparedStatement.setString(2, con.getLastName());
					preparedStatement.setString(3, con.getPhone());
					preparedStatement.setString(4, con.getEmail());	
					preparedStatement.execute();
				    }
				
							
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				try {
					connection.close();
					System.out.println(connection.isClosed());
					
					
					statement.close();
					preparedStatement.close();
				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
	}
}




 	