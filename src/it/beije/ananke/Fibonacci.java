package it.beije.ananke;
public class Fibonacci{
	public static void main(String[] args){
		long somma= 0L;
		long k =0;
		long j =1;
		int i=1;
		for(i=1;i<5;i++){
			int z=1;
			k=0;
			j=1;
			System.out.print(k + " ");
			System.out.print(j + " ");
			while(z<=i){
				somma=k+j;
				System.out.print(somma + " ");
				k=j;
				j=somma;
				z++;
			}
			System.out.println();
		}
	}
}