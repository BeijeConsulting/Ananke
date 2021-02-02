package it.beije.ananke.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class JDBCmanager {
	
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "Padawan02May4BeWithYou";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/ananke?serverTimezone=CET";

	public static void main(String[] args) {
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			statement = connection.createStatement();
			
			String name = "Franco";
			String surname = "Bianchi";
			
//			statement.execute("INSERT INTO contatti VALUES (null, 'prova','prova','prova','prova')");
			
//			String insert = "INSERT INTO contatti (name,surname) VALUES ('" + name + "','" + surname + "')";
//			System.out.println(insert);
//			statement.execute(insert);
			

			String psInsert = "INSERT INTO contact (name,surname) VALUES (?,?)";

			preparedStatement = connection.prepareStatement(psInsert);
			
			for (int i=1; i <= 2; i++) {
				preparedStatement.setString(1, name+i);
				preparedStatement.setString(2, surname+i);
				
				preparedStatement.execute();
			}
			
//			statement.executeUpdate("UPDATE contatti set name = 'Pippo' WHERE surname = 'prova'");
			
			rs = statement.executeQuery("SELECT * FROM contact");
			
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
				preparedStatement.close();
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
		
	}

}
