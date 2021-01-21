package tombola;

import java.util.*;

public class Tombola {
	public final int TOT = 90;
	public ArrayList<Integer> num;
	
	public static void main(String[] args) {
		Tombola batch1 = new Tombola();
		Batch cartelle = new Batch(batch1.num);
		batch1.stampa(cartelle);
	}
	
	public Tombola() {
		num = new ArrayList<Integer>();
		int i;
		for(i = 1; i <= TOT; i++) {
			num.add(i - 1, i);
		}
		
	}
	
	public void stampa(Batch batch) {
		for(Singola s : batch.tot) {
			for(int i = 0; i < batch.RIGHE; i++) {
				for(int j = 0; j < batch.COLONNE; j++) {
					System.out.print(s.singular.get(j).get(i) + "\t");
				}
				System.out.print("\n");
			}
			System.out.print("\n\n");
		}
	}
	

}
