package it.beije.ananke.astrazione;

public interface Spegnimento{
	public default void shotDown() {
		System.out.println("Arresto del sistema in corso");
		
	}
}
