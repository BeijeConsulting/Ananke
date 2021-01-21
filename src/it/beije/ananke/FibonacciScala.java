package it.beije.ananke;
public class FibonacciScala{
	public void calcolo(int n){
		int primo=0, secondo=1, terzo;
		String[] numbers = new String[50];
		
		numbers[0] = primo + "";
		numbers[1] = primo + " " + secondo;

		for(int i=2; i<n; i++){
			terzo = primo + secondo;
			numbers[i] = numbers[i-1] + " " + terzo;
			primo = secondo;    
			secondo = terzo;	
		}
		for(String number:numbers){
			System.out.println(number);

		}
	}
	
	public static void main (String[] args){
		FibonacciScala fs = new FibonacciScala();
		fs.calcolo(50);
		
	}
}