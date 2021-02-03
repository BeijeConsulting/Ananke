package it.beije.ananke.rubrica;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvFile {

    public static List<Contact> readFromCsv(String path){

    	List<Contact> rubric = new ArrayList<>();

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

                //TODO:POSSONO ESSERE NULL. OCIO!! DA CAMBIARE
                if(infoContatto.length == 4) {
	                contact.setName(infoContatto[0].trim());
	                contact.setSurname(infoContatto[1].trim());
	                contact.setTelephone(infoContatto[2].trim());
	                contact.setEmail(infoContatto[3].trim());
                }
                else {
                	//non ho tutte le informazioni. devo capire chi va dove.
                	//mi basta fare lo split, tenendo il ;
                	//in qusto modo dovrei avere comunque 4 elementi e capire chi è pieno e chi no
                	infoContatto = info.split("(?<=;)");
                	
                	if(infoContatto[0].trim().equals(";")) {
                		contact.setName("");
                	}
                	else {
                		contact.setName(infoContatto[0].trim());
                	}
                	if(infoContatto[1].trim().equals(";")) {
                		contact.setSurname("");
                	}
                	else {
                		contact.setSurname(infoContatto[1].trim());
                	}
                	if(infoContatto[2].trim().equals(";")) {
                		contact.setTelephone("");
                	}
                	else {
                		contact.setTelephone(infoContatto[2].trim());
                	}
                	if(infoContatto[3].trim().equals(";")) {
                		contact.setEmail("");
                	}
                	else {
                		contact.setEmail(infoContatto[3].trim());
                	}
                	
                }

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

    public static boolean writeOnCsv(String path, List<Contact> rubric){

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
