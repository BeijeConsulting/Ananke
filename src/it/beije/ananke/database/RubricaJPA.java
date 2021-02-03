package it.beije.ananke.database;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import it.beije.ananke.file.ContattoMio;
import it.beije.ananke.file.Rubrica;

public class RubricaJPA {
	public Rubrica r = new Rubrica();
	public final static String  STD_PATH= "C:\\Users\\Padawan04\\Desktop\\Snippets";
	
	public static void main(String[] args) throws IOException {
		RubricaJPA rub = new RubricaJPA();
		DataManager.menu();
		Scanner s = new Scanner(System.in);
		String st = s.next();

		while (!st.equalsIgnoreCase("6")) {
			rub.choice(st, s);
			DataManager.menu();
			st = s.next();
		}
		
		
		System.out.println("Arrivederci!");
		s.close();
	
	}
	
	public void choice(String i, Scanner s) throws IOException {
		EntityManager em = RubricaEntityManager.getEntityManager();
		switch(i) {
			case "0": stampa(lettura(em)); break;
			case "1": inserisci(em, s); break;
			case "2": modifica(em, s); break;
			case "3": eliminazione(em, s); break;
			case "4": ricerca(em, s); break;
			case "5": salvataggioFile(s, em);break;
			case "6": break;
			case "7": break;
			default: System.out.println("L'opzione selezionata non è valida.");
		}
		em.close();
	}
	
	
	public void stampa(List<ContattoMio> contatti) {
		if(contatti.size() != 0) {
			for(ContattoMio c : contatti) {
				System.out.println(c.toString());
			}
		}else {
			System.out.println("La rubrica è vuota.");
		}
	}
	
	
	public List<ContattoMio> lettura(EntityManager em){
		List<ContattoMio> contatti = new ArrayList<>();
		String jqlSelect = "SELECT c FROM ContattoMio as c";
		Query query = em.createQuery(jqlSelect);
		contatti = query.getResultList();
		//stampa(contatti);
		return contatti;
	}
	
	
	public List<ContattoMio> scan(EntityManager em, Scanner s){
		System.out.println("Scegliere in quale campo cercare:\n"
				+ "0: ID\n1: NOME\n2: COGNOME\n3: EMAIL\n4: TELEFONO\n"
				+ "(Attenzione il numero di telefono deve contenere eventuali prefissi)");
		String str = s.next();
		List<ContattoMio> contatti = new ArrayList<>();
		try {
			int indice = Integer.parseInt(str);
			
			while(indice >= 5) {
				System.out.println("Voce non valida, provare di nuovo.");
				str = s.next();
				indice = Integer.parseInt(str);
			}
			String search = "";
			
			switch(indice) {
				case 0: search = "id"; break;
				case 1: search = "name"; break;
				case 2: search = "surname"; break;
				case 3: search = "email"; break;
				case 4: search = "telephone"; break;
			}
			
			System.out.println("Inserire il valore da cercare: ");
			str = s.next();
			
			String select = "FROM ContattoMio c WHERE c." + search + " = '" + str + "'";
			Query query = em.createQuery(select);
			contatti = query.getResultList();
			
		} catch (NumberFormatException e) {
			System.out.println("Il valore inserito non è riconosciuto, sarai reindirizzato al menù.");
		}
		
		return contatti;
	}
	
	
	public void ricerca(EntityManager em, Scanner s) {
		List<ContattoMio> contatti = scan(em, s);
		if(contatti.size() == 0) {
			System.out.println("Non ci sono elementi che corrispondono ai criteri di ricerca.");
		}else {
			stampa(contatti);
		}
	}
	
	
	public void inserisci(EntityManager em, Scanner s) throws IOException {
		EntityTransaction et = em.getTransaction();
		et.begin();
		ContattoMio contatto = r.defContatto(s);
		em.persist(contatto);
		et.commit();
		System.out.println("La voce è stata inserita correttamente.");
	}
	
	
	public void eliminazione(EntityManager em, Scanner s) {
		List<ContattoMio> contatti = scan(em, s);
		if(contatti.size() == 0) {
			System.out.println("Non ci sono elementi che corrispondono ai criteri di ricerca.");
		}else {
			for(ContattoMio c : contatti) {
				EntityTransaction et = em.getTransaction();
				et.begin();
				em.detach(c);
				et.commit();
				System.out.println("I campi selezionai sono stati eliminati.");
			}
		}
	}
	
	
	public void modifica(EntityManager em, Scanner s) {
		List<ContattoMio> contatti = scan(em, s);
		if(contatti.size() == 0) {
			System.out.println("Non ci sono elementi che corrispondono ai criteri di ricerca.");
		}else {
			stampa(contatti);
			System.out.println("Scegliere il campo da modificare: \n"
					+ "1: NOME\n2: COGNOME\n3: EMAIL\n4: TELEFONO\n"
					+ "(Attenzione il numero di telefono deve contenere eventuali prefissi)");
			String str = s.next();
			
			try {
				int i = Integer.parseInt(str);
				while(i <= 0 || i > 4) {
					System.out.println("Scelta non valida. Inserire un nuovo valore.");
					str = s.next();
					i = Integer.parseInt(str);
				}
				
				System.out.println("Inserire il nuovo valore da salvare: ");
				String nuovo = s.next();
				
				for(ContattoMio c : contatti) {
					EntityTransaction et = em.getTransaction();
					et.begin();
					switch(i) {
						case 1: c.setName(nuovo); break;
						case 2: c.setSurname(nuovo); break;
						case 3: c.setEmail(nuovo); break;
						case 4: c.setTelephone(nuovo); break;
					}	
					em.persist(c);
					et.commit();
					System.out.println("Le modifiche sono state salvate.");
				}
				
			}catch(NumberFormatException e) {
				e.printStackTrace();
				System.out.println("Il valore inserito non è un numero. Le modifiche non saranno salvate.");
			}
			
			
		}
	}
	
	
	public void salvataggioFile(Scanner s, EntityManager em) {
		System.out.println("Fornire il path del file.\n(Inserire 0 se si desidera creare un nuovo file.\n"
				+ "Tale file verrà creato nella cartella '" + STD_PATH + "')");
		String path = s.next();
		String nome = "";
		
		if(path.trim().equalsIgnoreCase("0")) {
			System.out.println("Inserire il nome da dare al nuovo file: ");
			nome = s.next();
			path = STD_PATH + "\\" + nome;
		}	
		
		System.out.println("In che formato si vuole salvare la rubrica?");
		String format = s.next();
		
		List<ContattoMio> lista = new ArrayList<>();
		lista = lettura(em);
		
		try {
			if(format.equalsIgnoreCase("csv")){
				r.scriviCsv(path, lista);
			}else if(format.equalsIgnoreCase("xml")){
				r.scriviXml(path, lista);
			}else {
				System.out.println("Il formato selezionato non è disponibile.");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
}
