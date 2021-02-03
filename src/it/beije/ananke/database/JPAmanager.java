package it.beije.ananke.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.beije.ananke.rubrica.Contact;


public class JPAmanager {

	public static void main(String[] args) {
		
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Rubrica");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();
		
		EntityManager entityManager = RubricaEntityManager.getEntityManager();
		
//		Contatto contatto1 = entityManager.find(Contatto.class, 1);
//		System.out.println(contatto1);

		//JPQL
		String jpqlSelect = "SELECT c FROM Contatto as c";
		Query query = entityManager.createQuery(jpqlSelect);
		List<Contact> contatti = query.getResultList();

		for (Contact contatto : contatti) {
			System.out.println("id : " + contatto.getId());
			System.out.println("name : " + contatto.getFirstName());
			System.out.println("surname : " + contatto.getLastName());
			System.out.println("telephone : " + contatto.getPhoneNumber());
			System.out.println("email : " + contatto.getEmail());
		}

		//apro transazione
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
//		//UPDATE
//		Contatto c1 = contatti.get(contatti.size()-1);
//		c1.setTelephone("123456789");
//		
//		System.out.println("c1 ID PRE save  :" + c1.getId());
//		entityManager.persist(c1);
//		System.out.println("c1 ID POST save :" + c1.getId());
		
		//INSERT
		Contact newContatto = new Contact();
		newContatto.setFirstName("Piero");
		newContatto.setLastName("Pelù");
		newContatto.setEmail("ppelu@beije.it");
		
		System.out.println("newContatto ID PRE save  :" + newContatto.getId());
		entityManager.persist(newContatto);
		System.out.println("newContatto ID POST save :" + newContatto.getId());
		
		entityTransaction.commit();
//		entityTransaction.rollback();
		
		entityManager.close();
		
	}

}
