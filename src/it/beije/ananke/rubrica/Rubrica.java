package it.beije.ananke.rubrica;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Rubrica {
    public String root;
    private ArrayList<Contact> contacts = new ArrayList<>();

    public Rubrica() {
        System.out.println("Insert URL");
        Scanner scanner = new Scanner(System.in);
        String url = scanner.nextLine();
        this.root = url;
    }

    public void createRubrica() {
        File file = new File(root);
        try {
            if (file.createNewFile()) {
                System.out.println("Rubrica creata");
            } else {
                System.out.println("Rubrica già esistente");
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void addContact() {
        File file = new File(root);
        Contact contact;
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, true))) {
            Scanner scanner = new Scanner(System.in);

            System.out.println("Inserisci nome");
            String firstName = scanner.nextLine();
            bw.write(firstName + ";");
            System.out.println("Inserisci cognome");
            String lastName = scanner.nextLine();
            bw.write(lastName + ";");
            System.out.println("Inserisci numero di telefono");
            String phoneNumber = scanner.nextLine();
            bw.write(phoneNumber + ";");
            System.out.println("Inserisci mail");
            String email = scanner.nextLine();
            bw.write(email + "\n");

            contact = new Contact(firstName, lastName, phoneNumber, email);
            contacts.add(contact);
            bw.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public void orderByAsc() {
        Collections.sort(contacts, Comparator.comparing(Contact::getFirstname));
        for (Contact c : contacts) {
            System.out.println(c.toString());
        }
        this.write(contacts);
    }

    public void modifyContact() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digita il nome");
        String firstName = scanner.nextLine();
        System.out.println("Digita il cognome");
        String lastName = scanner.nextLine();
        Contact contact = getContact(firstName, lastName);
        int index = getContactIndex(firstName, lastName);

        if (contact != null) {

            int i;
            System.out.println("What to modify?");
            System.out.println("1 - first name");
            System.out.println("2 - last name");
            System.out.println("3 - phone number");
            System.out.println("4 - email");

            do {
                System.out.println("Cosa vuoi fare?");
                i = scanner.nextInt();
                switch (i) {
                    case 1:
                        System.out.println("set first name");
                        contact.setFirstname(scanner.next());

                        break;
                    case 2:
                        System.out.println("set last name");
                        contact.setLastName(scanner.next());
                        break;
                    case 3:
                        System.out.println("set phone number");
                        contact.setPhoneNumber(scanner.next());
                        break;
                    case 4:
                        System.out.println("set email");
                        contact.setEmail(scanner.next());
                        break;
                    case 0:
                        System.out.println("Done. Good bye!");
                        break;
                }
                scanner.nextLine();
            } while (i != 0);
            contacts.set(index, contact);
            this.write(contacts);
        } else {
            System.out.println("Contatto non presente in rubrica");
        }
    }

    public void open() {
        File file = new File(root);
        if(file.exists()) {
            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String str;
                String[] data;
                while (br.ready()) {
                    str = br.readLine();
                    data = str.split(";");
                    Contact contact = new Contact(data[0], data[1], data[2], data[3]);
                    contact.toString();
                    this.contacts.add(contact);
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("Rubrica insesistente");
        }
    }

    private void write(ArrayList<Contact> contacts) {
        File file = new File(root);
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file, false))) {
            for (Contact c : contacts) {
                bw.write(c.getFirstname() + ";" + c.getLastName() + ";" +
                        c.getPhoneNumber() + ";" + c.getEmail() + ";\n");
                bw.flush();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public Contact getContact(String firstName, String lastName) {
        for (Contact c : contacts) {
            if (c.getFirstname().equalsIgnoreCase(firstName) && c.getLastName().equalsIgnoreCase(lastName)) {
                return c;
            }
        }
        return null;
    }

    private int getContactIndex(String firstName, String lastName) {
        if (getContact(firstName, lastName) != null) {
            Contact contact = getContact(firstName, lastName);
            int index = contacts.indexOf(contact);
            return index;
        }
        return -1;
    }
}
