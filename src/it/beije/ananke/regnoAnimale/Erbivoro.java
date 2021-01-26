package it.beije.ananke.regnoAnimale;

public interface Erbivoro {
	
	public default void mangia() {
		System.out.println("MANGIO ERBA!");
	}
}
