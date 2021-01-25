package it.beije.ananke.tombola;


import java.util.ArrayList;

public class PoolCartelle {

    private ArrayList<CartellaTombola> pool = new ArrayList<>();
    private ArrayList<ArrayList<Integer>> disponibili = new ArrayList<>(9);

    public void creaCartelle(int qtaCartelle){

        popoloDisponibili();

        for (int i = 0; i < qtaCartelle; i++) {

            pool.add(new CartellaTombola(i+1));
            disponibili = pool.get(i).creaCartella(disponibili);
            if(i % 6 == 5)
                popoloDisponibili();
        }

    }

    public void stampaCartelle(){

        for (CartellaTombola cartella : pool) {
            System.out.println("Cartella id: "+ cartella.getId() + "\n");
            cartella.stampaCartella();
            System.out.println("\n\n");
        }

    }

    public void popoloDisponibili(){

        disponibili.clear();

        for (int i = 0; i < 9; i++) {
            int num;
            ArrayList<Integer> decina = new ArrayList<>(9);

            if(i == 0)
                num = 1;                                //inizializzo a uno
            else
                num = 10*i;   //inizializzo a 10, 20, 30, 40, 50, 60, 70, 80

            for(int j = num; (i == 8) ? (j <= num + 10) : ((i == 0) ? (j <= num + 8) : (j <= num + 9)); j++){
                decina.add(j);
            }
            disponibili.add(decina);
        }

    }

}