package it.beije.ananke.rubricajdbc;

public class Contact {
    private String firstName;
    private String lastName;
    private String phoneNumber;
    private String email;
    private int id;

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
