package it.beije.ananke.database;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import it.beije.ananke.file.Rubrica;
import it.beije.ananke.file.Contatto;


public class DataManager {
	public Rubrica r = new Rubrica();
	
	public static void main(String[] args) {
		DataManager data = new DataManager();
		menu();
		Scanner s = new Scanner(System.in);
		String st = s.next();

		while (!st.equalsIgnoreCase("5")) {
			data.choice(st, s);
			menu();
			st = s.next();
		}
		
		
		System.out.println("Arrivederci!");
		s.close();
	
	}
	
	public static void menu() {
		System.out.println("Benvenuto nella tua rubrica! Cosa vuoi fare?");
		System.out.println("-Digita 0 per vedere lo stato della tua rubrica");
		System.out.println("-Digita 1 per aggiungere una nuova voce");
		System.out.println("-Digita 2 per modificare una voce");
		System.out.println("-Digita 3 per cancellare una voce");
		System.out.println("-Digita 4 per cercare un contatto");
		System.out.println("-Digita 5 per uscire");
		System.out.println("-Digita 6 per ristampare il menù");
	}
	
	public void choice(String i, Scanner s) {
		Connection connection = null;
		Statement statement = null;
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");			
			connection = DriverManager.getConnection(Config.getDbUrl(), Config.getDbUser(), Config.getDbPwrd());
			
			switch(i) {
				case "0": stampa(connection, statement, rs); break;
				case "1": aggiungiVoce(connection, statement, s); break;
				case "2":  break;
				case "3":  break;
				case "4": ricerca(connection, statement, rs, s); break;
				case "5":  break;
				case "6": break;
				default: System.out.println("L'opzione selezionata non è valida.");
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				connection.close();
				//System.out.println(connection.isClosed());
				
				//rs.close();
				//statement.close();
				//preparedStatement.close();
			} catch (SQLException sqlEx) {
				sqlEx.printStackTrace();
			}
		}
		
	}
	
	public void lettura(ResultSet rs) throws SQLException {
		while (rs.next()) {
			System.out.println("id : " + rs.getInt("id"));
			System.out.println("nome : " + rs.getString("name"));
			System.out.println("cognome : " + rs.getString("surname"));
			System.out.println("email : " + rs.getString("email"));
			System.out.println("telefono : " + rs.getString("telephone"));
			System.out.println("-----");
		}
	}
	
	
	public void stampa(Connection connection, Statement statement, ResultSet rs) {
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("SELECT * FROM rubrica");
			lettura(rs);
		} catch (Exception e) {
			
		} finally {
			try {
				rs.close();
				statement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void aggiungiVoce(Connection connection, Statement statement, Scanner s) throws IOException {
		Contatto cont = r.defContatto(s);
		if(cont != null) {
			try {
				statement = connection.createStatement();
				String insert = "INSERT INTO rubrica (name,surname,email,telephone) "
						+ "VALUES ('" + cont.getName() + "','" + cont.getSurname() + "','" 
						+ cont.getEmail() + "','" + cont.getTelephone() + "')";
				statement.execute(insert);
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				try {
					statement.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}
	
	
	public void modificaEntry(Connection connection) {
		
	}
	
	
	public void eliminaVoce(Connection connection, Statement statement, ResultSet rs, Scanner s) {
		System.out.println("Si sta scegliendo di eliminare delle voci.");
		try{
			rs = scan(connection, statement, rs, s);
			if(rs != null) {
				ArrayList<Integer> elim = new ArrayList<>();
				while(rs.next()) {
					elim.add(rs.getInt("id"));
				}
				statement = connection.createStatement();
				for(Integer i : elim) {
					statement.execute("DELETE FROM rubrica WHERE id = " + i);
				}
			}
		}catch (SQLException e) {
			e.printStackTrace();
		}finally {
			
		}
		
		
		
		
		
		
		
	}
	
	public void ricerca(Connection connection, Statement statement, ResultSet rs, Scanner s) {
		try {
			rs = scan(connection, statement, rs, s);
			if(rs == null) {
				System.out.println("Non ci sono voci che corrispondono ai criteri di ricerca nel database.");
			}else {
				lettura(rs);
			}
		}catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				statement.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	
	//non usare da sola, se un altro metodo la chiama RICORDARSI di chiudere rs e statement in un finally
	public ResultSet scan(Connection connection, Statement statement, ResultSet rs, Scanner s) {
		System.out.println("Scegliere in quale campo cercare:\n"
				+ "0: ID\n 1: NOME\n2: COGNOME\n3: EMAIL\n4: TELEFONO\n"
				+ "(Attenzione il numero di telefono deve contenere eventuali prefissi)");
		String str = s.next();
		
		try {
			int indice = Integer.parseInt(str);
			
			while(indice >= 5) {
				System.out.println("Voce non valida, provare di nuovo.");
				str = s.next();
				indice = Integer.parseInt(str);
			}
			String search = "";
			
			switch(indice) {
				case 0: search = "id"; break;
				case 1: search = "name"; break;
				case 2: search = "surname"; break;
				case 3: search = "email"; break;
				case 4: search = "telephone"; break;
			}
			
			System.out.println("Inserire il valore da cercare: ");
			str = s.next();
			
			String select = "SELECT * FROM rubrica WHERE " + search + " = '" + str + "'";
			statement = connection.createStatement();
			rs = statement.executeQuery(select);
			//lettura(rs);
			
		} catch (NumberFormatException e) {
			System.out.println("Il valore inserito non è riconosciuto, sarai reindirizzato al menù.");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		
		
		return rs;
	}
}
