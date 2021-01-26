package it.beije.ananke.rubrica;

import java.util.Scanner;

public class RubricaTest {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Rubrica rubrica = new Rubrica();
        rubrica.open();
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
                    rubrica.createRubrica();
                    break;
                case 2:
                    rubrica.addContact();
                    break;
                case 3:
                    rubrica.orderByAsc();
                    break;
                case 4:
                    rubrica.modifyContact();
                    break;
                case 5:
                    scanner.nextLine();
                    System.out.println("Inserisci nome");
                    String nome = scanner.nextLine();
                    System.out.println("Inserisci cognome");
                    String cognome = scanner.nextLine();
                    if(rubrica.getContact(nome, cognome) != null) {
                        System.out.println(rubrica.getContact(nome, cognome));
                    } else {
                        System.out.println("Contact not found");
                    }
                    break;
                default:
                    printMenu();
                    break;
            }
        } while (i!=0);
        scanner.close();
    }

    public static void printMenu() {
        System.out.println("0 - Exit menu");
        System.out.println("1 - Create new rubrica from root");
        System.out.println("2 - Add new contact");
        System.out.println("3 - Order by asc");
        System.out.println("4 - Modify Contact");
        System.out.println("5 - Find a contact");
    }
}