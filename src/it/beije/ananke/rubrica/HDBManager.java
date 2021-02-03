package it.beije.ananke.rubrica;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HDBManager {
	
	public Session getSession() {
		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Contatto.class);
		SessionFactory sessionFactory = configuration.buildSessionFactory();
		Session session = sessionFactory.openSession();
		return session;
	}
	
	public void aggiungiContattoHDB(Contatto contatto, Session session) {
		Transaction transaction = session.beginTransaction();
		
		Contatto newContatto = new Contatto();
		newContatto.setNome(contatto.getNome());
		newContatto.setCognome(contatto.getCognome());
		newContatto.setEmail(contatto.getEmail());
		newContatto.setTelefono(contatto.getTelefono());
		
		session.save(newContatto);
		transaction.commit();
		System.out.println("Contatto aggiunto");
			
	}

	public List<Contatto> ricercaContattoHDB(Contatto contatto, Session session) {
		
		System.out.println("Ricerca contatti");
		
		String ricerca = "SELECT c FROM Contatto as c WHERE ";
		
		if(!contatto.getNome().trim().equals("")) {
			ricerca += "nome ='" + contatto.getNome() + "'"; 
		}
		
		if(!contatto.getCognome().trim().equals("")) {
			ricerca += " AND cognome ='" + contatto.getCognome() + "'";
		}
		
		if(!contatto.getEmail().trim().equals("")) {
			ricerca += " AND email ='" + contatto.getEmail() + "'";
		}
				
		if(!contatto.getTelefono().trim().equals("")) {
			ricerca += " AND telefono ='" + contatto.getTelefono() + "'";
		}
				
		Query<Contatto> query = session.createQuery(ricerca);
		
		List<Contatto> contatti = query.list();
				
		for(Contatto c : contatti) {
			System.out.println(c.toString());
		}
		
		return contatti;
	}
	
	public void modificaContattoHDB(List<Contatto> contatti,Contatto contattoModificato, Session session) {
		Transaction transaction = session.beginTransaction();
		
		for(Contatto contatto: contatti) {
			if(!contattoModificato.getNome().equals("")) {
				contatto.setNome(contattoModificato.getNome());
			}
			if(!contattoModificato.getCognome().equals("")) {
				contatto.setCognome(contattoModificato.getCognome());
			}
			if(!contattoModificato.getEmail().equals("")) {
				contatto.setEmail(contattoModificato.getEmail());
			}
			if(!contattoModificato.getTelefono().equals("")) {
				contatto.setTelefono(contattoModificato.getTelefono());
			}
			session.save(contatto);
		}	
		transaction.commit();
		
		System.out.println("Contatti modificati");
	}
	
	public void eliminaContattoHDB(List<Contatto> contatti, Session session) {
		Transaction transaction = session.beginTransaction();
		for(Contatto contatto : contatti) {
			session.delete(contatto);
		}
		transaction.commit();
		System.out.println("Contatti eliminati");
	}
}
