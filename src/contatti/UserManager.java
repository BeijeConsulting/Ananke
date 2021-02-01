package contatti;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface UserManager {

	void setUser(User user) throws IOException, SQLException;
	void removeUser(String email);
	void updateUser(User user);
	User getUser(String email);
	List<User> getAllUsers();
	
}