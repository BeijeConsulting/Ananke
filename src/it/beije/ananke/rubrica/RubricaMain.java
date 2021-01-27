package it.beije.ananke.rubrica;

import java.io.IOException;
import java.util.ArrayList;

public class RubricaMain {

	public static void main(String[] args) throws IOException {
		Rubrica r = new Rubrica("/Users/Padawan08/Desktop");
		r.aggiungiInRubrica();		
		r.aggiornaRubrica();
	}

}
