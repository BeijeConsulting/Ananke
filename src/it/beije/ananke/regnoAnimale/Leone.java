package it.beije.ananke.regnoAnimale;

public class Leone extends Mammiferi implements Carnivoro, RespiraPolmoni{
	
	public Leone(int numZampe, int numVertebre, String nome, int et�) {
		super(numZampe, numVertebre, false, nome, et�);
		
	}
	
	public Leone(int numZampe, String nome, int et�, int mesiGestazione) {
		super(numZampe, false, nome, et�, mesiGestazione);
		
	}

	@Override
	public String verso() {
		return "ROAR!";
		
	}

	@Override
public void mangia() {
		
		
	}

}
