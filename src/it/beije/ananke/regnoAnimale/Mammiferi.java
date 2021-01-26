package it.beije.ananke.regnoAnimale;

public abstract class Mammiferi extends Vertebrati{
	
	public int numZampe;
	public int mesiGestazione;
	
	public Mammiferi(int numZampe,int numVertebre, boolean cordati, String nome, int età, int mesiGestazione) {
		
		super(cordati,numVertebre,nome,età);
		this.numZampe = numZampe;
		this.mesiGestazione = mesiGestazione;

	}

	public Mammiferi(int numZampe, boolean cordati, String nome, int età, int mesiGestazione) {
		super(cordati,nome,età);
		this.numZampe = numZampe;
		this.mesiGestazione = mesiGestazione;
	}
	
	public Mammiferi(int numZampe,int numVertebre, boolean cordati, String nome, int età) {
		
		super(cordati,numVertebre,nome,età);
		this.numZampe = numZampe;
		this.mesiGestazione = 6;

	}
	
	//public abstract String verso();

}
