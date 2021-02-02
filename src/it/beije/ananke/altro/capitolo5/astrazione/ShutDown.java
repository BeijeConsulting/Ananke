package it.beije.ananke.altro.capitolo5.astrazione;

public interface ShutDown {
    public default void shutDown() {
        System.out.println("Arresto del sistema in corso");
    }
}
