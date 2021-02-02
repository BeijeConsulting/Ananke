package it.beije.ananke.rubrica;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class HDBManager {
	
	public Session getSession() {
		Configuration configuration = new Configuration().configure();
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
	
	public void modificaContattoDB() {
		
	}

	public void ricercaContattoHDB(Contatto contatto, Session session) {
		String ricerca = "SELECT c FROM Contatto as c WHERE "
				+ "(c.nome = :param1 OR :param2) AND "
				+ "(c.cognome = :param3 OR :param4) AND "
				+ "(c.email = :param5 OR :param6) AND "
				+ "(c.telefono = :param7 OR :param8)";
		
		Query<Contatto> query = session.createQuery(ricerca);
		query.setParameter("param1", contatto.getNome());
		
		if(contatto.getNome().equals("")) {
			query.setParameter("param2",true);
		}else {
			query.setParameter("param2",false);
		}
		
		query.setParameter("param3", contatto.getCognome());
		
		if(contatto.getCognome().equals("")) {
			query.setParameter("param4",true);
		}else {
			query.setParameter("param4",false);
		}
		
		query.setParameter("param5", contatto.getEmail());
		
		if(contatto.getEmail().equals("")) {
			query.setParameter("param6",true);
		}else {
			query.setParameter("param6",false);
		}
		
		query.setParameter("param7", contatto.getTelefono());
		
		if(contatto.getTelefono().equals("")) {
			query.setParameter("param8",true);
		}else {
			query.setParameter("param8",false);
		}
		
		List<Contatto> contatti = query.list();
		
		for(Contatto c : contatti) {
			c.toString();
		}

	}
	
	public void eliminaContattoDB(String email) {
//		//ricercaContattoDB(email);
//		Connection connection = getConnection();
//		PreparedStatement preparedStatement = null;
//		String ricerca = "DELETE FROM contatti WHERE email=?";
//
//		try {
//			preparedStatement = connection.prepareStatement(ricerca);	
//			preparedStatement.setString(1, email);
//			preparedStatement.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}		
	}
	public void modificaContattoDB(Contatto contattoModificato) {
		
	}
}
