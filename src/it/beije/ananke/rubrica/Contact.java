package it.beije.ananke.rubrica;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;

    public Contact() {

    }

    public Contact(String firstName, String lastName, String phoneNumber, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phoneNumber = phoneNumber;
        this.email = email;
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
        sb.append(getFirstName()).append("\t")
                .append(getLastName()).append("\t")
                .append(getPhoneNumber()).append("\t")
                .append(getEmail());
        return sb.toString();
    }

    public boolean equals(Contact c) {
        if (c.getFirstName().equalsIgnoreCase(firstName) &&
                c.getLastName().equalsIgnoreCase(lastName) &&
                c.getPhoneNumber().equalsIgnoreCase(phoneNumber) &&
                c.getEmail().equalsIgnoreCase(email)) {
            return true;
        }
        return false;
    }
}
