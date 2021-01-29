package it.beije.ananke.inherance;

public class Aquila extends Uccelli implements CapacitaVolare, DespositoUova{
    @Override
    void stampaSpecifiche() {
        getRegno();
        getPhylum();
        getClasse();
        getSpecie();
        quantitaUova();
        volare();
        velocitaMax();
    }

    @Override
    void getSpecie() {
        System.out.println("Sono un'aquila");
    }

    @Override
    public void quantitaUova() {
        System.out.println("Depongo da 1 a 3 uova");
    }

    @Override
    public void velocitaMax() {
        System.out.println("In picchiata raggiungo i 320 km/h");
    }
}
