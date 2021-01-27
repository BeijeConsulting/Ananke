package it.beije.ananke.inherance;

public class SerpenteVolanteDelParadiso extends Rettili implements Strisciamento, CapacitaVolare {
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
        System.out.println("Sono un serpente volante del paradiso");
    }

    @Override
    public void quantitaUova() {
        System.out.println("Depongo fino a 10 uova");
    }

    @Override
    public void velocitaMax() {
        System.out.println("I miei voli durano pochi secondi ma posso raggiungere i 40 km/h");
    }
}
