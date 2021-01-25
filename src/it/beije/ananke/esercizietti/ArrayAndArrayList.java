package it.beije.ananke.esercizietti;

import java.util.Random;
import java.util.ArrayList;
import java.util.Arrays;

public class ArrayAndArrayList {
	
	public static void main(String[] args) {
		
		int[] arrayNumeri = new int[10];
		
		//popolo l'array con numeri random senza ripetizione
		
		Random rand = new Random();
		
		
		 
		for(int i=0; i<arrayNumeri.length; i++) {
			boolean inserted = false;
			
			while(!inserted) {
			
				int numero = rand.nextInt(10)+1;
				boolean found = false;
				for(int j=0; j<i; j++) {
					if(numero == arrayNumeri[j]) {
						found = true;
						break;
					}
				}
				if(!found) {
					arrayNumeri[i] = numero;
					inserted = true;
				}
			}
		}
		
		//stampo un attimo
		for(int i=0; i<arrayNumeri.length; i++)
			System.out.print(arrayNumeri[i]+ " " );
		
		//ora ordiniamo l'array
		Arrays.sort(arrayNumeri);
		
		//ri-stampo un attimo
		System.out.println("\nHo riordinato l'array con sort() della classe Arrays");
		for(int i=0; i<arrayNumeri.length; i++)
			System.out.print(arrayNumeri[i]+ " " );
		
		//ora usiamo altri metodi
		int numRand = rand.nextInt(15);
		System.out.println("\nIl numero " + numRand + " � presente nell'array? " + (Arrays.binarySearch(arrayNumeri, numRand)>0));
		
	
		
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
		//adesso facciamo la stessa cosa con ArrayList
		//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
		ArrayList<Integer> arrayListNumeri = new ArrayList<Integer>();
			
		for(int i=0; i<10; i++) {
			boolean inserted = false;
			while(!inserted) {
			
				int numero = rand.nextInt(10)+1;
				boolean found = false;
				
				if(arrayListNumeri.contains(numero)) 
					found = true;
				
				if(!found) {
					arrayListNumeri.add(numero);
					inserted = true;
				}
			}
		}
		
	
		//stampo
		System.out.println(arrayListNumeri);
		
		int elementoRandom = rand.nextInt(10);
		System.out.println("Tolgo l'elemento in pos: " + elementoRandom);
		arrayListNumeri.remove(elementoRandom);
		System.out.println("in quella posizione setto un altro numero randomico");
		arrayListNumeri.set(elementoRandom, rand.nextInt(50));
		
		//ri-stampo
		System.out.println(arrayListNumeri);
		
		arrayListNumeri.clear();
		System.out.println("Ho clearato l'ArrayList. Infatti .isEmpty() ritorna: " + arrayListNumeri.isEmpty());
	
		//ri-stampo
		System.out.println(arrayListNumeri);
	}
	
}