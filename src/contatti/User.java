package contatti;

public class User {
	
	private int id;
	private String firstName;
	private String lastName;
	private String email;
	private String phoneNumber;
	
	public User(int id,String firstName, String lastName, String email, String phoneNumber) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.phoneNumber = phoneNumber;
		this.id=id;
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

	
	
}
