package it.beije.ananke.rubrica;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class HDataBaseContact {
	
	public static Session session;
	
	public static boolean openSession() {
		
		session = HybernateSessionManager.getSession();
		
		return session.isOpen();
	
	}
	
	public static boolean closeSession() {
		
		session.close();
		
		return session.isOpen();
		
	}
	
	public static List<Contact> select(){
		
		String hqlSelect = "SELECT c FROM Contact as c";
		Query<Contact> query = session.createQuery(hqlSelect);
		List<Contact> contacts = query.list();
		
		return contacts;
		
	}
	
	public static List<Contact> selectWhere(List<String> fields, List<String> values) {
		
		StringBuilder hqlSelect = new StringBuilder("SELECT c FROM Contact as c WHERE");
		
		for (int i = 0; i < fields.size(); i++) {

	            
			hqlSelect.append("(c.").append(fields.get(i)).append(" = '").append(values.get(i)).append("')");

			if(i < fields.size() - 1 )
				
				//ho ancora una condizione da aggiungere
				hqlSelect.append(" AND ");	

		}
		
		Query<Contact> query = session.createQuery(hqlSelect.toString());
		List<Contact> contacts = query.list();
		
		return contacts;
		
	}
	
	public static void insert(List<Contact> rubric) {
		
		//apro transazione
		Transaction transaction = session.beginTransaction();
				
		for (Contact contact : rubric) {
			
			session.save(contact);
			
		}
		
		transaction.commit();
		
	}
	
	public static void updateWhere(List<String> fields, List<String> values, List<String> modifiedFields, List<String> modifiedValues) {
		
		List<Contact> updateContacts = selectWhere(fields, values);
		
		//apro transazione
		Transaction transaction = session.beginTransaction();
		
		for (Contact contact : updateContacts) {
			
			for (int i = 0; i < modifiedFields.size(); i++) {
				
				switch(modifiedFields.get(i)) {
				
					case "name":
						
						contact.setName(modifiedValues.get(i));
						
						break;
						
					case "surname":
						
						contact.setSurname(modifiedValues.get(i));
						
						break;
						
					case "telephone":
						
						contact.setTelephone(modifiedValues.get(i));
						
						break;
						
					case "email":
						
						contact.setEmail(modifiedValues.get(i));
						
						break;
						
				
				}
				
			}
			
			session.save(contact);
			
		}
		
		transaction.commit();
		
	}
	
	public static void deleteWhere(List<String> fields, List<String> values) {
		
		//apro transazione
		Transaction transaction = session.beginTransaction();
		
		StringBuilder hqlDelete = new StringBuilder("DELETE FROM Contact WHERE");
		
		for (int i = 0; i < fields.size(); i++) {

	            
			hqlDelete.append("(").append(fields.get(i)).append(" = '").append(values.get(i)).append("')");

			if(i < fields.size() - 1 )
				
				//ho ancora una condizione da aggiungere
				hqlDelete.append(" AND ");	

		}
		
		Query query = session.createQuery(hqlDelete.toString());
		query.executeUpdate();
		
		transaction.commit();
		
	}
	
}
