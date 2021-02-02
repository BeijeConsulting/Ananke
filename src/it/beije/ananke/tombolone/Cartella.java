package it.beije.ananke.tombolone;

import it.beije.ananke.tombolone.NumeriTombola;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Cartella {
    private static final int NUM_RIGHE = 3;
    private static final int NUM_COLONNE = 9;
    private static final int MAX_NUM_PER_RIGA = 5;
    private static final int MAX_NUM_PER_COLONNA = 2;
    private static final int MAX_NUM = 15;
    private static ArrayList<ArrayList<Integer>> numeriTombola = new NumeriTombola().getNumeriTombola();
    public static int count = 0;
    public static int[][] countArray = new int[6][NUM_COLONNE];
    public static int[] conteggioNumeriUno = new int[NUM_COLONNE];

//	private int [][] matrice = new int[NUM_RIGHE][NUM_COLONNE];

    public int[][] riempiCartella() {
        System.out.println("Cartelle n. " + count);
        int[][] cartellaGenerata = riempiPosizioni();
        System.out.println("HO riempito");
        ordinaCartella(cartellaGenerata);
        System.out.println("Ho ordinato");


        System.out.println(numeriTombola.get(0));
        System.out.println(numeriTombola.get(1));
        System.out.println(numeriTombola.get(2));
        System.out.println(numeriTombola.get(3));
        System.out.println(numeriTombola.get(4));
        System.out.println(numeriTombola.get(5));
        System.out.println(numeriTombola.get(6));
        System.out.println(numeriTombola.get(7));
        System.out.println(numeriTombola.get(8));
        System.out.println();
        for (int i = 0; i < NUM_COLONNE; i++) {
            System.out.print(conteggioNumeriUno[i]);
        }
        System.out.println();
        return cartellaGenerata;
    }

    public ArrayList<ArrayList<Integer>> generaPosizioni() {
        System.out.println("Ho iniziato a generare le posizioni");
        ArrayList<ArrayList<Integer>> posizioni = new ArrayList<>();

        ArrayList<Integer> posizioniRigaUno = new ArrayList<>();
        Random random = new Random();
        while (posizioniRigaUno.size() < MAX_NUM_PER_RIGA) {
            int num = random.nextInt(9);
            if (!posizioniRigaUno.contains(Integer.valueOf(num)) && controlloQuantitaNumeri(num)) {
                posizioniRigaUno.add(Integer.valueOf(num));
            }
        }
        System.out.println("riga 1 fatta");
        System.out.println(posizioniRigaUno.toString());
        Collections.sort(posizioniRigaUno);
        posizioni.add(posizioniRigaUno);


        ArrayList<Integer> posizioniRigaDue = new ArrayList<>();
        ArrayList<Integer> numeriDisponibiliRigaDue = new ArrayList<>();
        for (int i = 0; i < NUM_COLONNE; i++) {
            if (i == 0) {
                if (conteggioNumeriUno[i] < 3) {
                    if(posizioniRigaUno.contains(i)) {
                        numeriDisponibiliRigaDue.add(i);
                    } else {
                        posizioniRigaDue.add(i);
                        numeriDisponibiliRigaDue.add(i);
                    }
                } else {
                    posizioniRigaDue.add(i);
                }

            } else if (i == NUM_COLONNE - 1) {
                if (conteggioNumeriUno[i] < 1) {
                    if(posizioniRigaUno.contains(i)) {
                        numeriDisponibiliRigaDue.add(i);
                    } else {
                        posizioniRigaDue.add(i);
                        numeriDisponibiliRigaDue.add(i);
                    }
                } else {
                    posizioniRigaDue.add(i);
                }

            } else {
                if (conteggioNumeriUno[i] < 2) {
                    if(posizioniRigaUno.contains(i)) {
                        numeriDisponibiliRigaDue.add(i);
                    } else {
                        posizioniRigaDue.add(i);
                        numeriDisponibiliRigaDue.add(i);
                    }
                }else {
                    posizioniRigaDue.add(i);
                }
            }
        }
        System.out.println("numeri disponibili riga 2");
        System.out.println(numeriDisponibiliRigaDue);



        while (posizioniRigaDue.size() < MAX_NUM_PER_RIGA) {
            int num = random.nextInt(9);
            if (!posizioniRigaDue.contains(Integer.valueOf(num)) && controlloQuantitaNumeri(num) &&
                    numeriDisponibiliRigaDue.contains(num)) {
                posizioniRigaDue.add(Integer.valueOf(num));
                int index = numeriDisponibiliRigaDue.indexOf(num);
                numeriDisponibiliRigaDue.remove(index);
            }
        }

        for(int i = 0 ; i<NUM_COLONNE; i++) {
            if(posizioniRigaUno.contains(i) && posizioniRigaDue.contains(i)) {
                if(numeriDisponibiliRigaDue.contains(i)){
                    int index = numeriDisponibiliRigaDue.indexOf(i);
                    numeriDisponibiliRigaDue.remove(i);
                }
            }
        }
        System.out.println("riga 2 fatta");

        System.out.println(posizioniRigaDue.toString());
        Collections.sort(posizioniRigaDue);
        posizioni.add(posizioniRigaDue);

        System.out.println(numeriDisponibiliRigaDue);

        ArrayList<Integer> posizioniRigaTre = new ArrayList<>();

        ArrayList<Integer> numeriDisponibiliRigaTre = numeriDisponibiliRigaDue;
        System.out.println("Numeri disponibili riga 3");
        System.out.println(numeriDisponibiliRigaTre);


        for (int i = 0; i < NUM_COLONNE; i++) {
            int index;
            if (i == 0) {
                if (conteggioNumeriUno[i] < 3) {
                    if(posizioniRigaUno.contains(i) && posizioniRigaDue.contains(i)) {
                        index = numeriDisponibiliRigaTre.indexOf(i);
                        if(index >= 0) {
                            numeriDisponibiliRigaTre.remove(index);
                        }
                    } else {
                        if(!numeriDisponibiliRigaTre.contains(i)) {
                            numeriDisponibiliRigaTre.add(i);
                        }
                    }
                } else {
                    posizioniRigaTre.add(i);
                }
            } else if (i == NUM_COLONNE - 1) {
                if (conteggioNumeriUno[i] < 1) {
                    if(posizioniRigaUno.contains(i) && posizioniRigaDue.contains(i)) {
                        index = numeriDisponibiliRigaTre.indexOf(i);
                        if(index >= 0) {
                            numeriDisponibiliRigaTre.remove(index);
                        }
                    } else {
                        if(!numeriDisponibiliRigaTre.contains(i)) {
                            numeriDisponibiliRigaTre.add(i);
                        }
                    }
                } else {
                    posizioniRigaDue.add(i);
                }
            } else {
                if (conteggioNumeriUno[i] < 2) {
                    if(posizioniRigaUno.contains(i) && posizioniRigaDue.contains(i)) {
                        index = numeriDisponibiliRigaTre.indexOf(i);
                        if(index >= 0) {
                            numeriDisponibiliRigaTre.remove(index);
                        }
                    } else {
                        if(!numeriDisponibiliRigaTre.contains(i)) {
                            numeriDisponibiliRigaTre.add(i);
                        }
                    }
                } else {
                    posizioniRigaDue.add(i);
                }
            }
        }


        for (int i = 0; i < NUM_COLONNE; i++) {
            if (!posizioniRigaUno.contains(i) && !posizioniRigaDue.contains(i)) {
                posizioniRigaTre.add(i);
            }
            if (posizioniRigaUno.contains(i) && posizioniRigaDue.contains(i)) {
                continue;
            } else if (!numeriDisponibiliRigaTre.contains(i)) {
                numeriDisponibiliRigaTre.add(i);
            }

        }

        System.out.println(numeriDisponibiliRigaTre);
        System.out.println(posizioniRigaTre);
        while (posizioniRigaTre.size() < MAX_NUM_PER_RIGA) {
            int num = random.nextInt(NUM_COLONNE);
            System.out.println("Ho estratto il " + num);

            if (numeriDisponibiliRigaTre.contains(num) && !posizioniRigaTre.contains(num)
                    && controlloQuantitaNumeri(num)) {
                int index = numeriDisponibiliRigaTre.indexOf(num);
                System.out.println("l'indice di " + num + " è " + index);
                if (index >= 0) {
                    posizioniRigaTre.add(num);
                    System.out.println(posizioniRigaTre);
                    index = numeriDisponibiliRigaTre.indexOf(num);
                    numeriDisponibiliRigaTre.remove(index);
                    System.out.println(numeriDisponibiliRigaTre);
                }

            }

        }
        System.out.println("Riga 3 fatta");
        System.out.println(posizioniRigaTre.toString());
        System.out.println("Ho fatto");
        Collections.sort(posizioniRigaTre);
        posizioni.add(posizioniRigaTre);
        System.out.println();
        for(ArrayList<Integer> posizione : posizioni) {
            System.out.println(posizione);
        }
        System.out.println();




        return posizioni;
    }

    private int[][] riempiPosizioni() {
        int[][] cartella = new int[NUM_RIGHE][NUM_COLONNE];
        Random random = new Random();
        ArrayList<ArrayList<Integer>> posizioni = this.generaPosizioni();
        int num;
        for (int i = 0; i < NUM_COLONNE; i++) {
            if (i == 0) {
                for (int j = 0; j < NUM_RIGHE; j++) {
                    num = random.nextInt(9) + 1;
                    System.out.println(num);
                    System.out.println(numeriTombola.get(i));
                    System.out.println(numeriTombola.get(i).contains(num));
                    if (numeriTombola.get(i).contains(num)) {
                        if (posizioni.get(j).contains(i)) {
                            cartella[j][i] = num;
                            int index = numeriTombola.get(i).indexOf(num);
                            numeriTombola.get(i).remove(index);
                            System.out.println("ho inserito " + num);
                        }
                        System.out.println("Ho saltato il passaggio perchè non c'è la posizione");

                    } else {
                        j--;
                    }
                }
            } else if (i == NUM_COLONNE - 1) {
                for (int j = 0; j < NUM_RIGHE; j++) {
                    num = (random.nextInt(11)) + 10 * i;

                    System.out.println(num);
                    System.out.println(numeriTombola.get(i));
                    System.out.println(numeriTombola.get(i).contains(num));
                    if (numeriTombola.get(i).contains(num)) {
                        if (posizioni.get(j).contains(i)) {
                            cartella[j][i] = num;
                            int index = numeriTombola.get(i).indexOf(num);
                            numeriTombola.get(i).remove(index);

                            System.out.println("ho inserito " + num);
                        }
                        System.out.println("Ho saltato il passaggio perchè non c'è la posizione");

                    }else {
                        j--;
                        System.out.println("Non ho trovato " + num);
                    }
                }
            } else {
                for (int j = 0; j < NUM_RIGHE; j++) {
                    num = random.nextInt(10) + 10 * i;
                    System.out.println(num);
                    System.out.println(numeriTombola.get(i));
                    System.out.println(numeriTombola.get(i).contains(num));
                    if (numeriTombola.get(i).contains(num)) {
                        if (posizioni.get(j).contains(i)) {
                            cartella[j][i] = num;
                            int index = numeriTombola.get(i).indexOf(num);
                            numeriTombola.get(i).remove(index);

                            System.out.println("ho inserito " + num);
                        }
                        System.out.println("Ho saltato il passaggio perchè non c'è la posizione");

                    }else {
                        j--;
                        System.out.println("Non ho trovato " + num);
                    }
                }
            }
        }
        for (int i = 0; i < NUM_RIGHE; i++) {
            for (int j = 0; j < NUM_COLONNE; j++) {
                System.out.print(cartella[i][j] + "\t");

            }
            System.out.println();
        }


        for (int i = 0; i < NUM_RIGHE; i++) {
            for (int j = 0; j < NUM_COLONNE; j++) {
                if (cartella[i][j] != 0) {
                    countArray[count][j]++;
                }
            }
        }
        System.out.println();
        for (int i = 0; i < 6; i++) {
            for (int j = 0; j < NUM_COLONNE; j++) {
                System.out.print(countArray[i][j] + "\t");

            }
            System.out.println();
        }
        count++;

        for (int i = 0; i < NUM_COLONNE; i++) {
            int countZeri = 0;
            for (int j = 0; j < 6; j++) {
                if (countArray[j][i] == 1) {
                    countZeri++;
                }
            }
            conteggioNumeriUno[i] = countZeri;

        }

        System.out.println("conto uno in colonna ");
        for (int i = 0; i < NUM_COLONNE; i++) {
            System.out.print(conteggioNumeriUno[i] + " ");
        }
        System.out.println();
        return cartella;
    }

    private boolean controlloQuantitaNumeri(int colonna) {
        if (colonna == 0) {
            if (numeriTombola.get(colonna).size() > 3) {
                return true;
            }
        } else if (colonna == NUM_COLONNE - 1) {
            if (numeriTombola.get(colonna).size() > 1) {
                return true;
            }
        } else {
            if (numeriTombola.get(colonna).size() > 2) {
                return true;
            }
        }
        return false;
    }


    //questo serve a verificare se c'è un solo zero nell'array colonna della cartella. restituisce false se non posso mettere più numeri
    private boolean verificaZeroInColonna(int[] zeros) {
        int count = 0;
        for (int i : zeros) {
            if (i == 0) {
                count++;
                if (count > 1 || count == 0) {
                    return false;
                }
            }
        }
        return true;

    }

    private void ordinaCartella(int[][] cartella) {
        for (int i = 0; i < NUM_COLONNE; i++) {
            int colonna[] = {cartella[0][i], cartella[1][i], cartella[2][i]};
            if (verificaZeroInColonna(colonna)) {
                int tmp = 0;
                if (cartella[0][i] == 0) {
                    if (cartella[1][i] > cartella[2][i]) {
                        tmp = cartella[1][i];
                        cartella[1][i] = cartella[2][i];
                        cartella[2][i] = tmp;
                    }
                } else if (cartella[1][i] == 0) {
                    if (cartella[0][i] > cartella[2][i]) {
                        tmp = cartella[0][i];
                        cartella[0][i] = cartella[2][i];
                        cartella[2][i] = tmp;
                    }
                } else if (cartella[2][i] == 0) {
                    if (cartella[0][i] > cartella[1][i]) {
                        tmp = cartella[0][i];
                        cartella[0][i] = cartella[1][i];
                        cartella[1][i] = tmp;
                    }
                }
            }
        }
    }


    public String toString(int[][] cartella) {
        String s = "|-------------------------------------------------------------------------------|\n";
        s += "|\t\t\t\t\t\t\t\t\t\t|\n";
        for (int i = 0; i < NUM_RIGHE; i++) {
            for (int j = 0; j < NUM_COLONNE; j++) {
                if (j == 0) {
                    if (cartella[i][j] != 0) {
                        s += "|\t" + cartella[i][j] + "\t";
                    } else {
                        s += "|\t\t";
                    }
                } else if (j == NUM_COLONNE - 1) {
                    if (cartella[i][j] != 0) {
                        s += cartella[i][j] + "\t|" + "\n";
                        s += "|\t\t\t\t\t\t\t\t\t\t|\n";
                    } else {
                        s += "\t|\n";
                        s += "|\t\t\t\t\t\t\t\t\t\t|\n";
                    }

                } else {

                    if (cartella[i][j] != 0) {
                        s += cartella[i][j] + "\t";
                    } else {
                        s += "\t";
                    }
                }
            }
        }
        s += "|\t\t\t\t\t\t\t\t\t\t|\n";
        s += "|-------------------------------------------------------------------------------|";

        return s;

    }

}
