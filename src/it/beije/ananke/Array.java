package it.beije.ananke;

import java.util.*;
public class Array {

	public static void main(String[] args) {

		//Arrays
		
		int[] numbers = {1,5,99,28,50};
		String strings[] = {"libro", "cielo", "mare", "oceano", "blu"};
		
		System.out.println("ARRAY DI RIFERIMENTO");
		System.out.println("numbers: " + Arrays.toString(numbers));
		System.out.println("strings: " + Arrays.toString(strings));
		
		System.out.println("----------------------------------------------");
		System.out.println("SORT");
		Arrays.sort(numbers);
		Arrays.sort(strings);
		System.out.println("numbers: " + Arrays.toString(numbers));
		System.out.println("strings: " + Arrays.toString(strings));
		
		System.out.println("----------------------------------------------");
		System.out.println("SEARCH");
		System.out.println("5 in numbers: " + Arrays.binarySearch(numbers, 5));
		System.out.println("verde in strings: "+ Arrays.binarySearch(strings, "verde"));
		
		System.out.println("----------------------------------------------");
		System.out.println("MULTIDIMENSIONAL ARRAY");
		String[][] multiArray = {{"rosso", "azzurro", "rosa"}, {"cane", "gatto", "gallina", "coniglio"}};
		for(String[] stringhe : multiArray) {
			for(String stringa : stringhe) {
				System.out.print(stringa + " ");
			}
			System.out.println();
		}
		
		//ArrayList
		
		ArrayList<String> colori = new ArrayList<>();
		
		System.out.println("----------------------------------------------");
		System.out.println("ARRAYLIST");
		System.out.println("ADD");
		colori.add("blu");
		colori.add(1, "arancione");
		colori.add("nero");
		colori.add("viola");
		colori.add("bianco");
		System.out.println(colori.toString());
		
		System.out.println("----------------------------------------------");
		System.out.println("REMOVE");
		System.out.println("rimuovi arancione: " + colori.remove("arancione"));
		System.out.println("rimuovi indice 2: " + colori.remove(2));
		System.out.println("rimuovi verde: " + colori.remove("verde"));
		System.out.println(colori.toString());
		
		System.out.println("----------------------------------------------");
		System.out.println("SET");
		System.out.println(colori.set(2, "marrone"));
		System.out.println(colori.toString());
		
		System.out.println("----------------------------------------------");
		System.out.println("CONTAINS");
		System.out.println("contiene marrone: " + colori.contains("marrone"));
		System.out.println("contiene giallo: " + colori.contains("giallo"));
		
		System.out.println("----------------------------------------------");
		System.out.println("ISEMPTY & SIZE");
		System.out.println("is empty: " + colori.isEmpty());
		System.out.println("size: " + colori.size());
		
		System.out.println("----------------------------------------------");
		System.out.println("CLEAR");
		colori.clear();
		System.out.println("is empty: " + colori.isEmpty());
		System.out.println("size: " + colori.size());
		
		
		
	}

}
