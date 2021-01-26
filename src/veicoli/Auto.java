package veicoli;

public class Auto extends VeicoliAMotore{

protected String modello;
protected String marca;
protected int nSportelli;
public Auto(int numPassegeri, int cc, int kw, String alimentazione, String modello, String marca, int nSportelli) {
	super(numPassegeri, cc, kw, alimentazione);
	this.modello = modello;
	this.marca = marca;
	this.nSportelli = nSportelli;
}

}
