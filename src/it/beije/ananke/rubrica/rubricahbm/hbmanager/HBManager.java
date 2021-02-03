package it.beije.ananke.rubrica.rubricahbm.hbmanager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import it.beije.ananke.rubrica.Contact;

import java.util.List;

public class HBManager {
	
	public static void insert(Contact contact) {
		Session session = HybernateSessionManager.getSession();
		Transaction transaction = session.beginTransaction();
		
		session.save(contact);
		transaction.commit();

	}
	
	public static void remove(Contact contact) {
		Session session = HybernateSessionManager.getSession();
		Transaction transaction = session.beginTransaction();
		
		session.remove(contact);
		transaction.commit();
	}
	
	public static void update(Contact contact) {
		Session session = HybernateSessionManager.getSession();
		Transaction transaction = session.beginTransaction();
		
		session.update(contact);
		transaction.commit();
	}
	
	public static List<Contact> selectAll() {
		Session session = HybernateSessionManager.getSession();
		
		System.out.println("Session is opened? " + session.isOpen());
		
		String hqlSelect = "SELECT c FROM Contact as c";
		Query<Contact> query = session.createQuery(hqlSelect);
		List<Contact> contacts = query.list();
		System.out.println(contacts.size());
		return contacts;
	}
	
	public static List<Contact> selectByField(String field, String search) {		
		Session session = HybernateSessionManager.getSession();
		String hqlSelect = "SELECT c FROM Contact as c WHERE " + field +" = '" + search + "'";
		List<Contact> contacts = session.createQuery(hqlSelect).list();
		
		for(Contact c : contacts) {
			System.out.println(c);
		}
		return contacts;
	}
	
    public static void main(String[] args) {

        List<Contact> contacts = selectByField("last_name","Alessi");
//        Contact contact = new Contact();
//        contact.setFirstName("Giulio");
//        contact.setLastName("Meloni");
//        insert(contact);

    }
}
