package csv;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import xmlFile.*;

public class Test {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
		ManagerCsv csv=new ManagerCsv();

XmlManager xml =new XmlManager();

Scanner s = new Scanner(System.in);
String st="";
System.out.println("Benvenuto/a nella rubrica");
do {
	System.out.println("digita Q se vuoi uscire senza salvare");
System.out.println("digita 1 se vuoi inserire un contatto nella rubrica csv");
System.out.println("digita 2 se vuoi salvare nel file nella rubrica csv");
System.out.println("digita 3 se vuoi importare un file  rubrica csv ed esportare il file in xml");
System.out.println("digita 4 se vuoi importare un file  rubrica xml ed esportare il file in csv");
System.out.println("digita 5 se vuoi leggere un file  rubrica xml");


 st=s.next();
if(st.equals("1")) {
	System.out.println("digita il nome");
	String nome=s.next();
	System.out.println("digita il cognome");
	String cognome=s.next();
	System.out.println("digita email");
	
	String email=s.next();
	System.out.println("digita il numero di telefono");
	String telefono=s.next();
	csv.aggiungiContatto(new Contatto(nome,cognome,telefono,email));
	
}
if(st.equals("2")) {
	csv.salvaContatti();
}
if(st.equals("3")) {
	System.out.println("Inserire il path del file rubrica.csv");
	String pathcsv= s.next();
	System.out.println("Inserire il path del nuovo file xml");
	String pathxml= s.next();
	List app= csv.readCsv(pathcsv);
	xml.writeArray(pathxml,app);

}
if(st.equals("4")) {
	
	System.out.println("Inserire il path del file xml");
	String pathxml= s.next();

	System.out.println("Inserire il nuovo path del nuovo file csv");
	String pathcsv= s.next();
	ArrayList<Contatto> app= (ArrayList<Contatto>) xml.readXml(pathxml);
	csv.salvaListaContatti(app, pathcsv);
}
if(st.equals("5")) {

}
if(st.equals("6")) {

}
}while(!st.equalsIgnoreCase("q"));

s.close();


	}

}
