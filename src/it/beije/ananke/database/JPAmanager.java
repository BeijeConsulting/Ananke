package it.beije.ananke.database;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import beije.ananke.rubrica.Contatto;
import beije.ananke.rubrica.Rubrica;
import beije.ananke.rubrica.RubricaXML;

//import it.beije.ananke.Contatto;

public class JPAmanager {

	public static void main(String[] args) throws Exception {

		Scanner input = new Scanner(System.in);

		menuDB(input);
	}

//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Rubrica");
//		EntityManager entityManager = entityManagerFactory.createEntityManager();

//		EntityManager entityManager = RubricaEntityManager.getEntityManager();

//		Contatto contatto1 = entityManager.find(Contatto.class, 1);
//		System.out.println(contatto1);

	// JPQL
//		String jpqlSelect = "SELECT c FROM Contatto as c";
//		Query query = entityManager.createQuery(jpqlSelect);
	// List<Contatto> contatti = query.getResultList();

//		for (Contatto contatto : contatti) {
//			System.out.println("id : " + contatto.getId());
//			System.out.println("name : " + contatto.getName());
//			System.out.println("surname : " + contatto.getSurname());
//			System.out.println("telephone : " + contatto.getTelephone());
//			System.out.println("email : " + contatto.getEmail());
//		}

	// apro transazione
//		EntityTransaction entityTransaction = entityManager.getTransaction();
//		entityTransaction.begin();

//		//UPDATE
//		Contatto c1 = contatti.get(contatti.size()-1);
//		c1.setTelephone("123456789");
//		
//		System.out.println("c1 ID PRE save  :" + c1.getId());
//		entityManager.persist(c1);
//		System.out.println("c1 ID POST save :" + c1.getId());

//		//INSERT
//	//	Contatto newContatto = new Contatto();
//		newContatto.setName("Piero");
//		newContatto.setSurname("Pelù");
//		newContatto.setEmail("ppelu@beije.it");
//		
//		System.out.println("newContatto ID PRE save  :" + newContatto.getId());
//		entityManager.persist(newContatto);
//		System.out.println("newContatto ID POST save :" + newContatto.getId());
//		
//		entityTransaction.commit();
////		entityTransaction.rollback();
//		
//		entityManager.close();

//	}

	public static void menuDB(Scanner input) throws Exception {
		String result;

		System.out.println("Sei nel menu del database. Scegli una funzionalita:");

		do {

			System.out.println("[1] Inserisci un nuovo contatto\n[2] Modifica un contatto\n[3] Cerca un contatto\n[4] "
					+ "Elimina un contatto\n[5] Esporta rubrica su file xml o csv\n[6] Importa una rubrica su db."
					+ "\n[7] Esci");

			result = input.nextLine();

			switch (result) {
			case "1":
				JPAmanager.aggiungiContatto(input);
				break;
			case "2":
				JPAmanager.modificaContatto(input);
				break;
			case "3":
				JPAmanager.cercaContatto(input);
				break;
			case "4":
				JPAmanager.eliminaContatto(input);
				break;
			case "5":
				JPAmanager.exportDB(input);
				break;
			case "6":
				JPAmanager.importOnDB(input);
				break;
			case "7":
				System.out.println("Uscita database..");
				break;
			}
		} while (!result.equals("7"));

	}

	public static void aggiungiContatto(Scanner input) {
		EntityManager entityManager = RubricaEntityManager.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		String val;
		Contatto cont = new Contatto();
		System.out.println("Inserisci il nome o 0 per lasciarlo vuoto:");
		val = input.nextLine();
		if (!val.equals("0"))
			cont.setName(val);
		System.out.println("Inserisci il cognome o 0 per lasciarlo vuoto:");
		val = input.nextLine();
		if (!val.equals("0"))
			cont.setLastName(val);
		System.out.println("Inserisci il numero di telefono o 0 per lasciarlo vuoto:");
		val = input.nextLine();
		if (!val.equals("0"))
			cont.setPhone(val);
		System.out.println("Inserisci l'email  0 per lasciarlo vuoto:");
		val = input.nextLine();
		if (!val.equals("0"))
			cont.setEmail(val);

		if (!(cont.getEmail().trim().length() == 0 && cont.getName().trim().length() == 0
				&& cont.getLastName().trim().length() == 0 && cont.getPhone().trim().length() == 0)) {
			// opening the transaction
			entityTransaction.begin();
			entityManager.persist(cont);
			entityTransaction.commit();
			entityManager.close();
			System.out.println("Contatto aggiunto al db");
		} else {
			System.out.println("Devi inserire almeno un campo. Ritorno al menu..");
			entityTransaction.rollback();
			entityManager.close();
			// sessionFactory.close();
		}

	}

