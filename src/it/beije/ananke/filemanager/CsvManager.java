package it.beije.ananke.filemanager;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CsvManager {

	public static void main(String[] args) throws Exception {

		String root = "C:/";
		File file = new File(root);
		System.out.println("file : " + file.getAbsolutePath());
		System.out.println("file exists ? " + file.exists());
		System.out.println("is a dir ? " + file.isDirectory());
		String[] names = file.list();
		ArrayList<ArrayList<String>> directories = new ArrayList<>();

		File[] files = file.listFiles();

		for (File file1 : files) {
			files = file1.listFiles();
			while(file1.isDirectory()) {

//				System.out.println(Arrays.asList(file2));
//				file2 = file2.li
			}
			System.out.println(file1);
		}


//		while(file.isDirectory()) {
//			String directory = root;
//			System.out.println(directory);
//			directory += names[0];
//
//		}
//		for(int i = 0; i < names.length; i++) {
//			ArrayList<String> subDirectories = new ArrayList<>();
//			String directory = root + names [i];
//			subDirectories.add(directory);
//			directories.add(subDirectories);
//			System.out.println(directory);
//
//			for(int j = 0; j < directories.size(); j++) {
//
//			}
//
//
//		}


//		for(String directory : directories) {
//			File files = new File(directory);
//			System.out.println("File: " + files.getAbsolutePath());
//
//		}


	}

}

/*
paperina;3484334654;paperina@pluto.net;
paperino;34843489654;paperino@pippo.it;
pippo;346789654;pippo@pippo.it;
pluto;346782312;pluto@pluto.com;
*/
