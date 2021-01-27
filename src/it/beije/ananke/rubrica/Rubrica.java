package it.beije.ananke.rubrica;

//salvare in un file rubrica.txt
//un elenco di contatti.
//devo modificare il file, non sovrascrivere ogni volta che lo apro.

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rubrica {

    private static List<Contatto> rubrica = new ArrayList<>();
    private static final String PATH = "C:\\Users\\Padawan02\\Desktop";
    private static final String NOME_FILE = "rubrica.txt";

    public static void main(String[] args) {

        Scanner inputTastiera = new Scanner(System.in);
        String path = PATH + "/" + NOME_FILE;
        String comando;

        //apro il file della rubrica. se non è presente dovrebbe crearlo
        //creando un oggetto fileWriter

        leggiContatti(path);

        //lo scanner con il file lo creo solo dopo aver aperto e creato il filewriter

        //prendo il primo comando che mi porta nel ciclo

        stampaListaComandi();
        comando = inputTastiera.nextLine();

        AZIONI: do{

            switch (comando){

                case "i":

                    inserisciContatto();

                    break;

                case "u":

                    aggiornaContatto();

                    break;

                case "d":

                    cancellaContatto();

                    break;

                case "f":

                    cercaContatto();

                    break;

                case "p":

                    stampaContatti();

                    break;

                case "q":

                    break AZIONI;

                default:

                    break;

            }

            //prendo un nuovo comando
            stampaListaComandi();
            comando = inputTastiera.nextLine();

        }while(!comando.equals("q"));

        scriviSuFile(path);

    }

    private static void stampaListaComandi() {
        System.out.println("Ciao! Ecco cosa puoi fare:\n" +
                "\t- i : inserisci un nuovo contatto;\n" +
                "\t- u : aggiorna un contatto nella rubrica;\n" +
                "\t- d : cancella un contatto esistente;\n" +
                "\t- f : cerca un contatto esistente;\n" +
                "\t- p : stampa dei contatti della rubrica;\n" +
                "\t- q : salva ed esci.\n");
    }

    //legge il file csv e tira fuori i contatti
    private static void leggiContatti(String path){

        rubrica = new ArrayList<>();

        BufferedReader reader = null;

        try {
            String info;
            reader = new BufferedReader(new FileReader(path));
            while((info = reader.readLine()) != null){

                //ho letto qualcosa di nuovo e non vuoto
                //devo splittare la stringa e estrapolare
                //le informazioni del contatto.
                String[] infoContatto = info.split(";");
                Contatto contatto = new Contatto();

                contatto.setNome(infoContatto[0]);
                contatto.setCognome(infoContatto[1]);
                contatto.setTelefono(infoContatto[2]);
                contatto.setEmail(infoContatto[3]);

                rubrica.add(contatto);
                System.out.println(contatto.toString());

            }

        } catch (FileNotFoundException e) {
            System.out.println("Il file non era presente. Ora ne creo uno nuovo");

            FileWriter writer = null;

            try {
                writer = new FileWriter(path);
                System.out.println("Ho creato correttamente il file");
            } catch (IOException ioException) {
                ioException.printStackTrace();
            }
            finally {
                try {
                    if(writer != null) {
                        writer.flush();
                        writer.close();
                    }
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            try {
                if(reader != null) {
                    reader.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    private static void scriviSuFile(String path) {

        FileWriter writer = null;

        try {

            writer = new FileWriter(path);

            //stampo tutta la rubrica
            for (Contatto contatto: rubrica) {

                writer.write(contatto.toString());

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        finally {

                try {
                    if(writer != null) {
                        writer.flush();
                        writer.close();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

        }

    }

    private static void inserisciContatto(){

        Scanner inputTastiera = new Scanner(System.in);
        Contatto contatto = new Contatto();

        System.out.println("Inserisci i dati del nuovo contatto.");
        System.out.println("\nNome: ");
        contatto.setNome(inputTastiera.next().trim());
        System.out.println("\nCognome: ");
        contatto.setCognome(inputTastiera.next().trim());
        System.out.println("\nTelefono: ");
        contatto.setTelefono(inputTastiera.next().trim());
        System.out.println("\nE-mail: ");
        contatto.setEmail(inputTastiera.next().trim());

        boolean presente = false;

        //controllo di non avere già il contatto
        if(rubrica.size() > 0) {
            for (Contatto contact : rubrica) {
                if (contact.equals(contatto)) {
                    presente = true;
                    break;
                }
            }
        }

        if(!presente)
            //aggiungo il contatto al mio arrayList
            rubrica.add(contatto);

        System.out.println(contatto.toString());

    }

    private static void aggiornaContatto() {

        int index = cercaContatto();

        Scanner inputTastiera = new Scanner(System.in);

        System.out.println("Quale campo vuoi modificare?" +
                "\t- n: nome" +
                "\t- c: cognome" +
                "\t- t: telefono" +
                "\t- e: email");

        String comando = inputTastiera.nextLine();

        switch (comando){
            case "n":
                System.out.println("Nome: ");
                rubrica.get(index).setNome(inputTastiera.nextLine());
                break;

            case "c":
                System.out.println("Cognome: ");
                rubrica.get(index).setCognome(inputTastiera.nextLine());
                break;

            case "t":
                System.out.println("Telefono: ");
                rubrica.get(index).setTelefono(inputTastiera.nextLine());
                break;

            case "e":
                System.out.println("Email: ");
                rubrica.get(index).setEmail(inputTastiera.nextLine());
                break;

            default:

        }
    }

    private static void cancellaContatto() {

        rubrica.remove(cercaContatto());

    }

    private static int cercaContatto(){

        Scanner inputTastiera = new Scanner(System.in);

        System.out.println("Puoi cercare un contatto specificando solo alcuni parametri:\n" +
                "\t- n&c : cercare un contatto specificando nome e cognome;\n" +
                "\t- t : cercare un contatto specificando il numero telefonico;\n" +
                "\t- e : cercare un contatto specificando l'email;\n");

        String comando = inputTastiera.nextLine();

        int index;

        //cerco il contatto in modi diversi
        switch (comando){

            case "n&c":

                index = cercaContattoNomeCognome();

                break;

            case "t":

                index = cercaContattoTelefono();

                break;

            case "e":

                index = cercaContattoEmail();

                break;

            default:

                index = -1;

        }

        if(index > 0)
            System.out.println("Ecco il contatto che cercavi:\n\t" + rubrica.get(index).toString());
        else
            System.out.println("Mi dispiace ma il contatto che cercavi non è in rubrica");

        return index;

    }

    private static int cercaContattoNomeCognome(){

        Scanner inputTastiera = new Scanner(System.in);

        System.out.println("Nome contatto: ");
        String nome = inputTastiera.nextLine();
        System.out.println("Cognome contatto: ");
        String cognome = inputTastiera.nextLine();

        for (Contatto contatto: rubrica) {

            if(contatto.getNome().equals(nome) && contatto.getCognome().equals(cognome))
                return rubrica.indexOf(contatto);

        }

        return -1;

    }

    private static int cercaContattoTelefono(){

        Scanner inputTastiera = new Scanner(System.in);

        System.out.println("Telefono contatto: ");
        String telefono = inputTastiera.nextLine();

        for (Contatto contatto: rubrica) {

            if(contatto.getTelefono().equals(telefono))
                return rubrica.indexOf(contatto);

        }

        return -1;

    }

    private static int cercaContattoEmail(){

        Scanner inputTastiera = new Scanner(System.in);

        System.out.println("Nome contatto: ");
        String email = inputTastiera.nextLine();

        for (Contatto contatto: rubrica) {

            if(contatto.getEmail().equals(email))
                return rubrica.indexOf(contatto);

        }

        return -1;

    }

    private static void stampaContatti() {
        for (Contatto contatto: rubrica) {
            System.out.println("[" + (int) (rubrica.indexOf(contatto) + 1) + "]\t" + contatto.toString());
        }
    }

}
