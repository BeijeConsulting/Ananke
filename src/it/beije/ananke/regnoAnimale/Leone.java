package it.beije.ananke.regnoAnimale;

public class Leone extends Mammiferi implements Carnivoro, RespiraPolmoni{
	
	public Leone(int numZampe, int numVertebre, String nome, int età) {
		super(numZampe, numVertebre, false, nome, età);
		
	}
	
	public Leone(int numZampe, String nome, int età, int mesiGestazione) {
		super(numZampe, false, nome, età, mesiGestazione);
		
	}

	@Override
	public String verso() {
		return "ROAR!";
		
	}

	@Override
public void mangia() {
		
		
	}

}
