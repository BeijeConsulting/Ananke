package animali;

public abstract class Vertebrati  extends Animali {
protected int numVertebre;

public Vertebrati(int age, int numVertebre) {
	super(age);
	this.numVertebre = numVertebre;
}



}
