package it.beije.ananke.rubrica.rubricajpa.jpamanager;

import java.util.List;
import java.util.Scanner;

import it.beije.ananke.rubrica.Contact;
import it.beije.ananke.rubrica.rubricajpa.Rubrica;

public class JPATest {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		String root = "C:\\Users\\Padawan01\\IdeaProjects\\git\\Ananke\\src\\it\\beije\\ananke\\rubrica\\rubricajpa\\rubrica_.txt";
		Rubrica rubrica = new Rubrica();
		
		List<Contact> list = rubrica.importFromDB();
		for(Contact c:list) {
			System.out.println(c);
		}
		try {
			rubrica.exportToDB(scanner);
//			rubrica.exportAsCSV(root, list);
		} catch (Exception e) {
			
		}
	}
	
}
	
