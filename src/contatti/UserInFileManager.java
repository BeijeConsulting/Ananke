package contatti;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class UserInFileManager implements UserManager {
 private FileWriter fileWriter;
 
	
	public  UserInFileManager(FileWriter fileWriter) throws IOException {
		this.fileWriter = fileWriter;
	}
	@Override
	public void setUser(User user) throws IOException {
		this.fileWriter.write(user.toString());
	}
	

	@Override
	public void removeUser(String email) {
		

	}

	@Override
	public void updateUser(User user) {
		

	}
	@Override
	public User getUser(String FirstName) {
		
		return null;
	}
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}

}
