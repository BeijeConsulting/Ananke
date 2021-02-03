package it.beije.ananke.rubrica;


import java.io.*;
import java.util.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contatti")

public class Contatti {
	
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;

	@Column(name="surname")
	private String surname;

	@Column(name="email")
	private String email;
	

	@Column(name="telephone")
	private String telephone;
	
	

	
	
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

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public boolean searchProperties(String s) {
		if(this.telephone.equals(s) || this.email.equalsIgnoreCase(s) || this.name.equalsIgnoreCase(s) || this.surname.equalsIgnoreCase(s) ) {
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
