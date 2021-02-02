package it.beije.ananke.hib.prova;

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
				//				System.out.println(connection.isClosed());

				//rs.close();
				statement.close();
				//preparedStatement.close();
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
	}

	public void inserisciConPreparedStatement(){
		Scanner scanner = new Scanner(System.in);
		Connection connection = null;
		Statement statement = null;
		ResultSet rs =null;
		PreparedStatement preparedStatement = null;
		try {
			System.out.println("Inserisci il numero di contatti da aggiungere");
			String nuoviContatti = scanner.nextLine();
			int nc = Integer.parseInt(nuoviContatti);

			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();
			String psInsert = "INSERT INTO contatti (name,surname,telephone,email) VALUES (?,?,?,?)";
			preparedStatement = connection.prepareStatement(psInsert);
			//rs = preparedStatement.getResultSet();
			for (int i=0;i< nc;i++) {	
				System.out.println("------------------------------------------");
				System.out.println("Inserisci il nome dell' utente");
				String name = scanner.nextLine();
				System.out.println("Inserisci il cognome dell' utente");
				String surname = scanner.nextLine();
				System.out.println("Inserisci il recapito telefonico dell' utente");
				String telephone = scanner.nextLine();
				System.out.println("Inserisci l' indirizzo email dell' utente");
				String email = scanner.nextLine();	
				preparedStatement.setString(1, name);
				preparedStatement.setString(2, surname);
				preparedStatement.setString(3, telephone);
				preparedStatement.setString(4, email);
				preparedStatement.execute();
				System.out.println("Il contatto è stato inserito");
				System.out.println("------------------------------------------");
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally {
			try {
				connection.close();
				//rs.close();
				statement.close();
				preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void LeggiRubrica(){
		Connection connection = null;
		Statement statement = null;
		ResultSet rs =null;

		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM contatti");
			ArrayList<Contatto> contattiDB = new ArrayList<Contatto>();
			System.out.println("LEGGO LA RUBRICA:");
			while (rs.next()) {
				System.out.println("id : " + rs.getInt("id"));
				System.out.println("cognome : " + rs.getString("surname"));
				System.out.println("nome : " + rs.getString("name"));
				System.out.println("email : " + rs.getString("email"));
				System.out.println("telefono : " + rs.getString("telephone"));
				System.out.println("------------------------------------------");
				contattiDB.add(new Contatto(rs.getString("name"),rs.getString("surname"),rs.getString("telephone"),rs.getString("email")));
			}	
			System.out.println("RUBRICA LETTA FINO ALLA FINE.");

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

	public void CancellaDaRubrica(){
		Connection connection = null;
		Statement statement = null;
		//	ResultSet rs =null;
		Scanner scanner = new Scanner(System.in);
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();
			//LeggiRubrica();
			System.out.println("Inserisci la mail del contatto da eliminare");
			String m = scanner.nextLine();
			int rs = statement.executeUpdate("Delete from contatti where email = '" + m +"'");
			System.out.println("Eliminazione effettuata");
			System.out.println("------------------------------------------");
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

	public void ModificaDaRubrica(){		
		Scanner scanner = new Scanner(System.in);
		Connection connection = null;;
		Statement statement =null;
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();
			//LeggiRubrica();
			System.out.println("Inserisci la mail del contatto da modificare");
			String m = scanner.nextLine();

			System.out.println("Inserisci il nuovo nome dell' utente");
			String name = scanner.nextLine();
			System.out.println("Inserisci il nuovo cognome dell' utente");
			String surname = scanner.nextLine();
			System.out.println("Inserisci il nuovo recapito telefonico dell' utente");
			String telephone = scanner.nextLine();
			System.out.println("Inserisci l' indirizzo email dell' utente");
			String email = scanner.nextLine();
			int rs = statement.executeUpdate("UPDATE contatti set name = '" + name +"', surname = '" + surname +"' , telephone = '" + telephone +"' , email = '" + email + "' WHERE email = '" + m +"' ");
			System.out.println("Modifica effettuata");
			System.out.println("------------------------------------------");
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

	public void InserisciInRubrica(){
		Scanner scanner = new Scanner(System.in);
		Connection connection = null;;
		Statement statement =null;
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();
			System.out.println("------------------------------------------");
			System.out.println("Inserisci il nome dell' utente");
			String name = scanner.nextLine();
			System.out.println("Inserisci il cognome dell' utente");
			String surname = scanner.nextLine();
			System.out.println("Inserisci il recapito telefonico dell' utente");
			String telephone = scanner.nextLine();
			System.out.println("Inserisci l' indirizzo email dell' utente");
			String email = scanner.nextLine();
			System.out.println("------------------------------------------");
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

	public void EsportaContattiDaRubricaDB_aCsv(){
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

	public void Ricerca(){
		Scanner scanner = new Scanner(System.in);
		Connection connection = null;
		Statement statement = null;
		ResultSet rs =null;

		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			statement = connection.createStatement();
			//	LeggiRubrica();
			System.out.println("Inserisci  'email' se vuoi ricercare per email, altrimenti inserisci 'telefono'");
			String emOrTel = scanner.nextLine();
			if(emOrTel.equalsIgnoreCase("email")) {
				System.out.println("Inserisci la mail del contatto da ricercare");
				String em = scanner.nextLine();
				rs = statement.executeQuery("SELECT * FROM contatti where email = '"+em+"' ");
				while (rs.next()) {
					System.out.println("------------------------------------------");
					System.out.println("id : " + rs.getInt("id"));
					System.out.println("cognome : " + rs.getString("surname"));
					System.out.println("nome : " + rs.getString("name"));
					System.out.println("telefono : " + rs.getString("telephone"));
					System.out.println("email : " + rs.getString("email"));
					System.out.println("------------------------------------------");
				}
			}
			else if(emOrTel.equalsIgnoreCase("telefono")){
				System.out.println("Inserisci il telefono del contatto da ricercare");
				String tel = scanner.nextLine();
				rs = statement.executeQuery("SELECT * FROM contatti where telephone = '"+tel+"' ");
				while (rs.next()) {
					System.out.println("------------------------------------------");
					System.out.println("id : " + rs.getInt("id"));
					System.out.println("cognome : " + rs.getString("surname"));
					System.out.println("nome : " + rs.getString("name"));
					System.out.println("telefono : " + rs.getString("telephone"));
					System.out.println("email : " + rs.getString("email"));
					System.out.println("------------------------------------------");
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

	public void ImportaContattiDaCsv_InDb(ArrayList<Contatto> arr){
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

	public void ImportaContattiDaXml_InDb(ArrayList<Contatto> arr){
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