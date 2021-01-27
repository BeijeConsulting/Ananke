package it.beije.ananke.inherance;

public class Anatra extends Uccelli implements CapacitaVolare, CapacitaNuotare, DespositoUova, SpostamentoDueZampe{
    @Override
    void stampaSpecifiche() {
        getRegno();
        getPhylum();
        getClasse();
        getSpecie();
        quantitaUova();
        nuotare();
        dueZampe();
        volare();
        velocitaMax();
    }

    @Override
    void getSpecie() {
        System.out.println("Sono un'anatra");
    }

    @Override
    public void quantitaUova() {
        System.out.println("Depongo dalle 8 alle 13 uova");
    }

    @Override
    public void velocitaMax() {
        System.out.println("Posso raggiungere i 130 km/h");
    }
}
