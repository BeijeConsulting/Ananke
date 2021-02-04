package dao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import exceptions.EmptyDatabaseException;
import exceptions.ItemAlreadyExistException;
import exceptions.ItemNotFoundException;
import model.User;

public interface UserManager {

	boolean setUser(User user) throws IOException, SQLException;
	void removeUser(String email);
	void updateUser(User user) ;
	User getUser(String email) ;
	List<User> getAllUsers();
	
}