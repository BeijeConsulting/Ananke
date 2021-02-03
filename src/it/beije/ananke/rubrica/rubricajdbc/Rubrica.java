package it.beije.ananke.rubrica.rubricajdbc;

import it.beije.ananke.rubrica.Contact;
import it.beije.ananke.rubrica.rubricajdbc.csvmanager.CSVManager;
import it.beije.ananke.rubrica.rubricajdbc.jdbcmanager.JDBCManager;
import it.beije.ananke.rubrica.rubricajdbc.xmlmanager.XMLManager;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.*;

public class Rubrica {
    public String CSVroot;
    public String XMLroot;
    private List<Contact> contacts = new ArrayList<>();

    public void importFromDB() {
        this.contacts = JDBCManager.selectAll();
    }

    public List<Contact> readFromCSV(String root) throws IOException {
        this.CSVroot = root;
        this.XMLroot = root.substring(root.length() - 3) + ".xml";
        this.contacts = CSVManager.open(CSVroot);
        return contacts;
    }

    public List<Contact> readFromXML(String root) throws ParserConfigurationException, SAXException, IOException {
        this.CSVroot = root.substring(root.length() - 3) + ".txt";
        this.XMLroot = root;
        this.contacts = XMLManager.open(XMLroot);
        return contacts;
    }

    public void exportToDB(Scanner scanner) throws IOException, ParserConfigurationException, SAXException {
        System.out.println("Type the root of a file with a .txt or a .xml extension");
        String root = scanner.nextLine();
        String extension = root.substring(root.length() - 3);
        if (extension.equalsIgnoreCase("txt")) {
            this.contacts = readFromCSV(root);
        } else if (extension.equalsIgnoreCase("xml")) {
            this.contacts = readFromXML(root);
        }

        for (Contact contact : contacts) {
            JDBCManager.insert(contact);
        }

    }

    public void exportAsCSV(String root, List<Contact> contacts) throws IOException {
        importFromDB();
        CSVManager.write(contacts, root);
    }

    public void exportAsXML(String root, List<Contact> contacts) throws TransformerException, ParserConfigurationException {
        importFromDB();
        XMLManager.write(contacts, root);
    }

    public void addContact(Scanner scanner) throws IOException, TransformerException, ParserConfigurationException {
        Contact contact = new Contact();
        String firstName;
        String lastName;
        String phoneNumber;
        String email;

        while (true) {
            try {
                System.out.println("Inserisci nome");
                firstName = scanner.nextLine();
                firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1).toLowerCase();
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
                lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1).toLowerCase();
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
                email = scanner.nextLine().toLowerCase();
                if (email.contains(";")) {
                    throw new IllegalArgumentException();
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Remove that damn ';'");
            }
        }
        contact.setFirstName(firstName);
        contact.setLastName(lastName);
        contact.setPhoneNumber(phoneNumber);
        contact.setEmail(email);

