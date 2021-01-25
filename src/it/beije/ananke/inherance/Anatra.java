package it.beije.ananke.inherance;

public class Anatra extends Uccelli implements CapacitàVolare, CapacitàNuotare, DespositoUova, SpostamentoDueZampe{
    @Override
    void stampaSpecifiche() {
        getRegno();
        getPhylum();
        getClasse();
        getSpecie();
        quantitàUova();
        nuotare();
        dueZampe();
        volare();
        velocitàMax();
    }

    @Override
    void getSpecie() {
        System.out.println("Sono un'anatra");
    }

    @Override
    public void quantitàUova() {
        System.out.println("Depongo dalle 8 alle 13 uova");
    }

    @Override
    public void velocitàMax() {
        System.out.println("Posso raggiungere i 130 km/h");
    }
}
