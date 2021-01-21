package it.beije.ananke;

public class ProvaStr{


	public static void main(String[] args){
	
		// STRING VS STRING
		String s1 = "ABC";
		String s2 = new String("ABC");
		String s3 = "ABC";
		String s4 = new String("ABC");
		
		System.out.println("Prova su stringhe");
		System.out.println("--------------------------");
		System.out.println(s1==s2);
		System.out.println(s1.equals(s2));
		System.out.println(s1==s3);
		System.out.println(s1.equals(s3));
		System.out.println(s2==s4);
		
		System.out.println();
		
		System.out.println(s2.replace("A","YYY").replace("C","XXX").replace("B","   "));
		
		System.out.println("--------------------------");
		
		// STRINGBUILD VS STRINGBUILD
		StringBuilder sb = new StringBuilder("NON SONO UNA STRINGA   ");
		
		System.out.println("Prova su stringBuild \n--------------------------");
		System.out.println(sb.append("MA ASSOMIGLIO").delete(21,23));
		System.out.println(sb.toString().toLowerCase());
		
		StringBuilder sb1 = new StringBuilder("S1");
		StringBuilder sb2 = new StringBuilder("S1");
		StringBuilder sb3 = new StringBuilder(3);
		System.out.println(sb1==sb2);
		System.out.println(sb1.equals(sb2));
		
		sb1.insert(0,"@");
		sb1.insert(3,"@");
		System.out.println(sb1);
		System.out.println();
		
		sb1 = new StringBuilder("A");  // Non posso assegnare a stringBuild
		sb2 = new StringBuilder("A");  // con il simbolo =, devo fare new StringBuild
		sb3 = sb1.append("GGG");
		System.out.println(sb1==sb2);
		System.out.println(sb1==sb3);
		System.out.println();
		
		StringBuilder sb5 = new StringBuilder("SONO UGUALE A SB5");
		StringBuilder sb6 = sb5;
		System.out.println(sb5);
		System.out.println(sb5);
		sb6.delete(0,5);
		System.out.println(sb5); // Se modifico sb6 modifico sb5
		System.out.println(sb5); // perchè al contrario delle stringhe
		System.out.println();	 // quando modifico non creo un nuovo 
								 // obj in memoria
		
		// STRING VS STRINGBUILD
		String str1 = "prova";
		StringBuilder strB = new StringBuilder("prova");
		System.out.println(str1.equals(strB));
	

	}


}