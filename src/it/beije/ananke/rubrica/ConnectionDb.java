package it.beije.ananke.rubrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ConnectionDb {

	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "Beije11";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/ananke?serverTimezone=CET";


	public Connection connectionJDBC() {

		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
		} catch (Exception e) {
			e.printStackTrace();
		}
       
		return connection;

	}

}
