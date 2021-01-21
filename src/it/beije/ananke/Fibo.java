package it.beije.ananke;

public class Fibo {
	

	public static void main(String[] args){
		
		long[] Fibo = new long[50];
		
		for( int i = 0; i < 50 ; i++ ){
			
			if(i==0){
				
				Fibo[i] = (long)i;
				System.out.print(Fibo[i] + " ");
				
			}else if(i==1){
				
				Fibo[i] = (long)i;
				System.out.print(Fibo[i] + " ");
				
			}else{
				
				Fibo[i] = Fibo[i-1] + Fibo[i-2];
				System.out.print(Fibo[i] + " ");
				
			}
			
		}
		
		System.out.println(); 
		System.out.println(); 
		
		
		for( int i = 0 ; i < 50 ; i++ ){
			
			for( int j = 0 ; j < i ; j++ )
				System.out.print(Fibo[j] + " ");
			
			System.out.println();
		}
	}

}
