package it.beije.ananke.hib.prova;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contatti")
public class Contatto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String surname;
	
	@Column(name="telephone")
	private String telephone;
	
	@Column(name="email")
	private String email;
	
	public Contatto() {	
	}
	
	public Contatto(String name, String surname, String telephone, String email) {
		this.name=name;
		this.surname=surname;
		this.telephone=telephone;
		this.email=email;
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
	
	public String getTelephone() {
		return telephone;
	}
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	
	public String toString() {
//		StringBuilder builder = new StringBuilder("[");
//		builder.append("name : ").append(name);
//		builder.append("surname : ").append(surname);
//		builder.append("telephone : ").append(telephone);
//		builder.append("email : ").append(email);
//		builder.append("]");
		
		StringBuilder builder = new StringBuilder("")
			.append(name + ";").append(surname + ";").append(telephone + ";").append(email+ ";" + "\n");
		
		return builder.toString();
	}
}
