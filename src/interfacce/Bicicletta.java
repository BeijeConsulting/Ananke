package interfacce;

public class Bicicletta extends MezziDiTerra {
	
	public void dettagliVeicolo() {
		System.out.println("Questo oggetto è una" + this.nome);
		System.out.println("La marca di questo oggetto è una" + this.marca);
		System.out.println("Questo veicolo ha" + this.numRuote + "ruote.");
	}
	
	public Bicicletta(String marca) {
		super.marca = marca;
		super.nome = "bicicletta";
		super.numRuote = 2;
		super.posti = 1;
	}
}
