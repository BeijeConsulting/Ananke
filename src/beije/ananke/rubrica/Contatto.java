package beije.ananke.rubrica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "contatti")
public class Contatto {
	
	
	//CONSTRUCTOR
	public Contatto()
	{
		name = " ";
		lastName = " ";
		phone = " ";
		email=" ";
	}
	//GETTERS AND SETTERS
	
	
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

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	

	//TO STRING
	@Override
	public String toString() {
		
		StringBuilder builder = new StringBuilder("").
				append("Nome: ").append(this.getName()+"\n").
				append("Cognome: ").append(this.getLastName()+"\n").
				append("Telefono: ").append(this.getPhone()+"\n").
				append("Email: ").append(this.getEmail()+"\n");
		
		return builder.toString();	
	}


	//ISTANCE FIELDS
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="name")
	private String name;
	
	@Column(name="surname")
	private String lastName;
	
	@Column(name="telephone")
	private String phone;
	
	@Column(name="email")
	private String email;
	
}
