package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contatti")
public class User {
	
	@Id
	@Column(name="id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name="firstName")
	private String firstName;
	
	@Column(name="lastName")
	private String lastName;
	
	@Column(name="email")
	private String email;
	
	@Column(name="phoneNumber")
	private String phoneNumber;
	
	public User(String firstName, String lastName, String email, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		
	}
	public User() {
		
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
    
	public String toString() {
		StringBuilder sb = new StringBuilder("[ ");
		sb.append("firstName : ").append(this.firstName);
		sb.append("; lastName : ").append(this.lastName);
		sb.append("; email : ").append(this.email);
		sb.append("; phoneNumber : ").append(this.phoneNumber);
		sb.append(" ]").append('\n');
		
		return sb.toString();
	}
	public void displayUser() {
		System.out.println("firstName : "+ this.firstName);
		   System.out.println("lastName : "+ this.lastName);
		   System.out.println("email : "+ this.email);
		   System.out.println("phoneNumber : "+ this.phoneNumber);
	}

	
	
}