        if (contactControl(contact)) {
            contacts.add(contact);
            System.out.println("Contact added");
        } else {
            System.out.println("Contact already exists");
        }
        JDBCManager.insert(contact);
        importFromDB();
        exportAsCSV(CSVroot, contacts);
        exportAsXML(XMLroot, contacts);
    }

    public void orderByName() throws IOException, TransformerException, ParserConfigurationException {
        Collections.sort(contacts, Comparator.comparing(Contact::getFirstName)); //(c1,c2)-> c1.getFirstName().compare(c2.getFirstName());
        printContacts(contacts);
        exportAsCSV(CSVroot, contacts);
        exportAsXML(XMLroot, contacts);
    }

    public void orderByLastName() throws IOException, TransformerException, ParserConfigurationException {
        Collections.sort(contacts, Comparator.comparing(Contact::getLastName)); //(c1,c2)-> c1.getFirstName().compare(c2.getFirstName());
        printContacts(contacts);
        exportAsCSV(CSVroot, contacts);
        exportAsXML(XMLroot, contacts);
    }

    public void modifyContact(Scanner scanner) throws IOException, TransformerException, ParserConfigurationException {
        System.out.println("Digita il nome");
        String firstName = scanner.nextLine();
        System.out.println("Digita il cognome");
        String lastName = scanner.nextLine();
        Contact contact = getContact(scanner);
        int index = getContactIndex(contact);

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
                                    newFirstName = newFirstName.substring(0, 1).toUpperCase() + newFirstName.substring(1).toLowerCase();
                                    if (newFirstName.contains(";")) {
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
                                    newLastName = newLastName.substring(0, 1).toUpperCase() + newLastName.substring(1).toLowerCase();
                                    if (newLastName.contains(";")) {
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
                                    if (newPhoneNumber.contains(";")) {
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
                                    String newEmail = scanner.nextLine().toLowerCase();
                                    if (newEmail.contains(";")) {
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
            JDBCManager.update(contact);
            importFromDB();
            exportAsCSV(CSVroot, contacts);
            exportAsXML(XMLroot, contacts);
        } else {
            System.out.println("Contact not found");
        }
    }

    public void removeContact(Scanner scanner) throws IOException, TransformerException, ParserConfigurationException {
        Contact contact = getContact(scanner);
        String confirm;
        do {
            System.out.println("Do you want to delete this contact? [yes/no]");
            confirm = scanner.next();
            final String approve = "yes";
            final String negate = "no";
            if (confirm.contains("y")) {
                confirm = "yes";
            } else if (confirm.contains("n")) {
                confirm = "no";
            }
            switch (confirm) {
                case approve:
                    contacts.remove(contact);
                    JDBCManager.delete(contact);
                    System.out.println("Contact removed");
                    break;
                case negate:
                    break;
                default:
                    System.out.println("Input not recognized, try again");
                    break;
            }
        } while (!confirm.contains("y") || !confirm.contains("n"));

        JDBCManager.delete(contact);
        importFromDB();
        exportAsCSV(CSVroot, contacts);
        exportAsXML(XMLroot, contacts);
    }

    public List<Contact> searchByField(Scanner scanner) {
        String field;
        List<Contact> contacts = null;
        System.out.println("Select field where to search");
        int choice = -1;
        StringBuilder firstName = new StringBuilder();
        StringBuilder lastName = new StringBuilder();
        StringBuilder phoneNumber = new StringBuilder();
        StringBuilder email = new StringBuilder();

        do {
            System.out.println("1 - first name");
            System.out.println("2 - last name");
            System.out.println("3 - phone number");
            System.out.println("4 - email");
            System.out.println("0 - exit menu");
            choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    field = "first_name";
                    boolean found1 = false;
                    do {
                        System.out.println("Type first name:");
                        firstName.append(scanner.nextLine());
                        contacts = JDBCManager.selectByField(field, firstName.toString());
                        System.out.println("Found? [yes/no]");
                        if(scanner.nextLine().equalsIgnoreCase("yes")) {
                            found1 = true;
                        } else {
                            contacts.clear();
                        }

                    } while (!found1);
                    break;
                case 2:
                    field = "last_name";
                    boolean found2 = false;
                    do {
                        System.out.println("Type last name:");
                        lastName.append(scanner.nextLine());
                        contacts = JDBCManager.selectByField(field, lastName.toString());
                        System.out.println("Found? [yes/no]");
                        if(scanner.nextLine().equalsIgnoreCase("yes")) {
                            found2 = true;
                        } else {
                            contacts.clear();
                        }
                    } while (!found2);
                    break;
                case 3:
                    field = "phone_number";
                    boolean found3 = false;
                    do {
                        System.out.println("Type phone number:");
                        phoneNumber.append(scanner.nextLine());
                        contacts = JDBCManager.selectByField(field, phoneNumber.toString());
                        System.out.println("Found? [yes/no]");
                        if(scanner.nextLine().equalsIgnoreCase("yes")) {
                            found3 = true;
                        } else {
                            contacts.clear();
                        }
                    } while (!found3);
                    break;
                case 4:
                    field = "email";
                    boolean found4 = false;
                    do {
                        System.out.println("Type email:");
                        email.append(scanner.nextLine());
                        contacts = JDBCManager.selectByField(field, email.toString());
                        System.out.println("Found? [yes/no]");
                        if(scanner.nextLine().equalsIgnoreCase("yes")) {
                            found4 = true;
                        } else {
                            contacts.clear();
                        }
                    } while (!found4);
                    break;
                default:
                    System.out.println("Invalid input");
            }
        }while (choice != 0);

        return contacts;
    }

    public Contact getContact(Scanner scanner) {
        List<Contact> contactsFound = searchByField(scanner);
        for(Contact contact: contactsFound) {
            System.out.println(contact);
        }
        System.out.println("Type contact id you read ahead");
        contactsFound = JDBCManager.selectByField("id", scanner.nextLine());
        return contactsFound.get(0);
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

    private int getContactIndex(Contact contact) {
        if (contacts.contains(contact)) {
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
