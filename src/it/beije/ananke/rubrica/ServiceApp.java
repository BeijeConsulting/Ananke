package it.beije.ananke.rubrica;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ServiceApp {

	Connection conn = null;
	PreparedStatement preparedStatement = null;
	Statement statement = null;

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



	public void aggiornaContattiDB(ArrayList<Contatto>contatto) throws SQLException {
		ConnectionDb con = new ConnectionDb();
		this.conn = con.connectionJDBC(); 
		String sql = "INSERT INTO contatti (name, surname,email,telephone) VALUES (?,?,?,?)";
	    preparedStatement = conn.prepareStatement(sql);
	    
		for(Contatto c : contatto) {
			preparedStatement.setString(1, c.getName());
			preparedStatement.setString(2, c.getSurname());
			preparedStatement.setString(3, c.getTelephone());
			preparedStatement.setString(4, c.getEmail());
  	        preparedStatement.execute();
		}
		
		conn.close();
		preparedStatement.close();
	
		

	}

}
