package it.beije.ananke.rubrica.jdbcmanager;

import it.beije.ananke.rubrica.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JDBCManager {

    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "skull.island";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/primo_schema?serverTimezone=CET";

    public static void insert(String firstName, String lastName, String phoneNumber, String email) {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            String sql = "INSERT INTO contatti (first_name, last_name, phone_number, email) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, firstName);
            preparedStatement.setString(2, lastName);
            preparedStatement.setString(3, phoneNumber);
            preparedStatement.setString(4, email);
            preparedStatement.execute();

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException SQLEx) {
                SQLEx.printStackTrace();
            }
        }
    }

    //sviluppare
    public static void delete(Scanner scanner) {
        List<Contact> contacts = selectByField(scanner);
        Connection connection = null;
        if (!contacts.isEmpty()) {
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                while(true) {
                    System.out.println("Select ID");
                    String id = Integer.valueOf(scanner.nextInt()).toString();
                    String testsql = "SELECT * FROM contatti WHERE id = " + id + ";";
                    System.out.println(testsql);
                    Statement statement = connection.createStatement();
                    ResultSet rs = statement.executeQuery(testsql);
                    List<Integer> id_s = new ArrayList<>();
                    while(rs.next()) {
                        id_s.add(rs.getInt(1));
                    }

                    //blocco di codice da rivedere
                    for(Integer i : id_s) {
                        if (id.equals(id_s.get(i))) {
                            String sql = "DELETE FROM contatti where id = " + id;
                            statement = connection.createStatement();
                            statement.execute(sql);
                            break;
                        }
                    }
                }

            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            } finally {
                try {
                    connection.close();
                } catch (SQLException SQLEx) {
                    SQLEx.printStackTrace();
                }
            }
        } else {
            System.out.println("No match found");
        }
    }

    public static void selectAll() {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            String sql = "SELECT * FROM contatti";
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt(1));
                System.out.println("Nome: " + rs.getString(2));
                System.out.println("Cognome: " + rs.getString(3));
                System.out.println("Tel: " + rs.getString(4));
                System.out.println("Email: " + rs.getString(5));
                System.out.println("--------------------------");
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException SQLEx) {
                SQLEx.printStackTrace();
            }
        }
    }

    public static List<Contact> selectByField(Scanner scanner) {
        Connection connection = null;
        List<Contact> contacts = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            printSelectionMenu();
            System.out.println("Specify the field where to search");
            String field = null;
            String test = scanner.nextLine();
            switch (test) {
                case "1":
                    field = "first_name";
                    System.out.println("Specify first name");
                    break;
                case "2":
                    field = "last_name";
                    System.out.println("Specify last name");
                    break;
                case "3":
                    field = "phone_number";
                    System.out.println("Specify phone number");
                    break;
                case "4":
                    field = "email";
                    System.out.println("Specify email");
                    break;
//                default:
//                aggiungere la clausola default
            }

            String search = scanner.nextLine();
            String sql = "SELECT * FROM contatti WHERE " + field + " = '" + search + "';";
            System.out.println(sql);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt(1));
                System.out.println("Nome: " + rs.getString(2));
                System.out.println("Cognome: " + rs.getString(3));
                System.out.println("Tel: " + rs.getString(4));
                System.out.println("Email: " + rs.getString(5));
                System.out.println("--------------------------");
                Contact contact = new Contact(rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
                contacts.add(contact);
            }

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                connection.close();
            } catch (SQLException SQLEx) {
                SQLEx.printStackTrace();
            }
        }
        return contacts;
    }

    private static void printSelectionMenu() {
        System.out.println("1 - first name");
        System.out.println("2 - last name");
        System.out.println("3 - phone number");
        System.out.println("4 - email");
    }

}