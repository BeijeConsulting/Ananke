package it.beije.ananke.veicles;

public abstract class Motore extends Veicolo {
	
	
	public int cc;
	public String alimentazione;
	public Motore(int cc, String alimentazione, int numPosti) {
		super(numPosti);
		this.cc = cc;
		this.alimentazione = alimentazione;
	}
	
	
}
