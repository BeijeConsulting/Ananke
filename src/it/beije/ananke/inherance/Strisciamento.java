package it.beije.ananke.inherance;

public interface Strisciamento extends Spostamento{

    default void strisciando(){
        System.out.println("Mi sposto strisciando");
    }
}
