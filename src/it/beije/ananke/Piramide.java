package it.beije.ananke;

public class Piramide{

	public static void main(String[] args){
		
	for(int i=1  ;i<=6 ; i++){
		
	
			
			for(int j=1 ;j<=i ; j++){
				System.out.print(j);
			}
			System.out.print('\t');
			for(int s=i+1;s<6;s++){System.out.print(" ");}
			
			for(int k=7-i; k>0 ; k--){
				System.out.print(k);
			}
				
				System.out.println();
		
	}
	
	}

}