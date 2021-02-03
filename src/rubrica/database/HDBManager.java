package rubrica.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import csv.Contatto;

public class HDBManager {

	ArrayList<Contatto> c=new ArrayList<>();
	
	private void aggiornaContatti() {
		HybernateSessionManager hybernateSessionManager = null;
		Session session=hybernateSessionManager.getSession();
		String hqlSelect = "SELECT c FROM Contatto as c";
		Query<Contatto> query = session.createQuery(hqlSelect);
		 c=(ArrayList<Contatto>) query.list();
		 session.close();
	}
	public ArrayList<Contatto> returnList(){
		HybernateSessionManager hybernateSessionManager = null;
		Session session=hybernateSessionManager.getSession();
		String hqlSelect = "SELECT c FROM Contatto as c";
		Query<Contatto> query = session.createQuery(hqlSelect);
		
		 session.close();
		
		return (ArrayList<Contatto>) query.list();
		
	}
	public void  addContatto(Contatto c){
		HybernateSessionManager hybernateSessionManager = null;
		Session session=hybernateSessionManager.getSession();
		Transaction transaction = session.beginTransaction();
		session.save(c);
		transaction.commit();
		session.close();
	}
	public void stampaContatti() {
aggiornaContatti();
for(Contatto z:c) {
	System.out.println(z.getId()+": "+z.getNome()+" "+z.getCognome()+" "+z.getEmail()+" "+z.getTel());
}
		
	}
	private boolean controlloIndice(int indice) {
		for(Contatto z:c)
			if(z.getId()==indice)
				return true;
		return false;
	}
	public void stampaLista(ArrayList<Contatto> d) {
		for(Contatto z:d) {
			System.out.println(z.getId()+": "+z.getNome()+" "+z.getCognome()+" "+z.getEmail()+" "+z.getTel());
		}
	}
	public void ricercaContatti() {
		Scanner s=new Scanner(System.in);
		 ArrayList<Contatto> trovati= new ArrayList<>();
			HybernateSessionManager hybernateSessionManager = null;
			Session session=hybernateSessionManager.getSession();
			String query="";
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
				query="Select c from Contatto as c where name='"+nome+"'";
				break;
			case "2":
				do {
					System.out.println("digita il nome");
					nome=s.nextLine();
					System.out.println("digita il cognome");
					cognome=s.nextLine();
				}while(nome.length()==0||cognome.length()==0);
				query="Select c from Contatto as c where name='"+nome+"' and surname='"+cognome+"';";
				
				
				break;
			case "3":
				do {
					System.out.println("digita l'email");
					email=s.nextLine();
					
				}while(email.length()==0);
				query="Select c from Contatto as c where email='"+email+"' ;";
		
				break;
			case "4":
				do {
					
					System.out.println("digita il numero di telefono");
					telefono=s.nextLine();
					
				}while(telefono.length()==0);
				query="Select c from Contatto as C where telephone='"+telefono+"' ;";
				
				break;
			default: 
				System.out.println("Attenzio non avete selezionato nulla, ritorno al menu precedente..."); 
			break;
			}
			Query<Contatto> result = session.createQuery(query);
			stampaLista((ArrayList<Contatto>)result.list());
			
	}
	public void removeContatto() {
		stampaContatti();
		Scanner s= new Scanner(System.in);
		int indice=0;
		do {
		System.out.println("Indica l'indice di quale contatto vuoi andare a eliminare");
		 indice= Integer.parseInt(s.nextLine());
		if(!controlloIndice(indice))
			System.out.println("Attenzione l'indice inserito non è valido...");

		}while(!controlloIndice(indice));
		Contatto dael=null;
		for(Contatto z: c) {
			if(z.getId()==indice)
				dael=z;
		}
		
		
		HybernateSessionManager hybernateSessionManager = null;
		Session session=hybernateSessionManager.getSession();
		Transaction transaction = session.beginTransaction();
		String hqlDelete = "delete from Contatto where id="+indice;;
	
		session.delete(dael);
		transaction.commit();
		session.close();
		
		
	}
	public void modificaContatto() {
		
		stampaContatti();
		Scanner s= new Scanner(System.in);
		int indice=0;
		do {
		System.out.println("Indica l'indice di quale contatto vuoi andare a modificare");
		 indice= Integer.parseInt(s.nextLine());
		if(!controlloIndice(indice))
			System.out.println("Attenzione l'indice inserito non è valido...");

		}while(!controlloIndice(indice));
		HybernateSessionManager hybernateSessionManager = null;
		Session session=hybernateSessionManager.getSession();
		Transaction transaction = session.beginTransaction();
		String hqlSelect = "SELECT c FROM Contatto as c where id="+indice;
		Query<Contatto> query = session.createQuery(hqlSelect);
ArrayList<Contatto> trovati= (ArrayList<Contatto>) query.list();
Contatto c1=trovati.get(0);

			String nome,cognome,telefono,email;
			do {
			System.out.println("Inserisci il nome o premi invio per non inserirlo");
			 nome= s.nextLine();
			if(nome.length()>0) {
			c1.setNome(nome);
				
			}
			System.out.println("Inserisci il cognome o premi invio per non inserirlo");
			 cognome= s.nextLine();
			if(cognome.length()>0) {
				c1.setCognome(nome);
				
			}
			System.out.println("Inserisci il email o premi invio per non inserirlo");
			 email= s.nextLine();
			if(email.length()>0) {
				c1.setEmail(email);
				
			}
			System.out.println("Inserisci il telefono o premi invio per non inserirlo");
			 telefono= s.nextLine();
			if(telefono.length()>0) {
			c1.setTel(telefono);
			}
				if(!(nome.length()>0||cognome.length()>0||telefono.length()>0||email.length()>0)) {
					System.out.println("Attenzione non hai inserito nessun campo");
					System.out.println("Digita 0 se vuoi ritornare al menu precedente senza effettuare modifiche");
					System.out.println("Altrimenti premi un tasto qualsisi escluso 0 per riprovare la modifica");
					String stop=s.nextLine();
					if(stop.equalsIgnoreCase("0")){
						break;
					}
				
				}else {
					session.save(c1);
					transaction.commit();
				session.close();
				
					
				}
					
			}while(!(nome.length()>0||cognome.length()>0||telefono.length()>0||email.length()>0));
		
	}
}
