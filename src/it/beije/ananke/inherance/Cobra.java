package it.beije.ananke.inherance;

public class Cobra extends Rettili implements Strisciamento{
    @Override
    void stampaSpecifiche() {
        getRegno();
        getPhylum();
        getClasse();
        getSpecie();
        quantitaUova();
        strisciando();
        velocitaMax();
    }

    @Override
    void getSpecie() {
        System.out.println("Sono un cobra");
    }

    @Override
    public void quantitaUova() {
        System.out.println("Depongo da 20 a 40 uova");
    }

    @Override
    public void velocitaMax() {
        System.out.println("Raggiungo  20 km/h");
    }
}
