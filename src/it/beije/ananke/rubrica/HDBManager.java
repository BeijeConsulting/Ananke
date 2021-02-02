package it.beije.ananke.rubrica;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.ananke.Contatto;

public class HDBManager {
	private static SessionFactory sf;
	
	public static void getSessionFactory() {
		Configuration cfg = new Configuration().configure();
		 sf = cfg.buildSessionFactory();
		
	 
	}
	public static Session getSession() {
		getSessionFactory();
		Session s = sf.openSession();
	 return s;
	}
	
	
	public static SessionFactory getSf() {
		return sf;
	}
	public static void readDB() {
		String hqlSelect = "SELECT c FROM Contatti as c";
		Session session = getSession();
		Query<Contatto> query = session.createQuery(hqlSelect);
		List<Contatto> contatti = query.list();
		System.out.println(contatti);
		session.close();
		
	}
	
	public static void addContact(List<Contatti> l) {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		for(Contatti c: l) {
		session.save(c);
		}
		transaction.commit();
		session.close();
	}
	
	public static List<Contatti> containContacts (String campo){
		String hqlSelect = "SELECT c FROM Contatti as c where email =: email ";
		
		Session session = getSession();
		Query<Contatti> query = session.createQuery(hqlSelect);
		query.setParameter("email", campo);
		List<Contatti> contatti = query.list();
		System.out.println(contatti);
		session.close();
		return contatti;
	}
	
	public static void deleteContacts(String campo) {
		List<Contatti> l = new ArrayList<>();
		l = containContacts(campo);
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		for(Contatti c: l) {
			session.delete(c);
		}
		transaction.commit();
		session.close();
	}
	
}