	public static List<Contatto> cercaContatto(Scanner input) {
		EntityManager entityManager = RubricaEntityManager.getEntityManager();

		String val;
		System.out.println("Inserisci un campo(nome,cognome,email,ecc) per effettuare la ricerca:");
		val = input.nextLine();
		String JPASelect = "SELECT c FROM Contatto as c WHERE name='" + val + "' OR surname='" + val
				+ "' OR telephone='" + val + "' OR email='" + val + "'";
		Query query = entityManager.createQuery(JPASelect);
		List<Contatto> contatti = query.getResultList();

		if (contatti.isEmpty()) {
			System.out.println("Nessun contatto trovato.");
			entityManager.close();
			return null;
		}

		System.out.println("Contatti trovati:");
		for (Contatto contatto : contatti) {
			System.out.println("id : " + contatto.getId());
			System.out.println("name : " + contatto.getName());
			System.out.println("surname : " + contatto.getLastName());
			System.out.println("telephone : " + contatto.getPhone());
			System.out.println("email : " + contatto.getEmail());

			entityManager.close();
		}
		return contatti;

	}

	public static void modificaContatto(Scanner input) {

		List<Contatto> contatti = cercaContatto(input);
		if (!contatti.isEmpty()) {

			EntityManager entityManager = RubricaEntityManager.getEntityManager();
			int id;
			String result;
			EntityTransaction entityTransaction = entityManager.getTransaction();

			System.out.println("Inserisci l'id del contatto da modificare:");
			id = Integer.parseInt(input.nextLine());

			Contatto contatto = entityManager.find(Contatto.class, id);
//			String JPASelect = "SELECT c FROM Contatto as c WHERE id=" + id;
//			Query query = entityManager.createQuery(JPASelect);
//			contatti = query.getResultList();
//			Contatto cont = null;
//			if (contatti.size() > 0) {
//				cont = contatti.get(0);

				System.out.println("Inserisci il campo che vuoi modificare:");
				result = input.nextLine();

				switch (result) {
				case "name":
					System.out.println("Inserisci il nuovo campo:");
					result = input.nextLine();
					entityTransaction.begin();
					contatto.setName(result);
					entityManager.persist(contatto);
					entityTransaction.commit();
					break;

				case "surname":
					System.out.println("Inserisci il nuovo campo:");
					result = input.nextLine();
					entityTransaction.begin();
					contatto.setLastName(result);
					entityManager.persist(contatto);
					entityTransaction.commit();
					break;

				case "telephone":
					System.out.println("Inserisci il nuovo campo:");
					result = input.nextLine();
					entityTransaction.begin();
					contatto.setPhone(result);
					entityManager.persist(contatto);
					entityTransaction.commit();
					break;

				case "email":
					System.out.println("Inserisci il nuovo campo:");
					result = input.nextLine();
					entityTransaction.begin();
					contatto.setEmail(result);
					entityManager.persist(contatto);
					entityTransaction.commit();
					break;

				}
			
			entityManager.close();
		} else {
			return;
		}
	}

	public static void eliminaContatto(Scanner input) {

		cercaContatto(input);

		EntityManager entityManager = RubricaEntityManager.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		int id;
		String result;

		System.out.println("Inserisci l'id del contatto da modificare:");
		id = Integer.parseInt(input.nextLine());

		Contatto contatto = entityManager.find(Contatto.class, id);
		

		entityTransaction.begin();
		entityManager.remove(contatto);
		entityTransaction.commit();
		System.out.println("Contatto eliminato");
		entityManager.close();
	}

	public static void exportDB(Scanner input) throws Exception {
		EntityManager entityManager = RubricaEntityManager.getEntityManager();
		String result, last3, xml = "xml", csv = "txt";
		String JPASelect = "SELECT c FROM Contatto as c";
		Query query = entityManager.createQuery(JPASelect);
		List<Contatto> contatti = query.getResultList();

		System.out.println("Inserisci il path in cui esportare la rubrica:");
		result = input.nextLine();
		last3 = result.substring(result.length() - 3);

		if (last3.equals(xml)) {
			RubricaXML.writeContactXML((ArrayList<Contatto>) contatti, result);
		} else if (last3.equals(csv)) {
			Rubrica.writeContacts(new File(result), (ArrayList<Contatto>) contatti);
		}

		entityManager.close();
	}

	public static void importOnDB(Scanner input) throws ParserConfigurationException, SAXException, IOException {
		String path, last3, xml = "xml", csv = "txt";
		System.out.println("Inserisci il path della rubrica da importare:");
		path = input.nextLine();
		last3 = path.substring(path.length() - 3);
		ArrayList<Contatto> contatti = null;

		if (last3.equals(xml)) {
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			Document document = builder.parse(path);
			contatti = RubricaXML.readContactsXML(document);
		} else if (last3.equals(csv)) {
			contatti = Rubrica.readContacts(new File(path));
		}

		EntityManager entityManager = RubricaEntityManager.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		for (Contatto con : contatti) {
			entityTransaction.begin();
			entityManager.persist(con);
			entityTransaction.commit();
		}
		System.out.println("Rubrica importata su db.");

		entityManager.close();
	}

}
