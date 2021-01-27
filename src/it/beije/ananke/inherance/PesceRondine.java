package it.beije.ananke.inherance;

public class PesceRondine extends Pesci implements CapacitaNuotare, CapacitaVolare {
    @Override
    void stampaSpecifiche() {
        getRegno();
        getPhylum();
        getClasse();
        getSpecie();
        quantitaUova();
        nuotare();
        volare();
        velocitaMax();
    }

    @Override
    void getSpecie() {
        System.out.println("Sono un pesce rondine");
    }

    @Override
    public void quantitaUova() {
        System.out.println("Non ho un dato sulla media delle uova deposte");
    }

    @Override
    public void velocitaMax() {
        System.out.println("Riesco a volare per 45 secondi");
    }
}
