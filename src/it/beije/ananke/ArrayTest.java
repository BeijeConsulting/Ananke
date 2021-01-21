package it.beije.ananke;

import java.util.Arrays;

public class ArrayTest {
	public static void main(String[] args) {
		int[] numbers = new int[10];
		for(int i = 0; i< numbers.length; i++) {
			numbers[i] = i*5;
		}
		
		for(int number:numbers) {
			System.out.println(number);
		}
		System.out.println("=====================");
		
		int[] newNumbers = {15,24,7,19,87,23};
		for(int number:newNumbers) {
			System.out.println(number);
		}
		System.out.println();
		System.out.println(Arrays.binarySearch(newNumbers, 15));
		
		System.out.println("=====================");
		Arrays.sort(newNumbers);
		for(int number:newNumbers) {
			System.out.println(number);
		}

		System.out.println();
		System.out.println(Arrays.binarySearch(newNumbers, 20));
		

		System.out.println("=====================");
		int[][] twoDArray = new int[5][5];
		for(int i=0; i< twoDArray.length; i++) {
			for(int j=0; j<twoDArray[i].length; j++) {
				twoDArray[i][j] = i * j *3;
			}
		}
		
		for(int row[]:twoDArray) {
			for(int column:row) {
				System.out.print(column+ "\t");
			}
			System.out.println();
		}
		

		System.out.println("=====================");
		
		String[][] str = {{"Giulio","Fossati"},{"Marco", "Alessandro", "Ronzoni"}, {"Arsenio", 
			"Lupin","Ladro","Gentiluomo"}};
		
		System.out.println(str[1][2]);
		

		System.out.println("=====================");
		String[] string = new String[5];
		Object[] object = string;
		System.out.println(object.length);
		object[1] = new String("Tacci mia");
		System.out.println(string[1] + "\n" + object[1]);
		//object[2] = new StringBuilder("Alias").append(" cosi cosi"); //genera una ArrayStoreException
		//object[3] = new Integer(4); //stessa eccezione di sopra
		//		string[3] = new StringBuilder("ciaone");
	}

}
