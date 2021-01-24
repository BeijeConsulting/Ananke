
import java.io.*;

public class ReadSubDirectory  {
	
	public static void readFiles(File f) throws Exception {
		for(File s: f.listFiles()) {
		if(s.isDirectory()) {
			System.out.println("DIRECTORY: " + s.getName());
			readFiles(s);
		} else {
			System.out.println("File: " + s.getName());
		}
		
		}
	}
	
	

public static void main(String[] args) throws Exception{
		ReadSubDirectory.readFiles(new File("/Users/Gianni"));
		
		
	
		
		
	}

}
