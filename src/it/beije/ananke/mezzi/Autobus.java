package it.beije.ananke.mezzi;

public class Autobus extends MezziViaTerra implements APagamento{
	int numPiani;
	double prezzoBiglietto;
	
	public Autobus(String marca, int numPiani, int numPosti) {
		super.ruote = 4;
		super.nome = "Autobus";
		super.marca = marca;
		this.numPiani = numPiani;
		super.numPosti = numPosti;
	}
	
	public double guadagnoCorsa(double prezzoBiglietto, int postiOccupati){
		if(postiOccupati<=super.numPosti) {
			return postiOccupati*prezzoBiglietto;
		}else {
			System.out.println("");
			return 0;
		}
	}
}
