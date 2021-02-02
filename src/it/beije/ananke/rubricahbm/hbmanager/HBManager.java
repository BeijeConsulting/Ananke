package it.beije.ananke.rubricahbm.hbmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.ananke.rubricahbm.Contact;

import java.util.List;

public class HBManager {
    public static void main(String[] args) {

        Configuration configuration = new Configuration().configure("hibernate.cfg.xml");

        SessionFactory sessionFactory = configuration.buildSessionFactory();

        Session session = sessionFactory.openSession();
        System.out.println("session is open ? " + session.isOpen());

        //SQL : "SELECT * FROM contatti"
        //HQL
        String hqlSelect = "SELECT c FROM Contact as c";
        Query<Contact> query = session.createQuery(hqlSelect);
        List<Contact> contatti = query.list();
        System.out.println(contatti.size());

		for (Contact contatto : query.list()) {
			System.out.println("id : " + contatto.getId());
			System.out.println("name : " + contatto.getFirstName());
			System.out.println("surname : " + contatto.getLastName());
			System.out.println("telephone : " + contatto.getPhoneNumber());
			System.out.println("email : " + contatto.getEmail());
		}

        //apro transazione
//        Transaction transaction = session.beginTransaction();

        //UPDATE
//        Contatto c1 = contatti.get(contatti.size()-1);
//        c1.setName("Pippo");
//        c1.setSurname("Vredi");
//        c1.setEmail("Pippo@beije.it");
//
//        System.out.println("c1 ID PRE save  :" + c1.getId());
//        session.save(c1);
//        System.out.println("c1 ID POST save :" + c1.getId());

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

//        transaction.commit();
//		//transaction.rollback();

        session.close();
        sessionFactory.close();
    }
}
