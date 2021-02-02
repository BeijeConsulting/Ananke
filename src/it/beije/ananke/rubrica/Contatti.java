package it.beije.ananke.rubrica;


import java.io.*;
import java.util.*;

public class Contatti {
	
	public int id;
	public String name;
	public String surname;
	public String email;
	public String telephone;
	
	public Contatti() {
		
	}
	public Contatti(String name, String surname, String email, String telephone) {
		this.name= name;
		this.surname = surname;
		this.email = email;
		this.telephone = telephone;
	}
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getSurname() {
		return surname;
	}


	public void setSurname(String surname) {
		this.surname = surname;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setCell(String cell) {
		this.telephone = cell;
	}

	public boolean searchProperties(String s) {
		if(this.telephone == s || this.email == s || this.name == s || this.surname == s ) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getName()).append(",");
		sb.append(this.getSurname()).append(",");
		sb.append(this.getEmail()).append(",");
		sb.append(this.getTelephone());
		
		return sb.toString();
	}
	
	





	
}
