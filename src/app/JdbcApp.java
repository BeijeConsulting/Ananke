package app;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.UserManager;
import dao.UsersInJdbcManager;
import model.User;

public class JdbcApp {

	public static void main(String[] args) throws IOException, SQLException {
		List<User> allUsers = new ArrayList<>();
		UserManager userManager = new UsersInJdbcManager();
		
		
		String email = "faheemjan58@gmail.com";
		User user = userManager.getUser(email);
		 if(user!=null)
		System.out.println("User retrieved from database is : "+ user.getFirstName()+ " " + user.getEmail() );
		 else
			 System.out.println("user not found in database!");
		
//		String email = "kaka58@gmail.com";
//		userManager.removeUser(email);
		
//		allUsers = userManager.getAllUsers();
//		for(User user : allUsers) {
//			System.out.println(user.getFirstName()+" " 
//		+user.getLastName() + " " +user.getEmail() + " "+user.getPhoneNumber());
//		}
		
//		User user = new User("kaka","macho","kaka58@gmail.com","82811111");
//		if(userManager.setUser(user) )
//		{
//			System.out.println("person added successfully!");
//			
//		}
//		else
//		{
//			System.out.println("this person already exist!");
//		}
		
	}
	
	
	}


