package it.beije.ananke.inherance;

public class Aquila extends Uccelli implements CapacitàVolare, DespositoUova{
    @Override
    void stampaSpecifiche() {
        getRegno();
        getPhylum();
        getClasse();
        getSpecie();
        quantitàUova();
        volare();
        velocitàMax();
    }

    @Override
    void getSpecie() {
        System.out.println("Sono un'aquila");
    }

    @Override
    public void quantitàUova() {
        System.out.println("Depongo da 1 a 3 uova");
    }

    @Override
    public void velocitàMax() {
        System.out.println("In picchiata raggiungo i 320 km/h");
    }
}
