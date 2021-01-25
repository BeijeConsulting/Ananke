package it.beije.ananke.inherance;

public interface SpostamentoQuattroZampe extends SpostamentoTerrestre{
    default void quattroZampe(){
        System.out.println("Mi sposto con tutti e quattro gli arti");
    }
}
