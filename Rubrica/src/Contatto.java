
public class Contatto {

	private int id;
	
	private String name;
	private String surname;
	private String telephone;
	private String email;
	
	public Contatto() {
		
	}
	
	public Contatto(String name, String surname, String telephone, String email) {
		this.name = name;
		this.surname = surname;
		this.telephone = telephone;
		this.email = email;
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
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		
		return name + ";" + surname + ";" + email + ";" + telephone;
		
	}
	
	public String stampa() {
		
		return "nome: " + name + " cognome: " + surname + " telefono: " + telephone + " email: " + email;
		
	}
	
}
