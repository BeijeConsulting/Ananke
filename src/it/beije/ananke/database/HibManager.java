package it.beije.ananke.database;

import it.beije.ananke.file.ContattoMio;
import it.beije.ananke.file.Rubrica;
import it.beije.ananke.Singleton;

import java.io.IOException;
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

	public static void main(String[] args) throws IOException {
		HibManager hib = new HibManager();
		
		DataManager.menu();
		Scanner s = new Scanner(System.in);
		String st = s.next();

		while (!st.equalsIgnoreCase("5")) {
			hib.choice(st, s);
			DataManager.menu();
			st = s.next();
		}
		
		
		System.out.println("Arrivederci!");
		s.close();
	
	}
	
	
	public void choice(String i, Scanner s) throws IOException {
		Session session = HybernateSessionManager.getSession();
		switch(i) {
			case "0": lettura(session); break;
			case "1": inserisci(session, s); break;
			case "2": modifica(session, s); break;
			case "3": eliminazione(session, s); break;
			case "4": ricerca(session, s); break;
			case "5": break;
			case "6": break;
			default: System.out.println("L'opzione selezionata non è valida.");
		}
		session.close();
	}
	
	
	public void stampa(List<ContattoMio> contatti) {
		if(contatti.size() != 0) {
			for(ContattoMio c : contatti) {
				System.out.println(c.toString());
			}
		}else {
			System.out.println("La rubrica è vuota.");
		}
	}
	
	
	public List<ContattoMio> lettura(Session session){
		List<ContattoMio> contatti = new ArrayList<>();
		String hqlSelect = "SELECT c FROM Contatto as c";
		Query<ContattoMio> query = session.createQuery(hqlSelect);
		contatti = query.list();
		stampa(contatti);
		return contatti;
	}
	
	
	public List<ContattoMio> scan(Session session, Scanner s){
		System.out.println("Scegliere in quale campo cercare:\n"
				+ "0: ID\n1: NOME\n2: COGNOME\n3: EMAIL\n4: TELEFONO\n"
				+ "(Attenzione il numero di telefono deve contenere eventuali prefissi)");
		String str = s.next();
		List<ContattoMio> contatti = new ArrayList<>();
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
			Query<ContattoMio> query = session.createQuery(select);
			contatti = query.list();
			
		} catch (NumberFormatException e) {
			System.out.println("Il valore inserito non è riconosciuto, sarai reindirizzato al menù.");
		}
		
		return contatti;
	}
	
	
	public void ricerca(Session session, Scanner s) {
		List<ContattoMio> contatti = scan(session, s);
		if(contatti.size() == 0) {
			System.out.println("Non ci sono elementi che corrispondono ai criteri di ricerca.");
		}else {
			stampa(contatti);
		}
	}
	
	
	public void inserisci(Session session, Scanner s) throws IOException {
		Transaction transaction = session.beginTransaction();
		ContattoMio contatto = r.defContatto(s);
		session.save(contatto);
		transaction.commit();
		System.out.println("La voce è stata inserita correttamente.");
	}
	
	
	public void eliminazione(Session session, Scanner s) {
		List<ContattoMio> contatti = scan(session, s);
		if(contatti.size() == 0) {
			System.out.println("Non ci sono elementi che corrispondono ai criteri di ricerca.");
		}else {
			for(ContattoMio c : contatti) {
				Transaction transaction = session.beginTransaction();
				session.delete(c);
				transaction.commit();
				System.out.println("I campi selezionai sono stati eliminati.");
			}
		}
	}
	
	
	public void modifica(Session session, Scanner s) {
		List<ContattoMio> contatti = scan(session, s);
		if(contatti.size() == 0) {
			System.out.println("Non ci sono elementi che corrispondono ai criteri di ricerca.");
		}else {
			stampa(contatti);
			System.out.println("Scegliere il campo da modificare: \n"
					+ "1: NOME\n2: COGNOME\n3: EMAIL\n4: TELEFONO\n"
					+ "(Attenzione il numero di telefono deve contenere eventuali prefissi)");
			String str = s.next();
			
			try {
				int i = Integer.parseInt(str);
				while(i <= 0 || i > 4) {
					System.out.println("Scelta non valida. Inserire un nuovo valore.");
					str = s.next();
					i = Integer.parseInt(str);
				}
				
				System.out.println("Inserire il nuovo valore da salvare: ");
				String nuovo = s.next();
				
				for(ContattoMio c : contatti) {
					Transaction transaction = session.beginTransaction();
					switch(i) {
						case 1: c.setName(nuovo); break;
						case 2: c.setSurname(nuovo); break;
						case 3: c.setEmail(nuovo); break;
						case 4: c.setTelephone(nuovo); break;
					}	
					session.save(c);
					transaction.commit();
					System.out.println("Le modifiche sono state salvate.");
				}
				
			}catch(NumberFormatException e) {
				e.printStackTrace();
				System.out.println("Il valore inserito non è un numero. Le modifiche non saranno salvate.");
			}
			
			
		}
	}
	
	
	
	
	
	
	
	
}
