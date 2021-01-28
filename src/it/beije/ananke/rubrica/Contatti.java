package it.beije.ananke.rubrica;


import java.io.*;
import java.util.*;

public class Contatti {
	
	public String name;
	public String surname;
	public String email;
	public String cell;
	
	public Contatti() {
		
	}
	public Contatti(String name, String surname, String email, String cell) {
		this.name= name;
		this.surname = surname;
		this.email = email;
		this.cell = cell;
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

	public String getCell() {
		return cell;
	}

	public void setCell(String cell) {
		this.cell = cell;
	}

	public boolean searchProperties(String s) {
		if(this.cell == s || this.email == s || this.name == s || this.surname == s ) {
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
		sb.append(this.getCell());
		
		return sb.toString();
	}
	
	





	
}
