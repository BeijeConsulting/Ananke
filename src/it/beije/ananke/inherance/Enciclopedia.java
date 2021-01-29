package it.beije.ananke.inherance;

import java.util.ArrayList;

public class Enciclopedia {
    public static void main(String[] args) {

        ArrayList<Animali> animali = new ArrayList<>();

        Aquila aquila = new Aquila();
        animali.add(aquila);

        Anatra anatra = new Anatra();
        animali.add(anatra);

        Cobra cobra = new Cobra();
        animali.add(cobra);

        PesceRondine pesceRondine = new PesceRondine();
        animali.add(pesceRondine);

        SerpenteVolanteDelParadiso serpenteVolanteDelParadiso = new SerpenteVolanteDelParadiso();
        animali.add(serpenteVolanteDelParadiso);

        Scimpanze scimpanze = new Scimpanze();
        animali.add(scimpanze);

        Tigre tigre = new Tigre();
        animali.add(tigre);

        for (Animali animale: animali
             ) {
            animale.stampaSpecifiche();
            System.out.println("\n\n");
        }
    }
}
