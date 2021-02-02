package it.beije.ananke.rubrica;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

public class DataBaseContact {

    public static final String DB_USER = "root";
    public static final String DB_PASSWORD = "Padawan02May4BeWithYou";
    public static final String DB_URL = "jdbc:mysql://localhost:3306/ananke?serverTimezone=CET";

    private static Connection connection;

    public static boolean openConnection() throws ClassNotFoundException, SQLException {

        connection = null;

        Class.forName("com.mysql.cj.jdbc.Driver");
        connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        return true;

    }

    public static boolean closeConnection(){

        try {
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return true;

    }

    public static ArrayList<Contact> select() throws SQLException {

        ArrayList<Contact> rubric = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM contact");
        Statement statement = null;
        ResultSet rs = null;

        statement = connection.createStatement();

        if (statement != null) {
            rs = statement.executeQuery(query.toString());
        }

        if (rs != null) {
            while (rs.next()){

                Contact contact =new Contact();
                contact.setName(rs.getString("name"));
                contact.setSurname(rs.getString("surname"));
                contact.setTelephone(rs.getString("telephone"));
                contact.setEmail(rs.getString("email"));

                rubric.add(contact);
            }
        }

        if (statement != null) {
            statement.close();
        }
        if (rs != null) {
            rs.close();
        }

        return rubric;

    }

    public static ArrayList<Contact> selectWhere(ArrayList<String> fields, ArrayList<String> values) throws SQLException {

        ArrayList<Contact> rubric = new ArrayList<>();
        StringBuilder query = new StringBuilder("SELECT * FROM contact WHERE ");
        Statement statement = null;
        ResultSet rs = null;

        statement = connection.createStatement();

        for (int i = 0; i < fields.size(); i++) {

            //TODO: controllare query
            query.append("(").append(fields.get(i)).append(" = '").append(values.get(i)).append("')");

            if(i < fields.size() - 1 )
                //ho ancora una condizione da aggiungere
                query.append(" AND ");
            else
                //non ho più condizioni da aggiungere
                query.append(";");

        }

        //stampa di debug per vedere se la query è stata costruita correttamente
        System.out.println("La query di ricerca è :" + query);

        if (statement != null) {
            rs = statement.executeQuery(query.toString());
        }

        if (rs != null) {
            while (rs.next()){

                Contact contact =new Contact();
                contact.setName(rs.getString("name"));
                contact.setSurname(rs.getString("surname"));
                contact.setTelephone(rs.getString("telephone"));
                contact.setEmail(rs.getString("email"));

                rubric.add(contact);
            }
        }

        if (statement != null) {
            statement.close();
        }
        if (rs != null) {
            rs.close();
        }

        return rubric;

    }

    public static boolean insert(ArrayList<Contact> rubric) throws SQLException {

        PreparedStatement preparedStatement = null;
        String psInsert = "INSERT INTO contact (name, surname, telephone, email) VALUES (?, ?, ?, ?)";
        preparedStatement = connection.prepareStatement(psInsert);

        for (Contact contact: rubric) {

            preparedStatement.setString(1, contact.getName());
            preparedStatement.setString(2, contact.getSurname());
            preparedStatement.setString(3, contact.getTelephone());
            preparedStatement.setString(4, contact.getEmail());

            preparedStatement.execute();

        }

        preparedStatement.close();

        return true;

    }

    public static boolean updateWhere(ArrayList<String> fields, ArrayList<String> values, ArrayList<String> modifyFields, ArrayList<String> modifyValues) throws SQLException {

        StringBuilder query = new StringBuilder("UPDATE contact SET ");
        Statement statement = null;

        statement = connection.createStatement();

        //ciclo per appendere tutti i set
        for (int i = 0; i < fields.size(); i++) {

            query.append(modifyFields.get(i)).append(" = '").append(modifyValues.get(i)).append("'");

            if(i < fields.size() - 1 )
                //ho ancora una condizione da aggiungere
                query.append(", ");
            else
                //non ho più condizioni da aggiungere
                query.append("WHERE ");

        }

        //ciclo per inserire le condizioni
        for (int i = 0; i < fields.size(); i++) {

            query.append(fields.get(i)).append(" = '").append(values.get(i)).append("'");

            if(i < fields.size() - 1 )
                //ho ancora una condizione da aggiungere
                query.append(" AND ");
            else
                //non ho più condizioni da aggiungere
                query.append(";");

        }

        System.out.println("La query di modifica: " + query);

        if (statement != null) {
            statement.executeUpdate(query.toString());
        }

        if (statement != null) {
            statement.close();
        }

        return true;
    }

    public static boolean deleteWhere(ArrayList<String> fields, ArrayList<String> values) throws SQLException {

        StringBuilder query = new StringBuilder("DELETE FROM contact WHERE ");
        Statement statement = null;

        statement = connection.createStatement();

        for (int i = 0; i < fields.size(); i++) {

            //TODO: controllare query
            query.append("(").append(fields.get(i)).append(" = '").append(values.get(i)).append("')");

            if(i < fields.size() - 1 )
                //ho ancora una condizione da aggiungere
                query.append(" AND ");
            else
                //non ho più condizioni da aggiungere
                query.append(";");

        }

        System.out.println(query);

        if (statement != null) {
            statement.executeUpdate(query.toString());
        }

        if (statement != null) {
            statement.close();
        }

        return true;
    }

}
