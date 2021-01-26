package esercizi.file;


import java.io.*;
public class SearchFile {
public void stampaSottoCartelle() throws Exception{
	
File k= new File("esercizi/file/SearchFile.class");
System.out.println("eeeeeee"+k.exists());
	File [] f= k.listFiles();
	for(File c : f)
		System.out.println(c.getCanonicalPath());
}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
