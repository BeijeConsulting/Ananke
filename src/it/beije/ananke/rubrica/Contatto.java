package it.beije.ananke.rubrica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contatti")
public class Contatto {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name = "name")
	private String nome;
	
	@Column(name = "surname")
	private String cognome;
	
	@Column(name = "telephone")
	private String numeroTel;
	
	@Column(name = "email")
	private String mail;
	
	
	public Contatto(String nome, String cognome, String numeroTel, String mail) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.numeroTel = numeroTel;
		this.mail = mail;
		
	}
	
	public Contatto() {}
	  
	
	public void visualizzaContatto() {
		
		System.out.print(this.getNome() + ";");
		System.out.print(this.getCognome() + ";");
		System.out.print(this.getNumeroTel() + ";");
		System.out.println(this.getMail() + ";");
	}
	
	public int getId() {
		return id;
	}
	public String getNome() {
		return nome;
	}
	public String getCognome() {
		return cognome;
	}
	public String getNumeoroTel() {
		return numeroTel;
	}
	public String getMail() {
		return mail;
	}
	public String getNumeroTel() {
		return numeroTel;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public void setNumeroTel(String numeroTel) {
		this.numeroTel = numeroTel;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public void setCognome(String cognome) {
		this.cognome = cognome;
	}
	public void setMail(String mail) {
		this.mail = mail;
	}
	
	

}
