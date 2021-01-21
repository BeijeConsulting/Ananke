package it.beije.ananke.tombolone;

import java.util.*;

public class NumeriTombola {
	
	private final static int NUM_LIST = 9;
	private final static int NUM_PRIMA_COLONNA = 9;
	private final static int NUM_ULTIMA_COLONNA = 11;
	private final static int NUM_COLONNA = 10;
	private ArrayList<ArrayList<Integer>> numeriTombola = new ArrayList<>();
	
	public NumeriTombola() {
		for (int i = 0; i < NUM_LIST; i++) {
			if(i==0) {
				ArrayList<Integer> list = new ArrayList<>();
				for (int j = 0; j < NUM_PRIMA_COLONNA; j++) {
					list.add(j+1);
				}
				numeriTombola.add(list);
			} else if (i == NUM_LIST-1) {
				ArrayList<Integer> list = new ArrayList<>();
				for (int j = 0; j < NUM_ULTIMA_COLONNA; j++) {
					int num = i*10 +j;
					list.add(num);
				}
				numeriTombola.add(list);
				
			} else {
				ArrayList<Integer> list = new ArrayList<>();
				for (int j = 0; j < NUM_COLONNA; j++) {
					int num = i*10 +j;
					list.add(num);
				}
				numeriTombola.add(list);
			}
		}
	}
	
	public ArrayList<Integer> getColonna(int i) {
		return numeriTombola.get(i);
	}
	
	public boolean isNumero(int num) {
		int resto = num/10;
		if(numeriTombola.get(resto).contains(num)) {
			return true;
		}
		return false;
	}
	
	public void removeNumero(int num) {
		if(isNumero(num)) {
			int resto = num/10;
			if(resto == NUM_LIST) {
				resto = NUM_LIST -1;
			}
			numeriTombola.get(resto).remove(numeriTombola.get(resto).indexOf(num));
		}
	}
	
	public int sizeControl(List<Integer> list) {
		return list.size();
	}

	public ArrayList<ArrayList<Integer>> getNumeriTombola() {
		return numeriTombola;
	}
	
	

}
