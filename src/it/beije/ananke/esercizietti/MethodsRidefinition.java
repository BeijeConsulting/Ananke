package it.beije.ananke.esercizietti;

import java.util.ArrayList;
import java.util.List;

public class MethodsRidefinition {
	
	public static String strngAppend(String orig, String add) {
		
		return orig + add;
		
	}
	
	public static String strngInsert(String orig, int offset, String add) {
		
		//devo trovare l'offset in orig (...)-> orig[offset-1]
		//perci� controllare che offset>0
		
		int index;
		String result = "";
		
		if(offset>0) {
			index = offset-1;
		}
		else {
			index = offset;
		}
		
		//copio la prima parte della stringa di origine
		for(int i=0; i<=index; i++) {
			result = result + orig.charAt(i);
		}
		
		//concateno la stringa da inserire
		
		result = result + add;
		
		//concateno l'altra parte della stringa
		for(int i=index+1; i<orig.length(); i++) {
			result = result + orig.charAt(i);
		}
		
		return result;
	
	}
	
	public static String strngDelete(String orig, int startIndx, int endIndx) {
		
		//la delete di StringBuilder conta che l'utente tenga conto dell'indicizzazione a partire da 0
		
		String firstPart = "";
		String secondPart = "";
		
		String result;
		
		for(int i=0; i<orig.length(); i++) {
			
			if(i<startIndx) {
				firstPart = firstPart + orig.charAt(i);
			}
			else if(i<endIndx) {
				continue;				
			}
			else {
				secondPart = secondPart + orig.charAt(i);
			}
		}
		
		result = firstPart + secondPart;
		
		return result;
		
	}
	
	public static String strngDeleteCharAt(String orig, int index) {
		
		String result = "";
		
		for(int i=0; i<orig.length(); i++) {
			
			if(i!=index)
				result = result + orig.charAt(i);
		}
		
		return result;
	}
	
	public static String strngReverse(String orig) {
		
		String result = "";
		
		for(int i=orig.length()-1; i>=0; i--)
			result = result + orig.charAt(i);
		
		return result;
	}
	
	public static boolean areEquals(List list1, List list2) {
		
		if(list1.size() != list2.size())
			return false;
		else {
			for(int i=0; i<list1.size(); i++) {
				
				if(!(list1.get(i).equals(list2.get(i)))) 
					return false;
				
			}
		}
		
		return true;
		
	}
	
	public static void main(String args[]) {
		
		//testeremo i metodi fatti sopra
		
		// 0 1 2 3 4 5 6
		// s t e f a n o
		String origString = "stefano";
		String addString = "Ciao";
		
		List<Integer> list1 = new ArrayList<>();
		List<Integer> list2 = new ArrayList<>();
		
		list1.add(0);
		list1.add(1);
		list1.add(2);
		
		list2.add(0);
		list2.add(1);
		list2.add(3);
		
		System.out.println(strngAppend(origString, addString));
		System.out.println(strngInsert(origString, 4, addString));
		System.out.println(strngDelete(origString, 2, 5));
		System.out.println(strngDeleteCharAt(origString, 4));
		System.out.println(strngReverse(origString));
		System.out.println("Are equals?\t" + areEquals(list1, list2));
		
	}

}
