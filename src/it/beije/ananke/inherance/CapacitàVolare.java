package it.beije.ananke.inherance;

public interface CapacitàVolare extends Spostamento{
    default void volare(){
        System.out.println("So volare");
    }

}
