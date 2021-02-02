package it.beije.ananke.rubrica;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import it.beije.ananke.Contatto;

public class HDBManager {

	public static Session getSession() {
		Configuration cfg = new Configuration().configure();
		SessionFactory sf = cfg.buildSessionFactory();
		Session s = sf.openSession();
	 return s;
	}
	
	public static void readDB() {
		String hqlSelect = "SELECT c FROM Contatto as c";
		Session session = getSession();
		Query<Contatto> query = session.createQuery(hqlSelect);
		List<Contatto> contatti = query.list();
		System.out.println(contatti.size());
	}
	
}
