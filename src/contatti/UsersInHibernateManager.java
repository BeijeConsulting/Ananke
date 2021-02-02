package contatti;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

public class UsersInHibernateManager implements UserManager {

	private static SessionFactory sessionFactory;
	
	public static void closeSessionFactory() {
		sessionFactory.close();
	}
	private Session getSession() {
		//Configuration configuration = new Configuration().configure();  for classic hybernate
		Configuration configuration = new Configuration().configure() //for annotated hybernation
				.addAnnotatedClass(User.class);
		this.sessionFactory = configuration.buildSessionFactory();
		Session session = this.sessionFactory.openSession();
		return session;
	} 
	@Override
	public boolean setUser(User newUser) throws IOException, SQLException {
		for(User user : getAllUsers()) {
			if(user.getEmail().equals(newUser.getEmail())) {
				return false;
			}
		}
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.save(newUser);
		transaction.commit();
		session.close();
		return true;
	}

	@Override
	public void removeUser(String email) {
		User user = getUser(email);
		if(user!=null) {
		Session session = getSession();
		session.delete(user);
		System.out.println("Removed contact successfully!");
		session.close();
		}
		else
		{
			System.out.println("Removing Contact Unsuccessful!");
		}
	}

	@Override
	public void updateUser(User updateUser) {
		Session session = getSession();
        Transaction transaction = session.beginTransaction();
		session.save(updateUser);
        session.close();
	}

	@Override
	public User getUser(String email) {
		List<User> usersList = new ArrayList<>();
		usersList = getAllUsers();
		for(User user : usersList) {
			if(user.getEmail().equals(email))
				return user;
		}
        return null;
	}

	@Override
	public List<User> getAllUsers() {
		Session session = getSession();
		String hqlSelect = "SELECT c FROM User as c";
		Query<User> query = session.createQuery(hqlSelect);
		List<User> usersList = query.list();
		session.close();
		return usersList;
	}
}
