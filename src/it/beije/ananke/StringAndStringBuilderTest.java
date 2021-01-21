package it.beije.ananke;

public class StringAndStringBuilderTest {
	public static void main(String[] args) {
		
		String name = "Stefano";
		String secondName = "Francesco";
		String surname = "Marinoni";
		
		System.out.println("Length of your name is: " + name.length());
		String firstAndSecond = name +" " + secondName; 
		System.out.println("Name&SecondName: " + firstAndSecond);
		
		//ora abbiamo tre oggetti stringa referenziati dentro alla string pool
		//mentre firstAndSecond è un oggetto non nella string pool
		
		System.out.println("Char at index 5 of your name: " + name.charAt(5) );
		System.out.println("No. That was the 6th. It's:  " + name.charAt(4) );
		
		System.out.println("Let's substring");
		System.out.println("Just one index till the end: " + name.substring(3));
		System.out.println("With the right index: " + name.substring(2, 5));
		System.out.println("Wrong index: " + name.substring(3, 3));
		
		String nameUpper = name.toUpperCase();
		System.out.println("Name to uppercase:" + nameUpper);
		
		System.out.println("substring of firstAndSecond is equal to name? " + firstAndSecond.substring(0,7).equals(name));
		System.out.println("firstAndSecond start with st? " + firstAndSecond.startsWith("st"));
		System.out.println("firstAndSecond end with sco? " + firstAndSecond.endsWith("sco"));
		
		System.out.println("firstAndSecond contains name? " + firstAndSecond.contains(name));
		System.out.println("firstAndSecond contains secondName? " + firstAndSecond.contains(secondName));
		
		String nameWithA = firstAndSecond.replace('a', 'A');
		System.out.println("Name with upper A: " + nameWithA);
		
		StringBuilder myName = new StringBuilder(20);
		System.out.println("It's empty: " + myName);
		System.out.println("Let's append the name: " + myName.append(name));
		System.out.println("If we create another referene of StringBuilder? ");
		StringBuilder anotherMyName = myName; 
		Boolean is = (anotherMyName == myName);
		System.out.println("Without creating a mew StringBuilder, they point to the same object? " + is);
		System.out.println("Insert some underscore: " + myName.append(secondName).insert(3, "_").insert(7, "_").insert(13, "_").insert(15, "_"));
		System.out.println("REVERS ALL!! " + myName.reverse());
	}
}
