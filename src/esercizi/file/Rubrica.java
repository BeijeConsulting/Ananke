package esercizi.file;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;

import org.xml.sax.SAXException;

import csv.*;
import xmlFile.XmlManager;

public class Rubrica {

	private ArrayList<Contatto> contatti;
	private XmlManager gestoreXml;
	private ManagerCsv gestoreCsv;
	

	public Rubrica() {
		super();
try {
	gestoreCsv=new ManagerCsv();
	gestoreXml=new XmlManager();
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
contatti=new ArrayList<>();


	}
	 public void readContattiCsv() {
		 Scanner s = new Scanner(System.in);
		 System.out.println("Inserire il nuovo path del file csv");
			String pathcsv= s.next();
			ArrayList<Contatto> app=gestoreCsv.readCsv(pathcsv);
			for(Contatto c: app)
				contatti.add(c);
		 
	 }
	 public void readContattiXml() {
		 Scanner s = new Scanner(System.in);
		 System.out.println("Inserire il nuovo path del file xml");
			String pathxml= s.next();
			ArrayList<Contatto> app;
			try {
				app = gestoreXml.readXml(pathxml);
				for(Contatto c: app)
					contatti.add(c);

			} catch (ParserConfigurationException | IOException | SAXException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
					 
	 }
	
public void importXmlExsportCsv() {
	Scanner s = new Scanner(System.in);
	System.out.println("Inserire il path del file xml");
	String pathxml= s.next();

	System.out.println("Inserire il nuovo path del nuovo file csv");
	String pathcsv= s.next();
	ArrayList<Contatto> app;
	try {
		app = (ArrayList<Contatto>)gestoreXml.readXml(pathxml);
		gestoreCsv.salvaListaContatti(app, pathcsv);
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


public void importCsvExsportXml() {
	Scanner s = new Scanner(System.in);
	System.out.println("Inserire il path del file csv");
	String pathcsv= s.next();


	System.out.println("Inserire il nuovo path del nuovo file xml");
	String pathxml= s.next();

	ArrayList<Contatto> app;
	try {
		app = (ArrayList<Contatto>)gestoreCsv.readCsv(pathcsv);
			gestoreXml.writeArray(pathxml, app);
			

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
public boolean isEmpty() {
	return contatti.isEmpty();
}
public void stampaContatti() {
	if(isEmpty()) {
		System.out.println("----Attualmente la tua rubrica è vuota----\n");
	}
	else {
	for(int i=0;i<contatti.size();i++) {
		System.out.println(i+" : "+contatti.get(i).getNome()+" "+contatti.get(i).getCognome()+contatti.get(i).getEmail()+contatti.get(i).getTel()); 
	}
	}
}
public void addContatto() {
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
	 if(nome.length()>0||cognome.length()>0||email.length()>0||telefono.length()>0) {
		 Contatto c= new Contatto(nome,cognome,telefono,email);
		 contatti.add(c);
		 stop=false;

	 }
	 else
		 System.out.println("Attenzione almeno un campo deve essere compilato");
	}while(stop);
	 System.out.println("|-----Contatto aggiunto correttamente-----|");
}
public void removeContatto() {
stampaContatti();
if(!isEmpty()) {
	Scanner s = new Scanner(System.in);
	System.out.println("Digita l'indice del contatto che vuoi eliminare");
	
	int indice= Integer.parseInt(s.nextLine());
	if(indice>=0&&indice< contatti.size()) {
		contatti.remove(indice);
	}else
		System.out.println("hai inserito un numero non valido");
}
}

public void salvaContattiCsv() {
	Scanner s = new Scanner(System.in);
	System.out.println("Inserire il nuovo path del nuovo file csv");
	String pathcsv= s.next();
	gestoreCsv.salvaListaContatti(contatti, pathcsv);
		
}
public void salvaContattiXml() {
	Scanner s = new Scanner(System.in);
	System.out.println("Inserire il nuovo path del nuovo file xml");
	String pathxml= s.next();
try {
	gestoreXml.writeArray(pathxml, contatti);
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

public ArrayList<Contatto> ricercaContatto() {
	Scanner s=new Scanner(System.in);
	 ArrayList<Contatto> trovati= new ArrayList<>();

	if(!isEmpty()) {
		String nome,cognome,email,telefono;
		boolean stop=true;
		System.out.println("Seleziona 1 per effettuare la ricerca in base al nome");
		System.out.println("Seleziona 2 per effettuare la ricerca in base al nome e al cognome");
		System.out.println("Seleziona 3 per effettuare la ricerca in base all'email");
		System.out.println("Seleziona 4 per effettuare la ricerca in base al telefono");

		 String scelta=s.nextLine();
		switch(scelta) {
		case "1":
			do {
				System.out.println("digita il nome");
				nome=s.nextLine();
				
			}while(nome.length()==0);
			for(Contatto c: contatti) {
				if(c.getNome().equalsIgnoreCase(nome))
					trovati.add(c);		
			}
			break;
		case "2":
			do {
				System.out.println("digita il nome");
				nome=s.nextLine();
				System.out.println("digita il cognome");
				cognome=s.nextLine();
			}while(nome.length()==0||cognome.length()==0);
			for(Contatto c: contatti) {
				if(c.getNome().equalsIgnoreCase(nome)&&c.getCognome().equalsIgnoreCase(cognome))
					trovati.add(c);		
			}
			break;
		case "3":
			do {
				System.out.println("digita l'email");
				email=s.nextLine();
				
			}while(email.length()==0);
			for(Contatto c: contatti) {
				if(c.getEmail().equalsIgnoreCase(email))
					trovati.add(c);		
			}
			break;
		case "4":
			do {
				System.out.println("digita il numero di telefono");
				telefono=s.nextLine();
				
			}while(telefono.length()==0);
			for(Contatto c: contatti) {
				if(c.getTel().equalsIgnoreCase(telefono))
					trovati.add(c);		
			}
			break;
		
		
		}
		
		
			 
				 
		

	}else
		System.out.println("Attenzione la ricerca non è possibile. Nessun contatto da ricercare");
	printList(trovati);
 return trovati;
	
}

public void replaceContatto() {
	stampaContatti();
	if(!isEmpty()) {
	Scanner s = new Scanner(System.in);
	System.out.println("Digita l'indice del contatto di cui vuoi effetuare  la modifica");
	
	int indice= Integer.parseInt(s.nextLine());
	if(indice>=0&&indice< contatti.size()) {
		System.out.println("Inserisci il nome o lascia uno spazio bianco per non effettuare la modifica");
		String nome=s.nextLine();
		if(nome.length()>0) {
			contatti.get(indice).setNome(nome);
		}
		System.out.println("Inserisci il cognome o lascia uno spazio bianco per non effettuare la modifica");
		String cognome=s.nextLine();
		if(cognome.length()>0) {
			contatti.get(indice).setCognome(cognome);
		}
		System.out.println("Inserisci l'email  o lascia uno spazio bianco per non effettuare la modifica");
		String email=s.nextLine();
		if(email.length()>0) {
			contatti.get(indice).setEmail(email);
		}
		System.out.println("Inserisci il nuovo numero di telefono  o lascia uno spazio bianco per non effettuare la modifica");
		String telefono=s.nextLine();
		if(telefono.length()>0) {
			contatti.get(indice).setTel(telefono);
		}
		
	}
	}
}
public void printList(ArrayList<Contatto> z) {
	for(int i=0;i<z.size();i++) {
		System.out.println(i+" : "+z.get(i).getNome()+" "+z.get(i).getCognome()+" "+z.get(i).getEmail()+" "+z.get(i).getTel()); 
	}
}
public void menu() {
	
	StringBuilder s=new StringBuilder();
	s.append("==================================================================================\n");
	s.append("				     Rubrica\n");
	s.append("==================================================================================\n");



	s.append("||	-Digita 1 per caricare da file csv la rubrica 				||\n");
	s.append("||	-Digita 2 per caricare da file xml la rubrica 				||\n");
	s.append("||	-Digita 3 per importare un file csv ed esportare un file xml 		||\n");
	s.append("||	-Digita 4 per importare un file XML ed esportare un file CSV 		||\n");
	s.append("||	-Digita 5 per aggiungere un nuovo contatto 				||\n");
	s.append("||	-Digita 6 per modificare un  contatto 					||\n");
	s.append("||	-Digita 7 per rimuovere un  contatto 					||\n");
	s.append("||	-Digita 8 per ricercare un lista di contatti				||\n");
	s.append("||	-Digita q se vuoi uscire senza salvare le modifiche			||\n");
	s.append("||	-Digita scsv se vuoi salvere su un file csv				||\n");
	s.append("||	-Digita sxml se vuoi salvere su un file xml				||\n");
	s.append("||	-Digita v se vuoi visualizzare i contatti				||\n");
	s.append("==================================================================================\n");

	s.append("==================================================================================\n");

	System.out.println(s.toString());

}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String scelta="";
		Scanner sc=new Scanner(System.in);
		Rubrica r=new Rubrica();

//Stampa menu
while(!scelta.equalsIgnoreCase("q")){
	r.menu();
 scelta=sc.next();
switch(scelta) {

case "1" :
	r.readContattiCsv();	
	break;
case "2" :
	r.readContattiXml();	
	break;
case "3" :
	r.importCsvExsportXml();	
	break;
case "4" :
	r.importXmlExsportCsv();	
	break;
case "5" :
	r.addContatto();
	break;
case "6" :
r.replaceContatto();
	break;
case "7" :
r.removeContatto();
	break;
case "8" :
r.ricercaContatto();
	break;
case "scsv" :
r.salvaContattiCsv();
	break;
case "sxml" :
r.salvaContattiXml();
	break;
case "v" :
r.stampaContatti();
	break;
	}
	}

	}
}
