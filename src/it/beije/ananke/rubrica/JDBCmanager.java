package it.beije.ananke.rubrica;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

public class JDBCmanager {

	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "Beije08";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/rubrica?serverTimezone=CET";

	public JDBCmanager() {
		Connection connection = null;
		Statement statement = null;
		//		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();
			//			EsportaContattiDaRubricaDB_aCsv();
			//			CancellaDaRubrica();
			//			LeggiRubrica();
			//			ModificaDaRubrica();
			//			InserisciInRubrica();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				System.out.println(connection.isClosed());

				//rs.close();
				statement.close();
				//preparedStatement.close();
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
	}

	public static void LeggiRubrica(){
		Connection connection = null;
		Statement statement = null;
		ResultSet rs =null;

		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM contatti");
			ArrayList<Contatto> contattiDB = new ArrayList<Contatto>();
			while (rs.next()) {
				System.out.println("id : " + rs.getInt("id"));
				System.out.println("cognome : " + rs.getString("surname"));
				System.out.println("nome : " + rs.getString("name"));
				System.out.println("email : " + rs.getString("email"));
				System.out.println("telefono : " + rs.getString("telephone"));
				System.out.println("-----");
				contattiDB.add(new Contatto(rs.getString("name"),rs.getString("surname"),rs.getString("telephone"),rs.getString("email")));
			}
			//System.out.println(contattiDB);
			//			CsvManager cs = new CsvManager("/Users/Padawan08/Desktop/esportatoDaDB.csv");
			//			cs.importaRubricaDaDB(contattiDB);	
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				rs.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void CancellaDaRubrica(){
		Connection connection = null;
		Statement statement = null;
		//	ResultSet rs =null;
		Scanner scanner = new Scanner(System.in);
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();
			LeggiRubrica();
			System.out.println("Inserisci l' id del contatto da eliminare");
			String id = scanner.nextLine();
			int ID = Integer.parseInt(id);
			int rs = statement.executeUpdate("Delete from contatti where Id = '" + ID +"'");
			System.out.println("Eliminazione effettuata");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				connection.close();
				//rs.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void ModificaDaRubrica(){		
		Scanner scanner = new Scanner(System.in);
		Connection connection = null;;
		Statement statement =null;
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();
			LeggiRubrica();
			System.out.println("Inserisci l' id del contatto da modificare");
			String iD = scanner.nextLine();
			int ID = Integer.parseInt(iD);
			System.out.println("Inserisci il nuovo nome dell' utente");
			String name = scanner.nextLine();
			System.out.println("Inserisci il nuovo cognome dell' utente");
			String surname = scanner.nextLine();
			System.out.println("Inserisci il nuovo recapito telefonico dell' utente");
			String telephone = scanner.nextLine();
			System.out.println("Inserisci l' indirizzo email dell' utente");
			String email = scanner.nextLine();
			int rs = statement.executeUpdate("UPDATE contatti set name = '" + name +"', surname = '" + surname +"' , telephone = '" + telephone +"' , email = '" + email + "' WHERE id = '" + ID +"' ");
			System.out.println("Modifica effettuata");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void InserisciInRubrica(){
		Scanner scanner = new Scanner(System.in);
		Connection connection = null;;
		Statement statement =null;
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();
			System.out.println("Inserisci il nome dell' utente");
			String name = scanner.nextLine();
			System.out.println("Inserisci il cognome dell' utente");
			String surname = scanner.nextLine();
			System.out.println("Inserisci il recapito telefonico dell' utente");
			String telephone = scanner.nextLine();
			System.out.println("Inserisci l' indirizzo email dell' utente");
			String email = scanner.nextLine();
			statement.execute("INSERT INTO contatti VALUES (null, '" +name+ "', '" +surname+ "','" +telephone+ "', '" +email+"')");
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public static void EsportaContattiDaRubricaDB_aCsv(){
		Connection connection = null;
		Statement statement = null;
		ResultSet rs = null;
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();;
			rs = statement.executeQuery("SELECT * FROM contatti");
			ArrayList<Contatto> contattiDB = new ArrayList<Contatto>();
			while (rs.next()) {
				contattiDB.add(new Contatto(rs.getString("name"),rs.getString("surname"),rs.getString("telephone"),rs.getString("email")));
			}
			CsvManager cs = new CsvManager("/Users/Padawan08/Desktop/esportatoDaDB.csv");
			cs.importaRubricaDaDB(contattiDB);	
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				rs.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


	public static void Ricerca(){
		Scanner scanner = new Scanner(System.in);
		Connection connection = null;
		Statement statement = null;
		ResultSet rs =null;

		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();
			LeggiRubrica();
			System.out.println("Inserisci  'email' se vuoi ricercare per email, altrimenti inserisci 'telefono'");
			String emOrTel = scanner.nextLine();
			if(emOrTel.equalsIgnoreCase("email")) {
				System.out.println("Inserisci la mail del contatto da ricercare");
				String em = scanner.nextLine();
				rs = statement.executeQuery("SELECT * FROM contatti where email = '"+em+"' ");
				while (rs.next()) {
					System.out.println("id : " + rs.getInt("id"));
					System.out.println("cognome : " + rs.getString("surname"));
					System.out.println("nome : " + rs.getString("name"));
					System.out.println("email : " + rs.getString("email"));
					System.out.println("telefono : " + rs.getString("telephone"));

				}
			}
			else if(emOrTel.equalsIgnoreCase("telefono")){
				System.out.println("Inserisci il telefono del contatto da ricercare");
				String tel = scanner.nextLine();
				rs = statement.executeQuery("SELECT * FROM contatti where telephone = '"+tel+"' ");
				while (rs.next()) {
					System.out.println("id : " + rs.getInt("id"));
					System.out.println("cognome : " + rs.getString("surname"));
					System.out.println("nome : " + rs.getString("name"));
					System.out.println("email : " + rs.getString("email"));
					System.out.println("telefono : " + rs.getString("telephone"));
				}
			}
			else {
				System.out.println("PUOI CERCARE SOLO PER EMAIL O PER TELEFONO");
			}
		}
		catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	public static void ImportaContattiDaCsv_InDb(ArrayList<Contatto> arr){
		Connection connection = null;
		Statement statement = null;
		//ResultSet rs = null;
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();
			int rs = statement.executeUpdate("Delete from contatti");
			for (Contatto contatto : arr) {
				statement.execute("INSERT INTO contatti VALUES (null, '" +contatto.getName()+ "', '" +contatto.getSurname()+ "','" +contatto.getTelephone()+ "', '" +contatto.getEmail()+"')");

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				//rs.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void ImportaContattiDaXml_InDb(ArrayList<Contatto> arr){
		Connection connection = null;
		Statement statement = null;
		//ResultSet rs = null;
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();
			int rs = statement.executeUpdate("Delete from contatti");
			for (Contatto contatto : arr) {
				statement.execute("INSERT INTO contatti VALUES (null, '" +contatto.getName()+ "', '" +contatto.getSurname()+ "','" +contatto.getTelephone()+ "', '" +contatto.getEmail()+"')");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				//rs.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}