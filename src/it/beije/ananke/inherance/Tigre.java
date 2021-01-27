package it.beije.ananke.inherance;

public class Tigre extends Mammiferi implements SpostamentoQuattroZampe, Gestazione{

    public void getSpecie(){
        System.out.println("Sono una tigre");
    }

    @Override
    void stampaSpecifiche() {
        getRegno();
        getPhylum();
        getClasse();
        getSpecie();
        tempoGestazione();
        quattroZampe();
        velocitaMax();
    }

    @Override
    public void tempoGestazione() {
        System.out.println("Il mio tempo di gestazione è tra i 93 e i 112 giorni");
    }

    @Override
    public void velocitaMax() {
        System.out.println("La mia velocità massima è di 65 km/h");
    }
}
