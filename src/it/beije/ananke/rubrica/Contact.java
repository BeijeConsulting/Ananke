package it.beije.ananke.rubrica;

public class Contact {
    private String firstname;
    private String lastName;
    private String phoneNumber;
    private String email;

    public Contact(String firstname, String lastName, String phoneNumber, String email) {
        this.firstname = firstname;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
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
        sb.append(getFirstname()).append("\t")
                .append(getLastName()).append("\t")
                .append(getPhoneNumber()).append("\t")
                .append(getEmail());
        return sb.toString();
    }
}
