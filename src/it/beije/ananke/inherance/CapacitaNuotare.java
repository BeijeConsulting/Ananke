package it.beije.ananke.inherance;

public interface CapacitaNuotare extends Spostamento{
    default void nuotare(){
        System.out.println("So nuotare");
    }
}
