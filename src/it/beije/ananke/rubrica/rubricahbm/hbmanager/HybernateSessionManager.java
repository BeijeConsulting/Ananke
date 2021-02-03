package it.beije.ananke.rubrica.rubricahbm.hbmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import it.beije.ananke.rubrica.Contact;

public class HybernateSessionManager {
	
	private HybernateSessionManager() {}
	
	private static SessionFactory sessionFactory;
	
	public static Session getSession() {
		
		if (sessionFactory == null) {
			Configuration configuration = new Configuration().configure()
					.addAnnotatedClass(Contact.class);

			sessionFactory = configuration.buildSessionFactory();
		}
		
		return sessionFactory.openSession();
	}

}
