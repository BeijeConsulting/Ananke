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
		Configuration configuration = new Configuration().configure();
		this.sessionFactory = configuration.buildSessionFactory();
		Session session = this.sessionFactory.openSession();
		return session;
	} 
	@Override
	public boolean setUser(User newUser) throws IOException, SQLException {
		Session session = getSession();
		Transaction transaction = session.beginTransaction();
		session.save(newUser);
		transaction.commit();
		return true;
	}

	@Override
	public void removeUser(String email) {
		User user = getUser(email);
		Session session = getSession();
		session.delete(user);
		session.close();
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
