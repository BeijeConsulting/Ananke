package it.beije.ananke.rubrica;

import it.beije.ananke.rubrica.csvmanager.CSVManager;
import it.beije.ananke.rubrica.xmlmanager.XMLManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.*;

public class Rubrica {
    public String CSVroot;
    public String XMLroot;
    private List<Contact> contacts = new ArrayList<>();

    public List<Contact> readFromCSV(String root) throws IOException {
        this.CSVroot = root + ".txt";
        this.XMLroot = root + ".xml";
        this.contacts = CSVManager.open(CSVroot);
        return contacts;
    }

    public List<Contact> readFromXML(String root) throws ParserConfigurationException, SAXException, IOException {
        this.CSVroot = root + ".txt";
        this.XMLroot = root + ".xml";
        this.contacts = XMLManager.open(XMLroot);
        return contacts;
    }

    public void writeToCSV(String root, List<Contact> contacts) throws IOException {
        CSVManager.write(contacts, root);
    }

    public void writeToXML(String root, List<Contact> contacts) throws TransformerException, ParserConfigurationException {
        XMLManager.write(contacts, root);
    }

    public void addContact() throws IOException, TransformerException, ParserConfigurationException {
        Contact contact = null;
        while (contact == null) {
            Scanner scanner = new Scanner(System.in);
            String firstName;
            String lastName;
            String phoneNumber;
            String email;

            while (true) {
                try {
                    System.out.println("Inserisci nome");
                    firstName = scanner.nextLine();
                    if (firstName.contains(";")) {
                        throw new IllegalArgumentException();
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Remove that damn ';'");
                }
            }
            while (true) {
                try {
                    System.out.println("Inserisci cognome");
                    lastName = scanner.nextLine();
                    if (lastName.contains(";")) {
                        throw new IllegalArgumentException();
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Remove that damn ';'");
                }
            }
            while (true) {
                try {
                    System.out.println("Inserisci numero di telefono");
                    phoneNumber = scanner.nextLine();
                    if (phoneNumber.contains(";")) {
                        throw new IllegalArgumentException();
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Remove that damn ';'");
                }
            }
            while (true) {
                try {
                    System.out.println("Inserisci mail");
                    email = scanner.nextLine();
                    if (email.contains(";")) {
                        throw new IllegalArgumentException();
                    }
                    break;
                } catch (IllegalArgumentException e) {
                    System.out.println("Remove that damn ';'");
                }
            }
            contact = new Contact(firstName, lastName, phoneNumber, email);

        }

        if (contactControl(contact)) {
            contacts.add(contact);
            System.out.println("Contact added");
        } else {
            System.out.println("Contact already exists");
        }
        writeToCSV(CSVroot, contacts);
        writeToXML(XMLroot, contacts);
    }

    public void orderByName() throws IOException, TransformerException, ParserConfigurationException {
        Collections.sort(contacts, Comparator.comparing(Contact::getFirstName)); //(c1,c2)-> c1.getFirstName().compare(c2.getFirstName());
        printContacts(contacts);
        writeToCSV(CSVroot, contacts);
        writeToXML(XMLroot, contacts);
    }

    public void orderByLastName() throws IOException, TransformerException, ParserConfigurationException {
        Collections.sort(contacts, Comparator.comparing(Contact::getLastName)); //(c1,c2)-> c1.getFirstName().compare(c2.getFirstName());
        printContacts(contacts);
        writeToCSV(CSVroot, contacts);
        writeToXML(XMLroot, contacts);
    }


    // Da verificare
    public void modifyContact() throws IOException, TransformerException, ParserConfigurationException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digita il nome");
        String firstName = scanner.nextLine();
        System.out.println("Digita il cognome");
        String lastName = scanner.nextLine();
        Contact contact = getContact(firstName, lastName);
        int index = getContactIndex(firstName, lastName);

        if (contact != null) {
            int i = -1;
            do {
                try {
                    System.out.println("What to modify?");
                    System.out.println("0 - exit menu");
                    System.out.println("1 - first name");
                    System.out.println("2 - last name");
                    System.out.println("3 - phone number");
                    System.out.println("4 - email");
                    i = scanner.nextInt();
                    scanner.nextLine();
                    switch (i) {
                        case 1:
                            while (true) {
                                try {
                                    System.out.println("set first name");
                                    String newFirstName = scanner.nextLine();
                                    if(newFirstName.contains(";")) {
                                        throw new IllegalArgumentException();
                                    }
                                    contact.setFirstName(newFirstName);
                                    break;
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Try again");
                                }
                            }
                            break;
                        case 2:
                            while (true) {
                                try {
                                    System.out.println("set last name");
                                    String newLastName = scanner.nextLine();
                                    if(newLastName.contains(";")) {
                                        throw new IllegalArgumentException();
                                    }
                                    contact.setLastName(newLastName);
                                    break;
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Try again");
                                }
                            }
                            break;
                        case 3:
                            while (true) {
                                try {
                                    System.out.println("set phone number");
                                    String newPhoneNumber = scanner.nextLine();
                                    if(newPhoneNumber.contains(";")) {
                                        throw new IllegalArgumentException();
                                    }
                                    contact.setPhoneNumber(newPhoneNumber);
                                    break;
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Try again");
                                }
                            }
                            break;
                        case 4:
                            while (true) {
                                try {
                                    System.out.println("set email");
                                    String newEmail = scanner.nextLine();
                                    if(newEmail.contains(";")) {
                                        throw new IllegalArgumentException();
                                    }
                                    contact.setEmail(newEmail);
                                    break;
                                } catch (IllegalArgumentException e) {
                                    System.out.println("Try again");
                                }
                            }
                            break;
                        case 0:
                            System.out.println("Done. Good bye!");
                            break;
                        default:
                            System.out.println("Invalid input");
                            System.out.println("What to modify?");
                            System.out.println("0 - exit menu");
                            System.out.println("1 - first name");
                            System.out.println("2 - last name");
                            System.out.println("3 - phone number");
                            System.out.println("4 - email");
                            break;
                    }
                } catch (InputMismatchException e) {
                    System.out.println("Invalid input");
                    System.out.println("What to modify?");
                    System.out.println("0 - exit menu");
                    System.out.println("1 - first name");
                    System.out.println("2 - last name");
                    System.out.println("3 - phone number");
                    System.out.println("4 - email");
                    scanner.next();
                }
            } while (i != 0);
            contacts.set(index, contact);
            writeToCSV(CSVroot, contacts);
            writeToXML(XMLroot, contacts);
        } else {
            System.out.println("Contact not found");
        }
    }

    public void removeContact() throws IOException, TransformerException, ParserConfigurationException {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Digita il nome");
        String firstName = scanner.nextLine();
        System.out.println("Digita il cognome");
        String lastName = scanner.nextLine();
        Contact contact = getContact(firstName, lastName);
        int index = getContactIndex(firstName, lastName);

        contacts.remove(contact);

        writeToCSV(CSVroot, contacts);
        writeToXML(XMLroot, contacts);
    }

    public List<Contact> searchByNumber() {
        List<Contact> contactsByNumber = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);
        int i = 0;
        String number = "";
        while (true) {
            try {
                contactsByNumber.clear();
                number += Integer.valueOf(scanner.nextInt()).toString();
                i = number.length();
                for (Contact c : contacts) {
                    if (c.getPhoneNumber().substring(0, number.length()).equals(number)) {
                        contactsByNumber.add(c);
                    }
                }
                if (contactsByNumber.isEmpty()) {
                    System.out.println("No match");
                    break;
                } else if (contactsByNumber.size() == 1) {
                    printContacts(contactsByNumber);
                    break;
                }
                printContacts(contactsByNumber);
                System.out.println(number);
            } catch (InputMismatchException e) {
                System.out.println("Invalid input, try again");
                System.out.println(number);
                scanner.next();

            }
        }
        return contactsByNumber;

    }

    public Contact getContact(String firstName, String lastName) {
        List<Contact> contactsByName = getContactByName(firstName);
        for (Contact c : contactsByName) {
            if (c.getLastName().equalsIgnoreCase(lastName)) {
                return c;
            }
        }
        return null;
    }

    public List<Contact> getContactByName(String name) {
        List<Contact> contactsByName = new ArrayList<>();
        for (Contact c : contacts) {
            if (c.getFirstName().equalsIgnoreCase(name)) {
                contactsByName.add(c);
            }
        }
        if (contactsByName.isEmpty()) {
            System.out.println("No match");
        }
        return contactsByName;
    }

    public List<Contact> getContactByLastName(String lastName) {
        List<Contact> contactsByLastName = new ArrayList<>();
        for (Contact c : contacts) {
            if (c.getLastName().equalsIgnoreCase(lastName)) {
                contactsByLastName.add(c);
            }
        }
        if (contactsByLastName.isEmpty()) {
            System.out.println("No match");
        }
        return contactsByLastName;
    }

    private int getContactIndex(String firstName, String lastName) {
        if (getContact(firstName, lastName) != null) {
            Contact contact = getContact(firstName, lastName);
            int index = contacts.indexOf(contact);
            return index;
        }
        return -1;
    }

    public void printContacts(List<Contact> contacts) {
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    private boolean contactControl(Contact contact) {
        for (Contact c : contacts) {
            if (contact.equals(c)) {
                return false;
            }
        }
        return true;
    }
}
