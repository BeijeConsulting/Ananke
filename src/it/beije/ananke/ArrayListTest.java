package it.beije.ananke;

import java.util.*;

public class ArrayListTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ArrayList list1 = new ArrayList();
		String string = "Felicitazioni";
		int n1 = 7;
		long n2 = 2L;
		char ch = 'A';
		boolean b = false;
		list1.add(string);
		list1.add(n1);
		list1.add(n2);
		list1.add(ch);
		list1.add(b);
		
		System.out.println(list1.toString()); //tutto il mappazzo sopra funziona, ho un arraylist pieno di oggetti eterogenei
		list1.set(3, string); // e posso cambiarli come mi pare
		System.out.println(list1.toString());
		System.out.println("=====================");
		
		ArrayList list2 = new ArrayList(list1);
		list2.remove(2);
		list2.remove(b);
		System.out.println(list2); //posso omettere pure il toString(), figo
		System.out.println(list1.toString()); 	//se costruisco una list come ho fatto su list2
												//la lista di partenza non rimane legata
		

		System.out.println("=====================");
		List<String> list3 = new ArrayList<String> ();
		for (int i=0; i< 10; i++) {
			list3.add("s"+i+i); //la regola del capitolo 2: i+i non stampa 2 ma 11 per l'ordine dell'operazione
			
		}
		System.out.println(list3.contains("s22"));
		
		System.out.println("=====================");

		List<String> list4 = new ArrayList<String> (list3);
		System.out.println(list4);
		System.out.println(list4.equals(list3) + "\n"
				+ list3.remove(2) + " è l'elemento eliminato\n"
				+ list4.set(1, "maledizione") + " è l'elemento sostituito, però "
				+ list4.get(1) + " è l'elemento che ho messo in lista");
		


		System.out.println("=====================");
		List<Integer> list5 = new ArrayList<Integer> ();
		Random random = new Random();
		int cheFa = random.nextInt(5);
		switch (cheFa) {
			case 0: case 2: case 5:
				list5.add(cheFa);
				break;
			default:
				list5.add(null);
				break;
			
		}
		
		System.out.println(list5.get(0) + " è un elemento inizializzato");
//		int j = list5.get(0);
//		System.out.println(j);
		int i = 0;
		try {
			i = list5.get(0);
		} catch (NullPointerException e) {
			i = 0;
			System.out.println(e.getMessage() + " i è stato inizializzato a 0");
		} finally {
			System.out.println(i);
		}
		
		System.out.println("=====================");

	}

}
