package it.beije.javaix;

public class Stringhe {

	public static void main(String[] args) {
		String[] array = new String[6];
		array[0] = "Pippo   ";
		array[1] = "Gino   ";
		array[2] = "Pino   ";
		array[3] = "Lino   ";
		array[4] = "Sant   ";
		array[5] = "Gennaro   ";
		StringBuilder f = new StringBuilder();
		for (int i = 0; i < 6; i++) {
f.append(array[i].trim().toUpperCase());
		}
System.out.println(f.toString());
	}

}
