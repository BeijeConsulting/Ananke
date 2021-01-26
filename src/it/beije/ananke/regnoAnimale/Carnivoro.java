package it.beije.ananke.regnoAnimale;

public interface Carnivoro {

	public default void mangia() {
		System.out.println("MANGIO CARNE!");
	}
}
