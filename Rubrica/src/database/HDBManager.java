package database;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import entità.Contatto;

public class HDBManager {
		
		/*
		Configuration configuration = new Configuration().configure()
				.addAnnotatedClass(Contatto.class);

		SessionFactory sessionFactory = configuration.buildSessionFactory();
		
		Session session;
		*/
	
		static Session session;
		
		
		public void connettiDb() {
		
			/*
			session = sessionFactory.openSession();
			System.out.println("session is open ? " + session.isOpen());
			*/
			
			session = HibernateSessionManager.getSession();
			System.out.println("session is open ? " + session.isOpen());
		
		}		
		
		/*
		public void chiudiConnessioneDb() {
			
			session.close();
			System.out.println("Connessione chiusa");
			
		}
		*/
		
		public void inserisciContattoDb(Contatto tempContatto) {
		
			Transaction transaction = session.beginTransaction();
			
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
			
			session.save(tempContatto);
			
			transaction.commit();
			
			/*
			INSERT
			Contatto newContatto = new Contatto();
			//DA NON FARE newContatto.setId(13);
			newContatto.setName("Stefano");
			newContatto.setSurname("Savallo 2");
			newContatto.setEmail("s.savallo2@beije.it");
			
			System.out.println("newContatto ID PRE save  :" + newContatto.getId());
			session.save(newContatto);
			System.out.println("newContatto ID POST save :" + newContatto.getId());

			transaction.commit();
			// transaction.rollback();
			*/
			
		}
		
		public Contatto cercaContattoDb(String nome, String cognome) {
			
			String hqlSelect = "SELECT c FROM Contatto as c WHERE c.name = '" + nome + "' and c.surname = '"+ cognome +"'";
			
			Query<Contatto> query = session.createQuery(hqlSelect);
			List<Contatto> contatti = query.list();
			
			System.out.println("Contatti trovati: " + contatti.size());
			System.out.println("");
			
			if (contatti.size() != 0) {
				for (Contatto contatto : query.list()) {
					System.out.println("id : " + contatto.getId());
					System.out.println("name : " + contatto.getName());
					System.out.println("surname : " + contatto.getSurname());
					System.out.println("telephone : " + contatto.getTelephone());
					System.out.println("email : " + contatto.getEmail());
					System.out.println("------------------");
				}
			}
			
			if (contatti.size() != 0) {
				return query.list().get(0);
			}
			
			return null;
			
		}
		
		public void eliminaContattoDb(String nome, String cognome) {
			
			Transaction transaction = session.beginTransaction();
			
			String delete =  "DELETE from Contatto where name = '" + nome + "' and surname = '" + cognome + "'";

		    Query query = session.createQuery(delete);
		    
		    System.out.println("Il contatto è stato eliminato con successo");
		    	
		    query.executeUpdate();
		    
		    transaction.commit();
		    
		    
		    
		}
		
		public void modificaNomeContattoDb(Contatto contatto, String nomeNuovo) throws SQLException {
			
			Transaction transaction = session.beginTransaction();
			
			contatto.setName(nomeNuovo);
			
			session.save(contatto);
			
			transaction.commit();
			
		}
		
		
		public void modificaCognomeContattoDb(Contatto contatto, String cognomeNuovo) throws SQLException {
			
			Transaction transaction = session.beginTransaction();
			
			contatto.setSurname(cognomeNuovo);
			
			session.save(contatto);
			
			transaction.commit();
			
		}
		
		public void modificaTelefonoContattoDb(Contatto contatto, String telefonoNuovo) throws SQLException {
			
			Transaction transaction = session.beginTransaction();
			
			contatto.setSurname(telefonoNuovo);
			
			session.save(contatto);
			
			transaction.commit();
			
		}
		
		public void modificaEmailContattoDb(Contatto contatto, String emailNuova) throws SQLException {
			
			Transaction transaction = session.beginTransaction();
			
			contatto.setSurname(emailNuova);
			
			session.save(contatto);
			
			transaction.commit();
			
		}
		
		public void visualizzaContattiDb() throws SQLException {
			
			String hqlSelect = "SELECT c FROM Contatto as c";
			
			Query<Contatto> query = session.createQuery(hqlSelect);
			List<Contatto> contatti = query.list();
			
			System.out.println("Tutti i contatti: " + contatti.size());
			System.out.println("");
			
			if (contatti.size() != 0) {
				for (Contatto contatto : query.list()) {
					System.out.println("id : " + contatto.getId());
					System.out.println("name : " + contatto.getName());
					System.out.println("surname : " + contatto.getSurname());
					System.out.println("telephone : " + contatto.getTelephone());
					System.out.println("email : " + contatto.getEmail());
					System.out.println("------------------");
				}
			}
			
		}
		
}
