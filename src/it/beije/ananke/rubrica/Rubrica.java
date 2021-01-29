package it.beije.ananke.rubrica;

//salvare in un file rubrica.txt
//un elenco di contatti.
//devo modificare il file, non sovrascrivere ogni volta che lo apro.

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Rubrica {

    private static List<Contatto> rubrica = new ArrayList<>();
    private static final String PATH = "C:\\Users\\Padawan02\\Desktop\\esercizietti\\rubrica";

    public static void main(String[] args) {

        Scanner inputTastiera = new Scanner(System.in);
        String path;
        String comando;

        File directory = new File(PATH);
        File[] files = directory.listFiles();
        int i=0;

        if(files == null) {
            System.out.println("C'è stato un problema. Riavvia l'applicatico");
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
                        leggiContattiXml(path);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                } else {
                    if (estensione.equals("csv"))
                        leggiContattiCsv(path);
                    else {
                        System.out.println("Mi dispiace ma non riesco a leggere un file di tale estensione");
                        return;
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

        //prendo il primo comando che mi porta nel ciclo
        //TODO: lo posso togliere tecnicamente se sposto l'altro in cima al do
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

                    cercaContatti();

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

        System.out.println("Su quale file vuoi salvare le modifiche alla rubrica?" +
                "\nDigita il numero corrispondente al file che vuoi, o un numero non presente per creare un nuovo file.");

        //listo tutti i file presenti nella directory

        String nomeFile = null;

        if(files != null) {

            i = 0;

            for (File file : files) {
                System.out.println("[" + i + "]\t" + file.getName());
                i++;
            }

            //faccio scegliere il file su cui salvare
            int numeroFile = Integer.parseInt(inputTastiera.nextLine());

            if(numeroFile < files.length)
                nomeFile = files[numeroFile].getName();
            else {
                System.out.println("Digita il nome del file con l'estensione csv/xml.");
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
                scriviSuFileXml(path);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            if (estensione.equals("csv"))
                scriviSuFileCsv(path);
        }

    }

    private static void stampaListaComandi() {
        System.out.println("\nEcco cosa puoi fare:\n" +
                "\t- i : inserisci un nuovo contatto;\n" +
                "\t- u : aggiorna un contatto nella rubrica;\n" +
                "\t- d : cancella un contatto esistente;\n" +
                "\t- f : cerca un contatto esistente;\n" +
                "\t- p : stampa dei contatti della rubrica;\n" +
                "\t- q : salva ed esci.\n");
    }

    //legge il file csv e tira fuori i contatti
    private static void leggiContattiCsv(String path){

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

                contatto.setNome(infoContatto[0].trim());
                contatto.setCognome(infoContatto[1].trim());
                contatto.setTelefono(infoContatto[2].trim());
                contatto.setEmail(infoContatto[3].trim());

                rubrica.add(contatto);
                //System.out.println(contatto.toString());

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

        stampaContatti();

    }

    private static void leggiContattiXml(String path) throws ParserConfigurationException, IOException, SAXException {

        rubrica = new ArrayList<>();

        //servono per leggere e scrivere file xml.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //creo il documento virtuale
        Document document = builder.parse(path);

        Element docElement = document.getDocumentElement();
        NodeList elementiContatto = docElement.getElementsByTagName("contatto");

        for (int i = 0; i < elementiContatto.getLength(); i++) {

            Contatto contatto = new Contatto();
            Element c = (Element) elementiContatto.item(i);

            NodeList valori = c.getChildNodes();
            //System.out.println(valori.getLength());
            for (int j = 0; j < valori.getLength(); j++) {
                Node n = valori.item(j);
                if (n instanceof Element) {
                    Element valore = (Element) n;
                    //System.out.println(valore.getTagName() + " : " + valore.getTextContent());
                    switch (valore.getTagName()) {
                        case "nome":
                            contatto.setNome(valore.getTextContent().trim());
                            break;
                        case "cognome":
                            contatto.setCognome(valore.getTextContent().trim());
                            break;
                        case "telefono":
                            contatto.setTelefono(valore.getTextContent().trim());
                            break;
                        case "email":
                            contatto.setEmail(valore.getTextContent().trim());
                            break;

                        default:
                            System.out.println("elemento in contatto non riconosciuto");
                            break;
                    }
                }
            }
            rubrica.add(contatto);
        }

        stampaContatti();

    }

    private static void scriviSuFileCsv(String path) {

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

    private static void scriviSuFileXml(String path) throws ParserConfigurationException, TransformerException {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        Document document = builder.newDocument();
        Element listaContatti = document.createElement("rubrica");
        document.appendChild(listaContatti);

        Element contatto = null;
        Element nome = null;
        Element cognome = null;
        Element telefono = null;
        Element email = null;

        for (int i = 0; i < rubrica.size(); i++) {

            contatto = document.createElement("contatto");
            //potrei fare anche contatto.setAttribte

            nome = document.createElement("nome");
            nome.setTextContent(rubrica.get(i).getNome());
            contatto.appendChild(nome);

            cognome = document.createElement("cognome");
            cognome.setTextContent(rubrica.get(i).getCognome());
            contatto.appendChild(cognome);

            telefono = document.createElement("telefono");
            telefono.setTextContent(rubrica.get(i).getTelefono());
            contatto.appendChild(telefono);

            email = document.createElement("email");
            email.setTextContent(rubrica.get(i).getEmail());
            contatto.appendChild(email);

            listaContatti.appendChild(contatto);
            //lui non aggiunge i tag con le identazioni corrette
        }

        //per scriverlo, bisogna prendere quello che c'è in memoria
        //e realizzare uno stream

        TransformerFactory transformerFactory = TransformerFactory.newInstance();
        Transformer transformer = transformerFactory.newTransformer();
        DOMSource source = new DOMSource(document);

        //lo stream può essere sì un file, ma anche il s.out, ma anche la risposta alla chiamata http
        StreamResult result = new StreamResult(new File(path));

        transformer.transform(source, result);

    }

    private static void inserisciContatto(){

        Scanner inputTastiera = new Scanner(System.in);
        Contatto contatto = new Contatto();

        System.out.println("\nInserisci i dati del nuovo contatto.");
        System.out.println("\nNome: ");
        contatto.setNome(inputTastiera.nextLine().trim());
        System.out.println("\nCognome: ");
        contatto.setCognome(inputTastiera.nextLine().trim());
        System.out.println("\nTelefono: ");
        contatto.setTelefono(inputTastiera.nextLine().trim());
        System.out.println("\nE-mail: ");
        contatto.setEmail(inputTastiera.nextLine().trim());

        if(contatto.getNome().length() == 0  &&
            contatto.getCognome().length() == 0  &&
            contatto.getTelefono().length() == 0  &&
            contatto.getEmail().length() == 0){

            System.out.println("Attenzione! Almeno un campo del contatto deve essere non vuoto");

        }
        else {

            boolean presente = false;

            //controllo di non avere già il contatto
            if (rubrica.size() > 0) {
                for (Contatto contact : rubrica) {
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
                vecchioCampo = rubrica.get(index).getNome();
                rubrica.get(index).setNome(inputTastiera.nextLine());
                break;

            case "c":
                System.out.println("Cognome: ");
                vecchioCampo = rubrica.get(index).getCognome();
                rubrica.get(index).setCognome(inputTastiera.nextLine());
                break;

            case "t":
                System.out.println("Telefono: ");
                vecchioCampo = rubrica.get(index).getTelefono();
                rubrica.get(index).setTelefono(inputTastiera.nextLine());
                break;

            case "e":
                System.out.println("Email: ");
                vecchioCampo = rubrica.get(index).getEmail();
                rubrica.get(index).setEmail(inputTastiera.nextLine());
                break;

            default:


        }

        if(rubrica.get(index).getNome().length() == 0  &&
                rubrica.get(index).getCognome().length() == 0  &&
                rubrica.get(index).getTelefono().length() == 0  &&
                rubrica.get(index).getEmail().length() == 0){

            System.out.println("Attenzione! Almeno un campo del contatto deve essere non vuoto");

            //e rimetto a posto il vecchio valore del campo
            switch (comando){
                case "n":
                    rubrica.get(index).setNome(vecchioCampo);
                    break;

                case "c":
                    rubrica.get(index).setCognome(vecchioCampo);
                    break;

                case "t":
                    rubrica.get(index).setTelefono(vecchioCampo);
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

        for (Contatto contatto: rubrica) {

            if(contatto.getNome().equals(nome))
                contattiTrovati.add(rubrica.indexOf(contatto));

        }

        return contattiTrovati;

    }

    private static ArrayList<Integer> cercaContattiCognome(){

        ArrayList<Integer> contattiTrovati = new ArrayList<>();

        Scanner inputTastiera = new Scanner(System.in);

        System.out.println("Cognome contatto: ");
        String cognome = inputTastiera.nextLine().trim();

        for (Contatto contatto: rubrica) {

            if(contatto.getCognome().equals(cognome))
                contattiTrovati.add(rubrica.indexOf(contatto));

        }

        return contattiTrovati;
    }

    private static ArrayList<Integer> cercaContattiTelefono(){

        ArrayList<Integer> contattiTrovati = new ArrayList<>();

        Scanner inputTastiera = new Scanner(System.in);

        System.out.println("Telefono contatto: ");
        String telefono = inputTastiera.nextLine().trim();

        for (Contatto contatto: rubrica) {

            if(contatto.getTelefono().equals(telefono))
                contattiTrovati.add(rubrica.indexOf(contatto));

        }

        return contattiTrovati;

    }

    private static ArrayList<Integer> cercaContattiEmail(){

        ArrayList<Integer> contattiTrovati = new ArrayList<>();

        Scanner inputTastiera = new Scanner(System.in);

        System.out.println("Email contatto: ");
        String email = inputTastiera.nextLine().trim();

        for (Contatto contatto: rubrica) {

            if(contatto.getEmail().equals(email))
                contattiTrovati.add(rubrica.indexOf(contatto));

        }

        return contattiTrovati;
    }

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

        for (Contatto contatto: rubrica) {

            if(contatto.getNome().equals(nome) && contatto.getCognome().equals(cognome))
                return rubrica.indexOf(contatto);

        }

        return -1;

    }

    private static int cercaContattoTelefono(){

        Scanner inputTastiera = new Scanner(System.in);

        System.out.println("Telefono contatto: ");
        String telefono = inputTastiera.nextLine().trim();

        for (Contatto contatto: rubrica) {

            if(contatto.getTelefono().equals(telefono))
                return rubrica.indexOf(contatto);

        }

        return -1;

    }

    private static int cercaContattoEmail(){

        Scanner inputTastiera = new Scanner(System.in);

        System.out.println("Nome contatto: ");
        String email = inputTastiera.nextLine().trim();

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

    /**
     *
     * @param contatto
     * @param chiave
     * @return true se la chiave è una chiave duplicata
     */
    private static boolean chiaveDuplicata(Contatto contatto, String chiave){

        switch (chiave){

            case "n&c":

                for (Contatto contattoConfronto: rubrica) {

                    //faccio il controllo per i duplicati, ma questo controllo è CASE SESITIVE
                    //per fare CASE UNSESITIVE basta usare equalsIgnoreCase()
                    if(contatto.getNome().equals(contattoConfronto.getNome()) &&
                            contatto.getCognome().equals(contattoConfronto.getCognome()))
                        return true;

                }

                break;

            case "t":

                for (Contatto contattoConfronto: rubrica) {

                    if(contatto.getTelefono().equals(contattoConfronto.getTelefono()))
                        return true;

                }

                break;

            case "e":

                for (Contatto contattoConfronto: rubrica) {

                    if(contatto.getEmail().equals(contattoConfronto.getEmail()))
                        return true;

                }

                break;

            default:

        }

        //se arrivo alla fine, vuol dire che non ho trovato duplicati
        return false;

    }

}
