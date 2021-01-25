package it.beije.ananke.inherance;

public class SerpenteVolanteDelParadiso extends Rettili implements Strisciamento, CapacitàVolare{
    @Override
    void stampaSpecifiche() {
        getRegno();
        getPhylum();
        getClasse();
        getSpecie();
        quantitàUova();
        strisciando();
        velocitàMax();
    }

    @Override
    void getSpecie() {
        System.out.println("Sono un serpente volante del paradiso");
    }

    @Override
    public void quantitàUova() {
        System.out.println("Depongo fino a 10 uova");
    }

    @Override
    public void velocitàMax() {
        System.out.println("I miei voli durano pochi secondi ma posso raggiungere i 40 km/h");
    }
}
