package it.beije.ananke.rubrica.rubricajpa;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import it.beije.ananke.rubrica.Contact;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class RubricaTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Rubrica rubrica = new Rubrica();
        String root = "C:\\Users\\Padawan01\\IdeaProjects\\git\\Ananke\\src\\it\\beije\\ananke\\rubrica\\rubricajpa\\rubrica_.txt";
        try {
            List<Contact> contacts = rubrica.importFromDB();
            int i;
            do {
                printMenu();
                System.out.println("Select an option");
                i = Integer.valueOf(scanner.nextLine());

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
                    	System.out.println("Do you know contact id? [yes/no]");
                    	String confirm = scanner.nextLine();
                    	final String approve = "yes";
            			final String negate = "no";
            			if (confirm.contains("y")) {
            				confirm = "yes";
            			} else if (confirm.contains("n")) {
            				confirm = "no";
            			}
            			switch (confirm) {
            			case approve:
            				rubrica.getContact(scanner);
            			case negate:
            				rubrica.searchByField(scanner);
            			}
                        
                        break;
                    case 5:
                    	rubrica.printContacts(contacts);
                    case 6:
                        rubrica.orderByName();
                        break;
                    case 7:
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
        System.out.println("5 - Print contacts");
        System.out.println("6 - Order by name");
        System.out.println("7 - Order by surname");

    }
}