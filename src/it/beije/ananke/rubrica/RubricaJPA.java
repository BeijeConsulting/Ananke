package it.beije.ananke.rubrica;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class RubricaJPA {
	
	
	public static List<Contatto> cercaContatti(String parolaChiave){
		
		List<Contatto> list = new ArrayList<>();
		EntityManager entityManager = JPAEntityManager.getEntityManager();
		
		String jpqlSelect = "SELECT c FROM Contatto as c " + "WHERE nome = " + "'" + parolaChiave + "'"
				  + "OR cognome = " + "'" + parolaChiave + "'"
				  + "OR mail = " + "'" + parolaChiave + "'"
				  + "OR numeroTel = " + "'" + parolaChiave + "'";
		
		Query query = entityManager.createQuery(jpqlSelect);
		list = query.getResultList();
		
		if (list.size() == 0) {
			System.out.print("NESSUN CONTATTO TROVATO!");
			return null;
		}
		
		for(Contatto c : list) {
			c.visualizzaContatto();
		}


		return list;
	}
	
	public static void inserisciContatto(String nome, String cognome, String numeroTel, String mail) {
		
		EntityManager entityManager = JPAEntityManager.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		entityTransaction.begin();
		
		Contatto newC = new Contatto();

		newC.setNome(nome);
		newC.setCognome(cognome);
		newC.setMail(mail);
		newC.setNumeroTel(numeroTel);
		entityManager.persist(newC);
		entityTransaction.commit();
		
		
	}
	
	public static void visualizzaContattiDB() {
		
		List<Contatto> list = new ArrayList<>();
		EntityManager entityManager = JPAEntityManager.getEntityManager();
		
		String jpqlSelect = "SELECT c FROM Contatto as c ";
				  
		Query query = entityManager.createQuery(jpqlSelect);
		list = query.getResultList();
		
		for(Contatto c : list)
			c.visualizzaContatto();

	}
	
	public static void eliminaContattoDB(String parolaChiave) {
		
		EntityManager entityManager = JPAEntityManager.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		List<Contatto> list = cercaContatti(parolaChiave);
		entityTransaction.begin();
		
		if(list == null) {
			//System.out.println("NESSUN CONTATTO PRESENTE!");
			return;
		}
		
		int i = 0; // usato sia per indicare quale contatto selezioare da modificcare sia per l'indice da modificare
		int indiceDaEiminare = 0;
		
		Scanner s = new Scanner(System.in);
		
		System.out.println();
		System.out.println("-----------------------------");
		System.out.println("QAULE CONTATTO VUOI ELIMINARE?");
		System.out.println("-----------------------------");

		for(Contatto contatto : list) {
			i++;
			System.out.print( i + " - ( ");

			System.out.print(contatto.getNome() + "  " + contatto.getCognome() + "  " 
					+ contatto.getNumeoroTel() + "  " + contatto.getMail());

			System.out.println(" )");
		}

		System.out.println("-----------------------------------------");
		
		String comando = s.next();
		int indice = Integer.parseInt(comando);
		Contatto cDaEliminare = list.get(indice - 1);
		
		entityManager.remove(cDaEliminare);
			
		entityTransaction.commit();
		
		
		
	}
	
public static void modificaContatto(String parolaChiave) {
		
		EntityManager entityManager = JPAEntityManager.getEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		List<Contatto> list = cercaContatti(parolaChiave);
		entityTransaction.begin();
		
		if(list == null) {
			//System.out.println("NESSUN CONTATTO PRESENTE!");
			return;
		}
		
		int i = 0; // usato sia per indicare quale contatto selezioare da modificcare sia per l'indice da modificare
		int indiceDaModificare = 0;
		
		Scanner s = new Scanner(System.in);
		
		System.out.println();
		System.out.println("-------------------------------");
		System.out.println("QAULE CONTATTO VUOI MODIFICARE?");
		System.out.println("-------------------------------");

		for(Contatto contatto : list) {
			i++;
			System.out.print( i + " - ( ");

			System.out.print(contatto.getNome() + "  " + contatto.getCognome() + "  " 
					+ contatto.getNumeoroTel() + "  " + contatto.getMail());

			System.out.println(" )");
		}

		System.out.println("-----------------------------------------");
		
		String comando = s.next();
		int indice = Integer.parseInt(comando);
		int idDaModificare = list.get(indice - 1).getId();
		
		System.out.println("----------------------------");
		System.out.println("QAULE CAMPO VUOI MODIFICARE?");
		System.out.println("----------------------------");
		System.out.println("1 - Nome");
		System.out.println("2 - Cognome");
		System.out.println("3 - Numero di telefono");
		System.out.println("4 - Email");
		
		String jpqlSelect = "SELECT c FROM Contatto as c";
		Query query = entityManager.createQuery(jpqlSelect);
		list = query.getResultList();
		
		i = 0;
		for(Contatto c : list) {
			if(c.getId() == idDaModificare) {
				indiceDaModificare = i;
				break;
			}
			i++;
		}
		String cmd = s.next();
		
		Contatto c = null;
		switch(cmd) {
		
		case "1":
			
			System.out.println("Inserisci nuovo nome:");
			cmd = s.next();
			
			
			c = list.get(indiceDaModificare);
			c.setNome(cmd);
			entityManager.persist(c);
			entityManager.refresh(c);
			entityTransaction.commit();
			
			break;
			
		case "2":
			
			System.out.println("Inserisci nuovo cognome:");
			cmd = s.next();
			

			break;
		
		case "3":
			
			System.out.println("Inserisci nuovo numero di telefono:");
			cmd = s.next();
			
		
						
			break;
		
		case "4":
			
			System.out.println("Inserisci nuova email:");
			cmd = s.next();
			
	
			break;
		
		}
		
		s.close();
		
		
	}
	

	
	public static void provaConnessione() {
		
		
		
		EntityManager entityManager = JPAEntityManager.getEntityManager();
		
		System.out.println(" LA CONNESSIONE é APERTA? : " + entityManager.isOpen());
		
	}
	
	
}
