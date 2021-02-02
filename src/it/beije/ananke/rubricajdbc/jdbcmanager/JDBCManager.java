package it.beije.ananke.rubricajdbc.jdbcmanager;

import it.beije.ananke.rubricajdbc.Contact;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCManager {

    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "skull.island";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/ananke?serverTimezone=CET";

    public static void insert(Contact contact) {
        Connection connection = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);


            String sql = "INSERT INTO contatti (first_name, last_name, phone_number, email) VALUES (?,?,?,?)";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);

            preparedStatement.setString(1, contact.getFirstName());
            preparedStatement.setString(2, contact.getLastName());
            preparedStatement.setString(3, contact.getPhoneNumber());
            preparedStatement.setString(4, contact.getEmail());
            preparedStatement.execute();

            String idSql = "SELECT * FROM contatti WHERE " +
                    "first_name = '" + contact.getFirstName() +
                    "', last_name = '" + contact.getLastName() +
                    "', phone_number = '" + contact.getPhoneNumber() +
                    "', email = '" + contact.getEmail() + "';";
            System.out.println(idSql);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(idSql);
            contact.setId(rs.getInt(1));


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

    public static void delete(Contact contact) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = "DELETE FROM contatti where id = " + contact.getId();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Contact deleted");
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

    public static void update(Contact contact) {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            String sql = "UPDATE contatti SET "+
                    "first_name = '" + contact.getFirstName() +
                    "', last_name = '" + contact.getLastName() +
                    "', phone_number = '" + contact.getPhoneNumber() +
                    "', last_name = '" + contact.getEmail() + "'"
                    + " where id = " + contact.getId();
            Statement statement = connection.createStatement();
            statement.execute(sql);
            System.out.println("Contact updated");
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

    public static List<Contact> selectAll() {
        List<Contact> contacts = new ArrayList<>();
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
                Contact contact = new Contact();
                contact.setId(rs.getInt(1));
                contact.setFirstName(rs.getString(2));
                contact.setLastName(rs.getString(3));
                contact.setPhoneNumber(rs.getString(4));
                contact.setEmail(rs.getString(5));
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

    public static List<Contact> selectByField(String field, String search) {
        Connection connection = null;
        List<Contact> contacts = new ArrayList<>();

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

//            select * from contatti where (substring(first_name, 1, 7) = 'stefano');
            String sql;
            if(!field.equalsIgnoreCase("id")) {
                sql = "SELECT * FROM contatti WHERE " +
                        "substring(" + field + ", 1 , " + search.length() + ") = '" + search + "';";
                System.out.println(sql);
            } else {
                sql = "SELECT * FROM contatti WHERE id = " + Integer.parseInt(search);
                System.out.println(sql);
            }
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery(sql);

            while (rs.next()) {
                System.out.println("ID: " + rs.getInt(1));
                System.out.println("Nome: " + rs.getString(2));
                System.out.println("Cognome: " + rs.getString(3));
                System.out.println("Tel: " + rs.getString(4));
                System.out.println("Email: " + rs.getString(5));
                System.out.println("--------------------------");
                Contact contact = new Contact();
                contact.setId(rs.getInt(1));
                contact.setFirstName(rs.getString(2));
                contact.setLastName(rs.getString(3));
                contact.setPhoneNumber(rs.getString(4));
                contact.setEmail(rs.getString(5));
                System.out.println(contact);
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