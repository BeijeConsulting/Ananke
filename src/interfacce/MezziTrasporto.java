package interfacce;

public abstract class MezziTrasporto {
	
	public int posti;
	public String nome;
	public String marca;
	
	public abstract void dettagliVeicolo();
	
	public void setPosti(int num) {
		if(num > 0) {
			this.posti = num;
		}else {
			System.out.println("Attenzione! Il numero di posti indicato non è valido!");
		}
	}
}
