package interfacce;

public class Autobus extends MezziDiTerra implements APagamento{
	public double prezzoBiglietto;
	
	public void dettagliVeicolo() {
		System.out.println("Questo oggetto è " + this.nome);
		System.out.println("La marca di questo oggetto è " + this.marca);
		System.out.println("Questo veicolo ha " + this.numRuote + " ruote.");
	}
	
	public Autobus(String marca, int posti, double costo) {
		this.marca = marca;
		super.nome = "autobus";
		super.numRuote = 4;
		super.posti = posti;
		this.prezzoBiglietto = costo;
	}
	
	public double guadagnoCorsaMax(int numPosti, float costoBiglietto) {
		if(numPosti > this.posti || numPosti < 0) {
			System.out.println("Il numero di passeggeri inserito non è consentito");
			return -1;
		}else {
			return numPosti*costoBiglietto;
		}
	}
}
