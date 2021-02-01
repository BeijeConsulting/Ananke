package it.beije.ananke.rubrica;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rubrica {

    private static final String PATH = "C:\\Users\\Padawan02\\Desktop\\esercizietti\\rubrica";

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        Scanner inputKeyword = new Scanner(System.in);
        String command;

        //prendo il primo comando che mi porta nel ciclo
        showCommandList();
        command = inputKeyword.nextLine().trim();

        AZIONI: do{

            switch (command){

                case "i":

                    insertContacts();

                    break;

                case "u":

                    modifyContacts();

                    break;

                case "d":

                    deleteContacts();

                    break;

                case "s":

                    ArrayList<String> fields = fieldsForSearch();
                    searchContacts(fields);

                    break;

                case "q":

                    break AZIONI;

                default:

                    break;

            }

            //prendo un nuovo comando
            showCommandList();
            command = inputKeyword.nextLine().trim();

        }while(!command.equals("q"));

        System.out.println("Vuoi esportare qualche contatto da databese su file?" +
                "\n\tyes?" +
                "\n\tno?");
        command = inputKeyword.nextLine().trim();

        if(command.equals("yes"))
            writeOnFile();

    }

    private static void insertContacts() throws SQLException, ClassNotFoundException {


        Scanner inputKeyword = new Scanner(System.in);
        String command = null;

        System.out.println("Come vuoi inserire i contatti?" +
                    "\n\t[1] Importa contatti da file" +
                    "\n\t[2] Inserisci contatti da tastiera");

        command = inputKeyword.nextLine().trim();

        switch (command) {

            case "1":

                insertFromFile();

                break;

            case "2":

                insertFromKeyWords();

                break;

            default:

        }

    }

    private static void insertFromKeyWords() throws SQLException, ClassNotFoundException {

        Scanner inputKeyWord = new Scanner(System.in);
        ArrayList<Contact> rubric = new ArrayList<>();
        String command = null;

        do {

            Contact contact = new Contact();

            System.out.println("\nInserisci i dati del nuovo contatto.");
            System.out.println("\nNome: ");
            contact.setName(inputKeyWord.nextLine().trim());
            System.out.println("\nCognome: ");
            contact.setSurname(inputKeyWord.nextLine().trim());
            System.out.println("\nTelefono: ");
            contact.setTelephone(inputKeyWord.nextLine().trim());
            System.out.println("\nE-mail: ");
            contact.setEmail(inputKeyWord.nextLine().trim());

            if(contact.getName().length() == 0  &&
                    contact.getSurname().length() == 0  &&
                    contact.getTelephone().length() == 0  &&
                    contact.getEmail().length() == 0){

                System.out.println("Attenzione! Almeno un campo del contatto deve essere non vuoto");

            }
            else
                rubric.add(contact);

            System.out.println("Vuoi continuare a inserire contatti?" +
                    "\n\tyes?" +
                    "\n\tno?");
            command = inputKeyWord.nextLine().trim();

        }while(!command.equals("no"));

        System.out.println("Questi sono i contatti che hai aggiunto:\n");
        printContacts(rubric);
        System.out.println("\nVuoi aggiungere questi contatti al database?" +
                "\n\tyes?" +
                "\n\tno?");

        //per ora i contatti li aggiungo qualsiasi sia la sua risposta. poi magari appunto dò la possibilità
        command = inputKeyWord.nextLine().trim();

        DataBaseContact.openConnection();

        DataBaseContact.insert(rubric);

        DataBaseContact.closeConnection();

    }

    private static void insertFromFile() {

        Scanner inputTastiera = new Scanner(System.in);
        String path;

        File directory = new File(PATH);
        File[] files = directory.listFiles();
        int i=0;

        if(files == null) {
            System.out.println("C'è stato un problema nel caricare la directory");
        }
        else{
            if(files.length > 0) {
                //esiste già un file dal quale leggere

                System.out.println("Quale file vuoi leggere?\n");

                for (File file : files) {
                    System.out.println("[" + i + "]\t" + file.getName());
                    i++;
                }

                //faccio scegliere il file da leggere
                int numeroFile = Integer.parseInt(inputTastiera.nextLine().trim());
                String nomeFile = files[numeroFile].getName();

                //capisco se è un file xml o csv
                String nome = nomeFile.split("\\.")[0];
                String estensione = (nomeFile.split("\\."))[1];

                path = PATH + "\\" + nomeFile;

                if (estensione.equals("xml")) {
                    try {
                        readContactFromXml(path);
                    } catch (Exception e) {
                        System.out.println("C'è stato un problema nella lettura del file.xml");
                        e.printStackTrace();
                    }
                } else {
                    if (estensione.equals("csv")) {
                        try {
                            readContactFromCsv(path);
                        } catch (SQLException | ClassNotFoundException throwables) {
                            System.out.println("C'è stato un problema nella lettura del file.csv");
                            throwables.printStackTrace();
                        }
                    }
                    else {
                        System.out.println("Mi dispiace ma non riesco a leggere un file di tale estensione");
                    }
                }
            }
            else{
                //non esistono file presenti nella directory
                System.out.println("Non sono ancora presenti file nella directory." +
                        "\nUna volta completate le operazioni potrai crearne uno\n");

                //non essendoci un file da cui leggere non faccio nulla a rubrica
                //che è gà stata inizializzata in linea
            }
        }

    }

    private static void readContactFromCsv(String path) throws SQLException, ClassNotFoundException {

        ArrayList<Contact> rubric = CsvFile.readFromCsv(path);

        System.out.println("Ora aggiungo questi contatti al database");
        printContacts(rubric);

        DataBaseContact.openConnection();

        DataBaseContact.insert(rubric);

        DataBaseContact.closeConnection();

    }

    private static void readContactFromXml(String path) throws ParserConfigurationException, IOException, SAXException, SQLException, ClassNotFoundException {

        ArrayList<Contact> rubric = XmlFile.readFromFile(path);

        System.out.println("Ora aggiungo questi contatti al database");
        printContacts(rubric);

        DataBaseContact.openConnection();

        DataBaseContact.insert(rubric);

        DataBaseContact.closeConnection();

    }

    private static void deleteContacts() throws SQLException, ClassNotFoundException {

        Scanner inputKeyword = new Scanner(System.in);
        String command = null;
        ArrayList<String> values = null;
        ArrayList<String> fields = null;

        do {

            fields = fieldsForSearch();
            values = searchContacts(fields);

            System.out.println("Sei sicuro di voler eliminare i precedenti contatti?" +
                    "\n\tyes?" +
                    "\n\tno? ");
            command = inputKeyword.nextLine().trim();

            if(command.equals("no")) {
                //l'utente vuole cambiare i field di ricerca
                fields.clear();
                values.clear();
            }

        } while(!command.equals("yes"));

        //finisco che ho la lista di campi e la lista dei rispettivi valori

        DataBaseContact.openConnection();

        DataBaseContact.deleteWhere(fields, values);

        DataBaseContact.closeConnection();

    }

    private static void modifyContacts() throws SQLException, ClassNotFoundException {

        Scanner inputKeyword = new Scanner(System.in);
        String command = null;
        ArrayList<String> fields = null;
        ArrayList<String> values = null;
        ArrayList<String> modifyFields = new ArrayList<>();
        ArrayList<String> modifyValues = new ArrayList<>();

        do {

            fields = fieldsForSearch();
            values = searchContacts(fields);

            System.out.println("Sei sicuro di voler modificare i precedenti contatti? ");
            command = inputKeyword.nextLine().trim();

            if(command.equals("no")) {
                //l'utente vuole cambiare i field di ricerca
                fields.clear();
                values.clear();
            }

        } while(!command.equals("yes"));

        //ho trovato dei contatti tramite certi campi
        //ora devo chiedere all'utente quali campi e come
        //vuole modificare

        do {

            String input = null;

            System.out.println("Tramite quale campo vuoi cercare?" +
                    "\n\t -n: name" +
                    "\n\t -s: surname" +
                    "\n\t -t: telephone" +
                    "\n\t -e: email" +
                    "\nScrivi il comando con - seguito dalla lettera");

            input = inputKeyword.nextLine().trim();

            switch (input){
                case "-n":
                    input = "name";
                    break;
                case "-s":
                    input = "surname";
                    break;
                case "-t":
                    input = "telephone";
                    break;
                case "-e":
                    input = "email";
                    break;
                default:
            }

            //aggiungo solo se il campo non l'ho già aggiunto
            if (!modifyFields.contains(input))
                modifyFields.add(input);
            else {
                System.out.println("\nHai già aggiunto questo campo per la ricerca");
            }

            System.out.println("Inserisci la modifica del campo");
            input = inputKeyword.nextLine().trim();
            modifyValues.add(input);

            if(fields.size() != 4) {
                System.out.println("Vuoi modificare altri campi?");
                command = inputKeyword.nextLine().trim();
            }

        }while(!command.equals("no"));

        //finisco che ho la lista di campi da modificare e la lista dei rispettivi valori

        DataBaseContact.openConnection();

        DataBaseContact.updateWhere(fields,values, modifyFields, modifyValues);

        DataBaseContact.closeConnection();

    }

    //funzione per sapere tramite quali campi cercare dei determinati dati nel db
    private static ArrayList<String> fieldsForSearch(){

        Scanner inputKeyWord = new Scanner(System.in);
        String command = null;
        ArrayList<String> fields = new ArrayList<>();

        System.out.println("Quanti campi vuoi specificare per trovare i contatti che ti interessano?" +
                "\n\t[1] -un solo campo" +
                "\n\t[2] -due campi solamente" +
                "\n\t[3] -tre campi" +
                "\n\t[4] -tutti i campi" +
                "\nDigita solamente il numero.");

        command = inputKeyWord.nextLine().trim();

        int numberOfFields = Integer.parseInt(command);
        if(numberOfFields > 4) {
            System.out.println("Nel db sono presenti solo 4 campi");
            numberOfFields = 4;
        }

        //riempiamo l'arraylist che poi farò tornare che mi serve per la delete e per la modify
        //con i campi che voglio specificare
        for (int i = 0; i < numberOfFields; i++) {

            System.out.println("Campo numero " + (i+1) + ": ");
            String value = null;

            System.out.println("Tramite quale campo vuoi cercare?" +
                    "\n\t -n: name" +
                    "\n\t -s: surname" +
                    "\n\t -t: telephone" +
                    "\n\t -e: email" +
                    "\nScrivi il comando con - seguito dalla lettera");

            value = inputKeyWord.nextLine().trim();

            switch (value){
                case "-n":
                    value = "name";
                    break;
                case "-s":
                    value = "surname";
                    break;
                case "-t":
                    value = "telephone";
                    break;
                case "-e":
                    value = "email";
                    break;
                default:
            }

            //aggiungo solo se il campo non l'ho già aggiunto
            if (!fields.contains(value))
                fields.add(value);
            else {
                System.out.println("\nHai già aggiunto questo campo per la ricerca");
                i--;
            }

        }

        return fields;

    }

    private static ArrayList<String> searchContacts(ArrayList<String> fields) throws SQLException, ClassNotFoundException {

        Scanner inputKeyWord = new Scanner(System.in);
        ArrayList<String> values = new ArrayList<>();

        for (int i = 0; i < fields.size(); i++) {

            //Chiedo all'utente di inserire il valore specfico del primo campo
            System.out.println(fields.get(i) + ":\t");
            String value = inputKeyWord.nextLine().trim();
            values.add(value);
        }

        DataBaseContact.openConnection();

        ArrayList<Contact> rubric = DataBaseContact.selectWhere(fields, values);

        printContacts(rubric);

        DataBaseContact.closeConnection();

        return values;
    }

    private static void writeOnFile() throws SQLException, ClassNotFoundException {

        ArrayList<Contact> rubric = new ArrayList<>();

        System.out.println("Vuoi esportare tutti i contatti su un file?" +
                "\n\tyes?" +
                "\n\tno?");
        String command = new Scanner(System.in).nextLine().trim();

        if(command.equals("no")) {
            //cerco i contatti e creo un arrayList che poi passo alle altre funzioni
            ArrayList<String> fields = fieldsForSearch();
            ArrayList<String> values = searchContacts(fields);

            DataBaseContact.openConnection();

            rubric = DataBaseContact.selectWhere(fields, values);

            DataBaseContact.closeConnection();
        }
        else{
            DataBaseContact.openConnection();

            rubric = DataBaseContact.select();

            DataBaseContact.closeConnection();
        }

        Scanner inputTastiera = new Scanner(System.in);
        String path;

        File directory = new File(PATH);
        File[] files = directory.listFiles();
        int i=0;

        System.out.println("Su quale file vuoi salvare questi contatti?" +
                "\nDigita il numero corrispondente al file che vuoi, o un numero non presente per creare un nuovo file.");

        //listo tutti i file presenti nella directory

        String nomeFile = null;

        if(files != null) {

            for (File file : files) {
                System.out.println("[" + i + "]\t" + file.getName());
                i++;
            }

            //faccio scegliere il file su cui salvare
            int numeroFile = Integer.parseInt(inputTastiera.nextLine());

            if(numeroFile < files.length)
                nomeFile = files[numeroFile].getName();
            else {
                System.out.println("Digita il nome del nuovo file con l'estensione csv/xml.");
                nomeFile = inputTastiera.nextLine();
            }

        }

        //capisco se è un file xml o csv

        String nome = null;
        String estensione = null;

        if(nomeFile != null) {
            nome = nomeFile.split("\\.")[0];
            estensione = (nomeFile.split("\\."))[1];
        }

        path = PATH + "\\" + nomeFile;

        if(estensione.equals("xml")) {
            try {
                writeOnFileXml(path, rubric);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            if (estensione.equals("csv"))
                writeOnFileCsv(path, rubric);
        }

    }

    private static void writeOnFileCsv(String path, ArrayList<Contact> rubric) {

        CsvFile.writeOnCsv(path, rubric);

    }

    private static void writeOnFileXml(String path, ArrayList<Contact> rubric) throws ParserConfigurationException, TransformerException {

        XmlFile.writeOnFile(path, rubric);

    }

    private static void printContacts(ArrayList<Contact> rubric) {
        for (Contact contact : rubric) {
            System.out.println("[" + (int) (rubric.indexOf(contact) + 1) + "]\t" + contact.toString());
        }
    }

    private static void showCommandList() {
        System.out.println("\nEcco cosa puoi fare:\n" +
                "\t- i : inserisci un nuovo contatto;\n" +
                "\t- u : aggiorna un contatto nella rubrica;\n" +
                "\t- d : cancella un contatto esistente;\n" +
                "\t- s : cerca e stampa contatti esistente;\n" +
                "\t- q : salva ed esci.\n");
    }

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////METODI IN DISUSO////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

    static private ArrayList<Contact> rubrica = new ArrayList<>();

    private static void inserisciContatto(){

        Scanner inputTastiera = new Scanner(System.in);
        Contact contatto = new Contact();

        System.out.println("\nInserisci i dati del nuovo contatto.");
        System.out.println("\nNome: ");
        contatto.setName(inputTastiera.nextLine().trim());
        System.out.println("\nCognome: ");
        contatto.setSurname(inputTastiera.nextLine().trim());
        System.out.println("\nTelefono: ");
        contatto.setTelephone(inputTastiera.nextLine().trim());
        System.out.println("\nE-mail: ");
        contatto.setEmail(inputTastiera.nextLine().trim());

        if(contatto.getName().length() == 0  &&
            contatto.getSurname().length() == 0  &&
            contatto.getTelephone().length() == 0  &&
            contatto.getEmail().length() == 0){

            System.out.println("Attenzione! Almeno un campo del contatto deve essere non vuoto");

        }
        else {

            boolean presente = false;

            //controllo di non avere già il contatto
            if (rubrica.size() > 0) {
                for (Contact contact : rubrica) {
                    if (contact.equals(contatto)) {
                        presente = true;
                        break;
                    }
                }
            }

            if (!presente) {
                //aggiungo il contatto al mio arrayList
                rubrica.add(contatto);

                System.out.println("\nHai aggiunto il seguente contatto alla rubrica:\t" + contatto.toString());
            }
            else
                System.out.println("Attenzione! Il contatto era già presente in rubrica");
        }

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
        String vecchioCampo = null;

        switch (comando){
            case "n":
                System.out.println("Nome: ");
                vecchioCampo = rubrica.get(index).getName();
                rubrica.get(index).setName(inputTastiera.nextLine());
                break;

            case "c":
                System.out.println("Cognome: ");
                vecchioCampo = rubrica.get(index).getSurname();
                rubrica.get(index).setSurname(inputTastiera.nextLine());
                break;

            case "t":
                System.out.println("Telefono: ");
                vecchioCampo = rubrica.get(index).getTelephone();
                rubrica.get(index).setTelephone(inputTastiera.nextLine());
                break;

            case "e":
                System.out.println("Email: ");
                vecchioCampo = rubrica.get(index).getEmail();
                rubrica.get(index).setEmail(inputTastiera.nextLine());
                break;

            default:


        }

        if(rubrica.get(index).getName().length() == 0  &&
                rubrica.get(index).getSurname().length() == 0  &&
                rubrica.get(index).getTelephone().length() == 0  &&
                rubrica.get(index).getEmail().length() == 0){

            System.out.println("Attenzione! Almeno un campo del contatto deve essere non vuoto");

            //e rimetto a posto il vecchio valore del campo
            switch (comando){
                case "n":
                    rubrica.get(index).setName(vecchioCampo);
                    break;

                case "c":
                    rubrica.get(index).setSurname(vecchioCampo);
                    break;

                case "t":
                    rubrica.get(index).setTelephone(vecchioCampo);
                    break;

                case "e":
                    rubrica.get(index).setEmail(vecchioCampo);
                    break;

                default:

            }
        }
    }

    private static void cancellaContatto() {

        rubrica.remove(cercaContatto());

    }

    private static void cercaContatti(){

        List<Integer> indici = new ArrayList<>();

        Scanner inputTastiera = new Scanner(System.in);

        System.out.println("\nPuoi cercare un contatto specificando solo alcuni parametri:\n" +
                "\t- n : cercare un contatto specificando nome;\n" +
                "\t- c : cercare un contatto specificando cognome;\\n\"" +
                "\t- t : cercare un contatto specificando il numero telefonico;\n" +
                "\t- e : cercare un contatto specificando l'email;\n");

        String comando = inputTastiera.nextLine().trim();

        //cerco il contatto in modi diversi
        switch (comando){

            case "n":

                indici = cercaContattiNome();

                break;

            case "c":

                indici = cercaContattiCognome();

                break;

            case "t":

                indici = cercaContattiTelefono();

                break;

            case "e":

                indici = cercaContattiEmail();

                break;

            default:


        }

        if(indici.size() > 0) {
            System.out.println("Ecco i contatti che cercavi:\t");
            for (Integer indice: indici) {
                System.out.println("\n\t[" + indice + "]\t" + rubrica.get(indice).toString() );
            }
        }
        else
            System.out.println("Mi dispiace ma il contatto che cercavi non è in rubrica");

    }

    private static ArrayList<Integer> cercaContattiNome(){

        ArrayList<Integer> contattiTrovati = new ArrayList<>();

        Scanner inputTastiera = new Scanner(System.in);

        System.out.println("Nome contatto: ");
        String nome = inputTastiera.nextLine().trim();

        for (Contact contact : rubrica) {

            if(contact.getName().equals(nome))
                contattiTrovati.add(rubrica.indexOf(contact));

        }

        return contattiTrovati;

    }

    private static ArrayList<Integer> cercaContattiCognome(){

        ArrayList<Integer> contattiTrovati = new ArrayList<>();

        Scanner inputTastiera = new Scanner(System.in);

        System.out.println("Cognome contatto: ");
        String cognome = inputTastiera.nextLine().trim();

        for (Contact contact : rubrica) {

            if(contact.getSurname().equals(cognome))
                contattiTrovati.add(rubrica.indexOf(contact));

        }

        return contattiTrovati;
    }

    private static ArrayList<Integer> cercaContattiTelefono(){

        ArrayList<Integer> contattiTrovati = new ArrayList<>();

        Scanner inputTastiera = new Scanner(System.in);

        System.out.println("Telefono contatto: ");
        String telefono = inputTastiera.nextLine().trim();

        for (Contact contact : rubrica) {

            if(contact.getTelephone().equals(telefono))
                contattiTrovati.add(rubrica.indexOf(contact));

        }

        return contattiTrovati;

    }

    private static ArrayList<Integer> cercaContattiEmail(){

        ArrayList<Integer> contattiTrovati = new ArrayList<>();

        Scanner inputTastiera = new Scanner(System.in);

        System.out.println("Email contatto: ");
        String email = inputTastiera.nextLine().trim();

        for (Contact contact : rubrica) {

            if(contact.getEmail().equals(email))
                contattiTrovati.add(rubrica.indexOf(contact));

        }

        return contattiTrovati;
    }

    //tecnicamente dovrebbero poterci essere dei duplicati
    //per nome&cognome, telefono, email
    private static int cercaContatto(){

        Scanner inputTastiera = new Scanner(System.in);

        System.out.println("\nPuoi cercare un contatto specificando solo alcuni parametri:\n" +
                "\t- n&c : cercare un contatto specificando nome e cognome;\n" +
                "\t- t : cercare un contatto specificando il numero telefonico;\n" +
                "\t- e : cercare un contatto specificando l'email;\n");

        String comando = inputTastiera.nextLine().trim();

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

        if(index >= 0)
            System.out.println("Ecco il contatto che cercavi:\t" + rubrica.get(index).toString());
        else
            System.out.println("Mi dispiace ma il contatto che cercavi non è in rubrica");

        return index;

    }

    private static int cercaContattoNomeCognome(){

        Scanner inputTastiera = new Scanner(System.in);

        System.out.println("Nome contatto: ");
        String nome = inputTastiera.nextLine().trim();
        System.out.println("Cognome contatto: ");
        String cognome = inputTastiera.nextLine().trim();

        for (Contact contact : rubrica) {

            if(contact.getName().equals(nome) && contact.getSurname().equals(cognome))
                return rubrica.indexOf(contact);

        }

        return -1;

    }

    private static int cercaContattoTelefono(){

        Scanner inputTastiera = new Scanner(System.in);

        System.out.println("Telefono contatto: ");
        String telefono = inputTastiera.nextLine().trim();

        for (Contact contact : rubrica) {

            if(contact.getTelephone().equals(telefono))
                return rubrica.indexOf(contact);

        }

        return -1;

    }

    private static int cercaContattoEmail(){

        Scanner inputTastiera = new Scanner(System.in);

        System.out.println("Nome contatto: ");
        String email = inputTastiera.nextLine().trim();

        for (Contact contact : rubrica) {

            if(contact.getEmail().equals(email))
                return rubrica.indexOf(contact);

        }

        return -1;

    }

    private static boolean chiaveDuplicata(Contact contact, String chiave){

        switch (chiave){

            case "n&c":

                for (Contact contactConfronto : rubrica) {

                    //faccio il controllo per i duplicati, ma questo controllo è CASE SESITIVE
                    //per fare CASE UNSESITIVE basta usare equalsIgnoreCase()
                    if(contact.getName().equals(contactConfronto.getName()) &&
                            contact.getSurname().equals(contactConfronto.getSurname()))
                        return true;

                }

                break;

            case "t":

                for (Contact contactConfronto : rubrica) {

                    if(contact.getTelephone().equals(contactConfronto.getTelephone()))
                        return true;

                }

                break;

            case "e":

                for (Contact contactConfronto : rubrica) {

                    if(contact.getEmail().equals(contactConfronto.getEmail()))
                        return true;

                }

                break;

            default:

        }

        //se arrivo alla fine, vuol dire che non ho trovato duplicati
        return false;

    }

}
