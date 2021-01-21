package it.beije.ananke;

public class Fibonacci {
		private long first = 0;
		private long second = 1;
		
		public void fibonacci(int number) {
			long next = 0;
			for(int i = 0; i < number; i++){
				System.out.print(first+ " " );
				next = first + second;
				first = second;
				second = next;
			}
		}
		
		public void reset(){
			this.first=0;
			this.second=1;
		}
}