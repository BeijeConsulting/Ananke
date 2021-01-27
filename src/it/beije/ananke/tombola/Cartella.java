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
	public void getNumeri() {
		for(int i=0;i<3;i++) {
			for(int j=0;j<9;j++) {
				System.out.print(numeri[i][j] + "	");
			}
			System.out.println("");
		}
		System.out.println("-----------------------------------------");
	}
	public void setNumeri(int numero, int riga, int colonna) {
	this.numeri[riga][colonna] = numero;
	}
}