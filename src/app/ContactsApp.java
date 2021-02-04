package contatti;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;


public class ContactsApp {

	public static void main(String[] args) throws IOException, SQLException {
		
		Scanner scanner = new Scanner(System.in);
		String fileName = "/home/faheem/Users.txt";
		FileWriter fileWriter = new FileWriter(fileName,true); 
		FileReader fileReader = new FileReader(fileName);
		UserManager userManager = new UserInFileManager(fileWriter);
		String i;
		mainMenu();
		i= scanner.nextLine();
		chooseFromMenu(fileReader,scanner, userManager, i);
		
		
		fileWriter.flush();
		fileWriter.close();
		scanner.close();
		
	}
	
	private static User getUserData(Scanner scanner) {
		User newUser= new User();
		System.out.println("Enter First name : ");
		newUser.setFirstName(scanner.nextLine());
		
		System.out.println("Enter Last name : ");
		newUser.setLastName(scanner.nextLine());
		
		System.out.println("Enter email : ");
		newUser.setEmail(scanner.nextLine());
		
		System.out.println("Enter phone number : ");
		newUser.setPhoneNumber(scanner.nextLine());
		
		System.out.println("thank you, user has been added successfully");
		
		return newUser;
	}
	
	private static void chooseFromMenu(FileReader fileReader,Scanner scanner, UserManager userManager, String i) throws IOException, SQLException {
		User user;
		switch (i) {
		case "1" :
			 user = getUserData(scanner);
			 userManager.setUser(user);
			 break;
		
		case "2" :
			user = getUserNameToRemove();
			break;
		case "3" :
		 	user = updateUser();
		    break;
		case "4" :
		 	readFile(fileReader);
		    break;
		default :
		System.out.println("Thank you! bye");
		break;
			
		}
	}
	
	private static void readFile(FileReader fileReader) throws IOException {
		while(fileReader.ready()) {
			System.out.print((char)fileReader.read());
		}
		
	}

	private static void mainMenu() {
		System.out.println("Choose from the following Menu!");
		System.out.println();
		System.out.println("Press 1 to insert a User in File!");
		System.out.println("Press 2 to remove a User from File!");
		System.out.println("Press 3 to update a User in File!");
		System.out.println("Press 4 to read File!");
		System.out.println("Press 0 to exit!");
	}
	
private static User updateUser() {
		
		return null;
	}

	private static User getUserNameToRemove() {
		// TODO Auto-generated method stub
		return null;
	}
}
