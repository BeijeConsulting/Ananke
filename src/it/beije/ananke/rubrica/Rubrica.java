package it.beije.ananke.rubrica;


import java.util.*;
import java.io.*;
import java.sql.*;

public class Rubrica {

	public List<Contatti> r = new ArrayList<Contatti>();
	public String path;
	
	public Rubrica() {
	
		}
	
	

	
	
	
	public List<Contatti> addContact() {
		List<Contatti> l = new ArrayList<>();
		String continua;
		do {
		String n,s,e,phone;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("Inserisci nome: ");
		 n =sc.nextLine();
		System.out.println("Inserisci cognome: ");
		 s = sc.nextLine();
		 
		 System.out.println("Inserisci email: ");
		 e = sc.nextLine();
		 
		
		System.out.println("Inserisci cellulare: ");
		
		phone = sc.nextLine();
		
		
		if(!(n.isEmpty() && s.isEmpty() && e.isEmpty() && phone.isEmpty())) {
		l.add(new Contatti(n,s,e,phone));
		
		} else {
		System.out.println("Mi dispiace, l'utente deve contenere almeno un campo.");
		}
		System.out.println("Vuoi continuare ad inserire? si/no");
		 continua = sc.next();
		}while(!continua.equalsIgnoreCase("no"));
		return l;
	}
	
	public List<Contatti> searchContact(String campo) {
		List<Contatti> rubrica = new ArrayList<>();
		for(Contatti c: this.r) {
			if(c.searchProperties(campo)) {
				rubrica.add(c);
			}
		}
		return rubrica;
	}
	
	public void deleteContact(String email) {
		int i=0;
		Iterator<Contatti> it = this.r.iterator();
		while(it.hasNext()) {
			Contatti c = it.next();
			if(c.email.equals(email)) {
				i++;
				
			}
		}
		if(i == 0) {
		System.out.println("Mi dispiace, ma il contatto non è presente in lista");
		} else {
			System.out.println("Il contatto è stato eliminato");
			
		}
		
		
	}



	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		int i=1;
		
	for(Contatti c : this.r) {
		sb.append("Contatto: " + Integer.toString(i++) + "\n");
		
		sb.append("Name: " + c.getName()).append(" Surname: " + c.getSurname()).append(" Email: " + c.getEmail()).append(" Telephone: " + c.getTelephone());
	sb.append("\n");
	}
	
	return sb.toString();
	}
	
	
	
	

}
