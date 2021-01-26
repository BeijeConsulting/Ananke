package veicoli;

public abstract class VeicoliAMotore extends Veicoli {

protected int cc;
protected int kw;
protected String alimentazione;
public VeicoliAMotore(int numPassegeri, int cc, int kw, String alimentazione) {
	super(numPassegeri);
	this.cc = cc;
	this.kw = kw;
	this.alimentazione = alimentazione;
}


}
