package it.beije.ananke.rubrica;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;


public class Principal {

	public static void main(String[] args) throws IOException, SQLException {
		ArrayList <Contatto> cont = new ArrayList<>();
		ServiceApp serv = new ServiceApp();
		
		cont = (ArrayList<Contatto>) serv.CaricaFileCsv("C:\\Users\\Padawan11\\Desktop\\FileDoc", "contatti");
		serv.aggiornaContattiDB(cont);
		
	}

}
