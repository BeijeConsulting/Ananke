package it.beije.ananke.api.prova;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import it.beije.ananke.api.prova.Contatto;


public class JPAmanager {
	private EntityManager entityManager;
	static Scanner scanner = new Scanner(System.in);

	public static void main(String[] args) {
		//		entityManager = RubricaEntityManager.getEntityManager();
		//gestisco con singleton quindi questo non mi serve più
		//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Rubrica");
		//		EntityManager entityManager = entityManagerFactory.createEntityManager();
		JPAmanager jpa = new JPAmanager();
		//search();
		//jpa.insert();
		//jpa.read();
		jpa.update();
		//jpa.delete();
	}
	public ArrayList<Contatto> prendirubrica(){
		entityManager = RubricaEntityManager.getEntityManager();
		String jpqlSelect = "SELECT c FROM Contatto as c";
		Query query = entityManager.createQuery(jpqlSelect);
		List<Contatto> contatti = query.getResultList();
		entityManager.close();
		return (ArrayList<Contatto>) contatti;
	}
	
	public void search() {
		entityManager = RubricaEntityManager.getEntityManager();
		System.out.println("Inserisci la mail (in questo caso la chiave è l' ID) del contatto da ricercare");
		String m = scanner.nextLine();		
		int indice = Integer.parseInt(m);
		Contatto contatto1 = entityManager.find(Contatto.class, indice);
		if(contatto1==null) {
			System.out.println("Contatto inesistente");
		}
		else {
			System.out.println(contatto1);
		}
	}

	public void update() {
		ArrayList<Contatto> contatti = prendirubrica();
		entityManager = RubricaEntityManager.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		System.out.println("Inserisci la mail del contatto da modificare");
		String m = scanner.nextLine();
		Contatto c1;
		int indice=0;
		boolean b = false;
		for(int i=0;i<contatti.size();i++) {
			if(contatti.get(i).getEmail().equalsIgnoreCase(m)) {
				System.out.println("Inserisci il nome dell' utente");
				String name = scanner.nextLine();
				System.out.println("Inserisci il cognome dell' utente");
				String surname = scanner.nextLine();
				System.out.println("Inserisci il recapito telefonico dell' utente");
				String telephone = scanner.nextLine();
				System.out.println("Inserisci l' indirizzo email dell' utente");
				String email = scanner.nextLine();	
				indice = contatti.get(i).getId();
				c1=entityManager.find(Contatto.class, indice);	
				c1.setName(name);
				c1.setSurname(surname);
				c1.setTelephone(telephone);
				c1.setEmail(email);
				if(c1.getEmail().trim().equals("")) {
					b=false;
					entityTransaction.rollback(); //se non voglio salvare la modifica	
				}
				else {
					b=true;
					entityManager.persist(c1);
					System.out.println("ARRIVO QUI");
					entityTransaction.commit(); //Se voglio applicare i cambiamenti
				}
			}
		}
	
		entityManager.close();
	}
				


	public void insert() {
		entityManager = RubricaEntityManager.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Contatto newContatto = new Contatto();
		System.out.println("------------------------------------------");
		System.out.println("Inserisci il nome dell' utente");
		String name = scanner.nextLine();
		System.out.println("Inserisci il cognome dell' utente");
		String surname = scanner.nextLine();
		System.out.println("Inserisci il recapito telefonico dell' utente");
		String telephone = scanner.nextLine();
		System.out.println("Inserisci l' indirizzo email dell' utente");
		String email = scanner.nextLine();	
		newContatto.setName(name);
		newContatto.setSurname(surname);
		newContatto.setTelephone(telephone);
		newContatto.setEmail(email);

		entityManager.persist(newContatto);
		System.out.println("l' id del nuovo contatto è: " + newContatto.getId());
		System.out.println("------------------------------------------");
		entityTransaction.commit();
		//		entityTransaction.rollback();

		entityManager.close();
	}

	public void read() {
		//JPQL
		entityManager = RubricaEntityManager.getEntityManager();
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
	}

	public void delete() {		
		ArrayList<Contatto> contatti= prendirubrica();
		entityManager = RubricaEntityManager.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		Contatto c1;
		System.out.println("Inserisci la mail del contatto da eliminare");
		String m = scanner.nextLine();
		int indice =0;
		for(int i=0;i<contatti.size();i++) {
			if(contatti.get(i).getEmail().equalsIgnoreCase(m)) {
				indice = contatti.get(i).getId();
				c1=entityManager.find(Contatto.class, indice);
				entityManager.remove(c1);
				System.out.println("Contatto eliminato");
				entityTransaction.commit();
			}


			//		entityTransaction.rollback();
		}
		entityManager.close();
	}
}
