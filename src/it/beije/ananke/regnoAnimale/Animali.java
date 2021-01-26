package it.beije.ananke.regnoAnimale;

public abstract class Animali{
	public String nome;
	public int età;
	
	public Animali(String nome, int età) {
		this.nome = nome;
		this.età = età;
	}

	public String getNome() {
		return this.nome;
	}
	
	public int getEtà() {
		return this.età;
	}
	
	public abstract String verso();
//	public abstract void mangia();
}
