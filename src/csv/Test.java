package csv;

import java.util.Scanner;

public class Test {

	public static void main(String[] args) throws Exception{
		// TODO Auto-generated method stub
ManagerCsv c=new ManagerCsv();
Scanner s = new Scanner(System.in);
String st="";
System.out.println("Benvenuto/a nella rubrica");
do {
	System.out.println("digita q se vuoi uscire senza salvare");
System.out.println("digita 1 se vuoi inserire un contatto");
System.out.println("digita 2 se vuoi salvare nel file");
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
	c.aggiungiContatto(new Contatto(nome,cognome,telefono,email));
	
}
if(st.equals("2")) {
	c.salvaContatti();
}

}while(!st.equalsIgnoreCase("q"));

s.close();


	}

}
