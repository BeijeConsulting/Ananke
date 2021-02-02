package it.beije.ananke.file;

public class Contatto {
	
	private int id;
	private String name = "";
	private String surname = "";
	private String telephone = "";
	private String email = "";
	
	
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
		
		StringBuilder builder = new StringBuilder("Contatto n° " + id)
				.append("\nname : ").append(name + " ")
				.append("\nsurname : ").append(surname + " ")
				.append("\ntelephone : ").append(telephone + " ")
				.append("\nemail : ").append(email);
		
		return builder.toString();
	}
}