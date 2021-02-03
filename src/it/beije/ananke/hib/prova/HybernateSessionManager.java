package it.beije.ananke.hib.prova;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import it.beije.ananke.hib.prova.Contatto;

public class HybernateSessionManager {
	
	private HybernateSessionManager() {}
	
	private static SessionFactory sessionFactory;
	
	public static Session getSession() {
		
		if (sessionFactory == null) {
			Configuration configuration = new Configuration().configure()
					.addAnnotatedClass(Contatto.class);

			sessionFactory = configuration.buildSessionFactory();
		}
		
		return sessionFactory.openSession();
	}

}
