package it.beije.ananke;

public class Stringa {

	public static void main(String[] args) {
		
		//Strings
		
		String s = "hello world";
		String s1 = s.toUpperCase();
		String s2  = "  hello world";
		
		System.out.println("Stringa di riferimento: '" + s + "'");
		System.out.println("lunghezza stringa: " + s.length());
		System.out.println("carattere nella posizione 8: " + s.charAt(8));
		System.out.println("posizione del carattere 'o': " + s.indexOf('o'));
		System.out.println("posizione della stringa 'rl': " + s.indexOf("rl"));
		System.out.println("posizione del carattere 'l' a partire dalla posizione 5: " + s.indexOf('l',5));
		System.out.println("sottostringa: " + s.substring(2,7));
		System.out.println("maiuscolo: " + s.toUpperCase());
		System.out.println("uguaglianza tra '" + s + "' e '" + s1 + "': " + s.equals(s1));
		System.out.println("uguaglianza ignore case tra '" + s + "' e '" + s1 + "': " + s.equalsIgnoreCase(s1));
		System.out.println("lettera iniziale 'h': " + s.startsWith("h"));
		System.out.println("lettere finali 'LD': " + s.endsWith("LD"));
		System.out.println("contiene la lettera 'g': " + s.contains("g"));
		System.out.println("rimpiazza 'h' con 'H': '" + s.replace('h', 'H') + "'");
		System.out.println("trim '" + s2 + "': " + "'" + s2.trim() + "'");
		
		//String builders
		
		StringBuilder sb = new StringBuilder("hello");
		System.out.println("append: " + sb.append(" ").append("world"));
		System.out.println("insert: " + sb.insert(11,"!"));
		System.out.println("delete at: " + sb.deleteCharAt(11));
		System.out.println("delete 'world': " + sb.delete(5, 12) );
		System.out.println("reverse: " + sb.reverse());
		System.out.println("to string: " + sb.reverse().toString());
	}

}
