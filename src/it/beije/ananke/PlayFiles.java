package it.beije.ananke;

import java.util.*;
import java.io.*;

public class PlayFiles {

	public static void main(String[] args) throws Exception{
		
	
		// READING FILES
		
		File root = new File("C:/Users/Padawan03/Desktop/prova.txt");
		
		System.out.println(root.getPath());
		
		if(root.exists() && root.isFile())
		{
			FileReader reader = new FileReader(root);
			
			BufferedReader br = new BufferedReader(reader);
			
			ArrayList<String> lines = new ArrayList<>();
			
			while(br.ready())
			{
				lines.add(br.readLine());			
			}		
			
			br.close();
			reader.close();
			
			for(String row : lines)
			{
				System.out.println(row);
			}
		}
	
			//Writing the content of a file into another
		
			if(new File("C:/Users/Padawan03/Desktop/prova2.txt").exists())
			{
				File prova2 = new File("C:/Users/Padawan03/Desktop/prova2.txt");
				
				FileReader reader2 = new FileReader(root);
				
				BufferedReader br2 = new BufferedReader(reader2);
							
				FileWriter filewr = new FileWriter(prova2);
				
				ArrayList<String> lines2 = new ArrayList<>();
				
				while(br2.ready())
				{
					lines2.add(br2.readLine());
				}		
				
				for(String type : lines2)
				{
					filewr.write(type);
				}
				
				br2.close();
				filewr.flush();
				filewr.close();
				
				
				
			}
	}
}
