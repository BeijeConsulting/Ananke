package dao;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import model.User;

public class UsersInJpaManager implements UserManager {

	
	private final EntityManagerFactory entityManagerFactory = Persistence
			.createEntityManagerFactory("Ananke");
	private final EntityManager entityManager = entityManagerFactory.createEntityManager();
	
	@Override
	public boolean setUser(User user) throws IOException, SQLException {
		entityManager.getTransaction().begin();
		entityManager.persist(user);
		entityManager.getTransaction().commit();
		return false;
	}

	@Override
	public void removeUser(String email) {
		User user = null;
		user = this.getUser(email);
		entityManager.getTransaction().begin();
		entityManager.remove(user);
		entityManager.getTransaction().commit();
	}

	@Override
	public void updateUser(User user) {
		entityManager.getTransaction().begin();
		entityManager.merge(user);
		entityManager.getTransaction().commit();
	}

	@Override
	public User getUser(String email) {
		List<User> allUsers = new ArrayList<>();
		allUsers = getAllUsers();
		for(User user : allUsers) {
			if(user.getEmail().equals(email)) {
				return user;
			}
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAllUsers() {
		List<User> allUsers = new ArrayList<>();
		entityManager.getTransaction().begin();
		allUsers = entityManager.createQuery("SELECT u FROM User u").getResultList();
		entityManager.getTransaction().commit();
		return allUsers;
	}
	

}
