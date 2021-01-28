package it.beije.ananke.rubrica.csvmanager;

import it.beije.ananke.rubrica.Contact;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CSVManager {
    public static List<Contact> open(String root) throws IOException {
        List<Contact> contacts = new ArrayList<>();
        File file = new File(root);
        if (file.exists()) {
            BufferedReader br = new BufferedReader(new FileReader(file));
            String str;
            String[] data;
            while (br.ready()) {
                str = br.readLine();
                data = str.split(";");
                Contact contact = new Contact(data[0], data[1], data[2], data[3]);
                contact.toString();
                contacts.add(contact);
            }
        } else {
            System.out.println("Rubrica insesistente");
        }
        return contacts;
    }

    public static void write(List<Contact> contacts, String root) throws IOException {
        File file = new File(root);
        BufferedWriter bw = new BufferedWriter(new FileWriter(file, false));
        for (Contact c : contacts) {
            bw.write(c.getFirstName() + ";" + c.getLastName() + ";" +
                    c.getPhoneNumber() + ";" + c.getEmail() + ";\n");
            bw.flush();
        }
        System.out.println(file.exists());
        bw.close();
    }
}
