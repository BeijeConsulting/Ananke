package it.beije.ananke.provametodi;

import java.util.*;

public class Array {

	public static void main(String[] args) {
//Prova ordinamento con valori int
		int[] numbers = { 6, 9, 1 };
		Arrays.sort(numbers);
		for (int i = 0; i < numbers.length; i++)
			System.out.print(numbers[i] + " ");

		// Prova ordinamento con stringhe
		String[] strings = { "10", "9", "100" };
		Arrays.sort(strings);
		for (String string : strings)
			System.out.print(string + " ");

		int[][] vars1; // 2D array
		int vars2[][]; // 2D array
		int[] vars3[]; // 2D array
		int[] vars4[], space[][]; // a 2D AND a 3D array
		// -------------------------------------------------------
		// Prova di arraylist
		ArrayList<String> list4 = new ArrayList<String>();
		ArrayList<String> list5 = new ArrayList<>();
		list4.add("gino");
		list4.add("aneke");
		System.out.println(list4.toString());
		list4.remove("gino");
		System.out.println(list4.toString());
		list4.set(0, "Gruppo aneke");
		System.out.println(list4.toString());
		System.out.println("La lista è composta da:" + list4.size());
		list4.add("aneke");
		list4.add("0");
		System.out.println(list4.toString());
		Collections.sort(list4);
		System.out.println(list4.toString());

		Random r = new Random();
		List<Integer> vettore = new ArrayList<>();
		for (int i = 0; i < 200; i++) {
			vettore.add(r.nextInt(100));
			
		}
		
		
		for (int i = 0; i < 200; i++)
			System.out.println(i + ": " + vettore.get(i));
/*
		for (int i = 0; i < 200; i++) {
			int conta=0;
			for (int k = 0; k < 200; k++) {
				if (vettore.get(i) == vettore.get(k)) {
					conta++;
				}


		}
	}
		*/

		
		
	}
}

