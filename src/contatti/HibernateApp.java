package contatti;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HibernateApp {

	public static void main(String[] args) throws IOException, SQLException {
		
		
		Utility.usersMenuManager();
		UsersInHibernateManager.closeSessionFactory();
	}
}
