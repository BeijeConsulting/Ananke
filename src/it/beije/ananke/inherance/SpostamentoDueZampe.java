package it.beije.ananke.inherance;

public interface SpostamentoDueZampe extends SpostamentoTerrestre {
    default void dueZampe(){
        System.out.println("Mi sposto usando gli arti inferiori");
    }
}
