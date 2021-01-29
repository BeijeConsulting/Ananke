package it.beije.ananke.esercizietti;

public class DoppiaPiramidina{
	
	public static void main(String[] args){
		
		int num = Integer.parseInt(args[0]);
		
		int count=0;
		
		/*
		for(int i=0; i<num; i++){
			count++;
			for(int j=0; j<count; j++){
				System.out.print(j+1);
			}
			for(int j=0; j<num; j++)
				System.out.print(" ");
			for(int j=num; j>count; j--){
				System.out.print(j);
			}
			
			System.out.print("\n");
		}
		*/
		
		for(int i=0; i<num; i++){
			count++;
			for(int j=0; j<count; j++){
				System.out.print(j+1);
			}
			for(int j=0; j<num; j++)
				System.out.print(" ");
			for(int j=num-count+1; j>0; j--){
				System.out.print(j);
			}
			
			System.out.print("\n");
		}
	}
}