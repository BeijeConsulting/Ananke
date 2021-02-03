package it.beije.ananke.rubrica;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

public class RubricaHQL {

	
	public static List<Contatto> cercaContatti(String parolaChiave){
		
		List<Contatto> listC = new ArrayList<Contatto>();
		Session session = SessionManager.apriSessione();
		
		String hqlSelect = "SELECT c FROM Contatto as c " + "WHERE nome = " + "'" + parolaChiave + "'"
				  + "OR cognome = " + "'" + parolaChiave + "'"
				  + "OR mail = " + "'" + parolaChiave + "'"
				  + "OR numeroTel = " + "'" + parolaChiave + "'";
				  
		@SuppressWarnings("unchecked")
		Query<Contatto> query = session.createQuery(hqlSelect);
		listC = query.list();
		
		if (listC.size() == 0) {
			System.out.print("NESSUN CONTATTO TROVATO!");
			return null;
		}
		
		for(Contatto c : listC)
			c.visualizzaContatto();
		//System.out.println(listC.size());
		SessionManager.chiudiSessione();
		
		return listC;
	}
	
	public static void inserisciContatto(String nome, String cognome, String numeroTel, String mail) {
		
		Session session = SessionManager.apriSessione();
		Transaction transaction = session.beginTransaction();
		
		Contatto newC = new Contatto();

		newC.setNome(nome);
		newC.setCognome(cognome);
		newC.setMail(mail);
		newC.setNumeroTel(numeroTel);
		session.save(newC);
		transaction.commit();
		SessionManager.chiudiSessione();
		
	}
	
	public static void modificaContatto(String parolaChiave) {
		
		Session session = SessionManager.apriSessione();
		List<Contatto> list = cercaContatti(parolaChiave);
		Transaction transaction;
		
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
		
		String hqlSelect = "SELECT c FROM Contatto as c";
		Query<Contatto> query = session.createQuery(hqlSelect);
		List<Contatto> contatti = query.list();
		
		i = 0;
		for(Contatto c : contatti) {
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
			
			transaction = session.beginTransaction();
			c = contatti.get(indiceDaModificare);
			c.setNome(cmd);
			session.update(c);
			session.save(c);
			transaction.commit();
			
			break;
			
		case "2":
			
			System.out.println("Inserisci nuovo cognome:");
			cmd = s.next();
			
			transaction = session.beginTransaction();
			c = contatti.get(indiceDaModificare);
			c.setCognome(cmd);
			session.update(c);
			session.save(c);
			transaction.commit();

			break;
		
		case "3":
			
			System.out.println("Inserisci nuovo numero di telefono:");
			cmd = s.next();
			
			transaction = session.beginTransaction();
			c = contatti.get(indiceDaModificare);
			c.setNumeroTel(cmd);
			session.update(c);
			session.save(c);
			transaction.commit();
						
			break;
		
		case "4":
			
			System.out.println("Inserisci nuova email:");
			cmd = s.next();
			
			transaction = session.beginTransaction();
			c = contatti.get(indiceDaModificare);
			c.setMail(cmd);
			session.update(c);
			session.save(c);
			transaction.commit();
			
			break;
		
		}
		
		s.close();
		
		
	}
	
	public static void eliminaContattoDB(String parolaChiave) {
		
		Session session = SessionManager.apriSessione();
		List<Contatto> list = cercaContatti(parolaChiave);
		Transaction transaction;
		
		if(list == null) {
			System.out.println("NESSUN CONTATTO PRESENTE!");
			return;
		}
		
		int i = 0; // usato sia per indicare quale contatto selezioare da modificcare sia per l'indice da modificare
		int indiceDaEiminare = 0;
		
		Scanner s = new Scanner(System.in);
		
		System.out.println();
		System.out.println("------------------------------");
		System.out.println("QAULE CONTATTO VUOI ELIMINARE?");
		System.out.println("------------------------------");

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

			
		transaction = session.beginTransaction();	  
		session.remove(cDaEliminare);
		transaction.commit();
		
		SessionManager.chiudiSessione();
	
	}
	
	@SuppressWarnings("unchecked")
	public static void visualizzaDB() {
		
		List<Contatto> listC = new ArrayList<Contatto>();
		Session session = SessionManager.apriSessione();
		
		String hqlSelect = "SELECT c FROM Contatto as c ";
				  
		Query<Contatto> query = session.createQuery(hqlSelect);
		listC = query.list();
		
		for(Contatto c : listC)
			c.visualizzaContatto();
	
		SessionManager.chiudiSessione();
		//System.out.println("LA SESSIONE E' ANCORA APERTA: " + session.isOpen());
		
	}
	
	
}
