package it.beije.ananke;

import java.util.*;

public class Lists_Arrays {

	 public static void main(String[] args) {
		
		String[] animals = {"Lion", "Dog", "Cat", "Bird", "Hawk"};
			
		ArrayList<String> listAnim = new ArrayList<>();
		
		List<String> transformed = Arrays.asList(animals);
		
		
		// trying custom method "equals" for List
		ArrayList<String>  anim= new ArrayList<>();
		ArrayList<String>  clone= new ArrayList<>();
		
		anim.add("Lion");
		clone.add("Lion");
		clone.add("Cat");
		anim.add("Cat");
		anim.add("Bird");
		clone.add("Bird");	
		System.out.println(Lists_Arrays.equals(anim,clone)); 
		
	// <-------------- end of try -------------------->
		
		
		for(String elem : animals)
		{
			listAnim.add(elem);
		}

		
		System.out.println("transformed and listAnim: " + transformed.equals(listAnim));
		
		Collections.sort(transformed);
				
		System.out.println("transformed(sorted) and listAnim: " + transformed.equals(listAnim));
		
		System.out.println("Elem removed: " + listAnim.remove(0));
		
		System.out.println(listAnim);
		
		System.out.println("Elem replaced: " + listAnim.set(0,"Wale"));
		
		System.out.println(listAnim);
		
		System.out.println(listAnim.contains("Bird"));
		
		listAnim.clear();
		
		System.out.println("Just cleared listAnimal: " + listAnim);
		
		System.out.println(listAnim.isEmpty());
		
}
	//equals method
	public static boolean equals(List list1, List list2)  //NON USANDO L'EQUALS DI ArrayList list1.equals(list2)
	{
		if(list1.size()==list2.size())
		{
			for(int i=0; i<list1.size(); i++)
			{
				if(list1.get(i) != list2.get(i))
				{
					return false;
				}		
			}
			
			return true;
		}
		
		else {
			
				return false;
		}
		
	}
	
	
	
}