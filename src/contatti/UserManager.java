package contatti;
import java.io.IOException;
import java.util.List;

public interface UserManager {

	void setUser(User user) throws IOException;
	void removeUser(String email);
	void updateUser(User user);
	User getUser(String email);
	List<User> getAllUsers();
	
}