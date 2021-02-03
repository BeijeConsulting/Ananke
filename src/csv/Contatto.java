package csv;

import javax.persistence.Column;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="contatti")
public class Contatto {
@Id
@GeneratedValue(strategy=GenerationType.IDENTITY)
@Column(name="id") 
int id;
@Column(name="name")
private String nome;
@Column(name="surname")
private String cognome;
@Column(name="telephone")
private String tel;
@Column(name="email")
private String email;
public Contatto() {

	}
public Contatto(String nome, String cognome, String tel, String email) {
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


public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}


}
