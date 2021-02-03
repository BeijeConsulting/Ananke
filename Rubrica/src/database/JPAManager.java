package database;

import java.sql.SQLException;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import entità.Contatto;

public class JPAManager {

		
	EntityManager entityManager = RubricaEntityManager.getEntityManager();
	
	public void chiudiConnessioneDb() {
		
		entityManager.close();
		
	}
	
	public void inserisciContattoDb(Contatto tempContatto) {
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		String nome = tempContatto.getName();
		
		if(nome.contentEquals(".")) {
			tempContatto.setName(null);
		}
		
		String cognome = tempContatto.getSurname();
		
		if(cognome.contentEquals(".")) {
			tempContatto.setSurname(null);
		}
		
		String telefono = tempContatto.getTelephone();
		
		if(telefono.contentEquals(".")) {
			tempContatto.setTelephone(null);
		}
		
		String email = tempContatto.getEmail();
		
		if(email.contentEquals(".")) {
			tempContatto.setEmail(null);
		}
		
		entityManager.persist(tempContatto);
			
		entityTransaction.commit();
		
	}
	
	public Contatto cercaContattoDb(String nome, String cognome) {
		
		String jpqlSelect = "SELECT c FROM Contatto as c WHERE c.name = '" + nome + "' and c.surname = '"+ cognome +"'";
		
		Query query = entityManager.createQuery(jpqlSelect);
		List<Contatto> contatti = query.getResultList();
		
		System.out.println("Contatti trovati: " + contatti.size());
		
		if (contatti.size() != 0) {
			for (Contatto contatto : contatti) {
				System.out.println("id : " + contatto.getId());
				System.out.println("name : " + contatto.getName());
				System.out.println("surname : " + contatto.getSurname());
				System.out.println("telephone : " + contatto.getTelephone());
				System.out.println("email : " + contatto.getEmail());
				System.out.println("------------------");
			}
		}
		
		if (contatti.size() != 0) {
			return contatti.get(0);
		}
		
		return null;
		
	}
	
	public void eliminaContattoDb(String nome, String cognome) {
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Contatto contatto = cercaContattoDb(nome, cognome);

		entityManager.remove(contatto);

	    System.out.println("Il contatto è stato eliminato con successo");
	    
	    entityTransaction.commit();
	     
	}
	
	public void modificaNomeContattoDb(Contatto contatto, String nomeNuovo) throws SQLException {
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		contatto.setName(nomeNuovo);
		
		entityManager.persist(contatto);
		
		entityTransaction.commit();
		
	}
	
	
	public void modificaCognomeContattoDb(Contatto contatto, String cognomeNuovo) throws SQLException {
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		contatto.setSurname(cognomeNuovo);
		
		entityManager.persist(contatto);
		
		entityTransaction.commit();
		
	}
	
	public void modificaTelefonoContattoDb(Contatto contatto, String telefonoNuovo) throws SQLException {
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		contatto.setTelephone(telefonoNuovo);
		
		entityManager.persist(contatto);
		
		entityTransaction.commit();
		
	}
	
	public void modificaEmailContattoDb(Contatto contatto, String emailNuova) throws SQLException {
		
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		contatto.setEmail(emailNuova);
		
		entityManager.persist(contatto);
		
		entityTransaction.commit();
		
	}
	
	public void visualizzaContattiDb() throws SQLException {
		
		String jpqlSelect = "SELECT c FROM Contatto as c";
		
		Query query = entityManager.createQuery(jpqlSelect);
		List<Contatto> contatti = query.getResultList();
		
		System.out.println("Tutti i contatti: " + contatti.size());
		System.out.println("");
		
		if (contatti.size() != 0) {
			for (Contatto contatto : contatti) {
				System.out.println("id : " + contatto.getId());
				System.out.println("name : " + contatto.getName());
				System.out.println("surname : " + contatto.getSurname());
				System.out.println("telephone : " + contatto.getTelephone());
				System.out.println("email : " + contatto.getEmail());
				System.out.println("------------------");
			}
		}
		
	}
	
	/*
	//JPQL
	String jpqlSelect = "SELECT c FROM Contatto as c";
	Query query = entityManager.createQuery(jpqlSelect);
	List<Contatto> contatti = query.getResultList();
	
	for (Contatto contatto : contatti) {
		System.out.println("id : " + contatto.getId());
		System.out.println("name : " + contatto.getName());
		System.out.println("surname : " + contatto.getSurname());
		System.out.println("telephone : " + contatto.getTelephone());
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
	Contatto newContatto = new Contatto();
	newContatto.setName("Piero");
	newContatto.setSurname("Pelù");
	newContatto.setEmail("ppelu@beije.it");
		
	System.out.println("newContatto ID PRE save  :" + newContatto.getId());
	entityManager.persist(newContatto);
	System.out.println("newContatto ID POST save :" + newContatto.getId());
		
	entityTransaction.commit();
//		entityTransaction.rollback();
		
	entityManager.close();
		
	}
	*/

}