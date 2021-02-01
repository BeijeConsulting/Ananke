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
	
	
	public void sciviNuovoContattoInDB(String nome, String cognome, String numTel, String mail) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		if(nome.trim().length() == 0 && cognome.trim().length() == 0 
				&& numTel.trim().length() == 0 && mail.trim().length() == 0) {
			 System.out.println("CONTATTO NON VALIDO, DEVI ISERIRE ALMENO UN CAMPO!");
			 
			 return;
		}
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			

			String psInsert = "INSERT INTO contatti (id,name,surname,telephone,email) VALUES (null,?,?,?,?)";
			preparedStatement = connection.prepareStatement(psInsert);
			
			preparedStatement.setString(2, nome);
			preparedStatement.setString(3, cognome);
			preparedStatement.setString(4, numTel);
			preparedStatement.setString(5, mail);
			
			preparedStatement.execute();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
	}
	
	public static void scriviContattiSuDB(List<Contatto> listaC) throws SQLException {
	
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			for(Contatto c : listaC) {

				String psInsert = "INSERT INTO contatti (id,name,surname,telephone,email) VALUES (null,?,?,?,?)";
				preparedStatement = connection.prepareStatement(psInsert);
			
				preparedStatement.setString(1, c.getNome());
				preparedStatement.setString(2, c.getCognome());
				preparedStatement.setString(3, c.getNumeoroTel());
				preparedStatement.setString(4, c.getMail());
			
				preparedStatement.execute();
			}
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			connection.close();
		}
		
	}
	
	
	public void modificaContattoSuDB(String parolaChiave) {
		//// DA MODIFICARE ////
		List<Integer> listID = new ArrayList<>();
		List<Contatto> list = new ArrayList<>();
		Statement statement = null;
		Connection connection = null;
		ResultSet rs = null;
		//Contatto c = new Contatto();
		int i = 0;
		Scanner s = new Scanner(System.in);
		int idDaModificare;
		
		
			
			try{
				
				connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
				
				statement = connection.createStatement();
				
				rs = statement.executeQuery("SELECT *"
	                 	  + "FROM contatti "
						  + "WHERE name = " + "'" + parolaChiave + "'"
						  + "OR surname = " + "'" + parolaChiave + "'"
						  + "OR email = " + "'" + parolaChiave + "'"
						  + "OR thelephone = " + "'" + parolaChiave + "'");
				
				while (rs.next()) {
					
					Contatto c = new Contatto();
					listID.add(rs.getInt("id"));
					c.setNome( rs.getString("name"));
					c.setCognome(rs.getString("surname"));
					c.setMail(rs.getString("email"));
					c.setNumeroTel(rs.getString("telephone"));

					list.add(c);
				}
				
				if(list.size() == 0) {
					
					System.out.println("CONTATTO NON TROVATO!");
					return;
					
				}
				
				if(list.size() == 1) {
					
					statement.executeQuery("DELETE FROM contatti WHERE id = " + listID.get(0));
					
				}else{
					
					System.out.println();
					System.out.println("------------------------------");
					System.out.println("QAULE CONTATTO VUOI MODIFICARE!");
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
				idDaModificare = listID.get(comando - 1);
				
				System.out.println("------------------------------");
				System.out.println("QAULE CAMPO VUOI MODIFICARE!");
				System.out.println("------------------------------");
				System.out.println("1) Nome");
				System.out.println("2) Cognome");
				System.out.println("3) Numero di telefono");
				System.out.println("4) Email");
				String cmd = s.next();
				
				switch(cmd) {
				
				case "1":
					
					System.out.println("Inserisci nuovo nome:");
					cmd = s.next();
					statement.executeQuery("UPDATE contatti SET name = '" + cmd  + "' WHERE id = " + idDaModificare);
					
					break;
					
				case "2":
					
					System.out.println("Inserisci nuovo cognome:");
					cmd = s.next();
					statement.executeQuery("UPDATE contatti SET surname = '" + cmd  + "' WHERE id = " + idDaModificare);
					
					
					break;
				
				case "3":
					
					System.out.println("Inserisci nuovo numero di telefono:");
					cmd = s.next();
					statement.executeQuery("UPDATE contatti SET telephone = '" + cmd  + "' WHERE id = " + idDaModificare);
					
					break;
				
				case "4":
					
					System.out.println("Inserisci nuova email:");
					cmd = s.next();
					statement.executeQuery("UPDATE contatti SET email = '" + cmd  + "' WHERE id = " + idDaModificare);
					
					break;
				
				}
				
				statement.executeQuery("DELETE FROM contatti WHERE id = " + idDaModificare);
				}

			}catch(SQLException sqlEx) {

				sqlEx.printStackTrace();
				
			}finally {
				
				try {
					
					s.close();  // SCANNER
					connection.close();
					//System.out.println(connection.isClosed());
					rs.close();
					statement.close();

				} catch (SQLException sqlEx) {
					sqlEx.printStackTrace();
				}
			}
		
	}

			

			
		

	
	
	public static void eliminaContattoSuDB(String parolaChiave) {
		

		List<Integer> listID = new ArrayList<>();
		List<Contatto> list = new ArrayList<>();
		Statement statement = null;
		Connection connection = null;
		ResultSet rs = null;
		//Contatto c = new Contatto();
		int i = 0;
		Scanner s = new Scanner(System.in);
		int idDaEliminare;
		
		try {
			
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			statement = connection.createStatement();
			
			rs = statement.executeQuery("SELECT *"
                 	  + "FROM contatti "
					  + "WHERE name = " + "'" + parolaChiave + "'"
					  + "OR surname = " + "'" + parolaChiave + "'"
					  + "OR email = " + "'" + parolaChiave + "'"
					  + "OR telephone = " + "'" + parolaChiave + "'");
			
			if(rs == null) {return;}
			while (rs.next()) {
				
				Contatto c = new Contatto();
				listID.add(Integer.parseInt(rs.getString("id")));
				c.setNome(rs.getString("name"));
				c.setCognome(rs.getString("surname"));
				c.setMail(rs.getString("email"));
				c.setNumeroTel(rs.getString("telephone"));

				list.add(c);
			}
			
			if(list.size() == 1) {
				System.out.println(listID.get(0).intValue());
				statement.execute("DELETE FROM contatti WHERE id = " + listID.get(0).intValue());
				
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
				//System.out.println(connection.isClosed());
				rs.close();
				statement.close();

			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
			
		}

		
	}
	
	public static void cercaContattoSuDB(String parolaChiave) {
		
		List<Integer> listID = new ArrayList<>();
		List<Contatto> list = new ArrayList<>();
		Statement statement = null;
		Connection connection = null;
		ResultSet rs = null;
		//Contatto c = new Contatto();

		
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			statement = connection.createStatement();
			
			rs = statement.executeQuery("SELECT *"
                 	  + "FROM contatti "
					  + "WHERE name = " + "'" + parolaChiave + "'"
					  + "OR surname = " + "'" + parolaChiave + "'"
					  + "OR email = " + "'" + parolaChiave + "'"
					  + "OR telephone = " + "'" + parolaChiave + "'");
			
			while (rs.next()) {

				Contatto c = new Contatto();
				listID.add(rs.getInt("id"));
				c.setNome( rs.getString("name"));
				c.setCognome(rs.getString("surname"));
				c.setMail(rs.getString("email"));
				c.setNumeroTel(rs.getString("telephone"));
				
				list.add(c);
			}
			
			for(Contatto contatto : list) {
				System.out.print(contatto.getNome() + ";");
				System.out.print(contatto.getCognome() + ";");
				System.out.print(contatto.getNumeoroTel() + ";");
				System.out.println(contatto.getMail() + ";");
			}
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				
				connection.close();
			//	System.out.println(connection.isClosed());
				rs.close();
				statement.close();

			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
			
		}

		
	}
	
	public static List<Contatto> caricaContattiDaDB() {
		
		List<Contatto> listC = new ArrayList<>();
		Statement statement = null;
		Connection connection = null;
		ResultSet rs = null;
		//Contatto c = new Contatto();
		
		try {
			connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
			statement = connection.createStatement();
			
			rs = statement.executeQuery("SELECT * FROM contatti");
			
			while (rs.next()) {
				//System.out.println(rs.getString("name"));
				Contatto c = new Contatto();
				c.setNome(rs.getString("name"));
				c.setCognome(rs.getString("surname"));
				c.setMail(rs.getString("email"));
				c.setNumeroTel(rs.getString("telephone"));
				//System.out.println(c.getNome());
				listC.add(c);
				
			}

			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			try {
				
				connection.close();
				//System.out.println(connection.isClosed());
				rs.close();
				statement.close();

			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
			
		}
		
		return listC;
	}


}
