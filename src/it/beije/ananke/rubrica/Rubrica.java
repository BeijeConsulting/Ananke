package it.beije.ananke.rubrica;

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
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Rubrica {

    private static List<Contact> rubrica = new ArrayList<>();
    private static final String PATH = "C:\\Users\\Padawan02\\Desktop\\esercizietti\\rubrica";

    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "Padawan02May4BeWithYou";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/ananke?serverTimezone=CET";

    public static void main(String[] args) {

        Scanner inputTastiera = new Scanner(System.in);
        String comando;

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

    }

    private static Connection createConnection() throws ClassNotFoundException, SQLException {

        Connection connection = null;


        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


        return connection;

    }

    private static void insertContacts(){

        Connection connection = null;

        try {

            connection = createConnection();
            Scanner inputKeyword = new Scanner(System.in);
            String command = null;

            System.out.println("Come vuoi inserire i contatti?" +
                    "\n\t[1] Importa contatti da file" +
                    "\n\t[2] Inserisci contatti da tastiera");

            command = inputKeyword.nextLine().trim();

            switch (command){

                case "1":

                    insertFromFile(connection);

                    break;

                case "2":

                    insertFromKeyWords(connection);

                    break;

                default:

            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        } finally {
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }

    }

    private static void insertFromKeyWords(Connection connection) throws SQLException {

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

            System.out.println("Vuoi continuare a inserire contatti?");
            command = inputKeyWord.nextLine().trim();

        }while(!command.equals("no"));

        System.out.println("Questi sono i contatti che hai aggiunto:\n");
        stampaContatti(rubric);
        System.out.println("\nVuoi aggiungere questi contatti al database?" +
                "\n\tyes?" +
                "\n\tno?");

        command = inputKeyWord.nextLine().trim();

        //TODO: potrei fare in modo che all'interno di questo pezzo, io possa anche eliminarne qualcuno o modificarlo
        //quindi farlo lavorare su questo arraylist prima di inserire sul db

        PreparedStatement preparedStatement = null;
        String psInsert = "INSERT INTO contact (name, surname, telephone, email) VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(psInsert);

        for (Contact contact: rubric) {

            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getSurname());
            preparedStatement.setString(3, contact.getTelephone());
            preparedStatement.setString(4, contact.getEmail());

            preparedStatement.execute();

        }

    }

    private static void insertFromFile(Connection connection) {

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
                        readContactFromXml(connection, path);
                    } catch (Exception e) {
                        System.out.println("C'è stato un problema nella lettura del file.xml");
                        e.printStackTrace();
                    }
                } else {
                    if (estensione.equals("csv")) {
                        try {
                            readContactFromCsv(connection, path);
                        } catch (SQLException throwables) {
                            System.out.println("C'è stato un problema nella lettura del file.csv");
                            throwables.printStackTrace();
                        }
                    }
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

    }

    //legge il file csv e tira fuori i contatti
    private static void readContactFromCsv(Connection connection, String path) throws SQLException {

        ArrayList<Contact> rubric = new ArrayList<>();

        BufferedReader reader = null;

        try {
            String info;
            reader = new BufferedReader(new FileReader(path));
            while((info = reader.readLine()) != null){

                //ho letto qualcosa di nuovo e non vuoto
                //devo splittare la stringa e estrapolare
                //le informazioni del contatto.
                String[] infoContatto = info.split(";");
                Contact contact = new Contact();

                contact.setName(infoContatto[0].trim());
                contact.setSurname(infoContatto[1].trim());
                contact.setTelephone(infoContatto[2].trim());
                contact.setEmail(infoContatto[3].trim());

                rubric.add(contact);
                //System.out.println(contatto.toString());

            }

        } catch (FileNotFoundException e) {
            System.out.println("Il file non era presente.");

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

        System.out.println("Ora aggiungo questi contatti al database");
        stampaContatti(rubric);

        PreparedStatement preparedStatement = null;
        String psInsert = "INSERT INTO contact (name, surname, telephone, email) VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(psInsert);

        for (Contact contact: rubric) {

            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getSurname());
            preparedStatement.setString(3, contact.getTelephone());
            preparedStatement.setString(4, contact.getEmail());

            preparedStatement.execute();

        }

    }

    private static void readContactFromXml(Connection connection, String path) throws ParserConfigurationException, IOException, SAXException, SQLException {

        ArrayList<Contact> rubric = new ArrayList<>();

        //servono per leggere e scrivere file xml.
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();

        //creo il documento virtuale
        Document document = builder.parse(path);

        Element docElement = document.getDocumentElement();
        NodeList elementiContatto = docElement.getElementsByTagName("contatto");

        for (int i = 0; i < elementiContatto.getLength(); i++) {

            Contact contact = new Contact();
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
                            contact.setName(valore.getTextContent().trim());
                            break;
                        case "cognome":
                            contact.setSurname(valore.getTextContent().trim());
                            break;
                        case "telefono":
                            contact.setTelephone(valore.getTextContent().trim());
                            break;
                        case "email":
                            contact.setEmail(valore.getTextContent().trim());
                            break;

                        default:
                            System.out.println("elemento in contatto non riconosciuto");
                            break;
                    }
                }
            }
            rubric.add(contact);
        }

        System.out.println("Ora aggiungo questi contatti al database");
        stampaContatti(rubric);

        PreparedStatement preparedStatement = null;
        String psInsert = "INSERT INTO contact (name, surname, telephone, email) VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(psInsert);

        for (Contact contact: rubric) {

            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getSurname());
            preparedStatement.setString(3, contact.getTelephone());
            preparedStatement.setString(4, contact.getEmail());

            preparedStatement.execute();

        }

    }

    private static void deleteContacts(Connection connection){

        //allora. gli chiedo prima di cercare dei contatti con dei criteri
        //poi chiedo conferma se vuole eliminarli tutti
        //si ok
        //no gli chiedo di essere più specifico e aggiungere altri campi
        //ma magari la aggiungo dopo questa funzionalità


    }

    private static void modifyContacts(Connection connection){

    }

    private static void findContacts(Connection connection) throws SQLException {

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

        //riempiamo l'arraylist che poi farò tornare che mi serve per la delete e per la modify
        //con i campi che voglio specificare
        for (int i = 0; i < Integer.parseInt(command); i++) {

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

        //a questo punto posso costruire la query e interrogare il db
        StringBuilder query = new StringBuilder("SELECT * FROM contact WHERE ");
        Statement statement = null;
        ResultSet rs = null;

        statement = connection.createStatement();

        for (int i = 0; i < fields.size(); i++) {

            //Chiedo all'utente di inserire il valore specfico del primo campo
            System.out.println(fields.get(i) + ":\t");
            String value = inputKeyWord.nextLine().trim();

            //TODO: controllare query
            query.append("(").append(fields.get(i)).append(" = '").append(value).append("')");

            if(i < fields.size() - 1 )
                //ho ancora una condizione da aggiungere
                query.append(" AND ");
            else
                //non ho più condizioni da aggiungere
                query.append(";");

        }


        if (statement != null) {
            rs = statement.executeQuery(query.toString());
        }

        if (rs != null) {

            System.out.println("Ecco i contatti che cercavi:");

            while (rs.next()) {
                System.out.println("id : " + rs.getInt("id"));
                System.out.println("cognome : " + rs.getString("surname"));
                System.out.println("nome : " + rs.getString("name"));
                System.out.println("email : " + rs.getString("email"));
                System.out.println("telefono : " + rs.getString("telephone"));
                System.out.println("-----");
            }
        }

        if (statement != null) {
            statement.close();
        }
        if (rs != null) {
            rs.close();
        }
    }

    private static void findContactsOneField(Connection connection) throws SQLException {

        Scanner inputKeyword = new Scanner(System.in);
        StringBuilder query = new StringBuilder("SELECT * FROM contact");
        String command = null;
        String field = null;
        String value = null;
        Statement statement = null;
        ResultSet rs = null;

        System.out.println("Per quale campo vuoi cercare?" +
                "\n\t -n: name" +
                "\n\t -s: surname" +
                "\n\t -t: telephone" +
                "\n\t -e: email" +
                "\nScrivi il comando con - seguito dalla lettera");

        command = inputKeyword.nextLine().trim();

        switch (command){

            case "-n":
                field = "name";
                break;

            case "-s":
                field = "surname";
                break;

            case "-t":
                field = "telephone";
                break;

            case "-e":
                field = "email";
                break;

            default:

        }

        statement = connection.createStatement();

        System.out.println(field + ":\t");
        value = inputKeyword.nextLine().trim();

        //TODO: controllare query
        query.append(" WHERE ('").append(field).append("' = '").append(value).append("');");

        if (statement != null) {
            rs = statement.executeQuery(query.toString());
        }

        if (rs != null) {

            System.out.println("Ecco i contatti che cercavi:");

            while (rs.next()) {
                System.out.println("id : " + rs.getInt("id"));
                System.out.println("cognome : " + rs.getString("surname"));
                System.out.println("nome : " + rs.getString("name"));
                System.out.println("email : " + rs.getString("email"));
                System.out.println("telefono : " + rs.getString("telephone"));
                System.out.println("-----");
            }
        }

        if (statement != null) {
            statement.close();
        }
        if (rs != null) {
            rs.close();
        }

    }

    private static void scriviSuFile(){

        Scanner inputTastiera = new Scanner(System.in);
        String path;

        File directory = new File(PATH);
        File[] files = directory.listFiles();
        int i=0;

        System.out.println("Su quale file vuoi salvare le modifiche alla rubrica?" +
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

    private static void scriviSuFileCsv(String path) {

        FileWriter writer = null;

        try {

            writer = new FileWriter(path);

            //stampo tutta la rubrica
            for (Contact contact : rubrica) {

                writer.write(contact.toString());

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
            nome.setTextContent(rubrica.get(i).getName());
            contatto.appendChild(nome);

            cognome = document.createElement("cognome");
            cognome.setTextContent(rubrica.get(i).getSurname());
            contatto.appendChild(cognome);

            telefono = document.createElement("telefono");
            telefono.setTextContent(rubrica.get(i).getTelephone());
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

    private static void stampaContatti(ArrayList<Contact> rubric) {
        for (Contact contact : rubric) {
            System.out.println("[" + (int) (rubric.indexOf(contact) + 1) + "]\t" + contact.toString());
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

    //////////////////////////////////////////////////////////////////////////
    //////////////////////////METODI IN DISUSO////////////////////////////////
    //////////////////////////////////////////////////////////////////////////

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
