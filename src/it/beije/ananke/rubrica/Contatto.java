package it.beije.ananke.rubrica;

// C://Users//Padawan09//Desktop//rubrica.xml
// C://Users//Padawan09//Desktop//rubrica.txt

public class Contatto {
	private String nome = "";
	private String cognome = "";
	private String email = "";
	private String telefono = "";
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String toString() {
		return nome + ";" + cognome + ";" + email + ";" + telefono;
	}
	
	public boolean equals(Contatto contattoCercato) {
		boolean checkNome = false;
		boolean checkCognome = false;
		boolean checkEmail = false;
		boolean checkTelefono = false;

		if(contattoCercato.getNome().length() == 0) {
			checkNome = true;
		}else if(contattoCercato.getNome().equals(this.getNome())){
			checkNome = true;
		}else {
			checkNome = false;
		}
		
		if(contattoCercato.getCognome().length() == 0) {
			checkCognome = true;
		}else if(contattoCercato.getCognome().equals(this.getCognome())){
			checkCognome = true;
		}else {
			checkCognome = false;
		}
		
		if(contattoCercato.getEmail().length() == 0) {
			checkEmail = true;
		}else if(contattoCercato.getEmail().equals(this.getEmail())){
			checkEmail = true;
		}else {
			checkEmail = false;
		}
		
		if(contattoCercato.getTelefono().length() == 0) {
			checkTelefono = true;
		}else if(contattoCercato.getTelefono().equals(this.getTelefono())){
			checkTelefono = true;
		}else {
			checkTelefono = false;
		}
		
		if(checkNome && checkCognome && checkEmail && checkTelefono) {
			return true;
		}else {
			return false;
		}
	}
}
