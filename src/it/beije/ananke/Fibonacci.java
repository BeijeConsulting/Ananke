package it.beije.ananke;
public class Fibonacci{
	
	public void calcolo(int n){
		int primo=0, secondo=1, terzo;
		System.out.print(primo + " " + secondo); 

		for(int i=2; i<n; i++){
			terzo = primo + secondo;
			System.out.print(" " + terzo);
			primo = secondo;    
			secondo = terzo;	
		}
	}
	
	public static void main (String[] args){
		Fibonacci f = new Fibonacci();
		f.calcolo(50);
	}
}