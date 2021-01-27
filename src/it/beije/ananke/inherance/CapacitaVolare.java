package it.beije.ananke.inherance;

public interface CapacitaVolare extends Spostamento{
    default void volare(){
        System.out.println("So volare");
    }

}
