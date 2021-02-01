package it.beije.ananke.rubrica.jdbcmanager;

import java.util.Scanner;

public class JDBCTest {
    public static void main(String[] args) {
//        JDBCManager.insert("Mario", "Alisei", "336335332", "marioalix@gmail.com");
        Scanner scanner = new Scanner(System.in);
        JDBCManager.selectAll();

//        JDBCManager.selectByField(scanner);

        JDBCManager.delete(scanner);

        scanner.close();
    }
}
