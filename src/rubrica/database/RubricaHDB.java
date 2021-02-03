package rubrica.database;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import csv.*;
import dbManager.DbManager;
import xmlFile.XmlManager;

public class RubricaHDB {

	private ArrayList<Contatto> contatti;
	private XmlManager gestoreXml;
	private ManagerCsv gestoreCsv;
	private HDBManager gestoreDB;

	public RubricaHDB() {
		super();
try {
	gestoreCsv=new ManagerCsv();
	gestoreXml=new XmlManager();
	gestoreDB=new HDBManager();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
contatti=new ArrayList<>();


	}
public void printList(ArrayList<Contatto> z) {
	for(int i=0;i<z.size();i++) {
		System.out.println(i+" : "+z.get(i).getNome()+" "+z.get(i).getCognome()+" "+z.get(i).getEmail()+" "+z.get(i).getTel()); 
	}
}
public void menuDB() {
	
	StringBuilder s=new StringBuilder();
	s.append("==================================================================================\n");
	s.append("				   Menu Database\n");
	s.append("==================================================================================\n");
	s.append("||	-Digita 1 per importare nel databse un file csv  			||\n");
	s.append("||	-Digita 2 per importare nel databse un file xml  			||\n");
	s.append("||	-Digita 3 per esportare il database in un file csv			||\n");
	s.append("||	-Digita 4 per esportare il database in un file xml			||\n");
	s.append("||	-Digita 5 per aggiungere un nuovo contatto 				||\n");
	s.append("||	-Digita 6 per modificare un  contatto 					||\n");
	s.append("||	-Digita 7 per rimuovere un  contatto 					||\n");
	s.append("||	-Digita 8 per ricercare un lista di contatti				||\n");

	s.append("||	-Digita q se vuoi tornare al menu principale				||\n");
	s.append("||	-Digita v se vuoi visualizzare i contatti				||\n");
	s.append("==================================================================================\n");

	s.append("==================================================================================\n");

	System.out.println(s.toString());

}

public void importDBExsportCsv(){
	Scanner sc=new Scanner(System.in);
	System.out.println("Inserisci il nome del file csv su dove vuoi salvare i contatti del db");
	String path=sc.nextLine();
	
	ArrayList<Contatto>app=gestoreDB.returnList();
	gestoreCsv.salvaListaContatti(app, path);
}
public void aggiungiContatoDB() {
	Scanner s = new Scanner(System.in);
	String nome,cognome,email,telefono;
	boolean stop=true;
	do {
	System.out.println("Inserisci il nome o lascia uno spazio bianco per non effettuare l'insermento");
	 nome=s.nextLine();
	
	System.out.println("Inserisci il cognome o lascia uno spazio bianco per non effettuare l'insermento");
	 cognome=s.nextLine();
	
	System.out.println("Inserisci l'email  o lascia uno spazio bianco per non effettuare l'insermento");
	 email=s.nextLine();
	
	System.out.println("Inserisci il nuovo numero di telefono  o lascia uno spazio bianco per non effettuare l'insermento");
	 telefono=s.nextLine();
	 if(nome.trim().length()>0||cognome.trim().length()>0||email.trim().length()>0||telefono.trim().length()>0) {
		 Contatto c= new Contatto(nome,cognome,telefono,email);
gestoreDB.addContatto(c);
stop=false;
	 }
	 else
		 System.out.println("Attenzione almeno un campo deve essere compilato");
	}while(stop);
	 System.out.println("|-----Contatto aggiunto correttamente-----|");
	
}
public void importCsvToDB() {
	Scanner sc=new Scanner(System.in);
	 File file=null;
	 String pathcsv;
	 
	 do {
		System.out.println("Inserisci il nome del file csv");
		 pathcsv= sc.next();
		file= new File(pathcsv);
		if(!file.exists()) {
			System.out.println("Attenzione il file inserito non esiste");
		}
	 }while(!file.exists());
	ArrayList<Contatto> c=gestoreCsv.readCsv(pathcsv);
		for(Contatto copy: c)
	gestoreDB.addContatto(copy);
	System.out.println("Tutti i "+c.size()+" contatti sono stati aggiunti al DB");
}
public void importDBExsportXml() {
	Scanner sc=new Scanner(System.in);
	System.out.println("Inserisci il nome del file xml da creare");
	String path=sc.nextLine();
	
	ArrayList<Contatto>app=gestoreDB.returnList();
	try {
		gestoreXml.writeArray(path, app);
	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (TransformerException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}
public void importXMLToDB() {

	Scanner sc=new Scanner(System.in);
	 String pathxml;
	 File file=null;
	 do {
	 System.out.println("Inserire il nuovo path del file xml");
		 pathxml= sc.next();
			file= new File(pathxml);
			if(!file.exists()) {
				System.out.println("Attenzione il file inserito non esiste");
			}
	 }while(!file.exists());
	ArrayList<Contatto> c;
	try {
		c = gestoreXml.readXml(pathxml);
		for(Contatto copy: c)
			gestoreDB.addContatto(copy);
		if(c.size()!=0)
			System.out.println("Tutti i "+c.size()+" contatti sono stati aggiunti al DB");
		else
			System.out.println("---- Attenzione non è stato trovato nessun contatto------");

	} catch (ParserConfigurationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (SAXException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		
}
public void interfacciaDB() {
	
	Scanner sc=new Scanner(System.in);
String scelta="";
while(!scelta.equalsIgnoreCase("q")){
	menuDB();
 scelta=sc.next();
switch(scelta) {

case "1" :
	importCsvToDB();
	break;
case "2" :
	importXMLToDB();
	break;
case "3" :
	importDBExsportCsv();
	break;
case "4" :
	importDBExsportXml();
	break;
case "5" :
	aggiungiContatoDB();
	break;
case "6" :
gestoreDB.modificaContatto();
	break;
case "7" :
	gestoreDB.removeContatto();
	break;
case "8" :
gestoreDB.ricercaContatti();
	break;
case "9" :
	break;
case "v" :
gestoreDB.stampaContatti();
	break;
	}
	}
	
	
	
	
}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String scelta="";
		Scanner sc=new Scanner(System.in);
		RubricaHDB r=new RubricaHDB();

//Stampa menu
while(!scelta.equalsIgnoreCase("q")){
	r.interfacciaDB();
 scelta=sc.next();

	
	

	}
}
}