package it.beije.ananke.capitoli123;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ArrayVSList {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<String> list = new ArrayList<String> ();
		for (int i=0; i< 10; i++) {
			list.add("s"+i+i); //la regola del capitolo 2: i+i non stampa 2 ma 11 per l'ordine dell'operazione
			
		}
		System.out.println(list);
		
		System.out.println("=====================");
		
		Object[] objectArray = list.toArray();
		for(Object o : objectArray) {
			System.out.print(o + " ");
		}
		
		objectArray[3] = new Piramide();
		System.out.println(objectArray[3]);
		

		System.out.println("=====================");
		
		String[] stringArray = list.toArray(new String[0]);
		for(String o : stringArray) {
			System.out.print(o + " ");
		}

//		stringArray[3] = 2; //errore in compilazione: mismatch type
		System.out.println(stringArray[3]);
		

		System.out.println("=====================");
		Integer[] numbers = new Integer[10];
		for(int i = 0; i< numbers.length; i++) {
			numbers[i] = i*5;
		}
		
		for(int number:numbers) {
			System.out.print(number + " ");
		}
		
		System.out.println("\n=====================");
		
		
		
		List<Integer> integers = Arrays.asList(numbers);

		System.out.println(integers);
		System.out.println("\n=====================");
		
		String[] strings = {"Giulio", "Leone", "Iorio"};
		
		List<String> stringList = Arrays.asList(strings);
		System.out.println(stringList);
		
		stringList.set(1, "Cesarino");
		System.out.println(stringList);
		for(String s:strings) {
			System.out.print(s + " ");
		}
		
		int num = 1;
		String two = num + " " + 1;
		
		

	}

}
