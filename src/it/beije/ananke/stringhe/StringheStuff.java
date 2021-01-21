package it.beije.ananke.stringhe;

public class StringheStuff {
	String name;
	
	public static void main(String[] args) {
		StringheStuff first = new StringheStuff("Sara");
		String usern = "Sara";
		String second = new String("Sara");
		//String sum; int cont; char ch;
		
		//equals
		System.out.println(usern.equals(usern));
		System.out.println(usern.equals(first.name));
		System.out.println(usern.equals(second));
		System.out.println(usern == second);
		System.out.println(first.name.equals(second));
		System.out.println(first.name == second);
		
		//length - toLowerCase
		System.out.println("Questo username è lungo " + usern.length() + " caratteri");
		System.out.println(usern.length() + first.name.length() + second.length());
		System.out.println(usern.toLowerCase());
		System.out.println(usern);
		
		//charAt - indexOf
		System.out.println(usern.charAt(usern.indexOf('a')));
		System.out.println(first.name.indexOf('a', first.name.indexOf('r')));
		
		//substring
		System.out.println(second.substring(second.indexOf('a')));
		System.out.println(second.substring(first.name.indexOf('S'), 2));
		
		//contains - replace
		System.out.println(usern.contains("sa"));
		System.out.println(second.replace('a', 'e'));
		System.out.println(second);
		
		String ciao = "Hello       There             \t\n";
		System.out.println(ciao);
		System.out.println(ciao.trim());
		
	}
	
	public StringheStuff(String value){
		name = value;
	}
}
