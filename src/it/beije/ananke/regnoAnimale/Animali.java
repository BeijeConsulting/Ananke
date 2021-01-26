package it.beije.ananke.regnoAnimale;

public abstract class Animali{
	public String nome;
	public int et�;
	
	public Animali(String nome, int et�) {
		this.nome = nome;
		this.et� = et�;
	}

	public String getNome() {
		return this.nome;
	}
	
	public int getEt�() {
		return this.et�;
	}
	
	public abstract String verso();
//	public abstract void mangia();
}
