package csv;

public class Contatto implements Comparable{
private String nome;
private String cognome;
private String tel;
private String email;
public Contatto(String nome, String cognome, String tel, String email) {
	super();
	this.nome = nome;
	this.cognome = cognome;
	this.tel = tel;
	this.email = email;
}
public String getNome() {
	return nome;
}
public void setNome(String nome) {
	this.nome = nome;
}
public String getCognome() {
	return cognome;
}
public void setCognome(String cognome) {
	this.cognome = cognome;
}
public String getTel() {
	return tel;
}
public void setTel(String tel) {
	this.tel = tel;
}
public String getEmail() {
	return email;
}
public void setEmail(String email) {
	this.email = email;
}
@Override
public String toString() {
	return nome+";"+cognome+";"+email+";"+tel;
}
@Override
public int compareTo(Object o) {
Contatto c= (Contatto)o;
	return nome.compareTo(c.getNome());
	
}

}
