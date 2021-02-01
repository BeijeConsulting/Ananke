package it.beije.ananke.rubrica;

import java.io.*;
import java.util.ArrayList;

public class CsvFile {

    public static ArrayList<Contact> readFromCsv(String path){

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

        return rubric;
    }

    public static boolean writeOnCsv(String path, ArrayList<Contact> rubric){

        FileWriter writer = null;

        try {

            writer = new FileWriter(path);

            //stampo tutta la rubrica
            for (Contact contact : rubric) {

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

        return true;
    }
}
