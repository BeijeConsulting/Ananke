package app;


import java.io.IOException;
import java.sql.SQLException;
import dao.UserManager;
import dao.UsersInJpaManager;
import utilities.Utility;

public class JpaApp {
	
	public static void main(String[] args) throws IOException, SQLException {
		UserManager userManager = new UsersInJpaManager();
		Utility.mainMenu(userManager);
		
	}
}
