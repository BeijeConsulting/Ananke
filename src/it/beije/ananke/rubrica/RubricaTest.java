package it.beije.ananke.rubrica;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class RubricaTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Rubrica rubrica = new Rubrica();
        String root = "C:\\Users\\Padawan01\\IdeaProjects\\git\\Ananke\\src\\it\\beije\\ananke\\rubrica\\rubrica_";
        try {
            List<Contact> contacts = rubrica.readFromCSV(root);
            rubrica.writeToXML(root, contacts);

            rubrica.printContacts(contacts);

            int i;
            do {
                printMenu();
                System.out.println("Select an option");
                i = scanner.nextInt();

                switch (i) {
                    case 0:
                        System.out.println("Thank you and goodbye");
                        break;
                    case 1:
                        rubrica.addContact(scanner);
                        break;
                    case 2:
                        rubrica.removeContact(scanner);
                        break;
                    case 3:
                        rubrica.modifyContact(scanner);
                        break;
                    case 4:
                        scanner.nextLine();
                        System.out.println("Inserisci nome");
                        String nome = scanner.nextLine();
                        System.out.println("Inserisci cognome");
                        String cognome = scanner.nextLine();
                        if (!rubrica.getContactByName(nome).isEmpty()) {
                            System.out.println(rubrica.getContact(nome, cognome));
                        } else {
                            System.out.println("Contact not found");
                        }
                        break;
                    case 5:
                        scanner.nextLine();
                        System.out.println("Insert first name");
                        String firstName = scanner.nextLine();
                        List<Contact> contactByName = rubrica.getContactByName(firstName);
                        rubrica.printContacts(contactByName);
                        break;
                    case 6:
                        scanner.nextLine();
                        System.out.println("Insert last name");
                        String lastName = scanner.nextLine();
                        List<Contact> contactByLastName = rubrica.getContactByLastName(lastName);
                        rubrica.printContacts(contactByLastName);
                        break;
                    case 7:
                        System.out.println("Insert number");
                        List<Contact> contactByNumber = rubrica.searchByNumber(scanner);
                        rubrica.printContacts(contactByNumber);
                        break;
                    case 8:
                        rubrica.orderByName();
                        break;
                    case 9:
                        rubrica.orderByLastName();
                        break;
                    default:
                        printMenu();
                        break;
                }
            } while (i != 0);
            scanner.close();
        } catch (TransformerException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void printMenu() {
        System.out.println("0 - Exit menu");
        System.out.println("1 - Add new contact");
        System.out.println("2 - Remove a contact");
        System.out.println("3 - Modify Contact");
        System.out.println("4 - Find a contact");
        System.out.println("5 - Find contacts of name...");
        System.out.println("6 - Find contacts of surname...");
        System.out.println("7 - Find contacts by number");
        System.out.println("8 - Order by name");
        System.out.println("9 - Order by surname");

    }
}