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
		int i = 0;
		Scanner s = new Scanner(System.in);
		
		System.out.println();
		System.out.println("------------------------------");
		System.out.println("QAULE CONTATTO VUOI MODIFICARE!");
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
		
		String hqlSelect = "SELECT c FROM Contatto as c";
		Query<Contatto> query = session.createQuery(hqlSelect);
		List<Contatto> contatti = query.list();
		
		Transaction transaction = session.beginTransaction();
		
		Contatto c = contatti.get(indice - 1);
		c.setNome("");
		c.setCognome("");
		c.setMail("");
		c.setNumeroTel("");
		
	
		session.save(c);
		
		
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
		
	}
	
	
}