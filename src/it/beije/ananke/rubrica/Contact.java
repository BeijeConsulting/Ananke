package it.beije.ananke.rubrica;

public class Contact {

    private String name;
    private String surname;
    private String telephone;
    private String email;


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

    @Override
    public String toString() {
        StringBuilder contact = new StringBuilder();
        contact.append(name)
                .append(";")
                .append(surname)
                .append(";")
                .append(telephone)
                .append(";")
                .append(email)
                .append("\n");

        return contact.toString();
    }
}
