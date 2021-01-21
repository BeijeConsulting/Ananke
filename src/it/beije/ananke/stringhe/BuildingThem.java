package it.beije.ananke.stringhe;

public class BuildingThem {
	StringBuilder nome;

	public static void main(String[] args) {
		BuildingThem persona1 = new BuildingThem(10);
		persona1.nome.append("Sara");
		StringBuilder omonima = new StringBuilder("Sara");
		System.out.println(persona1.nome.toString() == omonima.toString());
		System.out.println(persona1.nome.toString().equals(omonima.toString()));
		System.out.println(persona1.nome == omonima);
		omonima = persona1.nome;
		System.out.println(persona1.nome == omonima);
		
		System.out.println("Capacità prima dell'aggiunta del cognome: " + persona1.nome.capacity());
		persona1.nome.append(" De Monaco");
		System.out.println("Capacità dopo l'aggiunta del cognome: " + persona1.nome.capacity());
		System.out.println("Lunghezza dopo l'aggiunta del cognome: " + persona1.nome.length());
		
		System.out.println("Il mio nome è " + persona1.nome + ".");
		System.out.println("Il mio cognome è " + persona1.nome.substring(persona1.nome.indexOf("D")) + ".");
		
		persona1.nome.delete(0, persona1.nome.indexOf("a", persona1.nome.indexOf("r")) + 2);
		System.out.println("Il mio nome è " + persona1.nome + ".");
		
		persona1.nome.insert(0, "Sara ");
		System.out.println("Il mio nome è " + persona1.nome + ".");
		
		
	}
	
	public BuildingThem(int value) {
		nome = new StringBuilder(value);
	}
	
	public BuildingThem(String str) {
		nome = new StringBuilder(str);
	}

}
