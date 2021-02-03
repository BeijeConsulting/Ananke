package it.beije.ananke.rubrica;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contatti")
public class Contact {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	
	@Column(name="first_name")
    private String firstName;
	
	@Column(name="last_name")
    private String lastName;
	
	@Column(name="phone_number")
    private String phoneNumber;
	
	@Column(name="email")
    private String email;
    

    public Contact() {

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getId()).append("\t")
                .append(getFirstName()).append("\t")
                .append(getLastName()).append("\t")
                .append(getPhoneNumber()).append("\t")
                .append(getEmail());
        return sb.toString();
    }

    public boolean equals(Contact c) {
        if (c.getFirstName().equalsIgnoreCase(firstName) &&
                c.getLastName().equalsIgnoreCase(lastName) &&
                c.getPhoneNumber().equalsIgnoreCase(phoneNumber) &&
                c.getEmail().equalsIgnoreCase(email) &&
                c.getId() == id) {
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
