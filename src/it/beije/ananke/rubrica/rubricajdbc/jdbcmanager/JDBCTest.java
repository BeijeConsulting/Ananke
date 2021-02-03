package it.beije.ananke.rubrica.rubricajdbc.jdbcmanager;

import it.beije.ananke.rubrica.rubricajdbc.Rubrica;

import java.util.Scanner;

public class JDBCTest {
    public static void main(String[] args) {
//        JDBCManager.insert("Mario", "Alisei", "336335332", "marioalix@gmail.com");
        Scanner scanner = new Scanner(System.in);
//        JDBCManager.selectAll();

//        JDBCManager.selectByField("first_name", "ste");

//        JDBCManager.delete(scanner);
//        JDBCManager.insert("Gianni", "Caccino", "3385865695", "g.cacci@gmail.com");

        Rubrica rubrica = new Rubrica();
//        try {
//            rubrica.exportToDB(scanner);
//        } catch (IOException e) {
//
//        } catch (ParserConfigurationException e) {
//
//        }   catch (SAXException e) {
//
//        }
        scanner.close();
    }
}
