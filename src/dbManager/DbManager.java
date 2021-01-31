package dbManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import csv.Contatto;

public class DbManager {
	public static final String DB_USER = "root";
	public static final String DB_PASSWORD = "Beije07";
	public static final String DB_URL = "jdbc:mysql://localhost:3306/ananke?serverTimezone=CET";
	private Connection instauraConnessione() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public void addContatto(Contatto c) {
		String psInsert = "INSERT INTO contatti (name,surname,telephone,email) VALUES (?,?,?,?)";
		PreparedStatement preparedStatement = null;
		Connection connesione=null;
		try {
			 connesione=instauraConnessione();
			preparedStatement = connesione.prepareStatement(psInsert);
			preparedStatement.setString(1, c.getNome());
			preparedStatement.setString(2, c.getCognome());
			preparedStatement.setString(3, c.getTel());
			preparedStatement.setString(4, c.getEmail());
			if(preparedStatement.execute()) {
				System.out.println("Contatto aggiunto al db correttamente");	
			}
			preparedStatement.close();
			connesione.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	public void modificaContatto() {
		ArrayList<Integer> indici=stampaContatti();
		Scanner s= new Scanner(System.in);
		int indice=0;
		do {
		System.out.println("Indica l'indice di quale contatto vuoi andare a modificare");
		 indice= Integer.parseInt(s.nextLine());
		if(!indici.contains(Integer.valueOf(indice)))
			System.out.println("Attenzione l'indice inserito non è valido...");

		}while(!indici.contains(Integer.valueOf(indice)));
		String psUpdate = "";
		PreparedStatement preparedStatement = null;
		Connection connesione=null;
		try {
			String nome,cognome,telefono,email;
			do {
			 connesione=instauraConnessione();
			System.out.println("Inserisci il nome o premi invio per non inserirlo");
			 nome= s.nextLine();
			if(nome.length()>0) {
				psUpdate = "Update  contatti set name=? where id=?";
				preparedStatement = connesione.prepareStatement(psUpdate);
				preparedStatement.setString(1, nome);
				preparedStatement.setInt(2, indice);
				
			}
			System.out.println("Inserisci il cognome o premi invio per non inserirlo");
			 cognome= s.nextLine();
			if(cognome.length()>0) {
				psUpdate = "Update  contatti set surname=? where id=?";
				preparedStatement = connesione.prepareStatement(psUpdate);
				preparedStatement.setString(1,cognome);
				preparedStatement.setInt(2, indice);
				
			}
			System.out.println("Inserisci il email o premi invio per non inserirlo");
			 email= s.nextLine();
			if(email.length()>0) {
				psUpdate = "Update  contatti set email=? where id=?";
				preparedStatement = connesione.prepareStatement(psUpdate);
				preparedStatement.setString(1,email);
				preparedStatement.setInt(2, indice);
				
			}
			System.out.println("Inserisci il telefono o premi invio per non inserirlo");
			 telefono= s.nextLine();
			if(telefono.length()>0) {
				psUpdate = "Update  contatti set email=? where id=?";
				preparedStatement = connesione.prepareStatement(psUpdate);
				preparedStatement.setString(1,telefono);
				preparedStatement.setInt(2, indice);
			}
				if(!(nome.length()>0||cognome.length()>0||telefono.length()>0||email.length()>0)) {
					System.out.println("Attenzione non hai inserito nessun campo");
					System.out.println("Digita 0 se vuoi ritornare al menu precedente senza effettuare modifiche");
					System.out.println("Altrimenti premi un tasto qualsisi escluso 0 per riprovare la modifica");
					String stop=s.nextLine();
					if(stop.equalsIgnoreCase("0")){
						break;
					}
					

				
				}else {
					
				}
					if(preparedStatement!=null)
			preparedStatement.execute();
			}while(!(nome.length()>0||cognome.length()>0||telefono.length()>0||email.length()>0));
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			
			try {
				if(preparedStatement!=null&&!preparedStatement.isClosed())
					preparedStatement.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				try {
					connesione.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
	}
	public ArrayList<Contatto> returnList(){
		Connection connesione=null;
		 connesione=instauraConnessione();
		 Statement statment=null;
		 ResultSet rs = null;
		 ArrayList<Contatto> contatti= new ArrayList<>();
		 
		String query= "Select id,name,surname,email,telephone from contatti;";
		try {
			statment=connesione.createStatement();
			rs=statment.executeQuery(query);
			while(rs.next()) {
				contatti.add(new Contatto(rs.getString("name"),rs.getString("surname"),rs.getString("telephone"),rs.getString("email")));
				
			}
			   rs.close();
			 
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				if(statment.isClosed())
					statment.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
				try {
					connesione.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					if(!rs.isClosed()) 
					rs.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			
		}
		
		return contatti;
		
				
	}
private void stampaResult(ResultSet rs) {
	try {
		while(rs.next()) {
			System.out.println(rs.getInt(1)+" : "+rs.getString("name")+" "+rs.getString("surname")+" "+rs.getString("email")+" "+rs.getString("telephone"));

			
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}finally {
		try {
			rs.close();
			
			} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}

	public boolean removeContatto() {
		Scanner s= new Scanner(System.in);
		ArrayList<Integer> indici=stampaContatti();
		int indice=0;
		do {
		System.out.println("Indica l'indice di quale contatto vuoi andare a eliminare");
		 indice= Integer.parseInt(s.nextLine());
		if(!indici.contains(Integer.valueOf(indice)))
			System.out.println("Attenzione l'indice inserito non è valido...");

		}while(!indici.contains(Integer.valueOf(indice)));
		
		String psUpdate = "delete from contatti where id=? ";
		PreparedStatement preparedStatement = null;
		Connection connesione=null;
		try {
			 connesione=instauraConnessione();
			preparedStatement = connesione.prepareStatement(psUpdate);
				
				preparedStatement.setInt(1, indice);
				boolean z =preparedStatement.execute();
				if(z) 
			System.out.println("--Contatto Eliminato--");
	
		
			preparedStatement.close();
			connesione.close();
				return z;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
		public void ricercaContatti() {
			Scanner s=new Scanner(System.in);
			 ArrayList<Contatto> trovati= new ArrayList<>();
				Connection connesione=null;
				 connesione=instauraConnessione();
				 PreparedStatement statment=null;
				 ResultSet rs = null;
		
				String query;
				String nome,cognome,email,telefono;
				boolean stop=true;
				try {
				
			
				System.out.println("Seleziona 1 per effettuare la ricerca in base al nome");
				System.out.println("Seleziona 2 per effettuare la ricerca in base al nome e al cognome");
				System.out.println("Seleziona 3 per effettuare la ricerca in base all'email");
				System.out.println("Seleziona 4 per effettuare la ricerca in base al telefono");

				 String scelta=s.nextLine();
				switch(scelta) {
				case "1":
					do {
						System.out.println("digita il nome");
						nome=s.nextLine();
						
					}while(nome.length()==0);
					query="Select * from contatti where name=?;";
					statment=connesione.prepareStatement(query);
					statment.setString(1, nome);
					rs=statment.executeQuery();
					stampaResult(rs);
					break;
				case "2":
					do {
						System.out.println("digita il nome");
						nome=s.nextLine();
						System.out.println("digita il cognome");
						cognome=s.nextLine();
					}while(nome.length()==0||cognome.length()==0);
					query="Select * from contatti where name=? and surname=?;";
					statment=connesione.prepareStatement(query);
					statment.setString(1, nome);
					statment.setString(2,cognome);
					rs=statment.executeQuery();
					stampaResult(rs);
					
					break;
				case "3":
					do {
						System.out.println("digita l'email");
						email=s.nextLine();
						
					}while(email.length()==0);
					query="Select * from contatti where email=?;";
					statment=connesione.prepareStatement(query);
					statment.setString(1, email);
					rs=statment.executeQuery();
					stampaResult(rs);
					break;
				case "4":
					do {
						System.out.println("digita il numero di telefono");
						telefono=s.nextLine();
						
					}while(telefono.length()==0);
					query="Select * from contatti where telephone=?;";
					statment=connesione.prepareStatement(query);
					statment.setString(1, telefono);
					rs=statment.executeQuery();
					stampaResult(rs);
					break;
				default: 
					System.out.println("Attenzio non avete selezionato nulla, ritorno al menu precedente..."); 
				break;
				}
				}catch(Exception ss) {
					ss.printStackTrace();				
				}
				finally {
					try {
						statment.close();
						connesione.close();
						} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
				}
				
		}
				
		
				
	
	public ArrayList<Integer> stampaContatti() {
		Connection connesione=null;
		 connesione=instauraConnessione();
		 Statement statment=null;
		 ResultSet rs = null;
		 ArrayList<Integer> indice=new ArrayList<>();
		String query= "Select id,name,surname,email,telephone from contatti;";
		try {
			statment=connesione.createStatement();
			rs=statment.executeQuery(query);
			while(rs.next()) {
				indice.add(rs.getInt(1));
				System.out.println(rs.getInt(1)+" : "+rs.getString("name")+" "+rs.getString("surname")+" "+rs.getString("email")+" "+rs.getString("telephone"));
				
			}
			  statment.close();
			  rs.close();
			  connesione.close();
			  
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			try {
				rs.close();
				statment.close();
				connesione.close();
				} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
		return indice;
				
	}
	
	
}
