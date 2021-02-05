package it.beije.ananke.rubrica;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceApp {

	Connection conn = null;
	PreparedStatement preparedStatement = null;
	Statement statement = null;
	ResultSet rs = null;

	public ArrayList<Contatto> CaricaFileCsv(String path, String nomeFile) throws IOException {	
		ArrayList<Contatto> listContatti = new ArrayList<Contatto>();
		List <String> lista = new ArrayList<String>();
		File file = new File(path + "\\" + nomeFile + ".csv");
		BufferedReader bf = new BufferedReader(new FileReader(file));
		while(bf.ready()){
			lista.add(bf.readLine());
		}
		bf.close();
		Contatto contatto = null;
		for (String row : lista) {
			String[] rs = row.split(";");
			contatto = new Contatto();
			contatto.setName(rs[0]);
			contatto.setSurname(rs[1]);
			contatto.setTelephone(rs[2]);
			contatto.setEmail(rs[3]);
			listContatti.add(contatto);
		}

		return listContatti;
	}
	
	
	public boolean isDoppio(ArrayList<String> lista, String email, int index) {
		if(index == lista.size()) {
			return false;
		}
		if(lista.get(index).equals(email)) {
			return true;
		}
		return isDoppio(lista,email,++index);
	}



	public void aggiornaContattiDB(ArrayList<Contatto>contatto) throws SQLException {
		
		ArrayList<String> emaillista = new ArrayList<>();
		ConnectionDb con = new ConnectionDb();
		this.conn = con.connectionJDBC(); 
		String sql = "INSERT INTO contatti (name, surname,email,telephone) VALUES (?,?,?,?)";
		String sql1 = "SELECT email FROM contatti";
	    preparedStatement = conn.prepareStatement(sql);
	   
	    rs = statement.executeQuery(sql1);
		while(rs.next()) {
			String email = rs.getString("email");
			emaillista.add(email);
			
		}
		
	    
		for(Contatto c : contatto) {
			if(!isDoppio(emaillista,c.getEmail(),0)) {
			String name = c.getName();
			String surname = c.getSurname();
			String telephone = c.getTelephone();
			String email = c.getEmail();
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, surname);
			preparedStatement.setString(3, email);
			preparedStatement.setString(4, telephone);
  	        preparedStatement.execute();
			} else {
				System.out.println("l'email "+ c.getEmail()+ " è doppione, non inserita");
			}
		}
		
		conn.close();
		preparedStatement.close();
		rs.close();
		statement.close();
	
		

	}

}
