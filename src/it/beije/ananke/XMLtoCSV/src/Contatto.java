
public class Contatto {

	public String name;
	public String surname;
	public String email;
	public String telephone;
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
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getTelephone() {
		return telephone;
	}
	
	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getName()).append(",");
		sb.append(this.getSurname()).append(",");
		sb.append(this.getEmail()).append(",");
		sb.append(this.getTelephone());
		
		return sb.toString();
	}
	
	
}
