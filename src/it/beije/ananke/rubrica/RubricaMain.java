package it.beije.ananke.rubrica;

import java.io.IOException;
import java.util.ArrayList;

public class RubricaMain {

	public static void main(String[] args) throws IOException {
		CsvManager r = new CsvManager("/Users/Padawan08/Desktop/Rubrica.csv");
		r.aggiungiInRubrica();		
		r.aggiornaRubrica();
	}

}
