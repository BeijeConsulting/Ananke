package it.beije.ananke.tombola;

public class Cartella {
	int [][] numeri = new int [3][9];
	int idcartella;
	public Cartella(int contatore) {
		idcartella=contatore;
	}
	public int getIdcartella() {
		return idcartella;
	}
	public int[][] getNumeri() {
		return numeri;
	}
	public void setNumeri(int numero, int x, int y) {
		numeri[x][y] = numero;
	}
}