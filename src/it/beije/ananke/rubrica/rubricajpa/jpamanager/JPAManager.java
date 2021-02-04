package it.beije.ananke.rubrica.rubricajpa.jpamanager;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import it.beije.ananke.database.RubricaEntityManager;
import it.beije.ananke.rubrica.Contact;
import it.beije.ananke.rubrica.rubricajpa.Rubrica;

public class JPAManager {
//	public static EntityManager entityManager = RubricaEntityManager.getEntityManager();

	public static void insert(Contact contact) {
		EntityManager entityManager = RubricaEntityManager.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(contact);
		entityTransaction.commit();
		entityManager.close();

	}

	public static void update(Contact contact) {
		EntityManager entityManager = RubricaEntityManager.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.persist(entityManager.find(Contact.class, contact.getId()));
		entityTransaction.commit();
		entityManager.close();
	}

	public static void remove(Contact contact) {
		EntityManager entityManager = RubricaEntityManager.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		entityManager.remove(entityManager.find(Contact.class, contact.getId()));
		entityTransaction.commit();
		entityManager.close();
	}

	public static List<Contact> selectAll() {
		EntityManager entityManager = RubricaEntityManager.getEntityManager();

		String jpqlSelect = "SELECT c FROM Contact as c";
		Query query = entityManager.createQuery(jpqlSelect);
		List<Contact> contacts = query.getResultList();

		for (Contact contact : contacts) {
			System.out.println(contact);
		}

		entityManager.close();
		return contacts;
	}

	public static List<Contact> selectByField(String field, String search) {
		EntityManager entityManager = RubricaEntityManager.getEntityManager();
		List<Contact> contacts = new ArrayList<Contact>();
		if (!field.equalsIgnoreCase("id")) {
			String jpqlSelect = "SELECT c FROM Contact as c WHERE " + field + " = '" + search + "'";
			Query query = entityManager.createQuery(jpqlSelect);
			contacts = query.getResultList();
			for (Contact contact : contacts) {
				System.out.println(contact);
			}
		} else {
			Contact contact = entityManager.find(Contact.class, Integer.parseInt(search));
			contacts.add(contact);
		}
		return contacts;
	}

}
