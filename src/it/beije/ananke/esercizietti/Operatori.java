package it.beije.ananke.esercizietti;

//programmino per gli operatori
public class Operatori{
	public static void main(String[] args){
		int a = Integer.parseInt(args[0]);
		//facciamo una mega espressione solo su a con decremento e incremento post e pre
		int b;
		//  a + a+1 - a+1 + a+1
		b = a + ++a - a++ + --a;
		System.out.println(b);
		//capito benissimo
		int c;
		//  b + b   - b-2 + b-3
		c = b + b-- - --b + --b;
		System.out.println(c);
		//beccata anche questa mini espressione.
		//bisogna solamente starci un poco su.
	}
}