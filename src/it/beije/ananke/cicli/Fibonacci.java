package it.beije.ananke.cicli;

public class Fibonacci{
	long[] seq;

	public static void main(String[] args){
		Fibonacci fib = new Fibonacci();
		int j = 0;
		while(j < 50){
			for(int i = 0; i <= j; i++){	
				if(i != 0){
					System.out.print(", ");
				}		
				System.out.print(fib.seq[i]);
			}
			System.out.print("\n");
			j++;
		}
	}

	public Fibonacci(){
		seq = new long[50];
		seq[0] = 0;
		seq[1] = 1;
		for(int i = 2; i < 50; i++){
			seq[i] = seq[i - 1] + seq[i - 2];
		}
	}
}