package contatti;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public class UsersInHibernateManager implements UserManager {

	@Override
	public boolean setUser(User user) throws IOException, SQLException {
		System.out.println("hello world");
		return false;
	}

	@Override
	public void removeUser(String email) {
		// TODO Auto-generated method stub

	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public User getUser(String email) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
