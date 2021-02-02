package it.beije.ananke.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.ananke.rubrica.Contatto;


public class HDBmanager {

	public static void main(String[] args) {
		
		Configuration configuration = new Configuration().configure();

		SessionFactory sessionFactory = configuration.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		System.out.println("session is open ? " + session.isOpen());
		
		//SQL : "SELECT * FROM contatti"
		//HQL
		String hqlSelect = "SELECT c FROM Contatto as c";
		Query<Contatto> query = session.createQuery(hqlSelect);
		List<Contatto> contatti = query.list();
		System.out.println(contatti.size());
//		
//		for (Contatto contatto : query.list()) {
//			System.out.println("id : " + contatto.getId());
//			System.out.println("name : " + contatto.getName());
//			System.out.println("surname : " + contatto.getSurname());
//			System.out.println("telephone : " + contatto.getTelephone());
//			System.out.println("email : " + contatto.getEmail());
//		}
		
		//apro transazione
		Transaction transaction = session.beginTransaction();
		
		//UPDATE
		Contatto c1 = contatti.get(contatti.size()-1);
		c1.setNome("Pippo");
		c1.setCognome("Vredi");
		c1.setMail("Pippo@beije.it");
		
	
		session.save(c1);
	

//		//INSERT
//		Contatto newContatto = new Contatto();
//		//DA NON FARE newContatto.setId(13);
//		newContatto.setName("Stefano");
//		newContatto.setSurname("Savallo 2");
//		newContatto.setEmail("s.savallo2@beije.it");
//		
//		System.out.println("newContatto ID PRE save  :" + newContatto.getId());
//		session.save(newContatto);
//		System.out.println("newContatto ID POST save :" + newContatto.getId());
		
		transaction.commit();
//		//transaction.rollback();
		
		session.close();
		sessionFactory.close();
	}

}
