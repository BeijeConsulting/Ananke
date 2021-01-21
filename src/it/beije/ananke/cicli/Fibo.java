package it.beije.ananke.cicli;

public class Fibo{
	
	public static void main(String[] args) {
		
		long uno = 0, due = 1, ultimo;
		System.out.print(uno + ", " + due);
		for(int i = 0; i < 48; i++){
			ultimo = uno + due;
			System.out.print(", " + ultimo);
			uno = due;
			due = ultimo;
		}
	}
}