package it.beije.ananke;

public class Tree{
	public static void main(String[] args) {
		int x = 10;
		int y = 5 + --x + --x + x++;
		System.out.println(x + "must be 9");
		System.out.println(y + "must be 30");
	}
}