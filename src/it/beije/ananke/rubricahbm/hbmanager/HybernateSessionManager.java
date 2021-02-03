package it.beije.ananke.rubricahbm.hbmanager;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import it.beije.ananke.rubricahbm.Contact;

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
