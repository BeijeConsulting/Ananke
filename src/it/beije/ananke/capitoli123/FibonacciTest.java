package it.beije.ananke.capitoli123;

public class FibonacciTest{
	public static void main(String[] args){
		Fibonacci fib=new Fibonacci();
		
		//fib.fibonacci(50);
		//System.out.println();
		//System.out.println();
		
		for(int i= 1; i<=50; i++) {
			//System.out.println("iterazione #" + i);
			fib.reset();
			fib.fibonacci(i);
			System.out.println();
			//System.out.println();
		}
	}
}