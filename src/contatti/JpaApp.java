package contatti;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class JpaApp {
	
	private static Scanner scanner = new Scanner(System.in);
	
	public static void main(String[] args) throws IOException, SQLException {
		UserManager userManager = new UsersInJpaManager();
		mainMenu(userManager);
		scanner.close();
	}
	
	public static void mainMenu(UserManager userManager) throws IOException, SQLException {
		System.out.println("Press 1 to see all Contacts!");
		System.out.println("Press 2 to see a specific contact!");
		System.out.println("Press 3 to add a contact!");
		System.out.println("Press 4 to remove a contact!");
		System.out.println("Press 5 to update a contact!");
		System.out.println("Press 0 to eixt!");
		 String inputFromKeyboard= scanner.next();
		 int i= Integer.parseInt(inputFromKeyboard);
		serviceCallingManager(i,userManager);
	}
	public static void serviceCallingManager(int n,UserManager userManager) throws IOException, SQLException {
		switch(n) {
		case 1 :
		    {
			List<User> usersList = new ArrayList<>();
			usersList = userManager.getAllUsers();
			printUsers(usersList);
			System.out.println("####################################");
			mainMenu(userManager);
			break;
		     }
		case 2 : {
			String email=null;
			System.out.println("enter the email address of the user: ");
			email = scanner.next();
			//scanner.close();
			   User user = userManager.getUser(email);
			   if(user!=null) {
			   user.displayUser();
			   
			   System.out.println("####################################");
			   mainMenu(userManager);
			   }
			   else {
				   System.out.println("Sorry this contact is not exists in the database!");
				   System.out.println("####################################");
				   mainMenu(userManager);
			   }
			    break;
		         }
		 case 3 :
		         {
		        	 User user=null;
		        	 user = getUserFromKeyBoard(userManager);
		        	   if( !isUserExistInDatabase(user.getEmail(),userManager) ) {
			           userManager.setUser(user);
			         System.out.println("Contact added successfully!");
			         System.out.println("####################################");
			         mainMenu(userManager);
		        			break;
			     }
			     else {
			    	 System.out.println("this Contact is already exists!");
			    	 System.out.println("####################################");
			         mainMenu(userManager);
		        			break;
			     }
		         }
		 case 4 :
		         {
			      System.out.println("Enter the email address of the user to delete!");
			      String email =scanner.next();
			    	  if(isUserExistInDatabase(email, userManager)) {
			    		  userManager.removeUser(email);
			    		  System.out.println("person removed successfully!");
			    		  System.out.println("####################################");
					      mainMenu(userManager);
			    		  break;
			    	  } 
			    	  else {
						      System.out.println("Sorry there is no such user to remove!");
						      System.out.println("####################################");
						      mainMenu(userManager);
			         break;
			    	  }
		         }
		 case 5 :
		      {
			     User user = getUserFromKeyBoard(userManager);
			     if(isUserExistInDatabase(user.getEmail(), userManager)) {
			    	 userManager.updateUser(user);
			    	 System.out.println("User updated successfully!");
			    	 System.out.println("####################################");
				      mainMenu(userManager);
			    	 break;
			     }
			     else {
			    	 System.out.println("Sorry this user is not exists to update!");
			    	 System.out.println("####################################");
				      mainMenu(userManager);
			    	 break;
			     }
	        	 }
		 case 0:
			 System.out.println("thank you bye!");
			 break;
		        default :
		        	System.out.println("Wrong button pressed, plz try again!");
		        	break;
			 
		}
	}
		
		public static void printUsers(List<User> list) {
			for (User user : list) {
				user.displayUser();
			}
		}
		private static User getUserFromKeyBoard(UserManager userManager) {
			User user = new User();
			String data,email;
			System.out.println("Enter first name of the user : ");
			data = scanner.next();
			user.setFirstName(data);
			
			System.out.println("Enter last name of the user : ");
			data = scanner.next();
			user.setLastName(data);
			
			System.out.println("Enter email address of the user : ");
			email = scanner.next();
			user.setEmail(email);
			
			System.out.println("Enter phone number of the user : ");
			data = scanner.next();
			user.setPhoneNumber(data);
		return user;	
		
		}
		
		public static boolean isUserExistInDatabase(String email,UserManager userManager) {
			List<User> userList = userManager.getAllUsers();
			for(User user : userList) {
		    	  if(user.getEmail().equals(email)) {
			       return true;
		    	  }
		       }
	 return false;
		}
}
