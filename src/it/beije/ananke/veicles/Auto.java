package it.beije.ananke.veicles;

public class Auto extends Motore implements NonVola {
	public String casaProd;
	public String tipologia;
	
	public Auto(int cc, String alimentazione, int numPosti, String casaProd, String tipologia) {
		
		super(cc,alimentazione,numPosti);
		this.casaProd = casaProd;
		this.tipologia = tipologia;
	}

}
