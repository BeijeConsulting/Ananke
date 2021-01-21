package it.beije.ananke.vettori;
import java.util.*;



public class VettoreProva {
	int[][] primit;
	Object[] obj;
	
	public static void main(String[] args) {
		Random ran = new Random();
		
		int[] sortof = {968, 44, 132, 67, 3, 522, 90};
		for(int i : sortof) {
			System.out.print(i + "   ");
		}
		
		//?? perchè non stampa la binary su array non ordinato
		System.out.println(Arrays.binarySearch(sortof, 44));
		int var = Arrays.binarySearch(sortof, 3);
		System.out.println(var + "  search on unsorted");
		
		Arrays.sort(sortof);
		for(int i : sortof) {
			System.out.println(i);
		}
		System.out.println(Arrays.binarySearch(sortof, 44));
		
		ArrayList<String> listing = new ArrayList<>();
		listing.add("Ciao!");
		listing.add("Come va?");
		System.out.println(listing.toString());
		
		ArrayList<Integer> lista = new ArrayList<>();
		lista.add(78);
		lista.add(new Integer(23));
		System.out.println(lista.toString());
		System.out.println(lista.contains(23));
		lista.set(lista.indexOf(78), 43);
		System.out.println("Questa lista contiene " + lista.size() + " elementi.");
		System.out.println(lista.toString());
		lista.remove(0);
		System.out.println(lista.toString());
		
		if(lista.isEmpty()) {
			System.out.println("La lista ha ancora elementi al suo interno.");
		}else {
			System.out.println("La lista è vuota");
		}
		
		if(lista.contains(56)) {
			System.out.println("La lista contiene l'elemento cercato.");
		}else {
			System.out.println("La lista non contiene l'elemento cercato.");
		}
		
		lista.clear();
		
		VettoreProva prova = new VettoreProva(ran.nextInt(10), ran.nextInt(5));
		for(int[] vet : prova.primit) {
			for(int i = 0; i < vet.length; i++) {
				vet[i] = i + 3;
				System.out.print(vet[i] + "   ");
			}
			System.out.print("\t fine riga \n");
		}
		
	}
	
	public VettoreProva(int riga, int depth) {
		primit = new int[riga][depth];
	}
	
	public VettoreProva() {
		
	}
}
