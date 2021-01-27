package it.beije.ananke.mezzi;

public abstract class Mezzi {
	
	int numPosti;
	String nome;
	String marca;
	
	public void setPosti(int numPosti) {
		if(numPosti>0) {
			this.numPosti = numPosti;
		}else {
			System.out.println("Attenzione il numero di posti specificato non è corretot");
		}
		
	}
	
	public void dettagliVeicolo() {
		System.out.println("Nome: " + nome + ", numero posti: " + numPosti + ", marca: " + marca);
	}
}
