package contatti;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Utility {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void mainMenu() {
		System.out.println("Press 1 to see all Contacts!");
		System.out.println("Press 2 to see a specific contact!");
		System.out.println("Press 3 to add a contact!");
		System.out.println("Press 4 to delete a contact!");
		System.out.println("Press 5 to remove a contact!");
		System.out.println("Press 0 to eixt!");
	}
	public static void serviceCallingManager(int n, UserManager userManager) throws IOException, SQLException {
		switch(n) {
		case 1 :
		    {
			List<User> usersList = new ArrayList<>();
			usersList = userManager.getAllUsers();
			printUsers(usersList);
			mainMenu();
			break;
		     }
		case 2 : {
			String email=null;
			System.out.println("enter the email address of the user: ");
			email = scanner.nextLine();
			   User user = userManager.getUser(email);
			   if(user!=null) {
			   user.displayUser();
			   System.out.println("####################################");
			    mainMenu();
			   }
			   else {
				   System.out.println("Sorry this contact is not exists in the database!");
			   }
			    break;
		         }
		 case 3 :
		         {
			     if( userManager.setUser(getUserFromKeyBoard())){
			         System.out.println("Contact added successfully!");
			    	 mainMenu();
			     }
			     else {
			    	 System.out.println("this Contact is already exists!");
			     }
			      break;
		         }
		 case 4 :
		         {
			      System.out.println("Enter the email address of the user to delete!");
			      String email =scanner.nextLine();
			      userManager.removeUser(email);
			      mainMenu();
			      break;
		         }
		 case 5 : 
		        {
		         System.out.println("thank you bye!"); 
		         break;
		        }
		 case 0:
			 System.out.println("thank you bye!");
			 break;
		        default :
		        	System.out.println("Wrong button pressed, plz try again!");
		        	break;
			 
		}
	}
	private static User getUserFromKeyBoard() {
		User user = new User();
		String data;
		System.out.println("Enter first name of the user : ");
		data = scanner.nextLine();
		user.setFirstName(data);
		
		System.out.println("Enter last name of the user : ");
		data = scanner.nextLine();
		user.setLastName(data);
		
		System.out.println("Enter email address of the user : ");
		data = scanner.nextLine();
		user.setEmail(data);
		
		System.out.println("Enter phone number of the user : ");
		data = scanner.nextLine();
		user.setPhoneNumber(data);
		
		return user;
	}
	public static void printUsers(List<User> list) {
		for (User user : list) {
			System.out.println("id : " + user.getId());
			System.out.println("firstName : " + user.getFirstName());
			System.out.println("lastName : " + user.getLastName());
			System.out.println("email : " + user.getEmail());
			System.out.println("phoneNumber : " + user.getPhoneNumber());
		}
	}
}
