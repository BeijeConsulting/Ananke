package it.beije.ananke.database;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.beije.ananke.rubrica.Contatto;


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
		List<Contatto> contatti = query.getResultList();

		for (Contatto contatto : contatti) {
			System.out.println("id : " + contatto.getId());
			System.out.println("name : " + contatto.getNome());
			System.out.println("surname : " + contatto.getCognome());
			System.out.println("telephone : " + contatto.getNumeroTel());
			System.out.println("email : " + contatto.getMail());
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
		Contatto newContatto = new Contatto();
		newContatto.setNome("Piero");
		newContatto.setCognome("Pelù");
		newContatto.setMail("ppelu@beije.it");
		
		System.out.println("newContatto ID PRE save  :" + newContatto.getId());
		entityManager.persist(newContatto);
		System.out.println("newContatto ID POST save :" + newContatto.getId());
		
		entityTransaction.commit();
//		entityTransaction.rollback();
		
		entityManager.close();
		
	}

}
