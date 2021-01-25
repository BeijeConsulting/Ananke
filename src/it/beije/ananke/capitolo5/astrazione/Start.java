package it.beije.ananke.capitolo5.astrazione;

public abstract interface Start {
    public default void start() {
        System.out.println("Il pc si sta accendendo");
    }
}
