import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class JDBCManager {
	
	public static final String DB_USER = "hbstudent";
	public static final String DB_PASSWORD = "hbstudent";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/ananke?serverTimezone=CET";
	
	
	static Connection connection = null;
	static Statement statement = null;
	static PreparedStatement preparedStatement = null;
	static ResultSet rs = null;

	static void connettiDb() {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
	
	}
	
	public static void chiudiConnessioneDb() {
		
		try {
			connection.close();
			System.out.println("Connessione chiusa: " + connection.isClosed());
			
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
		
	}
	
	public static void inserisciContattoDb(Contatto contatto) throws SQLException {
		
		String nome = contatto.getName();
		
		if(nome.contentEquals(".")) {
			nome = null;
		}
		
		String cognome = contatto.getSurname();
		
		if(cognome.contentEquals(".")) {
			cognome = null;
		}
		
		String telefono = contatto.getTelephone();
		
		if(telefono.contentEquals(".")) {
			telefono = null;
		}
		
		String email = contatto.getEmail();
		
		if(email.contentEquals(".")) {
			email = null;
		}
		
		statement = connection.createStatement();
		
		String insert = "INSERT INTO contatti (name, surname, telephone, email) VALUES ('" + nome + "','" + cognome + "','" + telefono + "','" + email + "')";
		System.out.println(insert);
		statement.execute(insert);
		
		statement.close();
		
	}
	
	public static Contatto cercaContattoDb(String nome, String cognome) throws SQLException {
		
		statement = connection.createStatement();
		
		String search = "SELECT * FROM contatti WHERE name = '" + nome + "' and surname = '" + cognome + "'";
		
		rs = statement.executeQuery(search);
		
		ArrayList<Contatto> trovati = new ArrayList<>();
		
		while(rs.next()) {
			trovati.add(new Contatto(rs.getString("name"),rs.getString("surname"),rs.getString("telephone"),rs.getString("email")));
		}
		
		Contatto tempContatto = trovati.get(0);
		
		// rs.close();
		
		return tempContatto;
		
	}
	
	public static void eliminaContattoDb(Contatto contatto) throws SQLException {
		
		String nome = contatto.getName();
		String cognome = contatto.getSurname();
		String telefono = contatto.getTelephone();
		String email = contatto.getEmail();
		
		statement = connection.createStatement();
		
		String delete =  "DELETE from contatti where name = '" + nome + "' and surname = '" + cognome + "' and telephone = '" + telefono + "' and email = '" + email + "'";
		
		System.out.println(delete);
		statement.execute(delete);
		
		statement.close();
		
	}
	
	public static void modificaNomeContattoDb(Contatto contatto, String nomeNuovo) throws SQLException {
		
		String telefono = contatto.getTelephone();
		
		statement = connection.createStatement();
		
		// statement.executeUpdate("UPDATE contatti set name = 'Pippo' WHERE surname = 'prova'");
		String update = "UPDATE contatti set name = '" + nomeNuovo + "' WHERE telephone = '" + telefono + "'";
		
		System.out.println(update);
		statement.executeUpdate(update);
		
		
	}
	
	public static void modificaCognomeContattoDb(Contatto contatto, String cognomeNuovo) throws SQLException {
		
		String telefono = contatto.getTelephone();
		
		statement = connection.createStatement();
		
		// statement.executeUpdate("UPDATE contatti set name = 'Pippo' WHERE surname = 'prova'");
		String update = "UPDATE contatti set surname = '" + cognomeNuovo + "' WHERE telephone = '" + telefono + "'";
		
		System.out.println(update);
		statement.executeUpdate(update);
		
	}
	
	public static void modificaTelefonoContattoDb(Contatto contatto) {
		
	}
	
	public static void modificaEmailContattoDb(Contatto contatto,  String emailNuova) throws SQLException {
		
		String telefono = contatto.getTelephone();
		
		statement = connection.createStatement();
		
		// statement.executeUpdate("UPDATE contatti set name = 'Pippo' WHERE surname = 'prova'");
		String update = "UPDATE contatti set email = '" + emailNuova + "' WHERE telephone = '" + telefono + "'";
		
		System.out.println(update);
		statement.executeUpdate(update);
		
	}
	
	public static void visualizzaContattiDb() throws SQLException {
		
		statement = connection.createStatement();
		
		rs = statement.executeQuery("SELECT * FROM contatti");
		
		while (rs.next()) {
			System.out.println("id : " + rs.getInt("id"));
			System.out.println("cognome : " + rs.getString("surname"));
			System.out.println("nome : " + rs.getString("name"));
			System.out.println("email : " + rs.getString("email"));
			System.out.println("telefono : " + rs.getString("telephone"));
			System.out.println("-----");
		}
		
		connection.close();
		
	}
	
	public static ArrayList<Contatto> listaContattiDb() throws SQLException {
		
		statement = connection.createStatement();
		
		rs = statement.executeQuery("SELECT * FROM contatti");
		
		ArrayList<Contatto> lista = new ArrayList<>();
		
		while(rs.next()) {
			lista.add(new Contatto(rs.getString("name"),rs.getString("surname"),rs.getString("telephone"),rs.getString("email")));
		}
		
		   rs.close();
		
		return lista;
	}
	
}
