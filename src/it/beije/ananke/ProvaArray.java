package it.beije.ananke;
import java.util.*;


public class ProvaArray {

	public static void main(String... args) {
		
		
		//ARRAY
		
		int[] vett[] = {{52,22},{31,44},{71,66,50,39}};
		int[][] clone = vett;
		System.out.println(vett==clone);
		System.out.println();
		
		
		for(int[] inner : vett) {
			for(int num : inner) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
		
		for(int[] inner : vett) {
			Arrays.sort(inner);
		
		}
		
		System.out.print("----------------------------------");
		System.out.println();
		
		for(int[] inner : vett) {
			for(int num : inner) {
				System.out.print(num + " ");
			}
			System.out.println();
		}
		
		System.out.println();
		int V[] = {9,8,4,1,7,3,2,0,2,4,6,9,2,7,5,3,2,5,6};
		int pos = Arrays.binarySearch(V, 9);
		System.out.println(pos); // il vettore non è ordinato quindi la binary search non funziona!
		Arrays.sort(V);
		pos = Arrays.binarySearch(V, 9);
		System.out.println(pos); // stampa la prima poszione dove lo trova! le altre le ignora
		
		//ARRAYLIST
		System.out.println();
		List<String> List = new ArrayList<>();
		List.add("A");
		List.add(null);
		List.add("B");	
		List.add("C");	
		List.add("D");	
		System.out.println(List.size());
		List.add(1,"---------");
		System.out.println(List); // al contrario degli Array stampa effettivamente la lista
		List.remove(2);
		System.out.println(List);
		System.out.println(List.isEmpty());
		List.clear(); // svuota completamente l'arraylist
		System.out.println(List.isEmpty());
		
		//ARRAYLIST vs ARRAY
		
		String[] Vett = {"A","B","C"};
		List<String> AList = Arrays.asList(Vett);
		// AList.remove(0); // DA ECCEZIONE NON POSSO MODIFICARE LA LISTA IN QUANTO PUNTA AGLI ELEMENTI VETTORE
		// CHE E' STATICO DAL PUNTO DI VISTA DELLA DIMENSIONE
		AList.set(0, null);
		System.out.println();
		//System.out.println(Vett); // non stampa il vettore
		for(String s : Vett) {System.out.println(s);} // MODIFICANDO LA LISTA HO MODIFICATO ANCHE Vett
		
		
	}
	

	
}
