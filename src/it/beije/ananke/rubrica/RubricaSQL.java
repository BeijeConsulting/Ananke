package it.beije.ananke.rubrica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RubricaSQL {
	
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "Beije06";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/ananke?serverTimezone=CET";

	
	public static void provaConnessione() throws SQLException {
		Connection connection = null;

		try {
		
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			if(connection.isClosed() == false)
				System.out.println("CONNESSIONE STABILITA!");
			else
				System.out.println("NON C'E' CONNESSIONE!!!!!!");
		
		}catch(Exception e) {
			
		}finally {
			
			connection.close();
		}
	}
	
	
	public void sciviNuovoContattoInDB(String nome, String cognome, String numTel, String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
	
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			

			String psInsert = "INSERT INTO contatti (id,name,surname,telephone,email) VALUES (null,?,?,?,?)";
			preparedStatement = connection.prepareStatement(psInsert);
			
			preparedStatement.setString(2, nome);
			preparedStatement.setString(3, cognome);
			preparedStatement.setString(4, numTel);
			preparedStatement.setString(5, email);
			
			preparedStatement.execute();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
	}
	
	public void scriviContattiSuDB(List<Contatto> listaC) throws SQLException {
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			for(Contatto c : listaC) {

				String psInsert = "INSERT INTO contatti (id,name,surname,telephone,email) VALUES (null,?,?,?,?)";
				preparedStatement = connection.prepareStatement(psInsert);
			
				preparedStatement.setString(2, c.getNome());
				preparedStatement.setString(3, c.getCognome());
				preparedStatement.setString(4, c.getNumeoroTel());
				preparedStatement.setString(5, c.getMail());
			
				preparedStatement.execute();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		
	}
	
	
	public void modificaContattoSuDB() {
		//// DA MODIFICARE ////
		List<Contatto> list = new ArrayList<>();
		Statement statement = null;
		Connection connection = null;
		ResultSet rs = null;
		
		
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			statement = connection.createStatement();
			
			statement.executeQuery(  "UPDATE ananke.contatti"
										+ "SET surname = 'Neri'"
										+ "WHERE id = 1");
			

			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
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
	
	public void eliminaContattoSuDB(String parolaChiave) {
		

		List<Integer> listID = new ArrayList<>();
		List<Contatto> list = new ArrayList<>();
		Statement statement = null;
		Connection connection = null;
		ResultSet rs = null;
		Contatto c = new Contatto();
		int i = 0;
		Scanner s = new Scanner(System.in);
		int idDaEliminare;
		
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			statement = connection.createStatement();
			
			rs = statement.executeQuery("SELECT *"
                 	  + "FROM contatti "
					  + "WHERE name = " + " ' " + parolaChiave + " ' "
					  + "OR surname = " + " ' " + parolaChiave + " ' "
					  + "OR email = " + " ' " + parolaChiave + " ' "
					  + "OR thelephone = " + " ' " + parolaChiave + " ' ");
			
			while (rs.next()) {

				listID.add(rs.getInt("id"));
				c.setNome( rs.getString("name"));
				c.setCognome(rs.getString("surname"));
				c.setMail(rs.getString("email"));
				c.setNumeroTel(rs.getString("telephone"));

				list.add(c);
			}
			
			if(list.size() == 1) {
				
				statement.executeQuery("DELETE FROM contatti WHERE id = " + listID.get(0));
				
			}else {
				
				System.out.println();
				System.out.println("------------------------------");
				System.out.println("QAULE CONTATTO VUOI ELIMINARE!");
				System.out.println("------------------------------");

				for(Contatto contatto : list) {
					i++;
					System.out.print( i + " - ( ");
	
					System.out.print(contatto.getNome() + "  " + contatto.getCognome() + "  " 
							+ contatto.getNumeoroTel() + "  " + contatto.getMail());
	
					System.out.println(" )");
				}

				System.out.println("-----------------------------------------");
				
			
			
			int comando = Integer.parseInt(s.next());
			idDaEliminare = listID.get(comando - 1);
			
			statement.executeQuery("DELETE FROM contatti WHERE id = " + idDaEliminare);
		
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				
				s.close();  // SCANNER
				connection.close();
				System.out.println(connection.isClosed());
				rs.close();
				statement.close();

			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
			
		}

		
	}
	
	public static List<Contatto> caricaContattiDaDB() {
		
		List<Contatto> list = new ArrayList<>();
		Statement statement = null;
		Connection connection = null;
		ResultSet rs = null;
		Contatto c = new Contatto();
		
		
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			statement = connection.createStatement();
			
			rs = statement.executeQuery("SELECT * FROM rubrica");
			
			while (rs.next()) {

				c.setNome( rs.getString("name"));
				c.setCognome(rs.getString("surname"));
				c.setMail(rs.getString("email"));
				c.setNumeroTel(rs.getString("telephone"));
				
				list.add(c);

			}
			

			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				
				connection.close();
				System.out.println(connection.isClosed());
				rs.close();
				statement.close();

			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
			
		}
		
		return list;
	}


}
