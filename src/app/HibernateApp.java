package app;

import java.io.IOException;
import java.sql.SQLException;

import dao.UserManager;
import dao.UsersInHibernateManager;
import utilities.Utility;


public class HibernateApp {

	public static void main(String[] args) throws IOException, SQLException {
		
		UserManager userManager = new UsersInHibernateManager();
		Utility.mainMenu(userManager);
		UsersInHibernateManager.closeSessionFactory();
	}
}
