package it.beije.ananke.rubrica;

public class Contatto {
	
	private String nome;
	private String cognome;
	private String numeroTel;
	private String mail;
	
	
	public Contatto(String nome, String cognome, String numeroTel, String mail) {
		
		this.nome = nome;
		this.cognome = cognome;
		this.numeroTel = numeroTel;
		this.mail = mail;
		
	}
	
	public Contatto() {}
	  
	
	public void visualizzaContatto() {
		
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
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
