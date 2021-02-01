package it.beije.ananke.rubrica;
import java.util.*;
import java.sql.*;

public class JDBCManager {
	public static final String DB_USER = "root";
	public static final String DB_Password = "salop";
	public static final String DB_URL = "jdbc:mysql://127.0.0.1:3306/ananke?serverTimezone=CET";
	
	private static Connection getConnection() {
		Connection con = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			 con = DriverManager.getConnection(DB_URL, DB_USER, DB_Password);
			 
			
		} catch (Exception e) {
			
			e.printStackTrace();
		} finally {
			return con;
		}
		
	}

	public JDBCManager() {
		
	}
	
	public static List<Contatti> readDb() throws SQLException{
		List<Contatti> l = new ArrayList<>();
		Connection con = getConnection();
		String query = "SELECT * FROM contatti";
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery(query);
		while(rs.next()) {
			l.add(new Contatti(rs.getString("name"),rs.getString("surname"), rs.getString("email"), rs.getString("telephone")));
		}
		con.close();
		return l;
	}
	
	public static List<Contatti> containContacts(String campo) throws SQLException {
		List <Contatti> l = new ArrayList<>();
		Connection con = getConnection();
//		String query = "SELECT * FROM contatti WHERE email = " + campo;
//		Statement stmt = con.createStatement();
//		ResultSet rs = stmt.executeQuery(query);
		ResultSet rs;
		PreparedStatement preparedStatement;
		String psInsert = "Select * from contatti WHERE email = ?" ;
		preparedStatement = con.prepareStatement(psInsert);
		preparedStatement.setString(1,campo);
		rs = preparedStatement.executeQuery();
		
		
		
			while(rs.next()) {
				
				l.add(new Contatti(rs.getString("name"),rs.getString("surname"), rs.getString("email"), rs.getString("telephone")));
			}
//			stmt.close();
			rs.close();
			con.close();
			if(l.isEmpty()) {
				System.out.println("Non ci sono contatti con questa email.");
			} else {
				for(Contatti c: l) {
					System.out.println(c.toString());
				}
			}
			return l;
		
	}
	
	public static boolean addContacts(List<Contatti> l) throws SQLException {
		Connection con = getConnection();
		PreparedStatement preparedStatement;
		
		String psInsert = "INSERT INTO contatti (name,surname,email,telephone) VALUES (?,?,?,?)";
		preparedStatement = con.prepareStatement(psInsert);
		
		for(Contatti c : l) {
			preparedStatement.setString(1, c.getName());
			preparedStatement.setString(2, c.getSurname());
			preparedStatement.setString(3, c.getEmail());
			preparedStatement.setString(4, c.getCell());
			preparedStatement.execute();
		}
		preparedStatement.close();
		con.close();
		return true;
	}
	
	public static boolean deleteContact(String campo) {
		Connection con = getConnection();
		PreparedStatement preparedStatement = null;
		
		List<Contatti> l = new ArrayList<>();
		try {
			l = containContacts(campo);
			if(l.isEmpty()) {
				con.close();
				return false;
			} else {
				
				String psInsert = "DELETE from contatti WHERE email = ?" ;
				preparedStatement = con.prepareStatement(psInsert);
				preparedStatement.setString(1,campo);
				preparedStatement.execute();
				preparedStatement.close();
				con.close();
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return true;
	}
	
}
