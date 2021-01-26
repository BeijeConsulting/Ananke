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

}
