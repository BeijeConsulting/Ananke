package it.beije.ananke.inherance;

public interface CapacitàNuotare extends Spostamento{
    default void nuotare(){
        System.out.println("So nuotare");
    }
}
