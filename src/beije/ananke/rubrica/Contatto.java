package beije.ananke.rubrica;

public class Contatto {
	
	//GETTERS AND SETTERS
	
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
		
		StringBuilder builder = new StringBuilder("[ ").
				append("Name:").append(this.getName()).
				append(" LastName:").append(this.getLastName()).
				append(" Phone:").append(this.getPhone()).
				append(" Email:").append(this.getEmail()).
				append(" ]");
		
		return builder.toString();
		
	}


	//ISTANCE FIELDS
	private String name,lastName,phone,email;
}
