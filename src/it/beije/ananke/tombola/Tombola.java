package it.beije.ananke.tombola;

public class Tombola {

    public static void main(String[] args) {

        int qtaCartelle = 6;

        PoolCartelle pool = new PoolCartelle();
        pool.creaCartelle(qtaCartelle);
        pool.stampaCartelle();

    }
}
