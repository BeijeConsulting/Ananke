package it.beije.ananke.inherance;

public class PesceRondine extends Pesci implements CapacitàNuotare, CapacitàVolare{
    @Override
    void stampaSpecifiche() {
        getRegno();
        getPhylum();
        getClasse();
        getSpecie();
        quantitàUova();
        nuotare();
        volare();
        velocitàMax();
    }

    @Override
    void getSpecie() {
        System.out.println("Sono un pesce rondine");
    }

    @Override
    public void quantitàUova() {
        System.out.println("Non ho un dato sulla media delle uova deposte");
    }

    @Override
    public void velocitàMax() {
        System.out.println("Riesco a volare per 45 secondi");
    }
}
