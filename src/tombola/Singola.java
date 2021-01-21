package tombola;

import java.util.ArrayList;
import java.util.Random;

public class Singola {
	public final int RIGHE = 3;
	public final int COLONNE = 9;
	public int[] checkr = new int[RIGHE];
	public int[] checkc = new int[COLONNE];
	public ArrayList<ArrayList<Integer>> singular = new ArrayList<>(COLONNE);
	
	public Singola() {
		for(int i = 0; i < COLONNE; i++) {
			singular.add(new ArrayList<Integer>());
			singular.get(i).add(0);
			singular.get(i).add(0);
			singular.get(i).add(0);
		}
	}
	
	
	public boolean isEmpty(int riga, int colonna) {
		if(singular.get(colonna).get(riga) == 0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	public int checkRiga(int index) {
		if(checkr[index] < 5) {
			//posso inserire elemento in quella riga
			return 1;
		}else {
			//la riga è piena
			return -1;
		}
	}
	
	
	public int checkColonne(int index) {
		if(checkc[index] < 2) {
			//posso inserire elemento in quella colonna
			return 1;
		}else {
			//la colonna è piena
			return -1;
		}
	}

	
}
