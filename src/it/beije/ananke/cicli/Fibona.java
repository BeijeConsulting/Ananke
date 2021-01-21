package it.beije.ananke.cicli;

public class Fibona{
	
	public static void main(String[] args) {
		String num = args[0];
		int val = Integer.parseInt(num);
		long uno = 0, due = 1, ultimo;
		System.out.print(uno + ", " + due);
		for(int i = 0; i < val - 2; i++){
			ultimo = uno + due;
			System.out.print(", " + ultimo);
			uno = due;
			due = ultimo;
		}
	}
}