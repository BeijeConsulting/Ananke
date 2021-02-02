package it.beije.ananke.database;

import it.beije.ananke.file.Contatto;
import it.beije.ananke.file.Rubrica;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HibManager {
	public Rubrica r = new Rubrica();

	public static void main(String[] args) {
		HibManager hib = new HibManager();
		Configuration configuration = new Configuration().configure();
		SessionFactory sf = configuration.buildSessionFactory();
		
		menu();
		Scanner s = new Scanner(System.in);
		String st = s.next();

		while (!st.equalsIgnoreCase("5")) {
			hib.choice(st, s, sf);
			menu();
			st = s.next();
		}
		
		
		System.out.println("Arrivederci!");
		s.close();
		sf.close();
	
	}
	
	
	public static void menu() {
		System.out.println("Benvenuto nella tua rubrica! Cosa vuoi fare?");
		System.out.println("-Digita 0 per vedere lo stato della tua rubrica");
		System.out.println("-Digita 1 per aggiungere una nuova voce");
		System.out.println("-Digita 2 per modificare una voce");
		System.out.println("-Digita 3 per cancellare una voce");
		System.out.println("-Digita 4 per cercare un contatto");
		System.out.println("-Digita 5 per uscire");
		System.out.println("-Digita 6 per ristampare il menù");
	}
	
	
	public void choice(String i, Scanner s, SessionFactory sf) {
		Session session = sf.openSession();
		
		switch(i) {
			case "0": stampa(sf); break;
			case "1": break;
			case "2": break;
			case "3": break;
			case "4": break;
			case "5": break;
			case "6": break;
			default: System.out.println("L'opzione selezionata non è valida.");
		}
		
		session.close();
	}
	
	
	public void stampa(SessionFactory sf) {
		List<Contatto> contatti = lettura(sf);
		if(contatti.size() != 0) {
			for(Contatto c : contatti) {
				c.toString();
			}
		}else {
			System.out.println("La rubrica è vuota.");
		}
	}
	
	
	public List<Contatto> lettura(SessionFactory sf){
		Session session = sf.openSession();
		String hqlSelect = "SELECT c FROM Contatto as c";
		Query<Contatto> query = session.createQuery(hqlSelect);
		List<Contatto> contatti = query.list();
		return contatti;
	}
	
	
	public List<Contatto> scan(SessionFactory sf, Scanner s){
		Session session = sf.openSession();
		System.out.println("Scegliere in quale campo cercare:\n"
				+ "0: ID\n 1: NOME\n2: COGNOME\n3: EMAIL\n4: TELEFONO\n"
				+ "(Attenzione il numero di telefono deve contenere eventuali prefissi)");
		String str = s.next();
		List<Contatto> contatti = new ArrayList<>();
		try {
			int indice = Integer.parseInt(str);
			
			while(indice >= 5) {
				System.out.println("Voce non valida, provare di nuovo.");
				str = s.next();
				indice = Integer.parseInt(str);
			}
			String search = "";
			
			switch(indice) {
				case 0: search = "id"; break;
				case 1: search = "name"; break;
				case 2: search = "surname"; break;
				case 3: search = "email"; break;
				case 4: search = "telephone"; break;
			}
			
			System.out.println("Inserire il valore da cercare: ");
			str = s.next();
			
			String select = "FROM Contatto c WHERE c." + search + " = '" + str + "'";
			Query<Contatto> query = session.createQuery(select);
			contatti = query.list();
			
		} catch (NumberFormatException e) {
			System.out.println("Il valore inserito non è riconosciuto, sarai reindirizzato al menù.");
		}
		
		return contatti;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
