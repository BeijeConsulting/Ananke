package it.beije.ananke;
import java.util.*;

public class MyList {
	
	public static void main(String... args){
		
		List<String> l1 = new ArrayList<>();
		List<String> l2 = new ArrayList<>();
		List<String> l3 = new ArrayList<>();
		
		l1.add("sono");
		l1.add("uguale");
		l1.add("all'altra");
		l1.add("lista");
		
		l2.add("sono");
		l2.add("uguale");
		l2.add("all'altra");
		l2.add("lista");
		
		l3.add("sono");
		l3.add("diversa");
		l3.add("dall''altra");
		l3.add("lista");
		
		System.out.println(myEquals(l1,l2));
		System.out.println(myEquals(l1,l3));
		
	}
	
	public static boolean myEquals(List<String> l1, List<String> l2){
		
		//
		int i = 0;
		for(String s1: l1){
			
				if(s1.equals(l2.get(i)) == false){
					return false;
				}
				
				i++;
		}
		
		return true;
	}
	
	public static boolean myEquals2(List<String> l1, List<String> l2){
		
		String[] v1 = l1.toArray(new String[0]);
		String[] v2 = l2.toArray(new String[0]);
		
		for(int i = 0; i < l1.size(); i++) {
			if(v1[i].equals(v2[i]) == false){
				return false;
			}
			
		}
		
		return true;
	}
	
	

}
