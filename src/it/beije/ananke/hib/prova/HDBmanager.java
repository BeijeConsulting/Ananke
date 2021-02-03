package it.beije.ananke.hib.prova;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.hibernate.sql.Update;
import it.beije.ananke.hib.prova.Contatto;

public class HDBmanager {
	static Scanner scanner = new Scanner(System.in);
	//	static Configuration configuration = new Configuration().configure()
	//			.addAnnotatedClass(Contatto.class);

	public static void main(String[] args) {

		Session session = HybernateSessionManager.getSession();
		System.out.println("session is open ? " + session.isOpen());
		String hqlSelect = "SELECT c FROM Contatto as c";
		Query<Contatto> query = session.createQuery(hqlSelect);
		List<Contatto> contatti = query.list();
		System.out.println("Il numero di contatti è " + contatti.size());
		//lettura();
		//inserimento();
		update(contatti);
		//delete(contatti);
		//ricerca();
	}

	public static void lettura(){
		Session session = HybernateSessionManager.getSession();
		String hqlSelect = "SELECT c FROM Contatto as c";
		Query<Contatto> query = session.createQuery(hqlSelect);
		List<Contatto> contatti = query.list();
		System.out.println(contatti.size());

		for (Contatto contatto : query.list()) {
			System.out.println("id : " + contatto.getId());
			System.out.println("name : " + contatto.getName());
			System.out.println("surname : " + contatto.getSurname());
			System.out.println("telephone : " + contatto.getTelephone());
			System.out.println("email : " + contatto.getEmail());
		}
	}

	public static void ricerca() {
		Session session = HybernateSessionManager.getSession();
		System.out.println("Inserisci la mail del contatto da ricercare");
		String m = scanner.nextLine();									
		String hqlSelect = "SELECT c FROM Contatto as c where email = '" + m +"' ";
		Query<Contatto> query = session.createQuery(hqlSelect);
		List<Contatto> contatti = query.list();

		for (Contatto contatto : query.list()) {
			if(contatto.getEmail().equalsIgnoreCase(m)) {
				System.out.println("ELEMENTO TROVATO: ");
				System.out.println("id : " + contatto.getId());
				System.out.println("name : " + contatto.getName());
				System.out.println("surname : " + contatto.getSurname());
				System.out.println("telephone : " + contatto.getTelephone());
				System.out.println("email : " + contatto.getEmail());
			}
		}
	}

	public static void inserimento() {

		Session session = HybernateSessionManager.getSession();

		System.out.println("------------------------------------------");
		System.out.println("Inserisci il nome dell' utente");
		String name = scanner.nextLine();
		System.out.println("Inserisci il cognome dell' utente");
		String surname = scanner.nextLine();
		System.out.println("Inserisci il recapito telefonico dell' utente");
		String telephone = scanner.nextLine();
		System.out.println("Inserisci l' indirizzo email dell' utente");
		String email = scanner.nextLine();	

		Transaction transaction = session.beginTransaction();
		Contatto newContatto = new Contatto();
		newContatto.setName(name);
		newContatto.setSurname(surname);
		newContatto.setTelephone(telephone);
		newContatto.setEmail(email);
		System.out.println("Il contatto è stato inserito");
		System.out.println("------------------------------------------");
		session.save(newContatto);
		transaction.commit();

	}
	public static void update(List<Contatto> contatti) {
		Session session = HybernateSessionManager.getSession();
		Transaction transaction = session.beginTransaction();
		Contatto c1;
		System.out.println("Inserisci la mail del contatto da modificare");
		String m = scanner.nextLine();
		boolean b = false;
		for(int i=0;i<contatti.size();i++) {
			if(contatti.get(i).getEmail().equalsIgnoreCase(m)) {
				System.out.println("Inserisci il nome dell' utente");
				String name = scanner.nextLine();
				System.out.println("Inserisci il cognome dell' utente");
				String surname = scanner.nextLine();
				System.out.println("Inserisci il recapito telefonico dell' utente");
				String telephone = scanner.nextLine();
				System.out.println("Inserisci l' indirizzo email dell' utente");
				String email = scanner.nextLine();	
				c1=contatti.get(i);	
				c1.setName(name);
				c1.setSurname(surname);
				c1.setTelephone(telephone);
				c1.setEmail(email);
				if(c1.getEmail().equals("")) {
					b=false;
				}
				else {
					b=true;
					session.update(c1);
				}
			}
		}
		if(b=true)
		transaction.commit();
		else
			transaction.rollback();

	}
	public static void delete(List<Contatto> contatti) {
		Session session = HybernateSessionManager.getSession();
		Transaction transaction = session.beginTransaction();
		Contatto c1;
		System.out.println("Inserisci la mail del contatto da eliminare");
		String m = scanner.nextLine();
		for(int i=0;i<contatti.size();i++) {
			if(contatti.get(i).getEmail().equalsIgnoreCase(m)) {
				c1 = contatti.get(i);
				contatti.remove(i);
				session.delete(c1);	
			}
		}
		System.out.println("Contatto eliminato");
		transaction.commit();
		//transaction.rollback();
	}

}
