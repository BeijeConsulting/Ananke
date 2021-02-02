package it.beije.ananke.altro.capitolo5.astrazione;

public interface Touchscreen {
    public default void touch() {
        System.out.println("Toccami, vedrai cosa succede!");
    }
}
