package it.beije.ananke.rubrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Types;
import java.util.ArrayList;
import java.util.List;

public class DBManager {
	private String DB_USER;
	private String DB_PASSWORD;
	private String DB_URL;

	public DBManager(String DB_USER, String DB_PASSWORD, String DB_URL) {
		this.DB_USER = DB_USER;
		this.DB_PASSWORD = DB_PASSWORD;
		this.DB_URL = DB_URL;
	}
	
	public Connection getConnection() {
		Connection connection = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return connection;
	}
	
	public void closeConnection(Connection connection) {
		try {
			connection.close();
		} catch (SQLException sqlEx) {
			sqlEx.printStackTrace();
		}
	}
	
	public void aggiungiContattoDB(Contatto contatto) {
		Connection connection = getConnection();
		String insertContatto = "INSERT INTO contatti(name,surname,email,telephone) VALUES ('" 
		+ contatto.getNome() + "','" + contatto.getCognome() + "','" + contatto.getEmail() + "','" 
		+ contatto.getTelefono() + "')";
		try {
			Statement s = connection.createStatement();
			s.execute(insertContatto);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		closeConnection(connection);
	}

	public void ricercaContattoDB(Contatto contatto) {
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		String ricerca = "SELECT name,surname,email,telephone FROM contatti WHERE "
				+ "(name=? OR ?) AND "
				+ "(surname=? OR ?) AND "
				+ "(email=? OR ?) AND "
				+ "(telephone=? OR ?)";
		
		try {
			preparedStatement = connection.prepareStatement(ricerca);				
			preparedStatement.setString(1, contatto.getNome());
			
			if(contatto.getNome().equals("")) {
				preparedStatement.setBoolean(2,true);
			}else {
				preparedStatement.setBoolean(2,false);
			}
			
			preparedStatement.setString(3, contatto.getCognome());
			
			if(contatto.getCognome().equals("")) {
				preparedStatement.setBoolean(4,true);
			}else {
				preparedStatement.setBoolean(4,false);
			}
			
			preparedStatement.setString(5, contatto.getEmail());
			
			if(contatto.getEmail().equals("")) {
				preparedStatement.setBoolean(6,true);
			}else {
				preparedStatement.setBoolean(6,false);
			}
			
			preparedStatement.setString(7, contatto.getTelefono());
			
			if(contatto.getTelefono().equals("")) {
				preparedStatement.setBoolean(8,true);
			}else {
				preparedStatement.setBoolean(8,false);
			}
			
			rs = preparedStatement.executeQuery();
			
//			if(rs.getFetchSize() == 0) {
//				System.out.println("Nessun contatto trovato");
//			}else {
//			
				while (rs.next()) {
					System.out.println("cognome : " + rs.getString("surname"));
					System.out.println("nome : " + rs.getString("name"));
					System.out.println("email : " + rs.getString("email"));
					System.out.println("telefono : " + rs.getString("telephone"));
					System.out.println("-----");
				}
//			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		closeConnection(connection);
	}
	
	public void eliminaContattoDB(String email) {
		//ricercaContattoDB(email);
		Connection connection = getConnection();
		PreparedStatement preparedStatement = null;
		String ricerca = "DELETE FROM contatti WHERE email=?";

		try {
			preparedStatement = connection.prepareStatement(ricerca);	
			preparedStatement.setString(1, email);
			preparedStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}		
	}
	public void modificaContattoDB(Contatto contattoModificato) {
		
	}
	
	public void importaDB(List<Contatto> contatti) {
		for(Contatto contatto : contatti) {
			aggiungiContattoDB(contatto);
		}
		System.out.println("Contatti importati");
	}
	
	public List<Contatto> esportaDB() {
		Connection connection = getConnection();
		String select = "SELECT * FROM contatti";
		Statement statement = null;
		ResultSet rs = null;
		Contatto contatto = null;
		List<Contatto> contatti = new ArrayList<Contatto>();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery(select);	
			while(rs.next()) {
				contatto = new Contatto();
				contatto.setNome(rs.getString("name"));
				contatto.setCognome(rs.getString("surname"));
				contatto.setEmail(rs.getString("email"));
				contatto.setTelefono(rs.getString("telephone"));
				contatti.add(contatto);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}		
		return contatti;
	}
}
