package it.beije.ananke.rubrica;

//salvare in un file rubrica.txt
//un elenco di contatti.
//devo modificare il file, non sovrascrivere ogni volta che lo apro.

import java.util.Scanner;

public class Rubrica {

    public static void main(String[] args) {

        Scanner inputTastiera = new Scanner(System.in);
        String comando;

        //prendo il primo comando che mi porta nel ciclo
        stampaListaComandi();
        comando = inputTastiera.nextLine();

        AZIONI: do{

            switch (comando){
                case "i":

                    //inserisciContatto();

                    break;
                case "d":

                    //cancellaContatto();

                    break;

                case "f":

                    //cercaContatto();

                    break;

                case "p":

                    //stampaContatti();

                    break;

                case "q":

                    break AZIONI;

                default:

                    break;

            }

            System.out.println(comando);
            //prendo un nuovo comando
            stampaListaComandi();
            comando = inputTastiera.nextLine();
        }while(!comando.equals("q"));


    }

    private static void stampaListaComandi() {
        System.out.println("Ciao! Ecco cosa puoi fare:\n" +
                "\t- i : inserisci un nuovo contatto;\n" +
                "\t- d : cancella un contatto esistente;\n" +
                "\t- f : cerca un contatto esistente;\n" +
                "\t- p : stampa dei contatti della rubrica;\n" +
                "\t- q : salva ed esci.\n");
    }
}
