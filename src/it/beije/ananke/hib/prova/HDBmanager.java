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


public class HDBmanager {
	static Scanner scanner = new Scanner(System.in);
	static Configuration configuration = new Configuration().configure()
			.addAnnotatedClass(Contatto.class);

	public static void main(String[] args) {


		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		System.out.println("session is open ? " + session.isOpen());
		String hqlSelect = "SELECT c FROM Contatto as c";
		Query<Contatto> query = session.createQuery(hqlSelect);
		List<Contatto> contatti = query.list();
		System.out.println(contatti.size());
		//lettura();
		//inserimento();
		update(contatti);


	}


	public static void lettura(){
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
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

	public static void inserimento() {

		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		System.out.println("------------------------------------------");
		System.out.println("Inserisci il nome dell' utente");
		String name = scanner.nextLine();
		System.out.println("Inserisci il cognome dell' utente");
		String surname = scanner.nextLine();
		System.out.println("Inserisci il recapito telefonico dell' utente");
		String telephone = scanner.nextLine();
		System.out.println("Inserisci l' indirizzo email dell' utente");
		String email = scanner.nextLine();	
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
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();

		Contatto c1 = contatti.get(contatti.size()-1);
		c1.setName("Pippo");
		c1.setSurname("Vredi");
		c1.setEmail("Pippo@beije.it");

		session.save(c1);

		transaction.commit();
		//transaction.rollback();

		session.close();
		sessionFactory.close();
	}
}
