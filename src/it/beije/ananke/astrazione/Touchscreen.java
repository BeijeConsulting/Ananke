package it.beije.ananke.astrazione;

public interface Touchscreen {
	public default void touch() {
		System.out.println("Toccami");
	}
}
