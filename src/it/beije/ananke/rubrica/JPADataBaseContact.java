package it.beije.ananke.rubrica;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class JPADataBaseContact {
	
	public static List<Contact> select(){
		
		EntityManager entityManager = JPAEntityManager.getEntityManager();
		
		String jpqlSelect = "SELECT c FROM Contact as c";
		Query query = entityManager.createQuery(jpqlSelect);
		List<Contact> contacts = query.getResultList();
		
		entityManager.close();
		
		return contacts;
		
	}
	
	public static List<Contact> selectWhere(List<String> fields, List<String> values){
		
		EntityManager entityManager = JPAEntityManager.getEntityManager();
		
		StringBuilder jpqlSelect = new StringBuilder("SELECT c FROM Contact as c WHERE ");
		
		for (int i = 0; i < fields.size(); i++) {

            
			jpqlSelect.append("(c.").append(fields.get(i)).append(" = '").append(values.get(i)).append("')");

			if(i < fields.size() - 1 )
				
				//ho ancora una condizione da aggiungere
				jpqlSelect.append(" AND ");	

		}
		
		Query query = entityManager.createQuery(jpqlSelect.toString());
		List<Contact> contacts = query.getResultList();
		
		
		entityManager.close();
		
		return contacts;
	}
	
public static List<Contact> selectWhere(List<String> fields, List<String> values, EntityManager entityManager){
		
		StringBuilder jpqlSelect = new StringBuilder("SELECT c FROM Contact as c WHERE ");
		
		for (int i = 0; i < fields.size(); i++) {

            
			jpqlSelect.append("(c.").append(fields.get(i)).append(" = '").append(values.get(i)).append("')");

			if(i < fields.size() - 1 )
				
				//ho ancora una condizione da aggiungere
				jpqlSelect.append(" AND ");	

		}
		
		Query query = entityManager.createQuery(jpqlSelect.toString());
		List<Contact> contacts = query.getResultList();
		
		return contacts;
	}

	public static void insert(List<Contact> rubric) {
		
		EntityManager entityManager = JPAEntityManager.getEntityManager();
		
		//apro transazione
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		for (Contact contact : rubric) {
			
			entityManager.persist(contact);
			
		}
		
		entityTransaction.commit();
		
		entityManager.close();
		
	}

	public static void updateWhere(List<String> fields, List<String> values, List<String> modifiedFields, List<String> modifiedValues) {
		
		//non si riferisce allo stesso oggetto, o almeno non lo vede perché l'enityManager è un altro!
		//quindi magari faccio che la select non apre da solo una em
		//ma poi dovrei aprire l'entityManager dentro all'altra classe.
		//fare una select che apre e chiude e viene usata dall'altra classe e una select che viene usata qui?
		//ripetizione di codice, non è bellissimo ma ora faccio così
		
		EntityManager entityManager = JPAEntityManager.getEntityManager();
		List<Contact> updateContacts = selectWhere(fields, values, entityManager);
		
		//apro transazione
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
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
			
			entityManager.persist(contact);
			
		}
		
		entityTransaction.commit();
		
		
	}

	public static void deleteWhere(List<String> fields, List<String> values) {
		
		//alla delete non piace il FROM. forse perché sa già dove deve guardare? 
		//però non si spiega perché la select non mi dice nulla
		StringBuilder jpqlDelete = new StringBuilder("DELETE Contact c WHERE ");
		
		for (int i = 0; i < fields.size(); i++) {

            
			jpqlDelete.append("c.").append(fields.get(i)).append(" = '").append(values.get(i)).append("'");

			if(i < fields.size() - 1 )
				
				//ho ancora una condizione da aggiungere
				jpqlDelete.append(" AND ");	

		}

	
		EntityManager entityManager = JPAEntityManager.getEntityManager();
		
		//apro transazione
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Query query = entityManager.createQuery(jpqlDelete.toString());
		query.executeUpdate();
		
		
		entityTransaction.commit();
		
	}
}
