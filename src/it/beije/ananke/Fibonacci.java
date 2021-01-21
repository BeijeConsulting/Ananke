package it.beije.ananke;

public class Fibonacci{

	public static void main(String[] args){
		
	long fbn1=0, fbn2=1, fbnsum;
	
		System.out.println(fbn1);
	
		for(int i=1;i<49;i++)
		{
			fbnsum = fbn1 + fbn2;
			
			System.out.println(fbnsum);
			
			fbn1=fbn2;
			fbn2 = fbnsum;
			
		}
		
	}
		
}