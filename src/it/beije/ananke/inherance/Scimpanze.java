package it.beije.ananke.inherance;

public class Scimpanze extends Mammiferi implements Gestazione, SpostamentoQuattroZampe, SpostamentoDueZampe{
    @Override
    void stampaSpecifiche() {
        getRegno();
        getPhylum();
        getClasse();
        getSpecie();
        tempoGestazione();
        dueZampe();
        quattroZampe();
        velocitaMax();
    }

    @Override
    void getSpecie() {
        System.out.println("Sono uno scimpanzè");
    }

    @Override
    public void tempoGestazione() {
        System.out.println("Il mio tempo di gestazione è di 243 giorni");
    }

    @Override
    public void velocitaMax() {
        System.out.println("Le mie capacità mi permettono di muovermi agilmente tra i rami degli alberi");
    }
}
